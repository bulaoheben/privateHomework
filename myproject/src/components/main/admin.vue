<template>
  <div class="container">
    <div class="login-wrapper">
      <div class="header">Login</div>
      <el-form class="form-wrapper" :model="this.loginForm" ref="loginForm">
        <el-form-item  prop="userName" class="login-item" style="margin: 0">
          <input class="input-item" type="userName" v-model="loginForm.username" placeholder="账号">
        </el-form-item>
        <el-form-item prop="pwd" style="margin-top: 20px;margin-bottom: 0px">
          <input class="input-item" v-model="loginForm.password" placeholder="密码" type="password">
        </el-form-item>
        <el-form-item @click="submitForm()" style="margin-top: 20px">
          <div class="btn" @click="submitForm()"  type="primary">登录</div>
          <router-view></router-view>
        </el-form-item>
      </el-form>
      <div class="msg">
        Don't have account?
        <a href="#/createAccount">Sign up</a>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { api, authenticate } from '../apiauthentification';
export default {
  name: 'admin',
  data(){
    return {
      loginForm: {
        username: '',
        password: ''
      },
    }
  },
  methods:{
    submitForm () {
      console.log("1233444444444444")
      if(this.loginForm.username==""){
        alert("用户名不得为空");
      }else if(this.loginForm.password==""){
        alert("密码不得为空");
      }else {
        let data={
          username:this.loginForm.username,
          password:this.loginForm.password,
        }
        axios.post('http://localhost:8181/user/login', data)
          .then(response => {
            // 处理返回信息
            console.log(response.data);
            localStorage.setItem('token', JSON.stringify(response.data.data.token))
            if(response.data.data!==null){
              this.$router.push({
                path: `/mainpage`,
                query:{
                  username:response.data.data.username,
                  id:response.data.data.id,
                  email:response.data.data.email,
                }
              });
            }else {
              alert(response.data.msg);
            }
          })
          .catch(error => {
            console.log(error);
          });
      }
    },

  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
html {
  height: 100%;
}
body {
  height: 100%;
  background-image: linear-gradient(to right, #00AA55, #a6c1ee);
}
.container {
  height: 100%;
}
.login-wrapper {
  background-color: #fff;
  width: 358px;
  height: 588px;
  border-radius: 15px;
  padding: 0 50px;
  position: relative;
  left: 50%;
  top: 50%;
  transform: translate(-50%,12%);
}
.header {
  font-size: 38px;
  font-weight: bold;
  text-align: center;
  line-height: 200px;
}
.input-item {
  display: block;
  width: 100%;
  margin-bottom: 20px;
  border: 0;
  padding: 10px;
  border-bottom: 1px solid rgb(128, 125, 125);
  font-size: 15px;
  outline: none;
}
.input-item::placeholder {
  text-transform: uppercase;
}
.btn {
  text-align: center;
  padding: 10px;
  width: 100%;
  margin-top: 40px;
  background-image: linear-gradient(to right,#00DDAA,#a6c1ee);
  color: #fff;
}
.msg {
  text-align: center;
  line-height: 88px;
}
a {
  text-decoration-line: none;
  color: #abc1ee;
}
</style>
