<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px" size="small">
      <el-form-item label="新闻标题" prop="newsTitle">
        <el-input
          v-model="queryParams.newsTitle"
          clearable
          placeholder="请输入新闻标题"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="显示状态" prop="status">
        <el-select v-model="queryParams.status" clearable placeholder="请选择显示状态">
          <el-option
            v-for="dict in dict.type.news_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="导航分类" prop="isTop">
        <el-select v-model="queryParams.newsNavId" clearable placeholder="请选择导航分类">
          <el-option
            v-for="item in newsNavList"
            :key="item.navId"
            :label="item.navName"
            :value="item.navId"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否置顶" prop="isTop">
        <el-select v-model="queryParams.isTop" clearable placeholder="请选择是否置顶">
          <el-option
            v-for="dict in dict.type.news_is_top"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否审核" prop="auditStatus">
        <el-select v-model="queryParams.auditStatus" clearable placeholder="请选择是否审核">
          <el-option
            v-for="dict in dict.type.news_audit_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" size="mini" type="primary" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['news:content:add']"
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          @click="handleAdd"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['news:content:edit']"
          :disabled="single"
          icon="el-icon-edit"
          plain
          size="mini"
          type="success"
          @click="handleUpdate"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['news:content:remove']"
          :disabled="multiple"
          icon="el-icon-delete"
          plain
          size="mini"
          type="danger"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['news:content:export']"
          icon="el-icon-download"
          plain
          size="mini"
          type="warning"
          @click="handleExport"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="contentList" @selection-change="handleSelectionChange">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="新闻标题" prop="newsTitle"/>
      <el-table-column :show-overflow-tooltip="true" align="center" label="新闻描述" prop="newsDescription"/>
      <el-table-column align="center" label="新闻缩略图" prop="newsPic" width="100">
        <template slot-scope="scope">
          <image-preview :height="50" :src="scope.row.newsPic" :width="50"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="导航分类" prop="newsNavName"/>
      <el-table-column align="center" label="显示状态" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.news_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="是否置顶" prop="isTop">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.news_is_top" :value="scope.row.isTop"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="是否审核" prop="auditStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.news_audit_status" :value="scope.row.auditStatus"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="审核原因" prop="auditResult"/>
      <el-table-column align="center" label="浏览量" prop="lookNum"/>
      <el-table-column align="center" label="发布人" prop="createBy"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['news:content:edit']"
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            v-hasPermi="['news:content:audit']"
            icon="el-icon-top-right"
            size="mini"
            type="text"
            @click="handleAudit(scope.row)"
          >审核
          </el-button>
          <el-button
            v-hasPermi="['news:content:remove']"
            icon="el-icon-delete"
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :limit.sync="queryParams.pageSize"
      :page.sync="queryParams.pageNum"
      :total="total"
      @pagination="getList"
    />

    <!-- 添加或修改新闻内容对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="新闻标题" prop="newsTitle">
          <el-input v-model="form.newsTitle" placeholder="请输入新闻标题"/>
        </el-form-item>
        <el-form-item label="新闻描述" prop="newsDescription">
          <el-input v-model="form.newsDescription" placeholder="请输入新闻描述"/>
        </el-form-item>
        <el-form-item label="新闻缩略图" prop="newsPic">
          <image-upload v-model="form.newsPic"/>
        </el-form-item>
        <el-form-item label="新闻内容">
          <editor v-model="form.newsContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="导航分类">
          <el-select v-model="form.newsNavId" placeholder="请选择分类">
            <el-option
              v-for="item in newsNavOptions"
              :key="item.navId"
              :label="item.navName"
              :value="item.navId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="显示状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.news_status"
              :key="dict.value"
              :label="dict.value"
            >{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否置顶" prop="isTop">
          <el-radio-group v-model="form.isTop">
            <el-radio
              v-for="dict in dict.type.news_is_top"
              :key="dict.value"
              :label="dict.value"
            >{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <!--        <el-form-item label="是否审核" prop="auditStatus" v-hasPermi="['news:content:audit']">-->
        <!--          <el-radio-group v-model="form.auditStatus">-->
        <!--            <el-radio-->
        <!--              v-for="dict in dict.type.news_audit_status"-->
        <!--              :key="dict.value"-->
        <!--              :label="dict.value"-->
        <!--            >{{dict.label}}</el-radio>-->
        <!--          </el-radio-group>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="审核原因" prop="auditResult" v-hasPermi="['news:content:audit']">-->
        <!--          <el-input v-model="form.auditResult" type="textarea" placeholder="请输入内容" />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="浏览量" prop="lookNum">-->
        <!--          <el-input v-model="form.lookNum" placeholder="请输入浏览量" />-->
        <!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 审核新闻内容对话框 -->
    <el-dialog :title="title" :visible.sync="openAudit" append-to-body width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="是否审核" prop="auditStatus">
          <el-radio-group v-model="form.auditStatus">
            <el-radio
              v-for="dict in dict.type.news_audit_status"
              :key="dict.value"
              :label="dict.value"
            >{{ dict.label }}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审核原因" prop="auditResult">
          <el-input v-model="form.auditResult" placeholder="请输入内容" type="textarea"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitAuditForm">确 定</el-button>
        <el-button @click="cancelAudit">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {addContent, delContent, getContent, listContent, updateContent} from "@/api/news/content";
import {listNavNotPage} from "@/api/news/nav"

export default {
  name: "Content",
  dicts: ['news_is_top', 'news_status', 'news_audit_status'],
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
      // 新闻内容表格数据
      contentList: [],
      // 新闻导航列表
      newsNavList: [],
      newsNavOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示审核弹出层
      openAudit: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        newsTitle: null,
        newsDescription: null,
        newsPic: null,
        newsContent: null,
        newsNavId: null,
        status: null,
        isTop: null,
        auditStatus: null,
        auditResult: null,
        lookNum: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        newsTitle: [
          {required: true, message: "新闻标题不能为空", trigger: "blur"}
        ],
        newsContent: [
          {required: true, message: "新闻内容不能为空", trigger: "blur"}
        ],
        newsNavId: [
          {required: true, message: "导航不能为空", trigger: "change"}
        ],
        status: [
          {required: true, message: "显示状态不能为空", trigger: "change"}
        ],
        isTop: [
          {required: true, message: "是否置顶不能为空", trigger: "change"}
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getSearchNewsNavList();
  },
  methods: {
    /** 查询新闻内容列表 */
    getList() {
      this.loading = true;
      listContent(this.queryParams).then(response => {
        this.contentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getSearchNewsNavList() {
      listNavNotPage().then(response => {
        this.newsNavList = response.data;
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        newsId: null,
        newsTitle: null,
        newsDescription: null,
        newsPic: null,
        newsContent: null,
        newsNavId: null,
        newsNavName: null,
        status: null,
        isTop: null,
        auditStatus: null,
        auditResult: null,
        lookNum: null,
        createBy: null,
        createTime: null,
        updateBy: null,
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
      this.ids = selection.map(item => item.newsId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getContent().then(response => {
        this.newsNavOptions = response.newsNavs;
        this.open = true;
        this.title = "添加新闻内容";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const newsId = row.newsId || this.ids
      getContent(newsId).then(response => {
        this.newsNavOptions = response.newsNavs;
        this.$set(this.form, "newsNavId", response.newsNavId);
        this.form = response.data;
        this.open = true;
        this.title = "修改新闻内容";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.newsId != null) {
            updateContent(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addContent(this.form).then(response => {
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
      const newsIds = row.newsId || this.ids;
      this.$modal.confirm('是否确认删除新闻内容编号为"' + newsIds + '"的数据项？').then(function () {
        return delContent(newsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('news/content/export', {
        ...this.queryParams
      }, `content_${new Date().getTime()}.xlsx`)
    },
    /** 审核按钮操作 */
    handleAudit(row) {
      this.reset();
      const newsId = row.newsId || this.ids
      getContent(newsId).then(response => {
        this.form = response.data;
        this.openAudit = true;
        this.title = "修改新闻内容";
      });
    },
    /** 提交审核按钮 */
    submitAuditForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.newsId != null) {
            updateContent(this.form).then(response => {
              this.$modal.msgSuccess("审核成功");
              this.openAudit = false;
              this.getList();
            });
          }
        }
      });
    },
    // 取消按钮
    cancelAudit() {
      this.openAudit = false;
      this.reset();
    },
  }
};
</script>
