<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="用户ID" prop="userid">
      <el-input v-model="dataForm.userid" placeholder="用户ID"></el-input>
    </el-form-item>
    <el-form-item label="商品ID" prop="goodsId">
      <el-input v-model="dataForm.goodsId" placeholder="商品ID"></el-input>
    </el-form-item>
    <el-form-item label="支付渠道。alipay，支付宝；weixin，微信；apple，苹果" prop="channel">
      <el-input v-model="dataForm.channel" placeholder="支付渠道。alipay，支付宝；weixin，微信；apple，苹果"></el-input>
    </el-form-item>
    <el-form-item label="第三方订单号" prop="orderId">
      <el-input v-model="dataForm.orderId" placeholder="第三方订单号"></el-input>
    </el-form-item>
    <el-form-item label="实际支付人民币" prop="money">
      <el-input v-model="dataForm.money" placeholder="实际支付人民币"></el-input>
    </el-form-item>
    <el-form-item label="状态。1，下单；2，支付成功；3，发送金币完成；4，失效；" prop="state">
      <el-input v-model="dataForm.state" placeholder="状态。1，下单；2，支付成功；3，发送金币完成；4，失效；"></el-input>
    </el-form-item>
    <el-form-item label="创建订单时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建订单时间"></el-input>
    </el-form-item>
    <el-form-item label="失效时间" prop="invalidTime">
      <el-input v-model="dataForm.invalidTime" placeholder="失效时间"></el-input>
    </el-form-item>
    <el-form-item label="支付成功时间" prop="payTime">
      <el-input v-model="dataForm.payTime" placeholder="支付成功时间"></el-input>
    </el-form-item>
    <el-form-item label="完成时间" prop="completeTime">
      <el-input v-model="dataForm.completeTime" placeholder="完成时间"></el-input>
    </el-form-item>
    <el-form-item label="" prop="type">
      <el-input v-model="dataForm.type" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          userid: '',
          goodsId: '',
          channel: '',
          orderId: '',
          money: '',
          state: '',
          createTime: '',
          invalidTime: '',
          payTime: '',
          completeTime: '',
          type: ''
        },
        dataRule: {
          userid: [
            { required: true, message: '用户ID不能为空', trigger: 'blur' }
          ],
          goodsId: [
            { required: true, message: '商品ID不能为空', trigger: 'blur' }
          ],
          channel: [
            { required: true, message: '支付渠道。alipay，支付宝；weixin，微信；apple，苹果不能为空', trigger: 'blur' }
          ],
          orderId: [
            { required: true, message: '第三方订单号不能为空', trigger: 'blur' }
          ],
          money: [
            { required: true, message: '实际支付人民币不能为空', trigger: 'blur' }
          ],
          state: [
            { required: true, message: '状态。1，下单；2，支付成功；3，发送金币完成；4，失效；不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建订单时间不能为空', trigger: 'blur' }
          ],
          invalidTime: [
            { required: true, message: '失效时间不能为空', trigger: 'blur' }
          ],
          payTime: [
            { required: true, message: '支付成功时间不能为空', trigger: 'blur' }
          ],
          completeTime: [
            { required: true, message: '完成时间不能为空', trigger: 'blur' }
          ],
          type: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/zdgame/tblorder/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.userid = data.tblOrder.userid
                this.dataForm.goodsId = data.tblOrder.goodsId
                this.dataForm.channel = data.tblOrder.channel
                this.dataForm.orderId = data.tblOrder.orderId
                this.dataForm.money = data.tblOrder.money
                this.dataForm.state = data.tblOrder.state
                this.dataForm.createTime = data.tblOrder.createTime
                this.dataForm.invalidTime = data.tblOrder.invalidTime
                this.dataForm.payTime = data.tblOrder.payTime
                this.dataForm.completeTime = data.tblOrder.completeTime
                this.dataForm.type = data.tblOrder.type
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/zdgame/tblorder/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'userid': this.dataForm.userid,
                'goodsId': this.dataForm.goodsId,
                'channel': this.dataForm.channel,
                'orderId': this.dataForm.orderId,
                'money': this.dataForm.money,
                'state': this.dataForm.state,
                'createTime': this.dataForm.createTime,
                'invalidTime': this.dataForm.invalidTime,
                'payTime': this.dataForm.payTime,
                'completeTime': this.dataForm.completeTime,
                'type': this.dataForm.type
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
