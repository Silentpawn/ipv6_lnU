package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CbtCompInfoMapper;
import com.ruoyi.system.domain.CbtCompInfo;
import com.ruoyi.system.service.ICbtCompInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 竞赛属性Service业务层处理
 *
 * @author wsl
 * @date 2020-06-16
 */
@Service
public class CbtCompInfoServiceImpl implements ICbtCompInfoService {
    @Autowired
    private CbtCompInfoMapper cbtCompInfoMapper;

    /**
     * 查询竞赛属性
     *
     * @param compId 竞赛属性ID
     * @return 竞赛属性
     */
    @Override
    public CbtCompInfo selectCbtCompInfoById(Long compId) {
        return cbtCompInfoMapper.selectCbtCompInfoById(compId);
    }

    /**
     * 查询竞赛属性列表
     *
     * @param cbtCompInfo 竞赛属性
     * @return 竞赛属性
     */
    @Override
    public List<CbtCompInfo> selectCbtCompInfoList(CbtCompInfo cbtCompInfo) {
        return cbtCompInfoMapper.selectCbtCompInfoList(cbtCompInfo);
    }

    /**
     * 新增竞赛属性
     *
     * @param cbtCompInfo 竞赛属性
     * @return 结果
     */
    @Override
    public int insertCbtCompInfo(CbtCompInfo cbtCompInfo) {
        cbtCompInfo.setCreateTime(DateUtils.getNowDate());
        return cbtCompInfoMapper.insertCbtCompInfo(cbtCompInfo);
    }

    /**
     * 修改竞赛属性
     *
     * @param cbtCompInfo 竞赛属性
     * @return 结果
     */
    @Override
    public int updateCbtCompInfo(CbtCompInfo cbtCompInfo) {
        cbtCompInfo.setUpdateTime(DateUtils.getNowDate());
        return cbtCompInfoMapper.updateCbtCompInfo(cbtCompInfo);
    }

    /**
     * 删除竞赛属性对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCbtCompInfoByIds(String ids) {
        return cbtCompInfoMapper.deleteCbtCompInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除竞赛属性信息
     *
     * @param compId 竞赛属性ID
     * @return 结果
     */
    @Override
    public int deleteCbtCompInfoById(Long compId) {
        return cbtCompInfoMapper.deleteCbtCompInfoById(compId);
    }
}
