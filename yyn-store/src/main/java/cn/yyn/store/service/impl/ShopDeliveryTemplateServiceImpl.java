package cn.yyn.store.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.store.domain.ShopDeliveryRule;
import cn.yyn.store.domain.ShopDeliveryTemplate;
import cn.yyn.store.mapper.ShopDeliveryRuleMapper;
import cn.yyn.store.mapper.ShopDeliveryTemplateMapper;
import cn.yyn.store.service.IShopDeliveryTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.yyn.common.utils.SecurityUtils.getUsername;

/**
 * 配送模板主Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@Service
public class ShopDeliveryTemplateServiceImpl implements IShopDeliveryTemplateService
{
    @Autowired
    private ShopDeliveryTemplateMapper shopDeliveryTemplateMapper;

    @Autowired
    private ShopDeliveryRuleMapper shopDeliveryRuleMapper;
    @Value("${store.id}")
    private String storeId;

    /**
     * 查询配送模板主
     * 
     * @param deliveryId 配送模板主主键
     * @return 配送模板主
     */
    @Override
    public ShopDeliveryTemplate selectShopDeliveryByDeliveryId(Long deliveryId)
    {
        ShopDeliveryTemplate shopDeliveryTemplate = shopDeliveryTemplateMapper.selectShopDeliveryByDeliveryId(deliveryId);
        List<ShopDeliveryRule> ruleList = shopDeliveryRuleMapper.selectShopDeliveryRulesBydeliveryId(deliveryId);
        shopDeliveryTemplate.setRuleList(ruleList);
        return shopDeliveryTemplate;
    }

    /**
     * 查询配送模板主列表
     * 
     * @param shopDeliveryTemplate 配送模板主
     * @return 配送模板主
     */
    @Override
    public List<ShopDeliveryTemplate> selectShopDeliveryList(ShopDeliveryTemplate shopDeliveryTemplate)
    {
        shopDeliveryTemplate.setStoreId(Long.parseLong(storeId));
        return shopDeliveryTemplateMapper.selectShopDeliveryList(shopDeliveryTemplate);
    }

    /**
     * 新增配送模板主
     * 
     * @param shopDeliveryTemplate 配送模板主
     * @return 结果
     */
    @Override
    public int insertShopDelivery(ShopDeliveryTemplate shopDeliveryTemplate)
    {
        shopDeliveryTemplate.setStoreId(Long.parseLong(storeId));
        shopDeliveryTemplate.setCreateBy(getUsername());
        shopDeliveryTemplate.setCreateTime(DateUtils.getNowDate());
        shopDeliveryTemplate.setUpdateBy(getUsername());
        shopDeliveryTemplate.setUpdateTime(DateUtils.getNowDate());
        shopDeliveryTemplateMapper.insertShopDelivery(shopDeliveryTemplate);
        saveRules(shopDeliveryTemplate);
        return 1;
    }

    /**
     * 修改配送模板主
     * 
     * @param shopDeliveryTemplate 配送模板主
     * @return 结果
     */
    @Override
    public int updateShopDelivery(ShopDeliveryTemplate shopDeliveryTemplate)
    {
        shopDeliveryRuleMapper.deleteShopDeliveryRuleByDeliveryId(shopDeliveryTemplate.getDeliveryId());
        shopDeliveryTemplate.setUpdateBy(getUsername());
        shopDeliveryTemplate.setUpdateTime(DateUtils.getNowDate());
        shopDeliveryTemplateMapper.updateShopDelivery(shopDeliveryTemplate);
        saveRules(shopDeliveryTemplate);
        return 1;
    }

    private void saveRules(ShopDeliveryTemplate shopDeliveryTemplate) {
        List<ShopDeliveryRule> ruleList = shopDeliveryTemplate.getRuleList();
        for (ShopDeliveryRule shopDeliveryRule : ruleList) {
            shopDeliveryRule.setDeliveryId(shopDeliveryTemplate.getDeliveryId());
            shopDeliveryRule.setStoreId(Long.parseLong(storeId));
            shopDeliveryRule.setCreateBy(getUsername());
            shopDeliveryRule.setCreateTime(DateUtils.getNowDate());
            shopDeliveryRule.setUpdateBy(getUsername());
            shopDeliveryRule.setUpdateTime(DateUtils.getNowDate());
            shopDeliveryRuleMapper.insertShopDeliveryRule(shopDeliveryRule);
        }
    }

    /**
     * 批量删除配送模板主
     * 
     * @param deliveryIds 需要删除的配送模板主主键
     * @return 结果
     */
    @Override
    public int deleteShopDeliveryByDeliveryIds(Long[] deliveryIds)
    {
        return shopDeliveryTemplateMapper.deleteShopDeliveryByDeliveryIds(deliveryIds);
    }

    /**
     * 删除配送模板主信息
     * 
     * @param deliveryId 配送模板主主键
     * @return 结果
     */
    @Override
    public int deleteShopDeliveryByDeliveryId(Long deliveryId)
    {
        return shopDeliveryTemplateMapper.deleteShopDeliveryByDeliveryId(deliveryId);
    }
}
