import Fetch from "../utils/fetch.js";
import Convert from "../utils/convert.js";
import { SearchResult } from "./SearchResult.js";

let html = `
<div class="blur-box main-box container">
<form
    class="d-flex"
    onsubmit="return false;"
    role="search"
>
    <select
        id="search-sido"
        class="form-select me-2 glass-select"
    >
        <option value="0" selected>시/도</option>
    </select>
    <select
        id="search-gugun"
        class="form-select me-2 glass-select"
    >
        <option value="0" selected>구/군</option>
    </select>
    <select
        id="search-type"
        class="form-select me-2 glass-select"
    >
        <option value="0" selected>유형</option>
    </select>
    <input
        id="search-keyword"
        class="form-control me-2 glass-input"
        type="search"
        placeholder="검색어"
        aria-label="검색어"
    />
    <button
        id="btn-search"
        class="glass-btn"
        type="button"
    >
        <i class="bi bi-search"></i>
    </button>
</form>
</div>`;

export class SearchBar {
    constructor() {
        if (SearchBar.instance instanceof SearchBar) {
            return SearchBar.instance;
        }

        let searchBar = document.getElementById("search");
        searchBar.classList.add("hidden");
        searchBar.innerHTML = html;

        this.searchSido = document.getElementById("search-sido");

        this.searchSido.addEventListener("change", function () {
            SearchBar.instance.getGuGun();
        });

        this.searchGugun = document.getElementById("search-gugun");
        this.searchType = document.getElementById("search-type");

        this.searchKeyword = document.getElementById("search-keyword");
        this.btnSearch = document.getElementById("btn-search");

        this.btnSearch.addEventListener("click", function () {
            let searchResult = document.getElementById("search-result");
            SearchBar.instance.search().then((json) => {
                SearchBar.instance.searchResult.setData(json);
            });
        });

        this.userLatitude = 0;
        this.userLongitude = 0;

        this.getUserLocation();

        this.getSido();

        this.searchResult = new SearchResult();

        SearchBar.instance = this;
    }

    toggle() {
        let searchBar = document.getElementById("search");
        searchBar.classList.toggle("hidden");
    }

    // get user location
    getUserLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
                this.userLatitude = position.coords.latitude;
                this.userLongitude = position.coords.longitude;
            });
        }
    }

    async getSido() {
        let sido = [];
        let data = await Fetch.getRequest("/areasido/sido");
        let json = await Convert.convertToJson(data);
        sido = json;
        this.searchSido.innerHTML = `<option value="0" selected>시/도</option>`;
        sido.forEach((area) => {
            let opt = document.createElement("option");
            opt.setAttribute("value", area.sidoCode);
            opt.appendChild(document.createTextNode(area.sidoName));
            this.searchSido.appendChild(opt);
        });
    }

    async getGuGun() {
        let sidoCode = this.searchSido.value;
        if (sidoCode == 0) {
            this.searchGugun.innerHTML = `<option value="0" selected>구/군</option>`;
            return;
        }

        let gugun = [];
        let data = await Fetch.getRequest("/areasido/gugun/" + sidoCode);
        let json = await Convert.convertToJson(data);
        gugun = json;
        this.searchGugun.innerHTML = `<option value="0" selected>구/군</option>`;
        gugun.forEach((area) => {
            let opt = document.createElement("option");
            opt.setAttribute("value", area.gugunCode);
            opt.appendChild(document.createTextNode(area.gugunName));
            this.searchGugun.appendChild(opt);
        });
    }

    async search() {
        let sidoCode = this.searchSido.value;
        let gugunCode = this.searchGugun.value;
        let type = this.searchType.value;
        let keyword = this.searchKeyword.value;
        let searchResult = document.getElementById("search-result");

        searchResult.classList.remove("hidden");

        let url = "/attraction?action=search";
        if (sidoCode != 0) url += "&sidoCode=" + sidoCode;
        if (gugunCode != 0) url += "&gugunCode=" + gugunCode;
        if (type != 0) url += "&type=" + type;
        if (keyword != "") url += "&keyword=" + keyword;

        let data = await Fetch.getRequest(url);
        let json = await Convert.convertToJson(data);

        let types = {
            12: "관광지",
            14: "문화시설",
            15: "행사",
            25: "여행코스",
            28: "레포츠",
            32: "숙박",
            38: "쇼핑",
            39: "음식점",
        };

        json.forEach((attraction) => {
            let distance = Math.sqrt(
                Math.pow(this.userLatitude - attraction.latitude, 2) +
                    Math.pow(this.userLongitude - attraction.longitude, 2)
            );
            attraction.distance = distance;

            attraction.type = types[attraction.contentTypeId];
        });

        return json;
    }
}
