package com.ruoyi.aixuexi.mapper;

import java.util.List;
import com.ruoyi.aixuexi.domain.AxxServicemodule;

/**
 * 业务模块Mapper接口
 * 
 * @author tianhuiying
 * @date 2021-07-19
 */
public interface AxxServicemoduleMapper 
{
    /**
     * 查询业务模块
     * 
     * @param servicemoduleId 业务模块ID
     * @return 业务模块
     */
    public AxxServicemodule selectAxxServicemoduleById(Long servicemoduleId);

    /**
     * 查询业务模块列表
     * 
     * @param axxServicemodule 业务模块
     * @return 业务模块集合
     */
    public List<AxxServicemodule> selectAxxServicemoduleList(AxxServicemodule axxServicemodule);

    /**
     * 新增业务模块
     * 
     * @param axxServicemodule 业务模块
     * @return 结果
     */
    public int insertAxxServicemodule(AxxServicemodule axxServicemodule);

    /**
     * 修改业务模块
     * 
     * @param axxServicemodule 业务模块
     * @return 结果
     */
    public int updateAxxServicemodule(AxxServicemodule axxServicemodule);

    /**
     * 删除业务模块
     * 
     * @param servicemoduleId 业务模块ID
     * @return 结果
     */
    public int deleteAxxServicemoduleById(Long servicemoduleId);

    /**
     * 批量删除业务模块
     * 
     * @param servicemoduleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAxxServicemoduleByIds(Long[] servicemoduleIds);
}
