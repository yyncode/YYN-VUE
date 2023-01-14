package cn.yyn.store.service;

import cn.yyn.store.domain.ShopGoodsCategoryRel;

import java.util.List;

/**
 * 商品与分类关系记录Service接口
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public interface IShopGoodsCategoryRelService 
{
    /**
     * 查询商品与分类关系记录
     * 
     * @param id 商品与分类关系记录主键
     * @return 商品与分类关系记录
     */
    public ShopGoodsCategoryRel selectShopGoodsCategoryRelById(Long id);

    /**
     * 查询商品与分类关系记录列表
     * 
     * @param shopGoodsCategoryRel 商品与分类关系记录
     * @return 商品与分类关系记录集合
     */
    public List<ShopGoodsCategoryRel> selectShopGoodsCategoryRelList(ShopGoodsCategoryRel shopGoodsCategoryRel);

    /**
     * 新增商品与分类关系记录
     * 
     * @param shopGoodsCategoryRel 商品与分类关系记录
     * @return 结果
     */
    public int insertShopGoodsCategoryRel(ShopGoodsCategoryRel shopGoodsCategoryRel);

    /**
     * 修改商品与分类关系记录
     * 
     * @param shopGoodsCategoryRel 商品与分类关系记录
     * @return 结果
     */
    public int updateShopGoodsCategoryRel(ShopGoodsCategoryRel shopGoodsCategoryRel);

    /**
     * 批量删除商品与分类关系记录
     * 
     * @param ids 需要删除的商品与分类关系记录主键集合
     * @return 结果
     */
    public int deleteShopGoodsCategoryRelByIds(Long[] ids);

    /**
     * 删除商品与分类关系记录信息
     * 
     * @param id 商品与分类关系记录主键
     * @return 结果
     */
    public int deleteShopGoodsCategoryRelById(Long id);
}
