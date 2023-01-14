import request from '@/utils/request'

// 查询商家设置记录列表
export function listSetting(query) {
  return request({
    url: '/store/setting/list',
    method: 'get',
    params: query
  })
}

// 查询商家设置记录详细
export function getSetting(key) {
  return request({
    url: '/store/setting/' + key,
    method: 'get'
  })
}

// 新增商家设置记录
export function addSetting(data) {
  return request({
    url: '/store/setting',
    method: 'post',
    data: data
  })
}

// 保存商家设置记录
export function saveSetting(data) {
  return request({
    url: '/store/setting/save',
    method: 'post',
    data: data
  })
}

// 修改商家设置记录
export function updateSetting(data) {
  return request({
    url: '/store/setting',
    method: 'put',
    data: data
  })
}

// 删除商家设置记录
export function delSetting(key) {
  return request({
    url: '/store/setting/' + key,
    method: 'delete'
  })
}
