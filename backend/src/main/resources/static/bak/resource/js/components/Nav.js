import { Auth } from "../controllers/Auth.js";
import { GlassButton } from "./GlassButton.js";
import { Modal } from "./Modal.js";

export class Nav {
    constructor() {
        if (Nav.instance instanceof Nav) {
            return Nav.instance;
        }
        this.nav = null;
        this.isLoggedIn = false;
        Nav.instance = this;
    }

    render() {
        this.nav = document.createElement("div");
        this.nav.innerHTML = `
            <div class="blur-box header-box">
                <span class="logo">Enjoy Trip</span>
                <div id="login-group">
                </div>
            </div>
        `;
        this.updateLoginGroup();
        return this.nav;
    }

    update(isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
        console.log(this.isLoggedIn);
        this.updateLoginGroup();
    }

    updateLoginGroup() {
        const loginGroup = this.nav.querySelector("#login-group");
        loginGroup.innerHTML = "";

        if (this.isLoggedIn) {
            const myPageButton = new GlassButton(
                "마이페이지",
                () => Modal.activate("myPage"),
                "0 0 0 0.5rem"
            );
            const signOutButton = new GlassButton(
                "로그아웃",
                async () => {
                    let res = await Auth.signOut();
                    if (res) {
                        this.update(false);
                    }
                },
                "0 0 0 0.5rem"
            );
            loginGroup.appendChild(myPageButton.render());
            loginGroup.appendChild(signOutButton.render());
        } else {
            const signInButton = new GlassButton(
                "로그인",
                () => Modal.activate("signIn"),
                "0 0 0 0.5rem"
            );
            const signUpButton = new GlassButton(
                "회원가입",
                () => Modal.activate("signUp"),
                "0 0 0 0.5rem"
            );
            loginGroup.appendChild(signInButton.render());
            loginGroup.appendChild(signUpButton.render());
        }
    }
}
