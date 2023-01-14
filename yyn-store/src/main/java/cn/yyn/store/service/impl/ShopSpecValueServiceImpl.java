package cn.yyn.store.service.impl;

import java.util.List;
import cn.yyn.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.yyn.store.mapper.ShopSpecValueMapper;
import cn.yyn.store.domain.ShopSpecValue;
import cn.yyn.store.service.IShopSpecValueService;

/**
 * 商品规格值记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@Service
public class ShopSpecValueServiceImpl implements IShopSpecValueService 
{
    @Autowired
    private ShopSpecValueMapper shopSpecValueMapper;

    /**
     * 查询商品规格值记录
     * 
     * @param specValueId 商品规格值记录主键
     * @return 商品规格值记录
     */
    @Override
    public ShopSpecValue selectShopSpecValueBySpecValueId(Long specValueId)
    {
        return shopSpecValueMapper.selectShopSpecValueBySpecValueId(specValueId);
    }

    /**
     * 查询商品规格值记录列表
     * 
     * @param shopSpecValue 商品规格值记录
     * @return 商品规格值记录
     */
    @Override
    public List<ShopSpecValue> selectShopSpecValueList(ShopSpecValue shopSpecValue)
    {
        return shopSpecValueMapper.selectShopSpecValueList(shopSpecValue);
    }

    /**
     * 新增商品规格值记录
     * 
     * @param shopSpecValue 商品规格值记录
     * @return 结果
     */
    @Override
    public int insertShopSpecValue(ShopSpecValue shopSpecValue)
    {
        shopSpecValue.setCreateTime(DateUtils.getNowDate());
        return shopSpecValueMapper.insertShopSpecValue(shopSpecValue);
    }

    /**
     * 修改商品规格值记录
     * 
     * @param shopSpecValue 商品规格值记录
     * @return 结果
     */
    @Override
    public int updateShopSpecValue(ShopSpecValue shopSpecValue)
    {
        shopSpecValue.setUpdateTime(DateUtils.getNowDate());
        return shopSpecValueMapper.updateShopSpecValue(shopSpecValue);
    }

    /**
     * 批量删除商品规格值记录
     * 
     * @param specValueIds 需要删除的商品规格值记录主键
     * @return 结果
     */
    @Override
    public int deleteShopSpecValueBySpecValueIds(Long[] specValueIds)
    {
        return shopSpecValueMapper.deleteShopSpecValueBySpecValueIds(specValueIds);
    }

    /**
     * 删除商品规格值记录信息
     * 
     * @param specValueId 商品规格值记录主键
     * @return 结果
     */
    @Override
    public int deleteShopSpecValueBySpecValueId(Long specValueId)
    {
        return shopSpecValueMapper.deleteShopSpecValueBySpecValueId(specValueId);
    }
}
