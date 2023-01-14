package cn.yyn.store.service;

import cn.yyn.store.domain.ShopExpress;

import java.util.List;

/**
 * 物流公司记录Service接口
 * 
 * @author yyncode
 * @date 2023-01-12
 */
public interface IShopExpressService 
{
    /**
     * 查询物流公司记录
     * 
     * @param expressId 物流公司记录主键
     * @return 物流公司记录
     */
    public ShopExpress selectShopExpressByExpressId(Long expressId);

    /**
     * 查询物流公司记录列表
     * 
     * @param shopExpress 物流公司记录
     * @return 物流公司记录集合
     */
    public List<ShopExpress> selectShopExpressList(ShopExpress shopExpress);

    /**
     * 新增物流公司记录
     * 
     * @param shopExpress 物流公司记录
     * @return 结果
     */
    public int insertShopExpress(ShopExpress shopExpress);

    /**
     * 修改物流公司记录
     * 
     * @param shopExpress 物流公司记录
     * @return 结果
     */
    public int updateShopExpress(ShopExpress shopExpress);

    /**
     * 批量删除物流公司记录
     * 
     * @param expressIds 需要删除的物流公司记录主键集合
     * @return 结果
     */
    public int deleteShopExpressByExpressIds(Long[] expressIds);

    /**
     * 删除物流公司记录信息
     * 
     * @param expressId 物流公司记录主键
     * @return 结果
     */
    public int deleteShopExpressByExpressId(Long expressId);
}