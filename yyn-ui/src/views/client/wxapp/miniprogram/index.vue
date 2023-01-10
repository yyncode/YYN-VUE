<template>
<div class="app-container">
  <el-form :form="form" @submit="handleSubmit">
    <el-form-item class="mt-30" label="小程序 AppID">
      <el-input
        placeholder="请输入小程序AppID"
        v-model="form.app_id"
      />
    </el-form-item>
    <el-form-item label="小程序 AppSecret">
      <el-input
        placeholder="请输入小程序AppSecret"
        type="password"
        v-model="form.app_secret"
      />
    </el-form-item>
    <el-divider orientation="left">微信小程序支付</el-divider>
    <el-form-item label="微信商户号 MCHID" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <el-input
        placeholder="请输入微信商户号mchid"
        v-model="form.mchid"
      />
    </el-form-item>
    <el-form-item label="微信支付密钥 APIKEY" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <el-input
        type="password"
        v-model="form.apikey"
        placeholder="请输入微信支付密钥"
      />
    </el-form-item>
    <el-form-item class="mt-40" label="证书文件cert" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <el-input
        type="textarea"
        placeholder="使用文本编辑器打开apiclient_cert.pem文件，将文件的全部内容复制进来"
        v-model="form.cert_pem"
      >
      </el-input>
      <div class="form-item-help">
        <small>使用文本编辑器打开apiclient_cert.pem文件，将文件的全部内容复制进来</small>
      </div>
    </el-form-item>
    <el-form-item label="证书文件key" :labelCol="labelCol" :wrapperCol="wrapperCol">
      <el-input
        type="textarea"
        placeholder="使用文本编辑器打开apiclient_key.pem文件，将文件的全部内容复制进来"
        v-model="form.key_pem"
      >
      </el-input>
      <div class="form-item-help">
        <small>使用文本编辑器打开apiclient_key.pem文件，将文件的全部内容复制进来</small>
      </div>
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
  data() {
    return {
      form:{
        app_id:'',
        app_secret:'',
        mchid:'',
        apikey:'',
        cert_pem:'',
        key_pem:''
      }
    }
  },
  methods:{
    handleSubmit() {

    }
  }
}
</script>

<style scoped>

</style>
