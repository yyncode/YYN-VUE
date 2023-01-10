package cn.yyn.store.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 配送模板主对象 shop_delivery
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public class ShopDeliveryTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 模板ID */
    private Long deliveryId;

    /** 模板名称 */
    @Excel(name = "模板名称")
    private String name;

    /** 计费方式(10按件数 20按重量) */
    @Excel(name = "计费方式(10按件数 20按重量)")
    private Integer method;

    /** 排序方式(数字越小越靠前) */
    @Excel(name = "排序方式(数字越小越靠前)")
    private Long sort;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDelete;

    /** 小程序d */
    @Excel(name = "小程序d")
    private Long storeId;

    private List<ShopDeliveryRule> ruleList;

    public void setDeliveryId(Long deliveryId) 
    {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId() 
    {
        return deliveryId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setMethod(Integer method) 
    {
        this.method = method;
    }

    public Integer getMethod() 
    {
        return method;
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

    public List<ShopDeliveryRule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<ShopDeliveryRule> ruleList) {
        this.ruleList = ruleList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deliveryId", getDeliveryId())
            .append("name", getName())
            .append("method", getMethod())
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
