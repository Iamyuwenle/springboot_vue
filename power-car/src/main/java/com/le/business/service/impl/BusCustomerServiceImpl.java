package com.le.business.service.impl;

import java.util.List;
import com.le.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.le.business.mapper.BusCustomerMapper;
import com.le.business.domain.BusCustomer;
import com.le.business.service.IBusCustomerService;

/**
 * 客户管理Service业务层处理
 * 
 * @author leyouju
 * @date 2022-09-22
 */
@Service
public class BusCustomerServiceImpl implements IBusCustomerService 
{
    @Autowired
    private BusCustomerMapper busCustomerMapper;

    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    @Override
    public BusCustomer selectBusCustomerById(Integer id)
    {
        return busCustomerMapper.selectBusCustomerById(id);
    }

    /**
     * 查询客户管理列表
     * 
     * @param busCustomer 客户管理
     * @return 客户管理
     */
    @Override
    public List<BusCustomer> selectBusCustomerList(BusCustomer busCustomer)
    {
        return busCustomerMapper.selectBusCustomerList(busCustomer);
    }

    /**
     * 新增客户管理
     * 
     * @param busCustomer 客户管理
     * @return 结果
     */
    @Override
    public int insertBusCustomer(BusCustomer busCustomer)
    {
        busCustomer.setCreateTime(DateUtils.getNowDate());
        return busCustomerMapper.insertBusCustomer(busCustomer);
    }

    /**
     * 修改客户管理
     * 
     * @param busCustomer 客户管理
     * @return 结果
     */
    @Override
    public int updateBusCustomer(BusCustomer busCustomer)
    {
        busCustomer.setUpdateTime(DateUtils.getNowDate());
        return busCustomerMapper.updateBusCustomer(busCustomer);
    }

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的客户管理主键
     * @return 结果
     */
    @Override
    public int deleteBusCustomerByIds(Integer[] ids)
    {
        return busCustomerMapper.deleteBusCustomerByIds(ids);
    }

    /**
     * 删除客户管理信息
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    @Override
    public int deleteBusCustomerById(Integer id)
    {
        return busCustomerMapper.deleteBusCustomerById(id);
    }
}
