<template>
  <div class="app-container">
  <span>
    <div class="work-content">
      <Components @handleClickItem="onAddItem"></Components>
      <Phone
        v-if="!isLoading"
        :data="data"
        :selectedIndex="selectedIndex"
        @onEditer="onEditer"
        @onDeleleItem="onDeleleItem"
      />
      <!-- 编辑器 -->
        <Editor
          v-if="!isLoading"
          :defaultData="defaultData"
          :data="data"
          :selectedIndex="selectedIndex"
          :curItem="curItem"
        />
    </div>
    <!-- 操作栏 -->
      <div class="footer">
        <div class="footer-content">
          <el-button type="primary" @click="onFormSubmit">保存</el-button>
        </div>
      </div>
  </span>
  </div>
</template>

<script>
import _ from 'lodash'
import Components from "@/views/store/page/modules/components/Components";
import Phone from "@/views/store/page/modules/phone/Phone";
import Editor from "@/views/store/page/modules/editor/Editor";
import {inArray} from '@/utils/util'

const PageData = {
  "page": {
    "name": "页面设置",
    "type": "page",
    "params": {
      "name": "页面名称",
      "title": "页面标题",
      "shareTitle": "分享标题"
    },
    "style": {
      "titleTextColor": "black",
      "titleBackgroundColor": "#ffffff"
    }
  },
  "items": {
    "search": {
      "name": "搜索框",
      "type": "search",
      "params": {
        "placeholder": "请输入关键字进行搜索"
      },
      "style": {
        "textAlign": "left",
        "searchStyle": "square"
      }
    },
    "banner": {
      "name": "图片轮播",
      "type": "banner",
      "style": {
        "btnColor": "#ffffff",
        "btnShape": "round",
        "interval": 2.5
      },
      "data": [
        {
          "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/banner/01.png",
          "link": null
        },
        {
          "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/banner/01.png",
          "link": null
        }
      ]
    },
    "image": {
      "name": "图片",
      "type": "image",
      "style": {
        "paddingTop": 0,
        "paddingLeft": 0,
        "background": "#ffffff"
      },
      "data": [
        {
          "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/banner/01.png",
          "imgName": "image-1.jpg",
          "link": null
        }
      ]
    },
    "navBar": {
      "name": "导航组",
      "type": "navBar",
      "style": {
        "rowsNum": 4,
        "background": "#ffffff",
        "paddingTop": 0,
        "textColor": "#666666"
      },
      "data": [
        {
          "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/navbar/01.png",
          "imgName": "icon-1.png",
          "link": null,
          "text": "按钮文字1"
        },
        {
          "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/navbar/01.png",
          "imgName": "icon-2.jpg",
          "link": null,
          "text": "按钮文字2"
        },
        {
          "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/navbar/01.png",
          "imgName": "icon-3.jpg",
          "link": null,
          "text": "按钮文字3"
        },
        {
          "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/navbar/01.png",
          "imgName": "icon-4.jpg",
          "link": null,
          "text": "按钮文字4"
        }
      ]
    },
    "blank": {
      "name": "辅助空白",
      "type": "blank",
      "style": {
        "height": 20,
        "background": "#ffffff"
      }
    },
    "guide": {
      "name": "辅助线",
      "type": "guide",
      "style": {
        "background": "#ffffff",
        "lineStyle": "solid",
        "lineHeight": 1,
        "lineColor": "#000000",
        "paddingTop": 10
      }
    },
    "video": {
      "name": "视频组",
      "type": "video",
      "params": {
        "videoUrl": "http://wxsnsdy.tc.qq.com/105/20210/snsdyvideodownload?filekey=30280201010421301f0201690402534804102ca905ce620b1241b726bc41dcff44e00204012882540400.mp4",
        "poster": "https://shop2.yiovo.com/assets/store/img/diy/video_poster.png",
        "autoplay": 0
      },
      "style": {
        "paddingTop": 0,
        "height": 190
      }
    },
    "article": {
      "name": "文章组",
      "type": "article",
      "params": {
        "source": "auto",
        "auto": {
          "category": -1,
          "showNum": 6
        }
      },
      "defaultData": [
        {
          "title": "此处显示文章标题",
          "show_type": 10,
          "image": "https://shop2.yiovo.com/assets/store/img/diy/article/01.png",
          "views_num": 309
        },
        {
          "title": "此处显示文章标题",
          "show_type": 10,
          "image": "https://shop2.yiovo.com/assets/store/img/diy/article/01.png",
          "views_num": 309
        }
      ],
      "data": []
    },
    "notice": {
      "name": "店铺公告",
      "type": "notice",
      "params": {
        "text": "这里是第一条自定义公告的标题",
        "link": null,
        "showIcon": true,
        "scrollable": true
      },
      "style": {
        "paddingTop": 0,
        "background": "#fffbe8",
        "textColor": "#de8c17"
      }
    },
    "richText": {
      "name": "富文本",
      "type": "richText",
      "params": {
        "content": "<p>这里是文本的内容</p>"
      },
      "style": {
        "paddingTop": 0,
        "paddingLeft": 0,
        "background": "#ffffff"
      }
    },
    "window": {
      "name": "图片橱窗",
      "type": "window",
      "style": {
        "paddingTop": 0,
        "paddingLeft": 0,
        "background": "#ffffff",
        "layout": 2
      },
      "data": [
        {
          "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/window/01.jpg",
          "link": null
        },
        {
          "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/window/02.jpg",
          "link": null
        },
        {
          "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/window/03.jpg",
          "link": null
        },
        {
          "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/window/04.jpg",
          "link": null
        }
      ],
      "dataNum": 4
    },
    "goods": {
      "name": "商品组",
      "type": "goods",
      "params": {
        "source": "auto",
        "auto": {
          "category": 0,
          "goodsSort": "all",
          "showNum": 6
        }
      },
      "style": {
        "background": "#F6F6F6",
        "display": "list",
        "column": 2,
        "show": [
          "goodsName",
          "goodsPrice",
          "linePrice",
          "sellingPoint",
          "goodsSales"
        ]
      },
      "defaultData": [
        {
          "goods_name": "此处显示商品名称",
          "goods_image": "https://shop2.yiovo.com/assets/store/img/diy/goods/01.png",
          "goods_price_min": "99.00",
          "line_price_min": "139.00",
          "selling_point": "此款商品美观大方 不容错过",
          "goods_sales": 100
        },
        {
          "goods_name": "此处显示商品名称",
          "goods_image": "https://shop2.yiovo.com/assets/store/img/diy/goods/01.png",
          "goods_price_min": "99.00",
          "line_price_min": "139.00",
          "selling_point": "此款商品美观大方 不容错过",
          "goods_sales": 100
        },
        {
          "goods_name": "此处显示商品名称",
          "goods_image": "https://shop2.yiovo.com/assets/store/img/diy/goods/01.png",
          "goods_price_min": "99.00",
          "line_price_min": "139.00",
          "selling_point": "此款商品美观大方 不容错过",
          "goods_sales": 100
        },
        {
          "goods_name": "此处显示商品名称",
          "goods_image": "https://shop2.yiovo.com/assets/store/img/diy/goods/01.png",
          "goods_price_min": "99.00",
          "line_price_min": "139.00",
          "selling_point": "此款商品美观大方 不容错过",
          "goods_sales": 100
        }
      ],
      "data": [
        {
          "goods_name": "此处显示商品名称",
          "goods_image": "https://shop2.yiovo.com/assets/store/img/diy/goods/01.png",
          "goods_price_min": "99.00",
          "line_price_min": "139.00",
          "selling_point": "此款商品美观大方 不容错过",
          "goods_sales": 100,
          "is_default": true
        },
        {
          "goods_name": "此处显示商品名称",
          "goods_image": "https://shop2.yiovo.com/assets/store/img/diy/goods/01.png",
          "goods_price_min": "99.00",
          "line_price_min": "139.00",
          "selling_point": "此款商品美观大方 不容错过",
          "goods_sales": 100,
          "is_default": true
        }
      ]
    },
    "service": {
      "name": "在线客服",
      "type": "service",
      "params": {
        "type": "chat",
        "image": "https://shop2.yiovo.com/assets/store/img/diy/service.png",
        "tel": ""
      },
      "style": {
        "right": 1,
        "bottom": 10,
        "opacity": 100
      }
    }
  }
}

