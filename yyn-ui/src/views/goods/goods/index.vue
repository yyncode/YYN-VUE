<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品名称" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品编码" prop="goodsNo">
        <el-input
          v-model="queryParams.goodsNo"
          placeholder="请输入商品编码"
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
          v-hasPermi="['store:goods:add']"
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
          v-hasPermi="['store:goods:edit']"
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
          v-hasPermi="['store:goods:remove']"
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
          v-hasPermi="['store:goods:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="goodsList" @selection-change="handleSelectionChange">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="商品ID" prop="goodsId"/>
      <el-table-column label="商品图片" align="center" prop="firstImageUrl">
        <template slot-scope="scope">
          <image-preview :src="scope.row.firstImageUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="商品名称" prop="goodsName"/>
      <el-table-column align="center" label="商品价格" prop="goodsPriceMin"/>
      <el-table-column align="center" label="总销量">
        <template slot-scope="scope">
          {{scope.row.salesInitial + scope.row.salesActual}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="库存总量" prop="stockTotal"/>

<!--      <el-table-column align="center" label="初始销量" prop="salesInitial"/>-->
<!--      <el-table-column align="center" label="实际销量" prop="salesActual"/>-->
      <el-table-column label="商品状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.goods_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="排序" prop="sort"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['store:goods:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['store:goods:remove']"
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

    <!-- 添加或修改商品记录对话框 -->
    <el-dialog :close-on-click-modal="false" :title="title" :visible.sync="open" append-to-body width="1200px">
      <el-form ref="form" :model="form" :rules="rules" label-width="auto">

        <el-tabs v-model="activeName">
          <el-tab-pane label="基本信息" name="first">

            <el-form-item label="商品名称" prop="goodsName">
              <el-input v-model="form.goodsName" placeholder="请输入商品名称"/>
            </el-form-item>

            <el-form-item label="商品分类" prop="goodCategoryIds">
              <treeselect v-model="form.goodCategoryIds" :flat="true" :multiple="true" :normalizer="normalizer"
                          :options="categoryOptions" placeholder="请选择商品分类"/>
            </el-form-item>

            <el-form-item label="商品图片" prop="goodImageIds">
              <select-image :defaultList="form.goodImages" :defaultClickType="clickType"
                            v-model="form.goodImageIds" :maxNum="10" :multiple="true" :width="50"/>
            </el-form-item>

            <el-form-item label="商品编码" prop="goodsNo">
              <el-input v-model="form.goodsNo" placeholder="请输入商品编码"/>
            </el-form-item>

            <el-form-item label="运费模板" prop="deliveryId" required>
              <el-select v-model="form.deliveryId" placeholder="请选择运费模板">
                <el-option
                  v-for="item in deliveryTemplateList"
                  :key="item.deliveryId"
                  :label="item.name"
                  :value="item.deliveryId">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="商品状态" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in dict.type.goods_status"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{ dict.label }}
                </el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="商品排序" prop="sort">
              <el-input-number v-model="form.sort" placeholder="请输入商品排序"/>
            </el-form-item>
          </el-tab-pane>

          <el-tab-pane label="规格/库存" name="second">
            <el-form-item label="规格类型" prop="specType">
              <el-radio-group v-model="form.specType">
                <el-radio
                  v-for="dict in dict.type.goods_spec_type"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{ dict.label }}
                </el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item v-if="form.specType === 10" label="商品价格" prop="goodsPrice">
              <el-input-number v-model="form.goodsPrice" :min="0" :precision="2" placeholder="请输入商品价格"/>
            </el-form-item>

            <el-form-item v-if="form.specType === 10" label="划线价" prop="linePrice">
              <el-input-number v-model="form.linePrice" :min="0" :precision="2" placeholder="请输入划线价格"/>
            </el-form-item>

            <el-form-item v-if="form.specType === 10" label="当前库存数量" prop="stockNum">
              <el-input-number v-model="form.stockNum" :min="0" :precision="0" placeholder="请输入库存数量"/>
            </el-form-item>
            <el-form-item v-if="form.specType === 10" label="商品重量" prop="goodsWeight">
              <el-input-number v-model="form.goodsWeight" :min="0" :precision="2" placeholder="请输入商品重量"/>
            </el-form-item>
            <div>
              <MultiSpec v-if="form.specType === 20"
                         ref="MultiSpec"
                         :defaultSpecList="form.specData?form.specData.specList:[]"
                         :defaultSkuList="form.specData?form.specData.skuList:[]"
              />
            </div>
            <!--            <el-form-item label="商品规格" v-if="form.specType === 20">-->
            <!--              <el-button type="primary">添加规格组</el-button>-->
            <!--            </el-form-item>-->

            <el-form-item label="库存计算方式" prop="deductStockType">
              <el-radio-group v-model="form.deductStockType">
                <el-radio
                  v-for="dict in dict.type.goods_deduct_stock_type"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{ dict.label }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
          </el-tab-pane>


          <el-tab-pane label="商品详情" name="third">
            <el-form-item label="商品详情" prop="content">
              <editor v-model="form.content" :min-height="500"/>
            </el-form-item>
          </el-tab-pane>


          <el-tab-pane label="更多设置" name="fourth">


            <el-form-item label="商品卖点" prop="sellingPoint">
              <el-input v-model="form.sellingPoint" placeholder="请输入内容" type="textarea"/>
            </el-form-item>

            <el-form-item label="服务与承诺" prop="goodsServiceIds">
              <el-select v-model="form.goodsServiceIds" multiple placeholder="请选择服务与承诺">
                <el-option
                  v-for="item in goodsServiceList"
                  :key="item.serviceId"
                  :label="item.name"
                  :value="item.serviceId">
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="初始销量" prop="salesInitial">
              <el-input-number v-model="form.salesInitial" placeholder="请输入初始销量"/>
            </el-form-item>

            <el-divider content-position="left">积分设置</el-divider>
            <el-form-item label="积分赠送" prop="isPointsGift">
              <el-radio-group v-model="form.isPointsGift">
                <el-radio
                  v-for="dict in dict.type.goods_is_points_gift"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{ dict.label }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="积分抵扣" prop="isPointsDiscount">
              <el-radio-group v-model="form.isPointsDiscount">
                <el-radio
                  v-for="dict in dict.type.goods_is_points_discount"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{ dict.label }}
                </el-radio>
              </el-radio-group>
            </el-form-item>


            <el-divider content-position="left">会员折扣设置</el-divider>
            <el-form-item label="会员折扣" prop="isEnableGrade">
              <el-radio-group v-model="form.isEnableGrade">
                <el-radio
                  v-for="dict in dict.type.goods_is_enable_grade"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{ dict.label }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="会员折扣设置" prop="isAloneGrade">
              <el-radio-group v-model="form.isAloneGrade">
                <el-radio
                  v-for="dict in dict.type.goods_is_alone_grade"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{ dict.label }}
                </el-radio>
              </el-radio-group>
            </el-form-item>
            <!--            <el-form-item label="单独设置折扣的配置" prop="aloneGradeEquity">-->
            <!--              <el-input v-model="form.aloneGradeEquity" type="textarea" placeholder="请输入内容"/>-->
            <!--            </el-form-item>-->
          </el-tab-pane>
        </el-tabs>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {addGoods, delGoods, getGoods, listGoods, updateGoods} from "@/api/goods/goods";
