package cn.yyn.store.service.impl;

import java.util.List;
import cn.yyn.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.yyn.store.mapper.ShopGoodsServiceRelMapper;
import cn.yyn.store.domain.ShopGoodsServiceRel;
import cn.yyn.store.service.IShopGoodsServiceRelService;

/**
 * 商品与服务承诺关系记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@Service
public class ShopGoodsServiceRelServiceImpl implements IShopGoodsServiceRelService 
{
    @Autowired
    private ShopGoodsServiceRelMapper shopGoodsServiceRelMapper;

    /**
     * 查询商品与服务承诺关系记录
     * 
     * @param id 商品与服务承诺关系记录主键
     * @return 商品与服务承诺关系记录
     */
    @Override
    public ShopGoodsServiceRel selectShopGoodsServiceRelById(Long id)
    {
        return shopGoodsServiceRelMapper.selectShopGoodsServiceRelById(id);
    }

    /**
     * 查询商品与服务承诺关系记录列表
     * 
     * @param shopGoodsServiceRel 商品与服务承诺关系记录
     * @return 商品与服务承诺关系记录
     */
    @Override
    public List<ShopGoodsServiceRel> selectShopGoodsServiceRelList(ShopGoodsServiceRel shopGoodsServiceRel)
    {
        return shopGoodsServiceRelMapper.selectShopGoodsServiceRelList(shopGoodsServiceRel);
    }

    /**
     * 新增商品与服务承诺关系记录
     * 
     * @param shopGoodsServiceRel 商品与服务承诺关系记录
     * @return 结果
     */
    @Override
    public int insertShopGoodsServiceRel(ShopGoodsServiceRel shopGoodsServiceRel)
    {
        shopGoodsServiceRel.setCreateTime(DateUtils.getNowDate());
        return shopGoodsServiceRelMapper.insertShopGoodsServiceRel(shopGoodsServiceRel);
    }

    /**
     * 修改商品与服务承诺关系记录
     * 
     * @param shopGoodsServiceRel 商品与服务承诺关系记录
     * @return 结果
     */
    @Override
    public int updateShopGoodsServiceRel(ShopGoodsServiceRel shopGoodsServiceRel)
    {
        shopGoodsServiceRel.setUpdateTime(DateUtils.getNowDate());
        return shopGoodsServiceRelMapper.updateShopGoodsServiceRel(shopGoodsServiceRel);
    }

    /**
     * 批量删除商品与服务承诺关系记录
     * 
     * @param ids 需要删除的商品与服务承诺关系记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsServiceRelByIds(Long[] ids)
    {
        return shopGoodsServiceRelMapper.deleteShopGoodsServiceRelByIds(ids);
    }

    /**
     * 删除商品与服务承诺关系记录信息
     * 
     * @param id 商品与服务承诺关系记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsServiceRelById(Long id)
    {
        return shopGoodsServiceRelMapper.deleteShopGoodsServiceRelById(id);
    }
}
