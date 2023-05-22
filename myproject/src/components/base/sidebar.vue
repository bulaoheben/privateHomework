<template>
  <el-col :span="3">
    <el-menu
      style="height: 754px"
      class="el-menu-vertical-demo"
      background-color="#545c64"
      text-color="#fff">
      <el-menu-item index="4" style="margin-top: 30px;display: flex">
        <el-avatar></el-avatar>
        <div style="margin-left: 15px;font-weight: bold">{{this.username}} </div>
      </el-menu-item>
      <el-menu-item index="1" @click="topage1()" style="margin-top: 30px">
        <i class="el-icon-shopping-cart-1"></i>
        <span>创建订单</span>
      </el-menu-item>
      <el-menu-item index="2" @click="topage2()">
        <i class="el-icon-menu"></i>
        <span>检索账单</span>
      </el-menu-item>
      <el-menu-item index="3" @click="topage3()">
        <i class="el-icon-document"></i>
        <span>货物情况</span>
      </el-menu-item>
      <el-menu-item @click="topage4()" style="position: absolute;bottom: 30px">
        <i class="el-icon-d-arrow-left"></i>
        <span>退出系统</span>
      </el-menu-item>
    </el-menu>
  </el-col>
</template>

<script>
export default {
  name: "sidebar",
  data(){
    return{
      id:this.$route.query.id,
      username:this.$route.query.username,
      email:this.$route.query.email
    }
  },
  methods:{
    topage1(){
      this.$router.push({
        path: `/mainpage`,
        query:{
          username:this.username,
          id:this.id,
          email:this.email,
        }
      });
    },
    topage2(){
      this.$router.push({
        path: `/searchBill`,
        query:{
          username:this.username,
          id:this.id,
          email:this.email,
        }
      });
    },
    topage3(){
      this.$router.push({
        path: `/goodsSituation`,
        query:{
          username:this.username,
          id:this.id,
          email:this.email,
        }
      });
    },
    topage4(){
      this.$confirm('是否确认退出系统?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const url = "/"
        window.location.replace(url);
      }).catch(() => {
      });
    },
  }
}
</script>

<style scoped>
</style>
