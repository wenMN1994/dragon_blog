<template>
  <div style="height: 400px;width: 100%">
    <el-tabs v-model="activeName" @tab-click="tabChange" style="height: 300px">
      <el-tab-pane class="tablePane" label="访问日志" name="visitLogTab" :loading="true">
        <ul class="list" v-infinite-scroll="getVisitLog">
          <li v-for="item in visitLog.data" v-bind:key="item.index" class="list-item">
            <span class="list-item-left" v-html="item.username">{{item.username}}</span>
            <span class="list-item-center" v-html="item.operation">{{item.operation}}</span>
            <span class="list-item-right" v-html="item.createTime">{{item.createTime}}</span>
          </li>
          <p v-if="visitLog.loading" class="list-item">加载中...</p>
          <p v-if="visitLog.noMore" class="list-item">没有更多了</p>
        </ul>
      </el-tab-pane>
      <el-tab-pane class="tablePane" label="登录日志" name="loginLogTab">
        <ul class="list" v-infinite-scroll="getLoginLog">
          <li v-for="item in loginLog.data" v-bind:key="item.index" class="list-item">
            <span class="list-item-left" v-html="item.username">{{item.username}}</span>
            <span class="list-item-center" v-html="item.operation">{{item.operation}}</span>
            <span class="list-item-right" v-html="item.createTime">{{item.createTime}}</span>
          </li>
          <p v-if="loginLog.loading" class="list-item">加载中...</p>
          <p v-if="loginLog.noMore" class="list-item">没有更多了</p>
        </ul>
      </el-tab-pane>
      <el-tab-pane class="tablePane" label="操作日志" name="operateLogTab">
        <ul class="list" v-infinite-scroll="getOperateLog">
          <li v-for="item in operateLog.data" v-bind:key="item.index" class="list-item">
            <span class="list-item-left" v-html="item.username">{{item.username}}</span>
            <span class="list-item-center" v-html="item.operation">{{item.operation}}</span>
            <span class="list-item-right" v-html="item.createTime">{{item.createTime}}</span>
          </li>
          <li v-if="operateLog.loading" class="list-item">加载中...</li>
          <li v-if="operateLog.noMore" class="list-item">没有更多了</li>
        </ul>
      </el-tab-pane>
      <el-tab-pane class="tablePane" label="任务日志" name="taskLogTab">
        <ul class="list" v-infinite-scroll="getTaskLog">
          <li v-for="item in taskLog.data" v-bind:key="item.index" class="list-item">
            <span style="display: none;" v-html="item.jobId">{{item.jobId}}</span>
            <span class="list-item-left" v-html="item.beanName">{{item.beanName}}</span>
            <span class="list-item-center">
              <el-tag v-if="item.status === 0" size="small">成功</el-tag>
              <el-tag v-else size="small" type="danger" style="cursor: pointer;">失败</el-tag>
            </span>
            <span class="list-item-right" v-html="item.createTime">{{item.createTime}}</span>
          </li>
          <p v-if="taskLog.loading" class="list-item">加载中...</p>
          <p v-if="taskLog.noMore" class="list-item">没有更多了</p>
        </ul>
      </el-tab-pane>
    </el-tabs>

  </div>
</template>