import {listDeliveryNoPage} from "@/api/setting/delivery/template"
import {listCategory} from "@/api/goods/category";
import {listServiceNoPage} from "@/api/goods/service"
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import SelectImage from "@/components/SelectImage";
import MultiSpec from "@/views/goods/goods/modules/MultiSpec";

export default {
  name: "Goods",
  components: {
    Treeselect, SelectImage, MultiSpec
  },
  dicts: ['goods_is_enable_grade', 'goods_is_alone_grade', 'goods_is_points_discount', 'goods_spec_type', 'goods_deduct_stock_type', 'goods_is_points_gift', 'goods_is_alone_points_discount', 'goods_status'],
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
      // 商品记录表格数据
      goodsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsName: null,
        goodsNo: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        goodsName: [
          {required: true, message: "商品名称不能为空", trigger: "blur"}
        ],
        goodCategoryIds: [
          {required: true, message: "商品分类不能为空", trigger: "blur"}
        ],
        goodImageIds: [
          {required: true, message: "商品图片不能为空", trigger: "blur"}
        ],
        goodsNo: [
          {required: true, message: "商品编码不能为空", trigger: "blur"}
        ],
        deliveryId: [
          {required: true, message: "配送模板ID不能为空", trigger: "blur"}
        ],
        status: [
          {required: true, message: "商品状态不能为空", trigger: "change"}
        ],
        sort: [
          {required: true, message: "排序(数字越小越靠前)不能为空", trigger: "blur"}
        ],
        specType: [
          {required: true, message: "商品规格不能为空", trigger: "change"}
        ],
        goodsPrice: [
          {required: true, message: "商品价格不能为空", trigger: "blur"}
        ],
        linePrice: [
          {required: true, message: "划线价不能为空", trigger: "blur"}
        ],
        stockNum: [
          {required: true, message: "当前库存数量不能为空", trigger: "blur"}
        ],
        goodsWeight: [
          {required: true, message: "商品重量不能为空", trigger: "blur"}
        ],
        deductStockType: [
          {required: true, message: "库存计算方式不能为空", trigger: "change"}
        ],
        content: [
          {required: true, message: "商品详情不能为空", trigger: "blur"}
        ],
        sellingPoint: [
          {required: true, message: "商品卖点不能为空", trigger: "blur"}
        ],
        goodsServiceIds: [
          {required: true, message: "服务与承诺不能为空", trigger: "blur"}
        ],
        salesInitial: [
          {required: true, message: "初始销量不能为空", trigger: "blur"}
        ],
        isPointsGift: [
          {required: true, message: "是否开启积分赠送不能为空", trigger: "change"}
        ],
        isPointsDiscount: [
          {required: true, message: "是否允许使用积分抵扣不能为空", trigger: "change"}
        ],
        isEnableGrade: [
          {required: true, message: "是否开启会员折扣不能为空", trigger: "change"}
        ],
        isAloneGrade: [
          {required: true, message: "会员折扣设置不能为空", trigger: "change"}
        ]
      },
      activeName: 'first',
      // 商品分类树选项
      categoryOptions: [],
      // 商品分类选择
      goodCategories: [],
      // 商品图片
      formImages: [],
      clickType: null,
      //运费模板列表
      deliveryTemplateList: [],
      //运费模板ID
      deliveryTemplateId: null,
      //服务与承诺
      goodsServiceList: [],
      //已选择的服务与承诺
      goodsServiceIds: [],
    };
  },
  created() {
    this.getList();
    this.getDeliveryTemplateList();
    this.getGoodsServiceList();
  },
  methods: {
    /** 查询商品记录列表 */
    getList() {
      this.loading = true;
      listGoods(this.queryParams).then(response => {
        this.goodsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getDeliveryTemplateList() {
      listDeliveryNoPage().then(response => {
        this.deliveryTemplateList = response.data;
      })
    },
    getGoodsServiceList() {
      listServiceNoPage().then(response => {
        this.goodsServiceList = response.data;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.activeName = "first";
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        //商品id
        goodsId: null,
        //商品名称
        goodsName: '',
        //商品分类
        goodCategoryIds: [],
        //商品图片列表
        goodImageIds: [],
        goodImages: [],
        //商品编码
        goodsNo: '',
        //运费模板ID
        deliveryId: null,
        //商品状态
        status: 10,
        //商品排序
        sort: 100,
        //规格/库存
        specType: 10,
        //商品价格
        goodsPrice: null,
        //划线价
        linePrice: null,
        //当前库存数量
        stockNum: 0,
        //商品重量
        goodsWeight: 0,
        //库存计算方式
        deductStockType: 10,
        //商品详情
        content: null,
        //商品卖点
        sellingPoint: null,
        //服务与承诺
        goodsServiceIds: [],
        //初始销量
        salesInitial: 0,
        //积分赠送
        isPointsGift: 1,
        //积分赠送
        isPointsDiscount: 1,
        //会员折扣
        isEnableGrade: 1,
        //会员折扣设置
        isAloneGrade: 0,
      };
      this.activeName = "first";
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
      this.ids = selection.map(item => item.goodsId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getTreeselect();
      this.open = true;
      this.title = "添加商品记录";
      this.formImages = [];
      this.clickType = "add";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      const goodsId = row.goodsId || this.ids
      getGoods(goodsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品记录";
        this.formImages = response.data.formImages
        this.clickType = "update";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid,obj) => {
        if (valid) {

          // 验证多规格
          if (this.form.specType === 20) {
            const MultiSpec = this.$refs.MultiSpec
            if (!MultiSpec.verifyForm()) {
              this.$modal.msgError("多规格校验失败！");
              return false
            }
            // 记录多规格数据
            this.form.specData = MultiSpec.getFromSpecData()
          }
          console.log(JSON.stringify(this.form))
          if (this.form.goodsId != null) {
            updateGoods(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGoods(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        } else {
          console.log(JSON.stringify(obj))
          let a = [];
          for (let key in obj) {
            a.push(obj[key][0].message);
          }
          this.$modal.msgWarning(a[0])
          return false;
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const goodsIds = row.goodsId || this.ids;
      this.$modal.confirm('是否确认删除商品记录编号为"' + goodsIds + '"的数据项？').then(function () {
        return delGoods(goodsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('store/goods/export', {
        ...this.queryParams
      }, `goods_${new Date().getTime()}.xlsx`)
    },
    /** 转换商品分类数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.categoryId,
        label: node.name,
        children: node.children
      };
    },
    /** 查询商品分类下拉树结构 */
    getTreeselect() {
      listCategory().then(response => {
        this.categoryOptions = [];
        const data = {categoryId: 0, name: '顶级节点', children: [], isDisabled: true};
        data.children = this.handleTree(response.data, "categoryId", "parentId");
        this.categoryOptions.push(data);
      });
    },
  }
};
</script>
