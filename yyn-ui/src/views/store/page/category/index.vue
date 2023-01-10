<template>
  <div class="app-container">
    <div class="container clearfix">
      <el-row>
        <el-col :span="12">
          <div class="grid-content bg-purple preview">
            <img
              v-if="form.style"
              :src="`/static/img/category/${form.style}.png`"
            />
          </div>
        </el-col>
        <el-col :span="12">
          <div class="grid-content bg-purple-light form-box">
            <el-form :form="form" @submit="handleSubmit">
              <el-form-item label="分类页样式" :labelCol="labelCol" :wrapperCol="wrapperCol">
                <el-radio-group  v-model="form.style">
                  <el-radio
                    v-for="(item, index) in PageCategoryStyleEnum.data"
                    :key="index"
                    :label="item.value"
                  >{{ item.name }}</el-radio>
                </el-radio-group>
                <div class="form-item-help">
                  <p
                    v-if="form.style === PageCategoryStyleEnum.ONE_LEVEL_BIG.value"
                    class="extra"
                  >分类图尺寸：宽750像素 高度不限</p>
                  <p
                    v-if="form.style === PageCategoryStyleEnum.ONE_LEVEL_SMALL.value"
                    class="extra"
                  >分类图尺寸：宽188像素 高度不限</p>
                  <p
                    v-if="form.style === PageCategoryStyleEnum.TWO_LEVEL.value"
                    class="extra"
                  >分类图尺寸：宽150像素 高150像素</p>
                  <p
                    v-if="form.style === PageCategoryStyleEnum.COMMODITY.value"
                    class="extra"
                  >分类图尺寸：宽150像素 高150像素</p>
                </div>
              </el-form-item>
              <el-form-item label="分享标题" :labelCol="labelCol" :wrapperCol="wrapperCol">
                <el-input type="number" v-model="form.shareTitle">

<!--                  <template slot="suffix" ><span style="color: #0c0d0e">小时后自动关闭</span></template>-->
                </el-input>
<!--                <span class="input-text_right">{{form.shareTitle}}</span>-->
                <div class="form-item-help">
                  <p class="extra">分类页面分享时的标题内容</p>
                </div>
              </el-form-item>
              <el-form-item
                v-show="form.style === PageCategoryStyleEnum.COMMODITY.value"
                label="购物车按钮"
                :labelCol="labelCol"
                :wrapperCol="wrapperCol"
              >
                <el-radio-group v-model="form.showAddCart">
                  <el-radio :label="true">显示</el-radio>
                  <el-radio :label="false">隐藏</el-radio>
                </el-radio-group>
                <div class="form-item-help">
                  <p class="extra">是否显示加入购物车图标按钮</p>
                </div>
              </el-form-item>
              <el-form-item
                v-show="form.style === PageCategoryStyleEnum.COMMODITY.value"
                label="按钮样式"
                :labelCol="labelCol"
                :wrapperCol="wrapperCol"
              >
                <el-radio-group v-model="form.cartStyle">
                  <el-radio v-for="(val, index) in 3" :key="index" :label="index + 1" >样式{{ index + 1 }}</el-radio>
                </el-radio-group>
                <div class="form-item-help">
                  <p class="extra">加入购物车图标按钮的样式</p>
                </div>
              </el-form-item>
              <el-form-item :wrapperCol="{ span: wrapperCol.span, offset: labelCol.span }">
                <el-button type="primary" html-type="submit">提交</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import {SettingEnum} from "@/common/enum/store";
import {PageCategoryStyleEnum} from '@/common/enum/store/page/category'

export default {
  name: "index",
  components: {},
  data() {
    return {
      // 当前设置项的key
      key: SettingEnum.PAGE_CATEGORY_TEMPLATE.value,
      // 标签布局属性
      labelCol: {span: 5},
      // 输入框布局属性
      wrapperCol: {span: 19},
      // loading状态
      isLoading: false,
      // 枚举类
      PageCategoryStyleEnum,
      // 当前表单元素
      form: {
        style: 20,
        shareTitle: '',
        showAddCart: true,
        cartStyle: 1
      },
      // 当前记录详情
      record: {}
    }
  },
  // 初始化数据
  created() {
    // 获取当前详情记录
    this.getDetail()
  },
  methods: {

    // 获取当前详情记录
    getDetail() {

    },

    // 设置默认值
    setFieldsValue() {

    },

    // 确认按钮
    handleSubmit(e) {
      e.preventDefault()
      // 表单验证
      const {form: {validateFields}} = this
      validateFields((errors, values) => {
        // 提交到后端api
        !errors && this.onFormSubmit(values)
      })
    },

    // 提交到后端api
    onFormSubmit(values) {

    }

  }
}
</script>

<style scoped>
/deep/ .ant-form-item-control {
  padding-left: 10px;
}

/deep/ .ant-form-item-control .ant-form-item-control {
  padding-left: 0;
}

.container {
  max-width: 1000px;
  margin: 0 auto;
}

.container .preview {
  width: 300px;
}

.container .preview img {
  display: block;
  width: 100%;
  box-shadow: 0 3px 10px #dcdcdc;
}

.container .form-box {
  width: 650px;
}
.extra {
  color: rgba(0,0,0,.45);
  font-size: 13px!important;
}
</style>
