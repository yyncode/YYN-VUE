package cn.yyn.store.service;

import cn.yyn.store.domain.ShopUploadFile;

import java.util.List;

/**
 * 文件库记录Service接口
 * 
 * @author yyncode
 * @date 2023-01-04
 */
public interface IShopUploadFileService 
{
    /**
     * 查询文件库记录
     * 
     * @param fileId 文件库记录主键
     * @return 文件库记录
     */
    public ShopUploadFile selectShopUploadFileByFileId(Long fileId);

    /**
     * 查询文件库记录列表
     * 
     * @param shopUploadFile 文件库记录
     * @return 文件库记录集合
     */
    public List<ShopUploadFile> selectShopUploadFileList(ShopUploadFile shopUploadFile);

    /**
     * 新增文件库记录
     * 
     * @param shopUploadFile 文件库记录
     * @return 结果
     */
    public int insertShopUploadFile(ShopUploadFile shopUploadFile);

    /**
     * 修改文件库记录
     * 
     * @param shopUploadFile 文件库记录
     * @return 结果
     */
    public int updateShopUploadFile(ShopUploadFile shopUploadFile);

    /**
     * 批量删除文件库记录
     * 
     * @param fileIds 需要删除的文件库记录主键集合
     * @return 结果
     */
    public int deleteShopUploadFileByFileIds(Long[] fileIds);

    /**
     * 删除文件库记录信息
     * 
     * @param fileId 文件库记录主键
     * @return 结果
     */
    public int deleteShopUploadFileByFileId(Long fileId);
}
