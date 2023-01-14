package cn.yyn.store.mapper;

import java.util.List;
import cn.yyn.store.domain.ShopPage;

/**
 * 店铺页面记录Mapper接口
 * 
 * @author yyncode
 * @date 2023-01-12
 */
public interface ShopPageMapper 
{
    /**
     * 查询店铺页面记录
     * 
     * @param pageId 店铺页面记录主键
     * @return 店铺页面记录
     */
    public ShopPage selectShopPageByPageId(Long pageId);

    /**
     * 查询店铺页面记录列表
     * 
     * @param shopPage 店铺页面记录
     * @return 店铺页面记录集合
     */
    public List<ShopPage> selectShopPageList(ShopPage shopPage);

    /**
     * 新增店铺页面记录
     * 
     * @param shopPage 店铺页面记录
     * @return 结果
     */
    public int insertShopPage(ShopPage shopPage);

    /**
     * 修改店铺页面记录
     * 
     * @param shopPage 店铺页面记录
     * @return 结果
     */
    public int updateShopPage(ShopPage shopPage);

    /**
     * 删除店铺页面记录
     * 
     * @param pageId 店铺页面记录主键
     * @return 结果
     */
    public int deleteShopPageByPageId(Long pageId);

    /**
     * 批量删除店铺页面记录
     * 
     * @param pageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopPageByPageIds(Long[] pageIds);
}
