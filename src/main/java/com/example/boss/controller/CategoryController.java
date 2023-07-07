package com.example.boss.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.boss.dto.PetDto;
import com.example.boss.entity.Cat;
import com.example.boss.entity.Dog;
import com.example.boss.entity.User;
import com.example.boss.service.PetService;
import com.example.boss.service.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {
	
	private UserService userService;
	private PetService petService;

    public CategoryController(UserService userService, PetService petService) {
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
	@GetMapping("/dogs")
	public String Dogs(Model model){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		List <Dog> dogs= new ArrayList<Dog> ();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_pet", "root","123456");
			statement = connection.createStatement();	
			resultSet = statement.executeQuery("select * from dogs");
			
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				String recommendedFor = resultSet.getString("recommended_for");
				String maintenanceLevel = resultSet.getString("maintenance_level");
				String lifespan = resultSet.getString("lifespan");
				String temperament = resultSet.getString("temperament");
				String healthRisk = resultSet.getString("health_risk");
				String link = resultSet.getString("link");
				String description = resultSet.getString("description");
				dogs.add(new Dog(name,recommendedFor,maintenanceLevel,lifespan,temperament,healthRisk,link,description));		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("dogs", dogs);
		return "category";
	}
	public static List<Dog> getDogs(){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		List <Dog> dogs= new ArrayList<Dog> ();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_pet", "root","123456");
			statement = connection.createStatement();	
			resultSet = statement.executeQuery("select * from dogs");
			
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				String recommendedFor = resultSet.getString("recommended_for");
				String maintenanceLevel = resultSet.getString("maintenance_level");
				String lifespan = resultSet.getString("lifespan");
				String temperament = resultSet.getString("temperament");
				String healthRisk = resultSet.getString("health_risk");
				String link = resultSet.getString("link");
				String description = resultSet.getString("description");
				dogs.add(new Dog(name,recommendedFor,maintenanceLevel,lifespan,temperament,healthRisk,link,description));		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return dogs;
	}
	
	@GetMapping("/dog/{name}")
	public String getDog(Model model, @PathVariable String name) {
		List<PetDto> pets = petService.findPetsByBreed(name);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		Dog dog = new Dog();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_pet", "root","123456");
			ps = connection.prepareStatement("select * from dogs where name = ?");
			ps.setString(1,name);
			result = ps.executeQuery();
			
			while (result.next()) {	
				dog.setName(result.getString("name"));
				dog.setRecommendedFor(result.getString("recommended_for"));	
				dog.setMaintenanceLevel(result.getString("maintenance_level"));
				dog.setLifespan(result.getString("lifespan"));
				dog.setTemperament(result.getString("temperament"));
				dog.setHealthRisk(result.getString("health_risk"));
				dog.setLink(result.getString("link"));
				dog.setDescription(result.getString("description"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("dog", dog);
		model.addAttribute("pets", pets);
		return "category-detail";
	}
	@GetMapping("/cats")
	public String Cats(Model model){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		List <Cat> cats= new ArrayList<Cat> ();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_pet", "root","123456");
			statement = connection.createStatement();	
			resultSet = statement.executeQuery("select * from cats");
			
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				String infomation = resultSet.getString("infomation");
				String link = resultSet.getString("link");
				String description = resultSet.getString("description");
				cats.add(new Cat(name,infomation,link,description));		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("cats", cats);
		return "category";
	}
	public static List<Cat> getCats(){
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		List <Cat> cats= new ArrayList<Cat> ();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_pet", "root","123456");
			statement = connection.createStatement();	
			resultSet = statement.executeQuery("select * from cats");
			
			while(resultSet.next()) {
				String name = resultSet.getString("name");
				String infomation = resultSet.getString("infomation");
				String link = resultSet.getString("link");
				String description = resultSet.getString("description");
				cats.add(new Cat(name,infomation,link,description));		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cats;
	}
	@GetMapping("/cat/{name}")
	public String getCat(@PathVariable String name, Model model) {
		List<PetDto> pets = petService.findPetsByBreed(name);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		Cat cat = new Cat();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_pet", "root","123456");
			ps = connection.prepareStatement("select * from cats where name = ?");
			ps.setString(1,name);
			result = ps.executeQuery();
			
			while (result.next()) {	
				cat.setName(result.getString("name"));
				cat.setInfomation(result.getString("infomation"));	
				cat.setLink(result.getString("link"));
				cat.setDescription(result.getString("description"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("cat", cat);
		model.addAttribute("pets", pets);
		return "category-detail";
	}
}