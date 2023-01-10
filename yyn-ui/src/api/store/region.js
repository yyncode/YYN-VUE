import request from '@/utils/request'

// 查询省市区数据列表
export function listRegion(query) {
  return request({
    url: '/store/region/list',
    method: 'get',
    params: query
  })
}

// 查询省市区数据详细
export function getRegion(id) {
  return request({
    url: '/store/region/' + id,
    method: 'get'
  })
}

// 新增省市区数据
export function addRegion(data) {
  return request({
    url: '/store/region',
    method: 'post',
    data: data
  })
}

// 修改省市区数据
export function updateRegion(data) {
  return request({
    url: '/store/region',
    method: 'put',
    data: data
  })
}

// 删除省市区数据
export function delRegion(id) {
  return request({
    url: '/store/region/' + id,
    method: 'delete'
  })
}
