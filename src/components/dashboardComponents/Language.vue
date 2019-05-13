<template>
  <div class="row">
    <div class="input-field col s2 m2">
      <select class="icons" v-model="language" @change="changeLocale(language)">
        <option selected class="left" value="es">
          es-MX
        </option>
        <option class="left" value="en">
          en-US
        </option>
      </select>
      <label>Images in select</label>
    </div>
    <div class="col s1">
      <img v-if="language =='es'" src="@/assets/languages/mx.png" />
      <img v-else src="@/assets/languages/usa.png" />
    </div>
  </div>
</template>
<script>
import i18n from "@/plugins/i18n";
import M from "materialize-css";

export default {
  name: "Language",
  data() {
    return {
      language: "es"
    };
  },
  updated() {
    console.log(this);
  },
  mounted() {
    M.AutoInit();
    var elems = document.querySelectorAll("select");
    M.FormSelect.init(elems, null);
  },
  methods: {
    changeLocale(locale) {
      i18n.locale = locale;
    }
  },
  computed: {
    imageURL() {
      var imageURLr = "@/assets/languages/mx.png";
      switch (this.language) {
        case "es":
          imageURLr = "@/assets/languages/mx.png";
          break;
        case "en":
          imageURLr = "@/assets/languages/usa.png";
          break;
      }
      return imageURLr;
    }
  }
};
</script>
