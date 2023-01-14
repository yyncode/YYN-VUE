package cn.yyn.store.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.store.domain.ShopExpress;
import cn.yyn.store.mapper.ShopExpressMapper;
import cn.yyn.store.service.IShopExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物流公司记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-12
 */
@Service
public class ShopExpressServiceImpl implements IShopExpressService 
{
    @Autowired
    private ShopExpressMapper shopExpressMapper;

    /**
     * 查询物流公司记录
     * 
     * @param expressId 物流公司记录主键
     * @return 物流公司记录
     */
    @Override
    public ShopExpress selectShopExpressByExpressId(Long expressId)
    {
        return shopExpressMapper.selectShopExpressByExpressId(expressId);
    }

    /**
     * 查询物流公司记录列表
     * 
     * @param shopExpress 物流公司记录
     * @return 物流公司记录
     */
    @Override
    public List<ShopExpress> selectShopExpressList(ShopExpress shopExpress)
    {
        return shopExpressMapper.selectShopExpressList(shopExpress);
    }

    /**
     * 新增物流公司记录
     * 
     * @param shopExpress 物流公司记录
     * @return 结果
     */
    @Override
    public int insertShopExpress(ShopExpress shopExpress)
    {
        shopExpress.setCreateTime(DateUtils.getNowDate());
        return shopExpressMapper.insertShopExpress(shopExpress);
    }

    /**
     * 修改物流公司记录
     * 
     * @param shopExpress 物流公司记录
     * @return 结果
     */
    @Override
    public int updateShopExpress(ShopExpress shopExpress)
    {
        shopExpress.setUpdateTime(DateUtils.getNowDate());
        return shopExpressMapper.updateShopExpress(shopExpress);
    }

    /**
     * 批量删除物流公司记录
     * 
     * @param expressIds 需要删除的物流公司记录主键
     * @return 结果
     */
    @Override
    public int deleteShopExpressByExpressIds(Long[] expressIds)
    {
        return shopExpressMapper.deleteShopExpressByExpressIds(expressIds);
    }

    /**
     * 删除物流公司记录信息
     * 
     * @param expressId 物流公司记录主键
     * @return 结果
     */
    @Override
    public int deleteShopExpressByExpressId(Long expressId)
    {
        return shopExpressMapper.deleteShopExpressByExpressId(expressId);
    }
}
