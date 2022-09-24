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
import com.le.business.domain.BusCustomer;
import com.le.business.service.IBusCustomerService;
import com.le.common.utils.poi.ExcelUtil;
import com.le.common.core.page.TableDataInfo;

/**
 * 客户管理Controller
 * 
 * @author leyouju
 * @date 2022-09-22
 */
@RestController
@RequestMapping("/business/customer")
public class BusCustomerController extends BaseController
{
    @Autowired
    private IBusCustomerService busCustomerService;

    /**
     * 查询客户管理列表
     */
    @PreAuthorize("@ss.hasPermi('business:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusCustomer busCustomer)
    {
        startPage();
        List<BusCustomer> list = busCustomerService.selectBusCustomerList(busCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户管理列表
     */
    @PreAuthorize("@ss.hasPermi('business:customer:export')")
    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusCustomer busCustomer)
    {
        List<BusCustomer> list = busCustomerService.selectBusCustomerList(busCustomer);
        ExcelUtil<BusCustomer> util = new ExcelUtil<BusCustomer>(BusCustomer.class);
        util.exportExcel(response, list, "客户管理数据");
    }

    /**
     * 获取客户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(busCustomerService.selectBusCustomerById(id));
    }

    /**
     * 新增客户管理
     */
    @PreAuthorize("@ss.hasPermi('business:customer:add')")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusCustomer busCustomer)
    {
        return toAjax(busCustomerService.insertBusCustomer(busCustomer));
    }

    /**
     * 修改客户管理
     */
    @PreAuthorize("@ss.hasPermi('business:customer:edit')")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusCustomer busCustomer)
    {
        return toAjax(busCustomerService.updateBusCustomer(busCustomer));
    }

    /**
     * 删除客户管理
     */
    @PreAuthorize("@ss.hasPermi('business:customer:remove')")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(busCustomerService.deleteBusCustomerByIds(ids));
    }
}
