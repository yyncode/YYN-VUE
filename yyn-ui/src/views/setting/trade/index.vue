<template>
  <div class="app-container">
    <div class="trade">
      <div class="form-item">
        <span style="color: red;padding-top: 4px">*</span>
        <span class="form-item-input-label">
        未支付订单：</span>
        <div class="form-item-content">
          <div class="form-item-input">
            <el-input-number
              :min="0"
              :precision="0"
              controls-position="right"
              size="mini"
              v-model="form.order.closeDays"
            />
            <span class="input-text_right">小时后自动关闭</span>
<!--            <el-radio v-model="form.registerMethod">手机号 + 短信验证码</el-radio>-->
          </div>
          <div class="form-item-help">
            <p class="extra">如果在期间订单未付款，系统自动关闭，设置0天不自动关闭</p>
          </div>
        </div>
      </div>
      <div class="form-item">
        <span style="color: red;padding-top: 4px">*</span>
        <span class="form-item-input-label">
        已发货订单：</span>
        <div class="form-item-content">
          <div class="form-item-input">
            <el-input-number
              :min="0"
              :precision="0"
              controls-position="right"
              size="mini"
              v-model="form.order.receiveDays"
            />
            <span class="input-text_right">小时后自动关闭</span>
            <!--            <el-radio v-model="form.registerMethod">手机号 + 短信验证码</el-radio>-->
          </div>
          <div class="form-item-help">
            <p class="extra">如果在期间订单未付款，系统自动关闭，设置0天不自动关闭</p>
          </div>
        </div>
      </div>
      <div class="form-item">
        <span style="color: red;padding-top: 4px">*</span>
        <span class="form-item-input-label">
        未支付订单：</span>
        <div class="form-item-content">
          <div class="form-item-input">
            <el-input-number
              :min="0"
              :precision="0"
              controls-position="right"
              size="mini"
              v-model="form.order.refundDays"
            />
            <span class="input-text_right">小时后自动关闭</span>
            <!--            <el-radio v-model="form.registerMethod">手机号 + 短信验证码</el-radio>-->
          </div>
          <div class="form-item-help">
            <p class="extra">如果在期间订单未付款，系统自动关闭，设置0天不自动关闭</p>
          </div>
        </div>
      </div>
      <el-divider content-position="left"><span style="font-size: 13px;color: rgba(0,0,0,.45)">运费设置</span>
      </el-divider>
      <div class="form-item">
        <span style="color: red">*</span>
        <span class="form-item-label" >
        运费组合策略:
      </span>
        <div class="form-item-content">
          <div class="form-item-radio">
            <el-radio-group v-model="form.freightRule">
              <el-radio :label="10">
                <span>叠加</span>
                <!--              <el-tag color="green">推荐</el-tag>-->
              </el-radio>
              <el-radio :label="20">以最低运费结算</el-radio>
              <el-radio :label="30">以最高运费结算</el-radio>
            </el-radio-group>
          </div>
          <div class="form-item-help">
            <p class="extra" v-if="form.freightRule===10">
              <small>订单中的商品有多个运费模板时，将每个商品的运费之和订为订单总运费</small>
            </p>
            <p class="extra"  v-if="form.freightRule===20">
              <small>订单中的商品有多个运费模板时，取订单中运费最少的商品的运费计为订单总运费</small>
            </p>
            <p class="extra" v-if="form.freightRule===30">
              <small>订单中的商品有多个运费模板时，取订单中运费最多的商品的运费计为订单总运费</small>
            </p>
          </div>
        </div>
      </div>

      <el-button type="primary" style="margin-left: 150px;margin-top: 20px" @click="handleSubmit">提交</el-button>

    </div>
  </div>
</template>


<script>
export default {
  name: "index",
  data() {
    return {
      // 当前设置项的key
      key: 'trade',
      // 标签布局属性
      labelCol: {span: 4},
      // 输入框布局属性
      wrapperCol: {span: 10},
      // loading状态
      isLoading: false,
      // 当前表单元素
      form: {
        order: {
          closeDays:0,
          receiveDays:0,
          refundDays:0
        },
        freightRule:10
      },
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
  created() {
    // 获取当前详情记录
    this.getDetail()
  },
  methods: {

    // 获取当前详情记录
    getDetail() {
      this.isLoading = true
    },


    /**
     * 确认按钮
     */
    handleSubmit(e) {
      e.preventDefault()
      // 表单验证
      const {form: {validateFields}} = this
      validateFields((errors, values) => {
        // 提交到后端api
        !errors && this.onFormSubmit(values)
      })
    },

    /**
     * 提交到后端api
     */
    onFormSubmit(values) {
      this.isLoading = true
    }

  }
}
</script>

<style scoped>
.form-item {
  display: flex;
  margin-top: 40px;
  margin-left: 40px;
}

.trade .form-item .form-item-label {
  margin-left: 5px;
  margin-right: 10px;
  font-size: 13px;
  /*padding-top: 5px;*/
  /*justify-content: flex-end;*/
}
.trade .form-item .form-item-input-label {
  margin-left: 5px;
  margin-right: 10px;
  font-size: 13px;
  padding-top: 5px;
  /*justify-content: flex-end;*/
}

.trade .form-item .form-item-content .form-item-radio {
  margin: 0;
}

.trade .form-item .form-item-content .form-item-input {
  margin: 0;
}

.trade .form-item .form-item-content .form-item-input .input-text_right {
  margin-left: 10px;
  font-size: 13px;
}

.trade .form-item .form-item-content .form-item-help {
  color: rgba(0, 0, 0, .45);
  font-size: 13px !important;
}
</style>
