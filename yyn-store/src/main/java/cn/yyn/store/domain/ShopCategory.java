package cn.yyn.store.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品分类对象 shop_category
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public class ShopCategory extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品分类ID */
    private Long categoryId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String name;

    /** 分类图片ID */
    @Excel(name = "分类图片ID")
    private Long imageId;

    private String imageUrl;

    /** 状态(1显示 0隐藏) */
    @Excel(name = "状态(1显示 0隐藏)")
    private Integer status;

    /** 排序方式(数字越小越靠前) */
    @Excel(name = "排序方式(数字越小越靠前)")
    private Long sort;

    /** 商城ID */
    @Excel(name = "商城ID")
    private Long storeId;

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setImageId(Long imageId) 
    {
        this.imageId = imageId;
    }

    public Long getImageId() 
    {
        return imageId;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
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
            .append("categoryId", getCategoryId())
            .append("name", getName())
            .append("parentId", getParentId())
            .append("imageId", getImageId())
            .append("status", getStatus())
            .append("sort", getSort())
            .append("storeId", getStoreId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}