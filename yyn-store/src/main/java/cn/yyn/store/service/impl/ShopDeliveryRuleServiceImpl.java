package cn.yyn.store.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.store.domain.ShopDeliveryRule;
import cn.yyn.store.mapper.ShopDeliveryRuleMapper;
import cn.yyn.store.service.IShopDeliveryRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 配送模板区域及运费Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@Service
public class ShopDeliveryRuleServiceImpl implements IShopDeliveryRuleService 
{
    @Autowired
    private ShopDeliveryRuleMapper shopDeliveryRuleMapper;

    /**
     * 查询配送模板区域及运费
     * 
     * @param ruleId 配送模板区域及运费主键
     * @return 配送模板区域及运费
     */
    @Override
    public ShopDeliveryRule selectShopDeliveryRuleByRuleId(Long ruleId)
    {
        return shopDeliveryRuleMapper.selectShopDeliveryRuleByRuleId(ruleId);
    }

    /**
     * 查询配送模板区域及运费列表
     * 
     * @param shopDeliveryRule 配送模板区域及运费
     * @return 配送模板区域及运费
     */
    @Override
    public List<ShopDeliveryRule> selectShopDeliveryRuleList(ShopDeliveryRule shopDeliveryRule)
    {
        return shopDeliveryRuleMapper.selectShopDeliveryRuleList(shopDeliveryRule);
    }

    /**
     * 新增配送模板区域及运费
     * 
     * @param shopDeliveryRule 配送模板区域及运费
     * @return 结果
     */
    @Override
    public int insertShopDeliveryRule(ShopDeliveryRule shopDeliveryRule)
    {
        shopDeliveryRule.setCreateTime(DateUtils.getNowDate());
        return shopDeliveryRuleMapper.insertShopDeliveryRule(shopDeliveryRule);
    }

    /**
     * 修改配送模板区域及运费
     * 
     * @param shopDeliveryRule 配送模板区域及运费
     * @return 结果
     */
    @Override
    public int updateShopDeliveryRule(ShopDeliveryRule shopDeliveryRule)
    {
        shopDeliveryRule.setUpdateTime(DateUtils.getNowDate());
        return shopDeliveryRuleMapper.updateShopDeliveryRule(shopDeliveryRule);
    }

    /**
     * 批量删除配送模板区域及运费
     * 
     * @param ruleIds 需要删除的配送模板区域及运费主键
     * @return 结果
     */
    @Override
    public int deleteShopDeliveryRuleByRuleIds(Long[] ruleIds)
    {
        return shopDeliveryRuleMapper.deleteShopDeliveryRuleByRuleIds(ruleIds);
    }

    /**
     * 删除配送模板区域及运费信息
     * 
     * @param ruleId 配送模板区域及运费主键
     * @return 结果
     */
    @Override
    public int deleteShopDeliveryRuleByRuleId(Long ruleId)
    {
        return shopDeliveryRuleMapper.deleteShopDeliveryRuleByRuleId(ruleId);
    }
}
