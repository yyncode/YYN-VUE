import request from '@/utils/request'

// 查询导航设置列表
export function listNav(query) {
  return request({
    url: '/news/nav/list',
    method: 'get',
    params: query
  })
}

// 查询导航设置列表(不带分页)
export function listNavNotPage() {
  return request({
    url: '/news/nav/list/no/page',
    method: 'get'
  })
}

// 查询导航设置详细
export function getNav(navId) {
  return request({
    url: '/news/nav/' + navId,
    method: 'get'
  })
}

// 新增导航设置
export function addNav(data) {
  return request({
    url: '/news/nav',
    method: 'post',
    data: data
  })
}

// 修改导航设置
export function updateNav(data) {
  return request({
    url: '/news/nav',
    method: 'put',
    data: data
  })
}

// 删除导航设置
export function delNav(navId) {
  return request({
    url: '/news/nav/' + navId,
    method: 'delete'
  })
}
