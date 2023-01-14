import request from '@/utils/request'

// 查询店铺页面记录列表
export function listPage(query) {
  return request({
    url: '/store/page/list',
    method: 'get',
    params: query
  })
}

// 查询店铺页面记录详细
export function getPage(pageId) {
  return request({
    url: '/store/page/' + pageId,
    method: 'get'
  })
}

// 新增店铺页面记录
export function addPage(data) {
  return request({
    url: '/store/page',
    method: 'post',
    data: data
  })
}

// 修改店铺页面记录
export function updatePage(data) {
  return request({
    url: '/store/page',
    method: 'put',
    data: data
  })
}

// 删除店铺页面记录
export function delPage(pageId) {
  return request({
    url: '/store/page/' + pageId,
    method: 'delete'
  })
}
