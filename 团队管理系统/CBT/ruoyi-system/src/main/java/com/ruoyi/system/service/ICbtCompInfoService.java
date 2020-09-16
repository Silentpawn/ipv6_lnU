package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.CbtCompInfo;

/**
 * 竞赛属性Service接口
 *
 * @author wsl
 * @date 2020-06-16
 */
public interface ICbtCompInfoService {
    /**
     * 查询竞赛属性
     *
     * @param compId 竞赛属性ID
     * @return 竞赛属性
     */
    public CbtCompInfo selectCbtCompInfoById(Long compId);

    /**
     * 查询竞赛属性列表
     *
     * @param cbtCompInfo 竞赛属性
     * @return 竞赛属性集合
     */
    public List<CbtCompInfo> selectCbtCompInfoList(CbtCompInfo cbtCompInfo);

    /**
     * 新增竞赛属性
     *
     * @param cbtCompInfo 竞赛属性
     * @return 结果
     */
    public int insertCbtCompInfo(CbtCompInfo cbtCompInfo);

    /**
     * 修改竞赛属性
     *
     * @param cbtCompInfo 竞赛属性
     * @return 结果
     */
    public int updateCbtCompInfo(CbtCompInfo cbtCompInfo);

    /**
     * 批量删除竞赛属性
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCbtCompInfoByIds(String ids);

    /**
     * 删除竞赛属性信息
     *
     * @param compId 竞赛属性ID
     * @return 结果
     */
    public int deleteCbtCompInfoById(Long compId);
}
