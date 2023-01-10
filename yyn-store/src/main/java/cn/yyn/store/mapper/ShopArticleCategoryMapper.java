package cn.yyn.store.mapper;

import cn.yyn.store.domain.ShopArticleCategory;

import java.util.List;

/**
 * 文章分类Mapper接口
 * 
 * @author yyncode
 * @date 2023-01-04
 */
public interface ShopArticleCategoryMapper 
{
    /**
     * 查询文章分类
     * 
     * @param categoryId 文章分类主键
     * @return 文章分类
     */
    public ShopArticleCategory selectShopArticleCategoryByCategoryId(Long categoryId);

    /**
     * 查询文章分类列表
     * 
     * @param shopArticleCategory 文章分类
     * @return 文章分类集合
     */
    public List<ShopArticleCategory> selectShopArticleCategoryList(ShopArticleCategory shopArticleCategory);

    /**
     * 新增文章分类
     * 
     * @param shopArticleCategory 文章分类
     * @return 结果
     */
    public int insertShopArticleCategory(ShopArticleCategory shopArticleCategory);

    /**
     * 修改文章分类
     * 
     * @param shopArticleCategory 文章分类
     * @return 结果
     */
    public int updateShopArticleCategory(ShopArticleCategory shopArticleCategory);

    /**
     * 删除文章分类
     * 
     * @param categoryId 文章分类主键
     * @return 结果
     */
    public int deleteShopArticleCategoryByCategoryId(Long categoryId);

    /**
     * 批量删除文章分类
     * 
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopArticleCategoryByCategoryIds(Long[] categoryIds);
}
