<template>
  <div class="app-container">
    <div class="editor">
      <!-- 标题 -->
      <div class="editor-title">
        <span>{{ selectedIndex === 'page' ? data.page.name : curItem.name }}</span>
      </div>
      <!-- 编辑器: 标题栏 -->
      <div v-if="selectedIndex === 'page'" class="editor-content">
        <el-tabs :stretch="true" :value="'first'">
          <el-tab-pane label="页面设置" name="first">
            <div class="block-box">
              <div class="block-title">基本信息</div>
              <div class="block-item">
                <span class="label">页面名称</span>
                <div class="flex-box">
                  <el-input v-model="data.page.params.name" />
                  <div class="tips">页面名称仅用于后台管理</div>
                </div>
              </div>
              <div class="block-item">
                <span class="label">分享标题</span>
                <div class="flex-box">
                  <el-input v-model="data.page.params.shareTitle" />
                  <div class="tips">用户端转发时显示的标题</div>
                </div>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="标题栏设置" name="second">
            <div class="block-box">
              <div class="block-title">标题栏设置</div>
              <div class="block-item">
                <span class="label">标题名称</span>
                <div class="flex-box">
                  <el-input v-model="data.page.params.title" />
                  <div class="tips">用户端端顶部显示的标题</div>
                </div>
              </div>
              <div class="block-item">
                <span class="label">文字颜色</span>
                <el-radio-group v-model="data.page.style.titleTextColor">
                  <el-radio-button label="white" >白色</el-radio-button>
                  <el-radio-button label="black">黑色</el-radio-button>
                </el-radio-group>
              </div>
              <div class="block-item">
                <span class="label">标题栏背景</span>
                <div class="item-colorPicker">
                <span
                  class="rest-color"
                  @click="onEditorResetColor(data.page.style, 'titleBackgroundColor', '#fff')"
                >重置</span>
                  <el-color-picker v-model="data.page.style.titleBackgroundColor" :predefine="['#fff']" />
                </div>
              </div>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>

      <template v-if="data.items.length && curItem">
        <!-- 搜索栏 -->
        <div v-if="curItem.type === 'search'" class="editor-content">
          <el-tabs>
            <el-tab-pane key="1" tab="内容设置">
              <div class="block-box">
                <div class="block-title">功能设置</div>
                <div class="block-item">
                  <span class="label">提示文字</span>
                  <el-input v-model="curItem.params.placeholder" />
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane key="2" tab="样式设置">
              <div class="block-box">
                <div class="block-title">内容样式</div>
                <div class="block-item">
                  <span class="label">搜索框样式</span>
                  <el-radio-group buttonStyle="solid" v-model="curItem.style.searchStyle">
                    <el-radio-button value="square">方形</el-radio-button>
                    <el-radio-button value="radius">圆角</el-radio-button>
                    <el-radio-button value="round">圆弧</el-radio-button>
                  </el-radio-group>
                </div>
                <div class="block-item">
                  <span class="label">文字对齐</span>
                  <el-radio-group buttonStyle="solid" v-model="curItem.style.textAlign">
                    <el-radio-button value="left">居左</el-radio-button>
                    <el-radio-button value="center">居中</el-radio-button>
                    <el-radio-button value="right">居右</el-radio-button>
                  </el-radio-group>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>

        <!-- 辅助空白 -->
        <div v-if="curItem.type === 'blank'" class="editor-content">
          <div class="block-box">
            <div class="block-title">样式设置</div>
            <div class="block-item">
              <span class="label">组件高度</span>
              <div class="item-slider">
                <el-slider v-model="curItem.style.height" :min="5" :max="200" />
                <span class="unit-text">
                <span>{{ curItem.style.height }}</span>
                <span>像素</span>
              </span>
              </div>
            </div>
            <div class="block-item">
              <span class="label">背景颜色</span>
              <div class="item-colorPicker">
              <span
                class="rest-color"
                @click="onEditorResetColor(curItem.style, 'background', '#fff')"
              >重置</span>
                <el-color-picker v-model="curItem.style.background" :predefine="['#fff']" />
              </div>
            </div>
          </div>
        </div>

        <!-- 辅助线 -->
        <div v-if="curItem.type === 'guide'" class="editor-content">
          <div class="block-box">
            <div class="block-title">样式设置</div>
            <div class="block-item">
              <span class="label">线条样式</span>
              <el-radio-group buttonStyle="solid" v-model="curItem.style.lineStyle">
                <el-radio-button value="solid">实线</el-radio-button>
                <el-radio-button value="dashed">虚线</el-radio-button>
                <el-radio-button value="dotted">点状</el-radio-button>
              </el-radio-group>
            </div>
            <div class="block-item">
              <span class="label">线条颜色</span>
              <div class="item-colorPicker">
              <span
                class="rest-color"
                @click="onEditorResetColor(curItem.style, 'lineColor', '#000')"
              >重置</span>
                <el-color-picker v-model="curItem.style.lineColor" :predefine="['#000']" />
              </div>
            </div>
            <div class="block-item">
              <span class="label">线条高度</span>
              <div class="item-slider">
                <el-slider v-model="curItem.style.lineHeight" :min="1" :max="20" />
                <span class="unit-text">
                <span>{{ curItem.style.lineHeight }}</span>
                <span>像素</span>
              </span>
              </div>
            </div>
            <div class="block-item">
              <span class="label">上下边距</span>
              <div class="item-slider">
                <el-slider v-model="curItem.style.paddingTop" :min="0" :max="50" />
                <span class="unit-text">
                <span>{{ curItem.style.paddingTop }}</span>
                <span>像素</span>
              </span>
              </div>
            </div>
            <div class="block-item">
              <span class="label">背景颜色</span>
              <div class="item-colorPicker">
              <span
                class="rest-color"
                @click="onEditorResetColor(curItem.style, 'background', '#fff')"
              >重置</span>
                <el-color-picker v-model="curItem.style.background" :predefine="['#fff']" />
              </div>
            </div>
          </div>
        </div>

        <!-- 富文本 -->
        <div v-if="curItem.type === 'richText'" :style="{ width: '395px' }" class="editor-content">
          <el-tabs>
            <el-tab-pane key="1" tab="内容设置">
              <div class="block-box">
                <div class="block-title">文本内容</div>
                <div class="ueditor">
