package cn.yyn.store.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;

/**
 * 店铺页面记录对象 shop_page
 * 
 * @author yyncode
 * @date 2023-01-12
 */
public class ShopPage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 页面ID */
    private Long pageId;

    /** 页面类型(10首页 20自定义页) */
    @Excel(name = "页面类型(10首页 20自定义页)")
    private Integer pageType;

    /** 页面名称 */
    @Excel(name = "页面名称")
    private String pageName;

    /** 页面数据 */
    @Excel(name = "页面数据")
    private String pageData;

    /** 商城ID */
    @Excel(name = "商城ID")
    private Long storeId;

    /** 软删除 */
    @Excel(name = "软删除")
    private Integer isDelete;

    public void setPageId(Long pageId) 
    {
        this.pageId = pageId;
    }

    public Long getPageId() 
    {
        return pageId;
    }
    public void setPageType(Integer pageType) 
    {
        this.pageType = pageType;
    }

    public Integer getPageType() 
    {
        return pageType;
    }
    public void setPageName(String pageName) 
    {
        this.pageName = pageName;
    }

    public String getPageName() 
    {
        return pageName;
    }
    public void setPageData(String pageData) 
    {
        this.pageData = pageData;
    }

    public String getPageData() 
    {
        return pageData;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pageId", getPageId())
            .append("pageType", getPageType())
            .append("pageName", getPageName())
            .append("pageData", getPageData())
            .append("storeId", getStoreId())
            .append("isDelete", getIsDelete())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
