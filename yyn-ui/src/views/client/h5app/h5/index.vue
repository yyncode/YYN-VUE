<template>
  <div class="app-container">
    <el-form :form="form" @submit="handleSubmit">
      <el-form-item class="mt-30" label="是否开启访问" >
        <el-radio-group v-model="form.enabled">
          <el-radio :value="1">开启</el-radio>
          <el-radio :value="0">关闭</el-radio>
        </el-radio-group>
        <div class="form-item-help">
          <p class="extra">
            <span>注：如关闭，用户则无法通过H5端访问商城</span>
          </p>
        </div>
      </el-form-item>
      <el-form-item label="H5站点地址">
        <el-input
          v-model="form.baseUrl"
        />
        <div class="form-item-help">
          <p class="extra">
            <span>请填写H5端实际的访问地址，以</span>
            <el-tag class="mlr-5">https://</el-tag>开头； 斜杠
            <el-tag class="mlr-5">/</el-tag>
            <span>结尾</span>
          </p>
          <p class="extra">
            <span>例如：https://www.aaa.com/</span>
          </p>
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
