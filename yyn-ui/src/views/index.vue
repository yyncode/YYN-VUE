<template>
  <div class="app-container home">
<!--    <el-row :gutter="20">-->
<!--      <el-col :sm="24" :lg="24">-->
<!--        <blockquote class="text-warning" style="font-size: 14px">-->
<!--          领取阿里云通用云产品1888优惠券-->
<!--          <br />-->
<!--          <el-link-->
<!--            href="https://www.aliyun.com/minisite/goods?userCode=brki8iof"-->
<!--            type="primary"-->
<!--            target="_blank"-->
<!--            >https://www.aliyun.com/minisite/goods?userCode=brki8iof</el-link-->
<!--          >-->
<!--          <br />-->
<!--          领取腾讯云通用云产品2860优惠券-->
<!--          <br />-->
<!--          <el-link-->
<!--            href="https://cloud.tencent.com/redirect.php?redirect=1025&cps_key=198c8df2ed259157187173bc7f4f32fd&from=console"-->
<!--            type="primary"-->
<!--            target="_blank"-->
<!--            >https://cloud.tencent.com/redirect.php?redirect=1025&cps_key=198c8df2ed259157187173bc7f4f32fd&from=console</el-link-->
<!--          >-->
<!--          <br />-->
<!--          阿里云服务器折扣区-->
<!--          <el-link href="http://aly.ruoyi.vip" type="primary" target="_blank"-->
<!--            >>☛☛点我进入☚☚</el-link-->
<!--          >-->
<!--          &nbsp;&nbsp;&nbsp; 腾讯云服务器秒杀区-->
<!--          <el-link href="http://txy.ruoyi.vip" type="primary" target="_blank"-->
<!--            >>☛☛点我进入☚☚</el-link-->
<!--          ><br />-->
<!--          <h4 class="text-danger">-->
<!--            云产品通用红包，可叠加官网常规优惠使用。(仅限新用户)-->
<!--          </h4>-->
<!--        </blockquote>-->

