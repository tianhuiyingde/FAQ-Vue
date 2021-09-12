package com.ruoyi.aixuexi.service;

import java.util.List;
import com.ruoyi.aixuexi.domain.AxxQuestion;

/**
 * 问题Service接口
 * 
 * @author tianhuiying
 * @date 2021-07-19
 */
public interface IAxxQuestionService 
{
    /**
     * 查询问题
     * 
     * @param questionId 问题ID
     * @return 问题
     */
    public AxxQuestion selectAxxQuestionById(Long questionId);

    /**
     * 查询问题列表
     * 
     * @param axxQuestion 问题
     * @return 问题集合
     */
    public List<AxxQuestion> selectAxxQuestionList(AxxQuestion axxQuestion);

    /**
     * 新增问题
     * 
     * @param axxQuestion 问题
     * @return 结果
     */
    public int insertAxxQuestion(AxxQuestion axxQuestion);

    /**
     * 修改问题
     * 
     * @param axxQuestion 问题
     * @return 结果
     */
    public int updateAxxQuestion(AxxQuestion axxQuestion);

    /**
     * 批量删除问题
     * 
     * @param questionIds 需要删除的问题ID
     * @return 结果
     */
    public int deleteAxxQuestionByIds(Long[] questionIds);

    /**
     * 删除问题信息
     * 
     * @param questionId 问题ID
     * @return 结果
     */
    public int deleteAxxQuestionById(Long questionId);

    /**
     * 导入问题数据
     *
     * @param questionList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importQuestion(List<AxxQuestion> questionList, Boolean isUpdateSupport, String operName);
}
