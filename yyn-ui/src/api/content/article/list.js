import request from '@/utils/request'

// 查询文章记录列表
export function listArticle(query) {
  return request({
    url: '/content/article/list',
    method: 'get',
    params: query
  })
}

// 查询文章记录详细
export function getArticle(articleId) {
  return request({
    url: '/content/article/' + articleId,
    method: 'get'
  })
}

// 新增文章记录
export function addArticle(data) {
  return request({
    url: '/content/article',
    method: 'post',
    data: data
  })
}

// 修改文章记录
export function updateArticle(data) {
  return request({
    url: '/content/article',
    method: 'put',
    data: data
  })
}

// 删除文章记录
export function delArticle(articleId) {
  return request({
    url: '/content/article/' + articleId,
    method: 'delete'
  })
}
