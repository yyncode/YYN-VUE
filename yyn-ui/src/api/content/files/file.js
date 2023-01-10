import request from '@/utils/request'

// 查询文件库记录列表
export function listFile(query) {
  return request({
    url: '/content/files/list/list',
    method: 'get',
    params: query
  })
}

// 查询文件库记录详细
export function getFile(fileId) {
  return request({
    url: '/content/files/list/' + fileId,
    method: 'get'
  })
}

// 新增文件库记录
export function addFile(data) {
  return request({
    url: '/content/files/list',
    method: 'post',
    data: data
  })
}

// 修改文件库记录
export function updateFile(data) {
  return request({
    url: '/content/files/list',
    method: 'put',
    data: data
  })
}

// 删除文件库记录
export function delFile(fileId) {
  return request({
    url: '/content/files/list/' + fileId,
    method: 'delete'
  })
}
