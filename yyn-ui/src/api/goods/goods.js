import request from '@/utils/request'

// 查询商品记录列表
export function listGoods(query) {
  return request({
    url: '/goods/goods/list',
    method: 'get',
    params: query
  })
}

// 查询商品记录详细
export function getGoods(goodsId) {
  return request({
    url: '/goods/goods/' + goodsId,
    method: 'get'
  })
}

// 新增商品记录
export function addGoods(data) {
  return request({
    url: '/goods/goods',
    method: 'post',
    data: data
  })
}

// 修改商品记录
export function updateGoods(data) {
  return request({
    url: '/goods/goods',
    method: 'put',
    data: data
  })
}

// 删除商品记录
export function delGoods(goodsId) {
  return request({
    url: '/goods/goods/' + goodsId,
    method: 'delete'
  })
}
