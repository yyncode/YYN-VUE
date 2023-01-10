<template>
  <div class="transfer_wrap" :style="{ width, height }">
    <div class="trs_left">
      <div class="headers_left">
        <slot name="leftHeader">
          <el-checkbox
            v-show="showCheckAll"
            class="checkbox"
            v-model="leftcheckedAll"
            :disabled="leftDisabled"
            @change="changeLeftChecked"
          ></el-checkbox>
          <span :class="{ center: headerPosition === 'center' }">{{
              titles[0]
            }}</span>
          <span v-show="showCheckNum" class="check-num"
          >{{ leftCount.checkedNum }}/{{ leftCount.sum }}</span
          >
        </slot>
      </div>
      <div class="body_left">
        <slot name="leftSearch">
          <el-input
            v-if="search"
            v-model="filterLeft"
            prefix-icon="el-icon-search"
            class="search-input"
            :maxlength="inputLength"
            :placeholder="placeholder[0]"
            size="small"
            @input="(val) => searchKeyword(val, 'treeCheck')"
          ></el-input>
        </slot>
        <div class="el_tree_wrap">
          <el-scrollbar class="scroll-bar">
            <el-tree
              ref="treeCheck"
              v-bind="$attrs"
              v-on="$listeners"
              :node-key="nodeKey"
              :check-strictly="checkStrictly"
              :data="treeData"
              :props="props"
              :lazy="lazy"
              :load="load"
              :default-checked-keys="checkedArr"
              :filter-node-method="filterNode"
              :default-expanded-keys="expandedkeys"
              :allow-drag="allowDragDispose"
              :allow-drop="allowDropDispose"
              @node-drag-start="nodeDragStart"
              @node-drag-enter="nodeDragEnter"
              @node-drag-leave="nodeDragLeave"
              @node-drag-over="nodeDragOver"
              @node-drag-end="nodeDragEnd"
              @node-drop="nodeDrop"
              @check-change="checkChange"
              @node-click="
                (data, node, e) => handleNodeClick(data, node, e, 'treeCheck')
              "
            >
              <template #default="{ node,data }">
                <slot name="leftText" :node="node" :data="data">
                  <div :title="node.label" class="label-text">
                    {{ node.label }}
                  </div>
                </slot>
              </template>
            </el-tree>
          </el-scrollbar>
        </div>
      </div>
    </div>
    <div class="middle">
      <slot name="leftButton">
        <el-button
          type="primary"
          icon="el-icon-arrow-right"
          size="medium"
          :disabled="moveLeft"
          @click="rightMove"
        >{{ buttonTexts[0] }}
        </el-button>
      </slot>
      <slot name="rightButton">
        <el-button
          class="moveRight"
          type="primary"
          size="medium"
          icon="el-icon-arrow-left"
          :disabled="moveRight"
          @click="leftMove"
        >{{ buttonTexts[1] }}</el-button
        >
      </slot>
    </div>
    <div class="trs_right">
      <div class="headers_right">
        <slot name="rightHeader">
          <el-checkbox
            v-show="showCheckAll"
            class="checkbox"
            v-model="rightcheckedAll"
            @change="changeRightChecked"
          ></el-checkbox>
          <span :class="{ center: headerPosition === 'center' }">{{
              titles[1]
            }}</span>
          <span v-show="showCheckNum" class="check-num"
          >{{ rightCount.checkedNum }}/{{ rightCount.sum }}</span
          >
        </slot>
      </div>
      <div class="body_right">
        <slot name="rightSearch">
          <el-input
            v-if="search"
            v-model="filterRight"
            prefix-icon="el-icon-search"
            class="search-input"
            :maxlength="inputLength"
            :placeholder="placeholder[1]"
            size="small"
            @input="(val) => searchKeyword(val, 'treeChecked')"
          ></el-input>
        </slot>
        <div class="el_tree_wrap">
          <el-scrollbar class="scroll-bar">
            <el-tree
              ref="treeChecked"
              v-bind="$attrs"
              :node-key="nodeKey"
              :data="checkedNewData"
              :props="checkedProps"
              :filter-node-method="filterNode"
              @check-change="checkedChange"
              @node-click="
                (data, node, e) => handleNodeClick(data, node, e, 'treeChecked')
              "
            >
              <template #default="{ node,data }">
                <slot name="rightText" :node="node" :data="data">
                  <div :title="node.label" class="checked-label-text">
                    {{ node.label }}
                  </div>
                </slot>
              </template>
            </el-tree>
          </el-scrollbar>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import _ from "lodash";

