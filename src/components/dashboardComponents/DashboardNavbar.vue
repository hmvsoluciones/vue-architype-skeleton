<template>
  <div>
    <nav>
      <div class="nav-wrapper">
        <a href="#!" class="brand-logo">{{ $t("systemName") }}</a>
        <a
          href="#"
          data-target="mobile-demo"
          class="sidenav-trigger show-on-large"
        >
          <i class="material-icons">menu</i>
        </a>
      </div>
    </nav>

    <ul class="sidenav" id="mobile-demo">
      <li>
        <a href="sass.html">Sass</a>
      </li>
      <li>
        <a href="badges.html">Components</a>
      </li>
      <li>
        <a href="collapsible.html">Javascript</a>
      </li>
      <li>
        <a href="mobile.html">Mobile</a>
      </li>
    </ul>
  </div>
</template>
<script>
import M from "materialize-css";
export default {
  name: "DashboardNavbar",
  mounted() {
    M.AutoInit();
  }
};
</script>
