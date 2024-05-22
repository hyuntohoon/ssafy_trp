<script setup>
import PlaceCard from "@/components/common/PlaceCard.vue";
import Swal from "sweetalert2";

import { useRouteStore } from "@/stores/route";
import { storeToRefs } from "pinia";

const routeStore = useRouteStore();
const { route, isEditing, mobilityCosts } = storeToRefs(routeStore);

import { useSearchStore } from "@/stores/search";
const { typeData } = storeToRefs(useSearchStore());

import { useRouter } from "vue-router";
const router = useRouter();

const { deletePlace } = routeStore;

import { getWeather } from "@/api/weather";

import { useGptStore } from "@/stores/gpt";
const gptStore = useGptStore();
const { getResponse, setPrompt } = gptStore;
const { isLoading } = storeToRefs(gptStore);

const getWeatherData = async (lat, lon) => {
  const weather = await getWeather(lat, lon);
  return weather;
};

const getWeatherInfo = async () => {
  let result = [];
  for (const place of route.value.places) {
    const data = await getWeatherData(place.latitude, place.longitude);
    result.push(data);
  }
  return result;
};

const goEdit = () => {
  isEditing.value = true;
  router.push({
    name: "travel",
  });
};

const deleteCourse = () => {
  Swal.fire({
    title: "코스를 삭제하시겠습니까?",
    text: "코스가 삭제됩니다.",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "삭제",
    cancelButtonText: "취소",
  }).then((result) => {
    if (result.isConfirmed) {
      const res = deletePlace();
      if (res) {
        Swal.fire("삭제 완료", "", "success");
      } else {
        Swal.fire("삭제 실패", "", "error");
      }
    }
  });
};

const getPrompt = async () => {
  if (route.value.places.length === 0) {
    Swal.fire("코스에 장소가 없습니다.", "", "error");
    return;
  }
  isLoading.value = true;
  let promptObj = [];
  // check if route.value.tripDate in today + 16 days
  let today = new Date();
  let tripDate = new Date(route.value.tripDate);
  let diff = Math.ceil((tripDate - today) / (1000 * 60 * 60 * 24));
  let weather = {};
  console.log(diff);
  if (diff > 0 && diff < 16) {
    // 16일 이내 여행이면 날씨 정보 추가
    weather = await getWeatherInfo();
  }
  // index and place iterate
  for (const [index, place] of route.value.places.entries()) {
    // find type of place by contentTypeId in place in typeData
    let type = typeData.value.find((type) => type.typeCode === place.contentTypeId).typeName;
    let placeData = {
      title: place.title,
      addr: place.addr1,
      type: type,
    };
    // 날짜 확인 로직 추가
    if (diff > 0 && diff < 16) {
      // 16일 이내 여행이면 날씨 정보 추가
      console.log(weather[index]);
      promptObj.push({
        place: placeData,
        weather: {
          precipitation_sum:
            weather[index].daily.precipitation_sum[diff] +
            weather[index].daily_units.precipitation_sum,
          temperature_2m_max:
            weather[index].daily.temperature_2m_max[diff] +
            weather[index].daily_units.temperature_2m_max,
          temperature_2m_min:
            weather[index].daily.temperature_2m_min[diff] +
            weather[index].daily_units.temperature_2m_min,
          time: weather[index].daily.time[diff],
        },
      });
    } else {
      promptObj.push({
        place: placeData,
      });
    }
    console.log(promptObj);
  }

  let promptString =
    JSON.stringify(promptObj) +
    " 여행 계획 평가, 주의점을 HTML 문법에 맞게 div 태그 하나로 감싸서 출력해줘";

  setPrompt(promptString);
  getResponse();
};
</script>

