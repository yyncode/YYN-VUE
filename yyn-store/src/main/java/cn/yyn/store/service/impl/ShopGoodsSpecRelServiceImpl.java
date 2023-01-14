package cn.yyn.store.service.impl;

import java.util.List;
import cn.yyn.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.yyn.store.mapper.ShopGoodsSpecRelMapper;
import cn.yyn.store.domain.ShopGoodsSpecRel;
import cn.yyn.store.service.IShopGoodsSpecRelService;

/**
 * 商品与规格值关系记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@Service
public class ShopGoodsSpecRelServiceImpl implements IShopGoodsSpecRelService 
{
    @Autowired
    private ShopGoodsSpecRelMapper shopGoodsSpecRelMapper;

    /**
     * 查询商品与规格值关系记录
     * 
     * @param id 商品与规格值关系记录主键
     * @return 商品与规格值关系记录
     */
    @Override
    public ShopGoodsSpecRel selectShopGoodsSpecRelById(Long id)
    {
        return shopGoodsSpecRelMapper.selectShopGoodsSpecRelById(id);
    }

    /**
     * 查询商品与规格值关系记录列表
     * 
     * @param shopGoodsSpecRel 商品与规格值关系记录
     * @return 商品与规格值关系记录
     */
    @Override
    public List<ShopGoodsSpecRel> selectShopGoodsSpecRelList(ShopGoodsSpecRel shopGoodsSpecRel)
    {
        return shopGoodsSpecRelMapper.selectShopGoodsSpecRelList(shopGoodsSpecRel);
    }

    /**
     * 新增商品与规格值关系记录
     * 
     * @param shopGoodsSpecRel 商品与规格值关系记录
     * @return 结果
     */
    @Override
    public int insertShopGoodsSpecRel(ShopGoodsSpecRel shopGoodsSpecRel)
    {
        shopGoodsSpecRel.setCreateTime(DateUtils.getNowDate());
        return shopGoodsSpecRelMapper.insertShopGoodsSpecRel(shopGoodsSpecRel);
    }

    /**
     * 修改商品与规格值关系记录
     * 
     * @param shopGoodsSpecRel 商品与规格值关系记录
     * @return 结果
     */
    @Override
    public int updateShopGoodsSpecRel(ShopGoodsSpecRel shopGoodsSpecRel)
    {
        shopGoodsSpecRel.setUpdateTime(DateUtils.getNowDate());
        return shopGoodsSpecRelMapper.updateShopGoodsSpecRel(shopGoodsSpecRel);
    }

    /**
     * 批量删除商品与规格值关系记录
     * 
     * @param ids 需要删除的商品与规格值关系记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsSpecRelByIds(Long[] ids)
    {
        return shopGoodsSpecRelMapper.deleteShopGoodsSpecRelByIds(ids);
    }

    /**
     * 删除商品与规格值关系记录信息
     * 
     * @param id 商品与规格值关系记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsSpecRelById(Long id)
    {
        return shopGoodsSpecRelMapper.deleteShopGoodsSpecRelById(id);
    }
}
