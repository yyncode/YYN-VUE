package cn.yyn.store.service.impl;

import java.util.List;
import cn.yyn.common.utils.DateUtils;
import cn.yyn.store.domain.ShopRegion;
import cn.yyn.store.mapper.ShopRegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.yyn.store.mapper.ShopStoreAddressMapper;
import cn.yyn.store.domain.ShopStoreAddress;
import cn.yyn.store.service.IShopStoreAddressService;

/**
 * 商家地址记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-12
 */
@Service
public class ShopStoreAddressServiceImpl implements IShopStoreAddressService 
{
    @Autowired
    private ShopStoreAddressMapper shopStoreAddressMapper;

    @Autowired
    private ShopRegionMapper shopRegionMapper;

    /**
     * 查询商家地址记录
     * 
     * @param addressId 商家地址记录主键
     * @return 商家地址记录
     */
    @Override
    public ShopStoreAddress selectShopStoreAddressByAddressId(Long addressId)
    {
        return shopStoreAddressMapper.selectShopStoreAddressByAddressId(addressId);
    }

    /**
     * 查询商家地址记录列表
     * 
     * @param shopStoreAddress 商家地址记录
     * @return 商家地址记录
     */
    @Override
    public List<ShopStoreAddress> selectShopStoreAddressList(ShopStoreAddress shopStoreAddress)
    {
        List<ShopStoreAddress> shopStoreAddresses = shopStoreAddressMapper.selectShopStoreAddressList(shopStoreAddress);
        List<ShopRegion> shopRegions = shopRegionMapper.selectShopRegionList(new ShopRegion());
        for (ShopStoreAddress storeAddress : shopStoreAddresses) {
            storeAddress.setDetail(
                    shopRegions.stream().filter(item -> item.getId().equals(storeAddress.getProvinceId())).findFirst().get().getName() +
                            shopRegions.stream().filter(item -> item.getId().equals(storeAddress.getCityId())).findFirst().get().getName() +
                            shopRegions.stream().filter(item -> item.getId().equals(storeAddress.getRegionId())).findFirst().get().getName() +
                            storeAddress.getDetail()
                    );
        }
        return shopStoreAddresses;
    }

    /**
     * 新增商家地址记录
     * 
     * @param shopStoreAddress 商家地址记录
     * @return 结果
     */
    @Override
    public int insertShopStoreAddress(ShopStoreAddress shopStoreAddress)
    {
        shopStoreAddress.setCreateTime(DateUtils.getNowDate());
        return shopStoreAddressMapper.insertShopStoreAddress(shopStoreAddress);
    }

    /**
     * 修改商家地址记录
     * 
     * @param shopStoreAddress 商家地址记录
     * @return 结果
     */
    @Override
    public int updateShopStoreAddress(ShopStoreAddress shopStoreAddress)
    {
        shopStoreAddress.setUpdateTime(DateUtils.getNowDate());
        return shopStoreAddressMapper.updateShopStoreAddress(shopStoreAddress);
    }

    /**
     * 批量删除商家地址记录
     * 
     * @param addressIds 需要删除的商家地址记录主键
     * @return 结果
     */
    @Override
    public int deleteShopStoreAddressByAddressIds(Long[] addressIds)
    {
        return shopStoreAddressMapper.deleteShopStoreAddressByAddressIds(addressIds);
    }

    /**
     * 删除商家地址记录信息
     * 
     * @param addressId 商家地址记录主键
     * @return 结果
     */
    @Override
    public int deleteShopStoreAddressByAddressId(Long addressId)
    {
        return shopStoreAddressMapper.deleteShopStoreAddressByAddressId(addressId);
    }
}
