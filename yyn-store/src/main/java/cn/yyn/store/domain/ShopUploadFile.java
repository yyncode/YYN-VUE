package cn.yyn.store.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.Map;

/**
 * 文件库记录对象 shop_upload_file
 * 
 * @author yyncode
 * @date 2023-01-04
 */
public class ShopUploadFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件ID */
    private Long fileId;

    /** 文件分组ID */
    @Excel(name = "文件分组ID")
    private Long groupId;

    /** 上传来源(10商户后台 20用户端) */
    @Excel(name = "上传来源(10商户后台 20用户端)")
    private Integer channel;

    /** 存储方式 */
    @Excel(name = "存储方式")
    private String storage;

    /** 存储域名 */
    @Excel(name = "存储域名")
    private String domain;

    /** 文件类型(10图片 20附件 30视频) */
    @Excel(name = "文件类型(10图片 20附件 30视频)")
    private Integer fileType;

    /** 文件名称(仅显示) */
    @Excel(name = "文件名称(仅显示)")
    private String fileName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 文件大小(字节) */
    @Excel(name = "文件大小(字节)")
    private Long fileSize;

    /** 文件扩展名 */
    @Excel(name = "文件扩展名")
    private String fileExt;

    /** 文件封面 */
    @Excel(name = "文件封面")
    private String cover;

    /** 上传者用户ID */
    @Excel(name = "上传者用户ID")
    private Long uploaderId;

    /** 是否在回收站 */
    @Excel(name = "是否在回收站")
    private Integer isRecycle;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDelete;

    /** 商城ID */
    @Excel(name = "商城ID")
    private Long storeId;

    private List<Map<String,Object>> images;

    public void setFileId(Long fileId)
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setChannel(Integer channel) 
    {
        this.channel = channel;
    }

    public Integer getChannel() 
    {
        return channel;
    }
    public void setStorage(String storage) 
    {
        this.storage = storage;
    }

    public String getStorage() 
    {
        return storage;
    }
    public void setDomain(String domain) 
    {
        this.domain = domain;
    }

    public String getDomain() 
    {
        return domain;
    }
    public void setFileType(Integer fileType) 
    {
        this.fileType = fileType;
    }

    public Integer getFileType() 
    {
        return fileType;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    public String getFilePath() 
    {
        return filePath;
    }
    public void setFileSize(Long fileSize) 
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize() 
    {
        return fileSize;
    }
    public void setFileExt(String fileExt) 
    {
        this.fileExt = fileExt;
    }

    public String getFileExt() 
    {
        return fileExt;
    }
    public void setCover(String cover) 
    {
        this.cover = cover;
    }

    public String getCover() 
    {
        return cover;
    }
    public void setUploaderId(Long uploaderId) 
    {
        this.uploaderId = uploaderId;
    }

    public Long getUploaderId() 
    {
        return uploaderId;
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
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }

    public List<Map<String, Object>> getImages() {
        return images;
    }

    public void setImages(List<Map<String, Object>> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("groupId", getGroupId())
            .append("channel", getChannel())
            .append("storage", getStorage())
            .append("domain", getDomain())
            .append("fileType", getFileType())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("fileSize", getFileSize())
            .append("fileExt", getFileExt())
            .append("cover", getCover())
            .append("uploaderId", getUploaderId())
            .append("isRecycle", getIsRecycle())
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
