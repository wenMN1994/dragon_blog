<template>
  <el-dialog
    title="上传文件"
    :close-on-click-modal="false"
    @close="closeHandle"
    :visible.sync="visible">
    <el-upload
      drag
      action="http://blog-dragon.oss-cn-shenzhen.aliyuncs.com"
      :data="dataObj"
      :before-upload="beforeUpload"
      :on-success="handleUploadSuccess"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :file-list="fileList"
      list-type="picture"
      multiple
      style="text-align: center;">
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      <div class="el-upload__tip" slot="tip">只支持jpg、png、gif格式的图片！</div>
    </el-upload>
  </el-dialog>
</template>

<script>
  import { policy } from '@/components/upload/policy'
  import { getUUID } from '@/utils'
  export default {
    data () {
      return {
        dataObj: {
          policy: '',
          signature: '',
          key: '',
          ossaccessKeyId: '',
          dir: '',
          host: '',
          uuid: ''
        },
        visible: false,
        num: 0,
        successNum: 0,
        fileList: []
      }
    },
    computed: {
      fileList () {
        let fileList = []
        for (let i = 0; i < this.value.length; i++) {
          fileList.push({ url: this.value[i] })
        }
        return fileList
      }
    },
    methods: {
      init (id) {
        this.visible = true
      },
      emitInput (fileList) {
        let value = []
        for (let i = 0; i < fileList.length; i++) {
          value.push(fileList[i].url)
        }
        this.$emit("input", value)
      },
      handleRemove (file, fileList) {
        this.emitInput(fileList)
      },
      handlePreview (file) {
        console.log(file)
      },
      // 上传之前
      beforeUpload (file) {
        if (file.type !== 'image/jpg' && file.type !== 'image/jpeg' && file.type !== 'image/png' && file.type !== 'image/gif') {
          this.$message.error('只支持jpg、png、gif格式的图片！')
          return false
        }
        this.num++
        let _self = this
        return new Promise((resolve, reject) => {
          policy()
            .then(response => {
              _self.dataObj.policy = response.data.policy
              _self.dataObj.signature = response.data.signature
              _self.dataObj.ossaccessKeyId = response.data.accessid
              _self.dataObj.key = response.data.dir + getUUID() + '_${filename}'
              _self.dataObj.dir = response.data.dir
              _self.dataObj.host = response.data.host
              resolve(true)
            })
            .catch(err => {
              console.log('出错了...', err)
              reject(new Error(false))
            })
        })
      },
      // 上传成功
      handleUploadSuccess (response, file, fileList) {
        console.log(fileList)
        this.fileList.push({
          name: file.name,
          // url: this.dataObj.host + "/" + this.dataObj.dir + "/" + file.name； 替换${filename}为真正的文件名
          url: this.dataObj.host + "/" + this.dataObj.key.replace("${filename}", file.name)
        })
        this.emitInput(this.fileList)
        this.successNum++
        if (response && response.code === 0) {
          if (this.num === this.successNum) {
            this.$confirm('操作成功, 是否继续操作?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).catch(() => {
              this.visible = false
            })
          }
        } else {
          this.$message.error(response.msg)
        }
      },
      // 弹窗关闭时
      closeHandle () {
        this.fileList = []
        this.$emit('refreshDataList')
      }
    }
  }
</script>
