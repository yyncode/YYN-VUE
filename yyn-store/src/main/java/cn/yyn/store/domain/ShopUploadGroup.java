package cn.yyn.store.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 文件库分组记录对象 shop_upload_group
 * 
 * @author yyncode
 * @date 2023-01-04
 */
public class ShopUploadGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分组ID */
    private Long groupId;

    /** 分组名称 */
    @Excel(name = "分组名称")
    private String name;

    /** 上级分组ID */
    @Excel(name = "上级分组ID")
    private Long parentId;

    /** 排序(数字越小越靠前) */
    @Excel(name = "排序(数字越小越靠前)")
    private Long sort;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDelete;

    /** 商城ID */
    @Excel(name = "商城ID")
    private Long storeId;

    private List<ShopUploadGroup> children;

    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setParentId(Long parentId) 
    {
        this.parentId = parentId;
    }

    public Long getParentId() 
    {
        return parentId;
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

    public List<ShopUploadGroup> getChildren() {
        return children;
    }

    public void setChildren(List<ShopUploadGroup> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("groupId", getGroupId())
            .append("name", getName())
            .append("parentId", getParentId())
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