export default {
  name: "TreeTransfer",
  props: {
    // 绑定值
    value: {
      type: Array,
      default: () => [],
    },
    //宽度
    width: {
      type: String,
      default: "100%",
    },
    //高度
    height: {
      type: String,
      default: "47vh",
    },
    //配置项
    props: {
      type: Object,
      default() {
        return {
          label: "label",
          children: "children",
          disabled: "disabled",
          value: "value",
          isLeaf: () => {},
        };
      },
    },
    //选中节点后是否禁用该节点
    checkedDisabled: {
      type: Boolean,
      default: true,
    },
    //自定义列表标题
    titles: {
      type: Array,
      default: () => ["列表1", "列表2"],
    },
    //自定义搜索提示文字
    placeholder: {
      type: Array,
      default: () => ["请输入内容", "请输入内容"],
    },
    //自定义按钮文案
    buttonTexts: {
      type: Array,
      default: () => ["", ""],
    },
    //是否显示全选框
    showCheckAll: {
      type: Boolean,
      default: false,
    },
    //表头是否显示总数以及勾选数量
    showCheckNum: {
      type: Boolean,
      default: false,
    },
    //表头标题显示位置
    headerPosition: {
      type: String,
      default: "left",
      validate: (str) => ["left", "center"].includes(str),
    },
    //默认勾选的节点数组 值为节点标识
    checkedKeys: {
      type: Array,
      default: () => [],
    },
    //是否可搜索
    search: {
      type: Boolean,
      default: false,
    },
    //搜索输入长度
    inputLength: {
      type: Number,
      default: 50,
    },
    //标识
    nodeKey: {
      type: String,
      default: "id",
      require: true,
    },
    //树形控件展示数据
    data: {
      type: Array,
      default: () => [],
    },
    //是否展示多选框
    checkedBox: {
      type: Boolean,
      default: true,
    },
    //是否遵循父子节点不关联
    checkStrictly: {
      type: Boolean,
      default: false,
    },
    //是否对底层节点添加点击节点本身选中当前节点
    nodeCheck: {
      type: Boolean,
      default: false,
    },
    //是否开启懒加载
    lazy: {
      type: Boolean,
      default: false,
    },
    //最大选中数量
    selectMax: {
      type: [String, Number],
      default: 50,
    },
    //是否不允许跨一级添加 默认允许
    isAcross: {
      type: Boolean,
      default: false,
    },
    //是否遵守父节点选中子节点禁用规则
    isSuperior: {
      type: Boolean,
      default: false,
    },
    //过滤上级节点，只保存最下级节点
    isMostJunior: {
      type: Boolean,
      default: false,
    },
    //默认展开到多少级 懒加载时需手动调用defaultExpand方法
    expandLevelKeys: {
      type: Object,
      default: () => {
        return {
          level: 0,
          key: "level",
        };
      },
    },
    //需开启拖拽功能 允许拖拽节点等级,默认1级
    draggableLevel: {
      type: [String, Number],
      default: 1,
    },
    // fun start
    //开启懒加载回调
    load: {
      type: Function,
      default() {},
    },
    //右侧节点树勾选发生变化时触发
    rightCheckChange: {
      type: Function,
      default() {},
    },
    //判断节点能否被拖拽
    allowDrag: {
      type: Function,
      default() {
        return true;
      },
    },
    //拖拽时判定目标节点能否被放置
    allowDrop: {
      type: Function,
      default() {
        return true;
      },
    },
    //节点开始拖拽时触发的事件
    nodeDragStart: {
      type: Function,
      default() {},
    },
    //拖拽进入其他节点时触发的事件
    nodeDragEnter: {
      type: Function,
      default() {},
    },
    //拖拽离开某个节点时触发的事件
    nodeDragLeave: {
      type: Function,
      default() {},
    },
    //在拖拽节点时触发的事件（类似浏览器的 mouseover 事件）
    nodeDragOver: {
      type: Function,
      default() {},
    },
    //拖拽结束时（可能未成功）触发的事件
    nodeDragEnd: {
      type: Function,
      default() {},
    },
    //拖拽成功完成时触发的事件
    nodeDrop: {
      type: Function,
      default() {},
    },
    //fun end
  },
  data() {
    return {
      filterLeft: "", //左侧穿梭框搜索关键字
      filterLData: [], //左搜索匹配数据
      filterRight: "", //右侧穿梭框搜索关键字
      filterRData: [], //右搜索匹配数据
      checkedData: [], //选中的数据 引用关联
      checkedNewData: [], //选中的数据 无引用关联
      expandedkeys: [], //默认展开的节点
      leftcheckedAll: false, //左侧全选状态
      rightcheckedAll: false, //右侧全选状态
      moveLeft: true,
      moveRight: true,
      checkedProps: {
        label: this.props.label,
        children: "child" || this.props.children,
        disabled: this.props.disabled,
      },
      leftCheckedData: [],
      rightCheckedData: [],
      parentList: [],
      leftCount: {
        sum: 0,
        checkedNum: 0,
      },
      rightCount: {
        sum: 0,
        checkedNum: 0,
      },
      resetData: [],
      treeData: [],
      checkedArr: [],
      leftDisabled: false,
    };
  },
  watch: {
    "leftCount.checkedNum": {
      handler(val) {
        this.leftcheckedAll = val === this.leftCount.sum;
      },
    },
    "rightCount.checkedNum": {
      handler(val) {
        this.rightcheckedAll = val === this.rightCount.sum && val !== 0;
      },
    },
    leftCheckedData: {
      handler(val) {
        this.moveLeft = val.length ? false : true;
      },
    },
    rightCheckedData: {
      handler(val) {
        this.moveRight = val.length ? false : true;
      },
    },
    data: {
      handler(arr) {
        this.treeData = arr;
        this.resetData = _.cloneDeep(this.treeData);
        this.defaultValues();
      },
      immediate: true,
    },
    checkedKeys: {
      handler(arr) {
        this.checkedArr = arr;
      },
      immediate: true,
      deep: true,
    },
    value: {
      handler(val) {
        if (Array.isArray(val)) {
          const { $refs, checkStrictly, checkedData, props } = this;
          checkedData.forEach((_) =>
            $refs.treeChecked.setChecked(_, true, !checkStrictly)
          );
          this.checkedNewData.forEach((item) => {
            $refs.treeCheck.setChecked(item, false, !checkStrictly);
            $refs.treeCheck.getNode(item).data[props.disabled] = false;
          });
          this.checkedNewData = [];
          this.defaultValues();
          this.$nextTick(() => {
            this.leftDisabled =
              this.leftCount.sum === this.leftCount.checkedNum;
          });
        }
      },
    },
  },
  mounted() {
    this.lazy || this.init();
    this.leftCount.sum = this.changeTreeChecked(false, "left", this.treeData);
    this.setTreeMethods();
  },
  methods: {
    /**
     * @description: 回显选中
     */
    defaultValues() {
      const { value, checkStrictly, lazy, data } = this;
      (value || []).forEach((v, k) => {
        this.$nextTick(() => {
          this.$refs.treeCheck?.setChecked(v, true, !checkStrictly);
          if (k === value?.length - 1) {
            // 此时不需要更新value，避免进入死循环
            !lazy && data?.length && this.rightMove(false);
          }
        });
      });
    },
    /**
     * @description: 清除搜索框
     * @param { String } key all清除全部，left左侧，right右侧，默认清除全部
     */
    clearSearch(key = "all") {
      const filterKeys = {
        all: "filterLeft,filterRight",
        left: "filterLeft",
        right: "filterRight",
      };
      filterKeys[key].split(",").forEach((k) => {
        this[k] = "";
        this.searchKeyword(
          "",
          k === "filterLeft" ? "treeCheck" : "treeChecked"
        );
      });
    },
    /**
     * @description: 载入tree组件方法
     */
    setTreeMethods() {
      const { $refs } = this;
      Object.entries($refs.treeCheck).forEach(([key, value]) => {
        if (Object.prototype.toString.call(value) === "[object Function]")
          this[key] || (this[key] = value);
      });
    },
    /**
     * @description: 初始化调用
     */
    init() {
      this.expandLevelKeys.level && this.defaultExpand(this.treeData);
    },
    /**
     * @description: 设置默认展开几级tree
     * 懒加载时需调用此方法，并传入加载的数据，数据庞大时不推荐
     */
    defaultExpand(tree) {
      const expand = this.expandLevelKeys;
      tree &&
      tree.map((item) => {
        if (item[expand.key] <= expand.level) {
          this.expandedkeys.push(item[this.nodeKey]);
          this.defaultExpand(item[this.props.children]);
        }
      });
    },
    /**
     * @description: 左侧全选发生变化回调
     */
    changeLeftChecked(type) {
      this.changeTreeChecked(type, "left", this.treeData);
    },
    /**
     * @description: 右侧全选发生变化回调
     */
    changeRightChecked(type) {
      this.changeTreeChecked(type, "right", this.checkedNewData);
    },
    /**
     * @description: 选中状态切换
     * @param { Boolean } checked 选中与否
     * @param { String } flag 类型 left 左边 right右边树
     * @return { Number } 总数
     */
    changeTreeChecked(checked = true, flag = "left") {
      let treeData = flag == "left" ? this.treeData : this.checkedNewData,
        props = this.props,
        sum = 0;
      const result = () => {
        const mapTree = (checked, data, flag) => {
          let refName = flag == "left" ? "treeCheck" : "treeChecked";
          data.forEach((item) => {
            let node = this.$refs[refName].getNode(item);
            sum++;
            !item[props.disabled] && (node.checked = checked);
            if (item[props.children] && item[props.children].length) {
              mapTree.bind(this, checked, item[props.children], flag)();
            }
          });
          return sum;
        };
        return mapTree.bind(this, checked, treeData, flag)();
      };
      return result.call(this);
    },
    /**
     * @description: 设置右侧树选中个数及总数
     */
    getRightCheckedAndSum() {
      this.rightCount.checkedNum = this.$refs.treeChecked
        .getCheckedNodes()
        .filter((_) => !_[this.props.disabled]).length;
      this.rightCount.sum = this.checkedNewData.length;
    },
    /**
     * @description: 搜索过滤函数
     * @param {*} value
     * @param {*} data
     */
    filterNode(value, data) {
      if (!value) return true;
      return data[this.props.label].indexOf(value) !== -1;
    },
    /**
     * @description: 穿梭框树形结构搜索
     * @param {*} val 搜索关键词
     * @param {*} ref 组件标识
     */
    searchKeyword(val = "", ref = "treeCheck") {
      this.$refs[ref].filter(val);
    },
    /**
     * @description: node点击选中状态切换
     */
    handleNodeClick(data, node, e, name) {
      this.nodeCheck &&
      !node.disabled &&
      node.isLeaf &&
      this.$refs[name].setChecked(data, !node.checked);
      this.$emit("node-click", {
        data,
        node,
        e,
      });
    },
    /**
     * @description: 添加已选择data
     * @params { boolean } isUpdateValue 是否更新value
     */
    rightMove(isUpdateValue = true) {
      this.parentList = [];
      this.checkedData = this.$refs.treeCheck.getCheckedNodes();
      let newArr = _.cloneDeep(this.checkedData);
      //懒加载下 保存最下级
      if (this.isMostJunior) {
        newArr = newArr.filter((item) => {
          if (this.lazy) return item.leaf === true;
          else return !item[this.props.children];
        });
      }
      //限制选择数量
      if (newArr.length > this.selectMax)
        return this.$message.warning(`允许最大选择数量为：${this.selectMax}个`);
      //限制跨级选择
      if (this.isAcross) {
        this.getParentList(
          newArr.map((item) => this.$refs.treeCheck.getNode(item))
        );
        if (this.parentList.length > 1)
          return this.$message.warning(`不允许跨一级添加，请核查后再试`);
      }
      this.leftCheckedData = [];
      newArr.map((item) => (item[this.props.disabled] = false));
      this.checkedNewData = _.cloneDeep(newArr);
      //默认勾选禁用
      this.checkedArr = this.checkedNewData.map((item) => item[this.nodeKey]);
      this.checkedDisabled &&
      this.checkedData.map((item, index) => {
        this.$set(this.checkedData[index], this.props.disabled, true);
      });
      isUpdateValue && this.getChekcedNode();
      this.getRightCheckedAndSum();
      this.leftDisabled = this.leftCount.sum === this.leftCount.checkedNum;
      this.clearKeyWord();
    },
    /**
     * @description: 移除已选中
     * @params { boolean } isUpdateValue 是否更新value
     */
    leftMove(isUpdateValue = true) {
      const { props } = this;
      this.rightCheckedData = [];
      const reChecked = this.$refs.treeChecked.getCheckedNodes();
      //移除已选
      reChecked.map((checkedItem) => {
        this.checkedNewData.map(
          (v, k) =>
            checkedItem[props.value] == v[props.value] &&
            this.checkedNewData.splice(k, 1)
        );
        this.checkedArr.map((v, k) => {
          if (v == checkedItem[this.nodeKey]) {
            this.$refs.treeCheck.setChecked(v, false);
            if (!this.checkStrictly) {
              this.isMostJunior &&
              this.enableOrDisableParent([checkedItem], false);
            }
            this.checkedArr.splice(k, 1);
          }
        });
      });
      //移除数据重置
      this.checkedData.map((item, index) =>
        reChecked.map((v) => {
          if (item[props.value] == v[props.value]) {
            let node = this.$refs.treeCheck.getNode(this.checkedData[index]);
            !this.checkStrictly && this.parentNodeStart(node);
            this.checkedDisabled &&
            this.$set(this.checkedData[index], this.props.disabled, false);
            this.isSuperior && this.mapTreeByChild([item], "cancel");
            if (v.leaf) {
              const parent = this.$refs.treeCheck.getNode(v).parent;
              parent.data.disabled = false;
            }
          }
        })
      );
      this.checkedNewData = this.$refs.treeCheck
        .getCheckedNodes()
        .map((checked) => {
          checked = Object.assign({}, checked);
          checked[props.disabled] = false;
          if (this.isMostJunior) {
            if (this.lazy) {
              if (checked.leaf === true) return checked;
            } else {
              if (!checked[props.children]) {
                return checked;
              }
            }
          } else {
            return checked;
          }
        })
        .filter(Boolean);
      this.$refs.treeChecked.setCheckedKeys([]);
      isUpdateValue && this.getChekcedNode();
      this.getRightCheckedAndSum();
      this.clearKeyWord();
      this.$nextTick(() => {
        this.leftDisabled = this.leftCount.sum === this.leftCount.checkedNum;
      });
    },
    /**
     * @description: 清除搜索条件
     */
    clearKeyWord() {
      this.filterLeft = this.filterRight = "";
      ["treeChecked", "treeCheck"].forEach((ref) =>
        this.searchKeyword("", ref)
      );
    },
    /**
     * @description: 启用当前节点所有父节点选中功能
     * @param {*} parentNode 父节点node
     */
    parentNodeStart(node) {
      let parentNode = node.parent;
      if (parentNode && parentNode.label) {
        parentNode.data[this.props.disabled] = false;
        this.parentNodeStart(parentNode);
      }
    },
    /**
     * @description: 关联时子节点全未选中时，父级节点取消选中
     * @param {*} node 节点node
     */
    mapTreeByChildNode(node) {
      let parentNode = node.parent,
        props = this.props;
      if (parentNode && parentNode.label) {
        let checkedChildNode = parentNode.childNodes.filter(
          (child) => child.checked
        );
        if (!checkedChildNode.length) {
          this.checkedNewData.forEach((checkedItem, checkedIndex) => {
            if (checkedItem[props.value] == parentNode.data[props.value])
              this.checkedNewData.splice(checkedIndex, 1);
          });
          parentNode.checked = false;
          parentNode.data[props.disabled] = false;
          this.mapTreeByChildNode(parentNode);
        }
      }
    },
    /**
     * @description: 右侧节点选中状态发生变化时的回调
     */
    checkedChange(data, isChecked) {
      let props = this.props;
      if (isChecked) {
        this.rightCheckedData = [...this.rightCheckedData, data];
      } else {
        this.rightCheckedData.length &&
        this.rightCheckedData.map((item, index) => {
          if (item[props.label] == data[props.label])
            this.rightCheckedData.splice(index, 1);
        });
      }
      this.getRightCheckedAndSum();
    },
    /**
     * @description: 获取已选中节点, 更新当前绑定值
     * @param {*}
     */
    getChekcedNode() {
      this.rightCheckChange(
        this.checkedNewData.map((item) => item[this.nodeKey])
      );
      this.$emit(
        "input",
        this.checkedNewData.map((item) => item[this.nodeKey])
      );
    },
    /**
     * @description: 左侧节点选中状态发生变化时的回调
     */
    checkChange(data, isChecked) {
      let props = this.props;
      this.leftCount.checkedNum = this.$refs.treeCheck.getCheckedNodes().length;
      if (isChecked) {
        this.leftCheckedData = [...this.leftCheckedData, data];
        this.isSuperior && this.mapTreeByChild(data[props.children], "checked");
      } else {
        this.isSuperior &&
        this.mapTreeByChild(data[props.children], "cancel", true);
        this.leftCheckedData.length &&
        this.leftCheckedData.map((item, index) => {
          if (item[props.label] == data[props.label])
            this.leftCheckedData.splice(index, 1);
        });
      }
    },
    /**
     * @description: 启用|禁用父级选中功能
     * @param { Object | Array | string } data 启用的data 或者 key
     * @param { Boolean } type true禁用 false启用
     * @param { Boolean } isAll 是否递归启用所有父级
     */
    enableOrDisableParent(data, type = false, isAll = false) {
      const { props } = this;
      const parentNode = this.$refs.treeCheck.getNode(
        Array.isArray(data) && data[0] ? data[0] : data
      ).parent;
      parentNode.data[props.disabled] = type;
      if (isAll) {
        try {
          // 递归进行启用禁用
          const mapParent = (node) => {
            if (node.parent && node.parent.data instanceof Object) {
              node.parent.data[props.disabled] = type;
              mapParent(node.parent);
            }
          };
          mapParent(parentNode);
        } catch (error) {
          // todo
        }
      }
    },
    /**
     * @description: 启用|禁用节点
     * @param { Object | Array | string } data 启用的data 或者 key
     * @param { Boolean } type  true启用 false禁用
     */
    enableOrDisableNode(data, type = true) {
      const { props } = this;
      const node = this.$refs.treeCheck.getNode(
        Array.isArray(data) && data[0] ? data[0] : data
      );
      node.data[props.disabled] = type;
    },
    /**
     * @description: 通过 key / data 设置某个节点的勾选状态，使用此方法必须设置 node-key 属性
     * @data { Array } 勾选节点的 key 或者 data
     * @check { Boolean } 节点是否选中
     * @child { Boolean } 是否设置子节点 ，默认为 false
     */
    setChecked(data, check, child) {
      if (data && !data.length) return;
      data.map((item) => this.$refs.treeCheck.setChecked(item, check, child));
    },
    /**
     * @description: 获取已选中数据方法
     * @return { Array }
     */
    getChecked() {
      const checked = this.$refs.treeChecked.getCheckedNodes();
      return ((checked.length && checked) || this.checkedNewData).map((item) =>
        this.$refs.treeCheck.getNode(item)
      );
    },
    /**
     * @description: 清除选中节点，默认清除全部
     * @param { string } type left左边 right右边 all全部
     * @param { Boolean } 是否清除已选择的数据
     * @return {*}
     */
    clearChecked(type = "all", clearChecked = true) {
      let clearRefsName = ["treeCheck", "treeChecked"];
      type == "left" && (clearRefsName = ["treeCheck"]);
      type == "right" && (clearRefsName = ["treeChecked"]);
      clearRefsName.forEach((item) => {
        this.$refs[item].setCheckedKeys([]);
        if (item == "treeCheck") {
          this.mapTreeByChild(this.treeData, "cancel");
          clearChecked && (this.checkedNewData = []);
        }
      });
    },
    /**
     * @description: 通过 keys 设置节点子元素，使用此方法必须设置 node-key 属性
     * @param {*} key 节点 key
     * @param {*} data 节点数据的数组
     */
    updateKeyChildren(key, data) {
      this.$refs.treeCheck.updateKeyChildren(key, data);
    },
    /**
     * @description: 当前节点下所有节点 递归启用/禁用
     * @param { Array } data 节点数组
     * @param { String } type 禁用 checked 启用 其他
     * @param { Boolean } checked 是否取消选中
     */
    mapTreeByChild(data, type = "checked", checked = false) {
      let props = this.props;
      data &&
      data.map((item) => {
        item[props.disabled] = type == "checked" ? true : false;
        if (checked) {
          this.$refs.treeCheck.getNode(item).checked = false;
        }
        if (item[props.children] && item[props.children].length > 0)
          this.mapTreeByChild(item[props.children], type);
      });
    },
    /**
     * @description: 递归获取顶级标识
     * @param { Array } nodeList
     */
    getParentList(nodeList) {
      nodeList &&
      nodeList.forEach((node) => {
        if (
          node.level == 1 &&
          !this.parentList.includes(node.data[this.nodeKey])
        )
          this.parentList = [...this.parentList, node.data[this.nodeKey]];
        else {
          node.level && this.getParentList([node.parent]);
        }
      });
    },
    /**
     * @description: 判断节点能否被拖拽
     * @param {*} node 当前节点
     */
    allowDragDispose(node = {}) {
      if (node.level < this.draggableLevel) return false;
      return this.allowDrag(node);
    },
    /**
     * @description: 拖拽时判定目标节点能否被放置
     * @param {*} draggingNode 拖拽node
     * @param {*} dropNode
     * @param {*} type
     * @return {*}
     */
    allowDropDispose(draggingNode, dropNode, type) {
      if (draggingNode.level < this.draggableLevel) return false;
      return this.allowDrop(draggingNode, dropNode, type);
    },
  },
};
</script>

