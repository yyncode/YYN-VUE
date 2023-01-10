package cn.yyn.store.service;

import cn.yyn.store.domain.ShopGoodsService;

import java.util.List;

/**
 * 商品服务与承诺记录Service接口
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public interface IShopGoodsServiceService 
{
    /**
     * 查询商品服务与承诺记录
     * 
     * @param serviceId 商品服务与承诺记录主键
     * @return 商品服务与承诺记录
     */
    public ShopGoodsService selectShopGoodsServiceByServiceId(Long serviceId);

    /**
     * 查询商品服务与承诺记录列表
     * 
     * @param shopGoodsService 商品服务与承诺记录
     * @return 商品服务与承诺记录集合
     */
    public List<ShopGoodsService> selectShopGoodsServiceList(ShopGoodsService shopGoodsService);

    /**
     * 新增商品服务与承诺记录
     * 
     * @param shopGoodsService 商品服务与承诺记录
     * @return 结果
     */
    public int insertShopGoodsService(ShopGoodsService shopGoodsService);

    /**
     * 修改商品服务与承诺记录
     * 
     * @param shopGoodsService 商品服务与承诺记录
     * @return 结果
     */
    public int updateShopGoodsService(ShopGoodsService shopGoodsService);

    /**
     * 批量删除商品服务与承诺记录
     * 
     * @param serviceIds 需要删除的商品服务与承诺记录主键集合
     * @return 结果
     */
    public int deleteShopGoodsServiceByServiceIds(Long[] serviceIds);

    /**
     * 删除商品服务与承诺记录信息
     * 
     * @param serviceId 商品服务与承诺记录主键
     * @return 结果
     */
    public int deleteShopGoodsServiceByServiceId(Long serviceId);
}
