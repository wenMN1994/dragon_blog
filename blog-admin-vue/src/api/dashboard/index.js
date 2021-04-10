import request from '@/utils/httpRequest'

// 获取一年内博客提交的数量
export function getBlogContributeCount () {
  return request({
    url: '/sys/index/getBlogContributeCount',
    method: 'get'
  })
}

// 获取访问日志
export function listVisitLog (params) {
  return request({
    url: '/sys/index/visitLog',
    method: 'get',
    params: params
  })
}

// 获取操作日志
export function listOperateLog (params) {
  return request({
    url: request.adornUrl('/sys/index/operateLog'),
    method: 'get',
    params: params
  })
}

// 获取任务日志
export function listTaskLog (params) {
  return request({
    url: '/sys/index/taskLog',
    method: 'get',
    params: params
  })
}

// 获取登录日志
export function listLoginLog (params) {
  return request({
    url: '/sys/index/loginLog',
    method: 'get',
    params: params
  })
}
