<template>
  <div>
    <el-form-item label="商品规格">
      <div v-if="true" class="form-item-help" style="line-height: 36px">
        <small>最多添加3个商品规格组，生成的SKU数量不能超出50个</small>
      </div>
      <!-- 规格组 -->
      <div v-for="(item, index) in multiSpecData.specList" :key="index" class="spec-group">
        <div class="spec-group-item clearfix">
          <el-input
            v-model="item.specName"
            :readonly="isSpecLocked"
            class="group-item-input"
            placeholder="请输入规格名称"
            @change="onChangeSpecGroupIpt"
          />
          <a
            v-if="!isSpecLocked"
            class="group-item-delete"
            href="javascript:"
            @click="handleDeleteSpecGroup(index)"
          >删除规格组</a>
        </div>
        <div class="spec-value clearfix">
          <div v-for="(itm, idx) in item.valueList" :key="idx" class="spec-value-item">
            <el-input
              v-model="itm.specValue"
              :readonly="isSpecLocked"
              class="value-item-input"
              placeholder="请输入规格值"
              @change="onChangeSpecValueIpt"
            />
            <i
              v-if="!isSpecLocked"
              class="icon-close el-icon-close"
              @click="handleDeleteSpecValue(index, idx)"
            ></i>
          </div>
          <div v-if="!isSpecLocked" class="spec-value-add">
            <a
              class="group-item-delete"
              href="javascript:"
              @click="handleAddSpecValue(index)"
            >新增规格值</a>
          </div>
        </div>
      </div>
      <!-- 添加规格按钮 -->
      <el-button
        v-if="!isSpecLocked && multiSpecData.specList.length < 3"
        class="spec-group-add-btn"
        icon="plus"
        @click="handleAddSpecGroup"
      >添加规格组
      </el-button>
    </el-form-item>
    <el-form-item
      v-show="multiSpecData.skuList.length"
      label="SKU列表"
    >
      <!-- 批量设置 -->
      <div v-if="multiSpecData.skuList.length > 1" class="sku-batch">
        <span class="title">批量设置:</span>
        <el-input-number
          v-model="multiSpecData.skuBatchForm.goodsPrice"
          :min="0.01"
          :precision="2"
          placeholder="商品价格"
          size="mini"
        />
        <el-input-number
          v-model="multiSpecData.skuBatchForm.linePrice"
          :min="0"
          :precision="2"
          placeholder="划线价格"
          size="mini"
        />
        <el-input-number
          v-model="multiSpecData.skuBatchForm.stockNum"
          :min="0"
          :precision="0"
          placeholder="库存数量"
          size="mini"
        />
        <el-input-number
          v-model="multiSpecData.skuBatchForm.goodsWeight"
          :min="0"
          :precision="2"
          placeholder="商品重量"
          size="mini"
        />
        <el-input v-model="multiSpecData.skuBatchForm.goodsSkuNo" placeholder="sku编码" style="width: 200px"/>
        <el-button @click="handleSkuBatch">立即设置</el-button>
      </div>
      <!--      sku列表table-->
      <el-table
        :data="multiSpecData.skuList"
        border
        class="sku-list"
        style="width: 100%"
      >
        <el-table-column
          v-for="(item, index) in multiSpecData.specList"
          :key="'specValue'+item.key"
          :label="item.specName"
          :prop="'specValue'+item.key"
          width="110px"
        >
        </el-table-column>
        <el-table-column
          label="预览图"
          prop="imageId"
          width="70px"
        >
          <!-- 预览图 -->
          <template slot-scope="scope">
            <SelectImage
              v-model="scope.row.imageId"
              :defaultList="(scope.row.imageId > 0 && scope.row.image) ? [scope.row.image] : []"
              :width="50"
            />
          </template>
        </el-table-column>
        <el-table-column
          label="商品价格"
          prop="goodsPrice">
          <!-- 商品价格 -->
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.goodsPrice" :min="0.01" :precision="2" size="mini"/>
          </template>
        </el-table-column>
        <el-table-column
          label="划线价格"
          prop="linePrice">
          <!-- 划线价格 -->
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.linePrice" :min="0" :precision="2" size="mini"/>
          </template>
        </el-table-column>
        <el-table-column
          label="库存数量"
          prop="stockNum">
          <!-- 库存数量 -->
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.stockNum" :min="0" :precision="0" size="mini"/>
          </template>
        </el-table-column>
        <el-table-column
          label="商品重量"
          prop="goodsWeight">
          <!-- 商品重量 -->
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.goodsWeight" :min="0" :precision="2" size="mini"/>
          </template>
        </el-table-column>
        <el-table-column
          label="sku编码"
          prop="goodsSkuNo">
          <!-- sku编码 -->
          <template slot-scope="scope">
            <el-input v-model="scope.row.goodsSkuNo" size="small"/>
          </template>
        </el-table-column>
      </el-table>
    </el-form-item>
  </div>
</template>

<script>
import PropTypes from "vue-types";
import MultiSpecModel from '@/common/model/goods/MultiSpec'
import SelectImage from '@/components/SelectImage'

