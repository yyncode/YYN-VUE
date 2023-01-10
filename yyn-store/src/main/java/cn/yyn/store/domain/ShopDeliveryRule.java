package cn.yyn.store.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 配送模板区域及运费对象 shop_delivery_rule
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public class ShopDeliveryRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规则ID */
    private Long ruleId;

    /** 配送模板ID */
    @Excel(name = "配送模板ID")
    private Long deliveryId;

    /** 可配送区域(城市id集) */
    @Excel(name = "可配送区域(城市id集)")
    private String region;

    /** 可配送区域(文字展示) */
    @Excel(name = "可配送区域(文字展示)")
    private String regionText;

    /** 首件(个)/首重(Kg) */
    @Excel(name = "首件(个)/首重(Kg)")
    private String first;

    /** 运费(元) */
    @Excel(name = "运费(元)")
    private BigDecimal firstFee;

    /** 续件/续重 */
    @Excel(name = "续件/续重")
    private String additional;

    /** 续费(元) */
    @Excel(name = "续费(元)")
    private BigDecimal additionalFee;

    /** 商城ID */
    @Excel(name = "商城ID")
    private Long storeId;

    public void setRuleId(Long ruleId) 
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId() 
    {
        return ruleId;
    }
    public void setDeliveryId(Long deliveryId) 
    {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId() 
    {
        return deliveryId;
    }
    public void setRegion(String region) 
    {
        this.region = region;
    }

    public String getRegion() 
    {
        return region;
    }
    public void setRegionText(String regionText) 
    {
        this.regionText = regionText;
    }

    public String getRegionText() 
    {
        return regionText;
    }
    public void setFirst(String first) 
    {
        this.first = first;
    }

    public String getFirst() 
    {
        return first;
    }
    public void setFirstFee(BigDecimal firstFee) 
    {
        this.firstFee = firstFee;
    }

    public BigDecimal getFirstFee() 
    {
        return firstFee;
    }
    public void setAdditional(String additional) 
    {
        this.additional = additional;
    }

    public String getAdditional() 
    {
        return additional;
    }
    public void setAdditionalFee(BigDecimal additionalFee) 
    {
        this.additionalFee = additionalFee;
    }

    public BigDecimal getAdditionalFee() 
    {
        return additionalFee;
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
            .append("ruleId", getRuleId())
            .append("deliveryId", getDeliveryId())
            .append("region", getRegion())
            .append("regionText", getRegionText())
            .append("first", getFirst())
            .append("firstFee", getFirstFee())
            .append("additional", getAdditional())
            .append("additionalFee", getAdditionalFee())
            .append("storeId", getStoreId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