<!--        <hr />-->
<!--      </el-col>-->
<!--    </el-row>-->
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>月月鸟后台管理框架</h2>
        <p>
          一直想做一款后台管理系统，看了很多优秀的开源项目但是发现没有合适自己的。于是利用空闲休息时间开始自己写一套后台系统。如此有了月月鸟管理系统，她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA等等，当然，您也可以对她进行深度定制，以做出更强系统。所有前端后台代码封装过后十分精简易上手，出错概率低。同时支持移动客户端访问。系统会陆续更新一些实用功能。
        </p>
        <p>
          <b>当前版本:</b> <span>v{{ version }}</span>
        </p>
        <p>
          <el-tag type="danger">&yen;免费开源</el-tag>
        </p>
        <p>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-cloudy"
            plain
            @click="goTarget('')"
            >访问码云</el-button
          >
          <el-button
            size="mini"
            icon="el-icon-s-home"
            plain
            @click="goTarget('http://yyncode.cn')"
            >访问主页</el-button
          >
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>Spring Security</li>
              <li>JWT</li>
              <li>MyBatis</li>
              <li>Druid</li>
              <li>Fastjson</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>联系信息</span>
          </div>
          <div class="body">
            <p>
              <i class="el-icon-s-promotion"></i> 官网：<el-link
                href="http://yyncode.cn"
                target="_blank"
                >http://yyncode.cn</el-link
              >
            </p>
            <p>
              <i class="el-icon-user-solid"></i> QQ群：<s>满937441</s> <s>满887144332</s>
              <s>满180251782</s> <s>满104180207</s> <s>满186866453</s> <s>满201396349</s>
              <s>满101456076</s> <s>满101539465</s> <s>满264312783</s> <s>满167385320</s>
              <s>满104748341</s> <s>满160110482</s> <a href="https://jq.qq.com/?_wv=1027&k=Xnbinl0U" target="_blank">
                170801498</a
              >
            </p>
            <p>
              <i class="el-icon-chat-dot-round"></i> 微信：<a
                href="javascript:;"
                >/ *月月鸟</a
              >
            </p>
            <p>
              <i class="el-icon-money"></i> 支付宝：<a
                href="javascript:;"
                class="支付宝信息"
                >/ *月月鸟</a
              >
            </p>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>更新日志</span>
          </div>
          <el-collapse accordion>
            <el-collapse-item title="v3.8.4 - 2022-09-26">
              <ol>
                <li>数据逻辑删除不进行唯一验证</li>
                <li>Excel注解支持导出对象的子列表方法</li>
                <li>Excel注解支持自定义隐藏属性列</li>
                <li>Excel注解支持backgroundColor属性设置背景色</li>
                <li>支持配置密码最大错误次数/锁定时间</li>
                <li>登录日志新增解锁账户功能</li>
                <li>通用下载方法新增config配置选项</li>
                <li>支持多权限字符匹配角色数据权限</li>
                <li>页面内嵌iframe切换tab不刷新数据</li>
                <li>操作日志记录支持排除敏感属性字段</li>
                <li>修复多文件上传报错出现的异常问题</li>
                <li>修复图片预览组件src属性为null值控制台报错问题</li>
                <li>升级oshi到最新版本6.2.2</li>
                <li>升级fastjson到最新版2.0.14</li>
                <li>升级pagehelper到最新版1.4.3</li>
                <li>升级core-js到最新版本3.25.2</li>
                <li>升级element-ui到最新版本2.15.10</li>
                <li>优化任务过期不执行调度</li>
                <li>优化字典数据使用store存取</li>
                <li>优化修改资料头像被覆盖的问题</li>
                <li>优化修改用户登录账号重复验证</li>
                <li>优化代码生成同步后值NULL问题</li>
                <li>优化定时任务支持执行父类方法</li>
                <li>优化用户个人信息接口防止修改部门</li>
                <li>优化布局设置使用el-drawer抽屉显示</li>
                <li>优化没有权限的用户编辑部门缺少数据</li>
                <li>优化日志注解记录限制请求地址的长度</li>
                <li>优化excel/scale属性导出单元格数值类型</li>
                <li>优化日志操作中重置按钮时重复查询的问题</li>
                <li>优化多个相同角色数据导致权限SQL重复问题</li>
                <li>优化表格上右侧工具条（搜索按钮显隐&右侧样式凸出）</li>
                <li>其他细节优化</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v3.8.3 - 2022-06-27">
              <ol>
                <li>新增缓存列表菜单功能</li>
                <li>代码生成树表新增(展开/折叠)</li>
                <li>Excel注解支持color字体颜色</li>
                <li>新增Anonymous匿名访问不鉴权注解</li>
                <li>用户头像上传限制只能为图片格式</li>
                <li>接口使用泛型使其看到响应属性字段</li>
                <li>检查定时任务bean所在包名是否为白名单配置</li>
                <li>添加页签openPage支持传递参数</li>
                <li>用户缓存信息添加部门ancestors祖级列表</li>
                <li>升级element-ui到最新版本2.15.8</li>
                <li>升级oshi到最新版本6.1.6</li>
                <li>升级druid到最新版本1.2.11</li>
                <li>升级fastjson到最新版2.0.8</li>
                <li>升级spring-boot到最新版本2.5.14</li>
                <li>降级jsencrypt版本兼容IE浏览器</li>
                <li>删除多余的salt字段</li>
                <li>新增获取不带后缀文件名称方法</li>
                <li>新增获取配置文件中的属性值方法</li>
                <li>新增内容编码/解码方便插件集成使用</li>
                <li>字典类型必须以字母开头，且只能为（小写字母，数字，下滑线）</li>
                <li>优化设置分页参数默认值</li>
                <li>优化对空字符串参数处理的过滤</li>
                <li>优化显示顺序orderNum类型为整型</li>
                <li>优化表单构建按钮不显示正则校验</li>
                <li>优化字典数据回显样式下拉框显示值</li>
                <li>优化R响应成功状态码与全局保持一致</li>
                <li>优化druid开启wall过滤器出现的异常问题</li>
                <li>优化用户管理左侧树型组件增加选中高亮保持</li>
                <li>优化新增用户与角色信息&用户与岗位信息逻辑</li>
                <li>优化默认不启用压缩文件缓存防止node_modules过大</li>
                <li>修复字典数据显示不全问题</li>
                <li>修复操作日志查询类型条件为0时会查到所有数据</li>
                <li>修复Excel注解prompt/combo同时使用不生效问题</li>
                <li>其他细节优化</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v3.8.2 - 2022-04-01">
              <ol>
                <li>前端支持设置是否需要防止数据重复提交</li>
                <li>开启TopNav没有子菜单情况隐藏侧边栏</li>
                <li>侧边栏菜单名称过长悬停显示标题</li>
                <li>用户访问控制时校验数据权限，防止越权</li>
                <li>导出Excel时屏蔽公式，防止CSV注入风险</li>
                <li>组件ImagePreview支持多图预览显示</li>
                <li>组件ImageUpload支持多图同时选择上传</li>
                <li>组件FileUpload支持多文件同时选择上传</li>
                <li>服务监控新增运行参数信息显示</li>
                <li>定时任务目标字符串过滤特殊字符</li>
                <li>定时任务目标字符串验证包名白名单</li>
                <li>代码生成列表图片支持预览</li>
                <li>代码生成编辑修改打开新页签</li>
                <li>代码生成新增Java类型Boolean</li>
                <li>代码生成子表支持日期/字典配置</li>
                <li>代码生成同步保留必填/类型选项</li>
                <li>升级oshi到最新版本6.1.2</li>
                <li>升级fastjson到最新版1.2.80</li>
                <li>升级pagehelper到最新版1.4.1</li>
                <li>升级spring-boot到最新版本2.5.11</li>
                <li>升级spring-boot-mybatis到最新版2.2.2</li>
                <li>添加遗漏的分页参数合理化属性</li>
                <li>修改npm即将过期的注册源地址</li>
                <li>修复分页组件请求两次问题</li>
                <li>修复通用文件下载接口跨域问题</li>
                <li>修复Xss注解字段值为空时的异常问题</li>
                <li>修复选项卡点击右键刷新丢失参数问题</li>
                <li>修复表单清除元素位置未垂直居中问题</li>
                <li>修复服务监控中运行参数显示条件错误</li>
                <li>修复导入Excel时字典字段类型为Long转义为空问题</li>
                <li>修复登录超时刷新页面跳转登录页面还提示重新登录问题</li>
                <li>优化加载字典缓存数据</li>
                <li>优化IP地址获取到多个的问题</li>
                <li>优化任务队列满时任务拒绝策略</li>
                <li>优化文件上传兼容Weblogic环境</li>
                <li>优化定时任务默认保存到内存中执行</li>
                <li>优化部门修改缩放后出现的错位问题</li>
                <li>优化Excel格式化不同类型的日期对象</li>
                <li>优化菜单表关键字导致的插件报错问题</li>
                <li>优化Oracle用户头像列为空时不显示问题</li>
                <li>优化页面若未匹配到字典标签则返回原字典值</li>
                <li>优化修复登录失效后多次请求提示多次弹窗问题</li>
                <li>其他细节优化</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v3.8.1 - 2022-01-01">
              <ol>
                <li>新增Vue3前端代码生成模板</li>
                <li>新增图片预览组件</li>
                <li>新增压缩插件实现打包Gzip</li>
                <li>自定义xss校验注解实现</li>
                <li>自定义文字复制剪贴指令</li>
                <li>代码生成预览支持复制内容</li>
                <li>路由支持单独配置菜单或角色权限</li>
                <li>用户管理部门查询选择节点后分页参数初始</li>
                <li>修复用户分配角色属性错误</li>
                <li>修复打包后字体图标偶现的乱码问题</li>
                <li>修复菜单管理重置表单出现的错误</li>
                <li>修复版本差异导致的懒加载报错问题</li>
                <li>修复Cron组件中周回显问题</li>
                <li>修复定时任务多参数逗号分隔的问题</li>
                <li>修复根据ID查询列表可能出现的主键溢出问题</li>
                <li>修复tomcat配置参数已过期问题</li>
                <li>升级clipboard到最新版本2.0.8</li>
                <li>升级oshi到最新版本v5.8.6</li>
                <li>升级fastjson到最新版1.2.79</li>
                <li>升级spring-boot到最新版本2.5.8</li>
                <li>升级log4j2到2.17.1，防止漏洞风险</li>
                <li>优化下载解析blob异常提示</li>
                <li>优化代码生成字典组重复问题</li>
                <li>优化查询用户的角色组&岗位组代码</li>
                <li>优化定时任务cron表达式小时设置24</li>
                <li>优化用户导入提示溢出则显示滚动条</li>
                <li>优化防重复提交标识组合为(key+url+header)</li>
                <li>优化分页方法设置成通用方便灵活调用</li>
                <li>其他细节优化</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v3.8.0 - 2021-12-01">
              <ol>
                <li>新增配套并同步的Vue3前端版本</li>
                <li>新增通用方法简化模态/缓存/下载/权限/页签使用</li>
                <li>优化导出数据/使用通用下载方法</li>
                <li>Excel注解支持自定义数据处理器</li>
                <li>Excel注解支持导入导出标题信息</li>
                <li>Excel导入支持@Excels注解</li>
                <li>新增组件data-dict，简化数据字典使用</li>
                <li>新增Jaxb依赖，防止jdk8以上出现的兼容错误</li>
                <li>生产环境使用路由懒加载提升页面响应速度</li>
                <li>修复五级以上菜单出现的404问题</li>
                <li>防重提交注解支持配置间隔时间/提示消息</li>
                <li>日志注解新增是否保存响应参数</li>
                <li>任务屏蔽违规字符&参数忽略双引号中的逗号</li>
                <li>升级SpringBoot到最新版本2.5.6</li>
                <li>升级pagehelper到最新版1.4.0</li>
                <li>升级spring-boot-mybatis到最新版2.2.0</li>
                <li>升级oshi到最新版本v5.8.2</li>
                <li>升级druid到最新版1.2.8</li>
                <li>升级velocity到最新版本2.3</li>
                <li>升级fastjson到最新版1.2.78</li>
                <li>升级axios到最新版本0.24.0</li>
                <li>升级dart-sass到版本1.32.13</li>
                <li>升级core-js到最新版本3.19.1</li>
                <li>升级jsencrypt到最新版本3.2.1</li>
                <li>升级js-cookie到最新版本3.0.1</li>
                <li>升级file-saver到最新版本2.0.5</li>
                <li>升级sass-loader到最新版本10.1.1</li>
                <li>升级element-ui到最新版本2.15.6</li>
                <li>新增sendGet无参请求方法</li>
                <li>禁用el-tag组件的渐变动画</li>
                <li>代码生成点击预览重置激活tab</li>
                <li>AjaxResult重写put方法，以方便链式调用</li>
                <li>优化登录/验证码请求headers不设置token</li>
                <li>优化用户个人信息接口防止修改用户名</li>
                <li>优化Cron表达式生成器关闭时销毁避免缓存</li>
                <li>优化注册成功提示消息类型success</li>
                <li>优化aop语法，使用spring自动注入注解</li>
                <li>优化记录登录信息，移除不必要的修改</li>
                <li>优化mybatis全局默认的执行器</li>
                <li>优化Excel导入图片可能出现的异常</li>
                <li>修复代码生成模板主子表删除缺少事务</li>
                <li>修复日志记录可能出现的转换异常</li>
                <li>修复代码生成复选框字典遗漏问题</li>
                <li>修复关闭xss功能导致可重复读RepeatableFilter失效</li>
                <li>修复字符串无法被反转义问题</li>
                <li>修复后端主子表代码模板方法名生成错误问题</li>
                <li>修复xss过滤后格式出现的异常</li>
                <li>修复swagger没有指定dataTypeClass导致启动出现warn日志</li>
                <li>其他细节优化</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v1.0.0 - 2019-10-08">
              <ol>
                <li>月月鸟前后端分离系统正式发布</li>
              </ol>
            </el-collapse-item>
          </el-collapse>
        </el-card>
      </el-col>
<!--      <el-col :xs="24" :sm="24" :md="12" :lg="8">-->
<!--        <el-card class="update-log">-->
<!--          <div slot="header" class="clearfix">-->
<!--            <span>捐赠支持</span>-->
<!--          </div>-->
<!--          <div class="body">-->
<!--            <img-->
<!--              src="https://oscimg.oschina.net/oscnet/up-d6695f82666e5018f715c41cb7ee60d3b73.png"-->
<!--              alt="donate"-->
<!--              width="100%"-->
<!--            />-->
<!--            <span style="display: inline-block; height: 30px; line-height: 30px"-->
<!--              >你可以请作者喝杯咖啡表示鼓励</span-->
<!--            >-->
<!--          </div>-->
<!--        </el-card>-->
<!--      </el-col>-->
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.4",
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

