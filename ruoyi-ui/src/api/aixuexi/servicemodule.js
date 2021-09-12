import request from '@/utils/request'

// 查询业务模块列表
export function listServicemodule(query) {
  return request({
    url: '/aixuexi/servicemodule/list',
    method: 'get',
    params: query
  })
}


// 查询业务模块详细
export function getServicemodule(servicemoduleId) {
  return request({
    url: '/aixuexi/servicemodule/' + servicemoduleId,
    method: 'get'
  })
}
// 查询部门下拉树结构
export function treeselect() {
  return request({
    url: '/aixuexi/servicemodule/treeselect',
    method: 'get'
  })
}

// 新增业务模块
export function addServicemodule(data) {
  return request({
    url: '/aixuexi/servicemodule',
    method: 'post',
    data: data
  })
}

// 修改业务模块
export function updateServicemodule(data) {
  return request({
    url: '/aixuexi/servicemodule',
    method: 'put',
    data: data
  })
}

// 删除业务模块
export function delServicemodule(servicemoduleId) {
  return request({
    url: '/aixuexi/servicemodule/' + servicemoduleId,
    method: 'delete'
  })
}

// 导出业务模块
export function exportServicemodule(query) {
  return request({
    url: '/aixuexi/servicemodule/export',
    method: 'get',
    params: query
  })
}