<script>
  import {listVisitLog, listOperateLog, listLoginLog, listTaskLog} from '@/api/dashboard'

  export default {
    name: 'Log',
    created () {
      this.getVisitLog()
    },
    data () {
      return {
        activeName: 'visitLogTab',
        visitLog: {
          data: [],
          handle: false,
          noMore: false,
          loading: false,
          queryParams: {
            pageNum: 0,
            pageSize: 10,
            logType: '2',
            orderByColumn: 'CREATE_TIME',
            isAsc: 'DESC'
          }
        },
        loginLog: {
          data: [],
          handle: false,
          noMore: false,
          loading: false,
          queryParams: {
            pageNum: 0,
            pageSize: 10,
            logType: '0',
            orderByColumn: 'CREATE_TIME',
            isAsc: 'DESC'
          }
        },
        operateLog: {
          data: [],
          handle: false,
          noMore: false,
          loading: false,
          queryParams: {
            pageNum: 0,
            pageSize: 20,
            logType: '1',
            orderByColumn: 'CREATE_TIME',
            isAsc: 'DESC'
          }
        },
        taskLog: {
          data: [],
          handle: false,
          noMore: false,
          loading: false,
          queryParams: {
            pageNum: 0,
            pageSize: 20,
            orderByColumn: 'CREATE_TIME',
            isAsc: 'DESC'
          }
        }
      }
    },
    methods: {
      tabChange (target) {
        if (target.paneName === 'visitLogTab') {
          if (!this.visitLog.handle) {
            this.getVisitLog()
          }
        } else if (target.paneName === 'loginLogTab') {
          if (!this.loginLog.handle) {
            this.getLoginLog()
          }
        } else if (target.paneName === 'operateLogTab') {
          if (!this.operateLog.handle) {
            this.getOperateLog()
          }
        } else if (target.paneName === 'taskLogTab') {
          if (!this.taskLog.handle) {
            this.getTaskLog()
          }
        }
      },
      getVisitLog () {
        if (this.visitLog.noMore) {
          return
        }
        this.visitLog.loading = true
        setTimeout(() => {
          this.visitLog.queryParams.pageNum++
          listVisitLog(this.visitLog.queryParams).then(response => {
            this.visitLog.handle = true
            response.data.page.list.forEach(element => {
              let visitLogData = {
                username: '',
                operation: '',
                createTime: ''
              }
              visitLogData.username = element.username
              visitLogData.operation = element.operation
              visitLogData.createTime = element.createTime
              this.visitLog.data.push(visitLogData)
            });
            if (response.data.page.totalCount === this.visitLog.data.length) {
              this.visitLog.noMore = true
            }
            this.visitLog.loading = false
          })
        }, 1000)
      },
      getOperateLog () {
        if (this.operateLog.noMore) {
          return
        }
        this.operateLog.loading = true
        setTimeout(() => {
          this.operateLog.queryParams.pageNum++
          listOperateLog(this.operateLog.queryParams).then(response => {
            this.operateLog.handle = true
            response.data.page.list.forEach(element => {
              let operateLogData = {
                username: '',
                operation: '',
                createTime: ''
              }
              operateLogData.username = element.username
              operateLogData.operation = element.operation
              operateLogData.createTime = element.createTime
              this.operateLog.data.push(operateLogData)
            });

            if (response.data.page.totalCount === this.operateLog.data.length) {
              this.operateLog.noMore = true
            }
            this.operateLog.loading = false
          })
        }, 1000)
      },
      getLoginLog () {
        if (this.loginLog.noMore) {
          return
        }
        this.loginLog.loading = true
        setTimeout(() => {
          this.loginLog.queryParams.pageNum++
          listLoginLog(this.loginLog.queryParams).then(response => {
            this.loginLog.handle = true
            response.data.page.list.forEach(element => {
              let loginLogData = {
                username: '',
                operation: '',
                createTime: ''
              }
              loginLogData.username = element.username
              loginLogData.operation = element.operation
              loginLogData.createTime = element.createTime
              this.loginLog.data.push(loginLogData)
            });
            if (response.data.page.totalCount === this.loginLog.data.length) {
              this.loginLog.noMore = true
            }
            this.loginLog.loading = false
          })
        }, 1000)
      },
      getTaskLog () {
        if (this.taskLog.noMore) {
          return
        }
        this.taskLog.loading = true
        setTimeout(() => {
          this.taskLog.queryParams.pageNum++
          listTaskLog(this.taskLog.queryParams).then(response => {
            this.taskLog.handle = true
            response.data.page.list.forEach(element => {
              let taskLogData = {
                jobId: '',
                beanName: '',
                status: '',
                createTime: ''
              }
              taskLogData.jobId = element.jobId
              taskLogData.beanName = element.beanName
              taskLogData.status = element.status
              taskLogData.createTime = element.createTime
              this.taskLog.data.push(taskLogData)
            });
            if (response.data.page.totalCount === this.taskLog.data.length) {
              this.taskLog.noMore = true
            }
            this.taskLog.loading = false
          })
        }, 1000)
      }
    }
  }
</script>

<style scoped>
  *::-webkit-scrollbar {
    /*滚动条整体样式*/
    width: 8px; /*高宽分别对应横竖滚动条的尺寸*/
    height: 1px;
  }

  *::-webkit-scrollbar-thumb {
    /*滚动条里面小方块--纯色*/
    border-radius: 10px;
    box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    background: rgba(193, 193, 193, 1);
  }

  *::-webkit-scrollbar-track {
    /*滚动条里面轨道*/
    box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    border-radius: 10px;
    background: #ededed;
  }
  .tablePane {
    overflow-y: auto;
    overflow-x: hidden;
    height: 330px;
  }

  .list {
    width: 100%;
    padding: 0;
    margin: 0;
    list-style: none;
  }

  .list-item {
    margin: 10px;
  }

  .list-item-left {
    width: 20%;
  }

  .list-item-center {
    width: 50%;
  }

  .list-item-right {
    width: 30%;
  }
</style>
