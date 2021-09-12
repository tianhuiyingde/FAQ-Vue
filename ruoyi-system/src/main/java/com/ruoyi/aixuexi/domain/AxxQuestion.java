package com.ruoyi.aixuexi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 问题对象 axx_question
 * 
 * @author tianhuiying
 * @date 2021-07-19
 */
public class AxxQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 问题id */
    @Excel(name = "问题id")
    private Long questionId;

    /** 业务模块id */
    @Excel(name = "业务模块id")
    private Long servicemoduleId;

    /** 问题概述 */
    @Excel(name = "问题概述")
    private String questionDesc;

    /** 问题答案 */
    @Excel(name = "问题答案")
    private String questionAnswer;

    /** 问题状态（0正常 1停用） */
    @Excel(name = "问题状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 问题关键字 */
    @Excel(name = "问题关键字")
    private String questionKey;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setQuestionId(Long questionId) 
    {
        this.questionId = questionId;
    }

    public Long getQuestionId() 
    {
        return questionId;
    }
    public void setServicemoduleId(Long servicemoduleId) 
    {
        this.servicemoduleId = servicemoduleId;
    }

    public Long getServicemoduleId() 
    {
        return servicemoduleId;
    }
    public void setQuestionDesc(String questionDesc) 
    {
        this.questionDesc = questionDesc;
    }

    public String getQuestionDesc() 
    {
        return questionDesc;
    }
    public void setQuestionAnswer(String questionAnswer) 
    {
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionAnswer() 
    {
        return questionAnswer;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setQuestionKey(String questionKey) 
    {
        this.questionKey = questionKey;
    }

    public String getQuestionKey() 
    {
        return questionKey;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("questionId", getQuestionId())
            .append("servicemoduleId", getServicemoduleId())
            .append("questionDesc", getQuestionDesc())
            .append("questionAnswer", getQuestionAnswer())
            .append("status", getStatus())
            .append("questionKey", getQuestionKey())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
