package cn.yyn.store.mapper;

import java.util.List;
import cn.yyn.store.domain.ShopGoodsImage;

/**
 * 商品图片记录Mapper接口
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public interface ShopGoodsImageMapper 
{
    /**
     * 查询商品图片记录
     * 
     * @param id 商品图片记录主键
     * @return 商品图片记录
     */
    public ShopGoodsImage selectShopGoodsImageById(Long id);

    /**
     * 查询商品图片记录列表
     * 
     * @param shopGoodsImage 商品图片记录
     * @return 商品图片记录集合
     */
    public List<ShopGoodsImage> selectShopGoodsImageList(ShopGoodsImage shopGoodsImage);

    /**
     * 新增商品图片记录
     * 
     * @param shopGoodsImage 商品图片记录
     * @return 结果
     */
    public int insertShopGoodsImage(ShopGoodsImage shopGoodsImage);

    /**
     * 修改商品图片记录
     * 
     * @param shopGoodsImage 商品图片记录
     * @return 结果
     */
    public int updateShopGoodsImage(ShopGoodsImage shopGoodsImage);

    /**
     * 删除商品图片记录
     * 
     * @param id 商品图片记录主键
     * @return 结果
     */
    public int deleteShopGoodsImageById(Long id);

    /**
     * 批量删除商品图片记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopGoodsImageByIds(Long[] ids);

    /**
     * 根据商品id删除商品图片关联
     * @param goodsId
     */
    void deleteShopGoodsImageByGoodsId(Long goodsId);

    Long[] selectShopGoodsImageByGoodsId(Long goodsId);
}
