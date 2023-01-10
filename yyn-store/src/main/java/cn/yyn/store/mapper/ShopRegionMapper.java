package cn.yyn.store.mapper;

import cn.yyn.store.domain.ShopRegion;

import java.util.List;

/**
 * 省市区数据Mapper接口
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public interface ShopRegionMapper 
{
    /**
     * 查询省市区数据
     * 
     * @param id 省市区数据主键
     * @return 省市区数据
     */
    public ShopRegion selectShopRegionById(Long id);

    /**
     * 查询省市区数据列表
     * 
     * @param shopRegion 省市区数据
     * @return 省市区数据集合
     */
    public List<ShopRegion> selectShopRegionList(ShopRegion shopRegion);

    /**
     * 新增省市区数据
     * 
     * @param shopRegion 省市区数据
     * @return 结果
     */
    public int insertShopRegion(ShopRegion shopRegion);

    /**
     * 修改省市区数据
     * 
     * @param shopRegion 省市区数据
     * @return 结果
     */
    public int updateShopRegion(ShopRegion shopRegion);

    /**
     * 删除省市区数据
     * 
     * @param id 省市区数据主键
     * @return 结果
     */
    public int deleteShopRegionById(Long id);

    /**
     * 批量删除省市区数据
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopRegionByIds(Long[] ids);
}
