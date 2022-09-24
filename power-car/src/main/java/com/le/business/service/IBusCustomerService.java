package com.le.business.service;

import java.util.List;
import com.le.business.domain.BusCustomer;

/**
 * 客户管理Service接口
 * 
 * @author leyouju
 * @date 2022-09-22
 */
public interface IBusCustomerService 
{
    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    public BusCustomer selectBusCustomerById(Integer id);

    /**
     * 查询客户管理列表
     * 
     * @param busCustomer 客户管理
     * @return 客户管理集合
     */
    public List<BusCustomer> selectBusCustomerList(BusCustomer busCustomer);

    /**
     * 新增客户管理
     * 
     * @param busCustomer 客户管理
     * @return 结果
     */
    public int insertBusCustomer(BusCustomer busCustomer);

    /**
     * 修改客户管理
     * 
     * @param busCustomer 客户管理
     * @return 结果
     */
    public int updateBusCustomer(BusCustomer busCustomer);

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的客户管理主键集合
     * @return 结果
     */
    public int deleteBusCustomerByIds(Integer[] ids);

    /**
     * 删除客户管理信息
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    public int deleteBusCustomerById(Integer id);
}
