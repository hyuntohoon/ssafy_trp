import Convert from "../utils/convert.js";
import Fetch from "../utils/fetch.js";
import { Toast } from "./Toast.js";

export class Planner {
    constructor() {
        if (Planner.instance instanceof Planner) {
            return Planner.instance;
        }
        this.courseList = document.getElementById("course-list");
        this.currentView = [];
        this.curOption = 0;
        this.courses = {};

        this.toastInstance = new Toast();

        Planner.instance = this;
    }

    showPlanner() {
        let courseMenu = document.getElementById("course");

        if (courseMenu.classList.contains("hidden")) {
            courseMenu.classList.remove("hidden");
            this.getCourseFromLocalStorage();
            this.getCourses();
        } else {
            courseMenu.classList.add("hidden");
        }
    }

    getCourseFromLocalStorage() {
        let course = localStorage.getItem("selectedPlace");

        if (course == null || course.length < 3) {
            this.courseList.innerHTML = `
            <div class="col-lg-12">
            <p style="color: white">저장된 여행지가 없습니다.</p>
            </div>`;
        }

        let courseArr = JSON.parse(course);

        courseArr.forEach((place) => {
            this.currentView.push(place);
            if (place.firstImage.length == 0) {
                place.firstImage = `http://placehold.it/100x100`;
            }

            let tmp = "";
            tmp += `
            <div class="course-item" id="${place.contentId}">
                <div style="display:flex; justify-content: space-between;">
                    <img class="course-item-img" src="${place.firstImage}" alt="${place.title}">
                    <div style="width:30px;"></div>
                    <div class="course-item-info">
                        <h3>${place.title}</h3>
                        <p>${place.addr1}</p>
                    <button class="glass-btn" onclick="deletePlace(${place.contentId})">삭제</button>
                    </div>
                </div>
                <div>
                    <button class="glass-btn" style="background-color: rgba(255, 98, 98, 0.519)" onclick="moveUp(${place.contentId})">위로</button>
                    <button class="glass-btn" style="background-color: rgba(96, 146, 255, 0.519)" onclick="moveDown(${place.contentId})">아래로</button>
                </div>
            </div>
            `;
            this.courseList.innerHTML += tmp;
        });
    }

    deletePlace(contentId) {
        if (this.curOption == 0) {
            let course = localStorage.getItem("selectedPlace");
            let courseArr = JSON.parse(course);
            let idx = courseArr.findIndex((place) => {
                return place.contentId == contentId;
            });
            courseArr.splice(idx, 1);
            localStorage.setItem("selectedPlace", JSON.stringify(courseArr));

            if (courseArr.length == 0) {
                this.courseList.innerHTML = `
            <div class="col-lg-12">
            <p style="color: white">저장된 여행지가 없습니다.</p>
            </div>`;

                localStorage.removeItem("selectedPlace");
            }

            this.getCourseFromLocalStorage();
        } else {
            let course = this.courses[this.curOption];
            let idx = this.courses.findIndex((place) => {
                return place.contentId == contentId;
            });
            course.splice(idx, 1);
            this.courses[this.curOption] = course;

            this.selectCourse(this.curOption);
        }
    }

    moveUp(contentId) {
        let course = this.courses[this.curOption];
        let idx = course.findIndex((place) => {
            return place.contentId == contentId;
        });

        if (idx == 0) {
            return;
        }

        let tmp = course[idx];
        course[idx] = course[idx - 1];
        course[idx - 1] = tmp;

        this.courses[this.curOption] = course;
        this.selectCourse(this.curOption);
    }

    moveDown(contentId) {
        let course = this.courses[this.curOption];
        let idx = course.findIndex((place) => {
            return place.contentId == contentId;
        });

        if (idx == course.length - 1) {
            return;
        }

        let tmp = course[idx];
        course[idx] = course[idx + 1];
        course[idx + 1] = tmp;

        this.courses[this.curOption] = course;
        this.selectCourse(this.curOption);
    }

    async saveCourse() {
        if (this.curOption == 0) addCourse();
        else this.updateCourse();
    }

    async updateCourse() {
        let cookies = document.cookie.split(";");
        let userId = "";
        cookies.forEach((cookie) => {
            if (cookie.includes("userId")) {
                userId = cookie.split("=")[1];
            }
        });

        let name = document.getElementById("course-name").value;

        let formData = {
            action: "update",
            name: name,
            userId: userId,
            route: JSON.stringify(this.currentView),
            id: this.curOption,
        };

        let queryString = Object.entries(formData)
            .map(([key, value]) => `${key}=${value}`)
            .join("&");

        let response = await Fetch.postRequest("/tripPlan", queryString);

        if (response.status == 200) {
            this.getCourseFromLocalStorage();
            this.toastInstance.show(
                "코스 갱신 성공!",
                "성공적으로 업데이트되었습니다."
            );
        } else {
            this.toastInstance.show("갱신 실패!", "저장에 실패했습니다.");
        }
        this.init();
    }

