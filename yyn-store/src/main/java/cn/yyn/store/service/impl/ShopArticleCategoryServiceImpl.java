package cn.yyn.store.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.store.domain.ShopArticleCategory;
import cn.yyn.store.mapper.ShopArticleCategoryMapper;
import cn.yyn.store.service.IShopArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.yyn.common.utils.SecurityUtils.getUsername;

/**
 * 文章分类Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-04
 */
@Service
public class ShopArticleCategoryServiceImpl implements IShopArticleCategoryService 
{
    @Autowired
    private ShopArticleCategoryMapper shopArticleCategoryMapper;

    @Value("${store.id}")
    private String storeId;

    /**
     * 查询文章分类
     * 
     * @param categoryId 文章分类主键
     * @return 文章分类
     */
    @Override
    public ShopArticleCategory selectShopArticleCategoryByCategoryId(Long categoryId)
    {
        return shopArticleCategoryMapper.selectShopArticleCategoryByCategoryId(categoryId);
    }

    /**
     * 查询文章分类列表
     * 
     * @param shopArticleCategory 文章分类
     * @return 文章分类
     */
    @Override
    public List<ShopArticleCategory> selectShopArticleCategoryList(ShopArticleCategory shopArticleCategory)
    {
        shopArticleCategory.setStoreId(Long.parseLong(storeId));
        return shopArticleCategoryMapper.selectShopArticleCategoryList(shopArticleCategory);
    }

    /**
     * 新增文章分类
     * 
     * @param shopArticleCategory 文章分类
     * @return 结果
     */
    @Override
    public int insertShopArticleCategory(ShopArticleCategory shopArticleCategory)
    {
        shopArticleCategory.setStoreId(Long.parseLong(storeId));
        shopArticleCategory.setCreateBy(getUsername());
        shopArticleCategory.setCreateTime(DateUtils.getNowDate());
        return shopArticleCategoryMapper.insertShopArticleCategory(shopArticleCategory);
    }

    /**
     * 修改文章分类
     * 
     * @param shopArticleCategory 文章分类
     * @return 结果
     */
    @Override
    public int updateShopArticleCategory(ShopArticleCategory shopArticleCategory)
    {
        shopArticleCategory.setStoreId(Long.parseLong(storeId));
        shopArticleCategory.setUpdateBy(getUsername());
        shopArticleCategory.setUpdateTime(DateUtils.getNowDate());
        return shopArticleCategoryMapper.updateShopArticleCategory(shopArticleCategory);
    }

    /**
     * 批量删除文章分类
     * 
     * @param categoryIds 需要删除的文章分类主键
     * @return 结果
     */
    @Override
    public int deleteShopArticleCategoryByCategoryIds(Long[] categoryIds)
    {
        return shopArticleCategoryMapper.deleteShopArticleCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除文章分类信息
     * 
     * @param categoryId 文章分类主键
     * @return 结果
     */
    @Override
    public int deleteShopArticleCategoryByCategoryId(Long categoryId)
    {
        return shopArticleCategoryMapper.deleteShopArticleCategoryByCategoryId(categoryId);
    }
}
