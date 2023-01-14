package cn.yyn.store.service.impl;

import java.util.List;
import cn.yyn.common.utils.DateUtils;
import cn.yyn.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import cn.yyn.store.mapper.ShopStoreSettingMapper;
import cn.yyn.store.domain.ShopStoreSetting;
import cn.yyn.store.service.IShopStoreSettingService;

/**
 * 商家设置记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@Service
public class ShopStoreSettingServiceImpl implements IShopStoreSettingService
{
    @Value("${store.id}")
    private String storeId;
    @Autowired
    private ShopStoreSettingMapper shopStoreSettingMapper;

    /**
     * 查询商家设置记录
     * 
     * @param key 商家设置记录主键
     * @return 商家设置记录
     */
    @Override
    public ShopStoreSetting selectShopStoreSettingByKey(String key)
    {
        return shopStoreSettingMapper.selectShopStoreSettingByKey(key,Long.parseLong(storeId));
    }

    /**
     * 查询商家设置记录列表
     * 
     * @param shopStoreSetting 商家设置记录
     * @return 商家设置记录
     */
    @Override
    public List<ShopStoreSetting> selectShopStoreSettingList(ShopStoreSetting shopStoreSetting)
    {
        return shopStoreSettingMapper.selectShopStoreSettingList(shopStoreSetting);
    }

    /**
     * 新增商家设置记录
     * 
     * @param shopStoreSetting 商家设置记录
     * @return 结果
     */
    @Override
    public int insertShopStoreSetting(ShopStoreSetting shopStoreSetting)
    {

        shopStoreSetting.setCreateTime(DateUtils.getNowDate());
        return shopStoreSettingMapper.insertShopStoreSetting(shopStoreSetting);
    }

    /**
     * 修改商家设置记录
     * 
     * @param shopStoreSetting 商家设置记录
     * @return 结果
     */
    @Override
    public int updateShopStoreSetting(ShopStoreSetting shopStoreSetting)
    {
        shopStoreSetting.setUpdateTime(DateUtils.getNowDate());
        return shopStoreSettingMapper.updateShopStoreSetting(shopStoreSetting);
    }

    /**
     * 批量删除商家设置记录
     * 
     * @param keys 需要删除的商家设置记录主键
     * @return 结果
     */
    @Override
    public int deleteShopStoreSettingByKeys(String[] keys)
    {
        return shopStoreSettingMapper.deleteShopStoreSettingByKeys(keys);
    }

    /**
     * 删除商家设置记录信息
     * 
     * @param key 商家设置记录主键
     * @return 结果
     */
    @Override
    public int deleteShopStoreSettingByKey(String key)
    {
        return shopStoreSettingMapper.deleteShopStoreSettingByKey(key);
    }

    @Override
    public int saveShopStoreSetting(ShopStoreSetting shopStoreSetting) {
        shopStoreSetting.setStoreId(Long.parseLong(storeId));
        ShopStoreSetting get = shopStoreSettingMapper.selectShopStoreSettingByKey(shopStoreSetting.getKey(),Long.parseLong(storeId));
        if (StringUtils.isNull(get)) {
            return shopStoreSettingMapper.insertShopStoreSetting(shopStoreSetting);
        } else {
            return shopStoreSettingMapper.updateShopStoreSetting(shopStoreSetting);
        }
    }
}
