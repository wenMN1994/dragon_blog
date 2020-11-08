import http from '@/utils/httpRequest.js'
// 获取阿里云oss签名
export function policy () {
  return new Promise((resolve, reject) => {
    http({
      url: http.adornUrl('/thirdparty/oss/policy'),
      method: 'get',
      params: http.adornParams({})
    }).then(({ data }) => {
      resolve(data)
    })
  })
}

// 删除阿里云oss文件
export function deleteOssFile (params) {
  return new Promise((resolve, reject) => {
    http({
      url: http.adornUrl('/thirdparty/oss/delete'),
      method: 'get',
      params: http.adornParams({
        'fileName': params
      })
    }).then(({ data }) => {
      resolve(data)
    })
  })
}
