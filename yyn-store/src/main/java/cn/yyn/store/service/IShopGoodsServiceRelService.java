package cn.yyn.store.service;

import cn.yyn.store.domain.ShopGoodsServiceRel;

import java.util.List;

/**
 * 商品与服务承诺关系记录Service接口
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public interface IShopGoodsServiceRelService 
{
    /**
     * 查询商品与服务承诺关系记录
     * 
     * @param id 商品与服务承诺关系记录主键
     * @return 商品与服务承诺关系记录
     */
    public ShopGoodsServiceRel selectShopGoodsServiceRelById(Long id);

    /**
     * 查询商品与服务承诺关系记录列表
     * 
     * @param shopGoodsServiceRel 商品与服务承诺关系记录
     * @return 商品与服务承诺关系记录集合
     */
    public List<ShopGoodsServiceRel> selectShopGoodsServiceRelList(ShopGoodsServiceRel shopGoodsServiceRel);

    /**
     * 新增商品与服务承诺关系记录
     * 
     * @param shopGoodsServiceRel 商品与服务承诺关系记录
     * @return 结果
     */
    public int insertShopGoodsServiceRel(ShopGoodsServiceRel shopGoodsServiceRel);

    /**
     * 修改商品与服务承诺关系记录
     * 
     * @param shopGoodsServiceRel 商品与服务承诺关系记录
     * @return 结果
     */
    public int updateShopGoodsServiceRel(ShopGoodsServiceRel shopGoodsServiceRel);

    /**
     * 批量删除商品与服务承诺关系记录
     * 
     * @param ids 需要删除的商品与服务承诺关系记录主键集合
     * @return 结果
     */
    public int deleteShopGoodsServiceRelByIds(Long[] ids);

    /**
     * 删除商品与服务承诺关系记录信息
     * 
     * @param id 商品与服务承诺关系记录主键
     * @return 结果
     */
    public int deleteShopGoodsServiceRelById(Long id);
}
