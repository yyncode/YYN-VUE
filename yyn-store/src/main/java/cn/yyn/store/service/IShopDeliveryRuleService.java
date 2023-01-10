package cn.yyn.store.service;

import cn.yyn.store.domain.ShopDeliveryRule;

import java.util.List;

/**
 * 配送模板区域及运费Service接口
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public interface IShopDeliveryRuleService 
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
     * 批量删除配送模板区域及运费
     * 
     * @param ruleIds 需要删除的配送模板区域及运费主键集合
     * @return 结果
     */
    public int deleteShopDeliveryRuleByRuleIds(Long[] ruleIds);

    /**
     * 删除配送模板区域及运费信息
     * 
     * @param ruleId 配送模板区域及运费主键
     * @return 结果
     */
    public int deleteShopDeliveryRuleByRuleId(Long ruleId);
}
