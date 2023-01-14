package cn.yyn.store.service;

import cn.yyn.store.domain.ShopGoodsSpecRel;

import java.util.List;

/**
 * 商品与规格值关系记录Service接口
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public interface IShopGoodsSpecRelService 
{
    /**
     * 查询商品与规格值关系记录
     * 
     * @param id 商品与规格值关系记录主键
     * @return 商品与规格值关系记录
     */
    public ShopGoodsSpecRel selectShopGoodsSpecRelById(Long id);

    /**
     * 查询商品与规格值关系记录列表
     * 
     * @param shopGoodsSpecRel 商品与规格值关系记录
     * @return 商品与规格值关系记录集合
     */
    public List<ShopGoodsSpecRel> selectShopGoodsSpecRelList(ShopGoodsSpecRel shopGoodsSpecRel);

    /**
     * 新增商品与规格值关系记录
     * 
     * @param shopGoodsSpecRel 商品与规格值关系记录
     * @return 结果
     */
    public int insertShopGoodsSpecRel(ShopGoodsSpecRel shopGoodsSpecRel);

    /**
     * 修改商品与规格值关系记录
     * 
     * @param shopGoodsSpecRel 商品与规格值关系记录
     * @return 结果
     */
    public int updateShopGoodsSpecRel(ShopGoodsSpecRel shopGoodsSpecRel);

    /**
     * 批量删除商品与规格值关系记录
     * 
     * @param ids 需要删除的商品与规格值关系记录主键集合
     * @return 结果
     */
    public int deleteShopGoodsSpecRelByIds(Long[] ids);

    /**
     * 删除商品与规格值关系记录信息
     * 
     * @param id 商品与规格值关系记录主键
     * @return 结果
     */
    public int deleteShopGoodsSpecRelById(Long id);
}
