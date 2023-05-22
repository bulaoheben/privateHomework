<template>
<div>
  <div style="display: flex;height: 100%">
    <sidebar style="height: 100%"></sidebar>
    <div style="display: flex">
      <el-card style="width: 750px;margin-left: 40px;margin-top: 30px;height: 700px">
        <h5 style="position: relative;left: 120px">（带*的选项可以不填）</h5>
        <el-form ref="form" :model="form" label-width="120px">
          <el-form-item label="货物名称">
            <el-input v-model="form.name" style="width: 480px"></el-input>
          </el-form-item>
          <el-form-item label="数量*">
            <el-input  style="width: 480px" v-model="form.quantity"></el-input>
          </el-form-item>
          <el-form-item label="重量">
            <el-input  style="width: 480px" v-model="form.weight">
              <template slot="append">kg</template>
            </el-input>
          </el-form-item>
          <el-form-item label="出发时间">
            <el-date-picker  type="date" value-format="yyyy-MM-dd" placeholder="Pick a date" v-model="form.fromdate" :picker-options="pickerOptions" style="width: 480px"></el-date-picker>
          </el-form-item>
        </el-form>

        <el-form :inline="true" :model="form" style="padding-left:66px;margin-top: 50px">
          <el-form-item label="原寄地">
            <el-cascader
              size="large"
              :options="options"
              v-model="form.departAddresscode"
              @change="handleChange">
            </el-cascader>
          </el-form-item>
          <el-form-item label="详细地址">
            <el-input v-model="form.detailAdd1"></el-input>
          </el-form-item>
        </el-form>

        <el-form :inline="true" :model="form" style="padding-left:66px ">
          <el-form-item label="收件地址">
            <el-cascader
              size="large"
              :options="options"
              v-model="form.destinationAddresscode"
              @change="handleChange">
            </el-cascader>
          </el-form-item>
          <el-form-item label="详细地址">
            <el-input v-model="form.detailAdd2"></el-input>
          </el-form-item>
        </el-form>

        <el-form ref="form" :model="form" label-width="120px" style="margin-top: 30px">
          <el-form-item label="收件人姓名">
            <el-input style="width: 480px" v-model="form.reciverName"></el-input>
          </el-form-item>
          <el-form-item label="收件人电话">
            <el-input style="width: 480px" v-model="form.reciverPhone"></el-input>
          </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">确定</el-button>
          <el-button @click="cancel()">取消</el-button>
          <el-button @click="test()">测试数据</el-button>
        </el-form-item>
      </el-form>
    </el-card>

      <el-card style="width: 480px;margin-left: 40px;margin-top: 30px;height: 700px" v-show="this.display">
        <h3 style="text-align: center;margin-bottom: 10px">预览订单</h3>

        <el-form ref="form" :model="form" label-width="120px">
          <el-form-item label="货物名称">
            <el-input
              :placeholder="this.form.name"
              :readonly="true">
            </el-input>
          </el-form-item>
          <el-form-item label="原寄地">
            <el-input
              :placeholder="this.form.departAddress+'/'+this.form.detailAdd1"
              :readonly="true">
            </el-input>
          </el-form-item>
          <el-form-item label="收件地址">
            <el-input
              :placeholder="this.form.destinationAddress+'/'+this.form.detailAdd2"
              :readonly="true">
            </el-input>
          </el-form-item>
        </el-form>

        <el-form ref="form" :model="form" label-width="120px">
          <el-form-item label="数量*">
            <el-input
              :placeholder="this.form.quantity"
              :readonly="true">
            </el-input>
          </el-form-item>
          <el-form-item label="重量">
            <el-input
              :placeholder="this.form.weight"
              :readonly="true">
              <template slot="append">kg</template>
            </el-input>
          </el-form-item>
          <el-form-item label="出发时间">
            <el-input
              :placeholder="this.form.fromdate"
              :readonly="true">
            </el-input>
          </el-form-item>
          <el-form-item label="预计费用" id="cost">
            <el-input
              :placeholder="this.cost"
              :readonly="true">
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit2">确认</el-button>
            <el-button @click="cancel2()">取消</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>

</div>
</template>

<script>
import Sidebar from "../base/sidebar.vue";
import axios from "axios";
import {regionData,CodeToText} from 'element-china-area-data';

