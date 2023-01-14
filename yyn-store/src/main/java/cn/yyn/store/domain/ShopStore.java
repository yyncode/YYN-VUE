package cn.yyn.store.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商家(商户)记录对象 shop_store
 *
 * @author yyncode
 * @date 2023-01-02
 */
public class ShopStore extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商城ID */
    private Long storeId;

    /** 商城名称 */
    @Excel(name = "商城名称")
    private String storeName;

    /** 商城简介 */
    @Excel(name = "商城简介")
    private String describe;

    /** 商城logo文件ID */
    @Excel(name = "商城logo文件ID")
    private Long logoImageId;
    /** 商城logo */
    @Excel(name = "商城logo")
    private String logoImageUrl;


    /** 排序(数字越小越靠前) */
    @Excel(name = "排序(数字越小越靠前)")
    private Long sort;

    /** 是否回收 */
    @Excel(name = "是否回收")
    private Integer isRecycle;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDelete;

    public void setStoreId(Long storeId)
    {
        this.storeId = storeId;
    }

    public Long getStoreId()
    {
        return storeId;
    }
    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getStoreName()
    {
        return storeName;
    }
    public void setDescribe(String describe)
    {
        this.describe = describe;
    }

    public String getDescribe()
    {
        return describe;
    }
    public void setLogoImageId(Long logoImageId)
    {
        this.logoImageId = logoImageId;
    }

    public Long getLogoImageId()
    {
        return logoImageId;
    }

    public String getLogoImageUrl() {
        return logoImageUrl;
    }

    public void setLogoImageUrl(String logoImageUrl) {
        this.logoImageUrl = logoImageUrl;
    }

    public void setSort(Long sort)
    {
        this.sort = sort;
    }

    public Long getSort()
    {
        return sort;
    }
    public void setIsRecycle(Integer isRecycle)
    {
        this.isRecycle = isRecycle;
    }

    public Integer getIsRecycle()
    {
        return isRecycle;
    }
    public void setIsDelete(Integer isDelete)
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("storeId", getStoreId())
                .append("storeName", getStoreName())
                .append("describe", getDescribe())
                .append("logoImageId", getLogoImageId())
                .append("sort", getSort())
                .append("isRecycle", getIsRecycle())
                .append("isDelete", getIsDelete())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
