import request from '@/utils/request'

// 查询物流公司记录列表
export function listExpress(query) {
  return request({
    url: '/setting/delivery/express/list',
    method: 'get',
    params: query
  })
}

// 查询物流公司记录详细
export function getExpress(expressId) {
  return request({
    url: '/setting/delivery/express/' + expressId,
    method: 'get'
  })
}

// 新增物流公司记录
export function addExpress(data) {
  return request({
    url: '/setting/delivery/express',
    method: 'post',
    data: data
  })
}

// 修改物流公司记录
export function updateExpress(data) {
  return request({
    url: '/setting/delivery/express',
    method: 'put',
    data: data
  })
}

// 删除物流公司记录
export function delExpress(expressId) {
  return request({
    url: '/setting/delivery/express/' + expressId,
    method: 'delete'
  })
}
