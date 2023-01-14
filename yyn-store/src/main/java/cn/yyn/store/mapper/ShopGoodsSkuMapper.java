package cn.yyn.store.mapper;

import java.util.List;
import cn.yyn.store.domain.ShopGoodsSku;

/**
 * 商品规格Mapper接口
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public interface ShopGoodsSkuMapper 
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
     * 删除商品规格
     * 
     * @param id 商品规格主键
     * @return 结果
     */
    public int deleteShopGoodsSkuById(Long id);

    /**
     * 批量删除商品规格
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopGoodsSkuByIds(Long[] ids);

    void deleteShopGoodsSkuByGoodsId(Long goodsId);

    List<ShopGoodsSku> selectShopGoodsSkuByGoodsId(Long goodsId);
}
