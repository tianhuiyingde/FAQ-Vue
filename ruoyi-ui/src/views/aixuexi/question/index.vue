<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--业务模块数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="servicemoduleName"
            placeholder="请输入业务模块名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="servicemoduleOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>

      <!--问题数据-->
      <el-col :span="20" :xs="24">

        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="问题id" prop="questionId">
        <el-input
          v-model="queryParams.questionId"
          placeholder="请输入问题id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务模块id" prop="servicemoduleId">
        <el-input
          v-model="queryParams.servicemoduleId"
          placeholder="请输入业务模块id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="问题概述" prop="questionDesc">
        <el-input
          v-model="queryParams.questionDesc"
          placeholder="请输入问题概述"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="问题状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择问题状态" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

        <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['aixuexi:question:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['aixuexi:question:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['aixuexi:question:remove']"
        >删除</el-button>
      </el-col>
          <el-col :span="1.5">
            <el-button
              type="info"
              plain
              icon="el-icon-upload2"
              size="mini"
              @click="handleImport"
              v-hasPermi="['aixuexi:question:import']"
            >导入</el-button>
          </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
		  :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['aixuexi:question:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

        <el-table v-loading="loading" :data="questionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="问题id" align="center" prop="questionId" />
      <el-table-column label="业务模块id" align="center" prop="servicemoduleId" />
      <el-table-column label="问题概述" align="center" prop="questionDesc" />
      <el-table-column label="问题答案" align="center" prop="questionAnswer" />
      <el-table-column label="问题状态" align="center" prop="status" />
      <el-table-column label="问题关键字" align="center" prop="questionKey" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['aixuexi:question:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['aixuexi:question:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

        <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
      </el-col>
    </el-row>

    <!-- 添加或修改问题对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="业务模块id" prop="servicemoduleId">
          <el-input v-model="form.servicemoduleId" placeholder="请输入业务模块id" />
        </el-form-item>
        <el-form-item label="问题概述" prop="questionDesc">
          <el-input v-model="form.questionDesc" placeholder="请输入问题概述" />
        </el-form-item>
        <el-form-item label="问题答案" prop="questionAnswer">
          <el-input v-model="form.questionAnswer" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="问题状态">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="问题关键字" prop="questionKey">
          <el-input v-model="form.questionKey" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 问题导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <div class="el-upload__tip" slot="tip">
          <el-checkbox v-model="upload.updateSupport" />是否更新已经存在的问题数据
          <el-link type="info" style="font-size:12px" @click="importTemplate">下载模板</el-link>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入“xls”或“xlsx”格式文件！</div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listQuestion, getQuestion, delQuestion, addQuestion, updateQuestion, exportQuestion } from "@/api/aixuexi/question";
import { treeselect } from "@/api/aixuexi/servicemodule";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Question",
  components: { Treeselect},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 问题表格数据
      questionList: [],
      // 弹出层标题
      title: "",
      // 部门树选项
      servicemoduleOptions: undefined,
      // 部门名称
      servicemoduleName: undefined,
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        questionId: null,
        servicemoduleId: null,
        questionDesc: null,
        questionAnswer: null,
        status: null,
        questionKey: null,
      },
      // 表单参数
      form: {},
      defaultProps: {
          children: "children",
          label: "label"
      },
        // 用户导入参数
        upload: {
            // 是否显示弹出层（用户导入）
            open: false,
            // 弹出层标题（用户导入）
            title: "",
            // 是否禁用上传
            isUploading: false,
            // 是否更新已经存在的用户数据
            updateSupport: 0,
            // 设置上传的请求头部
            // headers: { Authorization: "Bearer " + getToken() },
            // 上传的地址
            url: process.env.VUE_APP_BASE_API + "/aixuexi/question/importData"
        },
      // 表单校验
      rules: {
        servicemoduleId: [
          { required: true, message: "业务模块id不能为空", trigger: "blur" }
        ],
      }
    };
  },
  watch: {
        // 根据名称筛选部门树
      servicemoduleName(val) {
            this.$refs.tree.filter(val);
        }
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询问题列表 */
    getList() {
      this.loading = true;
      listQuestion(this.queryParams).then(response => {
        this.questionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
      /** 查询业务模块下拉树结构 */
      getTreeselect() {
          treeselect().then(response => {
              this.servicemoduleOptions = response.data;
          });
      },
      // 筛选节点
      filterNode(value, data) {
          if (!value) return true;
          return data.label.indexOf(value) !== -1;
      },
      // 节点单击事件
      handleNodeClick(data) {
          this.queryParams.servicemoduleId = data.id;
          this.getList();
      },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        questionId: null,
        servicemoduleId: null,
        questionDesc: null,
        questionAnswer: null,
        status: "0",
        questionKey: null,
        remark: null,
        delFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.questionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加问题";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const questionId = row.questionId || this.ids
      getQuestion(questionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改问题";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.questionId != null) {
            updateQuestion(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQuestion(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const questionIds = row.questionId || this.ids;
      this.$confirm('是否确认删除问题编号为"' + questionIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delQuestion(questionIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有问题数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportQuestion(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    },
      /** 导入按钮操作 */
      handleImport() {
          this.upload.title = "问题导入";
          this.upload.open = true;
      },
      /** 下载模板操作 */
      importTemplate() {
          importTemplate().then(response => {
              this.download(response.msg);
          });
      },
      // 文件上传中处理
      handleFileUploadProgress(event, file, fileList) {
          this.upload.isUploading = true;
      },
      // 文件上传成功处理
      handleFileSuccess(response, file, fileList) {
          this.upload.open = false;
          this.upload.isUploading = false;
          this.$refs.upload.clearFiles();
          this.$alert(response.msg, "导入结果", { dangerouslyUseHTMLString: true });
          this.getList();
      },
      // 提交上传文件
      submitFileForm() {
          this.$refs.upload.submit();
      }
  }
};
</script>