export default {
  components: {
    SelectImage
  },
  props: {
    // 默认的规格列表
    defaultSpecList: PropTypes.array.def([]),
    // 默认的SKU列表
    defaultSkuList: PropTypes.array.def([]),
    // 商品规格是否锁定(锁定状态下不允许编辑规格)
    isSpecLocked: PropTypes.bool.def(false)
  },
  data() {
    return {
      // // 商品多规格模型
      MultiSpecModel: new MultiSpecModel(),
      // MultiSpecModel: Object,
      multiSpecData: {
        // 规格列表
        specList: [],
        // SKU列表
        skuList: [],
      },
      // 规格组名称列表
      specGroupNameList: [{label: '规格组名称1', prop: 'name1'}, {label: '规格组名称2', prop: 'name2'}]
    }
  },
  watch: {
    defaultSpecList(val) {
      if (val.length && this.MultiSpecModel.isEmpty()) {
        this.getData()
      }
    }
  },
  // 初始化数据
  created() {
    // 获取规格及SKU信息
    this.getData()
  },
  methods: {

    // 获取规格及SKU信息(展示)
    getData() {
      const {defaultSpecList, defaultSkuList} = this
      this.multiSpecData = this.MultiSpecModel.getData(defaultSpecList, defaultSkuList)
    },

    // 获取规格及SKU信息(表单提交)
    getFromSpecData() {
      return this.MultiSpecModel.getFromSpecData()
    },

    // 添加规格组
    handleAddSpecGroup() {
      if (this.checkSkuMaxNum()) {
        this.MultiSpecModel.handleAddSpecGroup()
      }
    },

    // 删除规格组
    handleDeleteSpecGroup(groupIndex) {
      const app = this
      this.$modal.confirm('您确定要删除该规格组吗?').then(function () {
        // 删除元素
        app.MultiSpecModel.handleDeleteSpecGroup(groupIndex)
      })
    },

    // 新增规格值
    handleAddSpecValue(groupIndex) {
      if (this.checkSkuMaxNum()) {
        this.MultiSpecModel.handleAddSpecValue(groupIndex)
      }
    },

    // 删除规格值
    handleDeleteSpecValue(groupIndex, valueIndex) {
      const app = this
      this.$modal.confirm('您确定要删除该规格值吗?').then(function () {
        // 删除元素
        app.MultiSpecModel.handleDeleteSpecValue(groupIndex, valueIndex)
      })
    },

    // 规格组输入框change事件
    onChangeSpecGroupIpt() {
      // 更新skuList
      this.MultiSpecModel.onUpdate(true)
    },

    // 规格值输入框change事件
    onChangeSpecValueIpt(event, itm) {
      // 更新skuList
      this.MultiSpecModel.onUpdate(true)
    },

    // 验证最大sku数量
    checkSkuMaxNum() {
      const skuList = this.multiSpecData.skuList
      if (skuList.length >= 50) {
        this.$modal.msgError(`生成的sku列表数量不能大于50个，当前数量：${skuList.length}个`)
        return false
      }
      return true
    },

    // 批量设置sku事件
    handleSkuBatch() {
      this.MultiSpecModel.handleSkuBatch()
    },

    // 验证多规格表单
    verifyForm() {
      if (!this.MultiSpecModel.verifyForm()) {
        this.$modal.msgError(this.MultiSpecModel.getError())
        return false
      }
      return true
    }

  }
}

</script>
<style scoped>
/*商品多规格*/
.spec-group {
  width: 895px;
  margin-bottom: 15px;
  line-height: normal;
}

.spec-group .ant-input {
  height: 28px;
  padding: 4px 11px;
  font-size: 13px;
  line-height: 1.5;
}

.spec-group .ant-input::placeholder {
  font-size: 13px;
}

.spec-group .form-item-help {
  line-height: 36px;
}

.spec-group .spec-group-item {
  background: #f4f5f9;
  padding: 7px 12px;
  line-height: 28px;
}

.spec-group .spec-group-item .group-item-input {
  float: left;
  width: 180px;
}

.spec-group .spec-group-item .group-item-delete {
  font-size: 12px;
  float: right;
}

.spec-group .spec-value {
  background: #fbfbfb;
  padding: 8px 35px;
}

.spec-group .spec-value .spec-value-add,
.spec-group .spec-value .spec-value-item {
  float: left;
  margin-bottom: 6px;
  margin-top: 6px;
}

.spec-group .spec-value .spec-value-add {
  height: 28px;
  line-height: 28px;
}

.spec-group .spec-value .spec-value-add a {
  font-size: 12px;
}

.spec-group .spec-value .spec-value-item {
  position: relative;
  margin-right: 20px;
}

.spec-group .spec-value .spec-value-item .value-item-input {
  width: 186px;
}

.spec-group .spec-value .spec-value-item:hover .icon-close {
  display: block;
}

.spec-group .spec-value .spec-value-item .icon-close {
  display: none;
  position: absolute;
  top: -8px;
  right: -8px;
  cursor: pointer;
  font-size: 16px;
  color: #7d7d7d;
}

.spec-group-add-btn {
  font-size: 13px;
}

.sku-batch {
  /*line-height: 40px;*/
  /*margin-bottom: 12px;*/
  /*display: flex;*/
}

.sku-batch .title {
  line-height: 28px;
  margin-right: 10px;
  font-size: 13px;
}

.sku-batch .el-input-number {
  margin-right: 10px;
}

.sku-batch .el-input {
  margin-right: 10px;
}

.sku-batch /deep/ .ant-input,
.sku-batch /deep/ .ant-input-number {
  width: 120px;
  margin-right: 15px;
}

.sku-batch /deep/ .ant-input {
  width: 140px;
}

.sku-list {
  width: 895px;
}

.sku-list /deep/ .ant-table-thead > tr > th,
.sku-list /deep/ .ant-table-tbody > tr > td {
  white-space: nowrap;
}

.sku-list /deep/ .ant-table-tbody > tr > td {
  padding: 12px 18px;
}

.sku-list /deep/ .ant-input-sm,
.sku-list /deep/ .ant-input-number-sm {
  height: 28px;
}

.sku-list /deep/ .ant-input-number-sm input {
  height: 26px;
}

</style>