export default {
  name: "mainpage",
  components: {Sidebar},
  data(){
    return{
      id:this.$route.query.id,
      username:this.$route.query.username,
      email:this.$route.query.email,

      pickerOptions:{
        disabledDate (time) {
          return time.getTime() < Date.now()//选当前时间之后的时间
        }
      },
      form:{
        destinationAddresscode:'',
        departAddresscode:'',
        destinationAddress:'',
        departAddress:'',

        name:'',
        currentLocation:'',
        quantity:0,
        weight:0.0,
        fromdate:'',
        detailAdd1:'',
        detailAdd2:'',
        reciverName:'',
        reciverPhone:'',
      },
      display:false,
      cost:0,

      options: regionData,
      selectedOptions: [],
      addtions:{},			//存储地址数据
    }
  },
  methods:{
    handleChange (value) {
      //我们选择地址后，selectedOptions 会保存对应的区域码，例如北京的区域码为'110000'
      //我们要取出区域码对应的汉字，就需要用CodeToText(区域码)进行输出
      this.addtions.selectedOptions = value
      var name = ''
      this.selectedOptions.map(item => name += CodeToText[item] + '/')
      this.addtions.names = name
    },
    test(){
        this.form.name='123',
        this.form.currentLocation='123',
        this.form.quantity='123',
        this.form.weight='123',
        this.form.fromdate='2023-05-17',
        this.form.detailAdd1='123',
        this.form.detailAdd2='123',
        this.form.reciverName='123',
        this.form.reciverPhone='123'
    },
    cancel(){
      this.form.destinationAddress=' ',
      this.form.name=' ',
      this.form.currentLocation=' ',
      this.form.departAddress=' ',
      this.form.quantity=' ',
      this.form.weight=' ',
      this.form.fromdate='2023-05-17',
      this.form.detailAdd1=' ',
      this.form.detailAdd2=' ',
      this.form.reciverName=' ',
      this.form.reciverPhone=' '
    },
    cancel2(){
      this.display=false;
    },
    onSubmit(){
      const pattern = /^\d*[1-9]\d*$/;
      if(!pattern.test(this.form.weight)){
        alert("请输入正确的货物重量！（货物重量应>0）")
      }else if (/^\s*$/.test(this.form.name)){
        alert("货物名称不得为空！")
      }else if (/^\s*$/.test(this.form.departAddresscode)){
        alert("起始地点不得为空！")
      }else if (/^\s*$/.test(this.form.destinationAddresscode)){
        alert("最终地点不得为空！")
      }else if (/^\s*$/.test(this.form.fromdate)){
        alert("出发时间不得为空！")
      }else if (/^\s*$/.test(this.form.detailAdd1)){
        alert("寄出地的详细地址不得为空！")
      }else if (/^\s*$/.test(this.form.detailAdd2)){
        alert("收件地址的详细地址不得为空！")
      }else if (/^\s*$/.test(this.form.reciverName)){
        alert("收件人姓名不得为空！")
      }else if (/^\s*$/.test(this.form.reciverPhone)){
        alert("收件人电话不得为空！")
      }else{
        this.cost=this.form.weight*6;
        // var codeArray=[110000,110100,110101];
        // this.form.departAddress=this.getCodeToText(codeArray);
        // this.form.destinationAddress=this.getCodeToText(codeArray);
        this.form.departAddress=this.getCodeToText(this.form.departAddresscode);
        this.form.destinationAddress=this.getCodeToText(this.form.destinationAddresscode);
        this.display=true;
      }
    },
    onSubmit2(){
      var token = localStorage.getItem('token')
      let data={
        consignorId: this.id,

        destinationAddress: this.form.destinationAddress+'/'+this.form.detailAdd2,
        departAddress: this.form.departAddress+'/'+this.form.detailAdd1,
        fromdate: this.form.fromdate,
        cost:this.cost,

        weight: this.form.weight,
        name:this.form.name,
        quantity: this.form.quantity,

        reciverName:this.form.reciverName,
        reciverPhone: this.form.reciverPhone
      };
      axios.post(
        'http://localhost:8181/order/create',
        data,
        {
            headers: {'token':token
        }
      })
        .then(response => {
          // 处理返回信息
          console.log(response.data);
          if (response.data.code=="1"){
            alert("订单创建成功，处理中~")
            this.display=false;
          }else if(response.data.code=="2"){
            alert("登录已过时，请重新登陆");
            window.location.replace("/");
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    getCodeToText(codeArray) {
      let area = "";
      switch (codeArray.length) {
        case 1:
          area += CodeToText[codeArray[0]];
          break;
        case 2:
          area += CodeToText[codeArray[0]] + "/" + CodeToText[codeArray[1]];
          break;
        case 3:
          area +=
            CodeToText[codeArray[0]] +
            "/" +
            CodeToText[codeArray[1]]  +
            "/" +
            CodeToText[codeArray[2]] ;
          break;
        default:
          break;
      }
      return area;
    }
  }
}
</script>

<style scoped>
body {
  height: 100%;
  background-image: linear-gradient(to right, #f0f9eb, #f0f9eb);

}
</style>
