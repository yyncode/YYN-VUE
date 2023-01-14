<template>
  <div class="app-container">
    <div class="delivery-way">
      <div class="form-item-group">
        <div class="form-item">

          <span class="form-item-label" ><span style="color: red">*</span>
        短信平台：
      </span>
          <div class="form-item-content">
            <div class="form-item-checkbox">
              <el-checkbox-group
                v-model="form.deliveryType"
              >
                <el-checkbox :value="10">快递配送</el-checkbox>
              </el-checkbox-group>
            </div>

            <div class="form-item-help">
              <p class="extra">
                <small>用户下单时可以选择的配送方式</small>
              </p>
            </div>
          </div>
        </div>
      </div>
      <div class="form-item-group">
        <div class="form-item">
          <el-divider content-position="left"><span style="font-size: 13px;color: rgba(0,0,0,.45);">物流查询API</span></el-divider>
        </div>
      </div>
      <div class="form-item-group">

        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        快递100 Customer：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
            <div class="form-item-help">
              <p class="extra">
                <small>用于查询快递/物流信息，需快递100企业版API<a href="https://api.kuaidi100.com/home" target="_blank"><span style="color: #00a0e9">去申请</span></a></small>
              </p>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        快递100 Key：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
      </div>
      <div class="form-item-group ">
        <div class="form-item">
          <div class="form-item-btn">
            <el-button type="primary" >提交</el-button>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "index",
  data(){
    return {
      form:{
        deliveryType:10,
        kuaidi100:{
          customer:'',
          key:''
        }
      }
    }
  },
  methods: {
    /**
     * 确认按钮
     */
    handleSubmit (e) {
      e.preventDefault()
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
.delivery-way .form-item-group .form-item{
  display: flex;
  margin-top: 40px;
  margin-left: 40px;
}

.delivery-way .form-item-group .form-item .form-item-label {
  margin-left: 5px;
  margin-right: 10px;
  font-size: 13px;
  width: 150px;
  text-align: right;
}
.delivery-way .form-item-group .form-item .form-item-input-label {
  margin-left: 5px;
  margin-right: 10px;
  font-size: 13px;
  padding-top: 5px;
  width: 150px;
  text-align: right;
}

.delivery-way .form-item-group .form-item .form-item-content .form-item-text{
  font-size: 13px;
}

.el-input {
  width: 350px;
}

.delivery-way .form-item-group .form-item  .form-item-btn {
  margin-left: 165px;
}
.delivery-way .form-item-group .form-item .form-item-help {
  color: rgba(0, 0, 0, .45);
  font-size: 13px !important;
}
</style>
