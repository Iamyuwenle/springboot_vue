package com.le.business.service.impl;

import java.util.List;
import com.le.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.le.business.mapper.BusCarMapper;
import com.le.business.domain.BusCar;
import com.le.business.service.IBusCarService;

import javax.annotation.Resource;

/**
 * 车辆管理Service业务层处理
 * 
 * @author leyouju
 * @date 2022-09-26
 */
@Service
public class BusCarServiceImpl implements IBusCarService 
{
    @Resource
    private BusCarMapper busCarMapper;

    /**
     * 查询车辆管理
     * 
     * @param id 车辆管理主键
     * @return 车辆管理
     */
    @Override
    public BusCar selectBusCarById(Long id)
    {
        return busCarMapper.selectBusCarById(id);
    }

    /**
     * 查询车辆管理列表
     * 
     * @param busCar 车辆管理
     * @return 车辆管理
     */
    @Override
    public List<BusCar> selectBusCarList(BusCar busCar)
    {
        return busCarMapper.selectBusCarList(busCar);
    }

    /**
     * 新增车辆管理
     * 
     * @param busCar 车辆管理
     * @return 结果
     */
    @Override
    public int insertBusCar(BusCar busCar)
    {
        busCar.setCreateTime(DateUtils.getNowDate());
        return busCarMapper.insertBusCar(busCar);
    }

    /**
     * 修改车辆管理
     * 
     * @param busCar 车辆管理
     * @return 结果
     */
    @Override
    public int updateBusCar(BusCar busCar)
    {
        busCar.setUpdateTime(DateUtils.getNowDate());
        return busCarMapper.updateBusCar(busCar);
    }

    /**
     * 批量删除车辆管理
     * 
     * @param ids 需要删除的车辆管理主键
     * @return 结果
     */
    @Override
    public int deleteBusCarByIds(Long[] ids)
    {
        return busCarMapper.deleteBusCarByIds(ids);
    }

    /**
     * 删除车辆管理信息
     * 
     * @param id 车辆管理主键
     * @return 结果
     */
    @Override
    public int deleteBusCarById(Long id)
    {
        return busCarMapper.deleteBusCarById(id);
    }
}
