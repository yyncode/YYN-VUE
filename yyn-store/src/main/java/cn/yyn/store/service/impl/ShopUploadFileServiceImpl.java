package cn.yyn.store.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.store.domain.ShopUploadFile;
import cn.yyn.store.mapper.ShopUploadFileMapper;
import cn.yyn.store.service.IShopUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static cn.yyn.common.utils.SecurityUtils.getUsername;

/**
 * 文件库记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-04
 */
@Service
public class ShopUploadFileServiceImpl implements IShopUploadFileService 
{
    @Autowired
    private ShopUploadFileMapper shopUploadFileMapper;
    @Value("${store.id}")
    private String storeId;
    /**
     * 查询文件库记录
     * 
     * @param fileId 文件库记录主键
     * @return 文件库记录
     */
    @Override
    public ShopUploadFile selectShopUploadFileByFileId(Long fileId)
    {
        return shopUploadFileMapper.selectShopUploadFileByFileId(fileId);
    }

    /**
     * 查询文件库记录列表
     * 
     * @param shopUploadFile 文件库记录
     * @return 文件库记录
     */
    @Override
    public List<ShopUploadFile> selectShopUploadFileList(ShopUploadFile shopUploadFile)
    {
        return shopUploadFileMapper.selectShopUploadFileList(shopUploadFile);
    }

    /**
     * 新增文件库记录
     * 
     * @param shopUploadFile 文件库记录
     * @return 结果
     */
    @Override
    public int insertShopUploadFile(ShopUploadFile shopUploadFile)
    {
        List<Map<String, Object>> images = shopUploadFile.getImages();
        int i = 0;
        for (Map<String, Object> image : images) {
            ShopUploadFile insertParams = new ShopUploadFile();
            insertParams.setGroupId(shopUploadFile.getGroupId());
            insertParams.setChannel(shopUploadFile.getChannel());
            insertParams.setStorage("10");
//            insertParams.setDomain();
            insertParams.setFileType(shopUploadFile.getFileType());
            insertParams.setFileName(String.valueOf(image.get("newFileName")));
            insertParams.setFilePath(String.valueOf(image.get("fileName")));
            insertParams.setFileSize(new BigDecimal(image.get("fileSize").toString()).longValue());
            insertParams.setFileExt(String.valueOf(image.get("fileExt")));
            insertParams.setCover(String.valueOf(image.get("url")));
//            insertParams.setUploaderId();
            insertParams.setStoreId(Long.parseLong(storeId));
            insertParams.setCreateBy(getUsername());
            insertParams.setCreateTime(DateUtils.getNowDate());
            insertParams.setUpdateBy(getUsername());
            insertParams.setUpdateTime(DateUtils.getNowDate());
            insertParams.setRemark(shopUploadFile.getRemark());
            i = i+shopUploadFileMapper.insertShopUploadFile(insertParams);
        }
        return i;
    }

    /**
     * 修改文件库记录
     * 
     * @param shopUploadFile 文件库记录
     * @return 结果
     */
    @Override
    public int updateShopUploadFile(ShopUploadFile shopUploadFile)
    {
        shopUploadFile.setUpdateTime(DateUtils.getNowDate());
        return shopUploadFileMapper.updateShopUploadFile(shopUploadFile);
    }

    /**
     * 批量删除文件库记录
     * 
     * @param fileIds 需要删除的文件库记录主键
     * @return 结果
     */
    @Override
    public int deleteShopUploadFileByFileIds(Long[] fileIds)
    {
        return shopUploadFileMapper.deleteShopUploadFileByFileIds(fileIds);
    }

    /**
     * 删除文件库记录信息
     * 
     * @param fileId 文件库记录主键
     * @return 结果
     */
    @Override
    public int deleteShopUploadFileByFileId(Long fileId)
    {
        return shopUploadFileMapper.deleteShopUploadFileByFileId(fileId);
    }
}
