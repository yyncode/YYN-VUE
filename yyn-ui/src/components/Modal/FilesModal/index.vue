<template>
  <el-dialog
    :title="title"
    :visible.sync="isLoading"
    :close-on-click-modal="false"
    width="900px"
    append-to-body>
    <div class="library-box">
      <!-- 分组列表 -->
      <div class="file-group">
        <div class="group-tree">
          <el-tree
            :data="treeData"
            :props="treeProps"
            ref="tree"
            node-key="groupId"
            default-expand-all
            highlight-current
            @node-click="handleTreeNodeClick">
          </el-tree>
        </div>
      </div>
      <!-- 文件列表 -->
      <div class="file-list">
        <!-- 头部操作栏 -->
        <div class="top-operate">
          <el-input style="width: 200px" v-model="queryParam.fileName" placeholder="搜索文件名称">
            <el-button slot="append" icon="el-icon-search" @click="searchFiles"></el-button>
          </el-input>
        </div>
        <div class="file-list-body">
          <!-- 文件列表 -->
          <div v-if="fileList && fileList.length" class="file-list-ul">
            <div class="file-item" :class="{ active: selectedIndexs.indexOf(index) > -1 }"
                 v-for="(item, index) in fileList" :key="index" @click="onSelectItem(item,index)">
              <div
                class="img-cover"
                :style="{ backgroundImage: `url('${item.cover}')`, width: fileType === FileTypeEnum.VIDEO.value ? '55px' : '95px' }"
              ></div>
              <p class="file-name oneline-hide">{{ item.fileName }}</p>
              <div class="select-mask">
                <!--                <a-icon class="selected-icon" type="check"/>-->
<!--                <svg-icon icon-class="select" class="selected-icon"/>-->
                <i class="el-icon-check selected-icon" ></i>
              </div>
            </div>
          </div>
          <!-- 无数据时显示 -->
          <el-empty v-else-if="fileList.length===0" description="该分组下暂无图片">

          </el-empty>
          <!-- 底部操作栏 -->
          <div class="footer-operate">
            <pagination
              v-show="total>0"
              :total="total"
              :page.sync="queryParam.pageNum"
              :limit.sync="queryParam.pageSize"
              @pagination="getFileList"
            />
          </div>
        </div>
      </div>
    </div>

    <div slot="footer" class="dialog-footer">
      <el-button @click="handleCancel">取 消</el-button>
      <el-button type="primary" @click="handleSubmit">确 定</el-button>
    </div>
  </el-dialog>
</template>

<script>

import PropTypes from "vue-types";
import FileTypeEnum from '@/common/enum/file/FileType'
import {treeGroup} from "@/api/content/files/group";
import {listFile} from "@/api/content/files/file";

