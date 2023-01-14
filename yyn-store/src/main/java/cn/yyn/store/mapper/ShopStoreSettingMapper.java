package cn.yyn.store.mapper;

import java.util.List;
import cn.yyn.store.domain.ShopStoreSetting;
import org.apache.ibatis.annotations.Param;

/**
 * 商家设置记录Mapper接口
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public interface ShopStoreSettingMapper 
{
    /**
     * 查询商家设置记录
     * 
     * @param key 商家设置记录主键
     * @return 商家设置记录
     */
    public ShopStoreSetting selectShopStoreSettingByKey(@Param("key") String key, @Param("storeId") Long storeId);

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
     * 删除商家设置记录
     * 
     * @param key 商家设置记录主键
     * @return 结果
     */
    public int deleteShopStoreSettingByKey(String key);

    /**
     * 批量删除商家设置记录
     * 
     * @param keys 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopStoreSettingByKeys(String[] keys);
}
