package cn.yyn.store.service.impl;

import java.util.List;
import cn.yyn.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.yyn.store.mapper.ShopGoodsImageMapper;
import cn.yyn.store.domain.ShopGoodsImage;
import cn.yyn.store.service.IShopGoodsImageService;

/**
 * 商品图片记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@Service
public class ShopGoodsImageServiceImpl implements IShopGoodsImageService 
{
    @Autowired
    private ShopGoodsImageMapper shopGoodsImageMapper;

    /**
     * 查询商品图片记录
     * 
     * @param id 商品图片记录主键
     * @return 商品图片记录
     */
    @Override
    public ShopGoodsImage selectShopGoodsImageById(Long id)
    {
        return shopGoodsImageMapper.selectShopGoodsImageById(id);
    }

    /**
     * 查询商品图片记录列表
     * 
     * @param shopGoodsImage 商品图片记录
     * @return 商品图片记录
     */
    @Override
    public List<ShopGoodsImage> selectShopGoodsImageList(ShopGoodsImage shopGoodsImage)
    {
        return shopGoodsImageMapper.selectShopGoodsImageList(shopGoodsImage);
    }

    /**
     * 新增商品图片记录
     * 
     * @param shopGoodsImage 商品图片记录
     * @return 结果
     */
    @Override
    public int insertShopGoodsImage(ShopGoodsImage shopGoodsImage)
    {
        shopGoodsImage.setCreateTime(DateUtils.getNowDate());
        return shopGoodsImageMapper.insertShopGoodsImage(shopGoodsImage);
    }

    /**
     * 修改商品图片记录
     * 
     * @param shopGoodsImage 商品图片记录
     * @return 结果
     */
    @Override
    public int updateShopGoodsImage(ShopGoodsImage shopGoodsImage)
    {
        shopGoodsImage.setUpdateTime(DateUtils.getNowDate());
        return shopGoodsImageMapper.updateShopGoodsImage(shopGoodsImage);
    }

    /**
     * 批量删除商品图片记录
     * 
     * @param ids 需要删除的商品图片记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsImageByIds(Long[] ids)
    {
        return shopGoodsImageMapper.deleteShopGoodsImageByIds(ids);
    }

    /**
     * 删除商品图片记录信息
     * 
     * @param id 商品图片记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsImageById(Long id)
    {
        return shopGoodsImageMapper.deleteShopGoodsImageById(id);
    }
}
