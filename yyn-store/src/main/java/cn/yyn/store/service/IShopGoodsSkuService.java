package cn.yyn.store.service;

import cn.yyn.store.domain.ShopGoodsSku;

import java.util.List;

/**
 * 商品规格Service接口
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public interface IShopGoodsSkuService 
{
    /**
     * 查询商品规格
     * 
     * @param id 商品规格主键
     * @return 商品规格
     */
    public ShopGoodsSku selectShopGoodsSkuById(Long id);

    /**
     * 查询商品规格列表
     * 
     * @param shopGoodsSku 商品规格
     * @return 商品规格集合
     */
    public List<ShopGoodsSku> selectShopGoodsSkuList(ShopGoodsSku shopGoodsSku);

    /**
     * 新增商品规格
     * 
     * @param shopGoodsSku 商品规格
     * @return 结果
     */
    public int insertShopGoodsSku(ShopGoodsSku shopGoodsSku);

    /**
     * 修改商品规格
     * 
     * @param shopGoodsSku 商品规格
     * @return 结果
     */
    public int updateShopGoodsSku(ShopGoodsSku shopGoodsSku);

    /**
     * 批量删除商品规格
     * 
     * @param ids 需要删除的商品规格主键集合
     * @return 结果
     */
    public int deleteShopGoodsSkuByIds(Long[] ids);

    /**
     * 删除商品规格信息
     * 
     * @param id 商品规格主键
     * @return 结果
     */
    public int deleteShopGoodsSkuById(Long id);
}
