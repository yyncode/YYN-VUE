<template>
  <div class="app-container">
    <div class="sms">
      <div class="form-item-group">
        <div class="form-item">

          <span class="form-item-label" ><span style="color: red">*</span>
        短信平台：
      </span>
          <div class="form-item-content">
            <div class="form-item-radio">
              <el-radio-group
                v-model="form.default"
                @change="onChangeEngine"
              >
                <el-radio
                  v-for="(engine, index) in record.engine"
                  :key="index"
                  :label="index"
                >{{ engine.name }}</el-radio>
              </el-radio-group>
            </div>

            <div v-if="form.default" class="form-item-help">
              <p class="extra">
                <small>短信平台管理地址:</small><a
                :href="record.engine[form.default].website"
                target="_blank"
                style="color: #00a0e9"
              >{{ record.engine[form.default].website }}</a>
              </p>
            </div>
          </div>
        </div>
      </div>
      <div class="form-item-group" v-show="form.default === 'aliyun'">
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        AccessKeyId：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        AccessKeySecret：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        短信签名 Sign：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
      </div>
      <div class="form-item-group" v-show="form.default === 'qcloud'">
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        SdkAppID：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        AccessKeyId：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        AccessKeySecret：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        短信签名 Sign：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
      </div>
      <div class="form-item-group" v-show="form.default === 'qiniu'">
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        AccessKey：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        SecretKey：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <!--                <el-input-number-->
              <!--                  :min="0"-->
              <!--                  :precision="0"-->
              <!--                  controls-position="right"-->
              <!--                  size="mini"-->
              <!--                />-->
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
          </div>
        </div>
      </div>
      <div class="form-item-group">
        <div class="form-item">
          <el-divider content-position="left"><span style="font-size: 13px;color: rgba(0,0,0,.45);">短信验证码 (通知用户)</span></el-divider>
        </div>
      </div>
      <div class="form-item-group">
        <div class="form-item">
          <span class="form-item-label" ><span style="color: red">*</span>
        是否开启：
      </span>
          <div class="form-item-content">
            <div class="form-item-radio">
              <el-radio-group >
                <el-radio :label="1">开启</el-radio>
                <el-radio :label="0">关闭</el-radio>
              </el-radio-group>
            </div>

          </div>
      </div>
        <div class="form-item">
          <span class="form-item-label" ><span style="color: red">*</span> 模板内容：</span>
          <div class="form-item-content">
            <div class="form-item-text">
              验证码${code}，您正在进行身份验证，打死不要告诉别人哦！
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        模板ID/Code：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
            <div class="form-item-help">
              <p class="extra">
                <small>例如：SMS_139800030</small>
              </p>
            </div>
          </div>
        </div>
      </div>
      <div class="form-item-group">
        <div class="form-item">
          <el-divider content-position="left"><span style="font-size: 13px;color: rgba(0,0,0,.45);">新付款订单 (通知商家)</span></el-divider>
        </div>
      </div>
      <div class="form-item-group">
        <div class="form-item">
          <span class="form-item-label" ><span style="color: red">*</span>
        是否开启：
      </span>
          <div class="form-item-content">
            <div class="form-item-radio">
              <el-radio-group >
                <el-radio :label="1">开启</el-radio>
                <el-radio :label="0">关闭</el-radio>
              </el-radio-group>
            </div>

          </div>
        </div>
        <div class="form-item">
          <span class="form-item-label" ><span style="color: red">*</span> 模板内容：</span>
          <div class="form-item-content">
            <div class="form-item-text">
              您有一条新订单，订单号为：${order_no}，请注意查看
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        模板ID/Code：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
            <div class="form-item-help">
              <p class="extra">
                <small>例如：SMS_139800030</small>
              </p>
            </div>
          </div>
        </div>
        <div class="form-item">

            <span class="form-item-input-label"><span style="color: red;padding-top: 4px">*</span>
        接收手机号：</span>
          <div class="form-item-content">
            <div class="form-item-input">
              <el-input placeholder="请输入内容" size="small"></el-input>
            </div>
            <div class="form-item-help">
              <p class="extra">
                <small>注：如需填写多个手机号，请用英文逗号<code> , </code>隔开</small>
              </p>
            </div>
          </div>
        </div>
      </div>
      <div class="form-item-group ">
        <div class="form-item">
          <div class="form-item-btn">
            <el-button type="primary" >提交</el-button>
          </div>
        </div>

      </div>
    </div>
