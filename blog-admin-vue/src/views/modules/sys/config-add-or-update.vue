<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="100px">
      <el-form-item label="所属系统" prop="paramBelongToSystem">
        <el-input v-model="dataForm.paramBelongToSystem" placeholder="所属系统"></el-input>
      </el-form-item>
      <el-form-item label="应用功能" prop="paramFunction">
        <el-input v-model="dataForm.paramFunction" placeholder="应用功能"></el-input>
      </el-form-item>
      <el-form-item label="参数键" prop="paramKey">
        <el-input v-model="dataForm.paramKey" placeholder="参数键"></el-input>
      </el-form-item>
      <el-form-item label="参数值" prop="paramValue">
        <el-input v-model="dataForm.paramValue" placeholder="参数值"></el-input>
      </el-form-item>
      <el-form-item label="应用父级键" prop="parentParamKey">
        <el-input v-model="dataForm.parentParamKey" placeholder="应用父级键"></el-input>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="dataForm.remark" placeholder="备注"></el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          paramBelongToSystem: '',
          paramFunction: '',
          paramKey: '',
          paramValue: '',
          parentParamKey: '',
          remark: ''
        },
        dataRule: {
          paramBelongToSystem: [
            { required: true, message: '所属系统不能为空', trigger: 'blur' }
          ],
          paramFunction: [
            { required: true, message: '应用功能不能为空', trigger: 'blur' }
          ],
          paramKey: [
            { required: true, message: '参数键不能为空', trigger: 'blur' }
          ],
          paramValue: [
            { required: true, message: '参数值不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/sys/config/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.paramBelongToSystem = data.config.paramBelongToSystem
                this.dataForm.paramFunction = data.config.paramFunction
                this.dataForm.paramKey = data.config.paramKey
                this.dataForm.paramValue = data.config.paramValue
                this.dataForm.parentParamKey = data.config.parentParamKey
                this.dataForm.remark = data.config.remark
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/sys/config/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'paramBelongToSystem': this.dataForm.paramBelongToSystem,
                'paramFunction': this.dataForm.paramFunction,
                'paramKey': this.dataForm.paramKey,
                'paramValue': this.dataForm.paramValue,
                'parentParamKey': this.dataForm.parentParamKey,
                'remark': this.dataForm.remark
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
