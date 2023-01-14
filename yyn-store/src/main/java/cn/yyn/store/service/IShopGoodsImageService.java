package cn.yyn.store.service;

import cn.yyn.store.domain.ShopGoodsImage;

import java.util.List;

/**
 * 商品图片记录Service接口
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public interface IShopGoodsImageService 
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
     * 批量删除商品图片记录
     * 
     * @param ids 需要删除的商品图片记录主键集合
     * @return 结果
     */
    public int deleteShopGoodsImageByIds(Long[] ids);

    /**
     * 删除商品图片记录信息
     * 
     * @param id 商品图片记录主键
     * @return 结果
     */
    public int deleteShopGoodsImageById(Long id);
}
