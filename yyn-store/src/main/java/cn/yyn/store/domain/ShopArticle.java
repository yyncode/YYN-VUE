package cn.yyn.store.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文章记录对象 shop_article
 * 
 * @author yyncode
 * @date 2023-01-04
 */
public class ShopArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章ID */
    private Long articleId;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String title;

    /** 列表显示方式(10小图展示 20大图展示) */
    @Excel(name = "列表显示方式(10小图展示 20大图展示)")
    private Integer showType;

    /** 文章分类ID */
//    @Excel(name = "文章分类ID")
    private Long categoryId;
    /** 文章分类 */
    @Excel(name = "文章分类")
    private String categoryName;

    /** 封面图ID */
//    @Excel(name = "封面图ID")
    private Long imageId;

    /** 封面图 */
    @Excel(name = "封面图")
    private String imageUrl;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String content;

    /** 文章排序(数字越小越靠前) */
    @Excel(name = "文章排序(数字越小越靠前)")
    private Long sort;

    /** 文章状态(0隐藏 1显示) */
    @Excel(name = "文章状态(0隐藏 1显示)")
    private Integer status;

    /** 虚拟阅读量(仅用作展示) */
    @Excel(name = "虚拟阅读量(仅用作展示)")
    private Long virtualViews;

    /** 实际阅读量 */
    @Excel(name = "实际阅读量")
    private Long actualViews;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Long isDelete;

    /** 商城ID */
    @Excel(name = "商城ID")
    private Long storeId;

    public void setArticleId(Long articleId) 
    {
        this.articleId = articleId;
    }

    public Long getArticleId() 
    {
        return articleId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setShowType(Integer showType) 
    {
        this.showType = showType;
    }

    public Integer getShowType() 
    {
        return showType;
    }
    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setImageId(Long imageId) 
    {
        this.imageId = imageId;
    }

    public Long getImageId() 
    {
        return imageId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setVirtualViews(Long virtualViews) 
    {
        this.virtualViews = virtualViews;
    }

    public Long getVirtualViews() 
    {
        return virtualViews;
    }
    public void setActualViews(Long actualViews) 
    {
        this.actualViews = actualViews;
    }

    public Long getActualViews() 
    {
        return actualViews;
    }
    public void setIsDelete(Long isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Long getIsDelete() 
    {
        return isDelete;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("articleId", getArticleId())
            .append("title", getTitle())
            .append("showType", getShowType())
            .append("categoryId", getCategoryId())
            .append("imageId", getImageId())
            .append("content", getContent())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("virtualViews", getVirtualViews())
            .append("actualViews", getActualViews())
            .append("isDelete", getIsDelete())
            .append("storeId", getStoreId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
