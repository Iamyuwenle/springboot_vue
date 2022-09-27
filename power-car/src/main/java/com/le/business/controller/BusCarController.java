package com.le.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.le.common.annotation.Log;
import com.le.common.core.controller.BaseController;
import com.le.common.core.domain.AjaxResult;
import com.le.common.enums.BusinessType;
import com.le.business.domain.BusCar;
import com.le.business.service.IBusCarService;
import com.le.common.utils.poi.ExcelUtil;
import com.le.common.core.page.TableDataInfo;

/**
 * 车辆管理Controller
 * 
 * @author leyouju
 * @date 2022-09-26
 */
@RestController
@RequestMapping("/business/car")
public class BusCarController extends BaseController
{
    @Autowired
    private IBusCarService busCarService;

    /**
     * 查询车辆管理列表
     */
    @PreAuthorize("@ss.hasPermi('business:car:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusCar busCar)
    {
        startPage();
        List<BusCar> list = busCarService.selectBusCarList(busCar);
        return getDataTable(list);
    }

    /**
     * 导出车辆管理列表
     */
    @PreAuthorize("@ss.hasPermi('business:car:export')")
    @Log(title = "车辆管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusCar busCar)
    {
        List<BusCar> list = busCarService.selectBusCarList(busCar);
        ExcelUtil<BusCar> util = new ExcelUtil<BusCar>(BusCar.class);
        util.exportExcel(response, list, "车辆管理数据");
    }

    /**
     * 获取车辆管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:car:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(busCarService.selectBusCarById(id));
    }

    /**
     * 新增车辆管理
     */
    @PreAuthorize("@ss.hasPermi('business:car:add')")
    @Log(title = "车辆管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusCar busCar)
    {
        return toAjax(busCarService.insertBusCar(busCar));
    }

    /**
     * 修改车辆管理
     */
    @PreAuthorize("@ss.hasPermi('business:car:edit')")
    @Log(title = "车辆管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusCar busCar)
    {
        return toAjax(busCarService.updateBusCar(busCar));
    }

    /**
     * 删除车辆管理
     */
    @PreAuthorize("@ss.hasPermi('business:car:remove')")
    @Log(title = "车辆管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(busCarService.deleteBusCarByIds(ids));
    }
}
