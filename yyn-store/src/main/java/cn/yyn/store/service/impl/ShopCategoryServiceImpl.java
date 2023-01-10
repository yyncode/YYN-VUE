package cn.yyn.store.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.store.domain.ShopCategory;
import cn.yyn.store.domain.ShopUploadFile;
import cn.yyn.store.mapper.ShopCategoryMapper;
import cn.yyn.store.mapper.ShopUploadFileMapper;
import cn.yyn.store.service.IShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static cn.yyn.common.utils.SecurityUtils.getUsername;

/**
 * 商品分类Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@Service
public class ShopCategoryServiceImpl implements IShopCategoryService 
{
    @Autowired
    private ShopCategoryMapper shopCategoryMapper;
    @Value("${store.id}")
    private String storeId;

    @Autowired
    private ShopUploadFileMapper shopUploadFileMapper;

    /**
     * 查询商品分类
     * 
     * @param categoryId 商品分类主键
     * @return 商品分类
     */
    @Override
    public ShopCategory selectShopCategoryByCategoryId(Long categoryId)
    {
        ShopCategory shopCategory = shopCategoryMapper.selectShopCategoryByCategoryId(categoryId);
        ShopUploadFile shopUploadFile = shopUploadFileMapper.selectShopUploadFileByFileId(shopCategory.getImageId());
        if (shopUploadFile!=null) {
            shopCategory.setImageUrl(shopUploadFile.getCover());
        }
        return shopCategory;
    }

    /**
     * 查询商品分类列表
     * 
     * @param shopCategory 商品分类
     * @return 商品分类
     */
    @Override
    public List<ShopCategory> selectShopCategoryList(ShopCategory shopCategory)
    {
        shopCategory.setStoreId(Long.parseLong(storeId));
        List<ShopCategory> shopCategories = shopCategoryMapper.selectShopCategoryList(shopCategory);
        List<ShopUploadFile> shopUploadFiles = shopUploadFileMapper.selectShopUploadFileList(new ShopUploadFile());
        for (ShopCategory category : shopCategories) {
            Optional<ShopUploadFile> shopUploadFileOptional = shopUploadFiles.stream().filter(item -> category.getImageId().equals(item.getFileId())).findFirst();
            shopUploadFileOptional.ifPresent(shopUploadFile -> category.setImageUrl(shopUploadFile.getCover()));
        }
        return shopCategories;
    }

    /**
     * 新增商品分类
     * 
     * @param shopCategory 商品分类
     * @return 结果
     */
    @Override
    public int insertShopCategory(ShopCategory shopCategory)
    {
        shopCategory.setStoreId(Long.parseLong(storeId));
        shopCategory.setCreateBy(getUsername());
        shopCategory.setCreateTime(DateUtils.getNowDate());
        return shopCategoryMapper.insertShopCategory(shopCategory);
    }

    /**
     * 修改商品分类
     * 
     * @param shopCategory 商品分类
     * @return 结果
     */
    @Override
    public int updateShopCategory(ShopCategory shopCategory)
    {
        shopCategory.setStoreId(Long.parseLong(storeId));
        shopCategory.setCreateBy(getUsername());
        shopCategory.setCreateTime(DateUtils.getNowDate());
        shopCategory.setUpdateBy(getUsername());
        shopCategory.setUpdateTime(DateUtils.getNowDate());
        return shopCategoryMapper.updateShopCategory(shopCategory);
    }

    /**
     * 批量删除商品分类
     * 
     * @param categoryIds 需要删除的商品分类主键
     * @return 结果
     */
    @Override
    public int deleteShopCategoryByCategoryIds(Long[] categoryIds)
    {
        return shopCategoryMapper.deleteShopCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除商品分类信息
     * 
     * @param categoryId 商品分类主键
     * @return 结果
     */
    @Override
    public int deleteShopCategoryByCategoryId(Long categoryId)
    {
        return shopCategoryMapper.deleteShopCategoryByCategoryId(categoryId);
    }
}
