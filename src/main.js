import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "@/store/store";
import i18n from "@/plugins/i18n";

import "materialize-css/dist/css/materialize.css";
import "materialize-css/dist/js/materialize.js";

import RoleItem from "@/components/roleComponents/RoleItem";
import RolesNavbar from "@/components/roleComponents/RolesNavbar";
import DashboardNavbar from "@/components/dashboardComponents/DashboardNavbar";
import DashboardFooter from "@/components/dashboardComponents/DashboardFooter";
import Language from "@/components/dashboardComponents/Language";

import "./registerServiceWorker";

Vue.config.productionTip = false;

Vue.component("role-item", RoleItem);
Vue.component("roles-navbar", RolesNavbar);
Vue.component("dashboard-navbar", DashboardNavbar);
Vue.component("dashboard-footer", DashboardFooter);
Vue.component("language", Language);

new Vue({
  i18n,
  router,
  store,
  render: h => h(App)
}).$mount("#app");
