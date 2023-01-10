<template>
  <div class="app-container">
    <div class="components">
      <el-collapse v-model="activeNames" @change="handleChange">
        <el-collapse-item v-for="group in components" :key="group.key" :title="group.name" :name="group.key">
          <div class="module-list">
            <div
              v-for="(item, index) in group.data"
              :key="index"
              class="module-item"
              @click="handleClickItem(item.type)"
            >
              <svg-icon class="module-icon" :icon-class="item.type"/>
              <span class="module-title">{{ item.name }}</span>
            </div>
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>

<script>

const components = [
  {
    name: '媒体组件',
    key: 'media',
    data: [
      {
        name: '轮播图',
        type: 'banner'
      },
      {
        name: '图片',
        type: 'image'
      },
      {
        name: '图片橱窗',
        type: 'window'
      },
      {
        name: '视频',
        type: 'video'
      },
      {
        name: '文章',
        type: 'article'
      }
    ]
  },
  {
    name: '商城组件',
    key: 'store',
    data: [
      {
        name: '搜索框',
        type: 'search'
      },
      {
        name: '店铺公告',
        type: 'notice'
      },
      {
        name: '导航',
        type: 'navBar'
      },
      {
        name: '商品',
        type: 'goods'
      },
      {
        name: '在线客服',
        type: 'service'
      }
    ]
  },
  {
    name: '其他组件',
    key: 'other',
    data: [
      {
        name: '富文本',
        type: 'richText'
      },
      {
        name: '辅助空白',
        type: 'blank'
      },
      {
        name: '辅助线',
        type: 'guide'
      }
    ]
  }
]

export default {
  name: "Components",
  data() {
    return {
      activeNames: ['media','store','other'], components
    };
  },
  methods: {
    handleChange(val) {
      console.log(val);
    },
    handleClickItem (type) {
      this.$emit('handleClickItem', type)
    }
  },
  computed: {
    componentKeys () {
      const { components } = this
      return components.map(item => item.key)
    }
  },
}
</script>

<style scoped>
.components{
  width: 310px;
  background: #f8f9fa;
}
.module-list {
  display: flex;
  flex-wrap: wrap;
}
.module-item {
  width: 90px;
  height: 90px;
  padding: 15px 0;
  text-align: center;
  font-size: 12px;
  color: #595961;
  line-height: 20px;
  position: relative;
  user-select: none;
}
.module-icon {
  display: block;
  width: 74px;
  height: 45px;
  line-height: 45px;
  margin: 0 auto;
  font-size: 26px;
  color: #9797a1;
}
</style>