<!--    <el-form :form="form" @submit="handleSubmit">-->
<!--      <el-form-item class="mb-20" label="短信平台" >-->
<!--        <el-radio-group-->
<!--          v-model="form.default"-->
<!--          @change="onChangeEngine"-->
<!--        >-->
<!--          <el-radio-->
<!--            v-for="(engine, index) in record.engine"-->
<!--            :key="index"-->
<!--            :label="index"-->
<!--          >{{ engine.name }}</el-radio>-->
<!--        </el-radio-group>-->

<!--        <div v-if="form.default" class="form-item-help">-->
<!--          <small>短信平台管理地址：</small>-->
<!--          <a-->
<!--            :href="record.engine[form.default].website"-->
<!--            target="_blank"-->
<!--          >{{ record.engine[form.default].website }}</a>-->
<!--        </div>-->
<!--      </el-form-item>-->
<!--      &lt;!&ndash; 阿里云配置 &ndash;&gt;-->
<!--      <div v-show="form.default === 'aliyun'">-->
<!--        <el-form-item label="AccessKeyId"  required>-->
<!--          <el-input  />-->
<!--        </el-form-item>-->
<!--        <el-form-item-->
<!--          label="AccessKeySecret"-->
<!--          required-->
<!--        >-->
<!--          <el-input  />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="短信签名 Sign" required>-->
<!--          <el-input />-->
<!--        </el-form-item>-->
<!--      </div>-->
<!--      &lt;!&ndash; 腾讯云配置 &ndash;&gt;-->
<!--      <div v-show="form.default === 'qcloud'">-->
<!--        <el-form-item label="SdkAppID" required>-->
<!--          <el-input  />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="AccessKeyId"  required>-->
<!--          <el-input />-->
<!--        </el-form-item>-->
<!--        <el-form-item-->
<!--          label="AccessKeySecret"-->
<!--          required-->
<!--        >-->
<!--          <el-input  />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="短信签名 Sign"  required>-->
<!--          <el-input />-->
<!--        </el-form-item>-->
<!--      </div>-->
<!--      &lt;!&ndash; 七牛云配置 &ndash;&gt;-->
<!--      <div v-show="form.default === 'qiniu'">-->
<!--        <el-form-item label="AccessKey" required>-->
<!--          <el-input  />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="SecretKey"  required>-->
<!--          <el-input />-->
<!--        </el-form-item>-->
<!--      </div>-->

<!--      &lt;!&ndash; 短信场景配置 &ndash;&gt;-->
<!--      <div v-for="(item, index) in record['scene']" :key="index">-->
<!--        <el-divider orientation="left">{{ item.name }}</el-divider>-->
<!--        <el-form-item label="是否开启" >-->
<!--          <el-radio-group-->
<!--            v-model="item.isEnable"-->
<!--          >-->
<!--            <el-radio :label="true">开启</el-radio>-->
<!--            <el-radio :label="false">关闭</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="模板内容"  required>-->
<!--          <span>{{ record.scene[index].contentPractical }}</span>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="模板ID/Code"  required>-->
<!--          <el-input  />-->
<!--          <div class="form-item-help">-->
<!--            <small>例如：SMS_139800030</small>-->
<!--          </div>-->
<!--        </el-form-item>-->
<!--        <el-form-item-->
<!--          v-if="record.scene[index].acceptPhone !== undefined"-->
<!--          label="接收手机号"-->
<!--          required-->
<!--        >-->
<!--          <el-input />-->
<!--          <div class="form-item-help">-->
<!--            <small>-->
<!--              注：如需填写多个手机号，请用英文逗号-->
<!--              <el-tag>,</el-tag>隔开-->
<!--            </small>-->
<!--          </div>-->
<!--        </el-form-item>-->
<!--      </div>-->
<!--      <el-form-item >-->
<!--        <el-button type="primary" html-type="submit">提交</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
  </div>
