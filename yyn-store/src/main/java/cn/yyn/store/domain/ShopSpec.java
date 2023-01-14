package cn.yyn.store.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;

/**
 * 商品规格组记录对象 shop_spec
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public class ShopSpec extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 规格组ID */
    private Long specId;

    /** 规格组名称 */
    @Excel(name = "规格组名称")
    private String specName;

    /** 商城ID */
    @Excel(name = "商城ID")
    private Long storeId;

    public void setSpecId(Long specId) 
    {
        this.specId = specId;
    }

    public Long getSpecId() 
    {
        return specId;
    }
    public void setSpecName(String specName) 
    {
        this.specName = specName;
    }

    public String getSpecName() 
    {
        return specName;
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
            .append("specId", getSpecId())
            .append("specName", getSpecName())
            .append("storeId", getStoreId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