<!--                  <Ueditor v-model="curItem.params.content" :config="{ initialFrameWidth: 375  }" />-->
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane key="2" tab="样式设置">
              <div class="block-box">
                <div class="block-title">组件样式</div>
                <div class="block-item">
                  <span class="label">上下边距</span>
                  <div class="item-slider">
                    <el-slider v-model="curItem.style.paddingTop" :min="0" :max="50" />
                    <span class="unit-text">
                    <span>{{ curItem.style.paddingTop }}</span>
                    <span>像素</span>
                  </span>
                  </div>
                </div>
                <div class="block-item">
                  <span class="label">左右边距</span>
                  <div class="item-slider">
                    <el-slider v-model="curItem.style.paddingLeft" :min="0" :max="50" />
                    <span class="unit-text">
                    <span>{{ curItem.style.paddingLeft }}</span>
                    <span>像素</span>
                  </span>
                  </div>
                </div>
                <div class="block-item">
                  <span class="label">背景颜色</span>
                  <div class="item-colorPicker">
                  <span
                    class="rest-color"
                    @click="onEditorResetColor(curItem.style, 'background', '#fff')"
                  >重置</span>
                    <el-color-picker v-model="curItem.style.background" :predefine="['#fff']" />
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>

        <!-- 店铺公告 -->
        <div v-if="curItem.type === 'notice'" class="editor-content">
          <el-tabs>
            <el-tab-pane key="1" tab="内容设置">
              <div class="block-box">
                <div class="block-title">公告文案</div>
                <div class="block-item">
                  <span class="label">内容</span>
                  <el-input v-model="curItem.params.text" />
                </div>
                <div class="block-item">
                  <span class="label">链接</span>
                  <div class="flex-box">
<!--                    <SLink v-model="curItem.params.link" />-->
                  </div>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane key="2" tab="样式设置">
              <div class="block-box">
                <div class="block-title">内容样式</div>
                <div class="block-item">
                  <span class="label">文字颜色</span>
                  <div class="item-colorPicker">
                  <span
                    class="rest-color"
                    @click="onEditorResetColor(curItem.style, 'textColor', '#000')"
                  >重置</span>
                    <el-color-picker v-model="curItem.style.textColor" :predefine="['#000']" />
                  </div>
                </div>
                <div class="block-item">
                  <span class="label">背景颜色</span>
                  <div class="item-colorPicker">
                  <span
                    class="rest-color"
                    @click="onEditorResetColor(curItem.style, 'background', '#fff')"
                  >重置</span>
                    <el-color-picker v-model="curItem.style.background" :predefine="['#fff']" />
                  </div>
                </div>
              </div>
              <div class="block-box">
                <div class="block-title">组件样式</div>
                <div class="block-item">
                  <span class="label">上下边距</span>
                  <div class="item-slider">
                    <el-slider v-model="curItem.style.paddingTop" :min="0" :max="50" />
                    <span class="unit-text">
                    <span>{{ curItem.style.paddingTop }}</span>
                    <span>像素</span>
                  </span>
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>

        <!-- 文章 -->
        <div v-if="curItem.type === 'article'" class="editor-content">
          <div class="block-box">
            <div class="block-title">文章内容</div>
            <div class="block-item">
              <span class="label">文章分类</span>
