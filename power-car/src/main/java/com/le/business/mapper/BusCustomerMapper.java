package com.le.business.mapper;

import java.util.List;
import com.le.business.domain.BusCustomer;

/**
 * 客户管理Mapper接口
 * 
 * @author leyouju
 * @date 2022-09-22
 */
public interface BusCustomerMapper 
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
     * 删除客户管理
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    public int deleteBusCustomerById(Integer id);

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusCustomerByIds(Integer[] ids);
}
