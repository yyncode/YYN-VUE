package cn.yyn.store.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.store.domain.ShopArticle;
import cn.yyn.store.domain.ShopArticleCategory;
import cn.yyn.store.domain.ShopUploadFile;
import cn.yyn.store.mapper.ShopArticleCategoryMapper;
import cn.yyn.store.mapper.ShopArticleMapper;
import cn.yyn.store.mapper.ShopUploadFileMapper;
import cn.yyn.store.service.IShopArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 文章记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-04
 */
@Service
public class ShopArticleServiceImpl implements IShopArticleService 
{
    @Autowired
    private ShopArticleMapper shopArticleMapper;
    @Autowired
    private ShopArticleCategoryMapper shopArticleCategoryMapper;
    @Autowired
    private ShopUploadFileMapper shopUploadFileMapper;

    /**
     * 查询文章记录
     * 
     * @param articleId 文章记录主键
     * @return 文章记录
     */
    @Override
    public ShopArticle selectShopArticleByArticleId(Long articleId)
    {
        ShopArticle shopArticle = shopArticleMapper.selectShopArticleByArticleId(articleId);
        ShopUploadFile shopUploadFile = shopUploadFileMapper.selectShopUploadFileByFileId(shopArticle.getImageId());
        if (shopUploadFile!=null) {
            shopArticle.setImageUrl(shopUploadFile.getCover());
        }
        return shopArticle;
    }

    /**
     * 查询文章记录列表
     * 
     * @param shopArticle 文章记录
     * @return 文章记录
     */
    @Override
    public List<ShopArticle> selectShopArticleList(ShopArticle shopArticle)
    {
        List<ShopArticle> shopArticles = shopArticleMapper.selectShopArticleList(shopArticle);
        List<ShopArticleCategory> shopArticleCategories = shopArticleCategoryMapper.selectShopArticleCategoryList(new ShopArticleCategory());
        List<ShopUploadFile> shopUploadFiles = shopUploadFileMapper.selectShopUploadFileList(new ShopUploadFile());
        for (ShopArticle article : shopArticles) {
            Optional<ShopArticleCategory> shopArticleCategoryOptional =
                    shopArticleCategories.stream().filter(item -> article.getCategoryId().equals(item.getCategoryId())).findFirst();
            shopArticleCategoryOptional.ifPresent(shopArticleCategory -> article.setCategoryName(shopArticleCategory.getName()));

            Optional<ShopUploadFile> shopUploadFileOptional = shopUploadFiles.stream().filter(item -> article.getImageId().equals(item.getFileId())).findFirst();
            shopUploadFileOptional.ifPresent(shopUploadFile -> article.setImageUrl(shopUploadFile.getCover()));
        }
        return shopArticles;
    }

    /**
     * 新增文章记录
     * 
     * @param shopArticle 文章记录
     * @return 结果
     */
    @Override
    public int insertShopArticle(ShopArticle shopArticle)
    {
        shopArticle.setCreateTime(DateUtils.getNowDate());
        return shopArticleMapper.insertShopArticle(shopArticle);
    }

    /**
     * 修改文章记录
     * 
     * @param shopArticle 文章记录
     * @return 结果
     */
    @Override
    public int updateShopArticle(ShopArticle shopArticle)
    {
        shopArticle.setUpdateTime(DateUtils.getNowDate());
        return shopArticleMapper.updateShopArticle(shopArticle);
    }

    /**
     * 批量删除文章记录
     * 
     * @param articleIds 需要删除的文章记录主键
     * @return 结果
     */
    @Override
    public int deleteShopArticleByArticleIds(Long[] articleIds)
    {
        return shopArticleMapper.deleteShopArticleByArticleIds(articleIds);
    }

    /**
     * 删除文章记录信息
     * 
     * @param articleId 文章记录主键
     * @return 结果
     */
    @Override
    public int deleteShopArticleByArticleId(Long articleId)
    {
        return shopArticleMapper.deleteShopArticleByArticleId(articleId);
    }
}
