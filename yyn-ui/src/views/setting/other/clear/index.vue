<template>
<div class="app-container">
  <div class="other-clear">
    <div class="form-item-group">
      <div class="form-item">
          <span class="form-item-label" ><span style="color: red">*</span>
        缓存项目：
      </span>
        <div class="form-item-content">
          <div class="form-item-checkbox">
            <el-checkbox-group v-model="checkedCities" @change="handleCheckedCitiesChange">
              <el-checkbox v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox>
            </el-checkbox-group>
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
const cityOptions = ['商品分类', '商城设置', '小程序设置', '临时图片'];
export default {
  name: "index",
  data() {
    return {
      checkAll: false,
      checkedCities: ['上海', '北京'],
      cities: cityOptions,
      isIndeterminate: true,
      form:{

      }
    };
  },
  methods: {
    handleCheckAllChange(val) {
      this.checkedCities = val ? cityOptions : [];
      this.isIndeterminate = false;
    },
    handleCheckedCitiesChange(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.cities.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
    },
    /**
     * 确认按钮
     */
    handleSubmit (e) {
      e.preventDefault()
    },

    /**
     * 提交到后端api
     */
    onFormSubmit (values) {
      this.isLoading = true
    }
  }
}
</script>

<style scoped>
.other-clear .form-item-group .form-item{
  display: flex;
  margin-top: 40px;
  margin-left: 40px;
}

.other-clear .form-item-group .form-item .form-item-label {
  margin-left: 5px;
  margin-right: 10px;
  font-size: 13px;
  width: 150px;
  text-align: right;
}
.other-clear .form-item-group .form-item  .form-item-btn {
  margin-left: 165px;
}
</style>