export default {
  name: "PageCreate",
  components: {Components, Phone, Editor},
  data() {
    return {
      // loading状态
      isLoading: false,
      // 页面装修默认数据
      defaultData: {},
      // 当前页面数据
      data: {page: {}, items: []},
      // 当前选中的元素索引
      selectedIndex: 'page',
      // 当前选中的元素
      curItem: {},
      PageData
    }
  },
  // 初始化数据
  created () {
    // 获取初始化数据
    this.initData()
  },
  methods: {
    // 获取初始化数据
    initData () {
      this.isLoading = true
      Promise.all([
        // 获取默认数据
        this.getDefaultData()
      ]).then(() => {
        // 生成默认的data
        this.createNewData()
        this.isLoading = false
      })
    },
    // 生成默认的data
    createNewData () {
      const { defaultData, data } = this
      data.page = defaultData.page
      data.items = []
    },

    // 获取默认数据
    getDefaultData () {
      // return new Promise((resolve, reject) => {
      //   Api.defaultData()
      //     .then(result => {
      //       this.defaultData = result.data
      //       resolve()
      //     })
      // })
      this.defaultData = {
        "page": {
          "name": "页面设置",
          "type": "page",
          "params": {
            "name": "页面名称",
            "title": "页面标题",
            "shareTitle": "分享标题"
          },
          "style": {
            "titleTextColor": "black",
            "titleBackgroundColor": "#ffffff"
          }
        },
        "items": {
          "search": {
            "name": "搜索框",
            "type": "search",
            "params": {
              "placeholder": "请输入关键字进行搜索"
            },
            "style": {
              "textAlign": "left",
              "searchStyle": "square"
            }
          },
          "banner": {
            "name": "图片轮播",
            "type": "banner",
            "style": {
              "btnColor": "#ffffff",
              "btnShape": "round",
              "interval": 2.5
            },
            "data": [
              {
                "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/banner/01.png",
                "link": null
              },
              {
                "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/banner/01.png",
                "link": null
              }
            ]
          },
          "image": {
            "name": "图片",
            "type": "image",
            "style": {
              "paddingTop": 0,
              "paddingLeft": 0,
              "background": "#ffffff"
            },
            "data": [
              {
                "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/banner/01.png",
                "imgName": "image-1.jpg",
                "link": null
              }
            ]
          },
          "navBar": {
            "name": "导航组",
            "type": "navBar",
            "style": {
              "rowsNum": 4,
              "background": "#ffffff",
              "paddingTop": 0,
              "textColor": "#666666"
            },
            "data": [
              {
                "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/navbar/01.png",
                "imgName": "icon-1.png",
                "link": null,
                "text": "按钮文字1"
              },
              {
                "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/navbar/01.png",
                "imgName": "icon-2.jpg",
                "link": null,
                "text": "按钮文字2"
              },
              {
                "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/navbar/01.png",
                "imgName": "icon-3.jpg",
                "link": null,
                "text": "按钮文字3"
              },
              {
                "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/navbar/01.png",
                "imgName": "icon-4.jpg",
                "link": null,
                "text": "按钮文字4"
              }
            ]
          },
          "blank": {
            "name": "辅助空白",
            "type": "blank",
            "style": {
              "height": 20,
              "background": "#ffffff"
            }
          },
          "guide": {
            "name": "辅助线",
            "type": "guide",
            "style": {
              "background": "#ffffff",
              "lineStyle": "solid",
              "lineHeight": 1,
              "lineColor": "#000000",
              "paddingTop": 10
            }
          },
          "video": {
            "name": "视频组",
            "type": "video",
            "params": {
              "videoUrl": "http://wxsnsdy.tc.qq.com/105/20210/snsdyvideodownload?filekey=30280201010421301f0201690402534804102ca905ce620b1241b726bc41dcff44e00204012882540400.mp4",
              "poster": "https://shop2.yiovo.com/assets/store/img/diy/video_poster.png",
              "autoplay": 0
            },
            "style": {
              "paddingTop": 0,
              "height": 190
            }
          },
          "article": {
            "name": "文章组",
            "type": "article",
            "params": {
              "source": "auto",
              "auto": {
                "category": -1,
                "showNum": 6
              }
            },
            "defaultData": [
              {
                "title": "此处显示文章标题",
                "show_type": 10,
                "image": "https://shop2.yiovo.com/assets/store/img/diy/article/01.png",
                "views_num": 309
              },
              {
                "title": "此处显示文章标题",
                "show_type": 10,
                "image": "https://shop2.yiovo.com/assets/store/img/diy/article/01.png",
                "views_num": 309
              }
            ],
            "data": []
          },
          "notice": {
            "name": "店铺公告",
            "type": "notice",
            "params": {
              "text": "这里是第一条自定义公告的标题",
              "link": null,
              "showIcon": true,
              "scrollable": true
            },
            "style": {
              "paddingTop": 0,
              "background": "#fffbe8",
              "textColor": "#de8c17"
            }
          },
          "richText": {
            "name": "富文本",
            "type": "richText",
            "params": {
              "content": "<p>这里是文本的内容</p>"
            },
            "style": {
              "paddingTop": 0,
              "paddingLeft": 0,
              "background": "#ffffff"
            }
          },
          "window": {
            "name": "图片橱窗",
            "type": "window",
            "style": {
              "paddingTop": 0,
              "paddingLeft": 0,
              "background": "#ffffff",
              "layout": 2
            },
            "data": [
              {
                "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/window/01.jpg",
                "link": null
              },
              {
                "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/window/02.jpg",
                "link": null
              },
              {
                "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/window/03.jpg",
                "link": null
              },
              {
                "imgUrl": "https://shop2.yiovo.com/assets/store/img/diy/window/04.jpg",
                "link": null
              }
            ],
            "dataNum": 4
          },
          "goods": {
            "name": "商品组",
            "type": "goods",
            "params": {
              "source": "auto",
              "auto": {
                "category": 0,
                "goodsSort": "all",
                "showNum": 6
              }
            },
            "style": {
              "background": "#F6F6F6",
              "display": "list",
              "column": 2,
              "show": [
                "goodsName",
                "goodsPrice",
                "linePrice",
                "sellingPoint",
                "goodsSales"
              ]
            },
            "defaultData": [
              {
                "goods_name": "此处显示商品名称",
                "goods_image": "https://shop2.yiovo.com/assets/store/img/diy/goods/01.png",
                "goods_price_min": "99.00",
                "line_price_min": "139.00",
                "selling_point": "此款商品美观大方 不容错过",
                "goods_sales": 100
              },
              {
                "goods_name": "此处显示商品名称",
                "goods_image": "https://shop2.yiovo.com/assets/store/img/diy/goods/01.png",
                "goods_price_min": "99.00",
                "line_price_min": "139.00",
                "selling_point": "此款商品美观大方 不容错过",
                "goods_sales": 100
              },
              {
                "goods_name": "此处显示商品名称",
                "goods_image": "https://shop2.yiovo.com/assets/store/img/diy/goods/01.png",
                "goods_price_min": "99.00",
                "line_price_min": "139.00",
                "selling_point": "此款商品美观大方 不容错过",
                "goods_sales": 100
              },
              {
                "goods_name": "此处显示商品名称",
                "goods_image": "https://shop2.yiovo.com/assets/store/img/diy/goods/01.png",
                "goods_price_min": "99.00",
                "line_price_min": "139.00",
                "selling_point": "此款商品美观大方 不容错过",
                "goods_sales": 100
              }
            ],
            "data": [
              {
                "goods_name": "此处显示商品名称",
                "goods_image": "https://shop2.yiovo.com/assets/store/img/diy/goods/01.png",
                "goods_price_min": "99.00",
                "line_price_min": "139.00",
                "selling_point": "此款商品美观大方 不容错过",
                "goods_sales": 100,
                "is_default": true
              },
              {
                "goods_name": "此处显示商品名称",
                "goods_image": "https://shop2.yiovo.com/assets/store/img/diy/goods/01.png",
                "goods_price_min": "99.00",
                "line_price_min": "139.00",
                "selling_point": "此款商品美观大方 不容错过",
                "goods_sales": 100,
                "is_default": true
              }
            ]
          },
          "service": {
            "name": "在线客服",
            "type": "service",
            "params": {
              "type": "chat",
              "image": "https://shop2.yiovo.com/assets/store/img/diy/service.png",
              "tel": ""
            },
            "style": {
              "right": 1,
              "bottom": 10,
              "opacity": 100
            }
          }
        }
      }

    },
    /**
     * 新增Diy组件
     * @param type
     */
    onAddItem (type) {
      // 验证新增Diy组件
      if (!this.onCheckAddItem(type)) {
        return false
      }
      const { defaultData, data } = this
      // 复制默认diy组件数据
      const newItem = _.cloneDeep(defaultData.items[type])
      // 新增到diy列表数据
      data.items.push(newItem)
      // 编辑当前选中的元素
      this.onEditer(data.items.length - 1)
    },
    /**
     * 验证新增Diy组件
     * @param type
     */
    onCheckAddItem (type) {
      const { data } = this
      // 验证xx组件只能存在一个
      if (type === 'xxx') {
        const itemsTypes = data.items.map(item => item.type)
        if (inArray(type, itemsTypes)) {
          this.$message.warning('该组件最多存在一个')
          return false
        }
      }
      return true
    },
    /**
     * 编辑当前选中的Diy元素
     * @param index
     */
    onEditer (index) {
      const { data } = this
      // 记录当前选中元素的索引
      this.selectedIndex = index
      // 当前选中的元素数据
      this.curItem = index === 'page' ? data.page
        : data.items[index]
    },
    /**
     * 删除diy元素
     * @param index
     */
    onDeleleItem (index) {
      const { data: { items } } = this
      items.splice(index, 1)
      this.selectedIndex = -1
    },
    // 提交到后端api
    onFormSubmit () {
      this.isLoading = true

    }
  }
}
</script>

<style scoped>
/*页面设计工作区*/
.work-content {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
}

/*!*底部操作栏*!*/
/*.footer {*/
/*  position: fixed;*/
/*  left: 210px;*/
/*  bottom: 0;*/
/*  right: 15px;*/
/*  border-top-left-radius: 10px;*/
/*  border-top-right-radius: 10px;*/
/*  background: #fff;*/
/*  z-index: 1001;*/
/*  box-shadow: 0 0 4px 0 rgba(200, 200, 200, 0.5);*/
/*  padding: 15px 10px;*/
/*}*/

.footer-content {
  display: flex;
  justify-content: center;
}

/*button {*/
/*  height: 30px;*/
/*  font-size: 13px;*/
/*  margin-right: 15px;*/
/*}*/
</style>
