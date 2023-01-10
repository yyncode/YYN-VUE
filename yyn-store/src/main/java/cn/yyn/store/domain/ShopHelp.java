package cn.yyn.store.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 帮助中心记录对象 shop_help
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public class ShopHelp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long helpId;

    /** 帮助标题 */
    @Excel(name = "帮助标题")
    private String title;

    /** 帮助内容 */
    @Excel(name = "帮助内容")
    private String content;

    /** 排序(数字越小越靠前) */
    @Excel(name = "排序(数字越小越靠前)")
    private Long sort;

    /** 是否删除(1已删除) */
    @Excel(name = "是否删除(1已删除)")
    private Integer isDelete;

    /** 商城ID */
    @Excel(name = "商城ID")
    private Long storeId;

    public void setHelpId(Long helpId) 
    {
        this.helpId = helpId;
    }

    public Long getHelpId() 
    {
        return helpId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
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
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("helpId", getHelpId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("sort", getSort())
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
