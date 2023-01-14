<template>
  <div class="app-container">
    <div class="register">
      <div class="form-item">
        <span style="color: red">*</span>
        <span class="form-item-label">
        默认登录/注册方式:
      </span>
        <div class="form-item-content">
          <div class="form-item-radio">
            <el-radio v-model="form.registerMethod">手机号 + 短信验证码</el-radio>
          </div>
          <div class="form-item-help">
            <p class="extra">
              <small>发送短信服务需要先配置</small>
              <router-link target="_blank" :to="{ path: '/setting/sms' }" style="color:#1890ff;">短信通知设置</router-link>
            </p>
            <p class="extra">使用手机号作为主账户可以实现多种客户端的账户统一，例如H5和微信小程序</p>
          </div>
        </div>
      </div>
      <el-divider content-position="left"><span style="font-size: 13px;color: rgba(0,0,0,.45)">微信小程序授权登录</span>
      </el-divider>
      <div class="form-item">
        <span style="color: red">*</span>
        <span class="form-item-label">
        一键授权登录/注册:
      </span>
        <div class="form-item-content">
          <div class="form-item-radio">
            <el-radio-group v-model="form.isOauthMpweixin">
              <el-radio :label="1">
                <span>开启</span>
                <!--              <el-tag color="green">推荐</el-tag>-->
              </el-radio>
              <el-radio :label="0">关闭</el-radio>
            </el-radio-group>
          </div>
          <div class="form-item-help">
            <p class="extra">
              <small>开启后在微信小程序端一键获取用户授权并登录和注册</small>
            </p>
          </div>
        </div>
      </div>
      <div class="form-item" v-show="form.isOauthMpweixin === 1">
        <span style="color: red">*</span>
        <span class="form-item-label">
        注册时绑定手机号:
      </span>
        <div class="form-item-content">
          <div class="form-item-radio">
            <el-radio-group v-model="form.isForceBindMpweixin">
              <el-radio :label="1">
                <span>强制绑定</span>
                <!--              <el-tag color="green">推荐</el-tag>-->
              </el-radio>
              <el-radio :label="0">不绑定</el-radio>
            </el-radio-group>
          </div>
          <div class="form-item-help">
            <p class="extra">开启后在微信小程序端一键授权注册时强制绑定手机号，仅首次注册时弹出</p>
            <p
              v-show="form.isForceBindMpweixin === 0"
              class="extra c-red"
            >如果不强制绑定手机号，会造成多端情况下同一个用户注册多个账户，强烈推荐绑定手机号</p>
          </div>
        </div>
      </div>

      <el-button type="primary" style="margin-left: 150px;margin-top: 20px" @click="handleSubmit">提交</el-button>

    </div>
  </div>
</template>

<script>
import {getSetting, saveSetting} from "@/api/store/setting";

export default {
  name: "index",
  data() {
    return {
      key:'register',
      form: {
        registerMethod: '',
        isOauthMpweixin: 1,
        isForceBindMpweixin: undefined,
        isManualBind: 1

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
.form-item {
  display: flex;
  margin-top: 40px;
  margin-left: 40px;
}

.register .form-item .form-item-label {
  margin-left: 5px;
  margin-right: 10px;
  font-size: 13px;
  /*justify-content: flex-end;*/
}

.register .form-item .form-item-content .form-item-radio {
  margin: 0;
}

.register .form-item .form-item-content .form-item-help {
  color: rgba(0, 0, 0, .45);
  font-size: 13px !important;
}
</style>
