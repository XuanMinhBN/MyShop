/* ----------------Login Page---------------- */
/* ----------------Register Page---------------- */
const personal = document.querySelector(".personal-form");
const business = document.querySelector(".business-form");
const form = document.querySelector("#form");
const change = document.querySelectorAll(".switch");

let current = 1;

function tab2() {
    form.style.marginLeft = "-100%";
    personal.style.background = "none";
    personal.style.color = "#000";
    business.style.background = "#000";
    business.style.color = "#fff";
    change[current - 1].classList.add("active");
}

function tab1() {
    form.style.marginLeft = "0";
    business.style.background = "none";
    business.style.color = "#000";
    personal.style.background = "#000";
    personal.style.color = "#fff";
    change[current - 1].classList.remove("active");
}
/* ----------------Home Page---------------- */
/* ----------------Color Mode---------------- */
const sun = `<i class="bi bi-brightness-high-fill"></i>`;
const moon = `<i class="bi bi-moon-stars-fill"></i>`;
const auto = `<i class="bi bi-circle-half"></i>`;

const getStoredTheme = () => localStorage.getItem("theme");
const setStoredTheme = (theme) => localStorage.setItem("theme", theme);

function getCurrentTheme() {
    const theme = getStoredTheme();
    if (theme) {
        return theme;
    }
    return window.matchMedia("(prefers-color-scheme: dark)").matches
        ? "dark"
        : "light";
}

function setTheme(theme) {
    if (theme == "dark") {
        document.documentElement.setAttribute("data-bs-theme", "dark");
        icon.innerHTML = moon;
    } else if (theme == "light") {
        document.documentElement.setAttribute("data-bs-theme", theme);
        icon.innerHTML = sun;
    } else {
        icon.innerHTML = auto;
        if (window.matchMedia("(prefers-color-scheme: dark)").matches) {
            document.documentElement.setAttribute("data-bs-theme", "dark");
        } else {
            document.documentElement.setAttribute("data-bs-theme", theme);
        }
    }
}

function showActionTheme(theme) {
    const linkActive = document.querySelector(`[theme-value="${theme}"]`);

    document.querySelectorAll("[theme-value]").forEach((element) => {
        element.classList.remove("active");
    });
    linkActive.classList.add("active");
}

function getTheme(theme) {
    setStoredTheme(theme);
    setTheme(theme);
    showActionTheme(theme);
}

showActionTheme(getCurrentTheme());
setTheme(getCurrentTheme());

window
    .matchMedia("(prefers-color-scheme: dark)")
    .addEventListener("change", () => {
        const storedTheme = getStoredTheme();
        if (storedTheme !== "light" && storedTheme !== "dark") {
            setTheme(getCurrentTheme());
        }
    });
/* ----------------Weather Status---------------- */
function getWeatherInfo(location) {
    fetch(
        "http://api.weatherapi.com/v1/current.json?key=0462b6c025fc4eba86d124804242603&q=" +
        location +
        "&aqi=no"
    )
        .then((response) => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then((data) => {
            let html =
                data.location.name +
                " " +
                data.current.temp_c +
                " " +
                data.current.condition.text;
            document.getElementById("weather").innerHTML = html;
        })
        .catch((error) => {
            console.error("There was a problem with the fetch operation:", error);
        });
}
getWeatherInfo((mylocation = "Hanoi"));
