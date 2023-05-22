import Vue from 'vue'
import Router from 'vue-router'
import admin from "../components/main/admin.vue";
import mainpage from "../components/main/mainpage.vue";
import goodsSituation from "../components/main/goodsSituation.vue";
import searchBill from "../components/main/searchBill.vue";
import createaccount from "../components/main/createaccount.vue";
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'admin',
      component: admin
    },
    {
      path: '/mainpage',
      name: 'mainpage',
      component: mainpage
    },
    {
      path: '/goodsSituation',
      name: 'goodsSituation',
      component: goodsSituation
    },
    {
      path: '/searchBill',
      name: 'searchBill',
      component: searchBill
    },
    {
      path: '/createAccount',
      name: 'createAccount',
      component: createaccount
    },
  ]
})
