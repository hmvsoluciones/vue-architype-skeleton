import Vue from "vue";
import VueI18n from "vue-i18n";

Vue.use(VueI18n);

const messages = {
  en: {
    welcomeMsg: "Welcome to Your Vue.js App",
    systemName: "System Name"
  },
  es: {
    welcomeMsg: "Bienvenido a tu aplicación Vue.js",
    systemName: "Nombre Sistema"
  }
};

const i18n = new VueI18n({
  locale: "es", // set locale
  fallbackLocale: "en", // set fallback locale
  messages // set locale messages
});

export default i18n;
