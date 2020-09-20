import request from '@/utils/httpRequest'

// 获取一年内博客提交的数量
export function getBlogContributeCount () {
  return request({
    url: '/index/getBlogContributeCount',
    method: 'get'
  })
}

// 获取访问日志
export function listVisitLog (params) {
  return request({
    url: '/dashboard/visitLog',
    method: 'get',
    params: params
  })
}

// 获取操作日志
export function listOperateLog (params) {
  return request({
    url: '/dashboard/operateLog',
    method: 'get',
    params: params
  })
}

// 获取任务日志
export function listTaskLog (params) {
  return request({
    url: '/dashboard/taskLog',
    method: 'get',
    params: params
  })
}

// 获取登录日志
export function listLoginLog (params) {
  return request({
    url: '/dashboard/loginLog',
    method: 'get',
    params: params
  })
}
