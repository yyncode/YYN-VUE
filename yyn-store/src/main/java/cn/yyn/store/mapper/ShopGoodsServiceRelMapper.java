package cn.yyn.store.mapper;

import java.util.List;
import cn.yyn.store.domain.ShopGoodsServiceRel;

/**
 * 商品与服务承诺关系记录Mapper接口
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public interface ShopGoodsServiceRelMapper 
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
     * 删除商品与服务承诺关系记录
     * 
     * @param id 商品与服务承诺关系记录主键
     * @return 结果
     */
    public int deleteShopGoodsServiceRelById(Long id);

    /**
     * 批量删除商品与服务承诺关系记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopGoodsServiceRelByIds(Long[] ids);

    /**
     * 根据商品id删除商品与服务承诺关系记录
     * @param goodsId
     */
    void deleteShopGoodsServiceRelByGoodsId(Long goodsId);

    Long[] selectShopGoodsServiceRelByGoodsId(Long goodsId);
}
