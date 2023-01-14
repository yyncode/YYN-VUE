package cn.yyn.store.service.impl;

import java.util.List;
import cn.yyn.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import cn.yyn.store.mapper.ShopPageMapper;
import cn.yyn.store.domain.ShopPage;
import cn.yyn.store.service.IShopPageService;

/**
 * 店铺页面记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-12
 */
@Service
public class ShopPageServiceImpl implements IShopPageService 
{
    @Autowired
    private ShopPageMapper shopPageMapper;
    @Value("${store.id}")
    private String storeId;

    /**
     * 查询店铺页面记录
     * 
     * @param pageId 店铺页面记录主键
     * @return 店铺页面记录
     */
    @Override
    public ShopPage selectShopPageByPageId(Long pageId)
    {
        return shopPageMapper.selectShopPageByPageId(pageId);
    }

    /**
     * 查询店铺页面记录列表
     * 
     * @param shopPage 店铺页面记录
     * @return 店铺页面记录
     */
    @Override
    public List<ShopPage> selectShopPageList(ShopPage shopPage)
    {
        shopPage.setStoreId(Long.parseLong(storeId));
        return shopPageMapper.selectShopPageList(shopPage);
    }

    /**
     * 新增店铺页面记录
     * 
     * @param shopPage 店铺页面记录
     * @return 结果
     */
    @Override
    public int insertShopPage(ShopPage shopPage)
    {
        shopPage.setCreateTime(DateUtils.getNowDate());
        return shopPageMapper.insertShopPage(shopPage);
    }

    /**
     * 修改店铺页面记录
     * 
     * @param shopPage 店铺页面记录
     * @return 结果
     */
    @Override
    public int updateShopPage(ShopPage shopPage)
    {
        shopPage.setUpdateTime(DateUtils.getNowDate());
        return shopPageMapper.updateShopPage(shopPage);
    }

    /**
     * 批量删除店铺页面记录
     * 
     * @param pageIds 需要删除的店铺页面记录主键
     * @return 结果
     */
    @Override
    public int deleteShopPageByPageIds(Long[] pageIds)
    {
        return shopPageMapper.deleteShopPageByPageIds(pageIds);
    }

    /**
     * 删除店铺页面记录信息
     * 
     * @param pageId 店铺页面记录主键
     * @return 结果
     */
    @Override
    public int deleteShopPageByPageId(Long pageId)
    {
        return shopPageMapper.deleteShopPageByPageId(pageId);
    }
}
