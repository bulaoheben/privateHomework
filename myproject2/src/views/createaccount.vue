<template>
    <div>
        <div class="login-wrapper">
            <div class="headere" style="padding-top: 30px;padding-left: 20px" @click=""><a href="#/">back</a></div>
            <div class="header">Create Account</div>
            <el-form ref="form" :model="form" :rules="rules" label-width="100px" style="padding-top: 20px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="form.username"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input type="password" v-model="form.password"></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="confirmPassword">
                    <el-input type="password" v-model="form.confirmPassword"></el-input>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="form.email"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">创建账户</el-button>
                    <el-button @click="onReset">重置</el-button>
                    <el-button @click="testinfo">测试数据</el-button>
                </el-form-item>
            </el-form>

        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "createaccount",
    data(){
        return{
            form: {
                username: '',
                password: '',
                confirmPassword: '',
                phone: '',
                email: ''
            },
            rules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur' }
                ],
                confirmPassword: [
                    { required: true, message: '请再次输入密码', trigger: 'blur' },
                    { validator: this.checkPassword, trigger: 'blur' }
                ],
                phone: [
                    { required: true, message: '请输入电话', trigger: 'blur' },
                    { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的电话号码', trigger: 'blur' }
                ],
                email: [
                    { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur,change' }
                ]
            }
        }
    },
    methods:{
        onSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    let data={
                        username:this.form.username,
                        password:this.form.password,
                        phone:this.form.phone,
                        email:this.form.email
                    }
                    axios.post('http://localhost:8181/user/create', data)
                        .then(response => {
                            // 处理返回信息
                            console.log(response.data);
                            if(response.data.data==1){
                                alert("账户创建成功！")
                                this.$router.push({path: `/`});
                            }else {
                                alert(response.data.msg);
                            }
                        })
                        .catch(error => {
                            console.log(error);
                        });

                } else {
                    console.log('error submit');
                    return false;
                }
            });
        },
        onReset() {
            this.$refs.form.resetFields();
        },
        checkPassword(rule, value, callback) {
            if (value !== this.form.password) {
                callback(new Error('两次输入密码不一致'));
            } else {
                callback();
            }
        },
        testinfo(){
            this.form.username="liu";
            this.form.password="yongyuanbudiu";
            this.form.confirmPassword="yongyuanbudiu";
            this.form.phone="18971822345";
            this.form.email="12345678@bjtu.edu.cn";
        }
    }
}
</script>

<style scoped>
body {
    height: 100%;
    background-image: linear-gradient(to right, #00AA55, #a6c1ee);
}
.login-wrapper {
    background-color: #fff;
    width: 800px;
    height: 670px;
    border-radius: 15px;
    padding: 0 50px;
    position: relative;
    left: 50%;
    top: 50%;
    transform: translate(-50%,10%);
}
.header {
    font-size: 38px;
    font-weight: bold;
    text-align: center;
    line-height: 100px;
}
.headere{
    font-size: 18px;
    font-weight: bold;
}
</style>
