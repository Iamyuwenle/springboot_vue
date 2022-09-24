<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="身份证" prop="identity">
        <el-input
          v-model="queryParams.identity"
          placeholder="请输入身份证"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="customerName">
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select @change="handleQuery" v-model="queryParams.sex" placeholder="请选择性别" clearable size="small">
          <el-option
            v-for="dict in dict.type.sys_user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职位" prop="job">
        <el-input
          v-model="queryParams.job"
          placeholder="请输入职位"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="部门" prop="deptId">
        <el-select @change="handleQuery" v-model="queryParams.deptId" placeholder="请选择部门" clearable size="small">
          <el-option
            v-for="dept in depts"
            :key="dept.deptId"
            :label="dept.deptName"
            :value="dept.deptId"
          />
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
          v-hasPermi="['business:customer:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['business:customer:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['business:customer:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['business:customer:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" border :data="customerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="编号" align="center" property="id"/>
      <el-table-column label="身份证" width="200px" align="center" prop="identity"/>
      <el-table-column label="姓名" align="center" prop="customerName"/>
      <el-table-column label="性别" align="center" prop="sex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex"/>
        </template>
      </el-table-column>
      <el-table-column label="地址" align="center" prop="address"/>
      <el-table-column label="电话" align="center" prop="phone"/>
      <el-table-column label="所属分公司" align="center" prop="deptName"/>
      <el-table-column label="职位" align="center" prop="job"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['business:customer:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['business:customer:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改客户管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" :close-on-click-modal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="身份证" prop="identity">
          <el-input v-model="form.identity" placeholder="请输入身份证"/>
        </el-form-item>
        <el-form-item label="姓名" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入姓名"/>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>

        <!--  所属部门-->
        <el-form-item label="部门" prop="deptId">
          <el-select v-model="form.deptId" placeholder="请选择部门">
            <el-option
              v-for="dept in depts"
              :key="dept.deptId"
              :label="dept.deptName"
              :value="dept.deptId"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址"/>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入电话"/>
        </el-form-item>


        <el-form-item label="职位" prop="job">
          <el-input v-model="form.job" placeholder="请输入职位"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {listCustomer, getCustomer, delCustomer, addCustomer, updateCustomer} from "@/api/business/customer";
  import {listDepts} from "@/api/business/dept";

  export default {
    name: "Customer",
    dicts: ['sys_user_sex'],
    data() {
      return {
        // 遮罩层
        loading: false,
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
        // 客户管理表格数据
        customerList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          identity: null,  //身份证
          customerName: null, //客户名称
          sex: null, //性别
          address: null, //地址
          phone: null, //电话
          deptName: null,  //部门名称
          deptId: null,  //部门Id
          job: null, //职位
        },
        depts: [], //部门的数组
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          identity: [
            {required: true, message: "身份证不能为空", trigger: "blur"}
          ],
          customerName: [
            {required: true, message: "姓名不能为空", trigger: "blur"}
          ],
          sex: [
            {required: true, message: "性别不能为空"}
          ],
        }
      };
    },

    //初始化客户信息
    created() {
      this.getAllDept();//查询所有部门
      this.getList();  //查寻所有客户信息
    },
    methods: {
      /** 查询客户管理列表 */
      getList() {
        this.loading = true;
        listCustomer(this.queryParams).then(response => {

          this.customerList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },

      //查询所有部门的信息
      getAllDept() {
        listDepts().then(resp => {
          console.log(resp)
          this.depts = resp.data;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          identity: null,
          customerName: null,
          sex: null,
          address: null,
          phone: null,
          deptName: null,
          deptId: null,
          job: null,
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
        this.handleQuery();  //查询所有数据
      },
      // 多选框选中数据
      handleSelectionChange(selection) {

        let that = this;
        this.ids = selection.map(item => item.id)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        //清空对话框数据
        this.reset();
        //打开弹出层
        this.open = true;
        //对话框的标题
        this.title = "添加客户";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        console.log(row)
        this.reset();
        const id = row.id || this.ids
        getCustomer(id).then(response => {
          this.form = response.data;
          //this.form.sex = this.form.sex+'';
          this.open = true;
          this.title = "修改客户管理";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.deptId) {
              this.depts.filter(dept => {
                //如果查询的结果集中的部门id和添加的部门id相等
                if (this.form.deptId == dept.deptId) {
                  // 把查询的部门名称赋值给需要添加的部门名称
                  this.form.deptName = dept.deptName;
                }
              });
            }


            if (this.form.id != null) {
              updateCustomer(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addCustomer(this.form).then(response => {
                this.$message.success("新增成功");
                this.open = false; //关闭弹出层
                this.getList(); //查询客户信息
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        console.log(row)
        const ids = row.id || this.ids;
        this.$modal.confirm('是否确认删除客户管理编号为"' + ids + '"的数据项？').then(function () {
          return delCustomer(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('business/customer/export', {
          ...this.queryParams
        }, `customer_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
