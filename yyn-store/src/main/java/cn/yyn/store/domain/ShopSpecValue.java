package cn.yyn.store.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;

/**
 * 商品规格值记录对象 shop_spec_value
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public class ShopSpecValue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规格值ID */
    private Long specValueId;

    /** 规格值 */
    @Excel(name = "规格值")
    private String specValue;

    /** 规格组ID */
    @Excel(name = "规格组ID")
    private Long specId;

    /** 商城ID */
    @Excel(name = "商城ID")
    private Long storeId;

    public void setSpecValueId(Long specValueId) 
    {
        this.specValueId = specValueId;
    }

    public Long getSpecValueId() 
    {
        return specValueId;
    }
    public void setSpecValue(String specValue) 
    {
        this.specValue = specValue;
    }

    public String getSpecValue() 
    {
        return specValue;
    }
    public void setSpecId(Long specId) 
    {
        this.specId = specId;
    }

    public Long getSpecId() 
    {
        return specId;
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
            .append("specValueId", getSpecValueId())
            .append("specValue", getSpecValue())
            .append("specId", getSpecId())
            .append("storeId", getStoreId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
