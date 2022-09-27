package com.le.business.mapper;

import java.util.List;
import com.le.business.domain.BusCar;

/**
 * 车辆管理Mapper接口
 * 
 * @author leyouju
 * @date 2022-09-26
 */
public interface BusCarMapper 
{
    /**
     * 查询车辆管理
     * 
     * @param id 车辆管理主键
     * @return 车辆管理
     */
    public BusCar selectBusCarById(Long id);

    /**
     * 查询车辆管理列表
     * 
     * @param busCar 车辆管理
     * @return 车辆管理集合
     */
    public List<BusCar> selectBusCarList(BusCar busCar);

    /**
     * 新增车辆管理
     * 
     * @param busCar 车辆管理
     * @return 结果
     */
    public int insertBusCar(BusCar busCar);

    /**
     * 修改车辆管理
     * 
     * @param busCar 车辆管理
     * @return 结果
     */
    public int updateBusCar(BusCar busCar);

    /**
     * 删除车辆管理
     * 
     * @param id 车辆管理主键
     * @return 结果
     */
    public int deleteBusCarById(Long id);

    /**
     * 批量删除车辆管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusCarByIds(Long[] ids);
}
