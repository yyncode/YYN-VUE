import request from '@/utils/request'

// 查询商品服务与承诺记录列表
export function listService(query) {
  return request({
    url: '/goods/service/list',
    method: 'get',
    params: query
  })
}

// 查询商品服务与承诺记录列表(无分页)
export function listServiceNoPage() {
  return request({
    url: '/goods/service/list/no/page',
    method: 'get'
  })
}

// 查询商品服务与承诺记录详细
export function getService(serviceId) {
  return request({
    url: '/goods/service/' + serviceId,
    method: 'get'
  })
}

// 新增商品服务与承诺记录
export function addService(data) {
  return request({
    url: '/goods/service',
    method: 'post',
    data: data
  })
}

// 修改商品服务与承诺记录
export function updateService(data) {
  return request({
    url: '/goods/service',
    method: 'put',
    data: data
  })
}

// 删除商品服务与承诺记录
export function delService(serviceId) {
  return request({
    url: '/goods/service/' + serviceId,
    method: 'delete'
  })
}
