import request from '@/utils/request'

// 查询商家(商户)记录列表
export function listStore(query) {
  return request({
    url: '/system/store/list',
    method: 'get',
    params: query
  })
}

// 查询商家(商户)记录详细
export function getStoreDetail() {
  return request({
    url: '/store/detail/get',
    method: 'get'
  })
}

// 新增商家(商户)记录
export function addStore(data) {
  return request({
    url: '/system/store',
    method: 'post',
    data: data
  })
}

// 修改商家(商户)记录
export function updateStore(data) {
  return request({
    url: '/system/store',
    method: 'put',
    data: data
  })
}

// 删除商家(商户)记录
export function delStore(storeId) {
  return request({
    url: '/system/store/' + storeId,
    method: 'delete'
  })
}
