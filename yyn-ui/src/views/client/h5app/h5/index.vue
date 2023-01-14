<template>
  <div class="app-container">
    <el-form :model="form" :rules="rules" label-width="200px" size="small" style="width: 800px" >
      <el-form-item class="mt-30" label="是否开启访问" prop="enabled">
        <el-radio-group v-model="form.enabled">
          <el-radio :label="1">开启</el-radio>
          <el-radio :label="0">关闭</el-radio>
        </el-radio-group>
        <div class="form-item-help">
          <p class="extra">
            <span>注：如关闭，用户则无法通过H5端访问商城</span>
          </p>
        </div>
      </el-form-item>
      <el-form-item label="H5站点地址" prop="baseUrl">
        <el-input
          v-model="form.baseUrl"
        />
        <div class="form-item-help">
          <p class="extra">
            <span>请填写H5端实际的访问地址，以</span>
            <el-tag>https://</el-tag>开头； 斜杠
            <el-tag>/</el-tag>
            <span>结尾</span>
          </p>
          <p class="extra">
            <span>例如：https://www.aaa.com/</span>
          </p>
        </div>
      </el-form-item>
      <el-form-item >
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
      key: 'h5app_h5',
      form:{
        enabled:1,
        baseUrl:''
      },
      rules: {
        enabled: [
          {required: true, message: "请选择是否开启访问", trigger: "blur"}
        ],
        baseUrl: [
          {required: true, message: "请填写H5端实际的访问地址", trigger: "blur"}
        ]
      }
    }
  },
  created() {
    this.handleGetSetting();
  },
  methods:{
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
