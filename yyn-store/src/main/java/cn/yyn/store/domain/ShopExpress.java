package cn.yyn.store.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 物流公司记录对象 shop_express
 * 
 * @author yyncode
 * @date 2023-01-12
 */
public class ShopExpress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物流公司ID */
    private Long expressId;

    /** 物流公司名称 */
    @Excel(name = "物流公司名称")
    private String expressName;

    /** 物流公司编码 (快递100) */
    @Excel(name = "物流公司编码 (快递100)")
    private String kuaidi100Code;

    /** 排序(数字越小越靠前) */
    @Excel(name = "排序(数字越小越靠前)")
    private Long sort;

    /** 商城ID */
    @Excel(name = "商城ID")
    private Long storeId;

    public void setExpressId(Long expressId) 
    {
        this.expressId = expressId;
    }

    public Long getExpressId() 
    {
        return expressId;
    }
    public void setExpressName(String expressName) 
    {
        this.expressName = expressName;
    }

    public String getExpressName() 
    {
        return expressName;
    }
    public void setKuaidi100Code(String kuaidi100Code) 
    {
        this.kuaidi100Code = kuaidi100Code;
    }

    public String getKuaidi100Code() 
    {
        return kuaidi100Code;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("expressId", getExpressId())
            .append("expressName", getExpressName())
            .append("kuaidi100Code", getKuaidi100Code())
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
