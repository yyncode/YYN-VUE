package cn.yyn.store.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品服务与承诺记录对象 shop_goods_service
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public class ShopGoodsService extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品服务ID */
    private Long serviceId;

    /** 服务名称 */
    @Excel(name = "服务名称")
    private String name;

    /** 概述 */
    @Excel(name = "概述")
    private String summary;

    /** 是否默认(新增商品时) */
    @Excel(name = "是否默认(新增商品时)")
    private Integer isDefault;

    /** 状态(1显示 0隐藏) */
    @Excel(name = "状态(1显示 0隐藏)")
    private Integer status;

    /** 排序方式(数字越小越靠前) */
    @Excel(name = "排序方式(数字越小越靠前)")
    private Long sort;

    /** 是否删除(1已删除) */
    @Excel(name = "是否删除(1已删除)")
    private Integer isDelete;

    /** 商城ID */
    @Excel(name = "商城ID")
    private Long storeId;

    public void setServiceId(Long serviceId) 
    {
        this.serviceId = serviceId;
    }

    public Long getServiceId() 
    {
        return serviceId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSummary(String summary) 
    {
        this.summary = summary;
    }

    public String getSummary() 
    {
        return summary;
    }
    public void setIsDefault(Integer isDefault) 
    {
        this.isDefault = isDefault;
    }

    public Integer getIsDefault() 
    {
        return isDefault;
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
            .append("serviceId", getServiceId())
            .append("name", getName())
            .append("summary", getSummary())
            .append("isDefault", getIsDefault())
            .append("status", getStatus())
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
