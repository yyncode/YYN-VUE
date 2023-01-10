<template>
  <div class="app-container">
    <el-form :form="form" @submit="handleSubmit">
      <el-form-item
        label="配送方式"
        extra="用户下单时可以选择的配送方式"
      >
        <el-checkbox-group
          v-model="form.delivery_type"
        >
          <el-checkbox :value="10">快递配送</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
      <el-divider orientation="left">物流查询API</el-divider>
      <el-form-item label="快递100 Customer" >
        <el-input v-model="form.kuaidi100.customer" />
        <div class="form-item-help">
          <small style="margin-right: 6px;">用于查询快递/物流信息，需快递100企业版API</small>
          <a href="https://api.kuaidi100.com/home" target="_blank">去申请</a>
        </div>
      </el-form-item>
      <el-form-item label="快递100 Key" >
        <el-input v-model="form.kuaidi100.key" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" html-type="submit">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "index",
  data(){
    return {
      form:{
        delivery_type:10,
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

</style>
