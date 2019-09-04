import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    },

    {
      path: '/query',
      name: 'query',
      component: () => import('./views/Query.vue')
    },
    {
      path: '/sign-in',
      name: 'sign-in',
      component: () => import('./views/SignIn.vue')
    },
    {
      path: '/sign-out',
      name: 'sign-out',
      component: () => import('./views/SignOut.vue')
    },
    {
      path: '/sign-up',
      name: 'sign-up',
      component: () => import('./views/SignUp.vue')
    },
    {
      path: '/user-info',
      name: 'user-info',
      component: () => import('./views/UserInfo.vue')
    },
    {
      path: '/user-devices',
      name: 'user-devices',
      component: () => import('./views/UserDevices.vue')
    },

    // debug views

    {
      path: '/debug/new-credential',
      name: 'debug-new-credential',
      component: () => import('./views/debug/NewCredential.vue')
    },
    {
      path: '/debug/session',
      name: 'debug-session',
      component: () => import('./views/debug/SessionInfo.vue')
    },

  ]
})
