import Vue from "vue";
import VueI18n from "vue-i18n";

Vue.use(VueI18n);

const messages = {
  en: {
    welcomeMsg: "Welcome to Your Vue.js App",
    systemName: "System Name",
    systemSlogan: "This is the slogan",
    copyright: "2019 Copyright Text"
  },
  es: {
    welcomeMsg: "Bienvenido a tu aplicación Vue.js",
    systemName: "Nombre Sistema",
    systemSlogan: "Este es el slogan",
    copyright: "2019 Todos los derechos reservados"
  }
};

const i18n = new VueI18n({
  locale: "es", // set locale
  fallbackLocale: "en", // set fallback locale
  messages // set locale messages
});

export default i18n;
