<template>
  <div class="app-container">
    <div class="phone-content">
      <!-- 顶部导航栏 -->
      <div
        class="phone-top optional"
        :class="{ selected: 'page' === selectedIndex, [data.page.style.titleTextColor]: true }"
        :style="{ backgroundColor: data.page.style.titleBackgroundColor }"
        @click="handelClickItem('page')"
      >
        <p
          class="title"
          :style="{ color: data.page.style.titleTextColor }"
        >{{ data.page.params.title }}</p>
      </div>
      <!-- 内容区域 -->
      <div class="phone-main">
        <draggable
          :list="data.items"
          class="content"
          @update="handelDragItem"
          v-bind="{ animation: 120, filter: '.undrag' }"
        >
          <!-- 内容元素 -->
          <div
            class="devise-item optional"
            v-for="(item, index) in data.items"
            :key="index"
            @click="handelClickItem(index)"
            :class="{ selected: index === selectedIndex, undrag: inArray(item.type, undragList) }"
            :style="renderItemStyle(item)"
          >
            <!-- 图片轮播 -->
            <div v-if="item.type == 'banner'" class="diy-banner">
              <img
                v-for="(dataItem, dataIdx) in item.data"
                :key="`${index}_${dataIdx}_img`"
                v-show="dataIdx <= 1"
                :src="dataItem.imgUrl"
              />
              <div class="dots" :class="item.style.btnShape">
                <div
                  v-for="(dataItem, dataIdx) in item.data"
                  :key="`${index}_${dataIdx}_dots`"
                  :style="{ background: item.style.btnColor }"
                  class="dots-item"
                ></div>
              </div>
            </div>

            <!-- 图片组 -->
            <div
              v-else-if="item.type == 'image'"
              class="diy-image"
              :style="{ paddingBottom: item.style.paddingTop + 'px', background: item.style.background}"
            >
              <div
                class="item-image"
                v-for="(dataItm, dataIdx) in item.data"
                :key="`${index}_${dataIdx}`"
                :style="{ padding: `${item.style.paddingTop}px ${item.style.paddingLeft}px 0` }"
              >
                <img :src="dataItm.imgUrl" />
              </div>
            </div>

            <!-- 图片橱窗 -->
            <div
              v-else-if="item.type == 'window'"
              class="diy-window"
              :style="{ background: item.style.background, padding: `${item.style.paddingTop}px ${item.style.paddingLeft}px` }"
            >
              <ul
                v-if="item.style.layout > -1"
                class="data-list clearfix"
                :class="`avg-sm-${item.style.layout}`"
              >
                <li
                  v-for="(window, dataIdx) in item.data"
                  :key="`${index}_${dataIdx}`"
                  class="data-item"
                  :style="{ padding: `${item.style.paddingTop}px ${item.style.paddingLeft}px` }"
                >
                  <div class="item-image">
                    <img :src="window.imgUrl" />
                  </div>
                </li>
              </ul>
              <div class="display" v-else>
                <div
                  class="display-left"
                  :style="{ padding: `${item.style.paddingTop}px ${item.style.paddingLeft}px` }"
                >
                  <img :src="item.data[0].imgUrl" />
                </div>
                <div class="display-right">
                  <div
                    v-if="item.data.length >= 2"
                    class="display-right1"
                    :style="{ padding: `${item.style.paddingTop}px ${item.style.paddingLeft}px` }"
                  >
                    <img :src="item.data[1].imgUrl" />
                  </div>
                  <div class="display-right2">
                    <div
                      v-if="item.data.length >= 3"
                      class="left"
                      :style="{ padding: `${item.style.paddingTop}px ${item.style.paddingLeft}px` }"
                    >
                      <img :src="item.data[2].imgUrl" />
                    </div>
                    <div
                      v-if="item.data.length >= 4"
                      class="right"
                      :style="{ padding: `${item.style.paddingTop}px ${item.style.paddingLeft}px` }"
                    >
                      <img :src="item.data[3].imgUrl" />
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- 视频组 -->
            <div
              v-else-if="item.type == 'video'"
              class="diy-video"
              :style="{ padding: `${item.style.paddingTop}px 0` }"
            >
              <video
                :style="{ height: `${item.style.height}px` }"
                :src="item.params.videoUrl"
                :poster="item.params.poster"
                controls
              >您的浏览器不支持 video 标签</video>
            </div>

            <!-- 文章组 -->
            <div v-else-if="item.type == 'article'" class="diy-article">
              <div class="diy-article">
                <div
                  class="article-item"
                  v-for="(dataItm, dataIdx) in (item.params.source == 'choice' ? item.data : item.defaultData)"
                  :key="`${index}_${dataIdx}`"
                  :class="`show-type__${dataItm.show_type}`"
                >
                  <!-- 小图模式 -->
                  <template v-if="dataItm.show_type == 10">
                    <div class="article-item__left flex-box">
                      <div class="article-item__title twolist-hidden">
                        <span class="article-title">{{ dataItm.title }}</span>
                      </div>
                      <div class="article-item__footer">
                        <span class="article-views">{{ dataItm.views_num }}次浏览</span>
                      </div>
                    </div>
                    <div class="article-item__image">
                      <img :src="dataItm.image" alt />
                    </div>
                  </template>
                  <!-- 大图模式 -->
                  <template v-if="dataItm.show_type == 20">
                    <div class="article-item__title">
                      <span class="article-title">{{ dataItm.title }}</span>
                    </div>
                    <div class="article-item__image">
                      <img :src="dataItm.image" />
                    </div>
                    <div class="article-item__footer">
                      <span class="article-views">{{ dataItm.views_num }}次浏览</span>
                    </div>
                  </template>
                </div>
              </div>
            </div>

            <!-- 搜索栏 -->
            <div v-else-if="item.type == 'search'" class="diy-search">
              <div class="inner" :class="item.style.searchStyle">
                <div class="search-input" :style="{ textAlign: item.style.textAlign }">
                  <svg-icon class="search-icon" icon-class="search"/>
                  <span>{{ item.params.placeholder }}</span>
                </div>
              </div>
            </div>

            <!-- 店铺公告 -->
            <div
              v-else-if="item.type == 'notice'"
              class="diy-notice"
              :style="{ padding: `${item.style.paddingTop}px 0` }"
            >
              <div
                class="notice-body"
                :style="{ background: item.style.background, color: item.style.textColor }"
              >
                <div class="notice__icon">
                  <svg-icon class="notice-icon" icon-class="volumeFill"/>
                </div>
                <div class="notice__text flex-box oneline-hide">
                  <span>{{ item.params.text }}</span>
                </div>
              </div>
            </div>

            <!-- 导航组 -->
            <div
              v-else-if="item.type == 'navBar'"
              class="diy-navBar"
              :style="{ padding: `${item.style.paddingTop}px 0`, background: item.style.background, color: item.style.textColor }"
            >
              <ul class="data-list clearfix" :class="`avg-sm-${item.style.rowsNum}`">
                <li
                  class="item-nav"
                  v-for="(dataItm, dataIdx) in item.data"
                  :key="`${index}_${dataIdx}`"
                >
                  <div class="item-image">
                    <img :src="dataItm.imgUrl" />
                  </div>
                  <p class="item-text oneline-hide">{{ dataItm.text }}</p>
                </li>
              </ul>
            </div>

            <!-- 商品组 -->
            <div
              v-else-if="item.type == 'goods'"
              class="diy-goods"
              :style="{ background: item.style.background }"
            >
              <ul
                class="goods-list clearfix"
                :class="['display__' + item.style.display, 'column__' + item.style.column]"
              >
                <li
                  class="goods-item"
                  v-for="(dataItm, dataIdx) in (item.params.source == 'choice' ? item.data : item.defaultData)"
                  :key="`${index}_${dataIdx}`"
                >
                  <!-- 单列商品 -->
                  <template v-if="item.style.column == 1">
                    <div class="flex">
                      <!-- 商品图片 -->
                      <div class="goods-item_left">
                        <img :src="dataItm.goods_image" />
                      </div>
                      <div class="goods-item_right">
                        <!-- 商品名称 -->
                        <div
                          v-if="item.style.show.includes('goodsName')"
                          class="goods-item_title twolist-hidden"
                        >
                          <span>{{ dataItm.goods_name }}</span>
                        </div>
                        <div class="goods-item_desc">
                          <!-- 商品卖点 -->
                          <div
                            v-if="item.style.show.includes('sellingPoint')"
                            class="desc-selling_point oneline-hide"
                          >
                            <span>{{ dataItm.selling_point }}</span>
                          </div>
                          <!-- 商品销量 -->
                          <div
                            v-if="item.style.show.includes('goodsSales')"
                            class="desc-goods_sales oneline-hide"
                          >
                            <span>已售{{ dataItm.goods_sales }}件</span>
                          </div>
                          <!-- 商品价格 -->
                          <div class="desc_footer">
                          <span v-if="item.style.show.includes('goodsPrice')" class="price_x">
                            <span class="small-unit">¥</span>
                            <span>{{ dataItm.goods_price_min }}</span>
                          </span>
                            <span
                              class="price_y"
                              v-if="item.style.show.includes('linePrice') && dataItm.line_price_min > 0"
                            >¥{{ dataItm.line_price_min }}</span>
                          </div>
                        </div>
                      </div>
                    </div>
                  </template>
                  <!-- 两列三列 -->
                  <template v-else>
                    <div class="goods-image">
                      <img :src="dataItm.goods_image" />
                    </div>
                    <div class="detail">
                      <p
                        v-if="item.style.show.includes('goodsName')"
                        class="goods-name twolist-hidden"
                      >{{ dataItm.goods_name }}</p>
                      <p class="detail-price">
                      <span v-if="item.style.show.includes('goodsPrice')" class="goods-price">
                        <span class="small-unit">¥</span>
                        <span>{{ dataItm.goods_price_min }}</span>
                      </span>
                        <span
                          v-if="item.style.show.includes('linePrice') && dataItm.line_price_min > 0"
                          class="line-price"
                        >
                        <span class="small-unit">¥</span>
                        <span>{{ dataItm.line_price_min }}</span>
                      </span>
                      </p>
                    </div>
                  </template>
                </li>
              </ul>
            </div>

            <!-- 辅助空白 -->
            <div
              v-else-if="item.type == 'blank'"
              class="diy-blank"
              :style="{ height: `${item.style.height}px` , background: item.style.background }"
            ></div>

            <!-- 辅助线 -->
            <div
              v-else-if="item.type == 'guide'"
              class="diy-guide"
              :style="{ padding: `${item.style.paddingTop}px 0`, background: item.style.background }"
            >
              <p
                class="line"
                :style="{
                borderTopWidth: item.style.lineHeight + 'px',
                borderTopColor: item.style.lineColor,
                borderTopStyle: item.style.lineStyle
              }"
              ></p>
            </div>

            <!-- 在线客服 -->
            <div
              v-else-if="item.type == 'service'"
              class="diy-service"
              :style="{ opacity: item.style.opacity / 100 }"
            >
              <div class="service-icon">
                <img class="image" :src="item.params.image" alt />
              </div>
            </div>

            <!-- 富文本 -->
            <div
              v-else-if="item.type == 'richText'"
              class="diy-richText"
              :style="{ background: item.style.background, padding: `${item.style.paddingTop}px ${item.style.paddingLeft}px` }"
              v-html="item.params.content"
            ></div>

            <!-- 删除操作 -->
            <div class="btn-edit-del">
              <div class="btn-del" @click="handleDeleleItem(index)">删除</div>
            </div>
          </div>
        </draggable>
      </div>
    </div>

  </div>
