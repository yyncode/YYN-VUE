<template>
  <div class="app-container">
    <div class="content">
      <el-form :form="form" @submit="handleSubmit">
        <el-form-item label="未支付订单:">
          <div>
            <el-input-number
              :min="0"
              :precision="0"
            />
            <span class="input-text_right">小时后自动关闭</span>
          </div>
          <div class="form-item-help">
            <p class="extra">如果在期间订单未付款，系统自动关闭，设置0小时不自动关闭</p>
          </div>
        </el-form-item>
        <el-form-item label="已发货订单:">
          <div>
            <el-input-number
              :min="0"
              :precision="0"
            />
            <span class="input-text_right">天后自动确认收货</span>
          </div>
          <div class="form-item-help">
            <p class="extra">如果在期间未确认收货，系统自动完成收货，设置0天不自动收货</p>
          </div>
        </el-form-item>
        <el-form-item label="已完成订单:">
          <div>
            <el-input-number
              :min="0"
              :precision="0"
            />
            <span class="input-text_right">天内允许申请售后</span>
          </div>
          <div class="form-item-help">
            <p class="extra">订单完成后，用户在指定期限内可申请售后，设置0天不允许申请</p>
          </div>
        </el-form-item>

        <el-divider content-position="left">运费设置</el-divider>

        <el-form-item style="padding-top: 20px" label="运费组合策略">
          <el-radio-group v-model="radio">
            <div class="radio-item">
              <div>
                <el-radio :label="10">叠加</el-radio>
              </div>
            </div>
            <div class="radio-item">
              <div>
                <el-radio :label="20">以最低运费结算</el-radio>
              </div>
            </div>
            <div class="radio-item">
              <div>
                <el-radio :label="30">以最高运费结算</el-radio>
              </div>
            </div>
          </el-radio-group>
          <div class="form-item-help" v-show="radio === 10">
            <p class="extra">订单中的商品有多个运费模板时，将每个商品的运费之和订为订单总运费</p>
          </div>
          <div class="form-item-help" v-show="radio === 20">
            <p class="extra">订单中的商品有多个运费模板时，取订单中运费最少的商品的运费计为订单总运费</p>
          </div>
          <div class="form-item-help" v-show="radio === 30">
            <p class="extra">订单中的商品有多个运费模板时，取订单中运费最多的商品的运费计为订单总运费</p>
          </div>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" html-type="submit">提交</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "index",
  data () {
    return {
      // 当前设置项的key
      key: 'trade',
      // 标签布局属性
      labelCol: { span: 4 },
      // 输入框布局属性
      wrapperCol: { span: 10 },
      // loading状态
      isLoading: false,
      // 当前表单元素
      form: {},
      // 单选框样式
      radioStyle: {
        display: 'block',
        height: '30px',
        lineHeight: '30px'
      },
      // 当前记录详情
      record: {},
      radio: 10
    }
  },
  // 初始化数据
  created () {
    // 获取当前详情记录
    this.getDetail()
  },
  methods: {

    // 获取当前详情记录
    getDetail () {
      this.isLoading = true
    },


    /**
     * 确认按钮
     */
    handleSubmit (e) {
      e.preventDefault()
      // 表单验证
      const { form: { validateFields } } = this
      validateFields((errors, values) => {
        // 提交到后端api
        !errors && this.onFormSubmit(values)
      })
    },

    /**
     * 提交到后端api
     */
    onFormSubmit (values) {
      this.isLoading = true
    }

  }
}
</script>

<style scoped>
/*.content {*/
/*  margin: 20px;*/
/*  display: flex;*/
/*  justify-content: center; !* 水平居中 *!*/
/*  align-items: center;     !* 垂直居中 *!*/
/*}*/
.content{
  padding-left: 100px;
}
.input-text_right {
  margin-left: 10px;
}
.radio-item {
  margin-bottom: 16px;
}
.extra {
  color: rgba(0,0,0,.45);
  font-size: 13px!important;
}

</style>
