import request from '@/utils/request'

// 查询所有的二级部门
export function listDepts() {
  return request({
    url: '/business/dept/list',
    method: 'get',
  })
}
