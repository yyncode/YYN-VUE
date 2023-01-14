<template>
  <div class="app-container">
    <div class="upload">
      <div class="form-item-group">
        <div class="form-item">

          <span class="form-item-label" ><span style="color: red;padding-top: 4px">*</span>
        默认上传方式：
      </span>
          <div class="form-item-content">
            <div class="form-item-radio">
              <el-radio-group v-model="form.default">
                <el-radio
                  v-for="(item, index) in StorageEnum.data"
                  :key="index"
                  :label="item.value"
                >{{ item.name }} {{ item.value === StorageEnum.LOCAL.value ? '(不推荐)' : '' }}</el-radio>
              </el-radio-group>
              <!--            <el-radio-group v-model="form.freightRule">-->
              <!--              -->
              <!--              <el-radio :label="10">-->
              <!--                <span>叠加</span>-->
              <!--                &lt;!&ndash;              <el-tag color="green">推荐</el-tag>&ndash;&gt;-->
              <!--              </el-radio>-->
              <!--              <el-radio :label="20">以最低运费结算</el-radio>-->
              <!--              <el-radio :label="30">以最高运费结算</el-radio>-->
              <!--            </el-radio-group>-->
            </div>
          </div>
        </div>
      </div>
      <div class="form-item-group" v-show="form.default === StorageEnum.QINIU.value">
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        存储空间名称 Bucket：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        ACCESS_KEY AK：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        SECRET_KEY SK：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        空间域名 Domain：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
      </div>
      <div class="form-item-group" v-show="form.default === StorageEnum.ALIYUN.value">
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        存储空间名称 Bucket：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        AccessKeyId：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        AccessKeySecret：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        空间域名 Domain：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
      </div>
      <div class="form-item-group" v-show="form.default === StorageEnum.QCLOUD.value">
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        存储空间名称 Bucket：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        所属地域 Region：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        SecretId：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        SecretKey：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        空间域名 Domain：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
      </div>
      <div class="form-item-group ">
        <div class="form-item">
          <div class="form-item-btn">
            <el-button type="primary" >提交</el-button>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import StorageEnum from '@/common/enum/file/Storage'

export default {
  name: "index",

  data(){
    return {
      form:{
        default:StorageEnum.LOCAL.value
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
.upload .form-item-group .form-item{
  display: flex;
  margin-top: 40px;
  margin-left: 40px;
}

.upload .form-item-group .form-item .form-item-label {
  margin-left: 5px;
  margin-right: 10px;
  font-size: 13px;
  width: 150px;
  text-align: right;
}
.upload .form-item-group .form-item .form-item-input-label {
  margin-left: 5px;
  margin-right: 10px;
  font-size: 13px;
  padding-top: 5px;
  width: 150px;
  text-align: right;
}

.el-input {
  width: 350px;
}

.upload .form-item-group .form-item  .form-item-btn {
  margin-left: 165px;
}

</style>
