import axios from 'axios';
import jwt from 'jsonwebtoken';

// 设置JWT的密钥
const JWT_SECRET = 'your_jwt_secret';

// 设置Axios的默认配置
axios.defaults.baseURL = 'http://localhost:8181/';
axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');

// 创建一个API请求函数
export function api(endpoint, method, data = null) {
  return axios({
    method: method,
    url: endpoint,
    data: data,
  })
    .then(response => response.data)
    .catch(error => {
      if (error.response.status === 401) {
        // 如果API返回401错误，则表示未授权，需要重新认证
        localStorage.removeItem('token');
        location.reload();
      } else {
        throw error;
      }
    });
}

// 创建一个认证函数
export function authenticate(username, password) {
  return axios.post('auth/token', { username: username, password: password })
    .then(response => {
      // 从API响应中获取JWT并将其存储到本地存储中
      const token = response.data.access_token;
      localStorage.setItem('token', token);
      // 使用JWT密钥验证并返回JWT负载
      const payload = jwt.verify(token, JWT_SECRET);
      return payload;
    });
}
