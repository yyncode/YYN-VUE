package cn.yyn.store.service.impl;

import java.util.List;
import cn.yyn.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.yyn.store.mapper.ShopGoodsSkuMapper;
import cn.yyn.store.domain.ShopGoodsSku;
import cn.yyn.store.service.IShopGoodsSkuService;

/**
 * 商品规格Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@Service
public class ShopGoodsSkuServiceImpl implements IShopGoodsSkuService 
{
    @Autowired
    private ShopGoodsSkuMapper shopGoodsSkuMapper;

    /**
     * 查询商品规格
     * 
     * @param id 商品规格主键
     * @return 商品规格
     */
    @Override
    public ShopGoodsSku selectShopGoodsSkuById(Long id)
    {
        return shopGoodsSkuMapper.selectShopGoodsSkuById(id);
    }

    /**
     * 查询商品规格列表
     * 
     * @param shopGoodsSku 商品规格
     * @return 商品规格
     */
    @Override
    public List<ShopGoodsSku> selectShopGoodsSkuList(ShopGoodsSku shopGoodsSku)
    {
        return shopGoodsSkuMapper.selectShopGoodsSkuList(shopGoodsSku);
    }

    /**
     * 新增商品规格
     * 
     * @param shopGoodsSku 商品规格
     * @return 结果
     */
    @Override
    public int insertShopGoodsSku(ShopGoodsSku shopGoodsSku)
    {
        shopGoodsSku.setCreateTime(DateUtils.getNowDate());
        return shopGoodsSkuMapper.insertShopGoodsSku(shopGoodsSku);
    }

    /**
     * 修改商品规格
     * 
     * @param shopGoodsSku 商品规格
     * @return 结果
     */
    @Override
    public int updateShopGoodsSku(ShopGoodsSku shopGoodsSku)
    {
        shopGoodsSku.setUpdateTime(DateUtils.getNowDate());
        return shopGoodsSkuMapper.updateShopGoodsSku(shopGoodsSku);
    }

    /**
     * 批量删除商品规格
     * 
     * @param ids 需要删除的商品规格主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsSkuByIds(Long[] ids)
    {
        return shopGoodsSkuMapper.deleteShopGoodsSkuByIds(ids);
    }

    /**
     * 删除商品规格信息
     * 
     * @param id 商品规格主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsSkuById(Long id)
    {
        return shopGoodsSkuMapper.deleteShopGoodsSkuById(id);
    }
}
