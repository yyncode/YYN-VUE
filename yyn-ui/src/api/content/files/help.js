import request from '@/utils/request'

// 查询帮助中心记录列表
export function listHelp(query) {
  return request({
    url: '/content/files/help/list',
    method: 'get',
    params: query
  })
}

// 查询帮助中心记录详细
export function getHelp(helpId) {
  return request({
    url: '/content/files/help/' + helpId,
    method: 'get'
  })
}

// 新增帮助中心记录
export function addHelp(data) {
  return request({
    url: '/content/files/help',
    method: 'post',
    data: data
  })
}

// 修改帮助中心记录
export function updateHelp(data) {
  return request({
    url: '/content/files/help',
    method: 'put',
    data: data
  })
}

// 删除帮助中心记录
export function delHelp(helpId) {
  return request({
    url: '/content/files/help/' + helpId,
    method: 'delete'
  })
}
