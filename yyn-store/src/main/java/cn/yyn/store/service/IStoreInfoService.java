package cn.yyn.store.service;

import cn.yyn.store.domain.ShopStore;

import java.util.List;

public interface IStoreInfoService {
    /**
     * 查询商家(商户)记录列表
     *
     * @param shopStore 商家(商户)记录
     * @return 商家(商户)记录集合
     */
    public List<ShopStore> selectShopStoreList(ShopStore shopStore);
    /**
     * 查询商家(商户)记录
     *
     * @param storeId 商家(商户)记录主键
     * @return 商家(商户)记录
     */
    public ShopStore selectShopStoreByStoreId(Long storeId);

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
     * 批量删除商家(商户)记录
     *
     * @param storeIds 需要删除的商家(商户)记录主键集合
     * @return 结果
     */
    public int deleteShopStoreByStoreIds(Long[] storeIds);

    /**
     * 删除商家(商户)记录信息
     *
     * @param storeId 商家(商户)记录主键
     * @return 结果
     */
    public int deleteShopStoreByStoreId(Long storeId);
}
