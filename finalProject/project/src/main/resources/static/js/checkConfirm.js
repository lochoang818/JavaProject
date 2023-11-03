//check confirm password #e20714

function checkConfirm() {
    var password = document.getElementById("password").value.trim();
    var confirmPassword = document.getElementById("confirm").value.trim();
    var username = document.getElementById("username").value.trim();
    var email = document.getElementById("email").value.trim();
    var phone = document.getElementById("phone").value.trim();
    var address = document.getElementById("address").value.trim();
    var signup = document.getElementById("createButton");
    var validEmail =
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var validPhone =
        /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;
    var validUsername = /^[a-zA-Z0-9]+$/;

    if (
        password == confirmPassword &&
        check() == 4 &&
        email.match(validEmail) &&
        username.match(validUsername) &&
        phone.match(validPhone) &&
        address != ""
    ) {
        signup.style.pointerEvents = "auto";
        signup.style.backgroundColor = "red";
        $("#createButton").hover(
            function () {
                $(this).css({ backgroundColor: " #e20714" });
            },
            function () {
                $(this).css({ backgroundColor: "red" });
            }
        );
        signup.disabled = false;
    } else {
        signup.style.backgroundColor = "#7e7e7e";
        signup.style.pointerEvents = "none";
        signup.disabled = true;
    }
}

function check() {
    var password = document.getElementById("password").value;
    password = password.trim();

    let count = 0;

    if (password.length >= 8) {
        document.getElementById("check1").style.color = "#90EE90";
        document.getElementById("check1").style.fontWeight = "900";
        count++;
    } else {
        document.getElementById("check1").style.color = "#FFF";
        document.getElementById("check1").style.fontWeight = "200";
        count--;
    }
    if (password.match(/^(?=.*[a-zA-Z])(?=.*\d).+$/)) {
        document.getElementById("check2").style.color = "#90EE90";
        document.getElementById("check2").style.fontWeight = "900";
        count++;
    } else {
        document.getElementById("check2").style.color = "#FFF";
        document.getElementById("check2").style.fontWeight = "200";
        count--;
    }
    if (password.match(/[A-Z]/)) {
        document.getElementById("check3").style.color = "#90EE90";
        document.getElementById("check3").style.fontWeight = "900";
        count++;
    } else {
        document.getElementById("check3").style.color = "#FFF";
        document.getElementById("check3").style.fontWeight = "200";
        count--;
    }

    if (password.match(/[!@#$%^&*]/)) {
        document.getElementById("check4").style.color = "#90EE90";
        document.getElementById("check4").style.fontWeight = "900";
        count++;
    } else {
        document.getElementById("check4").style.color = "#FFF";
        document.getElementById("check4").style.fontWeight = "200";
        count--;
    }
    return count;
}

function check2() {
    var password = document.querySelector(".password").value;

    password = password.trim();

    let count = 0;

    if (password.length >= 8) {
        document.querySelector(".check1").style.color = "#90EE90";
        document.querySelector(".check1").style.fontWeight = "900";
        count++;
    } else {
        document.querySelector(".check1").style.color = "#FFF";
        document.querySelector(".check1").style.fontWeight = "200";
        count--;
    }
    if (password.match(/^(?=.*[a-zA-Z])(?=.*\d).+$/)) {
        document.querySelector(".check2").style.color = "#90EE90";
        document.querySelector(".check2").style.fontWeight = "900";
        count++;
    } else {
        document.querySelector(".check2").style.color = "#FFF";
        document.querySelector(".check2").style.fontWeight = "200";
        count--;
    }
    if (password.match(/[A-Z]/)) {
        document.querySelector(".check3").style.color = "#90EE90";
        document.querySelector(".check3").style.fontWeight = "900";
        count++;
    } else {
        document.querySelector(".check3").style.color = "#FFF";
        document.querySelector(".check3").style.fontWeight = "200";
        count--;
    }

    if (password.match(/[!@#$%^&*]/)) {
        document.querySelector(".check4").style.color = "#90EE90";
        document.querySelector(".check4").style.fontWeight = "900";
        count++;
    } else {
        document.querySelector(".check4").style.color = "#FFF";
        document.querySelector(".check4").style.fontWeight = "200";
        count--;
    }
    return count;
}

function checkConfirm2() {
    var password = document.querySelector(".password").value.trim();
    var confirmPassword = document.querySelector(".confirm").value.trim();
    var email = document.querySelector(".email").value.trim();
    var sendOtp = document.querySelector(".otp").value.trim();
    var signup = document.querySelector(".createButton");
    var validEmail =
        /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (
        password == confirmPassword &&
        check2() == 4 &&
        email.match(validEmail) &&
        sendOtp != ""
    ) {
        signup.style.pointerEvents = "auto";
        signup.style.backgroundColor = "red";
        $(".createButton").hover(
            function () {
                $(this).css({ backgroundColor: " #e20714" });
            },
            function () {
                $(this).css({ backgroundColor: "red" });
            }
        );
        signup.disabled = false;
    } else {
        signup.style.backgroundColor = "#7e7e7e";
        signup.style.pointerEvents = "none";
        signup.disabled = true;
    }
}