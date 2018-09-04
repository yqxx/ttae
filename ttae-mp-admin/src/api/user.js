import axios from '@/libs/api.request'

export const login = ({ username, password }) => {
  return axios.request({
    url: '/login',
    params: {
      username,
      password
    },
    method: 'get'
  })
}

export const getUserInfo = (token) => {
  return axios.request({
    url: '/info',
    params: {
      token
    },
    method: 'get'
  })
}

export const logout = (token) => {
  return axios.request({
    url: 'logout',
    method: 'post'
  })
}
