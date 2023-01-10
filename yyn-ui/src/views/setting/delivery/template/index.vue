<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="模板名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入模板名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['setting:delivery:template:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['setting:delivery:template:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['setting:delivery:template:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['setting:delivery:template:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deliveryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="模板ID" align="center" prop="deliveryId"/>
      <el-table-column label="模板名称" align="center" prop="name"/>
      <el-table-column label="计费方式" align="center" prop="method">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.delivery_template_billing_method" :value="scope.row.method"/>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="sort"/>
      <el-table-column label="添加时间" align="center" prop="createTime"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['setting:delivery:template:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['setting:delivery:template:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改配送模板主对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="70%" :close-on-click-modal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="140px">
        <el-form-item label="模板名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入模板名称"/>
        </el-form-item>
        <el-form-item label="计费方式" prop="method">
          <el-radio-group v-model="form.method" @input="onChangeMethod">
            <el-radio
              v-for="dict in dict.type.delivery_template_billing_method"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="配送区域及运费" required>
          <el-table
            v-show="ruleList.length"
            :data="ruleList"
            border
            fit>
            <el-table-column
              label="可配送区域">
              <template slot-scope="scope">
                <!--                <p>{{scope.row}}</p>-->
                <p class="content">
                  <span v-for="(province, pidx) in scope.row.regionText" :key="pidx">
                    <span>{{ province.name }}</span>
                    <template v-if="province.citys.length">
                      <span>(</span>
                      <span
                        class="city-name"
                        v-for="(city, cidx) in province.citys"
                        :key="cidx"
                      >{{ city.name }}{{ province.citys.length > cidx + 1 ? '、' : '' }}</span>
                      <span>)</span>
                    </template>
                    <span>{{ ' ' }}</span>
                  </span>
                </p>
                <p class="operation">
                  <a href="javascript:void(0);" class="edit"
                     @click="handleTableAreasEdit(scope.$index, scope.row)">编辑</a>
                  <a href="javascript:void(0);" class="delete" @click="handleTableAreasDelete(scope.$index)">删除</a>
                </p>
              </template>
            </el-table-column>
            <el-table-column
              prop="first"
              :label="first">
              <template slot-scope="scope">
                <el-input-number
                  v-model="scope.row.first"
                  :min="form.method === 10 ? 1 : 0.01"
                  :precision="form.method === 10 ? 0 : 2"
                  size="small"
                />
              </template>
            </el-table-column>
            <el-table-column
              prop="firstFee"
              label="运费(元)">
              <template slot-scope="scope">
                <el-input-number v-model="scope.row.firstFee" size="small" :min="0" :precision="2"/>
              </template>
            </el-table-column>
            <el-table-column
              prop="additional"
              :label="additional">
              <template slot-scope="scope">
                <el-input-number v-model="scope.row.additional" size="small" :min="0"
                                 :precision="form.method === 10 ? 0 : 2"/>
              </template>
            </el-table-column>
            <el-table-column
              prop="additionalFee"
              label="续费(元)">
              <template slot-scope="scope">
                <el-input-number v-model="scope.row.additionalFee" size="small" :min="0" :precision="2"/>
              </template>
            </el-table-column>
          </el-table>
          <el-button plain @click="handleAreasAdd">点击添加配送区域</el-button>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number v-model="form.sort" placeholder="请输入排序"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <AreasModal ref="AreasModal" @handleSubmit="handleAreaSubmit"/>
  </div>
</template>

<script>
import {addDelivery, delDelivery, getDelivery, listDelivery, updateDelivery} from "@/api/setting/delivery/template";
import TreeTransfer from "@/components/TreeTransfer";
import AreasModal from "@/components/Modal/AreasModal";

const defaultItem = {
  key: 0,
  first: 1,
  firstFee: 0,
  additional: 0,
  additionalFee: 0,
  children: [],
  regionText: []
}

export default {
  name: "Delivery",
  dicts: ['delivery_template_billing_method'],
  components: {
    TreeTransfer, AreasModal
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 配送模板主表格数据
      deliveryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        method: null,
        sort: null,
        isDelete: null,
        storeId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          {required: true, message: "模板名称不能为空", trigger: "blur"}
        ],
        method: [
          {required: true, message: "计费方式(10按件数 20按重量)不能为空", trigger: "change"}
        ],
        sort: [
          {required: true, message: "排序方式(数字越小越靠前)不能为空", trigger: "blur"}
        ],
        isDelete: [
          {required: true, message: "是否删除不能为空", trigger: "blur"}
        ],
        storeId: [
          {required: true, message: "小程序d不能为空", trigger: "blur"}
        ],
      },
      ruleList: [],
      first: '首件 (个)',
      additional: '续件 (个)'
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询配送模板主列表 */
    getList() {
      this.loading = true;
      listDelivery(this.queryParams).then(response => {
        this.deliveryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        deliveryId: null,
        name: null,
        method: 10,
        sort: null,
        isDelete: null,
        storeId: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.ruleList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.deliveryId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加配送模板";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const deliveryId = row.deliveryId || this.ids
      getDelivery(deliveryId).then(response => {
        this.form = response.data;
        console.log(this.form)
        this.form.ruleList.forEach(item => {
          item.region = JSON.parse(item.region)
          item.regionText = JSON.parse(item.regionText)
        });
        this.ruleList = this.form.ruleList;
        this.open = true;
        this.title = "修改配送模板";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (!this.ruleList) {
            this.$modal.msgError("请添加配送区域");
            return;
          }
          this.form.ruleList = this.ruleList;
          this.form.ruleList.forEach(item => {
            item.region = JSON.stringify(item.region)
            item.regionText = JSON.stringify(item.regionText)
          })
          if (this.form.deliveryId != null) {
            updateDelivery(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDelivery(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const deliveryIds = row.deliveryId || this.ids;
      this.$modal.confirm('是否确认删除配送模板主编号为"' + deliveryIds + '"的数据项？').then(function () {
        return delDelivery(deliveryIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('store/delivery/export', {
        ...this.queryParams
      }, `delivery_${new Date().getTime()}.xlsx`)
    },
    // 更改计费方式
    onChangeMethod(e) {
      const text = {
        10: {first: '首件 (个)', additional: '续件 (个)'},
        20: {first: '首重 (Kg)', additional: '续重 (Kg)'}
      }
      this.first = text[e].first
      this.additional = text[e].additional
    },
    // 新增记录
    handleAreasAdd() {
      const index = this.ruleList.length
      const newItem = {...defaultItem, key: index}
      // 排除的城市id集(已存在的城市id集)
      const excludedCityIds = this.getExcludedCityIds()
      if (excludedCityIds.length === this.citysCount) {
        this.$modal.msgError("已选择了所有的区域");
        return false
      }
      // 显示选择地区对话框
      this.handleAreasModal('add', index, newItem, excludedCityIds)
    },
    // 编辑记录
    handleAreasEdit(index, item) {
      // 排除的城市id集(已存在的城市id集)
      const excludedCityIds = this.getExcludedCityIds()
      // 显示选择地区对话框
      this.handleAreasModal('edit', index, item, excludedCityIds)
    },
    // 显示选择地区对话框
    handleAreasModal(scene, index, item, excludedCityIds) {
      this.$refs.AreasModal.handle({scene, index, item}, item.region, excludedCityIds)
    },
    // 排除的城市id集(已存在的城市id集)
    getExcludedCityIds() {
      const excludedCityIds = []
      this.ruleList.forEach(item => {
        item.region.forEach(cityId => {
          excludedCityIds.push(cityId)
        })
      })
      return excludedCityIds
    },
    // 删除记录
    handleAreasDelete(index) {
      const app = this
      // const modal = this.$confirm({
      //   title: '您确定要删除该记录吗?',
      //   onOk () {
      //     app.ruleList.splice(index, 1)
      //     modal.destroy()
      //   }
      // })
    },

    // 选择地区后的回调
    handleAreaSubmit(result) {
      console.log(result)
      const {custom: {scene, item}} = result
      item.region = result.selectedCityIds
      item.regionText = result.selectedText
      if (scene === 'add') {
        this.ruleList.push(item)
      }
    },
    // 编辑记录
    handleTableAreasEdit(index, item) {
      // 排除的城市id集(已存在的城市id集)
      const excludedCityIds = this.getExcludedCityIds()
      // 显示选择地区对话框
      this.handleAreasModal('edit', index, item, excludedCityIds)
    },
    // 删除记录
    handleTableAreasDelete(index) {
      const app = this
      const modal = this.$modal.confirm('您确定要删除该记录吗').then(function () {
        app.ruleList.splice(index, 1)
      })
    },
  }
};
</script>

<style scoped>
.operation {
  text-align: right;
}

.operation a {
  font-size: 13px;
  margin-left: 6px;
  color: #00a0e9;
}

.content {
  color: #505050;
  white-space: normal;
}

.content .city-name {
  font-size: 12.5px;
  color: #7b7b7b;
}

</style>
