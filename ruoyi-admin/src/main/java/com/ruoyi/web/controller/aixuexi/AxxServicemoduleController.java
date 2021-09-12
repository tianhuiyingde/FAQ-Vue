package com.ruoyi.web.controller.aixuexi;

import java.util.List;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.aixuexi.domain.AxxServicemodule;
import com.ruoyi.aixuexi.service.IAxxServicemoduleService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 业务模块Controller
 * 
 * @author tianhuiying
 * @date 2021-07-19
 */
@RestController
@RequestMapping("/aixuexi/servicemodule")
public class AxxServicemoduleController extends BaseController
{
    @Autowired
    private IAxxServicemoduleService axxServicemoduleService;

    /**
     * 查询业务模块列表
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:servicemodule:list')")
    @GetMapping("/list")
    public AjaxResult list(AxxServicemodule axxServicemodule)
    {
        List<AxxServicemodule> list = axxServicemoduleService.selectAxxServicemoduleList(axxServicemodule);
        return AjaxResult.success(list);
    }

    /**
     * 导出业务模块列表
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:servicemodule:export')")
    @Log(title = "业务模块", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AxxServicemodule axxServicemodule)
    {
        List<AxxServicemodule> list = axxServicemoduleService.selectAxxServicemoduleList(axxServicemodule);
        ExcelUtil<AxxServicemodule> util = new ExcelUtil<AxxServicemodule>(AxxServicemodule.class);
        return util.exportExcel(list, "业务模块数据");
    }

    /**
     * 获取业务模块详细信息
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:servicemodule:query')")
    @GetMapping(value = "/{servicemoduleId}")
    public AjaxResult getInfo(@PathVariable("servicemoduleId") Long servicemoduleId)
    {
        return AjaxResult.success(axxServicemoduleService.selectAxxServicemoduleById(servicemoduleId));
    }

    /**
     * 新增业务模块
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:servicemodule:add')")
    @Log(title = "业务模块", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AxxServicemodule axxServicemodule)
    {
        return toAjax(axxServicemoduleService.insertAxxServicemodule(axxServicemodule));
    }

    /**
     * 修改业务模块
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:servicemodule:edit')")
    @Log(title = "业务模块", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AxxServicemodule axxServicemodule)
    {
        return toAjax(axxServicemoduleService.updateAxxServicemodule(axxServicemodule));
    }

    /**
     * 删除业务模块
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:servicemodule:remove')")
    @Log(title = "业务模块", businessType = BusinessType.DELETE)
	@DeleteMapping("/{servicemoduleIds}")
    public AjaxResult remove(@PathVariable Long[] servicemoduleIds)
    {
        return toAjax(axxServicemoduleService.deleteAxxServicemoduleByIds(servicemoduleIds));
    }
}
