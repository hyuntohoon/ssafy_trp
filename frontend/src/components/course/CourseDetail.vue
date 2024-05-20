<script setup>
import PlaceCard from "@/components/common/PlaceCard.vue";
import Swal from "sweetalert2";

import { useRouteStore } from "@/stores/route";
import { storeToRefs } from "pinia";

const routeStore = useRouteStore();
const { route, isEditing } = storeToRefs(routeStore);

import { useRouter } from "vue-router";
const router = useRouter();

const { deletePlace } = routeStore;

import { getWeather } from "@/api/weather";
import { watch } from "vue";

const getWeatherData = async (lat, lon) => {
  const weather = await getWeather(lat, lon);
  return weather;
};

watch(routeStore.route, async (newValue) => {
  console.log("route changed", newValue);
  for (const place of newValue.places) {
    const weather = await getWeatherData(place.latitude, place.longitude);
    place.weather = weather;
  }
});

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
</script>

<template>
  <div class="wrap">
    <div class="nav-wrap">
      <div class="text">
        <h2>여행 계획</h2>
      </div>
      <div class="action-wrap">
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
    <hr />
    <span v-if="route.places.length === 0">코스에 장소가 없습니다.</span>
    <div class="row">
      <div class="col-6 card-wrap" v-for="place in route.places" :key="place.id">
        <PlaceCard :data="place" @remove="removePlace">
          <template v-slot:actions>
            <div style="display: flex; justify-content: space-between; align-items: end">
              <!-- show index of this element -->
              <div class="number-icon">{{ route.places.indexOf(place) + 1 }}</div>
              <span>
                {{ place.weather }}
              </span>
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
  padding: 0.5rem 1rem;
  border-radius: 10px;
  font-size: 1rem;
}

#save:hover {
  background-color: var(--primary-color);
  color: white;
  transition: background-color 0.5s, color 0.5s;
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
</style>
