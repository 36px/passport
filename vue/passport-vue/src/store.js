import Vue from 'vue'
import Vuex from 'vuex'
import rest from './rest'

import module_query from './store/query.js'
import module_sign_in from './store/sign-in.js'
import module_sign_up from './store/sign-up.js'

Vue.use(Vuex);


export default new Vuex.Store({

  state: {

  },

  mutations: {

  },

  actions: {

    rest(context, param) {
      rest.execute(param);
    }

  },

  modules: {
    query: module_query,
    sign_in: module_sign_in,
    sign_up: module_sign_up
  }

});
