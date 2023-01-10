<template>
  <div class="image-list clearfix" :class="{ multiple }">
    <!-- 文件列表 -->
    <!-- draggable是拖拽组件 -->
    <draggable
      v-if="selectedItems.length"
      v-model="selectedItems"
      @start="drag=true"
      @end="drag=false"
      @update="onUpdate"
    >
      <transition-group class="draggable-item" type="transition" :name="'flip-list'">
        <div
          v-for="(item, index) in selectedItems"
          :key="item.fileId"
          class="file-item"
          :style="{ width: `${width}px`, height: `${width}px` }"
        >
          <!-- 预览图 -->
          <a :href="item.cover" target="_blank">
            <div class="img-cover" :style="{ backgroundImage: `url('${item.cover}')` }"></div>
          </a>
          <svg-icon class="icon-close" icon-class="bug" @click="handleDeleteFileItem(index)"/>
        </div>
      </transition-group>
    </draggable>
    <!-- 图片选择器 -->
    <!-- 如果单选, selectedItems无内容时 显示 -->
    <!-- 如果多选, selectedItems数量小于 maxNum 显示 -->
    <div
      v-show="(!multiple && selectedItems.length <= 0) || (multiple && selectedItems.length < maxNum)"
      class="selector"
      :style="{width: `${width}px`, height: `${width}px`}"
      title="点击选择图片"
      @click="handleSelectImage"
    >
      <i class="el-icon-plus"></i>
    </div>
    <!--     文件选择器-->
    <files-modal
      ref="FilesModal"
      :multiple="multiple"
      :maxNum="maxNum"
      :selectedNum="selectedItems.length"
      @handleSubmit="handleSelectImageSubmit"
    />
  </div>
</template>

<script>
import draggable from 'vuedraggable'
import PropTypes from 'vue-types'
import FilesModal from "@/components/Modal/FilesModal";
import cloneDeep from 'lodash.clonedeep'

export default {
  name: "SelectImage",
  components: {
    draggable, FilesModal
  },
  props: {
    // 多选模式, 如果false为单选
    multiple: PropTypes.bool.def(false),
    // 最大选择的数量限制, multiple模式下有效
    maxNum: PropTypes.integer.def(200),
    // 默认选中的文件
    defaultList: PropTypes.array.def([]),
    defaultClickType: PropTypes.string.def(''),
    isEdit: PropTypes.bool.def(false),
    // 元素的尺寸(宽)
    width: PropTypes.integer.def(200)
  },
  model: {
    prop: 'value',
    event: 'change'
  },
  watch: {
    // 设置默认数据
    defaultList: {
      // 首次加载的时候执行函数
      immediate: true,
      handler(val) {
        const {selectedItems, allowProps, defaultClickType} = this
        if (!val.length) {
          this.selectedItems = []
        }
        if (val.length && selectedItems.length && val[0].fileId !== selectedItems[0].fileId && defaultClickType === 'update') {
          this.selectedItems =cloneDeep(val)
        }
        if (val.length && !selectedItems.length && allowProps) {
          this.selectedItems = cloneDeep(val)
          this.onChange()
        }
      }
    }
  },
  data() {
    return {
      selectedItems: [],
      // 禁止传参 (防止selectedItems为空时defaultList重新赋值)
      allowProps: true,
      drag: false,
      isEditData: false
    }
  },
  methods: {
    // 拖动排序后的回调事件
    onUpdate() {
      this.onChange()
    },

    // 打开文件选择器
    handleSelectImage() {
      this.$refs.FilesModal.show()
    },

    // 文件库modal确认回调
    handleSelectImageSubmit(result) {

      if (result.length > 0) {
        // 记录选中的图片列表
        const {multiple, selectedItems} = this
        this.selectedItems = multiple ? selectedItems.concat(result) : result
        this.onChange()
      }
    },

    // 删除文件
    handleDeleteFileItem(index) {
      this.selectedItems.splice(index, 1);
    },

    // 触发change事件
    onChange() {
      const {multiple, selectedItems} = this
      if (selectedItems.length <= 0) {
        // return;
        return this.$emit('change', multiple ? [] : 0)
      }
      // 生成fileId
      const fileId = multiple ? selectedItems.map(item => item.fileId) : selectedItems[0].fileId
      // 触发change事件
      return this.$emit('change', fileId, selectedItems)
    }

  }
}
</script>

<style scoped>
/deep/ .flip-list-move {
  transition: transform 0.3s !important;
}

/deep/ .no-move {
  transition: transform 0s;
}

.image-list.multiple .file-item,
.image-list.multiple .selector {
  margin-right: 10px;
  margin-bottom: 10px;
}

.file-item {
  float: left;
  width: 80px;
  height: 80px;
  position: relative;
  padding: 2px;
  border: 1px solid #ddd;
  background: #fff;
}

.file-item .img-cover {
  display: block;
  width: 100%;
  height: 100%;
  background: no-repeat center center / 100%;
}

.file-item:hover .icon-close {
  display: block;
}

.file-item .icon-close {
  display: none;
  position: absolute;
  top: -8px;
  right: -8px;
  cursor: pointer;
  font-size: 16px;
  color: #c5c5c5;
}

.file-item .icon-close:hover {
  color: #7d7d7d;
}

.file-item:hover {
  border: 1px solid #a7c3de;
}

.selector {
  width: 150px;
  height: 150px;
  float: left;
  border: 1px dashed #5b5858;
  text-align: center;
  color: #dad9d9;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
}

.selector:hover {
  border: 1px dashed #40a9ff;
  color: #40a9ff;
}

.selector .icon-plus {
  font-size: 32px;
}

</style>
