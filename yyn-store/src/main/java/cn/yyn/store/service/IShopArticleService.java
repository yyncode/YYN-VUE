package cn.yyn.store.service;

import cn.yyn.store.domain.ShopArticle;

import java.util.List;

/**
 * 文章记录Service接口
 * 
 * @author yyncode
 * @date 2023-01-04
 */
public interface IShopArticleService 
{
    /**
     * 查询文章记录
     * 
     * @param articleId 文章记录主键
     * @return 文章记录
     */
    public ShopArticle selectShopArticleByArticleId(Long articleId);

    /**
     * 查询文章记录列表
     * 
     * @param shopArticle 文章记录
     * @return 文章记录集合
     */
    public List<ShopArticle> selectShopArticleList(ShopArticle shopArticle);

    /**
     * 新增文章记录
     * 
     * @param shopArticle 文章记录
     * @return 结果
     */
    public int insertShopArticle(ShopArticle shopArticle);

    /**
     * 修改文章记录
     * 
     * @param shopArticle 文章记录
     * @return 结果
     */
    public int updateShopArticle(ShopArticle shopArticle);

    /**
     * 批量删除文章记录
     * 
     * @param articleIds 需要删除的文章记录主键集合
     * @return 结果
     */
    public int deleteShopArticleByArticleIds(Long[] articleIds);

    /**
     * 删除文章记录信息
     * 
     * @param articleId 文章记录主键
     * @return 结果
     */
    public int deleteShopArticleByArticleId(Long articleId);
}
