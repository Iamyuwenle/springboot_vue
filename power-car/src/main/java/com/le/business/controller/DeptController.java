package com.le.business.controller;

import com.le.common.core.controller.BaseController;
import com.le.common.core.domain.AjaxResult;
import com.le.common.core.domain.entity.SysDept;
import com.le.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/business/dept")
public class DeptController extends BaseController {

    @Autowired
    private ISysDeptService deptService;

    @RequestMapping("/list")
    public AjaxResult queryDept() {
        List<SysDept> depts = deptService.selectAllDept();
        System.out.println("12334455667");
        return AjaxResult.success("查询成功", depts);
    }
}
