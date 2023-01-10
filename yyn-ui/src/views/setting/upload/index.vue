<template>
  <div class="app-container">
    <el-form :form="form" @submit="handleSubmit">
      <el-form-item label="默认上传方式" >
        <el-radio-group v-model="form.default">
          <el-radio
            v-for="(item, index) in StorageEnum.data"
            :key="index"
            :label="item.value"
          >{{ item.name }} {{ item.value === StorageEnum.LOCAL.value ? '(不推荐)' : '' }}</el-radio>
        </el-radio-group>
      </el-form-item>
      <!-- 七牛云配置 -->
      <div v-show="form.default === StorageEnum.QINIU.value">
        <el-form-item label="存储空间名称 Bucket" >
          <el-input />
        </el-form-item>
        <el-form-item label="ACCESS_KEY AK" >
          <el-input/>
        </el-form-item>
        <el-form-item label="SECRET_KEY SK" >
          <el-input />
        </el-form-item>
        <el-form-item label="空间域名 Domain" >
          <el-input />
        </el-form-item>
      </div>
      <!-- 阿里云配置 -->
      <div v-show="form.default === StorageEnum.ALIYUN.value">
        <el-form-item label="存储空间名称 Bucket" >
          <el-input />
        </el-form-item>
        <el-form-item label="AccessKeyId" >
          <el-input />
        </el-form-item>
        <el-form-item label="AccessKeySecret" >
          <el-input  />
        </el-form-item>
        <el-form-item label="空间域名 Domain" >
          <el-input  />
        </el-form-item>
      </div>
      <!-- 腾讯云配置 -->
      <div v-show="form.default === StorageEnum.QCLOUD.value">
        <el-form-item label="存储空间名称 Bucket" >
          <el-input  />
        </el-form-item>
        <el-form-item label="所属地域 Region" >
          <el-input  />
        </el-form-item>
        <el-form-item label="SecretId" >
          <el-input  />
        </el-form-item>
        <el-form-item label="SecretKey" >
          <el-input  />
        </el-form-item>
        <el-form-item label="空间域名 Domain" >
          <el-input  />
        </el-form-item>
      </div>
      <el-form-item>
        <el-button type="primary" html-type="submit">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import StorageEnum from '@/common/enum/file/Storage'

export default {
  name: "index",

  data(){
    return {
      form:{

      },
      StorageEnum
    }
  },
  methods: {
    /**
     * 确认按钮
     */
    handleSubmit (e) {
      e.preventDefault()
    },

  }
}
</script>

<style scoped>

</style>
