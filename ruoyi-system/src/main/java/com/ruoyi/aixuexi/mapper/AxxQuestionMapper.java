package com.ruoyi.aixuexi.mapper;

import java.util.List;
import com.ruoyi.aixuexi.domain.AxxQuestion;

/**
 * 问题Mapper接口
 * 
 * @author tianhuiying
 * @date 2021-07-19
 */
public interface AxxQuestionMapper 
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
     * 删除问题
     * 
     * @param questionId 问题ID
     * @return 结果
     */
    public int deleteAxxQuestionById(Long questionId);

    /**
     * 批量删除问题
     * 
     * @param questionIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAxxQuestionByIds(Long[] questionIds);
}
