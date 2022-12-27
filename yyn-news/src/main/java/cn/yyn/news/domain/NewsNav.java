package cn.yyn.news.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 导航设置对象 news_nav
 *
 * @author yyncode
 * @date 2022-12-27
 */
public class NewsNav extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 导航id
     */
    private Long navId;

    /**
     * 导航名称
     */
    @Excel(name = "导航名称")
    private String navName;

    /**
     * 显示顺序
     */
    @Excel(name = "显示顺序")
    private Integer orderNum;

    public Long getNavId() {
        return navId;
    }

    public void setNavId(Long navId) {
        this.navId = navId;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("navId", getNavId())
                .append("navName", getNavName())
                .append("orderNum", getOrderNum())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
