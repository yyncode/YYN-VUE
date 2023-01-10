<template>
<div class="app-container">
  <el-form :form="form" @submit="handleSubmit">
    <el-form-item label="缓存项目">
      <el-checkbox-group v-model="checkedCities" @change="handleCheckedCitiesChange">
        <el-checkbox v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox>
      </el-checkbox-group>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" html-type="submit">提交</el-button>
    </el-form-item>
  </el-form>
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

</style>