</template>

<script>
import PropTypes from 'vue-types'
import {inArray} from '@/utils/util'
import draggable from 'vuedraggable'
// 禁止拖拽的组件
const undragList = ['service']


export default {
  name: "Phone",
  props: {
    // 页面数据
    data: PropTypes.object.def({}),
    // 当前选中的元素索引
    selectedIndex: PropTypes.oneOfType([PropTypes.number, PropTypes.string]).def(0)
  },
  components: {
    draggable
  },
  data () {
    return {
      // 禁止拖拽的组件
      undragList
    }
  },
  beforeCreate () {
    this.inArray = inArray
  },
  methods: {

    /**
     * 拖动diy元素更新当前索引
     * @param e
     */
    handelDragItem (e) {
      this.$emit('onEditer', e.newIndex)
    },

    /**
     * 点击当前选中的Diy元素
     * @param index
     */
    handelClickItem (index) {
      this.$emit('onEditer', index)
    },

    /**
     * 删除当前选中的Diy元素
     * @param index
     */
    handleDeleleItem (index) {
      this.$emit('onDeleleItem', index)
    },

    // 渲染组件外层容器的样式
    renderItemStyle (item) {
      if (item.type === 'service') {
        return {
          position: 'absolute',
          right: item.style.right + '%',
          bottom: item.style.bottom + '%'
        }
      }
      return {}
    }

  }
}
</script>

