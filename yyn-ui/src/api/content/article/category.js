import request from '@/utils/request'

// 查询文章分类列表
export function listCategory(query) {
  return request({
    url: '/content/article/category/list',
    method: 'get',
    params: query
  })
}

// 查询文章分类列表不分页
export function listCategoryNoPage() {
  return request({
    url: '/content/article/category/list/no/page',
    method: 'get'
  })
}

// 查询文章分类详细
export function getCategory(categoryId) {
  return request({
    url: '/content/article/category/' + categoryId,
    method: 'get'
  })
}

// 新增文章分类
export function addCategory(data) {
  return request({
    url: '/content/article/category',
    method: 'post',
    data: data
  })
}

// 修改文章分类
export function updateCategory(data) {
  return request({
    url: '/content/article/category',
    method: 'put',
    data: data
  })
}

// 删除文章分类
export function delCategory(categoryId) {
  return request({
    url: '/content/article/category/' + categoryId,
    method: 'delete'
  })
}
