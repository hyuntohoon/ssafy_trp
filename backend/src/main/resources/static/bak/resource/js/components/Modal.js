import { GlassButton } from "./GlassButton.js";
import { Auth } from "../controllers/Auth.js";
import { Toast } from "./Toast.js";
import { Nav } from "./Nav.js";

export class Modal {
    constructor() {
        if (Modal.instance instanceof Modal) {
            return Modal.instance;
        }

        this.modal = document.getElementById("modal");
        this.modal.setAttribute("hidden", true);
        this.modal.innerHTML = "";

        Modal.instance = this;
    }

    static activate(content) {
        const modal = document.getElementById("modal");

        document.getElementById("main").classList.add("blurOn");
        document.getElementById("main").classList.remove("blurOff");

        switch (content) {
            case "signIn":
                this.buildSignIn();
                break;
            case "signUp":
                this.buildSignUp();
                break;
            case "myPage":
                this.buildMyPage();
                break;
            case "withdraw":
                this.buildWithdraw();
                break;
            case "passwordChange":
                this.buildPasswordChange();
                break;
            default:
                return;
        }

        modal.removeAttribute("hidden");
    }

    static deactivate() {
        const modal = document.getElementById("modal");
        modal.innerHTML = "";
        modal.setAttribute("hidden", true);

        document.getElementById("main").classList.remove("blurOn");
        document.getElementById("main").classList.add("blurOff");
    }

    static buildSignIn() {
        const modal = document.getElementById("modal");
        modal.innerHTML = "";

        let title = document.createElement("h1");
        title.classList.add("mb-3");
        title.textContent = "회원 가입";
        modal.appendChild(title);

        let idInputGroup = document.createElement("div");
        idInputGroup.classList.add("input-group");
        idInputGroup.classList.add("mb-3");

        let idInput = document.createElement("input");
        idInput.setAttribute("type", "text");
        idInput.classList.add("form-control");
        idInput.setAttribute("placeholder", "아이디");
        idInput.setAttribute("id", "SignInID");
        idInputGroup.appendChild(idInput);

        modal.appendChild(idInputGroup);

        let pwInputGroup = document.createElement("div");
        pwInputGroup.classList.add("input-group");
        pwInputGroup.classList.add("mb-3");

        let pwInput = document.createElement("input");
        pwInput.setAttribute("type", "password");
        pwInput.classList.add("form-control");
        pwInput.setAttribute("placeholder", "비밀번호");
        pwInput.setAttribute("id", "SignInPW");
        pwInputGroup.appendChild(pwInput);

        modal.appendChild(pwInputGroup);

        let buttonWrap = document.createElement("div");
        buttonWrap.classList.add("button-wrap");

        let SignInButton = new GlassButton(
            "로그인",
            async () => {
                let id = document.getElementById("SignInID").value;
                let pw = document.getElementById("SignInPW").value;

                let result = await Auth.signIn(id, pw);

                if (result) {
                    this.deactivate();
                    Nav.instance.update(true);
                } else {
                    Toast.show(
                        "로그인 실패",
                        "아이디와 비밀번호를 확인해주세요."
                    );
                }
            },
            "0 0.5rem",
            true
        );
        buttonWrap.appendChild(SignInButton.render());

        let closeButton = new GlassButton(
            "닫기",
            this.deactivate,
            "0 0.5rem",
            false
        );
        buttonWrap.appendChild(closeButton.render());

        modal.appendChild(buttonWrap);
    }

