export class GlassButton {
    constructor(text, onClick, margin, isColored = false) {
        this.text = text;
        this.onClick = onClick;
        this.button = null;
        this.margin = margin;
        this.isColored = isColored;
    }

    render() {
        this.button = document.createElement("button");
        this.button.textContent = this.text;
        this.button.onclick = this.onClick;
        this.button.className = this.isColored
            ? "glass-btn-colored"
            : "glass-btn";
        if (this.margin) this.button.style.margin = this.margin;
        return this.button;
    }
}
