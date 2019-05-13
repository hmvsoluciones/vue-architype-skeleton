<template>
  <div>
    <nav>
      <div class="nav-wrapper">
        <a href="#" class="brand-logo">{{ $t("systemName") }}</a>
      </div>
    </nav>
  </div>
</template>
<script>
import M from "materialize-css";

export default {
  mounted() {
    M.AutoInit();
  }
};
</script>
