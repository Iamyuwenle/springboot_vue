import request from '@/utils/request'

// 查询客户管理列表
export function listCustomer(query) {
  return request({
    url: '/business/customer/list',
    method: 'get',
    params: query
  })
}

// 查询客户管理详细
export function getCustomer(id) {
  return request({
    url: '/business/customer/' + id,
    method: 'get'
  })
}

// 新增客户管理
export function addCustomer(data) {
  return request({
    url: '/business/customer',
    method: 'post',
    data: data
  })
}

// 修改客户管理
export function updateCustomer(data) {
  return request({
    url: '/business/customer/updateCustomer',
    method: 'put',
    data: data
  })
}

// 删除客户管理
export function delCustomer(id) {
  return request({
    url: '/business/customer/' + id,
    method: 'delete'
  })
}
