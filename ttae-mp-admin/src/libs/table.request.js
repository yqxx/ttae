import HttpRequest from '@/libs/axios'
const axios = new HttpRequest()

export const del = (that) => {
  return new Promise((resolve, reject) => {
    axios
      .request({
        url: that.table.url,
        data: that.table.data,
        method: 'delete'
      })
      .then(res => {
        if (res.status === 'SUCCESS') {
          that.onLoad.call()
          that.$Message.success('删除成功')
        } else {
          that.$Message.error(res.msg)
        }
        resolve(res)
      })
      .catch(err => {
        that.$Message.error('删除失败')
        reject(err)
      })
  })
}

export const save = (that) => {
  let method = that.table.data.id ? 'put' : 'post'
  return new Promise((resolve, reject) => {
    axios
      .request({
        url: that.table.url,
        data: that.table.data,
        method: method
      })
      .then(res => {
        if (res.status === 'SUCCESS') {
          that.onLoad.call()
          that.$Message.success('保存成功')
        } else {
          that.$Message.error(res.msg)
        }
        resolve(res)
      })
      .catch(err => {
        that.$Message.error('保存失败')
        reject(err)
      })
  })
}

export const load = (that) => {
  that.table.loading = true
  return new Promise((resolve, reject) => {
    axios
      .request({
        url: that.table.url,
        method: 'get'
      })
      .then(res => {
        that.table.datas = res.data
        that.table.loading = false
        resolve(res)
      })
      .catch(err => {
        that.$Message.error('查询失败')
        reject(err)
      })
  })
}
