import request from '@/utils/request'

// 查询商家(商户)记录列表
export function listStore(query) {
  return request({
    url: '/store/info/list',
    method: 'get',
    params: query
  })
}

// 查询商家(商户)记录详细
export function getStoreDetail() {
  return request({
    url: '/store/info/get',
    method: 'get'
  })
}

// 保存商家(商户)记录
export function saveStore(data) {
  return request({
    url: '/store/info/save',
    method: 'post',
    data: data
  })
}

// 新增商家(商户)记录
export function addStore(data) {
  return request({
    url: '/store/info',
    method: 'post',
    data: data
  })
}

// 修改商家(商户)记录
export function updateStore(data) {
  return request({
    url: '/store/info',
    method: 'put',
    data: data
  })
}

// 删除商家(商户)记录
export function delStore(storeId) {
  return request({
    url: '/store/info/' + storeId,
    method: 'delete'
  })
}
