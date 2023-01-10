package cn.yyn.store.mapper;

import cn.yyn.store.domain.ShopDeliveryRule;

import java.util.List;

/**
 * 配送模板区域及运费Mapper接口
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public interface ShopDeliveryRuleMapper 
{
    /**
     * 查询配送模板区域及运费
     * 
     * @param ruleId 配送模板区域及运费主键
     * @return 配送模板区域及运费
     */
    public ShopDeliveryRule selectShopDeliveryRuleByRuleId(Long ruleId);

    /**
     * 查询配送模板区域及运费列表
     * 
     * @param shopDeliveryRule 配送模板区域及运费
     * @return 配送模板区域及运费集合
     */
    public List<ShopDeliveryRule> selectShopDeliveryRuleList(ShopDeliveryRule shopDeliveryRule);

    /**
     * 新增配送模板区域及运费
     * 
     * @param shopDeliveryRule 配送模板区域及运费
     * @return 结果
     */
    public int insertShopDeliveryRule(ShopDeliveryRule shopDeliveryRule);

    /**
     * 修改配送模板区域及运费
     * 
     * @param shopDeliveryRule 配送模板区域及运费
     * @return 结果
     */
    public int updateShopDeliveryRule(ShopDeliveryRule shopDeliveryRule);

    /**
     * 删除配送模板区域及运费
     * 
     * @param ruleId 配送模板区域及运费主键
     * @return 结果
     */
    public int deleteShopDeliveryRuleByRuleId(Long ruleId);

    /**
     * 批量删除配送模板区域及运费
     * 
     * @param ruleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopDeliveryRuleByRuleIds(Long[] ruleIds);

    /**
     * 根据模板ID删除配送模板区域及运费
     *
     * @param deliveryId 模板主键
     * @return 结果
     */
    void deleteShopDeliveryRuleByDeliveryId(Long deliveryId);

    /**
     * 根据模板ID查询配送模板区域及运费列表
     *
     * @param deliveryId 模板ID
     * @return 配送模板区域及运费集合
     */
    List<ShopDeliveryRule> selectShopDeliveryRulesBydeliveryId(Long deliveryId);
}
