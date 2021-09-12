package com.ruoyi.web.controller.aixuexi;

import java.util.List;

import com.ruoyi.common.utils.ServletUtils;
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
import com.ruoyi.aixuexi.domain.AxxQuestion;
import com.ruoyi.aixuexi.service.IAxxQuestionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 问题Controller
 * 
 * @author tianhuiying
 * @date 2021-07-19
 */
@RestController
@RequestMapping("/aixuexi/question")
public class AxxQuestionController extends BaseController
{
    @Autowired
    private IAxxQuestionService axxQuestionService;

    /**
     * 查询问题列表
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:question:list')")
    @GetMapping("/list")
    public TableDataInfo list(AxxQuestion axxQuestion)
    {
        startPage();
        List<AxxQuestion> list = axxQuestionService.selectAxxQuestionList(axxQuestion);
        return getDataTable(list);
    }

    /**
     * 导出问题列表
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:question:export')")
    @Log(title = "问题管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AxxQuestion axxQuestion)
    {
        List<AxxQuestion> list = axxQuestionService.selectAxxQuestionList(axxQuestion);
        ExcelUtil<AxxQuestion> util = new ExcelUtil<AxxQuestion>(AxxQuestion.class);
        return util.exportExcel(list, "问题数据");
    }

    /**
     * 导入问题数据
     * @param file
     * @param updateSupport
     * @return
     * @throws Exception
     */
    @Log(title = "问题管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('aixuexi:question:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<AxxQuestion> util = new ExcelUtil<AxxQuestion>(AxxQuestion.class);
        List<AxxQuestion> questionList = util.importExcel(file.getInputStream());
//        AxxQuestion axxQuestion = axxQuestionService.selectAxxQuestionList(ServletUtils.getRequest());
//        String operName = loginUser.getUsername();
//        String message = userService.importUser(questionList, updateSupport, operName);
        return AjaxResult.success();
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<AxxQuestion> util = new ExcelUtil<AxxQuestion>(AxxQuestion.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 获取问题详细信息
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:question:query')")
    @GetMapping(value = "/{questionId}")
    public AjaxResult getInfo(@PathVariable("questionId") Long questionId)
    {
        return AjaxResult.success(axxQuestionService.selectAxxQuestionById(questionId));
    }

    /**
     * 新增问题
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:question:add')")
    @Log(title = "问题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AxxQuestion axxQuestion)
    {
        return toAjax(axxQuestionService.insertAxxQuestion(axxQuestion));
    }

    /**
     * 修改问题
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:question:edit')")
    @Log(title = "问题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AxxQuestion axxQuestion)
    {
        return toAjax(axxQuestionService.updateAxxQuestion(axxQuestion));
    }

    /**
     * 删除问题
     */
    @PreAuthorize("@ss.hasPermi('aixuexi:question:remove')")
    @Log(title = "问题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{questionIds}")
    public AjaxResult remove(@PathVariable Long[] questionIds)
    {
        return toAjax(axxQuestionService.deleteAxxQuestionByIds(questionIds));
    }
}