<!--              <SArticleCate v-model="curItem.params.auto.category" />-->
            </div>
            <div class="block-item">
              <span class="label">显示数量</span>
              <div class="block-item-right">
                <el-input-number v-model="curItem.params.auto.showNum" :min="1" :max="20" />
                <span class="unit-text">
                <span>篇</span>
              </span>
              </div>
            </div>
          </div>
        </div>

        <!-- 在线客服 -->
        <div v-if="curItem.type === 'service'" class="editor-content">
          <el-tabs>
            <el-tab-pane key="1" tab="内容设置">
              <div class="block-box">
                <div class="block-title">功能设置</div>
                <div class="block-item">
                  <span class="label">客服类型</span>
                  <el-radio-group buttonStyle="solid" v-model="curItem.params.type">
                    <el-radio-button value="chat">在线聊天</el-radio-button>
                    <el-radio-button value="phone">拨打电话</el-radio-button>
                  </el-radio-group>
                </div>
                <div v-show="curItem.params.type === 'phone'" class="block-item">
                  <span class="label">电话号码</span>
                  <el-input v-model="curItem.params.tel" />
                </div>
                <div class="block-item">
                  <span class="label">客服图标</span>
                  <span class="tips-wrap">建议尺寸：90×90</span>
<!--                  <SImage v-model="curItem.params.image" :width="60" :height="60" />-->
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane key="2" tab="样式设置">
              <div class="block-box">
                <div class="block-title">组件样式</div>
                <div class="block-item">
                  <span class="label">底边距</span>
                  <div class="item-slider">
                    <el-slider v-model="curItem.style.bottom" :min="0" :max="50" />
                    <span class="unit-text">
                    <span>{{ curItem.style.bottom }}</span>
                    <span>像素</span>
                  </span>
                  </div>
                </div>
                <div class="block-item">
                  <span class="label">右边距</span>
                  <div class="item-slider">
                    <el-slider v-model="curItem.style.right" :min="0" :max="50" />
                    <span class="unit-text">
                    <span>{{ curItem.style.right }}</span>
                    <span>像素</span>
                  </span>
                  </div>
                </div>
                <div class="block-item">
                  <span class="label">不透明度</span>
                  <div class="item-slider">
                    <el-slider v-model="curItem.style.opacity" :min="0" :max="100" />
                    <span class="unit-text">
                    <span>{{ curItem.style.opacity }}</span>
                    <span>%</span>
                  </span>
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>

        <!-- 视频 -->
        <div v-if="curItem.type === 'video'" class="editor-content">
          <el-tabs>
            <el-tab-pane key="1" tab="内容设置">
              <div class="block-box">
                <div class="block-title">功能设置</div>
                <div class="block-item">
                  <span class="label">视频地址</span>
                  <div class="flex-box">
                    <el-input v-model="curItem.params.videoUrl" />
                    <div class="tips">仅支持.mp4格式的视频源地址</div>
                  </div>
                </div>
                <div class="block-item">
                  <span class="label">视频封面</span>
                  <div class="flex-box">
