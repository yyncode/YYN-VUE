import request from '@/utils/request'

// 查询文件库分组记录列表
export function listGroup(query) {
  return request({
    url: '/content/files/group/list',
    method: 'get',
    params: query
  })
}

// 查询文件库分组记录树结构列表
export function treeGroup() {
  return request({
    url: '/content/files/group/treeSelect',
    method: 'get'
  })
}

// 查询文件库分组记录详细
export function getGroup(groupId) {
  return request({
    url: '/content/files/group/' + groupId,
    method: 'get'
  })
}

// 新增文件库分组记录
export function addGroup(data) {
  return request({
    url: '/content/files/group',
    method: 'post',
    data: data
  })
}

// 修改文件库分组记录
export function updateGroup(data) {
  return request({
    url: '/content/files/group',
    method: 'put',
    data: data
  })
}

// 删除文件库分组记录
export function delGroup(groupId) {
  return request({
    url: '/content/files/group/' + groupId,
    method: 'delete'
  })
}
