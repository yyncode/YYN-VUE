<template>
  <div class="app-container">
    <el-form :model="this.form" :rules="rules" label-width="200px" size="small" style="width: 800px">
      <el-form-item label="小程序 AppID" prop="appId">
        <el-input
          placeholder="请输入小程序AppID"
          v-model="form.appId"
        />
      </el-form-item>
      <el-form-item label="小程序 AppSecret" prop="appSecret">
        <el-input
          placeholder="请输入小程序AppSecret"
          type="password"
          v-model="form.appSecret"
        />
      </el-form-item>
      <el-divider content-position="left"><span style="font-size: 13px;color: rgba(0,0,0,.45)">微信小程序支付</span>
      </el-divider>
      <el-form-item label="微信商户号 MCHID" prop="mchid">
        <el-input
          placeholder="请输入微信商户号mchid"
          v-model="form.mchid"
        />
      </el-form-item>
      <el-form-item label="微信支付密钥 APIKEY" prop="apikey">
        <el-input
          type="password"
          v-model="form.apikey"
          placeholder="请输入微信支付密钥"
        />
      </el-form-item>
      <el-form-item class="mt-40" label="证书文件cert" prop="certPem">
        <el-input
          type="textarea"
          placeholder="使用文本编辑器打开apiclient_cert.pem文件，将文件的全部内容复制进来"
          v-model="form.certPem"
        >
        </el-input>
        <div class="form-item-help">
          <small>使用文本编辑器打开apiclient_cert.pem文件，将文件的全部内容复制进来</small>
        </div>
      </el-form-item>
      <el-form-item label="证书文件key" prop="keyPem">
        <el-input
          type="textarea"
          placeholder="使用文本编辑器打开apiclient_key.pem文件，将文件的全部内容复制进来"
          v-model="form.keyPem"
        >
        </el-input>
        <div class="form-item-help">
          <small>使用文本编辑器打开apiclient_key.pem文件，将文件的全部内容复制进来</small>
        </div>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="handleSubmit">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {getSetting, saveSetting} from "@/api/store/setting";

export default {
  name: "index",
  data() {
    return {
      key:'wxapp_miniprogram',
      form: {
        appId: '',
        appSecret: '',
        mchid: '',
        apikey: '',
        certPem: '',
        keyPem: ''
      },// 表单校验
      rules: {
        appId: [
          {required: true, message: "请输入小程序AppID", trigger: "blur"}
        ],
        appSecret: [
          {required: true, message: "请输入小程序AppSecret", trigger: "blur"}
        ],
        mchid: [
          {required: true, message: "请输入微信商户号mchid", trigger: "blur"}
        ],
        apikey: [
          {required: true, message: "请输入微信支付密钥", trigger: "blur"}
        ],
        certPem: [
          {required: true, message: "请输入apiclient_cert.pem文件中的内容", trigger: "blur"}
        ],
        keyPem: [
          {required: true, message: "请输入apiclient_key.pem文件中的内容", trigger: "blur"}
        ],
      }
    }
  },
  created() {
    this.handleGetSetting();
  },
  methods: {
    handleGetSetting() {
      getSetting(this.key).then(response => {
        this.form = JSON.parse(response.data.values);
      });
    },
    handleSubmit() {
      this.form.key = this.key;
      this.form.values = JSON.stringify(this.form);
      saveSetting(this.form).then(response => {
        this.$modal.msgSuccess("保存成功");
        this.handleGetSetting();
      });
    }
  }
}
</script>

<style scoped>
.el-form {
  margin: 40px;
}

.el-form-item {
  margin-bottom: 20px;
}

.form-item-help {
  color: rgba(0, 0, 0, .45);
  font-size: 13px !important;
}
</style>
