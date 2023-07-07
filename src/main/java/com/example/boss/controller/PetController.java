package com.example.boss.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.boss.dto.PetDto;
import com.example.boss.entity.Cat;
import com.example.boss.entity.Dog;
import com.example.boss.entity.Pet;
import com.example.boss.entity.User;
import com.example.boss.service.PetService;
import com.example.boss.service.UserService;


@Controller
public class PetController {
	private UserService userService;
	private PetService petService;
	
    public PetController(UserService userService, PetService petService) {
        this.userService = userService;
        this.petService = petService;
    }
    
    @ModelAttribute("currentUser")
    public User currentUser(Authentication auth) {
    	if (auth == null) {
    		return new User();
    	}
    	User currentUser = userService.findUserByEmail(auth.getName());
        return currentUser;
    }
    @ModelAttribute("isAdmin")
    public Boolean isAdmin(Authentication auth) {
    	if (auth == null) {
    		return false;
    	}
        for (GrantedAuthority authority : auth.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }
    
    
    @GetMapping("/pets")
    public String pets(Model model){
        List<PetDto> pets = petService.findAllPets();
        model.addAttribute("pets", pets);
        return "pets";
    }
    
    @GetMapping("/pet/{id}/add")
    public String showAddPetForm(Model model, @PathVariable("id") Long id,Authentication auth){
    	User user = userService.findUserById(id);
    	User currentUser = currentUser(auth);
    	List<Dog> dogs = CategoryController.getDogs();
    	List<Cat> cats = CategoryController.getCats();
        PetDto pet = new PetDto();
        if(currentUser.getId() == id) {
        	model.addAttribute("user", user);
            model.addAttribute("pet", pet);
            model.addAttribute("dogs",dogs);
            model.addAttribute("cats",cats);
            return "add-pet";
        }
        return "redirect:/user/" + String.valueOf(id);
    }
    
    @PostMapping("/pet/{id}/save")
    public String addPet(@ModelAttribute("pet") PetDto petDto,Model model, @PathVariable("id") Long id, @RequestParam("photo") MultipartFile petImage){
    	User user = userService.findUserById(id);
        petService.savePet(petDto, petImage, user);
        return "redirect:/user/" + String.valueOf(user.getId());
    }
    @GetMapping("/pet/{id}/update")
    public String showUpdatePetForm(Model model, @PathVariable("id") Long id,Authentication auth){
    	Pet pet = petService.findPetById(id);
    	PetDto petDto = petService.mapToPetDto(pet);
    	User currentUser = currentUser(auth);
    	List<Dog> dogs = CategoryController.getDogs();
    	List<Cat> cats = CategoryController.getCats();
    	if(currentUser.getId() == pet.getUser().getId()) {
    		model.addAttribute("pet", petDto);
    		model.addAttribute("user", new User());
    		model.addAttribute("dogs",dogs);
            model.addAttribute("cats",cats);
    		return "add-pet";
    	}
    	else {
    		return "redirect:/user/" + String.valueOf(pet.getUser().getId());
    	}
        
    }
    @PostMapping("/pet/update")
    public String updatePet(Model model, PetDto petDto, @RequestParam(name = "id") String id,Authentication auth, @RequestParam("photo") MultipartFile petImage) {
    	petService.updatePet(petDto, petImage, Long.parseLong(id));
    	Pet pet = petService.findPetById(Long.parseLong(id));
    	return "redirect:/user/" + String.valueOf(pet.getUser().getId());
    }
    @PostMapping("/pet/delete/{id}")
    public String deletePet(Model model, @PathVariable("id") Long id, Authentication auth){
    	User currentUser = currentUser(auth);
    	Long userId = petService.findPetById(id).getUser().getId();
    	
    	if(currentUser.getId() == userId || isAdmin(auth)) {
    		petService.deletePet(id);
        	return "redirect:/user/" + String.valueOf(userId);
    	}
    	else {
    		return "redirect:/user/" + String.valueOf(userId);
    	}
    }
}
