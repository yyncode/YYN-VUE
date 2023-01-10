<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文件名称" prop="fileName">
        <el-input
          v-model="queryParams.fileName"
          placeholder="请输入文件名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件分组" prop="groupId" >
<!--        <el-input-->
<!--          v-model="queryParams.groupId"-->
<!--          placeholder="请输入文件分组"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
        <treeselect v-model="queryParams.groupId" :multiple="false" :options="parentGroupData" :show-count="true" :normalizer="normalizer" placeholder="请选择文件分组" style="width: 150px"/>
      </el-form-item>
      <el-form-item label="上传来源" prop="channel">
        <el-select v-model="queryParams.channel" placeholder="请选择上传来源" clearable>
          <el-option
            v-for="dict in dict.type.content_files_upload_channel"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="文件类型" prop="fileType">
        <el-select v-model="queryParams.fileType" placeholder="请选择文件类型" clearable>
          <el-option
            v-for="dict in dict.type.content_files_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['content:files:list:add']"
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
          v-hasPermi="['content:files:list:edit']"
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
          v-hasPermi="['content:files:list:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['content:files:list:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="fileList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="文件ID" align="center" prop="fileId" />
      <el-table-column label="文件预览" align="center" prop="imageId" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.cover" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="文件名称" align="center" prop="fileName" />
      <el-table-column label="上传来源" align="center" prop="channel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.content_files_upload_channel" :value="scope.row.channel"/>
        </template>
      </el-table-column>
      <el-table-column label="存储方式" align="center" prop="storage" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.file_upload_way" :value="scope.row.storage"/>
        </template>
      </el-table-column>
      <el-table-column label="文件类型" align="center" prop="fileType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.content_files_type" :value="scope.row.fileType"/>
        </template>
      </el-table-column>
      <el-table-column label="文件路径" align="center" prop="filePath" />
      <el-table-column label="文件大小(字节)" align="center" prop="fileSize" />
      <el-table-column label="文件扩展名" align="center" prop="fileExt" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['content:files:list:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['content:files:list:remove']"
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

    <!-- 添加或修改文件库记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="上传文件" prop="images" v-if="!form.fileId">
<!--          <image-upload v-model="form.imageMsg" :limit="1"></image-upload>-->
          <div class="component-upload-image">
            <el-upload
              multiple
              :action="uploadImgUrl"
              list-type="picture-card"
              :on-success="handleImageUploadSuccess"
              :before-upload="handleBeforeImageUpload"
              :limit="limit"
              :on-error="handleImageUploadError"
              :on-exceed="handleImageExceed"
              ref="imageUpload"
              :on-remove="handleImageDelete"
              :show-file-list="true"
              :headers="headers"
              :file-list="form.images"
              :on-preview="handlePictureCardPreview"
              :class="{hide: this.form.images.length >= this.limit}"
            >
              <i class="el-icon-plus"></i>
            </el-upload>

<!--            &lt;!&ndash; 上传提示 &ndash;&gt;-->
<!--            <div class="el-upload__tip" slot="tip" v-if="showTip">-->
<!--              请上传-->
<!--              <template v-if="fileSize"> 大小不超过 <b style="color: #f56c6c">{{ fileSize }}MB</b> </template>-->
<!--              <template v-if="fileType"> 格式为 <b style="color: #f56c6c">{{ fileType.join("/") }}</b> </template>-->
<!--              的文件-->
<!--            </div>-->

            <el-dialog
              :visible.sync="dialogPreviewVisible"
              title="预览"
              width="800"
              append-to-body
            >
              <img
                :src="dialogImageUrl"
                style="display: block; max-width: 100%; margin: 0 auto"
              />
            </el-dialog>
          </div>
        </el-form-item>
        <el-form-item label="文件分组" prop="groupId">
          <treeselect v-model="form.groupId" :multiple="false" :options="parentGroupData" :show-count="true" :normalizer="normalizer" placeholder="请选择文件分组"/>
        </el-form-item>
        <el-form-item label="上传来源" prop="channel">
          <el-radio-group v-model="form.channel">
            <el-radio
              v-for="dict in dict.type.content_files_upload_channel"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="文件类型" prop="fileType">
          <el-radio-group v-model="form.fileType">
            <el-radio
              v-for="dict in dict.type.content_files_type"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
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
import {addFile, delFile, getFile, listFile, updateFile} from "@/api/content/files/file";
import {treeGroup} from "@/api/content/files/group";
import {getToken} from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "File",
  components: {
    Treeselect
  },
  dicts: ['content_files_upload_channel', 'content_files_type','file_upload_way'],
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
      // 文件库记录表格数据
      fileList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        groupId: null,
        channel: null,
        fileType: null,
        fileName: null
      },
      // 表单参数
      form: {
        fileId:null,
        images:[],
        groupId: null,
        channel: null,
        fileType: null,
        remark:''
      },
      // 表单校验
      rules: {
        images: [
          { required: true, message: "文件不能为空", trigger: "blur" }
        ],
        groupId: [
          { required: true, message: "文件分组不能为空", trigger: "blur" }
        ],
        channel: [
          { required: true, message: "上传来源不能为空", trigger: "change" }
        ],
        fileType: [
          { required: true, message: "文件类型不能为空", trigger: "change" }
        ]
      },
      imageUrl: '',
      uploadImgUrl: process.env.VUE_APP_BASE_API + "/common/upload/v1", // 上传的图片服务器地址
      limit:5,
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      uploadImageList:[],
      dialogPreviewVisible:false,
      dialogImageUrl:'',
      parentGroupData:[],
    };
  },
  created() {
    this.getTreeList();
    this.getList();
  },
  methods: {
    /** 查询文件库记录列表 */
    getList() {
      this.loading = true;
      listFile(this.queryParams).then(response => {
        this.fileList = response.rows;
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
        images:[],
        groupId: null,
        channel: null,
        fileType: null
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
      this.ids = selection.map(item => item.fileId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加文件库记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fileId = row.fileId || this.ids
      getFile(fileId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改文件库记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fileId != null) {
            updateFile(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFile(this.form).then(response => {
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
      const fileIds = row.fileId || this.ids;
      this.$modal.confirm('是否确认删除文件库记录编号为"' + fileIds + '"的数据项？').then(function() {
        return delFile(fileIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('store/file/export', {
        ...this.queryParams
      }, `file_${new Date().getTime()}.xlsx`)
    },
    // 上传前loading加载
    handleBeforeImageUpload(file) {

    },
    // 文件个数超出
    handleImageExceed() {
      this.$modal.msgError(`上传文件数量不能超过 ${this.limit} 个!`);
    },
    // 上传成功回调
    handleImageUploadSuccess(res, file) {
      if (res.code === 200) {
        this.form.images.push(res.data);
      } else {
        this.form.images = this.form.images.filter(arrItem => arrItem['newFileName']!==file['newFileName']);
        this.$modal.msgError(res.msg);
      }
    },
    // 删除图片
    handleImageDelete(file) {
      console.log("图片链接："+JSON.stringify(file));
      this.form.images = this.form.images.filter(arrItem => arrItem['newFileName']!==file['newFileName']);
      console.log("图片集合images："+JSON.stringify(this.form.images));
    },
    // 上传失败
    handleImageUploadError() {
      this.$modal.msgError('上传失败！');
    },
    // 预览
    handlePictureCardPreview(file) {
      // console.log("图片链接："+JSON.stringify(file));
      // console.log("图片集合uploadImageList："+JSON.stringify(this.uploadImageList));
      this.dialogImageUrl = file.url;
      this.dialogPreviewVisible = true;
    },
    getTreeList() {
      this.loading = true;
      treeGroup().then(response => {
        this.parentGroupData = response.data;
        this.loading = false;
      });
    },
    normalizer(node) {
      return {
        id: node.groupId,
        label: node.name,
        children: node.children,
      }
    },
  }
};
</script>

<style scoped lang="scss">
// .el-upload--picture-card 控制加号部分
::v-deep.hide .el-upload--picture-card {
  display: none;
}
// 去掉动画效果
::v-deep .el-list-enter-active,
::v-deep .el-list-leave-active {
  transition: all 0s;
}

::v-deep .el-list-enter, .el-list-leave-active {
  opacity: 0;
  transform: translateY(0);
}
</style>

