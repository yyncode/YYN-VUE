<template>
<div class="app-container">
  <el-form :form="form" @submit="handleSubmit">
    <el-form-item label="默认登录/注册方式" >
      <el-radio-group v-model="form.registerMethod">
        <el-radio :value="10">手机号 + 短信验证码</el-radio>
      </el-radio-group>
      <div class="form-item-help">
        <p class="extra">
          <small>发送短信服务需要先配置</small>
          <router-link target="_blank" :to="{ path: '/setting/sms' }">短信通知设置</router-link>
        </p>
        <p class="extra">使用手机号作为主账户可以实现多种客户端的账户统一，例如H5和微信小程序</p>
      </div>
    </el-form-item>

    <el-divider orientation="left">微信小程序授权登录</el-divider>

    <el-form-item label="一键授权登录/注册" >
      <el-radio-group v-model="form.isOauthMpweixin">
        <el-radio :value="1">
          <span>开启</span>
          <el-tag class="ml-5" color="green">推荐</el-tag>
        </el-radio>
        <el-radio :value="0">关闭</el-radio>
      </el-radio-group>
      <div class="form-item-help">
        <small>开启后在微信小程序端一键获取用户授权并登录和注册</small>
      </div>
    </el-form-item>

    <el-form-item
      v-show="form.isOauthMpweixin === 1"
      label="注册时绑定手机号"
      :labelCol="labelCol"
      :wrapperCol="wrapperCol"
    >
      <el-radio-group v-model="form.isForceBindMpweixin">
        <el-radio :value="1">
          <span>强制绑定</span>
          <el-tag class="ml-5" color="green">推荐</el-tag>
        </el-radio>
        <el-radio :value="0">不绑定</el-radio>
      </el-radio-group>
      <div class="form-item-help">
        <p class="extra">开启后在微信小程序端一键授权注册时强制绑定手机号，仅首次注册时弹出</p>
        <p
          v-show="form.isForceBindMpweixin === 0"
          class="extra c-red"
        >如果不强制绑定手机号，会造成多端情况下同一个用户注册多个账户，强烈推荐绑定手机号</p>
      </div>
    </el-form-item>

    <el-form-item
      v-show="form.isOauthMpweixin === 1 && form.isForceBindMpweixin === 0"
      label="手动绑定手机号"
      :labelCol="labelCol"
      :wrapperCol="wrapperCol"
    >
      <el-radio-group v-model="form.isManualBind">
        <el-radio :value="1">显示</el-radio>
        <el-radio :value="0">不显示</el-radio>
      </el-radio-group>
      <div class="form-item-help">
        <small>用户在个人中心页可以手动操作绑定手机号</small>
      </div>
    </el-form-item>

    <el-form-item >
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
        registerMethod:'',
        isOauthMpweixin:1,
        isForceBindMpweixin:undefined,
        isManualBind:1

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
