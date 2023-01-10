package cn.yyn.store.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 省市区数据对象 shop_region
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public class ShopRegion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 区划信息ID */
    private Long id;

    /** 区划名称 */
    @Excel(name = "区划名称")
    private String name;

    /** 父级ID */
    @Excel(name = "父级ID")
    private Long pid;

    /** 区划编码 */
    @Excel(name = "区划编码")
    private String code;

    /** 层级(1省级 2市级 3区/县级) */
    @Excel(name = "层级(1省级 2市级 3区/县级)")
    private Integer level;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setLevel(Integer level) 
    {
        this.level = level;
    }

    public Integer getLevel() 
    {
        return level;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("pid", getPid())
            .append("code", getCode())
            .append("level", getLevel())
            .toString();
    }
}
