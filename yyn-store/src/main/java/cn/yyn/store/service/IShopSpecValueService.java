package cn.yyn.store.service;

import cn.yyn.store.domain.ShopSpecValue;

import java.util.List;

/**
 * 商品规格值记录Service接口
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public interface IShopSpecValueService 
{
    /**
     * 查询商品规格值记录
     * 
     * @param specValueId 商品规格值记录主键
     * @return 商品规格值记录
     */
    public ShopSpecValue selectShopSpecValueBySpecValueId(Long specValueId);

    /**
     * 查询商品规格值记录列表
     * 
     * @param shopSpecValue 商品规格值记录
     * @return 商品规格值记录集合
     */
    public List<ShopSpecValue> selectShopSpecValueList(ShopSpecValue shopSpecValue);

    /**
     * 新增商品规格值记录
     * 
     * @param shopSpecValue 商品规格值记录
     * @return 结果
     */
    public int insertShopSpecValue(ShopSpecValue shopSpecValue);

    /**
     * 修改商品规格值记录
     * 
     * @param shopSpecValue 商品规格值记录
     * @return 结果
     */
    public int updateShopSpecValue(ShopSpecValue shopSpecValue);

    /**
     * 批量删除商品规格值记录
     * 
     * @param specValueIds 需要删除的商品规格值记录主键集合
     * @return 结果
     */
    public int deleteShopSpecValueBySpecValueIds(Long[] specValueIds);

    /**
     * 删除商品规格值记录信息
     * 
     * @param specValueId 商品规格值记录主键
     * @return 结果
     */
    public int deleteShopSpecValueBySpecValueId(Long specValueId);
}
