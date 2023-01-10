package cn.yyn.store.service.impl;

import cn.yyn.store.domain.ShopRegion;
import cn.yyn.store.mapper.ShopRegionMapper;
import cn.yyn.store.service.IShopRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 省市区数据Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@Service
public class ShopRegionServiceImpl implements IShopRegionService 
{
    @Autowired
    private ShopRegionMapper shopRegionMapper;

    /**
     * 查询省市区数据
     * 
     * @param id 省市区数据主键
     * @return 省市区数据
     */
    @Override
    public ShopRegion selectShopRegionById(Long id)
    {
        return shopRegionMapper.selectShopRegionById(id);
    }

    /**
     * 查询省市区数据列表
     * 
     * @param shopRegion 省市区数据
     * @return 省市区数据
     */
    @Override
    public List<ShopRegion> selectShopRegionList(ShopRegion shopRegion)
    {
        return shopRegionMapper.selectShopRegionList(shopRegion);
    }

    /**
     * 新增省市区数据
     * 
     * @param shopRegion 省市区数据
     * @return 结果
     */
    @Override
    public int insertShopRegion(ShopRegion shopRegion)
    {
        return shopRegionMapper.insertShopRegion(shopRegion);
    }

    /**
     * 修改省市区数据
     * 
     * @param shopRegion 省市区数据
     * @return 结果
     */
    @Override
    public int updateShopRegion(ShopRegion shopRegion)
    {
        return shopRegionMapper.updateShopRegion(shopRegion);
    }

    /**
     * 批量删除省市区数据
     * 
     * @param ids 需要删除的省市区数据主键
     * @return 结果
     */
    @Override
    public int deleteShopRegionByIds(Long[] ids)
    {
        return shopRegionMapper.deleteShopRegionByIds(ids);
    }

    /**
     * 删除省市区数据信息
     * 
     * @param id 省市区数据主键
     * @return 结果
     */
    @Override
    public int deleteShopRegionById(Long id)
    {
        return shopRegionMapper.deleteShopRegionById(id);
    }
}
