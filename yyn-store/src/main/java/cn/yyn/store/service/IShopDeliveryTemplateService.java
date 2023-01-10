package cn.yyn.store.service;

import cn.yyn.store.domain.ShopDeliveryTemplate;

import java.util.List;

/**
 * 配送模板主Service接口
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public interface IShopDeliveryTemplateService
{
    /**
     * 查询配送模板主
     * 
     * @param deliveryId 配送模板主主键
     * @return 配送模板主
     */
    public ShopDeliveryTemplate selectShopDeliveryByDeliveryId(Long deliveryId);

    /**
     * 查询配送模板主列表
     * 
     * @param shopDeliveryTemplate 配送模板主
     * @return 配送模板主集合
     */
    public List<ShopDeliveryTemplate> selectShopDeliveryList(ShopDeliveryTemplate shopDeliveryTemplate);

    /**
     * 新增配送模板主
     * 
     * @param shopDeliveryTemplate 配送模板主
     * @return 结果
     */
    public int insertShopDelivery(ShopDeliveryTemplate shopDeliveryTemplate);

    /**
     * 修改配送模板主
     * 
     * @param shopDeliveryTemplate 配送模板主
     * @return 结果
     */
    public int updateShopDelivery(ShopDeliveryTemplate shopDeliveryTemplate);

    /**
     * 批量删除配送模板主
     * 
     * @param deliveryIds 需要删除的配送模板主主键集合
     * @return 结果
     */
    public int deleteShopDeliveryByDeliveryIds(Long[] deliveryIds);

    /**
     * 删除配送模板主信息
     * 
     * @param deliveryId 配送模板主主键
     * @return 结果
     */
    public int deleteShopDeliveryByDeliveryId(Long deliveryId);
}