    static buildSignUp() {
        const modal = document.getElementById("modal");
        modal.innerHTML = "";

        let title = document.createElement("h1");
        title.classList.add("mb-3");
        title.textContent = "회원 가입";
        modal.appendChild(title);

        let idInputGroup = document.createElement("div");
        idInputGroup.classList.add("input-group");
        idInputGroup.classList.add("mb-3");

        let idInput = document.createElement("input");
        idInput.setAttribute("type", "text");
        idInput.classList.add("form-control");
        idInput.setAttribute("placeholder", "아이디");
        idInput.setAttribute("id", "SignUpID");
        idInputGroup.appendChild(idInput);

        modal.appendChild(idInputGroup);

        let nameInputGroup = document.createElement("div");
        nameInputGroup.classList.add("input-group");
        nameInputGroup.classList.add("mb-3");

        let nameInput = document.createElement("input");
        nameInput.setAttribute("type", "text");
        nameInput.classList.add("form-control");
        nameInput.setAttribute("placeholder", "닉네임");
        nameInput.setAttribute("id", "SignUpName");
        nameInputGroup.appendChild(nameInput);

        modal.appendChild(nameInputGroup);

        let pwInputGroup = document.createElement("div");
        pwInputGroup.classList.add("input-group");
        pwInputGroup.classList.add("mb-3");

        let pwInput = document.createElement("input");
        pwInput.setAttribute("type", "password");
        pwInput.classList.add("form-control");
        pwInput.setAttribute("placeholder", "비밀번호");
        pwInput.setAttribute("id", "SignUpPW");
        pwInputGroup.appendChild(pwInput);

        modal.appendChild(pwInputGroup);

        let pwcInputGroup = document.createElement("div");
        pwcInputGroup.classList.add("input-group");
        pwcInputGroup.classList.add("mb-3");

        let pwcInput = document.createElement("input");
        pwcInput.setAttribute("type", "password");
        pwcInput.classList.add("form-control");
        pwcInput.setAttribute("placeholder", "비밀번호 확인");
        pwcInput.setAttribute("id", "SignUpPWC");
        pwcInputGroup.appendChild(pwcInput);

        modal.appendChild(pwcInputGroup);

        let buttonWrap = document.createElement("div");
        buttonWrap.classList.add("button-wrap");

        let signUpButton = new GlassButton(
            "회원가입",
            async () => {
                let id = document.getElementById("SignUpID").value;
                let name = document.getElementById("SignUpName").value;
                let pw = document.getElementById("SignUpPW").value;
                let pwc = document.getElementById("SignUpPWC").value;

                if (pw !== pwc) {
                    Toast.show(
                        "회원가입 실패",
                        "비밀번호가 일치하지 않습니다."
                    );
                    return;
                }

                let result = await Auth.signUp(id, name, pw);
                console.log(result);
                if (result) {
                    this.deactivate();
                } else {
                    Toast.show(
                        "회원가입 실패",
                        "아이디와 닉네임을 확인해주세요."
                    );
                }
            },
            "0 0.5rem",
            true
        );
        buttonWrap.appendChild(signUpButton.render());

        let closeButton = new GlassButton(
            "닫기",
            this.deactivate,
            "0 0.5rem",
            false
        );
        buttonWrap.appendChild(closeButton.render());

        modal.appendChild(buttonWrap);
    }

