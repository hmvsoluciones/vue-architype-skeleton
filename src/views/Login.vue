<template>
  <div>
    <roles-navbar />
    <div id="loginContainer">
      <router-link to="/roles">roles</router-link>
      <div id="login-page" class="row">
        <h3>Login</h3>
        <div class="col s12 z-depth-6 card-panel">
          <form class="login-form">
            <div class="row"></div>
            <div class="row">
              <div class="input-field col s12">
                <i class="material-icons prefix">mail_outline</i>
                <input class="validate" id="email" type="email" />
                <label for="email" data-error="wrong" data-success="right">
                  Email
                </label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <i class="material-icons prefix">lock_outline</i>
                <input id="password" type="password" />
                <label for="password">Password</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s12">
                <a href="#" class="btn waves-effect waves-light col s12">
                  Login
                </a>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
#login-page {
  width: 500px;
}
</style>