<!--                    <SImage v-model="curItem.params.poster" :width="160" :height="90" />-->
                    <div class="tips">建议封面图片尺寸与视频比例一致</div>
                  </div>
                </div>
              </div>
              <div class="block-box">
                <div class="block-title">播放设置</div>
                <div class="block-item">
                  <span class="label">自动播放</span>
                  <span class="tips-wrap">仅支持小程序</span>
                  <el-radio-group buttonStyle="solid" v-model="curItem.params.autoplay">
                    <el-radio-button :value="1">开启</el-radio-button>
                    <el-radio-button :value="0">关闭</el-radio-button>
                  </el-radio-group>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane key="2" tab="样式设置">
              <div class="block-box">
                <div class="block-title">
                  <span>内容样式</span>
                  <span class="tips">视频宽度为750像素</span>
                </div>
                <div class="block-item">
                  <span class="label">视频高度</span>
                  <div class="item-slider">
                    <el-slider v-model="curItem.style.height" :min="50" :max="400" />
                    <span class="unit-text">
                    <span>{{ curItem.style.height }}</span>
                    <span>像素</span>
                  </span>
                  </div>
                </div>
              </div>
              <div class="block-box">
                <div class="block-title">组件样式</div>
                <div class="block-item">
                  <span class="label">上下边距</span>
                  <div class="item-slider">
                    <el-slider v-model="curItem.style.paddingTop" :min="0" :max="50" />
                    <span class="unit-text">
                    <span>{{ curItem.style.paddingTop }}</span>
                    <span>像素</span>
                  </span>
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>

        <!-- 图片 -->
        <div v-if="curItem.type === 'image'" class="editor-content">
          <el-tabs>
            <el-tab-pane key="1" tab="内容设置">
              <div class="sub-title">添加图片 (最多10张，可拖动排序）</div>
              <draggable
                :list="curItem.data"
                v-bind="{ animation: 120, filter: 'input', preventOnFilter: false }"
              >
                <div v-for="(item, index) in curItem.data" :key="index" class="block-box drag">
                  <div class="block-title">
                    <span class="left">图片 {{ index + 1 }}</span>
                    <a class="link" @click="handleDeleteData(curItem, index)">删除</a>
                  </div>
                  <div class="block-item">
                    <div class="block-item-common">
                      <div class="block-item-common-row">
                        <span class="label">图片</span>
                        <span class="label value">{{ item.imgName }}</span>
                      </div>
                      <div class="block-item-common-row">
                        <span class="label">链接</span>
<!--                        <SLink v-model="item.link" />-->
                      </div>
                    </div>
                    <div class="block-item-custom">
<!--                      <SImage-->
<!--                        v-model="item.imgUrl"-->
<!--                        tips="建议尺寸：宽750"-->
<!--                        @update="item.imgName = $event.file_name"-->
<!--                      />-->
                    </div>
                  </div>
                </div>
              </draggable>
              <div v-if="curItem.data.length < 10" class="datel-add">
                <el-button icon="plus" @click="handleAddData(10)">添加图片</el-button>
              </div>
            </el-tab-pane>
            <el-tab-pane key="2" tab="样式设置">
              <div class="block-box">
                <div class="block-title">组件样式</div>
                <div class="block-item">
                  <span class="label">上下边距</span>
                  <div class="item-slider">
                    <el-slider v-model="curItem.style.paddingTop" :min="0" :max="50" />
                    <span class="unit-text">
                    <span>{{ curItem.style.paddingTop }}</span>
                    <span>像素</span>
                  </span>
                  </div>
                </div>
                <div class="block-item">
                  <span class="label">左右边距</span>
                  <div class="item-slider">
                    <el-slider v-model="curItem.style.paddingLeft" :min="0" :max="50" />
                    <span class="unit-text">
                    <span>{{ curItem.style.paddingLeft }}</span>
                    <span>像素</span>
                  </span>
                  </div>
                </div>
                <div class="block-item">
                  <span class="label">背景颜色</span>
                  <div class="item-colorPicker">
                  <span
                    class="rest-color"
                    @click="onEditorResetColor(curItem.style, 'background', '#fff')"
                  >重置</span>
                    <el-color-picker v-model="curItem.style.background" :predefine="['#fff']" />
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>

        <!-- 轮播图 -->
        <div v-if="curItem.type === 'banner'" class="editor-content">
          <el-tabs :stretch="true" :value="'first'">
            <el-tab-pane  name="first" label="内容设置">
              <div class="sub-title">添加图片 (最多10张，可拖动排序）</div>
              <draggable
                :list="curItem.data"
                v-bind="{ animation: 120, filter: 'input', preventOnFilter: false }"
              >
                <div v-for="(item, index) in curItem.data" :key="index" class="block-box drag">
                  <div class="block-title">
                    <span class="left">图片 {{ index + 1 }}</span>
                    <a class="link" @click="handleDeleteData(curItem, index)">删除</a>
                  </div>
                  <div class="block-item">
                    <div class="block-item-common">
                      <div class="block-item-common-row">
                        <span class="label">图片</span>
                        <span class="label value">{{ item.imgName }}</span>
                      </div>
                      <div class="block-item-common-row">
                        <span class="label">链接</span>
<!--                        <SLink v-model="item.link" />-->
                      </div>
                    </div>
                    <div class="block-item-custom">
<!--                      <SImage-->
<!--                        v-model="item.imgUrl"-->
<!--                        tips="建议尺寸：750×400"-->
<!--                        @update="item.imgName = $event.file_name"-->
<!--                      />-->
                    </div>
                  </div>
                </div>
              </draggable>
              <div v-if="curItem.data.length < 10" class="datel-add">
                <el-button icon="plus" @click="handleAddData(10)">添加图片</el-button>
              </div>
            </el-tab-pane>
            <el-tab-pane name="second" label="样式设置">
              <div class="block-box">
                <div class="block-title">内容样式</div>
                <div class="block-item">
                  <span class="label">指示点形状</span>
                  <el-radio-group buttonStyle="solid" v-model="curItem.style.btnShape">
                    <el-radio-button value="round">圆形</el-radio-button>
                    <el-radio-button value="square">正方形</el-radio-button>
                    <el-radio-button value="rectangle">长方形</el-radio-button>
                  </el-radio-group>
                </div>
                <div class="block-item">
                  <span class="label">指示点颜色</span>
                  <div class="item-colorPicker">
                  <span
                    class="rest-color"
                    @click="onEditorResetColor(curItem.style, 'btnColor', '#fff')"
                  >重置</span>
                    <el-color-picker v-model="curItem.style.btnColor" :predefine="['#fff']" />
                  </div>
                </div>
                <div class="block-item">
                  <span class="label">切换时间</span>
                  <div class="item-slider" style="width: 190px;">
                    <el-slider v-model="curItem.style.interval" :min="1" :max="20"/>
                    <span class="unit-text">
                    <span>{{ curItem.style.interval }}</span>
                    <span>秒</span>
                  </span>
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>

        <!-- 商品组 -->
        <div v-if="curItem.type === 'goods'" class="editor-content">
          <el-tabs>
            <el-tab-pane key="1" tab="内容设置">
              <div class="block-box">
                <div class="block-title">
                  <span>商品来源</span>
                  <el-radio-group buttonStyle="solid" v-model="curItem.params.source">
                    <el-radio-button value="auto">自动获取</el-radio-button>
                    <el-radio-button value="choice">手动选择</el-radio-button>
                  </el-radio-group>
                </div>
              </div>
              <!-- 手动选择 -->
              <div v-if="curItem.params.source === 'choice'" class="block-box">
                <div class="block-title">选择商品 ({{ curItem.data.length }})</div>
<!--                <SGoods v-model="curItem.data" />-->
              </div>
              <!-- 自动获取 -->
              <div v-if="curItem.params.source === 'auto'" class="block-box">
                <div class="block-title">商品内容</div>
                <div class="block-item">
                  <span class="label">商品分类</span>
<!--                  <SGoodsCate v-model="curItem.params.auto.category" />-->
                </div>
                <div class="block-item">
                  <span class="label">商品排序</span>
                  <el-radio-group buttonStyle="solid" v-model="curItem.params.auto.goodsSort">
                    <el-radio-button value="all">默认</el-radio-button>
                    <el-radio-button value="sales">销量</el-radio-button>
                    <el-radio-button value="price">价格</el-radio-button>
                  </el-radio-group>
                </div>
                <div class="block-item">
                  <span class="label">显示数量</span>
                  <div class="block-item-right">
                    <el-input-number v-model="curItem.params.auto.showNum" :min="1" :max="50" />
                    <span class="unit-text">
                    <span>件</span>
                  </span>
                  </div>
                </div>
              </div>
            </el-tab-pane>
            <el-tab-pane key="2" tab="样式设置">
              <div class="block-box">
                <div class="block-title">内容样式</div>
                <div class="block-item">
                  <span class="label">显示类型</span>
                  <el-radio-group buttonStyle="solid" v-model="curItem.style.display">
                    <el-radio-button value="list">列表平铺</el-radio-button>
                    <el-radio-button :disabled="curItem.style.column === 1" value="slide">横向滑动</el-radio-button>
                  </el-radio-group>
                </div>
                <div class="block-item">
                  <span class="label">分列数量</span>
                  <el-radio-group buttonStyle="solid" v-model="curItem.style.column">
                    <el-radio-button :disabled="curItem.style.display !== 'list'" :value="1">单列</el-radio-button>
                    <el-radio-button :value="2">两列</el-radio-button>
                    <el-radio-button :value="3">三列</el-radio-button>
                  </el-radio-group>
                </div>
                <div class="block-item">
                  <span class="label">显示内容</span>
                  <div class="item-checkbox" :style="{ width: '180px' }">
                    <el-checkbox-group v-model="curItem.style.show">
                      <el-checkbox value="goodsName">商品名称</el-checkbox>
                      <el-checkbox value="goodsPrice">商品价格</el-checkbox>
                      <el-checkbox value="linePrice">划线价格</el-checkbox>
                      <el-checkbox v-show="curItem.style.column === 1" value="sellingPoint">商品卖点</el-checkbox>
                      <el-checkbox v-show="curItem.style.column === 1" value="goodsSales">商品销量</el-checkbox>
                    </el-checkbox-group>
                  </div>
                </div>
              </div>
              <div class="block-box">
                <div class="block-title">组件样式</div>
                <div class="block-item">
                  <span class="label">背景颜色</span>
                  <div class="item-colorPicker">
                  <span
                    class="rest-color"
                    @click="onEditorResetColor(curItem.style, 'background', '#fff')"
                  >重置</span>
                    <el-color-picker v-model="curItem.style.background" :predefine="['#fff']" />
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>

        <!-- 导航 -->
        <div v-if="curItem.type === 'navBar'" class="editor-content">
          <el-tabs>
            <el-tab-pane key="1" tab="内容设置">
              <div class="sub-title">添加导航 (最少4个，最多10个，可拖动排序)</div>
              <draggable
                :list="curItem.data"
                v-bind="{ animation: 120, filter: 'input', preventOnFilter: false }"
              >
                <div v-for="(item, index) in curItem.data" :key="index" class="block-box drag">
                  <div class="block-title">
                    <span class="left">导航 {{ index + 1 }}</span>
                    <a class="link" @click="handleDeleteData(curItem, index)">删除</a>
                  </div>
                  <div class="block-item">
                    <div class="block-item-common">
                      <div class="block-item-common-row">
                        <span class="label">名称</span>
                        <el-input v-model="item.text" />
                      </div>
                      <div class="block-item-common-row">
                        <span class="label">链接</span>
<!--                        <SLink v-model="item.link" />-->
                      </div>
                    </div>
                    <div class="block-item-custom">
<!--                      <SImage v-model="item.imgUrl" tips="建议尺寸：100×100" />-->
                    </div>
                  </div>
                </div>
              </draggable>
              <div v-if="curItem.data.length < 10" class="datel-add">
                <el-button icon="plus" @click="handleAddData(10)">添加导航</el-button>
              </div>
            </el-tab-pane>
            <el-tab-pane key="2" tab="样式设置">
              <div class="block-box">
                <div class="block-title">内容样式</div>
                <div class="block-item">
                  <span class="label">每行数量</span>
                  <el-radio-group buttonStyle="solid" v-model="curItem.style.rowsNum">
                    <el-radio-button :value="3">3个</el-radio-button>
                    <el-radio-button :value="4">4个</el-radio-button>
                    <el-radio-button :value="5">5个</el-radio-button>
                  </el-radio-group>
                </div>
                <div class="block-item">
                  <span class="label">文字颜色</span>
                  <div class="item-colorPicker">
                  <span
                    class="rest-color"
                    @click="onEditorResetColor(curItem.style, 'textColor', '#000')"
                  >重置</span>
                    <el-color-picker v-model="curItem.style.textColor" :predefine="['#000']" />
                  </div>
                </div>
              </div>
              <div class="block-box">
                <div class="block-title">组件样式</div>
                <div class="block-item">
                  <span class="label">上下边距</span>
                  <div class="item-slider">
                    <el-slider v-model="curItem.style.paddingTop" :min="0" :max="50" />
                    <span class="unit-text">
                    <span>{{ curItem.style.paddingTop }}</span>
                    <span>像素</span>
                  </span>
                  </div>
                </div>
                <div class="block-item">
                  <span class="label">背景颜色</span>
                  <div class="item-colorPicker">
                  <span
                    class="rest-color"
                    @click="onEditorResetColor(curItem.style, 'background', '#fff')"
                  >重置</span>
                    <el-color-picker v-model="curItem.style.background" :predefine="['#fff']" />
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>

        <!-- 橱窗 -->
        <div v-if="curItem.type === 'window'" class="editor-content">
          <el-tabs>
            <el-tab-pane key="1" tab="内容设置">
              <div class="sub-title">添加图片 (最多10个，可拖动排序)</div>
              <draggable
                :list="curItem.data"
                v-bind="{ animation: 120, filter: 'input', preventOnFilter: false }"
              >
                <div v-for="(item, index) in curItem.data" :key="index" class="block-box drag">
                  <div class="block-title">
                    <span class="left">图片 {{ index + 1 }}</span>
                    <a class="link" @click="handleDeleteData(curItem, index)">删除</a>
                  </div>
                  <div class="block-item">
                    <div class="block-item-common">
                      <div class="block-item-common-row">
                        <span class="label">名称</span>
                        <span class="label value">{{ item.imgName }}</span>
                      </div>
                      <div class="block-item-common-row">
                        <span class="label">链接</span>
<!--                        <SLink v-model="item.link" />-->
                      </div>
                    </div>
                    <div class="block-item-custom">
<!--                      <SImage v-model="item.imgUrl" @update="item.imgName = $event.file_name" />-->
                    </div>
                  </div>
                </div>
              </draggable>
              <div v-if="curItem.data.length < 10" class="datel-add">
                <el-button icon="plus" @click="handleAddData(10)">添加图片</el-button>
              </div>
            </el-tab-pane>
            <el-tab-pane key="2" tab="样式设置">
              <div class="block-box">
                <div class="block-title">内容样式</div>
                <div class="block-item">
                  <span class="label">每行数量</span>
                  <el-radio-group buttonStyle="solid" v-model="curItem.style.layout">
                    <el-radio-button :value="2">2列</el-radio-button>
                    <el-radio-button :value="3">3列</el-radio-button>
                    <el-radio-button :value="4">4列</el-radio-button>
                    <el-radio-button :value="-1">橱窗</el-radio-button>
                  </el-radio-group>
                </div>
                <div class="block-item">
                  <span class="label">上下边距</span>
                  <div class="item-slider" :style="{ width: '210px' }">
                    <el-slider v-model="curItem.style.paddingTop" :min="0" :max="50" />
                    <span class="unit-text">
                    <span>{{ curItem.style.paddingTop }}</span>
                    <span>像素</span>
                  </span>
                  </div>
                </div>
                <div class="block-item">
                  <span class="label">左右边距</span>
                  <div class="item-slider" :style="{ width: '210px' }">
                    <el-slider v-model="curItem.style.paddingLeft" :min="0" :max="50" />
                    <span class="unit-text">
                    <span>{{ curItem.style.paddingLeft }}</span>
                    <span>像素</span>
                  </span>
                  </div>
                </div>
              </div>
              <div class="block-box">
                <div class="block-title">组件样式</div>
                <div class="block-item">
                  <span class="label">背景颜色</span>
                  <div class="item-colorPicker">
                  <span
                    class="rest-color"
                    @click="onEditorResetColor(curItem.style, 'background', '#fff')"
                  >重置</span>
                    <el-color-picker v-model="curItem.style.background" :predefine="['#fff']" />
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </template>
    </div>


  </div>
</template>

<script>
import PropTypes from 'vue-types'
import draggable from 'vuedraggable'

export default {
  name: "Editor",
  props: {
    // 页面装修默认数据
    defaultData: PropTypes.object.def({}),
    // 页面数据
    data: PropTypes.object.def({}),
    // 当前选中的元素
    curItem: PropTypes.object.def({}),
    // 当前选中的元素索引
    selectedIndex: PropTypes.oneOfType([PropTypes.number, PropTypes.string]).def(0)
  },
  components: {
    draggable
  },
  methods: {
    /**
     * 重置颜色
     * @param holder
     * @param attribute
     * @param color
     */
    onEditorResetColor (holder, attribute, color) {
      holder[attribute] = color
    },
    // 新增数据
    handleAddData (max = 1) {
      const { defaultData, curItem } = this
      const newDataItem = defaultData.items[curItem.type].data[0]
      curItem.data.push(_.cloneDeep(newDataItem))
    },
    /**
     * 编辑器：删除data元素
     * @param curItem
     * @param index
     */
    handleDeleteData (curItem, index) {
      if (curItem.data.length <= 1) {
        this.$message.warning('至少保留一个')
        return false
      }
      curItem.data.splice(index, 1)
    },
  }
}
</script>

<style scoped>
.editor {
  min-width: 370px;
  min-height: 648px;
  padding: 10px 15px;
  background: #f8f9fa;
  overflow: hidden;
}
.editor-title {
  font-size: 14px;
  font-weight: 600;
  color: #595961;
  margin-top: 5px;
}
.editor-content {
  margin-top: 15px;
  width: 340px;
}
.editor .sub-title {
  text-align: center;
  font-size: 14px;
  color: #9797a1;
  margin-bottom: 15px;
  line-height: 20px;
}
.editor .label,
.editor .tips-wrap,
.editor .link {
  display: block;
  line-height: 32px;
  white-space: nowrap;
  font-size: 12px;
}
.editor .label {
  margin-right: 20px;
}
.editor .label.value {
  max-width: 164px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
  word-break: break-all;
}
.editor .tips-wrap {
  flex: 1;
  color: #9797a1;
}
.editor .block-box {
  border-radius: 4px;
  padding: 10px 10px 10px 10px;
  background: #f0f2f5;
  margin: 0 auto 10px;
}
.editor .block-box.drag .block-title {
  cursor: move;
}
.editor .block-box .block-title {
  line-height: 30px;
  font-weight: 700;
  font-size: 14px;
  margin-bottom: 10px;
  display: flex;
  justify-content: space-between;
}
.editor .block-box .block-title .link,
.editor .block-box .block-title .tips {
  font-size: 12px;
  font-weight: normal;
}
.editor .block-box .block-title .link {
  letter-spacing: 1px;
}
.editor .block-box .block-title .tips {
  color: #9797a1;
}
.editor .block-box .block-item {
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;
}
.editor .block-box .block-item .tips {
  margin-top: 5px;
  font-size: 12px;
  color: #9797a1;
}
.editor .block-box .block-item-common {
  flex: 1;
}
.editor .block-box .block-item-common-row {
  display: flex;
  margin-bottom: 6px;
}
.editor .block-box .block-item-common-row:last-child {
  margin-bottom: 0;
}
.editor .block-box .block-item-custom {
  margin-left: 20px;
}
.item-colorPicker {
  display: flex;
}
.item-colorPicker .rest-color {
  margin-right: 10px;
  font-size: 12px;
  line-height: 32px;
  cursor: pointer;
  color: blue !important;
}
.item-colorPicker .m-colorPicker {
  display: block;
  background-color: #ffffff;
  border: 1px solid #efefef;
  padding: 5px;
  width: 61px;
}
.item-colorPicker .m-colorPicker /deep/ .colorBtn {
  cursor: pointer;
  width: 48px;
  height: 20px;
  border: 1px solid #000;
}
.item-colorPicker .m-colorPicker /deep/ .box {
  left: -152px;
  top: 35px;
}
.item-checkbox .ant-checkbox-wrapper {
  margin: 0 10px 0 0;
  line-height: 28px;
  font-size: 12px;
}
.item-checkbox .ant-checkbox-wrapper + .ant-checkbox-wrapper {
  margin-left: 0;
}


.unit-text {
  padding: 0 10px;
  line-height: 32px;
  font-size: 12px;
}
.datel-add {
  margin-top: 10px;
}
.datel-add button {
  width: 100%;
  color: blue;
  height: 40px;
  border-radius: 5px;
  font-size: 12px;
  border-color: #cccccc21;
}
.datel-add button:hover {
  border-color: blue;
}
a {
  user-select: none;
}
.ant-input {
  font-size: 12px;
  border-radius: 5px;
}
.ant-tabs {
  overflow: unset;
}
.ant-tabs /deep/ .ant-tabs-nav-container {
  font-size: 12.5px;
}
.ant-tabs /deep/ .ant-tabs-nav-container .ant-tabs-nav {
  width: 100%;
}
.ant-tabs /deep/ .ant-tabs-nav-container .ant-tabs-nav .ant-tabs-tab {
  padding: 12px 20px;
  color: #595961;
  width: 105px;
  margin: 0 30px;
  text-align: center;
}
.ant-tabs /deep/ .ant-tabs-nav-container .ant-tabs-ink-bar {
  background-color: #595961;
  height: 3px;
}
.ant-radio-group {
  font-size: 12px;
}
.ant-radio-group /deep/ .ant-radio-button-wrapper {
  height: 30px;
  line-height: 28px;
}

</style>
