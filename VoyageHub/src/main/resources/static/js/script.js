let searchBtn = document.querySelector("#search-btn");
let searchBar = document.querySelector(".search-bar-container");
let formBtn = document.querySelector("#login-btn");
let loginForm = document.querySelector(".login-form-container");
let formClose = document.querySelector("#form-close");
let registerForm = document.getElementById("register-form");
let toggleRegisterLink = document.querySelector("#toggle-register");
let menu = document.querySelector("#menu-bar");
let navbar = document.querySelector(".navbar");
let videoBtn = document.querySelectorAll(".vid-btn");
let registerFormContainer = document.querySelector(".register-form-container");

document.addEventListener("DOMContentLoaded", function () {
  window.onscroll = () => {
    searchBtn.classList.remove("fa-times");
    searchBar.classList.remove("active");
    menu.classList.remove("fa-times");
    navbar.classList.remove("active");
    // No quitar la clase 'active' del contenedor de formulario de registro al hacer scroll
  };

  menu.addEventListener("click", () => {
    menu.classList.toggle("fa-times");
    navbar.classList.toggle("active");
  });

  searchBtn.addEventListener("click", () => {
    searchBtn.classList.toggle("fa-times");
    searchBar.classList.toggle("active");
  });

  formBtn.addEventListener("click", () => {
    loginForm.classList.add("active");
  });

  toggleRegisterLink.addEventListener("click", function (event) {
    event.preventDefault();
    loginForm.style.display = "none";
    registerForm.style.display = "block";
    registerFormContainer.classList.add("active");
  });

  formClose.addEventListener("click", () => {
    loginForm.classList.remove("active");
    registerFormContainer.classList.remove("active"); // Asegúrate de que se quite la clase 'active' al cerrar el formulario de inicio de sesión
  });

  videoBtn.forEach((btn) => {
    btn.addEventListener("click", () => {
      document.querySelector(".controls .active").classList.remove("active");
      btn.classList.add("active");
      let src = btn.getAttribute("data-src");
      document.querySelector("#video-slider").src = src;
    });
  });
});
