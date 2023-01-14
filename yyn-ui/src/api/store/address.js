import request from '@/utils/request'

// 查询商家地址记录列表
export function listAddress(query) {
  return request({
    url: '/store/address/list',
    method: 'get',
    params: query
  })
}

// 查询商家地址记录详细
export function getAddress(addressId) {
  return request({
    url: '/store/address/' + addressId,
    method: 'get'
  })
}

// 新增商家地址记录
export function addAddress(data) {
  return request({
    url: '/store/address',
    method: 'post',
    data: data
  })
}

// 修改商家地址记录
export function updateAddress(data) {
  return request({
    url: '/store/address',
    method: 'put',
    data: data
  })
}

// 删除商家地址记录
export function delAddress(addressId) {
  return request({
    url: '/store/address/' + addressId,
    method: 'delete'
  })
}
