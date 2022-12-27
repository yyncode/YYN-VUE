import request from '@/utils/request'
import {parseStrEmpty} from "@/utils/ruoyi";

// 查询新闻内容列表
export function listContent(query) {
  return request({
    url: '/news/content/list',
    method: 'get',
    params: query
  })
}

// 查询新闻内容详细
export function getContent(newsId) {
  return request({
    url: '/news/content/' + parseStrEmpty(newsId),
    method: 'get'
  })
}

// 新增新闻内容
export function addContent(data) {
  return request({
    url: '/news/content',
    method: 'post',
    data: data
  })
}

// 修改新闻内容
export function updateContent(data) {
  return request({
    url: '/news/content',
    method: 'put',
    data: data
  })
}

// 删除新闻内容
export function delContent(newsId) {
  return request({
    url: '/news/content/' + newsId,
    method: 'delete'
  })
}