<template>
  <div class="wrap">
    <div class="nav-wrap">
      <div class="text">
        <h2>여행 계획</h2>
      </div>
      <div class="action-wrap">
        <button id="gpt" @click="getPrompt">
          <i class="bi bi-magic"></i>
        </button>
        <button id="save" @click="goEdit">
          <span>코스 수정 </span>
          <i class="bi bi-pencil"></i>
        </button>
        <button id="flush" @click="deleteCourse">
          <span>코스 삭제 </span>
          <i class="bi bi-eraser"></i>
        </button>
      </div>
    </div>
    <div class="row-wrap">
      <div class="cost-item">
        <i class="bi bi-taxi-front"></i>
        <span style="margin-left: 0.5rem"> {{ mobilityCosts.taxi }}원</span>
      </div>
      <div class="cost-item">
        <i class="bi bi-credit-card"></i>
        <span style="margin-left: 0.5rem"> {{ mobilityCosts.toll }}원</span>
      </div>
      <div class="cost-item">
        <i class="bi bi-geo-alt"></i>
        <span style="margin-left: 0.5rem"> {{ Math.round(mobilityCosts.distance / 1000) }}km</span>
      </div>
      <div class="cost-item">
        <i class="bi bi-clock"></i>
        <span style="margin-left: 0.5rem"> {{ Math.round(mobilityCosts.duration / 60) }}분</span>
      </div>
    </div>
    <hr />
    <div style="display: flex; justify-content: space-between; align-items: center">
      <span v-if="route.places.length === 0">코스에 장소가 없습니다.</span>
      <span v-else>총 {{ route.places.length }}개의 장소가 있습니다.</span>
      <span> 여행 날짜: {{ route.tripDate }} </span>
    </div>
    <div class="row">
      <div class="col-6 card-wrap" v-for="place in route.places" :key="place.id">
        <PlaceCard :data="place">
          <template v-slot:actions>
            <div style="display: flex; justify-content: space-between; align-items: end">
              <!-- show index of this element -->
              <div class="number-icon">{{ route.places.indexOf(place) + 1 }}</div>
            </div>
          </template>
        </PlaceCard>
      </div>
    </div>
  </div>
</template>

<style scoped>
.wrap {
  margin-top: 1rem;
  background-color: rgba(255, 255, 255, 0.5);
  backdrop-filter: blur(10px);
  padding: 1rem;
  border-radius: 10px;
  overflow-y: auto;
  overflow-x: hidden;
}

.nav-wrap {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}

.row-wrap {
  display: flex;
  flex-direction: row;
  align-items: center;
}

.cost-item {
  display: flex;
  align-items: center;
  margin-right: 1rem;
}

button {
  background-color: transparent;
  border: none;
  cursor: pointer;
  font-size: 1.5rem;
}

button:hover {
  color: var(--accent-color);
  transition: color 0.5s;
}

button:active {
  color: var(--accent-color-dark-8);
}

.button-wrap {
  display: flex;
  justify-content: end;
  margin-top: 1rem;
}

.action-wrap {
  display: flex;
  align-items: center;
}

#flush {
  border: 1px solid var(--accent-color);
  color: var(--accent-color-dark-8);
  margin-left: 1rem;
  padding: 0.5rem 1rem;
  border-radius: 10px;
  font-size: 1rem;
}

#flush:hover {
  background-color: var(--accent-color);
  color: white;
  transition: background-color 0.5s, color 0.5s;
}

#save {
  border: 1px solid var(--primary-color);
  color: var(--primary-color-dark-8);
  margin-left: 1rem;
  padding: 0.5rem 1rem;
  border-radius: 10px;
  font-size: 1rem;
}

#save:hover {
  background-color: var(--primary-color);
  color: white;
  transition: background-color 0.5s, color 0.5s;
}

#gpt {
  border: 1px solid var(--secondary-color);
  color: var(--secondary-color-dark-8);
  padding: 0.5rem 1rem;
  border-radius: 10px;
  font-size: 1rem;
}

#gpt:hover {
  /* change color rainbow */
  background-image: linear-gradient(90deg, #8dcce1 0%, #efe09a 49%, #ffa6a6 80%, #8dcce1 100%);
  animation: slidebg 8s linear infinite;
  color: white;
}

@keyframes slidebg {
  to {
    background-position: 20vw;
  }
}

@keyframes scale-in-center {
  0% {
    transform: scale(0.5);
    opacity: 0;
  }
  100% {
    transform: scale(1);
    opacity: 1;
  }
}

@keyframes scale-out-center {
  0% {
    transform: scale(1);
    opacity: 1;
  }
  100% {
    transform: scale(0.5);
    opacity: 0;
  }
}

.card-wrap {
  animation: scale-in-center 0.3s cubic-bezier(0.25, 0.46, 0.45, 0.94) both;
}

.number-icon {
  width: 30px;
  height: 30px;
  background-color: var(--accent-color);
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-weight: bold;
}

.weather-icon {
  font-size: 1.5rem;
}
</style>
