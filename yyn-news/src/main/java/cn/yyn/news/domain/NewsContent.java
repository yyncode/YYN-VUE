package cn.yyn.news.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 新闻内容对象 news_content
 *
 * @author yyncode
 * @date 2022-12-27
 */
public class NewsContent extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 新闻id
     */
    private Long newsId;

    /**
     * 新闻标题
     */
    @Excel(name = "新闻标题")
    private String newsTitle;

    /**
     * 新闻描述
     */
    @Excel(name = "新闻描述")
    private String newsDescription;

    /**
     * 新闻缩略图
     */
    @Excel(name = "新闻缩略图")
    private String newsPic;

    /**
     * 新闻内容
     */
    @Excel(name = "新闻内容")
    private String newsContent;

    /**
     * 导航
     */
//    @Excel(name = "导航")
    private Long newsNavId;

    /**
     * 导航名称（非数据库数据）
     */
    @Excel(name = "导航")
    private String newsNavName;

    /**
     * 显示状态（0隐藏 1显示）
     */
    @Excel(name = "显示状态", readConverterExp = "0=隐藏,1=显示")
    private String status;

    /**
     * 是否置顶（0否 1是）
     */
    @Excel(name = "是否置顶", readConverterExp = "0=否,1=是")
    private String isTop;

    /**
     * 是否审核（0驳回 1审核成功 2审核中）
     */
    @Excel(name = "是否审核", readConverterExp = "0=驳回,1=审核成功,2=审核中")
    private String auditStatus;

    /**
     * 审核原因
     */
    @Excel(name = "审核原因")
    private String auditResult;

    /**
     * 浏览量
     */
    @Excel(name = "浏览量")
    private Long lookNum;

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsPic() {
        return newsPic;
    }

    public void setNewsPic(String newsPic) {
        this.newsPic = newsPic;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Long getNewsNavId() {
        return newsNavId;
    }

    public void setNewsNavId(Long newsNavId) {
        this.newsNavId = newsNavId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public Long getLookNum() {
        return lookNum;
    }

    public void setLookNum(Long lookNum) {
        this.lookNum = lookNum;
    }

    public String getNewsNavName() {
        return newsNavName;
    }

    public void setNewsNavName(String newsNavName) {
        this.newsNavName = newsNavName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("newsId", getNewsId())
                .append("newsTitle", getNewsTitle())
                .append("newsDescription", getNewsDescription())
                .append("newsPic", getNewsPic())
                .append("newsContent", getNewsContent())
                .append("newsNavId", getNewsNavId())
                .append("newsNavName", getNewsNavName())
                .append("status", getStatus())
                .append("isTop", getIsTop())
                .append("auditStatus", getAuditStatus())
                .append("auditResult", getAuditResult())
                .append("lookNum", getLookNum())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
