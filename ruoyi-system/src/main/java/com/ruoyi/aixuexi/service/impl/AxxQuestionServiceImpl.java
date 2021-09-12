package com.ruoyi.aixuexi.service.impl;

import java.util.List;

import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.aixuexi.mapper.AxxQuestionMapper;
import com.ruoyi.aixuexi.domain.AxxQuestion;
import com.ruoyi.aixuexi.service.IAxxQuestionService;

/**
 * 问题Service业务层处理
 * 
 * @author tianhuiying
 * @date 2021-07-19
 */
@Service
public class AxxQuestionServiceImpl implements IAxxQuestionService 
{
    private static final Logger log = LoggerFactory.getLogger(AxxQuestionServiceImpl.class);

    @Autowired
    private AxxQuestionMapper axxQuestionMapper;

    /**
     * 查询问题
     * 
     * @param questionId 问题ID
     * @return 问题
     */
    @Override
    public AxxQuestion selectAxxQuestionById(Long questionId)
    {
        return axxQuestionMapper.selectAxxQuestionById(questionId);
    }

    /**
     * 查询问题列表
     * 
     * @param axxQuestion 问题
     * @return 问题
     */
    @Override
    public List<AxxQuestion> selectAxxQuestionList(AxxQuestion axxQuestion)
    {
        return axxQuestionMapper.selectAxxQuestionList(axxQuestion);
    }

    /**
     * 新增问题
     * 
     * @param axxQuestion 问题
     * @return 结果
     */
    @Override
    public int insertAxxQuestion(AxxQuestion axxQuestion)
    {
        axxQuestion.setCreateTime(DateUtils.getNowDate());
        return axxQuestionMapper.insertAxxQuestion(axxQuestion);
    }

    /**
     * 修改问题
     * 
     * @param axxQuestion 问题
     * @return 结果
     */
    @Override
    public int updateAxxQuestion(AxxQuestion axxQuestion)
    {
        axxQuestion.setUpdateTime(DateUtils.getNowDate());
        return axxQuestionMapper.updateAxxQuestion(axxQuestion);
    }

    /**
     * 批量删除问题
     * 
     * @param questionIds 需要删除的问题ID
     * @return 结果
     */
    @Override
    public int deleteAxxQuestionByIds(Long[] questionIds)
    {
        return axxQuestionMapper.deleteAxxQuestionByIds(questionIds);
    }

    /**
     * 删除问题信息
     * 
     * @param questionId 问题ID
     * @return 结果
     */
    @Override
    public int deleteAxxQuestionById(Long questionId)
    {
        return axxQuestionMapper.deleteAxxQuestionById(questionId);
    }

    /**
     * 导入问题数据
     *
     * @param questionList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importQuestion(List<AxxQuestion> questionList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(questionList) || questionList.size() == 0)
        {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
//        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (AxxQuestion question : questionList)
        {
            try
            {
                // 验证是否存在这个用户
                AxxQuestion q = axxQuestionMapper.selectAxxQuestionById(question.getQuestionId());
                if (StringUtils.isNull(q))
                {
//                    question.setPassword(SecurityUtils.encryptPassword(password));
                    question.setCreateBy(operName);
                    this.insertAxxQuestion(question);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、问题 " + question.getQuestionDesc() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    question.setUpdateBy(operName);
                    this.updateAxxQuestion(question);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、问题 " + question.getQuestionDesc() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、问题 " + question.getQuestionDesc() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、问题 " + question.getQuestionDesc() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
