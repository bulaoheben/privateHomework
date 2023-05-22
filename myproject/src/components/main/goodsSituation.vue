<template>
<div>
  <div>
    <div style="display: flex;height: 100%">
      <sidebar style="height: 100%"></sidebar>
      <div>
        <el-card style="margin-left: 30px;margin-top: 30px;">
          <el-table
            :data="orders"
            border
            height="680px"
            style="width: 100%;"
            @expand-change="searchTransport">

            <el-table-column type="expand">
              <template slot-scope="scope">
                <div style="margin-top: 30px">
                  <el-steps :space="40" direction="vertical" :active=detailTransport.length+2 style="margin-left: 30px" finish-status="success">
                    <el-step title="您已提交订单,请等待系统确认" :description=scope.row.fromDate></el-step>
                    <el-step title="订单确认，已打包完成" :description=scope.row.fromDate></el-step>
                    <el-step v-for="item in detailTransport" :title="item.currentLocation!=null?
                    '货件已到达'+item.currentLocation:
                    '货件正在由'+item.departAddress+'运往'+item.destinationAddress"
                    :description=item.fromDate></el-step>
                  </el-steps>
                </div>
              </template>
            </el-table-column>

            <el-table-column
              prop="id"
              label="订单编号"
              width="120">
            </el-table-column>

            <el-table-column
              prop="name"
              label="货物名称">
            </el-table-column>

            <el-table-column
              prop="fromDate"
              label="寄出时间"
              width="180">
            </el-table-column>

            <el-table-column
              prop="departAddress"
              label="原寄地"
              width="340">
            </el-table-column>

            <el-table-column
              prop="destinationAddress"
              label="收件地址"
              width="340">
            </el-table-column>

          </el-table>
        </el-card>
      </div>
    </div>

  </div>
</div>
</template>

<script>
import Sidebar from "../base/sidebar.vue";
import axios from "axios";

export default {
  name: "goodsSituation",
  components: {Sidebar},
  mounted() {
    this.id = this.$route.query.id;
    let data = {consignorId: this.id};
    var token = localStorage.getItem('token')
    axios.post('http://localhost:8181/order/search', data, {
      headers: {'token': token}
    })
      .then(response => {
        // 处理返回信息
        if (response.data.code == "1") {
          this.orders = response.data.data;
          for (var i = 0; i < response.data.data.length; i++) {
            this.orders[i].fromDate = this.orders[i].fromDate.slice(0, 10);
          }
        } else if (response.data.code == "2") {
          alert("登录已过时，请重新登陆");
          window.location.replace("/");
        }
        console.log("1111111111111")
        console.log(this.orders)
      })
      .catch(error => {
        console.log(error);
      });

  },
  data() {
    return {
      id: this.$route.query.id,
      username: this.$route.query.username,
      email: this.$route.query.email,
      token: localStorage.getItem('token'),
      orders: [
        {
          destinationAddress: '',
          name: '',
          currentLocation: '',
          departAddress: '',
          quantity: 0,
          weight: 0.0,
          fromDate: '',
          status: 'processing',
        },
      ],
      fromdate: " ",
      todate: " ",
      detailTransport:[],
    }
  },

  methods: {
    searchTransport(row,expandedRows){
      if(expandedRows.length>0){
        let data = {
          consignorId: this.id,
          id: row.id
        };
        axios.post('http://localhost:8181/order/transport', data, {
          headers: {'token': this.token}
        })
          .then(response => {
            if (response.data.code=="1"){
              this.detailTransport=response.data.data;
              for(var i=0;i<this.detailTransport.length;i++){
                this.detailTransport[i].fromDate= this.detailTransport[i].fromDate.slice(0, 10);
              }
            }else if(response.data.code=="2"){
              alert("登录已过时，请重新登陆");
              window.location.replace("/");
            }
          })
          .catch(error => {
            console.log(error);
          });
      }else{
        this.detailTransport=[];
      }
    }
  }
}
</script>

<style scoped>
.demo-table-expand {
  font-size: 0;
  margin-left: 30px;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
