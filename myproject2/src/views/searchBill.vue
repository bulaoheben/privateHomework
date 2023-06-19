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
                                :row-class-name="tableRowClassName"
                                style="width: 100%;">

                            <el-table-column
                                    label="展开"
                                    type="expand">
                                <template slot-scope="props" >
                                    <el-form label-position="left" inline class="demo-table-expand">
                                        <el-form-item label="ID">
                                            <span>{{ props.row.id }}</span>
                                        </el-form-item>
                                        <el-form-item label="名称">
                                            <span>{{ props.row.name }}</span>
                                        </el-form-item>
                                        <el-form-item label="费用">
                                            <span>{{ props.row.cost }}元</span>
                                        </el-form-item>
                                        <el-form-item label="数量">
                                            <span>{{ props.row.quantity }}</span>
                                        </el-form-item>
                                        <el-form-item label="重量">
                                            <span>{{ props.row.weight }}Kg</span>
                                        </el-form-item>
                                        <el-form-item label="状态">
                                            <span>{{ props.row.status }}</span>
                                        </el-form-item>
                                        <el-form-item label="原寄地">
                                            <span>{{ props.row.departAddress }}</span>
                                        </el-form-item>
                                        <el-form-item label="收件地址">
                                            <span>{{ props.row.destinationAddress }}</span>
                                        </el-form-item>
                                    </el-form>
                                </template>
                            </el-table-column>

                            <el-table-column
                                    prop="id"
                                    label="ID"
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

                            <el-table-column
                                    prop="quantity"
                                    label="数量">
                            </el-table-column>

                            <el-table-column
                                    prop="weight"
                                    label="重量">
                            </el-table-column>

                            <el-table-column
                                    prop="cost"
                                    label="费用">
                            </el-table-column>

                        </el-table>
                    </el-card>
                    <!--&lt;!&ndash;          <div style="display: flex;width: 800px;justify-content:center;align-items:center;margin-left: 40px;margin-top: 30px">-->
                    <!--            <el-input-->
                    <!--              style="margin-right: 30px;width: 300px"-->
                    <!--              placeholder="请选择日期"-->
                    <!--              suffix-icon="el-icon-date"-->
                    <!--              v-model="fromdate">-->
                    <!--            </el-input>-->
                    <!--            <i class="el-icon-caret-right" style="margin-right: 30px"></i>-->
                    <!--            <el-input-->
                    <!--              style="width: 300px"-->
                    <!--              placeholder="请选择日期"-->
                    <!--              suffix-icon="el-icon-date"-->
                    <!--              v-model="todate">-->
                    <!--            </el-input>-->
                    <!--          </div>&ndash;&gt;-->
                </div>
            </div>

        </div>
    </div>
</template>

<script>
import Sidebar from "../components/base/sidebar.vue";
import axios from "axios";
import request from "@/network/request";
import router from "@/router";

export default {
    name: "searchBill",
    components: {Sidebar},
    mounted() {
        this.id = this.$route.query.id;
        let data = {consignorId:this.id};
        var token = localStorage.getItem('token')
        request.post(
            "/api/order/search",
            data,
            {
                headers: {'token':token}
            }
        ).then(response => {
            // 处理返回信息
            console.log("3333333333333333333333333")
            console.log(response)
            if (response.code=="1"){
                this.orders=response.data;
                for(var i=0;i<response.data.length;i++){
                    this.orders[i].fromDate= this.orders[i].fromDate.slice(0, 10);
                }
            }else if(response.code=="2"){
                alert("登录已过时，请重新登陆");
                window.location.replace("/");
            }
        })

    },
    data(){
        return{
            id:this.$route.query.id,
            username:this.$route.query.username,
            email:this.$route.query.email,
            orders:[
                {
                    destinationAddress:'',
                    name:'',
                    currentLocation:'',
                    departAddress:'',
                    quantity:0,
                    weight:0.0,
                    fromDate:'',
                    status:'processing',
                    cost:0
                },
            ],
            fromdate:" ",
            todate:" ",
        }
    },
    methods:{
        tableRowClassName({row}) {
            console.log("23232323233",row.status)
            if (row.status == "processing") {
                return 'warning-row';
            } else {
                return 'success-row';
            }
            return '';
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

.el-table .warning-row {
    background: oldlace;
}

.el-table .success-row {
    background: #f0f9eb;
}
</style>
