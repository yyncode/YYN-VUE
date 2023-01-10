package cn.yyn.store.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.store.domain.ShopGoods;
import cn.yyn.store.mapper.ShopGoodsMapper;
import cn.yyn.store.service.IShopGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@Service
public class ShopGoodsServiceImpl implements IShopGoodsService 
{
    @Autowired
    private ShopGoodsMapper shopGoodsMapper;

    /**
     * 查询商品记录
     * 
     * @param goodsId 商品记录主键
     * @return 商品记录
     */
    @Override
    public ShopGoods selectShopGoodsByGoodsId(Long goodsId)
    {
        return shopGoodsMapper.selectShopGoodsByGoodsId(goodsId);
    }

    /**
     * 查询商品记录列表
     * 
     * @param shopGoods 商品记录
     * @return 商品记录
     */
    @Override
    public List<ShopGoods> selectShopGoodsList(ShopGoods shopGoods)
    {
        return shopGoodsMapper.selectShopGoodsList(shopGoods);
    }

    /**
     * 新增商品记录
     * 
     * @param shopGoods 商品记录
     * @return 结果
     */
    @Override
    public int insertShopGoods(ShopGoods shopGoods)
    {
        shopGoods.setCreateTime(DateUtils.getNowDate());
        return shopGoodsMapper.insertShopGoods(shopGoods);
    }

    /**
     * 修改商品记录
     * 
     * @param shopGoods 商品记录
     * @return 结果
     */
    @Override
    public int updateShopGoods(ShopGoods shopGoods)
    {
        shopGoods.setUpdateTime(DateUtils.getNowDate());
        return shopGoodsMapper.updateShopGoods(shopGoods);
    }

    /**
     * 批量删除商品记录
     * 
     * @param goodsIds 需要删除的商品记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsByGoodsIds(Long[] goodsIds)
    {
        return shopGoodsMapper.deleteShopGoodsByGoodsIds(goodsIds);
    }

    /**
     * 删除商品记录信息
     * 
     * @param goodsId 商品记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsByGoodsId(Long goodsId)
    {
        return shopGoodsMapper.deleteShopGoodsByGoodsId(goodsId);
    }
}
