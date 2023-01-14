<template>
  <div class="app-container">
    <div class="store-setting">
      <div class="form-item-group">

        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        商城名称：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <el-input placeholder="请输入内容" size="small" v-model="form.storeName"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        商城简介：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <el-input placeholder="请输入内容" size="small" v-model="form.describe"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        商城Logo：</span>
          <div class="form-item-content">
            <div class="form-item-component">
              <select-image :defaultList="logoImageUrls" :defaultClickType="clickType"
                            v-model="form.logoImageId" :maxNum="1"  :width="150"/>
            </div>
          </div>
<!--          {{this.logoImageUrls}}-->
        </div>
      </div>
      <div class="form-item-group ">
        <div class="form-item">
          <div class="form-item-btn">
            <el-button type="primary" @click="onSubmit">提交</el-button>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>

import {getStoreDetail, saveStore} from "@/api/store/store";
import SelectImage from "@/components/SelectImage"

export default {
  name: "index",
  components: {
    SelectImage
  },
  data() {
    return {
      clickType:'update',
      form: {
        storeName: '',
        describe: '',
        logoImageId: 0
      },
      logoImageUrls: [],
    };
  },
  // 初始化数据
  created() {
    // 获取当前详情记录
    this.getDetail()
  },
  methods: {
    // 获取当前详情记录
    getDetail() {
      getStoreDetail().then(response => {
        this.form.storeName = response.data.storeName;
        this.form.describe = response.data.describe;
        this.form.logoImageId = response.data.logoImageId;
        if (response.data.logoImageUrl) {
          this.logoImageUrls = [{fileId:response.data.logoImageId,cover:response.data.logoImageUrl}]
        }
        // this.logoImageUrls = [response.data.logoImageUrl]
      });
    },

    onSubmit: function () {
      saveStore(this.form).then(response => {
        this.$modal.msgSuccess("保存成功");
        this.getDetail();
      });
    }
  }

}
</script>

<style scoped>
.store-setting .form-item-group .form-item {
  display: flex;
  margin-top: 40px;
  margin-left: 40px;
}

.store-setting .form-item-group .form-item .form-item-label {
  margin-left: 5px;
  margin-right: 10px;
  font-size: 13px;
  width: 150px;
  text-align: right;
}

.store-setting .form-item-group .form-item .form-item-input-label {
  margin-left: 5px;
  margin-right: 10px;
  font-size: 13px;
  padding-top: 5px;
  width: 150px;
  text-align: right;
}

.store-setting .form-item-group .form-item .form-item-content .form-item-text {
  font-size: 13px;
}

.el-input {
  width: 350px;
}

.store-setting .form-item-group .form-item .form-item-btn {
  margin-left: 165px;
}

.store-setting .form-item-group .form-item .form-item-help {
  color: rgba(0, 0, 0, .45);
  font-size: 13px !important;
}
</style>
