<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" width="50" height="50" />
    <br />
    <button @click="changeLocale('es')">
      es
    </button>
    <button @click="changeLocale('en')">
      en
    </button>
    <HelloWorld />
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from "@/components/HelloWorld.vue";
import i18n from "@/plugins/i18n";

export default {
  name: "home",
  components: {
    HelloWorld
  },
  methods: {
    changeLocale(locale) {
      i18n.locale = locale;
    }
  }
};
</script>
