import Vue from 'vue'
import VueRouter from 'vue-router'




Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: () => import('../views/login.vue'),
    meta:{
      title: '登录'
    }
  },
  {
    path: '/mainpage',
    name: 'mainpage',
    component: () => import('../views/mainpage.vue')
  },
  {
    path: '/searchBill',
    name: 'searchBill',
    component: () => import('../views/searchBill.vue')
  },
  {
    path: '/createaccount',
    name: 'createaccount',
    component: () => import('../views/createaccount.vue')
  },
  {
    path: '/goodsSituation',
    name: 'goodsSituation',
    component: () => import('../views/goodsSituation.vue')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
