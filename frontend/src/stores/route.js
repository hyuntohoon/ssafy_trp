import { defineStore } from "pinia";

export const useRouteStore = defineStore("route", {
  state: () => ({
    routeInfo: [],
  }),
  getters: {
    getRouteInfo() {
      return this.routeInfo;
    },
  },
  actions: {
    appendRouteInfo(routeInfo) {
      this.routeInfo = this.routeInfo.concat(routeInfo);
    },
    removeRouteInfo(name) {
      this.routeInfo = this.routeInfo.filter((route) => route.name !== name);
    },
    clearRouteInfo() {
      this.routeInfo = [];
    },
  },
});
