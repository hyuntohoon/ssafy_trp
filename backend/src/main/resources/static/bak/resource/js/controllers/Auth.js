import { Nav } from "../components/Nav.js";
import Fetch from "../utils/fetch.js";

export class Auth {
    constructor() {
        if (Auth.instance instanceof Auth) {
            return Auth.instance;
        }
        this.isLoggedIn = false;
        this.userId = null;
        this.userName = null;

        Auth.checkLogin();
        Auth.instance = this;
    }

    static checkLogin() {
        let cookie = document.cookie;
        let cookieArray = cookie.split("; ");
        let flag = false;
        for (let i = 0; i < cookieArray.length; i++) {
            let key = cookieArray[i].split("=")[0];
            let value = cookieArray[i].split("=")[1];
            if (key === "userId") {
                this.userId = value;
                flag = true;
            } else if (key === "userName") {
                this.userName = value;
            }
        }
        this.isLoggedIn = flag;
    }

    static async signIn(id, pw) {
        let formData = {
            id: id,
            pw: pw,
            action: "signin",
        };

        let queryString = Object.entries(formData)
            .map(([key, value]) => `${key}=${value}`)
            .join("&");

        let result = await Fetch.postRequest("/user", queryString);

        if (result.status === 200) {
            return true;
        }

        return false;
    }

    static async signUp(id, pw, name) {
        let formData = {
            id: id,
            pw: pw,
            name: name,
            action: "signup",
        };

        let queryString = Object.entries(formData)
            .map(([key, value]) => `${key}=${value}`)
            .join("&");

        let result = await Fetch.postRequest("/user", queryString);

        if (result.status === 200) {
            return true;
        }

        return false;
    }

    static async signOut() {
        let result = await Fetch.getRequest("/user?action=signout");

        if (result.status !== 200) {
            return false;
        }

        return true;
    }

    static async changePW(pw) {
        let formData = {
            pw: pw,
            action: "changePW",
        };

        let queryString = Object.entries(formData)
            .map(([key, value]) => `${key}=${value}`)
            .join("&");

        let result = await Fetch.postRequest("/user", queryString);

        if (result.status === 200) {
            return true;
        }

        return false;
    }

    static async withdraw() {
        let result = await Fetch.getRequest("/user?action=withdraw");

        if (result.status !== 200) {
            return false;
        }

        return true;
    }
}
