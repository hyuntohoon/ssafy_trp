export class Map {
    constructor() {
        if (Map.instance instanceof Map) {
            return Map.instance;
        }
        let mapContainer = document.getElementById("map");
        let mapOption = {
            center: new kakao.maps.LatLng(37.27538, 127.05488),
            level: 13,
        };
        this.map = new kakao.maps.Map(mapContainer, mapOption);
        this.clusterer = new kakao.maps.MarkerClusterer({
            map: this.map,
            averageCenter: true,
            minLevel: 10,
        });
        this.selectedMarker = null;
        Map.instance = this;
    }

    displayMarker(positions) {
        this.clusterer.clear();
        positions.forEach((marker) => {
            let makerObj = new kakao.maps.Marker({
                map: this.map,
                position: marker.latlng,
            });

            let content = `
                <div style="padding:10px;">
                    <h5>${marker.title}</h5>
                    <p>${marker.content}</p>
                </div>
            `;
            let infowindow = new kakao.maps.InfoWindow({
                content: content,
                removable: true,
            });

            kakao.maps.event.addListener(makerObj, "click", () => {
                infowindow.open(this.map, makerObj);
                if (this.selectedMarker) {
                    this.selectedMarker.setZIndex(0);
                    infowindow.close();
                }
                this.selectedMarker = makerObj;
                this.selectedMarker.setZIndex(1);
                infowindow.open(this.map, makerObj);
                infowindow.setContent(content);
            });

            this.clusterer.addMarker(makerObj);
        });
    }

    toggleMap() {
        let mapResult = document.getElementById("map");
        if (mapResult.classList.contains("hidden")) {
            mapResult.classList.remove("hidden");

            this.map.relayout();

            this.map.setCenter(new kakao.maps.LatLng(37.27538, 127.05488));
        } else {
            mapResult.classList.add("hidden");
        }
    }

    onResultClick(lat, lng) {
        let level = 5;
        this.map.setLevel(level);
        this.map.setCenter(new kakao.maps.LatLng(lat, lng));
    }
}
