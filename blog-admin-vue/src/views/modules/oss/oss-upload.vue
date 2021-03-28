<template>
  <el-dialog
    title="上传文件"
    :close-on-click-modal="false"
    @close="closeHandle"
    :visible.sync="visible">
    <el-upload
      action="http://blog-dragon.oss-cn-shenzhen.aliyuncs.com"
      :data="dataObj"
      :before-upload="beforeUpload"
      :on-success="handleUploadSuccess"
      :on-preview="handlePreview"
      :before-remove="beforeRemove"
      :on-remove="handleRemove"
      :file-list="fileList"
      list-type="picture"
      style="text-align: center;"
      multiple>
      <el-button size="small" type="primary">点击上传</el-button>
      <div class="el-upload__tip" slot="tip">只支持jpg、png、gif格式的图片！</div>
    </el-upload>
    <span slot="footer" class="dialog-footer">
      <el-button @click="cancelDialog()">取消</el-button>
      <el-button type="primary" @click="saveFiles()">保存</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { policy,deleteOssFile } from '@/components/upload/policy'
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
        fileList: [],
        imageList: []
      }
    },
    methods: {
      init (id) {
        this.visible = true
      },
      beforeRemove (file, fileList) {
        this.$confirm('是否删除已上传的文件?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let fileUrl = this.imageList[this.fileList.indexOf(file)].url
          console.log('将要删除的文件名',fileUrl)
          console.log('阿里云删除前',this.imageList)
          return new Promise((resolve, reject) => {
            deleteOssFile(fileUrl).then(response => {
              console.log('服务器响应数据',response)
              if (response.code === 0) {
                this.fileList.splice(this.fileList.indexOf(file), 1)
                this.imageList.splice(this.fileList.indexOf(file), 1)
                console.log('阿里云删除后imageList',this.imageList)
                console.log('阿里云删除后fileList',this.fileList)
                this.$message({
                  message: '删除成功！',
                  type: 'success'
                })
              } else {
                this.$message.error('删除失败！')
              }
              resolve(true)
            }).catch(err => {
              reject(new Error(false))
            })
          })
        }).catch(() => {

        })
        return false
      },
      handleRemove (file, fileList) {
        console.log(file)
      },
      handlePreview (file) {
        console.log(file)
      },
      // 上传之前
      beforeUpload (file) {
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
              this.imageList.push({
                name: file.name,
                url: this.dataObj.host + '/' + this.dataObj.key.replace('${filename}', file.name)
              })
              resolve(true)
            })
            .catch(err => {
              reject(new Error(false))
            })
        })
      },
      // 上传成功
      handleUploadSuccess (response, file, fileList) {
        this.fileList = fileList
      },
      // 保存上传的文件到数据
      saveFiles () {
        console.log('保存文件到数据库', this.imageList)
        let mapData = {};
        this.imageList.forEach(item => {
            let key = item.name
            mapData[key] = item.url
        })
        console.log('组装后的数据',mapData)
        this.$http({
          url: this.$http.adornUrl('/sys/oss/saveFile'),
          method: 'post',
          params: this.$http.adornParams({
            'files': mapData
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.fileList = []
            this.imageList = []
            this.visible = false
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      // 点击取消
      cancelDialog () {
        this.$confirm('你确定不保存已上传的文件?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          // 点击确认时执行
          this.visible = false
        }).catch(() => {
          // 点击取消时执行
        })
      },
      // 弹窗关闭时
      closeHandle () {
        this.fileList = []
        this.imageList = []
        this.$emit('refreshDataList')
      }
    }
  }
</script>
