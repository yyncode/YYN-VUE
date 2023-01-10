import request from '@/utils/request'

// 查询配送模板主列表
export function listDelivery(query) {
  return request({
    url: '/setting/delivery/template/list',
    method: 'get',
    params: query
  })
}

// 查询配送模板主详细
export function getDelivery(deliveryId) {
  return request({
    url: '/setting/delivery/template/' + deliveryId,
    method: 'get'
  })
}

// 新增配送模板主
export function addDelivery(data) {
  return request({
    url: '/setting/delivery/template',
    method: 'post',
    data: data
  })
}

// 修改配送模板主
export function updateDelivery(data) {
  return request({
    url: '/setting/delivery/template',
    method: 'put',
    data: data
  })
}

// 删除配送模板主
export function delDelivery(deliveryId) {
  return request({
    url: '/setting/delivery/template/' + deliveryId,
    method: 'delete'
  })
}