export default {
  name: "FilesModal",
  props: {
    // 多选模式, 如果false为单选
    multiple: PropTypes.bool.def(false),
    // 最大选择的数量限制, multiple模式下有效
    maxNum: PropTypes.integer.def(100),
    // 已选择的数量
    selectedNum: PropTypes.integer.def(0),
    // 文件类型 (10图片 30视频)
    fileType: PropTypes.integer.def(FileTypeEnum.IMAGE.value),
  },
  created() {
    this.getTreeList();
    this.getFileList();
  },
  data() {
    return {
      title: "图片库",
      isLoading: false,
      formLabelWidth: '120px',
      form: {},
      treeData: [],
      treeProps: {
        children: 'children',
        label: 'name',
        id: 'groupId'
      },
      // 总条数
      total: 0,
      queryParam: {
        groupId: null,
        fileName: '',
        pageNum: 1,
        pageSize: 10
      },
      // 枚举类
      FileTypeEnum,
      // 上传文件大小限制
      uploadSizeLimit: 2,
      fileList: [],
      // 选中的文件
      selectedIndexs: [],
    }
  },
  methods: {
    // 显示对话框
    show() {
      // 显示窗口
      console.log(this.queryParam)
      // this.$refs.tree.setCurrentKey();
      this.isLoading = true
      // this.resetChecked()
    },
    getTreeList() {
      treeGroup().then(response => {
        this.treeData = response.data;
      });
    },
    handleTreeNodeClick(data) {
      this.queryParam.groupId = data.groupId;
      this.getFileList();
    },
    getFileList() {
      this.loading = true;
      listFile(this.queryParam).then(response => {
        this.fileList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    searchFiles() {
      this.getFileList()
    },
    // 记录选中的文件
    onSelectItem(item, index) {
      // console.log("选中的图片是：" + JSON.stringify(item) + ";索引是：" + index);
      const {multiple, maxNum, selectedIndexs} = this
      // 记录选中状态
      if (!multiple) {
        this.selectedIndexs = [index]
        return
      }
      const key = selectedIndexs.indexOf(index)
      const selected = key > -1
      // 验证数量限制
      if (!selected && (selectedIndexs.length + this.selectedNum) >= maxNum) {
        this.$message.warning(`最多可选${maxNum}个文件`)
        return
      }
      !selected ? this.selectedIndexs.push(index) : this.selectedIndexs.splice(key, 1)
    },
    // 获取选中的文件
    getSelectedItems () {
      const selectedItems = []
      for (const key in this.selectedIndexs) {
        const index = this.selectedIndexs[key]
        selectedItems.push(this.fileList[index])
      }
      return selectedItems
    },
    // 确认按钮
    handleSubmit (e) {
      e.preventDefault()
      // 获取选中的文件
      const selectedItems = this.getSelectedItems()
      // 通知父端组件提交完成了
      this.$emit('handleSubmit', selectedItems)
      // 关闭对话框
      this.handleCancel()
    },
    resetParam() {
      this.queryParam = {
        groupId: null,
        fileName: '',
        pageNum: 1,
        pageSize: 10
      }
    },
    // 关闭对话框事件
    handleCancel () {
      this.isLoading = false;
      this.$refs.tree.setCurrentKey(null);
      this.resetParam();
      this.getFileList();
      this.selectedIndexs = [];

    },
  }
}
</script>

<style scoped>
/* 文件库 */
.library-box {
  user-select: none;
  display: flex;
}

.library-box .file-group {
  /*float: left;*/
  border-right: 1px solid #e6e6e6;
}

.library-box .file-group .group-tree {
  width: 150px;
  height: 540px;
  overflow-y: auto;
  overflow-x: auto;
}

/*.library-box .file-group .group-tree /deep/ .ant-tree {*/
/*  display: inline-block;*/
/*  min-width: 100%;*/
/*  max-height: 380px;*/
/*  width: auto;*/
/*}*/
.library-box .file-group .group-add {
  display: block;
  margin-top: 34px;
  font-size: 13px;
  padding: 0 30px;
}

.library-box .file-list {
  /*float: left;*/
  width: 630px;
  margin-left: 20px;
}

.library-box .file-list .top-operate {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.library-box .file-list .top-operate .file-upload {
  display: flex;
  align-items: center;
}

.library-box .file-list .top-operate .file-upload .upload-desc {
  font-size: 12px;
  padding-right: 10px;
  color: #999;
}

.library-box .file-list .file-list-body {
  height: 455px;
  /*display: flex;*/
  /*flex-flow: row nowrap;*/
}

.library-box .file-list .file-list-body .file-list-ul {
  margin: 0;
  padding: 0;
  /*height: 417px;*/
  list-style: none;
  display: flex;
  flex-flow: row wrap;
}

.library-box .file-list .file-list-body .file-item {
  width: 110px;
  position: relative;
  cursor: pointer;
  border-radius: 2px;
  padding: 4px;
  border: 1px solid rgba(0, 0, 0, 0.05);
  /*float: left;*/
  margin: 4px;
  -webkit-transition: All 0.2s ease-in-out;
  -moz-transition: All 0.2s ease-in-out;
  -o-transition: All 0.2s ease-in-out;
  transition: All 0.2s ease-in-out;
  /*list-style: none;*/
}

.library-box .file-list .file-list-body .file-item:hover {
  border: 1px solid #16bce2;
}

.library-box .file-list .file-list-body .file-item .file-name {
  font-size: 12px;
  text-align: center;
}

.library-box .file-list .file-list-body .file-item .img-cover {
  margin: 0 auto;
  width: 95px;
  height: 95px;
  background: no-repeat center center / 100%;
}

.library-box .file-list .file-list-body .file-item.active .select-mask {
  display: block;
}

.oneline-hide {
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
  word-break: break-all;
}

.library-box .file-list .file-list-body .file-item .select-mask {
  display: none;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.41);
  text-align: center;
  border-radius: 2px;
}

.library-box .file-list .file-list-body .file-item .select-mask .selected-icon {
  font-size: 26px;
  color: #fff;
  line-height: 122px;
  /*text-align: center;*/
}

.library-box .file-list .file-list-body .footer-operate {
  height: 25px;
  margin-top: 10px;
  /*display: flex;*/
  /*justify-content: space-between;*/
}

.library-box .file-list .file-list-body .footer-operate .footer-desc {
  color: #999;
  margin-right: 10px;
}

</style>
