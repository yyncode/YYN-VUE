package cn.yyn.store.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.store.domain.ShopGoodsService;
import cn.yyn.store.mapper.ShopGoodsServiceMapper;
import cn.yyn.store.service.IShopGoodsServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务与承诺记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@Service
public class ShopGoodsServiceServiceImpl implements IShopGoodsServiceService 
{
    @Autowired
    private ShopGoodsServiceMapper shopGoodsServiceMapper;

    /**
     * 查询商品服务与承诺记录
     * 
     * @param serviceId 商品服务与承诺记录主键
     * @return 商品服务与承诺记录
     */
    @Override
    public ShopGoodsService selectShopGoodsServiceByServiceId(Long serviceId)
    {
        return shopGoodsServiceMapper.selectShopGoodsServiceByServiceId(serviceId);
    }

    /**
     * 查询商品服务与承诺记录列表
     * 
     * @param shopGoodsService 商品服务与承诺记录
     * @return 商品服务与承诺记录
     */
    @Override
    public List<ShopGoodsService> selectShopGoodsServiceList(ShopGoodsService shopGoodsService)
    {
        return shopGoodsServiceMapper.selectShopGoodsServiceList(shopGoodsService);
    }

    /**
     * 新增商品服务与承诺记录
     * 
     * @param shopGoodsService 商品服务与承诺记录
     * @return 结果
     */
    @Override
    public int insertShopGoodsService(ShopGoodsService shopGoodsService)
    {
        shopGoodsService.setCreateTime(DateUtils.getNowDate());
        return shopGoodsServiceMapper.insertShopGoodsService(shopGoodsService);
    }

    /**
     * 修改商品服务与承诺记录
     * 
     * @param shopGoodsService 商品服务与承诺记录
     * @return 结果
     */
    @Override
    public int updateShopGoodsService(ShopGoodsService shopGoodsService)
    {
        shopGoodsService.setUpdateTime(DateUtils.getNowDate());
        return shopGoodsServiceMapper.updateShopGoodsService(shopGoodsService);
    }

    /**
     * 批量删除商品服务与承诺记录
     * 
     * @param serviceIds 需要删除的商品服务与承诺记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsServiceByServiceIds(Long[] serviceIds)
    {
        return shopGoodsServiceMapper.deleteShopGoodsServiceByServiceIds(serviceIds);
    }

    /**
     * 删除商品服务与承诺记录信息
     * 
     * @param serviceId 商品服务与承诺记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsServiceByServiceId(Long serviceId)
    {
        return shopGoodsServiceMapper.deleteShopGoodsServiceByServiceId(serviceId);
    }
}