    static buildMyPage() {
        const modal = document.getElementById("modal");
        modal.innerHTML = "";

        let title = document.createElement("h1");
        title.textContent = "마이 페이지";
        modal.appendChild(title);

        let myPage = document.createElement("div");
        myPage.style.width = "100%";

        let myPageID = document.createElement("div");
        myPageID.className = "mypage-item";
        myPageID.innerHTML = `
            <span>아이디</span>
        `;
        let myPageIDValue = document.createElement("span");
        myPageIDValue.textContent = Auth.userId;
        myPageID.appendChild(myPageIDValue);
        myPage.appendChild(myPageID);

        let myPageName = document.createElement("div");
        myPageName.className = "mypage-item";
        myPageName.innerHTML = `
            <span>이름</span>
        `;
        let myPageNameValue = document.createElement("span");
        myPageNameValue.textContent = Auth.userName;
        myPageName.appendChild(myPageNameValue);
        myPage.appendChild(myPageName);

        let myPagePWChange = document.createElement("div");
        myPagePWChange.className = "mypage-item";
        myPagePWChange.innerHTML = `
            <span>비밀번호 변경</span>
            <a class="text-button">변경</a>
        `;
        myPagePWChange.querySelector("a").addEventListener("click", () => {
            this.deactivate();
            Modal.activate("passwordChange");
        });
        myPage.appendChild(myPagePWChange);

        let myPageWithdraw = document.createElement("div");
        myPageWithdraw.className = "mypage-item";
        myPageWithdraw.innerHTML = `
            <span>회원탈퇴</span>
            <a class="text-button">탈퇴</a>
        `;
        myPageWithdraw.querySelector("a").addEventListener("click", () => {
            this.deactivate();
            Modal.activate("withdraw");
        });
        myPage.appendChild(myPageWithdraw);

        modal.appendChild(myPage);

        let buttonWrap = document.createElement("div");
        buttonWrap.classList.add("button-wrap");

        let closeButton = new GlassButton(
            "닫기",
            this.deactivate,
            "0.5rem",
            false
        );
        buttonWrap.appendChild(closeButton.render());

        modal.appendChild(buttonWrap);
    }

    static buildWithdraw() {
        const modal = document.getElementById("modal");
        modal.innerHTML = "";

        let title = document.createElement("h1");
        title.textContent = "회원탈퇴";
        modal.appendChild(title);

        let buttonWrap = document.createElement("div");
        buttonWrap.classList.add("button-wrap");

        let withdrawButton = new GlassButton(
            "회원탈퇴",
            async () => {
                let result = await Auth.withdraw();
                if (result) {
                    Nav.instance.update(false);
                    this.deactivate();
                } else {
                    Toast.show(
                        "탈퇴 실패",
                        "서버 오류입니다. 잠시 후 다시 시도해주세요."
                    );
                }
            },
            "0 0.5rem",
            true
        );
        buttonWrap.appendChild(withdrawButton.render());

        let closeButton = new GlassButton("닫기", this.deactivate, "", false);
        buttonWrap.appendChild(closeButton.render());

        modal.appendChild(buttonWrap);
    }

    static buildPasswordChange() {
        const modal = document.getElementById("modal");
        modal.innerHTML = "";

        let title = document.createElement("h1");
        title.textContent = "비밀번호 변경";
        modal.appendChild(title);

        let div = document.createElement("div");
        div.innerHTML = `
        <div class="input-group mb-3 mt-3">
        <input
            type="password"
            class="form-control"
            placeholder="비밀번호"
            aria-label="비밀번호"
            aria-describedby="button-addon2"
            id="changePW" />
        </div>
        <div class="input-group mb-3">
        <input
            type="password"
            class="form-control"
            placeholder="비밀번호 확인"
            aria-label="비밀번호 확인"
            aria-describedby="button-addon2"
            id="changePWC" />
        </div>
        `;
        modal.appendChild(div);

        let buttonWrap = document.createElement("div");
        buttonWrap.classList.add("button-wrap");

        let changeButton = new GlassButton(
            "변경",
            async () => {
                let pw = document.getElementById("changePW").value;
                let pwc = document.getElementById("changePWC").value;

                if (pw !== pwc) {
                    Toast.show(
                        "비밀번호 변경 실패",
                        "비밀번호가 일치하지 않습니다."
                    );
                    return;
                }

                let result = await Auth.changePW(pw);
                if (result) {
                    this.deactivate();
                } else {
                    Toast.show(
                        "비밀번호 변경 실패",
                        "서버 오류입니다. 잠시 후 다시 시도해주세요."
                    );
                }
            },
            "0 0.5rem",
            true
        );
        buttonWrap.appendChild(changeButton.render());

        let closeButton = new GlassButton("닫기", this.deactivate, false);
        buttonWrap.appendChild(closeButton.render());

        modal.appendChild(buttonWrap);
    }
}
