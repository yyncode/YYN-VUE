package cn.yyn.store.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.store.domain.ShopUploadGroup;
import cn.yyn.store.mapper.ShopUploadGroupMapper;
import cn.yyn.store.service.IShopUploadGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.yyn.common.utils.SecurityUtils.getUsername;

/**
 * 文件库分组记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-04
 */
@Service
public class ShopUploadGroupServiceImpl implements IShopUploadGroupService 
{
    @Value("${store.id}")
    private String storeId;
    @Autowired
    private ShopUploadGroupMapper shopUploadGroupMapper;

    /**
     * 查询文件库分组记录
     * 
     * @param groupId 文件库分组记录主键
     * @return 文件库分组记录
     */
    @Override
    public ShopUploadGroup selectShopUploadGroupByGroupId(Long groupId)
    {
        return shopUploadGroupMapper.selectShopUploadGroupByGroupId(groupId);
    }

    /**
     * 查询文件库分组记录列表
     * 
     * @param shopUploadGroup 文件库分组记录
     * @return 文件库分组记录
     */
    @Override
    public List<ShopUploadGroup> selectShopUploadGroupList(ShopUploadGroup shopUploadGroup)
    {
        shopUploadGroup.setStoreId(Long.parseLong(storeId));
        return shopUploadGroupMapper.selectShopUploadGroupList(shopUploadGroup);
    }

    /**
     * 新增文件库分组记录
     * 
     * @param shopUploadGroup 文件库分组记录
     * @return 结果
     */
    @Override
    public int insertShopUploadGroup(ShopUploadGroup shopUploadGroup)
    {
        shopUploadGroup.setStoreId(Long.parseLong(storeId));
        shopUploadGroup.setCreateBy(getUsername());
        shopUploadGroup.setCreateTime(DateUtils.getNowDate());
        shopUploadGroup.setUpdateBy(getUsername());
        shopUploadGroup.setUpdateTime(DateUtils.getNowDate());
        return shopUploadGroupMapper.insertShopUploadGroup(shopUploadGroup);
    }

    /**
     * 修改文件库分组记录
     * 
     * @param shopUploadGroup 文件库分组记录
     * @return 结果
     */
    @Override
    public int updateShopUploadGroup(ShopUploadGroup shopUploadGroup)
    {
        shopUploadGroup.setStoreId(Long.parseLong(storeId));
        shopUploadGroup.setUpdateBy(getUsername());
        shopUploadGroup.setUpdateTime(DateUtils.getNowDate());
        return shopUploadGroupMapper.updateShopUploadGroup(shopUploadGroup);
    }

    /**
     * 批量删除文件库分组记录
     * 
     * @param groupIds 需要删除的文件库分组记录主键
     * @return 结果
     */
    @Override
    public int deleteShopUploadGroupByGroupIds(Long[] groupIds)
    {
        return shopUploadGroupMapper.deleteShopUploadGroupByGroupIds(groupIds);
    }

    /**
     * 删除文件库分组记录信息
     * 
     * @param groupId 文件库分组记录主键
     * @return 结果
     */
    @Override
    public int deleteShopUploadGroupByGroupId(Long groupId)
    {
        return shopUploadGroupMapper.deleteShopUploadGroupByGroupId(groupId);
    }

    /**
     * 查询文件库分组树结构
     *
     * @return
     */
    @Override
    public List<ShopUploadGroup> selectShopUploadGroupTree() {
        //最终树
        List<ShopUploadGroup> treeList = new LinkedList<>();
        //查询文件库分组列表
        List<ShopUploadGroup> shopUploadGroups = selectShopUploadGroupList(new ShopUploadGroup());
        //筛选根节点
        List<ShopUploadGroup> rootList = shopUploadGroups.stream().filter(vo -> vo.getParentId() == 0).collect(Collectors.toList());
        //寻找子节点
        rootList.forEach(tree -> treeList.add(findChildren(tree, shopUploadGroups)));
        return treeList;
    }

    /**
     * 寻找子节点
     *
     * @param tree
     * @param list
     * @return
     */
    private ShopUploadGroup findChildren(ShopUploadGroup tree, List<ShopUploadGroup> list) {

        list.stream().filter(node -> tree.getGroupId().equals(node.getParentId())).collect(Collectors.toList()).forEach(node -> {
            if (tree.getChildren() == null) {
                tree.setChildren(new ArrayList<>());
            }
            tree.getChildren().add(findChildren(node, list));
        });
        return tree;
    }
}
