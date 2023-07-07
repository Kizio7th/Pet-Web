let mybutton = document.getElementById("backtoheader");
    window.onscroll = function () {
      scrollFunction();
    };
    function scrollFunction() {
      if (document.body.scrollTop > 20 ||document.documentElement.scrollTop > 20) {
        mybutton.style.display = "block";
      } else {
        mybutton.style.display = "none";
      }
    }
    function topFunction() {
      document.body.scrollTop = 0;
      document.documentElement.scrollTop = 0;
    }
// ---------------------------------------

// -----------------------------------------------
function previewImage(event) {
  var file = event.target.files[0];
  if (file.size > 1024 * 1024) {
    alert("Image size should not exceed 1MB");
    return;
  }
  
  var reader = new FileReader();
  reader.onload = function() {
    var preview = document.getElementById("thumbnail");
    preview.src = reader.result;
  }
  reader.readAsDataURL(file);
}

function validateForm() {
  const thumbnail = document.getElementById("thumbnail");
  if (thumbnail.getAttribute("src") === "data:image/png;base64,null") {
    alert("Please choose a photo of your pet");
    return false;
  }
  
  return true;
}
//-------------------------------------------------
function getBreed() {
  const speciesSelect = document.getElementById('species');
  const selectedSpecies = speciesSelect.value;
  const dogs = document.querySelectorAll(".dog-breed");
  const cats = document.querySelectorAll(".cat-breed");
  
  if (selectedSpecies === 'dog' ) {
    for (var i = 0; i < dogs.length; i++) {
      dogs[i].style.display = "block";
    }
    for (var i = 0; i < cats.length; i++) {
      cats[i].style.display = "none";
    }
  } else if (selectedSpecies === 'cat') {
    for (var i = 0; i < dogs.length; i++) {
      dogs[i].style.display = "none";
    }
    for (var i = 0; i < cats.length; i++) {
      cats[i].style.display = "block";
    }
  } else {
    for (var i = 0; i < dogs.length; i++) {
      dogs[i].style.display = "none";
    }
    for (var i = 0; i < cats.length; i++) {
      cats[i].style.display = "none";
    }
  }
}
//-----------------------------------------
function searchByBreed() {
  var input, filter, ul, dogList, catList, li, a, i, txtValue;
  input = document.getElementById("searchInput");
  filter = input.value.toUpperCase();

  dogList = document.getElementById("dogList");
  catList = document.getElementById("catList");

  var lists = [dogList, catList]; 

  for (var j = 0; j < lists.length; j++) {
    ul = lists[j];
    li = ul.getElementsByTagName("li");

    for (i = 0; i < li.length; i++) {
      if(li[i].getElementsByTagName("p")[2] == null){
        a = li[i].getElementsByTagName("h2")[0];
      }
      else a = li[i].getElementsByTagName("p")[2];
      txtValue = a.textContent || a.innerText;

      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        li[i].style.display = "";
      } else {
        li[i].style.display = "none";
      }
    }
  }
}



