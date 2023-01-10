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
        >新增</el-button>
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
        >修改</el-button>
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
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['store:goods:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="goodsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品ID" align="center" prop="goodsId" />
      <el-table-column label="商品图片" align="center" prop="goodsName">
        <template slot-scope="scope">
          <image-preview :src="scope.row.images[0].cover" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="商品名称" align="center" prop="goodsName" />
      <el-table-column label="商品价格" align="center" prop="goodsPriceMin" />
      <el-table-column label="库存总量" align="center" prop="stockTotal" />
      <el-table-column label="初始销量" align="center" prop="salesInitial" />
      <el-table-column label="实际销量" align="center" prop="salesActual" />
      <el-table-column label="商品状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.goods_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['store:goods:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['store:goods:remove']"
          >删除</el-button>
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
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-tabs v-model="activeName">
          <el-tab-pane label="基本信息" name="first">
            <el-form-item label="商品名称" prop="goodsName">
              <el-input v-model="form.goodsName" placeholder="请输入商品名称" />
            </el-form-item>
            <el-form-item label="商品分类" required>
              <treeselect :multiple="true" :flat="true" v-model="goodCategories" :options="categoryOptions" :normalizer="normalizer" placeholder="请选择商品分类" />
            </el-form-item>
            <el-form-item label="商品图片" required>
              <select-image :defaultList="formImages" :defaultClickType="clickType"
                            v-model="form.imageId" :multiple="true" :maxNum="10" :width="50" />
              <!--          <el-input v-model="form.imageId" placeholder="请输入分类图片" />-->
            </el-form-item>
            <el-form-item label="商品编码" prop="goodsNo">
              <el-input v-model="form.goodsNo" placeholder="请输入商品编码" />
            </el-form-item>
            <el-form-item label="商品状态" prop="status">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in dict.type.goods_status"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="商品排序" prop="sort">
              <el-input-number v-model="form.sort" placeholder="请输入商品排序" />
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="规格/库存" name="second">
            <el-form-item label="规格类型" prop="specType">
              <el-radio-group v-model="form.specType">
                <el-radio
                  v-for="dict in dict.type.goods_spec_type"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="商品价格(最低)" prop="goodsPriceMin">
              <el-input v-model="form.goodsPriceMin" placeholder="请输入商品价格(最低)" />
            </el-form-item>
            <el-form-item label="商品价格(最高)" prop="goodsPriceMax">
              <el-input v-model="form.goodsPriceMax" placeholder="请输入商品价格(最高)" />
            </el-form-item>
            <el-form-item label="划线价格(最低)" prop="linePriceMin">
              <el-input v-model="form.linePriceMin" placeholder="请输入划线价格(最低)" />
            </el-form-item>
            <el-form-item label="划线价格(最高)" prop="linePriceMax">
              <el-input v-model="form.linePriceMax" placeholder="请输入划线价格(最高)" />
            </el-form-item>
            <el-form-item label="库存总量(包含所有sku)" prop="stockTotal">
              <el-input v-model="form.stockTotal" placeholder="请输入库存总量(包含所有sku)" />
            </el-form-item>
            <el-form-item label="库存计算方式(10下单减库存 20付款减库存)" prop="deductStockType">
              <el-radio-group v-model="form.deductStockType">
                <el-radio
                  v-for="dict in dict.type.goods_deduct_stock_type"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="商品详情" name="third">
            <el-form-item label="商品详情">
              <editor v-model="form.content" :min-height="192"/>
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="更多设置" name="fourth"><el-form-item label="商品卖点" prop="sellingPoint">
            <el-input v-model="form.sellingPoint" type="textarea" placeholder="请输入内容" />
          </el-form-item>
            <el-form-item label="初始销量" prop="salesInitial">
              <el-input v-model="form.salesInitial" placeholder="请输入初始销量" />
            </el-form-item>
            <el-form-item label="实际销量" prop="salesActual">
              <el-input v-model="form.salesActual" placeholder="请输入实际销量" />
            </el-form-item>
            <el-form-item label="配送模板ID" prop="deliveryId">
              <el-input v-model="form.deliveryId" placeholder="请输入配送模板ID" />
            </el-form-item>
            <el-form-item label="是否开启积分赠送" prop="isPointsGift">
              <el-radio-group v-model="form.isPointsGift">
                <el-radio
                  v-for="dict in dict.type.goods_is_points_gift"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="是否允许使用积分抵扣" prop="isPointsDiscount">
              <el-radio-group v-model="form.isPointsDiscount">
                <el-radio
                  v-for="dict in dict.type.goods_is_points_discount"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="积分抵扣设置" prop="isAlonePointsDiscount">
              <el-radio-group v-model="form.isAlonePointsDiscount">
                <el-radio
                  v-for="dict in dict.type.goods_is_alone_points_discount"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="单独设置积分抵扣的配置" prop="pointsDiscountConfig">
              <el-input v-model="form.pointsDiscountConfig" type="textarea" placeholder="请输入内容" />
            </el-form-item>
            <el-form-item label="是否开启会员折扣" prop="isEnableGrade">
              <el-radio-group v-model="form.isEnableGrade">
                <el-radio
                  v-for="dict in dict.type.goods_is_enable_grade"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="会员折扣设置" prop="isAloneGrade">
              <el-radio-group v-model="form.isAloneGrade">
                <el-radio
                  v-for="dict in dict.type.goods_is_alone_grade"
                  :key="dict.value"
                  :label="parseInt(dict.value)"
                >{{dict.label}}</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="单独设置折扣的配置" prop="aloneGradeEquity">
              <el-input v-model="form.aloneGradeEquity" type="textarea" placeholder="请输入内容" />
            </el-form-item>
            <el-form-item label="是否删除" prop="isDelete">
              <el-input v-model="form.isDelete" placeholder="请输入是否删除" />
            </el-form-item>
            <el-form-item label="商城ID" prop="storeId">
              <el-input v-model="form.storeId" placeholder="请输入商城ID" />
            </el-form-item></el-tab-pane>
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
import {listCategory} from "@/api/goods/category";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import SelectImage from "@/components/SelectImage";

export default {
  name: "Goods",
  components: {
    Treeselect,SelectImage
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
        goodsNo: null,
        sellingPoint: null,
        specType: null,
        goodsPriceMin: null,
        goodsPriceMax: null,
        linePriceMin: null,
        linePriceMax: null,
        stockTotal: null,
        deductStockType: null,
        content: null,
        salesInitial: null,
        salesActual: null,
        deliveryId: null,
        isPointsGift: null,
        isPointsDiscount: null,
        isAlonePointsDiscount: null,
        pointsDiscountConfig: null,
        isEnableGrade: null,
        isAloneGrade: null,
        aloneGradeEquity: null,
        status: null,
        sort: null,
        isDelete: null,
        storeId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        goodsName: [
          { required: true, message: "商品名称不能为空", trigger: "blur" }
        ],
        goodsNo: [
          { required: true, message: "商品编码不能为空", trigger: "blur" }
        ],
        sellingPoint: [
          { required: true, message: "商品卖点不能为空", trigger: "blur" }
        ],
        specType: [
          { required: true, message: "商品规格不能为空", trigger: "change" }
        ],
        goodsPriceMin: [
          { required: true, message: "商品价格(最低)不能为空", trigger: "blur" }
        ],
        goodsPriceMax: [
          { required: true, message: "商品价格(最高)不能为空", trigger: "blur" }
        ],
        stockTotal: [
          { required: true, message: "库存总量(包含所有sku)不能为空", trigger: "blur" }
        ],
        deductStockType: [
          { required: true, message: "库存计算方式(10下单减库存 20付款减库存)不能为空", trigger: "change" }
        ],
        content: [
          { required: true, message: "商品详情不能为空", trigger: "blur" }
        ],
        salesInitial: [
          { required: true, message: "初始销量不能为空", trigger: "blur" }
        ],
        salesActual: [
          { required: true, message: "实际销量不能为空", trigger: "blur" }
        ],
        deliveryId: [
          { required: true, message: "配送模板ID不能为空", trigger: "blur" }
        ],
        isPointsGift: [
          { required: true, message: "是否开启积分赠送不能为空", trigger: "change" }
        ],
        isPointsDiscount: [
          { required: true, message: "是否允许使用积分抵扣不能为空", trigger: "change" }
        ],
        isAlonePointsDiscount: [
          { required: true, message: "积分抵扣设置不能为空", trigger: "change" }
        ],
        pointsDiscountConfig: [
          { required: true, message: "单独设置积分抵扣的配置不能为空", trigger: "blur" }
        ],
        isEnableGrade: [
          { required: true, message: "是否开启会员折扣不能为空", trigger: "change" }
        ],
        isAloneGrade: [
          { required: true, message: "会员折扣设置不能为空", trigger: "change" }
        ],
        status: [
          { required: true, message: "商品状态不能为空", trigger: "change" }
        ],
        sort: [
          { required: true, message: "排序(数字越小越靠前)不能为空", trigger: "blur" }
        ],
        isDelete: [
          { required: true, message: "是否删除不能为空", trigger: "blur" }
        ],
        storeId: [
          { required: true, message: "商城ID不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ]
      },
      activeName: 'first',
      // 商品分类树选项
      categoryOptions: [],
      // 商品分类选择
      goodCategories:[],
      // 商品图片
      formImages:[],
      clickType: null
    };
  },
  created() {
    this.getList();
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
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        goodsId: null,
        goodsName: null,
        goodsNo: null,
        sellingPoint: null,
        specType: null,
        goodsPriceMin: null,
        goodsPriceMax: null,
        linePriceMin: null,
        linePriceMax: null,
        stockTotal: null,
        deductStockType: null,
        content: null,
        salesInitial: null,
        salesActual: null,
        deliveryId: null,
        isPointsGift: null,
        isPointsDiscount: null,
        isAlonePointsDiscount: null,
        pointsDiscountConfig: null,
        isEnableGrade: null,
        isAloneGrade: null,
        aloneGradeEquity: null,
        status: 10,
        sort: 100,
        isDelete: null,
        storeId: null,
        createTime: null,
        updateTime: null
      };
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
      this.single = selection.length!==1
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
      this.$refs["form"].validate(valid => {
        if (valid) {
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
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const goodsIds = row.goodsId || this.ids;
      this.$modal.confirm('是否确认删除商品记录编号为"' + goodsIds + '"的数据项？').then(function() {
        return delGoods(goodsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
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
        const data = { categoryId: 0, name: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "categoryId", "parentId");
        this.categoryOptions.push(data);
      });
    },
  }
};
</script>
