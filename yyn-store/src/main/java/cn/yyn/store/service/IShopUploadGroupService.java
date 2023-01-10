package cn.yyn.store.service;

import cn.yyn.store.domain.ShopUploadGroup;

import java.util.List;

/**
 * 文件库分组记录Service接口
 * 
 * @author yyncode
 * @date 2023-01-04
 */
public interface IShopUploadGroupService 
{
    /**
     * 查询文件库分组记录
     * 
     * @param groupId 文件库分组记录主键
     * @return 文件库分组记录
     */
    public ShopUploadGroup selectShopUploadGroupByGroupId(Long groupId);

    /**
     * 查询文件库分组记录列表
     * 
     * @param shopUploadGroup 文件库分组记录
     * @return 文件库分组记录集合
     */
    public List<ShopUploadGroup> selectShopUploadGroupList(ShopUploadGroup shopUploadGroup);

    /**
     * 新增文件库分组记录
     * 
     * @param shopUploadGroup 文件库分组记录
     * @return 结果
     */
    public int insertShopUploadGroup(ShopUploadGroup shopUploadGroup);

    /**
     * 修改文件库分组记录
     * 
     * @param shopUploadGroup 文件库分组记录
     * @return 结果
     */
    public int updateShopUploadGroup(ShopUploadGroup shopUploadGroup);

    /**
     * 批量删除文件库分组记录
     * 
     * @param groupIds 需要删除的文件库分组记录主键集合
     * @return 结果
     */
    public int deleteShopUploadGroupByGroupIds(Long[] groupIds);

    /**
     * 删除文件库分组记录信息
     * 
     * @param groupId 文件库分组记录主键
     * @return 结果
     */
    public int deleteShopUploadGroupByGroupId(Long groupId);

    /**
     * 查询文件库分组树结构
     * @return
     */
    List<ShopUploadGroup> selectShopUploadGroupTree();
}