</template>

<script>

const values = {
  "default": "aliyun",
  "engine": {
    "aliyun": {
      "name": "阿里云短信",
      "website": "https://dysms.console.aliyun.com/dysms.htm",
      "AccessKeyId": "",
      "AccessKeySecret": "",
      "sign": "萤火商城"
    },
    "qcloud": {
      "name": "腾讯云短信",
      "website": "https://console.cloud.tencent.com/smsv2",
      "SdkAppID": "",
      "AccessKeyId": "",
      "AccessKeySecret": "",
      "sign": "萤火商城"
    },
    "qiniu": {
      "name": "七牛云短信",
      "website": "https://portal.qiniu.com/sms/dashboard",
      "AccessKey": "",
      "SecretKey": "",
      "sign": "萤火商城"
    }
  },
  "scene": {
    "captcha": {
      "name": "短信验证码 (通知用户)",
      "isEnable": false,
      "templateCode": "",
      "content": "验证码%s，您正在进行身份验证，打死不要告诉别人哦！",
      "variables": {
        "aliyun": [
          "${code}"
        ],
        "qiniu": [
          "${code}"
        ],
        "qcloud": [
          "{1}"
        ]
      }
    },
    "order_pay": {
      "name": "新付款订单 (通知商家)",
      "isEnable": true,
      "templateCode": "",
      "acceptPhone": "",
      "content": "您有一条新订单，订单号为：%s，请注意查看",
      "variables": {
        "aliyun": [
          "${order_no}"
        ],
        "qiniu": [
          "${order_no}"
        ],
        "qcloud": [
          "{1}"
        ]
      }
    }
  }
}
export default {
  name: "index",
  data(){
    return {
      record:values,
      form:{

      }
    }
  },
  // 初始化数据
  created () {
    // 获取当前详情记录
    this.getDetail()
  },
  methods: {

    // 获取当前详情记录
    getDetail () {
      this.isLoading = true
    },

    // 切换短信平台事件
    onChangeEngine (e) {
      // const app = this
      // const engine = e.target.value
      // for (const index in app.record.scene) {
      //   const item = app.record.scene[index]
      //   item.contentPractical = app.onVsprintf(item.content, item.variables[engine])
      // }
    },

    // 解析短信内容变量, 生成完整的模板内容
    onVsprintf (str, variables) {
      const reg = new RegExp('%s')
      for (let i = 0; i < variables.length; i++) {
        str = str.replace(reg, variables[i])
      }
      return str
    },

    /**
     * 设置默认值
     */
    setFieldsValue () {


    },

    /**
     * 确认按钮
     */
    handleSubmit (e) {
      e.preventDefault()
      // 表单验证
      const { form: { validateFields } } = this
      validateFields((errors, values) => {
        // 提交到后端api
        !errors && this.onFormSubmit(values)
      })
    },

    /**
     * 提交到后端api
     */
    onFormSubmit (values) {
      this.isLoading = true
    }

  }
}
</script>

<style scoped>
.sms .form-item-group .form-item{
  display: flex;
  margin-top: 40px;
  margin-left: 40px;
}

.sms .form-item-group .form-item .form-item-label {
  margin-left: 5px;
  margin-right: 10px;
  font-size: 13px;
  width: 150px;
  text-align: right;
}
.sms .form-item-group .form-item .form-item-input-label {
  margin-left: 5px;
  margin-right: 10px;
  font-size: 13px;
  padding-top: 5px;
  width: 150px;
  text-align: right;
}

.sms .form-item-group .form-item .form-item-content .form-item-text{
  font-size: 13px;
}

.el-input {
  width: 350px;
}

.sms .form-item-group .form-item  .form-item-btn {
  margin-left: 165px;
}
.sms .form-item-group .form-item .form-item-help {
  color: rgba(0, 0, 0, .45);
  font-size: 13px !important;
}
</style>
