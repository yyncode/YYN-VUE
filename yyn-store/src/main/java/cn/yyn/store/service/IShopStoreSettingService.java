package cn.yyn.store.service;

import cn.yyn.store.domain.ShopStoreSetting;

import java.util.List;

/**
 * 商家设置记录Service接口
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public interface IShopStoreSettingService 
{
    /**
     * 查询商家设置记录
     * 
     * @param key 商家设置记录主键
     * @return 商家设置记录
     */
    public ShopStoreSetting selectShopStoreSettingByKey(String key);

    /**
     * 查询商家设置记录列表
     * 
     * @param shopStoreSetting 商家设置记录
     * @return 商家设置记录集合
     */
    public List<ShopStoreSetting> selectShopStoreSettingList(ShopStoreSetting shopStoreSetting);

    /**
     * 新增商家设置记录
     * 
     * @param shopStoreSetting 商家设置记录
     * @return 结果
     */
    public int insertShopStoreSetting(ShopStoreSetting shopStoreSetting);

    /**
     * 修改商家设置记录
     * 
     * @param shopStoreSetting 商家设置记录
     * @return 结果
     */
    public int updateShopStoreSetting(ShopStoreSetting shopStoreSetting);

    /**
     * 批量删除商家设置记录
     * 
     * @param keys 需要删除的商家设置记录主键集合
     * @return 结果
     */
    public int deleteShopStoreSettingByKeys(String[] keys);

    /**
     * 删除商家设置记录信息
     * 
     * @param key 商家设置记录主键
     * @return 结果
     */
    public int deleteShopStoreSettingByKey(String key);

    int saveShopStoreSetting(ShopStoreSetting shopStoreSetting);
}
