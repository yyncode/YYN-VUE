package cn.yyn.store.mapper;

import java.util.List;
import cn.yyn.store.domain.ShopStoreAddress;

/**
 * 商家地址记录Mapper接口
 * 
 * @author yyncode
 * @date 2023-01-12
 */
public interface ShopStoreAddressMapper 
{
    /**
     * 查询商家地址记录
     * 
     * @param addressId 商家地址记录主键
     * @return 商家地址记录
     */
    public ShopStoreAddress selectShopStoreAddressByAddressId(Long addressId);

    /**
     * 查询商家地址记录列表
     * 
     * @param shopStoreAddress 商家地址记录
     * @return 商家地址记录集合
     */
    public List<ShopStoreAddress> selectShopStoreAddressList(ShopStoreAddress shopStoreAddress);

    /**
     * 新增商家地址记录
     * 
     * @param shopStoreAddress 商家地址记录
     * @return 结果
     */
    public int insertShopStoreAddress(ShopStoreAddress shopStoreAddress);

    /**
     * 修改商家地址记录
     * 
     * @param shopStoreAddress 商家地址记录
     * @return 结果
     */
    public int updateShopStoreAddress(ShopStoreAddress shopStoreAddress);

    /**
     * 删除商家地址记录
     * 
     * @param addressId 商家地址记录主键
     * @return 结果
     */
    public int deleteShopStoreAddressByAddressId(Long addressId);

    /**
     * 批量删除商家地址记录
     * 
     * @param addressIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopStoreAddressByAddressIds(Long[] addressIds);
}