    async addCourse() {
        let cookies = document.cookie.split(";");
        let userId = "";
        cookies.forEach((cookie) => {
            if (cookie.includes("userId")) {
                userId = cookie.split("=")[1];
            }
        });

        let name = document.getElementById("course-name").value;

        if (name.length == 0) {
            this.toastInstance.show(
                "이름을 입력하세요",
                "코스 이름을 입력해주세요."
            );
            return;
        }

        let formData = {
            action: "add",
            name: name,
            userId: userId,
            route: JSON.stringify(this.currentView),
        };

        let queryString = Object.entries(formData)
            .map(([key, value]) => `${key}=${value}`)
            .join("&");

        let response = await Fetch.postRequest("/tripPlan", queryString);

        if (response.status == 200) {
            this.getCourseFromLocalStorage();
            this.toastInstance.show(
                "코스 추가 성공!",
                "성공적으로 추가되었습니다."
            );
        } else {
            this.toastInstance.show("추가 실패!", "저장에 실패했습니다.");
        }

        this.init();
    }

    async getCourses() {
        let cookies = document.cookie.split(";");
        let userId = "";
        cookies.forEach((cookie) => {
            if (cookie.includes("userId")) {
                userId = cookie.split("=")[1];
            }
        });

        let response = await Fetch.getRequest(
            `/tripPlan?action=read&userId=${userId}`
        );
        let json = await Convert.convertToJson(response);

        let courseSelect = document.getElementById("course-select");
        courseSelect.innerHTML = `<option value="0" selected>현재 선택된 여행지</option>`;

        let tmp = "";
        json.forEach((course) => {
            this.courses[course.id] = JSON.parse(course.route);
            tmp += `<option value="${course.id}">${course.name}</option>`;
        });
        courseSelect.innerHTML += tmp;

        courseSelect.addEventListener("change", (event) => {
            this.selectCourse(event.target.value);
        });
    }

    selectCourse(idx) {
        if (idx == 0) {
            this.curOption = 0;
            this.getCourseFromLocalStorage();
            return;
        }
        this.curOption = idx;
        this.currentView = this.courses[idx];

        this.courseList.innerHTML = "";

        document.getElementById("course-name").value =
            document.getElementById("course-select").options[
                document.getElementById("course-select").selectedIndex
            ].text;

        this.courses[idx].forEach((place) => {
            if (place.firstImage.length == 0) {
                place.firstImage = `http://placehold.it/100x100`;
            }

            let tmp = "";
            tmp += `
            <div class="course-item" id="${place.contentId}">
                <div style="display:flex; justify-content: space-between;">
                    <img class="course-item-img" src="${place.firstImage}" alt="${place.title}">
                    <div style="width:30px;"></div>
                    <div class="course-item-info">
                        <h3>${place.title}</h3>
                        <p>${place.addr1}</p>
                    <button class="glass-btn" onclick="deletePlace(${place.contentId})">삭제</button>
                    </div>
                </div>
                <div>
                    <button class="glass-btn" style="background-color: rgba(255, 98, 98, 0.519)" onclick="moveUp(${place.contentId})">위로</button>
                    <button class="glass-btn" style="background-color: rgba(96, 146, 255, 0.519)" onclick="moveDown(${place.contentId})">아래로</button>
                </div>
            </div>
            `;
            this.courseList.innerHTML += tmp;
        });

        if (this.currentView.length == 0) {
            this.courseList.innerHTML = `
            <div class="col-lg-12">
            <p style="color: white">저장된 여행지가 없습니다.</p>
            </div>`;
        }
    }

    async removeCourse() {
        let cookies = document.cookie.split(";");
        let userId = "";
        cookies.forEach((cookie) => {
            if (cookie.includes("userId")) {
                userId = cookie.split("=")[1];
            }
        });

        let formData = {
            action: "delete",
            id: this.curOption,
        };

        let queryString = Object.entries(formData)
            .map(([key, value]) => `${key}=${value}`)
            .join("&");

        let response = await Fetch.postRequest("/tripPlan", queryString);

        if (response.status == 200) {
            this.getCourseFromLocalStorage();
            this.toastInstance.show(
                "코스 삭제 성공!",
                "성공적으로 삭제되었습니다."
            );
        } else {
            this.toastInstance.show("삭제 실패!", "삭제에 실패했습니다.");
        }
        this.init();
    }

    init() {
        this.currentView = [];
        this.curOption = 0;
        this.courses = {};
        this.getCourseFromLocalStorage();
        this.getCourses();
        document.getElementById("course-name").value = "";
    }
}
