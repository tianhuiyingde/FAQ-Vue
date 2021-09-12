package com.ruoyi.aixuexi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 业务模块对象 axx_servicemodule
 * 
 * @author tianhuiying
 * @date 2021-07-19
 */
public class AxxServicemodule extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 业务模块id */
    private Long servicemoduleId;

    /** 业务模块名称 */
    @Excel(name = "业务模块名称")
    private String servicemoduleName;

    /** 负责人 */
    @Excel(name = "负责人")
    private String leader;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 业务模块状态（0正常 1停用） */
    @Excel(name = "业务模块状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setServicemoduleId(Long servicemoduleId) 
    {
        this.servicemoduleId = servicemoduleId;
    }

    public Long getServicemoduleId() 
    {
        return servicemoduleId;
    }
    public void setServicemoduleName(String servicemoduleName) 
    {
        this.servicemoduleName = servicemoduleName;
    }

    public String getServicemoduleName() 
    {
        return servicemoduleName;
    }
    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("servicemoduleId", getServicemoduleId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("servicemoduleName", getServicemoduleName())
            .append("orderNum", getOrderNum())
            .append("leader", getLeader())
            .append("email", getEmail())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