<style scoped>
.transfer_wrap {
  display: flex;
  justify-content: space-between;
}
.transfer_wrap .scroll-bar {
  height: 100%;
}
.transfer_wrap .el-scrollbar__wrap {
  overflow: auto;
}
.transfer_wrap .label-text,
.transfer_wrap .checked-label-text {
  max-width: 150px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.transfer_wrap .checked-label-text {
  max-width: 200px;
}
.transfer_wrap .moveRight {
  margin: 0 !important;
}
.transfer_wrap ::v-deep .el-tree {
  min-width: 100%;
  display: inline-block !important;
}
.transfer_wrap ::v-deep .el-tree-node__label {
  width: 150px;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.transfer_wrap .trs_left,
.transfer_wrap .trs_right {
  width: 45%;
  border: 1px solid #ebeef5;
}
.transfer_wrap .trs_left .body_left,
.transfer_wrap .trs_left .body_right,
.transfer_wrap .trs_right .body_left,
.transfer_wrap .trs_right .body_right {
  height: 44vh;
  padding: 20px;
  box-sizing: border-box;
  border-top: none;
}
.transfer_wrap .trs_left .body_left .el_tree_wrap,
.transfer_wrap .trs_left .body_right .el_tree_wrap,
.transfer_wrap .trs_right .body_left .el_tree_wrap,
.transfer_wrap .trs_right .body_right .el_tree_wrap {
  width: 100%;
  height: calc(100% - 60px);
}
.search-input{
  margin-bottom: 20px;
}
.transfer_wrap .trs_left .headers_left,
.transfer_wrap .trs_left .headers_right,
.transfer_wrap .trs_right .headers_left,
.transfer_wrap .trs_right .headers_right {
  position: relative;
  width: 100%;
  height: 40px;
  line-height: 40px;
  background: #f5f7fa;
  margin: 0;
  padding: 0 20px;
  border-bottom: 1px solid #ebeef5;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  color: #000;
  font-size: 14px;
}
.transfer_wrap .trs_left .headers_left .check-num,
.transfer_wrap .trs_left .headers_right .check-num,
.transfer_wrap .trs_right .headers_left .check-num,
.transfer_wrap .trs_right .headers_right .check-num {
  color: #909399;
  position: absolute;
  right: 20px;
}
.transfer_wrap .trs_left .headers_left .checkbox,
.transfer_wrap .trs_left .headers_right .checkbox,
.transfer_wrap .trs_right .headers_left .checkbox,
.transfer_wrap .trs_right .headers_right .checkbox {
  margin-right: 5px;
}
.transfer_wrap .trs_left .center,
.transfer_wrap .trs_right .center {
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}
.transfer_wrap .middle {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  padding: 0 10px;
}
.transfer_wrap .middle .add,
.transfer_wrap .middle .remove {
  width: 40px;
  height: 40px;
  border-radius: 4px;
  background-color: #a0cfff;
  color: #fff;
  text-align: center;
  line-height: 40px;
}
.transfer_wrap .middle .add:hover,
.transfer_wrap .middle .remove:hover {
  background-color: #409eff;
}

</style>
