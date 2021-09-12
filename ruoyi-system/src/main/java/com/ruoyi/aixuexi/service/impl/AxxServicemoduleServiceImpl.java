package com.ruoyi.aixuexi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.aixuexi.mapper.AxxServicemoduleMapper;
import com.ruoyi.aixuexi.domain.AxxServicemodule;
import com.ruoyi.aixuexi.service.IAxxServicemoduleService;

/**
 * 业务模块Service业务层处理
 * 
 * @author tianhuiying
 * @date 2021-07-19
 */
@Service
public class AxxServicemoduleServiceImpl implements IAxxServicemoduleService 
{
    @Autowired
    private AxxServicemoduleMapper axxServicemoduleMapper;

    /**
     * 查询业务模块
     * 
     * @param servicemoduleId 业务模块ID
     * @return 业务模块
     */
    @Override
    public AxxServicemodule selectAxxServicemoduleById(Long servicemoduleId)
    {
        return axxServicemoduleMapper.selectAxxServicemoduleById(servicemoduleId);
    }

    /**
     * 查询业务模块列表
     * 
     * @param axxServicemodule 业务模块
     * @return 业务模块
     */
    @Override
    public List<AxxServicemodule> selectAxxServicemoduleList(AxxServicemodule axxServicemodule)
    {
        return axxServicemoduleMapper.selectAxxServicemoduleList(axxServicemodule);
    }

    /**
     * 新增业务模块
     * 
     * @param axxServicemodule 业务模块
     * @return 结果
     */
    @Override
    public int insertAxxServicemodule(AxxServicemodule axxServicemodule)
    {
        axxServicemodule.setCreateTime(DateUtils.getNowDate());
        return axxServicemoduleMapper.insertAxxServicemodule(axxServicemodule);
    }

    /**
     * 修改业务模块
     * 
     * @param axxServicemodule 业务模块
     * @return 结果
     */
    @Override
    public int updateAxxServicemodule(AxxServicemodule axxServicemodule)
    {
        axxServicemodule.setUpdateTime(DateUtils.getNowDate());
        return axxServicemoduleMapper.updateAxxServicemodule(axxServicemodule);
    }

    /**
     * 批量删除业务模块
     * 
     * @param servicemoduleIds 需要删除的业务模块ID
     * @return 结果
     */
    @Override
    public int deleteAxxServicemoduleByIds(Long[] servicemoduleIds)
    {
        return axxServicemoduleMapper.deleteAxxServicemoduleByIds(servicemoduleIds);
    }

    /**
     * 删除业务模块信息
     * 
     * @param servicemoduleId 业务模块ID
     * @return 结果
     */
    @Override
    public int deleteAxxServicemoduleById(Long servicemoduleId)
    {
        return axxServicemoduleMapper.deleteAxxServicemoduleById(servicemoduleId);
    }
}
