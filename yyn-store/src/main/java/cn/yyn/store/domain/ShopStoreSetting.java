package cn.yyn.store.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;

/**
 * 商家设置记录对象 shop_store_setting
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public class ShopStoreSetting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设置项标示 */
    private String key;

    /** 设置项描述 */
    @Excel(name = "设置项描述")
    private String describe;

    /** 设置内容（json格式） */
    @Excel(name = "设置内容", readConverterExp = "j=son格式")
    private String values;

    /** 商城ID */
    private Long storeId;

    public void setKey(String key) 
    {
        this.key = key;
    }

    public String getKey() 
    {
        return key;
    }
    public void setDescribe(String describe) 
    {
        this.describe = describe;
    }

    public String getDescribe() 
    {
        return describe;
    }
    public void setValues(String values) 
    {
        this.values = values;
    }

    public String getValues() 
    {
        return values;
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
            .append("key", getKey())
            .append("describe", getDescribe())
            .append("values", getValues())
            .append("storeId", getStoreId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
