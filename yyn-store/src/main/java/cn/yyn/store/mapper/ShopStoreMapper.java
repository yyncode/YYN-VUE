package cn.yyn.store.mapper;

import cn.yyn.store.domain.ShopStore;

import java.util.List;

public interface ShopStoreMapper {
    /**
     * 查询商家(商户)记录
     *
     * @param storeId 商家(商户)记录主键
     * @return 商家(商户)记录
     */
    public ShopStore selectShopStoreByStoreId(Long storeId);

    /**
     * 查询商家(商户)记录列表
     *
     * @param shopStore 商家(商户)记录
     * @return 商家(商户)记录集合
     */
    public List<ShopStore> selectShopStoreList(ShopStore shopStore);

    /**
     * 新增商家(商户)记录
     *
     * @param shopStore 商家(商户)记录
     * @return 结果
     */
    public int insertShopStore(ShopStore shopStore);

    /**
     * 修改商家(商户)记录
     *
     * @param shopStore 商家(商户)记录
     * @return 结果
     */
    public int updateShopStore(ShopStore shopStore);

    /**
     * 删除商家(商户)记录
     *
     * @param storeId 商家(商户)记录主键
     * @return 结果
     */
    public int deleteShopStoreByStoreId(Long storeId);

    /**
     * 批量删除商家(商户)记录
     *
     * @param storeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopStoreByStoreIds(Long[] storeIds);
}
