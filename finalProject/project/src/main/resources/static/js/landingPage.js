const sign_up_btn = document.querySelector("#sign-up-btn");
const sign_in_btn = document.querySelector("#sign-in-btn");
const Section_top = document.querySelector(".Section_top");

sign_up_btn.addEventListener('click', () => {
    Section_top.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener('click', () => {
    Section_top.classList.remove("sign-up-mode");
});

// Check if the URL contains '/register' and show sign-up form
if (window.location.pathname.includes('/register')) {
    Section_top.classList.add("sign-up-mode");
}

