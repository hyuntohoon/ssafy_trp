import { Map } from "./Map.js";

export class SearchResult {
    constructor() {
        if (SearchResult.instance instanceof SearchResult) {
            return SearchResult.instance;
        }

        SearchResult.instance = this;

        this.mapInstance = new Map();
        this.toggleMap = document.getElementById("toggle-map");
        this.toggleMap.addEventListener("click", () => {
            this.mapInstance.toggleMap();
        });
        this.searchResultList = document.getElementById("search-result-list");
        this.sortKey = "";
        this.reverse = false; // 오름차순
        this.data = [];

        SearchResult.instance = this;
    }

    setData(json) {
        let positions = [];

        this.data = json;
        let sortWrapper = document.createElement("div");
        let idSort = document.createElement("a");
        idSort.classList.add("text-button");
        idSort.id = "id";
        idSort.innerHTML = "기본";
        idSort.style.marginRight = "10px";
        let titleSort = document.createElement("a");
        titleSort.classList.add("text-button");
        titleSort.id = "title";
        titleSort.innerHTML = "이름";
        titleSort.style.marginRight = "10px";
        let distanceSort = document.createElement("a");
        distanceSort.classList.add("text-button");
        distanceSort.id = "distance";
        distanceSort.innerHTML = "거리";
        distanceSort.style.marginRight = "10px";
        let arrowSort = document.createElement("a");
        arrowSort.classList.add("arrow-button");
        arrowSort.id = "arrow-button-01";
        let arrow = document.createElement("i");
        arrow.classList.add("bi");
        arrow.classList.add(this.reverse ? "bi-arrow-up" : "bi-arrow-down");
        arrow.id = "arrow-button-02";
        arrowSort.appendChild(arrow);

        sortWrapper.appendChild(idSort);
        sortWrapper.appendChild(titleSort);
        sortWrapper.appendChild(distanceSort);
        sortWrapper.appendChild(arrowSort);

        this.searchResultList.innerHTML = "";
        this.searchResultList.appendChild(sortWrapper);

        let idx = 0;
        json.forEach((data) => {
            let position = {
                title: data.title,
                content: data.addr1 + " " + data.addr2,
                latlng: new kakao.maps.LatLng(data.latitude, data.longitude),
            };

            positions.push(position);

            let tmp = `
            <div class="col-lg-6 search-result-wrapper">
            <button class="col-md-6 glass-btn" id="selected-${idx}" style="width: 100%">
                <div class="result-holder">
                    <div class="result-text-holder">
                    <h1> ${data.title} </h1>
                    <p> ${data.addr1} ${data.addr2} </p>
                    </div>
                    <div class="result-img-holder">
                    `;
            if (data.firstImage != "") {
                tmp += `<img src="${data.firstImage}" class="result-img" alt="attraction image">`;
            }
            tmp += `
                    </div>
                </div>
                </button>
            </div>`;
            this.searchResultList.innerHTML += tmp;

            idx += 1;
        });

        let buttons = this.searchResultList.querySelectorAll(".glass-btn");
        buttons.forEach((button) => {
            button.addEventListener("click", () => {
                let idx = button.id.split("-")[1];
                this.onSelect(idx);
            });
        });

        let textBtns = this.searchResultList.querySelectorAll(".text-button");
        textBtns.forEach((btn) => {
            btn.addEventListener("click", () => {
                this.sortData(btn.id);
            });
        });

        let arrowBtn = this.searchResultList.querySelector("#arrow-button-01");
        arrowBtn.addEventListener("click", () => {
            this.sortToggle();
        });

        let selectedPlace = JSON.parse(localStorage.getItem("selectedPlaces"));
        if (selectedPlace == null) {
            return;
        }

        selectedPlace.forEach((place) => {
            let contentId = place.contentId;
            for (let i = 0; i < json.length; i++) {
                if (json[i].contentId == contentId) {
                    let selected = this.searchResultList.querySelector(
                        `#selected-${i}`
                    );
                    selected.classList.remove("glass-btn");
                    selected.classList.add("glass-btn-colored");
                    break;
                }
            }
        });

        this.mapInstance.displayMarker(positions);
    }

    onSelect(idx) {
        let selected = this.searchResultList.querySelector(`#selected-${idx}`);

        let data = this.data[idx];

        if (selected.classList.contains("glass-btn")) {
            selected.classList.remove("glass-btn");
            selected.classList.add("glass-btn-colored");

            let selectedPlaces =
                JSON.parse(localStorage.getItem("selectedPlaces")) ?? [];
            selectedPlaces.push(data);
            localStorage.setItem(
                "selectedPlaces",
                JSON.stringify(selectedPlaces)
            );
        } else {
            selected.classList.remove("glass-btn-colored");
            selected.classList.add("glass-btn");

            let selectedPlaces = JSON.parse(
                localStorage.getItem("selectedPlaces")
            );
            let tmp = [];
            selectedPlaces.forEach((place) => {
                if (place.id != idx) {
                    tmp.push(place);
                }
            });
            localStorage.setItem("selectedPlaces", JSON.stringify(tmp));
        }

        this.mapInstance.onResultClick(data.latitude, data.longitude);
    }

    sortToggle() {
        let arrow = this.searchResultList.querySelector("#arrow-button-01");

        this.reverse = !this.reverse;

        if (this.reverse) {
            arrow.innerHTML = `<i class="bi bi-arrow-up"></i>`;
        } else {
            arrow.innerHTML = `<i class="bi bi-arrow-down"></i>`;
        }

        if (this.sortKey == "") return;

        this.sortData(this.sortKey);
    }

    sortData(key) {
        this.sortKey = key;

        let res = this.quickSort(this.data, key, this.reverse);

        this.setData(res);
    }

    quickSort(arr, parm, order = true) {
        if (arr.length <= 1) {
            return arr;
        }

        const pivot = arr[0];
        const left = [];
        const right = [];

        for (let i = 1; i < arr.length; i++) {
            if (order) {
                if (arr[i][parm] < pivot[parm]) {
                    left.push(arr[i]);
                } else {
                    right.push(arr[i]);
                }
            } else {
                if (arr[i][parm] > pivot[parm]) {
                    left.push(arr[i]);
                } else {
                    right.push(arr[i]);
                }
            }
        }

        return [
            ...this.quickSort(left, parm, order),
            pivot,
            ...this.quickSort(right, parm, order),
        ];
    }
}
