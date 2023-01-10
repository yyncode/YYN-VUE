package cn.yyn.store.service;

import cn.yyn.store.domain.ShopGoods;

import java.util.List;

/**
 * 商品记录Service接口
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public interface IShopGoodsService 
{
    /**
     * 查询商品记录
     * 
     * @param goodsId 商品记录主键
     * @return 商品记录
     */
    public ShopGoods selectShopGoodsByGoodsId(Long goodsId);

    /**
     * 查询商品记录列表
     * 
     * @param shopGoods 商品记录
     * @return 商品记录集合
     */
    public List<ShopGoods> selectShopGoodsList(ShopGoods shopGoods);

    /**
     * 新增商品记录
     * 
     * @param shopGoods 商品记录
     * @return 结果
     */
    public int insertShopGoods(ShopGoods shopGoods);

    /**
     * 修改商品记录
     * 
     * @param shopGoods 商品记录
     * @return 结果
     */
    public int updateShopGoods(ShopGoods shopGoods);

    /**
     * 批量删除商品记录
     * 
     * @param goodsIds 需要删除的商品记录主键集合
     * @return 结果
     */
    public int deleteShopGoodsByGoodsIds(Long[] goodsIds);

    /**
     * 删除商品记录信息
     * 
     * @param goodsId 商品记录主键
     * @return 结果
     */
    public int deleteShopGoodsByGoodsId(Long goodsId);
}
