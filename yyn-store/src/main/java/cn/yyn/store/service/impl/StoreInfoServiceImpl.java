package cn.yyn.store.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.store.domain.ShopStore;
import cn.yyn.store.mapper.ShopStoreMapper;
import cn.yyn.store.service.IStoreInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StoreInfoServiceImpl implements IStoreInfoService {

    @Resource
    private ShopStoreMapper shopStoreMapper;

    /**
     * 查询商家(商户)记录列表
     *
     * @param shopStore 商家(商户)记录
     * @return 商家(商户)记录
     */
    @Override
    public List<ShopStore> selectShopStoreList(ShopStore shopStore)
    {
        return shopStoreMapper.selectShopStoreList(shopStore);
    }


    /**
     * 查询商家(商户)记录
     *
     * @param storeId 商家(商户)记录主键
     * @return 商家(商户)记录
     */
    @Override
    public ShopStore selectShopStoreByStoreId(Long storeId) {
        return shopStoreMapper.selectShopStoreByStoreId(storeId);
    }


    /**
     * 新增商家(商户)记录
     *
     * @param shopStore 商家(商户)记录
     * @return 结果
     */
    @Override
    public int insertShopStore(ShopStore shopStore)
    {
        shopStore.setCreateTime(DateUtils.getNowDate());
        return shopStoreMapper.insertShopStore(shopStore);
    }

    /**
     * 修改商家(商户)记录
     *
     * @param shopStore 商家(商户)记录
     * @return 结果
     */
    @Override
    public int updateShopStore(ShopStore shopStore)
    {
        shopStore.setUpdateTime(DateUtils.getNowDate());
        return shopStoreMapper.updateShopStore(shopStore);
    }

    /**
     * 批量删除商家(商户)记录
     *
     * @param storeIds 需要删除的商家(商户)记录主键
     * @return 结果
     */
    @Override
    public int deleteShopStoreByStoreIds(Long[] storeIds)
    {
        return shopStoreMapper.deleteShopStoreByStoreIds(storeIds);
    }

    /**
     * 删除商家(商户)记录信息
     *
     * @param storeId 商家(商户)记录主键
     * @return 结果
     */
    @Override
    public int deleteShopStoreByStoreId(Long storeId)
    {
        return shopStoreMapper.deleteShopStoreByStoreId(storeId);
    }
}
