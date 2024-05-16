import { Nav } from "./components/Nav.js";
import { Modal } from "./components/Modal.js";
import { Auth } from "./controllers/Auth.js";
import { SearchBar } from "./components/SearchBar.js";
import { Toast } from "./components/Toast.js";
import { Planner } from "./components/Planner.js";

const nav = document.querySelector("#nav");
const isLoggedIn = nav.dataset.isLoggedIn === "true";
const navComponent = new Nav(isLoggedIn);
nav.appendChild(navComponent.render());

const modalInstance = new Modal();

const authInstance = new Auth();

const plannerInstance = new Planner();

let buttonKeyword = document.getElementById("button-keyword");
buttonKeyword.addEventListener("click", function () {
    searchBarInstance.toggle();
});

let buttonCourse = document.getElementById("button-course");
buttonCourse.addEventListener("click", function () {
    plannerInstance.showPlanner();
});

let searchBarInstance = new SearchBar();
let toastInstance = new Toast();
