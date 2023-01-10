<template>
  <div class="app-container">
    <el-form :form="form" @submit="handleSubmit">
      <el-form-item class="mb-20" label="短信平台" >
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

        <div v-if="form.default" class="form-item-help">
          <small>短信平台管理地址：</small>
          <a
            :href="record.engine[form.default].website"
            target="_blank"
          >{{ record.engine[form.default].website }}</a>
        </div>
      </el-form-item>
      <!-- 阿里云配置 -->
      <div v-show="form.default === 'aliyun'">
        <el-form-item label="AccessKeyId"  required>
          <el-input  />
        </el-form-item>
        <el-form-item
          label="AccessKeySecret"
          required
        >
          <el-input  />
        </el-form-item>
        <el-form-item label="短信签名 Sign" required>
          <el-input />
        </el-form-item>
      </div>
      <!-- 腾讯云配置 -->
      <div v-show="form.default === 'qcloud'">
        <el-form-item label="SdkAppID" required>
          <el-input  />
        </el-form-item>
        <el-form-item label="AccessKeyId"  required>
          <el-input />
        </el-form-item>
        <el-form-item
          label="AccessKeySecret"
          required
        >
          <el-input  />
        </el-form-item>
        <el-form-item label="短信签名 Sign"  required>
          <el-input />
        </el-form-item>
      </div>
      <!-- 七牛云配置 -->
      <div v-show="form.default === 'qiniu'">
        <el-form-item label="AccessKey" required>
          <el-input  />
        </el-form-item>
        <el-form-item label="SecretKey"  required>
          <el-input />
        </el-form-item>
      </div>

      <!-- 短信场景配置 -->
      <div v-for="(item, index) in record['scene']" :key="index">
        <el-divider orientation="left">{{ item.name }}</el-divider>
        <el-form-item label="是否开启" >
          <el-radio-group
            v-model="item.isEnable"
          >
            <el-radio :label="true">开启</el-radio>
            <el-radio :label="false">关闭</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="模板内容"  required>
          <span>{{ record.scene[index].contentPractical }}</span>
        </el-form-item>
        <el-form-item label="模板ID/Code"  required>
          <el-input  />
          <div class="form-item-help">
            <small>例如：SMS_139800030</small>
          </div>
        </el-form-item>
        <el-form-item
          v-if="record.scene[index].acceptPhone !== undefined"
          label="接收手机号"
          required
        >
          <el-input />
          <div class="form-item-help">
            <small>
              注：如需填写多个手机号，请用英文逗号
              <el-tag>,</el-tag>隔开
            </small>
          </div>
        </el-form-item>
      </div>
      <el-form-item >
        <el-button type="primary" html-type="submit">提交</el-button>
      </el-form-item>
    </el-form>
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

</style>
