<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="页面名称" prop="pageName">
        <el-input
          v-model="queryParams.pageName"
          placeholder="请输入页面名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商城ID" prop="storeId">
        <el-input
          v-model="queryParams.storeId"
          placeholder="请输入商城ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="软删除" prop="isDelete">
        <el-input
          v-model="queryParams.isDelete"
          placeholder="请输入软删除"
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
          v-hasPermi="['system:page:add']"
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
          v-hasPermi="['system:page:edit']"
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
          v-hasPermi="['system:page:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:page:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="pageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="页面ID" align="center" prop="pageId" />
      <el-table-column label="页面类型(10首页 20自定义页)" align="center" prop="pageType" />
      <el-table-column label="页面名称" align="center" prop="pageName" />
      <el-table-column label="页面数据" align="center" prop="pageData" />
      <el-table-column label="商城ID" align="center" prop="storeId" />
      <el-table-column label="软删除" align="center" prop="isDelete" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:page:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:page:remove']"
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

    <!-- 添加或修改店铺页面记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="页面名称" prop="pageName">
          <el-input v-model="form.pageName" placeholder="请输入页面名称" />
        </el-form-item>
        <el-form-item label="页面数据" prop="pageData">
          <el-input v-model="form.pageData" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="商城ID" prop="storeId">
          <el-input v-model="form.storeId" placeholder="请输入商城ID" />
        </el-form-item>
        <el-form-item label="软删除" prop="isDelete">
          <el-input v-model="form.isDelete" placeholder="请输入软删除" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "Page",
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
      // 店铺页面记录表格数据
      pageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pageType: null,
        pageName: null,
        pageData: null,
        storeId: null,
        isDelete: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        pageType: [
          { required: true, message: "页面类型(10首页 20自定义页)不能为空", trigger: "change" }
        ],
        pageName: [
          { required: true, message: "页面名称不能为空", trigger: "blur" }
        ],
        pageData: [
          { required: true, message: "页面数据不能为空", trigger: "blur" }
        ],
        storeId: [
          { required: true, message: "商城ID不能为空", trigger: "blur" }
        ],
        isDelete: [
          { required: true, message: "软删除不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询店铺页面记录列表 */
    getList() {
      // this.loading = true;
      // listPage(this.queryParams).then(response => {
      //   this.pageList = response.rows;
      //   this.total = response.total;
        this.loading = false;
      // });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        pageId: null,
        pageType: null,
        pageName: null,
        pageData: null,
        storeId: null,
        isDelete: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
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
      this.ids = selection.map(item => item.pageId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.$router.push('/store/store-page/create')
      // this.reset();
      // this.open = true;
      // this.title = "添加店铺页面记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pageId = row.pageId || this.ids
      // getPage(pageId).then(response => {
      //   this.form = response.data;
      //   this.open = true;
      //   this.title = "修改店铺页面记录";
      // });
    },
    /** 提交按钮 */
    submitForm() {
      // this.$refs["form"].validate(valid => {
      //   if (valid) {
      //     if (this.form.pageId != null) {
      //       updatePage(this.form).then(response => {
      //         this.$modal.msgSuccess("修改成功");
      //         this.open = false;
      //         this.getList();
      //       });
      //     } else {
      //       addPage(this.form).then(response => {
      //         this.$modal.msgSuccess("新增成功");
      //         this.open = false;
      //         this.getList();
      //       });
      //     }
      //   }
      // });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      // const pageIds = row.pageId || this.ids;
      // this.$modal.confirm('是否确认删除店铺页面记录编号为"' + pageIds + '"的数据项？').then(function() {
      //   return delPage(pageIds);
      // }).then(() => {
      //   this.getList();
      //   this.$modal.msgSuccess("删除成功");
      // }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/page/export', {
        ...this.queryParams
      }, `page_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
