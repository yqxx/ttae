<template>
  <div>
    <Card>
       <p slot="title">
        </p>
        <div slot="extra">
            <ButtonGroup>
                <Button type="success" @click="modal = true">增加</Button>
                <Button type="warning" @click="exportExcel">导出</Button>
            </ButtonGroup>
        </div>
      <Table ref="tables" stripe border :columns="cols" :data="datas" :loading="loading"></Table>
      <Modal
          v-model="modal"
          :mask-closable="false"
          title="角色配置">
          <Form ref="form" :model="data" :rules="rule" :label-width="80">
              <FormItem label="编码">
                  <Input v-model="data.code"></Input>
              </FormItem>
              <FormItem label="名称">
                  <Input v-model="data.name"></Input>
              </FormItem>
              <FormItem label="角色点">
                  <Input v-model="data.actions"></Input>
              </FormItem>
          </Form>
          <div slot="footer">
              <ButtonGroup>
                  <Button type="success" @click="onSave">保存</Button>
                  <Button @click="modal = false;data = {}">取消</Button>
              </ButtonGroup>
          </div>
      </Modal>
    </Card>
  </div>
</template>

<script>
import axios from '@/libs/api.request'

export default {
  name: 'role_list',
  data () {
    return {
      url: '/role',
      loading: false,
      data: {},
      datas: [],
      modal: false,
      rule: {
        code: [{ required: true, message: '编码', trigger: 'blur' }],
        name: [{ required: true, message: '名称', trigger: 'blur' }],
        actions: [{ required: true, message: '角色点', trigger: 'blur' }]
      },
      cols: [
        { title: '编码', key: 'code' },
        { title: '名称', key: 'name' },
        { title: '角色点', key: 'actions' },
        {
          title: '操作',
          key: 'action',
          width: 150,
          align: 'center',
          render: (h, params) => {
            return h('div', [
              h(
                'Button',
                {
                  props: { type: 'text', size: 'small' },
                  on: {
                    click: () => {
                      this.data = this.datas[params.index]
                      this.modal = true
                    }
                  }
                },
                '编辑'
              ),
              h(
                'Button',
                {
                  props: { type: 'text', size: 'small' },
                  on: {
                    click: () => {
                      this.data = this.datas[params.index]
                      this.onDelete()
                    }
                  }
                },
                '删除'
              )
            ])
          }
        }
      ]
    }
  },
  methods: {
    onSave () {
      let method = this.data.id ? 'put' : 'post'
      axios
        .request({
          url: this.url,
          data: this.data,
          method: method
        })
        .then(res => {
          if (res.status === 'SUCCESS') {
            this.onLoad()
            this.$Message.success('保存成功')
          } else {
            this.$Message.error(res.msg)
          }
        })
        .catch(() => {
          this.$Message.error('保存失败')
        })
      this.modal = false
    },
    onDelete () {
      axios
        .request({
          url: this.url,
          data: this.data,
          method: 'delete'
        })
        .then(res => {
          if (res.status === 'SUCCESS') {
            this.onLoad()
            this.$Message.success('删除成功')
          } else {
            this.$Message.error(res.msg)
          }
        })
        .catch(() => {
          this.$Message.error('删除失败')
        })
    },
    onLoad () {
      this.loading = true
      axios
        .request({
          url: this.url,
          method: 'get'
        })
        .then(res => {
          this.datas = res.data
          this.loading = false
        })
        .catch(() => {
          this.$Message.error('查询失败')
        })
    },
    exportExcel () {
      this.$refs.tables.exportCsv({
        filename: `table-${new Date().valueOf()}.csv`
      })
    }
  },
  mounted () {
    this.onLoad()
  }
}
</script>
