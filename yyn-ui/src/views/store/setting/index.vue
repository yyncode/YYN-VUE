<template>
  <div class="app-container" style="width: 800px">
    <div style="margin: 20px;" v-if="!isLoading"></div>
      <el-form ref="form" :label-position="labelPosition" label-width="150px" :model="form">
        <el-form-item label="商城名称：">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="商城简介：">
          <el-input type="textarea" v-model="form.region"></el-input>
        </el-form-item>
        <el-form-item label="商城Logo：">
<!--          <image-upload v-model="form.url" :limit="1"></image-upload>-->
          <SelectImage
            :defaultList="form.logoImage ? [form.logoImage] : []"
            v-model="form.logoImageId"
          />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">提交</el-button>
        </el-form-item>
      </el-form>
  </div>
</template>

<script>

import {getStoreDetail} from "@/api/store/store";
import SelectImage from "@/components/SelectImage"

export default {
  name: "index",
  components:{
    SelectImage
  },
  data() {
    return {
      labelPosition: 'right',
      isLoading: false,
      form: {
        name: '',
        region: '',
        url: '',
        logoImage:[],
        logoImageId:0
      }
    };
  },
  // 初始化数据
  created () {
    // 获取当前详情记录
    this.getDetail()
  },
  methods: {
    // 获取当前详情记录
    getDetail () {
      this.isLoading = true
      getStoreDetail().then(response => {
        console.log(response)
        this.loading = false;
      });
    },

    onSubmit: function () {
      console.log(this.form)
    }
  }

}
</script>

<style scoped>

</style>
