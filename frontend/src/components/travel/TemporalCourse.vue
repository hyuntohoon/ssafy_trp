<script setup>
import PlaceCard from "@/components/common/PlaceCard.vue";
import draggable from "vuedraggable";
import { useRouter } from "vue-router";

const router = useRouter();

import { storeToRefs } from "pinia";

import { useSearchStore } from "@/stores/search";
const searchStore = useSearchStore();

import { useRouteStore } from "@/stores/route";
const routeStore = useRouteStore();

import { useGptStore } from "@/stores/gpt";
const gptStore = useGptStore();

const { getPlan } = gptStore;
const { autoPlan } = storeToRefs(gptStore);

const { focus } = storeToRefs(searchStore);
const { getAttraction } = searchStore;
const { route, isEditing, mobilityCosts } = storeToRefs(routeStore);
const { postPlace, putPlace, flush } = routeStore;

import Swal from "sweetalert2";

const moveFocus = (data) => {
  focus.value = { lat: data.latitude, lng: data.longitude, level: 3 };
};

const removePlace = (data) => {
  const index = route.value.places.findIndex((place) => place.contentId === data.contentId);
  if (index === -1) {
    return;
  }
  route.value.places.splice(index, 1);
};

const flushPlaces = () => {
  // confirm swal
  Swal.fire({
    title: "코스를 삭제하시겠습니까?",
    text: "전체 코스가 삭제됩니다.",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "삭제",
    cancelButtonText: "취소",
  }).then((result) => {
    if (result.isConfirmed) {
      flush();
      Swal.fire("삭제 완료", "", "success");
    }
  });
};

const doPost = () => {
  // prompt swal that gets name of the course
  if (route.value.places.length === 0) {
    Swal.fire("코스에 장소를 추가해주세요.", "", "warning");
    return;
  } else if (!route.value.tripDate) {
    Swal.fire("여행 날짜를 입력해주세요.", "", "warning");
    return;
  } else if (route.value.tripDate < new Date().toISOString().split("T")[0]) {
    Swal.fire("시간 여행은 현재 금지되어 있습니다.", "", "warning");
    return;
  }
  Swal.fire({
    title: "코스 이름을 입력하세요",
    input: "text",
    inputAttributes: {
      autocapitalize: "off",
    },
    showCancelButton: true,
    confirmButtonText: "저장",
    cancelButtonText: "취소",
    showLoaderOnConfirm: true,
    preConfirm: async (name) => {
      const res = await postPlace(name);
      if (!res) {
        Swal.showValidationMessage("코스 저장에 실패했습니다.");
      }
    },
    allowOutsideClick: () => !Swal.isLoading(),
  }).then((result) => {
    if (result.isConfirmed) {
      console.log(autoPlan.value);
      Swal.fire("저장 완료", "", "success");
    }
  });
};

const doPut = () => {
  // prompt swal that gets name of the course
  Swal.fire({
    title: "코스 이름을 입력하세요",
    input: "text",
    inputAttributes: {
      autocapitalize: "off",
    },
    showCancelButton: true,
    confirmButtonText: "수정",
    cancelButtonText: "취소",
    showLoaderOnConfirm: true,
    preConfirm: async (name) => {
      const res = await putPlace(name);
      if (!res) {
        Swal.showValidationMessage("코스 수정에 실패했습니다.");
      }
    },
    allowOutsideClick: () => !Swal.isLoading(),
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire("수정 완료", "", "success");
    }
  });
};

const goWrite = (place) => {
  Swal.fire({
    title: "장소 후기 작성",
    text: "장소와 관련된 후기를 작성하러 이동하시겠습니까?",
    icon: "question",
    showCancelButton: true,
    confirmButtonText: "이동",
    cancelButtonText: "취소",
  }).then((result) => {
    if (result.isConfirmed) {
      router.push({
        name: "board-create",
        query: {
          contentId: place.contentId,
          title: place.title,
        },
      });
    }
  });
};

const openGPTMenu = () => {
  // swal get text input
  Swal.fire({
    title: "AI를 이용한 코스 생성",
    input: "text",
    inputAttributes: {
      autocapitalize: "off",
    },
    showCancelButton: true,
    confirmButtonText: "생성",
    cancelButtonText: "취소",
    showLoaderOnConfirm: true,
    preConfirm: async (prompt) => {
      const res = await getPlan(prompt);
      if (!res) {
        Swal.showValidationMessage("코스 생성에 실패했습니다.");
      }
    },
    allowOutsideClick: () => !Swal.isLoading(),
  }).then((result) => {
    if (result.isConfirmed) {
      for (const data of autoPlan.value.tripPlan) {
        let contentId = data.contentId;

        getAttraction(contentId).then((res) => {
          console.log(res);
          if (res) {
            route.value.places.push(res);
          }
        });
      }
      Swal.fire("생성 완료", "", "success");
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
        <button id="gpt" @click="openGPTMenu">
          <i class="bi bi-magic"></i>
        </button>
        <button id="save" @click="doPost" v-if="!isEditing">
          <span>코스 저장 </span>
          <i class="bi bi-save"></i>
        </button>
        <button id="save" @click="doPut" v-else>
          <span>코스 수정 </span>
          <i class="bi bi-save"></i>
        </button>
        <button id="flush" @click="flushPlaces">
          <span>코스 삭제 </span>
          <i class="bi bi-trash"></i>
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
    <div
      style="
        display: flex;
        justify-content: space-between;
        margin-bottom: 1rem;
        align-items: center;
      ">
      <span v-if="route.places.length === 0">코스에 장소가 없습니다.</span>
      <span v-else>총 {{ route.places.length }}개의 장소가 추가되었습니다.</span>
      <div style="display: flex; align-items: center">
        <label for="date">여행 날짜</label>
        <div style="width: 1rem"></div>
        <input type="date" id="date" v-model="route.tripDate" />
      </div>
    </div>
    <div class="row">
      <div v-if="autoPlan">
        <p>{{ autoPlan.tripPurpose }}</p>
        <p>{{ autoPlan.tripOverview }}</p>
        <p>{{ autoPlan.tripCaution }}</p>
      </div>
      <draggable
        class="row card-wrap"
        v-model="route.places"
        item-key="contentId"
        @start="drag = true"
        @end="drag = false">
        <template #item="{ element }">
          <div class="col-12">
            <PlaceCard :data="element">
              <template v-slot:actions>
                <div style="display: flex; justify-content: space-between; align-items: end">
                  <!-- show index of this element -->
                  <div class="number-icon">{{ route.places.indexOf(element) + 1 }}</div>
                  <div class="button-wrap">
                    <button @click="moveFocus(element)">
                      <i class="bi bi-geo-alt"></i>
                    </button>
                    <button @click="goWrite(element)">
                      <i class="bi bi-heart"></i>
                    </button>
                    <button @click="removePlace(element)">
                      <i class="bi bi-x-circle"></i>
                    </button>
                  </div>
                </div>
              </template>
            </PlaceCard>
          </div>
        </template>
      </draggable>
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
  width: 60vw;
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

input[type="date"] {
  padding: 0.3rem;
  border: 1px solid var(--primary-color);
  border-radius: 10px;
}
</style>