<style scoped>
ol,
ul,
dl {
  margin: 0;
  padding: 0;
}
.phone-content {
  width: 377px;
  margin-top: 25px;
  border-radius: 3px;
  box-shadow: 0 0 28px 0 #ccc;
  border: 1px solid #ddd;
  background: #fff;
}
.phone-content .phone-top {
  width: 100%;
  height: 66px;
  text-align: center;
  background-position: center;
  background-size: contain;
  background-repeat: no-repeat;
}
.phone-content .phone-top.black {
  background-image: url('~@/assets/images/phone-top-black.png');
}
.phone-content .phone-top.white {
  background-image: url('~@/assets/images/phone-top-white.png');
}
.phone-content .phone-top .title {
  margin: 0;
  font-size: 14px;
  font-weight: normal;
  white-space: nowrap;
  line-height: 88px;
}
.phone-content .phone-main {
  position: relative;
  border-top: 0;
  user-select: none;
  line-height: normal;
}
.phone-content .phone-main .content {
  overflow-y: auto;
  height: 580px;
}
.phone-content .phone-main .devise-item {
  display: block;
  overflow: hidden;
  font-size: 13px;
}
.phone-content .phone-main .devise-item .btn-edit-del {
  height: 16px;
  position: absolute;
  right: 2px;
  bottom: 2px;
  display: none;
  z-index: 90;
}
.phone-content .phone-main .devise-item .btn-edit-del > div {
  width: 32px;
  height: 16px;
  line-height: 16px;
  display: inline-block;
  text-align: center;
  font-size: 10px;
  color: #fff;
  background: rgba(0, 0, 0, 0.4);
  margin-left: 2px;
  cursor: pointer;
  position: relative;
  z-index: 11;
}
.phone-content .phone-main .devise-item.selected .btn-edit-del,
.phone-content .phone-main .devise-item:hover .btn-edit-del {
  display: block;
}
.phone-content .phone-main a {
  color: #337ab7;
}
.phone-content .phone-main .diy-search {
  background: #f1f1f2;
  padding: 10px 10px;
}
.phone-content .phone-main .diy-search .inner {
  height: 30px;
  background: #fff;
  overflow: hidden;
}
.phone-content .phone-main .diy-search .inner.radius {
  border-radius: 5px;
}
.phone-content .phone-main .diy-search .inner.round {
  border-radius: 30px;
}
.phone-content .phone-main .diy-search .inner .search-input {
  height: 30px;
  line-height: 30px;
  color: #999999;
  padding: 0 10px;
  font-size: 13px;
}
.phone-content .phone-main .diy-search .inner .search-input .search-icon {
  margin-right: 4px;
}
.phone-content .phone-main .diy-banner {
  display: block;
  margin: 0;
  padding: 0;
  height: auto;
  overflow: hidden;
}
.phone-content .phone-main .diy-banner img {
  display: none;
  width: 100%;
}
.phone-content .phone-main .diy-banner img:first-child {
  display: block;
}
.phone-content .phone-main .diy-banner .dots {
  height: 14px;
  padding: 0 10px;
  position: absolute;
  left: 0;
  right: 0;
  bottom: 10px;
  opacity: 0.8;
  display: flex;
  justify-content: center;
}
.phone-content .phone-main .diy-banner .dots-item {
  margin-right: 3px;
  height: 10px;
  width: 10px;
}
.phone-content .phone-main .diy-banner .dots-item:last-child {
  margin-right: 0;
}
.phone-content .phone-main .diy-banner .dots.rectangle .dots-item {
  width: 16px;
}
.phone-content .phone-main .diy-banner .dots.square .dots-item {
  width: 10px;
}
.phone-content .phone-main .diy-banner .dots.round .dots-item {
  height: 10px;
  width: 10px;
  border-radius: 10px;
}
.phone-content .phone-main .diy-image {
  display: block;
  margin: 0;
  padding: 0;
  height: auto;
  overflow: hidden;
}
.phone-content .phone-main .diy-image img {
  display: block;
  width: 100%;
}
.phone-content .phone-main .diy-navBar .item-nav {
  float: left;
  margin: 10px 0;
  text-align: center;
}
.phone-content .phone-main .diy-navBar .item-nav .item-image {
  text-align: center;
  margin-bottom: 4px;
}
.phone-content .phone-main .diy-navBar .item-nav .item-image img {
  height: 44px;
  width: 44px;
}
/* 分列布局 */
.phone-content .phone-main .diy-navBar .avg-sm-3 > .item-nav {
  width: 33.33333333%;
}
.phone-content .phone-main .diy-navBar .avg-sm-4 > .item-nav {
  width: 25%;
}
.phone-content .phone-main .diy-navBar .avg-sm-5 > .item-nav {
  width: 20%;
}
.phone-content .phone-main .diy-guide .line {
  height: 0;
  width: 100%;
}
.phone-content .phone-main .diy-video video {
  display: block;
  width: 100%;
  height: 100%;
}
.phone-content .phone-main .diy-article {
  background: #f7f7f7;
}
.phone-content .phone-main .diy-article .article-item {
  margin-bottom: 10px;
  padding: 15px;
  background: #fff;
}
.phone-content .phone-main .diy-article .article-item:last-child {
  margin-bottom: 0;
}
.phone-content .phone-main .diy-article .article-item .article-title {
  font-size: 13px;
  color: #333;
}
.phone-content .phone-main .diy-article .article-item__image image {
  display: block;
}
.phone-content .phone-main .diy-article .article-item__footer {
  margin-top: 5px;
}
.phone-content .phone-main .diy-article .article-item__footer .article-views {
  font-size: 12px;
  color: #888;
}
.phone-content .phone-main .diy-article .article-item.show-type__10 {
  display: flex;
}
.phone-content .phone-main .diy-article .article-item.show-type__10 .article-item__left {
  padding-right: 10px;
}
.phone-content .phone-main .diy-article .article-item.show-type__10 .article-item__title {
  min-height: 36px;
}
.phone-content .phone-main .diy-article .article-item.show-type__10 .article-item__image img {
  display: block;
  width: 120px;
}
.phone-content .phone-main .diy-article .article-item.show-type__20 .article-item__image {
  margin-top: 12.5px;
}
.phone-content .phone-main .diy-article .article-item.show-type__20 .article-item__image img {
  display: block;
  width: 100%;
}
.phone-content .phone-main .diy-special {
  line-height: normal;
}
.phone-content .phone-main .diy-special .special-left {
  padding: 5px 10px;
}
.phone-content .phone-main .diy-special .special-left img {
  display: block;
  width: 70px;
}
.phone-content .phone-main .diy-special .special-content {
  overflow: hidden;
}
.phone-content .phone-main .diy-special .special-content .content-item {
  padding: 2px 0;
  font-size: 13px;
  color: #141414;
}
.phone-content .phone-main .diy-special .special-content.display_1 {
  height: 22px;
}
.phone-content .phone-main .diy-special .special-content.display_2 {
  height: 44px;
}
.phone-content .phone-main .diy-special .special-more {
  padding: 12px 10px;
  font-size: 12px;
}
.phone-content .phone-main .diy-window .item-image img {
  display: block;
  width: 100%;
}
.phone-content .phone-main .diy-window .data-item {
  float: left;
}
/* 分列布局 */
.phone-content .phone-main .diy-window .avg-sm-2 > .data-item {
  width: 50%;
}
.phone-content .phone-main .diy-window .avg-sm-3 > .data-item {
  width: 33.33333333%;
}
.phone-content .phone-main .diy-window .avg-sm-4 > .data-item {
  width: 25%;
}
.phone-content .phone-main .diy-window .avg-sm-5 > .data-item {
  width: 20%;
}
.phone-content .phone-main .diy-window .display {
  height: 0;
  width: 100%;
  margin: 0;
  padding-bottom: 50%;
  position: relative;
}
.phone-content .phone-main .diy-window .display img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.phone-content .phone-main .diy-window .display .display-left {
  width: 50%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
}
.phone-content .phone-main .diy-window .display .display-right {
  width: 50%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 50%;
}
.phone-content .phone-main .diy-window .display .display-right1 {
  width: 100%;
  height: 50%;
  position: absolute;
  top: 0;
  left: 0;
}
.phone-content .phone-main .diy-window .display .display-right2 {
  width: 100%;
  height: 50%;
  position: absolute;
  top: 50%;
  left: 0;
}
.phone-content .phone-main .diy-window .display .display-right2 .left {
  width: 50%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
}
.phone-content .phone-main .diy-window .display .display-right2 .right {
  width: 50%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 50%;
}
.phone-content .phone-main .diy-goods .goods-list {
  padding: 2px;
}
.phone-content .phone-main .diy-goods .goods-list.display__slide {
  overflow-x: hidden;
  white-space: nowrap;
  font-size: 0;
}
.phone-content .phone-main .diy-goods .goods-list.display__slide::-webkit-scrollbar {
  display: none;
}
.phone-content .phone-main .diy-goods .goods-list.display__slide .goods-item {
  display: inline-block;
}
.phone-content .phone-main .diy-goods .goods-list.display__list .goods-item {
  float: left;
}
.phone-content .phone-main .diy-goods .goods-list.column__1 .goods-item {
  width: 100%;
  height: 140px;
  margin-bottom: 6px;
  padding: 10px;
  box-sizing: border-box;
  background: #fff;
}
.phone-content .phone-main .diy-goods .goods-list.column__1 .goods-item:last-child {
  margin-bottom: 0;
}
/* 商品图片 */
.phone-content .phone-main .diy-goods .goods-list.column__1 .goods-item_left {
  display: flex;
  width: 40%;
  background: #fff;
  align-items: center;
}
.phone-content .phone-main .diy-goods .goods-list.column__1 .goods-item_left img {
  display: block;
  width: 120px;
  height: 120px;
}
.phone-content .phone-main .diy-goods .goods-list.column__1 .goods-item_right {
  position: relative;
  width: 60%;
}
/* 商品名称 */
.phone-content .phone-main .diy-goods .goods-list.column__1 .goods-item_right .goods-item_title {
  height: 32px;
  margin-top: 10px;
  font-size: 13px;
  line-height: 1.3;
  color: #333;
}
.phone-content .phone-main .diy-goods .goods-list.column__1 .goods-item_desc {
  margin-top: 4px;
}
/* 商品卖点 */
.phone-content .phone-main .diy-goods .goods-list.column__1 .desc-selling_point {
  width: 200px;
  height: 20px;
  font-size: 12px;
  color: #ff495e;
}
.phone-content .phone-main .diy-goods .goods-list.column__1 .desc-goods_sales {
  color: #999;
  font-size: 12px;
}
/* 商品价格 */
.phone-content .phone-main .diy-goods .goods-list.column__1 .desc_footer {
  font-size: 12px;
}
.phone-content .phone-main .diy-goods .goods-list.column__1 .desc_footer .price_x {
  margin-right: 8px;
  color: #f03c3c;
  font-size: 15px;
}
.phone-content .phone-main .diy-goods .goods-list.column__1 .desc_footer .price_y {
  color: #999;
  text-decoration: line-through;
}
.phone-content .phone-main .diy-goods .goods-list.column__2 .goods-item {
  width: 50%;
}
.phone-content .phone-main .diy-goods .goods-list.column__3 .goods-item {
  width: 33.33333%;
}
.phone-content .phone-main .diy-goods .goods-list .goods-item {
  padding: 3px;
}
.phone-content .phone-main .diy-goods .goods-list .goods-item .goods-image {
  position: relative;
  width: 100%;
  height: 0;
  padding-bottom: 100%;
  overflow: hidden;
  background: #fff;
}
.phone-content .phone-main .diy-goods .goods-list .goods-item .goods-image:after {
  content: '';
  display: block;
  margin-top: 100%;/* margin 百分比相对父元素宽度计算 */
}
.phone-content .phone-main .diy-goods .goods-list .goods-item .goods-image img {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  -o-object-fit: cover;
  object-fit: cover;
}
.phone-content .phone-main .diy-goods .goods-list .goods-item .detail {
  padding: 4px;
  background: #fff;
  font-size: 13px;
}
.phone-content .phone-main .diy-goods .goods-list .goods-item .detail .goods-name {
  color: #484848;
  height: 32px;
  overflow: hidden;
  font-size: 13px;
  line-height: 1.3;
}
.phone-content .phone-main .diy-goods .goods-list .goods-item .detail .goods-price {
  font-size: 15px;
  color: #f00;
}
.phone-content .phone-main .diy-goods .goods-list .goods-item .detail .line-price {
  margin-left: 5px;
  font-size: 12px;
  text-decoration: line-through;
  color: #999;
}
.phone-content .phone-main .diy-goods .goods-list .goods-item .detail .selling-point {
  font-size: 12px;
  color: #f20c59;
}
.phone-content .phone-main .diy-goods .goods-list .goods-item .small-unit {
  font-size: 13px;
  margin-right: 2px;
}
.phone-content .phone-main .diy-notice {
  font-size: 14px;
}
.phone-content .phone-main .diy-notice .notice-body {
  padding: 6px 12px;
  display: flex;
  align-items: center;
}
.phone-content .phone-main .diy-notice .notice__icon {
  font-size: 17px;
}
.phone-content .phone-main .diy-notice .notice__text {
  padding-left: 6px;
  line-height: 18px;
}
.phone-content .phone-main .diy-richText {
  font-size: 14px;
  line-height: normal;
}
.phone-content .phone-main .diy-richText /deep/ p {
  padding: 0 4px;
}
.phone-content .phone-main .diy-richText /deep/ img {
  display: block;
  width: 375px;
  margin-left: -4px;
}
.phone-content .phone-main .diy-richText /deep/ video {
  width: 100%;
}
.phone-content .phone-main .diy-richText /deep/ hr {
  margin: 5px 0;
}
.phone-content .phone-main .diy-richText * + h1,
.phone-content .phone-main .diy-richText * + h2,
.phone-content .phone-main .diy-richText * + h3,
.phone-content .phone-main .diy-richText * + h4,
.phone-content .phone-main .diy-richText * + h5,
.phone-content .phone-main .diy-richText * + h6 {
  margin: 20px 0;
}
.phone-content .phone-main .diy-service {
  z-index: 999;
}
.phone-content .phone-main .diy-service .service-icon {
  padding: 5px;
}
.phone-content .phone-main .diy-service .service-icon .image {
  display: block;
  width: 45px;
  height: 45px;
  border-radius: 50%;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}
.phone-content .optional {
  position: relative;
}
.phone-content .optional:before {
  content: '';
}
.phone-content .optional.__z10:before {
  z-index: 10;
}
.phone-content .optional.__no-move:before {
  cursor: pointer !important;
}
.phone-content .optional.selected:before,
.phone-content .optional:hover:before {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  border: 2px dashed #00a0e9;
  cursor: move;
}

</style>
