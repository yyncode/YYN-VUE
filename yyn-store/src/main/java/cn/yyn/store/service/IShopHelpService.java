package cn.yyn.store.service;

import cn.yyn.store.domain.ShopHelp;

import java.util.List;

/**
 * 帮助中心记录Service接口
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public interface IShopHelpService 
{
    /**
     * 查询帮助中心记录
     * 
     * @param helpId 帮助中心记录主键
     * @return 帮助中心记录
     */
    public ShopHelp selectShopHelpByHelpId(Long helpId);

    /**
     * 查询帮助中心记录列表
     * 
     * @param shopHelp 帮助中心记录
     * @return 帮助中心记录集合
     */
    public List<ShopHelp> selectShopHelpList(ShopHelp shopHelp);

    /**
     * 新增帮助中心记录
     * 
     * @param shopHelp 帮助中心记录
     * @return 结果
     */
    public int insertShopHelp(ShopHelp shopHelp);

    /**
     * 修改帮助中心记录
     * 
     * @param shopHelp 帮助中心记录
     * @return 结果
     */
    public int updateShopHelp(ShopHelp shopHelp);

    /**
     * 批量删除帮助中心记录
     * 
     * @param helpIds 需要删除的帮助中心记录主键集合
     * @return 结果
     */
    public int deleteShopHelpByHelpIds(Long[] helpIds);

    /**
     * 删除帮助中心记录信息
     * 
     * @param helpId 帮助中心记录主键
     * @return 结果
     */
    public int deleteShopHelpByHelpId(Long helpId);
}
