package cn.yyn.store.service.impl;

import java.util.List;
import cn.yyn.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.yyn.store.mapper.ShopGoodsCategoryRelMapper;
import cn.yyn.store.domain.ShopGoodsCategoryRel;
import cn.yyn.store.service.IShopGoodsCategoryRelService;

/**
 * 商品与分类关系记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@Service
public class ShopGoodsCategoryRelServiceImpl implements IShopGoodsCategoryRelService 
{
    @Autowired
    private ShopGoodsCategoryRelMapper shopGoodsCategoryRelMapper;

    /**
     * 查询商品与分类关系记录
     * 
     * @param id 商品与分类关系记录主键
     * @return 商品与分类关系记录
     */
    @Override
    public ShopGoodsCategoryRel selectShopGoodsCategoryRelById(Long id)
    {
        return shopGoodsCategoryRelMapper.selectShopGoodsCategoryRelById(id);
    }

    /**
     * 查询商品与分类关系记录列表
     * 
     * @param shopGoodsCategoryRel 商品与分类关系记录
     * @return 商品与分类关系记录
     */
    @Override
    public List<ShopGoodsCategoryRel> selectShopGoodsCategoryRelList(ShopGoodsCategoryRel shopGoodsCategoryRel)
    {
        return shopGoodsCategoryRelMapper.selectShopGoodsCategoryRelList(shopGoodsCategoryRel);
    }

    /**
     * 新增商品与分类关系记录
     * 
     * @param shopGoodsCategoryRel 商品与分类关系记录
     * @return 结果
     */
    @Override
    public int insertShopGoodsCategoryRel(ShopGoodsCategoryRel shopGoodsCategoryRel)
    {
        shopGoodsCategoryRel.setCreateTime(DateUtils.getNowDate());
        return shopGoodsCategoryRelMapper.insertShopGoodsCategoryRel(shopGoodsCategoryRel);
    }

    /**
     * 修改商品与分类关系记录
     * 
     * @param shopGoodsCategoryRel 商品与分类关系记录
     * @return 结果
     */
    @Override
    public int updateShopGoodsCategoryRel(ShopGoodsCategoryRel shopGoodsCategoryRel)
    {
        shopGoodsCategoryRel.setUpdateTime(DateUtils.getNowDate());
        return shopGoodsCategoryRelMapper.updateShopGoodsCategoryRel(shopGoodsCategoryRel);
    }

    /**
     * 批量删除商品与分类关系记录
     * 
     * @param ids 需要删除的商品与分类关系记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsCategoryRelByIds(Long[] ids)
    {
        return shopGoodsCategoryRelMapper.deleteShopGoodsCategoryRelByIds(ids);
    }

    /**
     * 删除商品与分类关系记录信息
     * 
     * @param id 商品与分类关系记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsCategoryRelById(Long id)
    {
        return shopGoodsCategoryRelMapper.deleteShopGoodsCategoryRelById(id);
    }
}
