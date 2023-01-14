package cn.yyn.store.mapper;

import java.util.List;
import cn.yyn.store.domain.ShopSpec;

/**
 * 商品规格组记录Mapper接口
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public interface ShopSpecMapper 
{
    /**
     * 查询商品规格组记录
     * 
     * @param specId 商品规格组记录主键
     * @return 商品规格组记录
     */
    public ShopSpec selectShopSpecBySpecId(Long specId);

    /**
     * 查询商品规格组记录列表
     * 
     * @param shopSpec 商品规格组记录
     * @return 商品规格组记录集合
     */
    public List<ShopSpec> selectShopSpecList(ShopSpec shopSpec);

    /**
     * 新增商品规格组记录
     * 
     * @param shopSpec 商品规格组记录
     * @return 结果
     */
    public int insertShopSpec(ShopSpec shopSpec);

    /**
     * 修改商品规格组记录
     * 
     * @param shopSpec 商品规格组记录
     * @return 结果
     */
    public int updateShopSpec(ShopSpec shopSpec);

    /**
     * 删除商品规格组记录
     * 
     * @param specId 商品规格组记录主键
     * @return 结果
     */
    public int deleteShopSpecBySpecId(Long specId);

    /**
     * 批量删除商品规格组记录
     * 
     * @param specIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopSpecBySpecIds(Long[] specIds);

    ShopSpec selectShopSpecBySpecName(String specName);
}
