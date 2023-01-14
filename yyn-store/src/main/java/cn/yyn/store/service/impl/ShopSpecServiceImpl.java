package cn.yyn.store.service.impl;

import java.util.List;
import cn.yyn.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.yyn.store.mapper.ShopSpecMapper;
import cn.yyn.store.domain.ShopSpec;
import cn.yyn.store.service.IShopSpecService;

/**
 * 商品规格组记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@Service
public class ShopSpecServiceImpl implements IShopSpecService 
{
    @Autowired
    private ShopSpecMapper shopSpecMapper;

    /**
     * 查询商品规格组记录
     * 
     * @param specId 商品规格组记录主键
     * @return 商品规格组记录
     */
    @Override
    public ShopSpec selectShopSpecBySpecId(Long specId)
    {
        return shopSpecMapper.selectShopSpecBySpecId(specId);
    }

    /**
     * 查询商品规格组记录列表
     * 
     * @param shopSpec 商品规格组记录
     * @return 商品规格组记录
     */
    @Override
    public List<ShopSpec> selectShopSpecList(ShopSpec shopSpec)
    {
        return shopSpecMapper.selectShopSpecList(shopSpec);
    }

    /**
     * 新增商品规格组记录
     * 
     * @param shopSpec 商品规格组记录
     * @return 结果
     */
    @Override
    public int insertShopSpec(ShopSpec shopSpec)
    {
        shopSpec.setCreateTime(DateUtils.getNowDate());
        return shopSpecMapper.insertShopSpec(shopSpec);
    }

    /**
     * 修改商品规格组记录
     * 
     * @param shopSpec 商品规格组记录
     * @return 结果
     */
    @Override
    public int updateShopSpec(ShopSpec shopSpec)
    {
        shopSpec.setUpdateTime(DateUtils.getNowDate());
        return shopSpecMapper.updateShopSpec(shopSpec);
    }

    /**
     * 批量删除商品规格组记录
     * 
     * @param specIds 需要删除的商品规格组记录主键
     * @return 结果
     */
    @Override
    public int deleteShopSpecBySpecIds(Long[] specIds)
    {
        return shopSpecMapper.deleteShopSpecBySpecIds(specIds);
    }

    /**
     * 删除商品规格组记录信息
     * 
     * @param specId 商品规格组记录主键
     * @return 结果
     */
    @Override
    public int deleteShopSpecBySpecId(Long specId)
    {
        return shopSpecMapper.deleteShopSpecBySpecId(specId);
    }
}
