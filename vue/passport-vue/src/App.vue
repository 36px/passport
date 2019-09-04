<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link>|
      <router-link to="/about">About</router-link>|
      <router-link to="/query">Query</router-link>|
      <router-link to="/sign-in">Sign In</router-link>|
      <router-link to="/sign-out">Sign Out</router-link>|
      <router-link to="/sign-up">Sign Up</router-link>|
      <router-link to="/user-info">User Info</router-link>|
      <router-link to="/user-devices">User Devices</router-link>|
    </div>

    <div id="nav">
      <router-link to="/debug/new-credential">New Credential</router-link>|
      <router-link to="/debug/session">Session Info</router-link>|
    </div>

    <router-view />
  </div>
</template>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
