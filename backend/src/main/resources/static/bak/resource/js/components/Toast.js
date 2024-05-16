// let toast = bootstrap.Toast.getOrCreateInstance(
//     document.getElementById("liveToast")
// );
// let toastTitle = document.getElementById("toast-title");
// let toastBody = document.getElementById("toast-body");

// export default class ToastController {
//     static show(title, body) {
//         toastTitle.innerHTML = title;
//         toastBody.innerHTML = body;
//         toast.show();
//     }
// }

export class Toast {
    constructor() {
        if (Toast.instance instanceof Toast) {
            return Toast.instance;
        }
        this.toast = bootstrap.Toast.getOrCreateInstance(
            document.getElementById("liveToast")
        );
        this.toast.innerHTML = toast;
        this.toastTitle = document.getElementById("toast-title");
        this.toastBody = document.getElementById("toast-body");

        Toast.instance = this;
    }

    static show(title, body) {
        const instance = new Toast();
        instance.toastTitle.innerHTML = title;
        instance.toastBody.innerHTML = body;
        instance.toast.show();
    }
}
