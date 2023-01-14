package cn.yyn.store.mapper;

import java.util.List;
import cn.yyn.store.domain.ShopSpecValue;
import org.apache.ibatis.annotations.Param;

/**
 * 商品规格值记录Mapper接口
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public interface ShopSpecValueMapper 
{
    /**
     * 查询商品规格值记录
     * 
     * @param specValueId 商品规格值记录主键
     * @return 商品规格值记录
     */
    public ShopSpecValue selectShopSpecValueBySpecValueId(Long specValueId);

    /**
     * 查询商品规格值记录列表
     * 
     * @param shopSpecValue 商品规格值记录
     * @return 商品规格值记录集合
     */
    public List<ShopSpecValue> selectShopSpecValueList(ShopSpecValue shopSpecValue);

    /**
     * 新增商品规格值记录
     * 
     * @param shopSpecValue 商品规格值记录
     * @return 结果
     */
    public int insertShopSpecValue(ShopSpecValue shopSpecValue);

    /**
     * 修改商品规格值记录
     * 
     * @param shopSpecValue 商品规格值记录
     * @return 结果
     */
    public int updateShopSpecValue(ShopSpecValue shopSpecValue);

    /**
     * 删除商品规格值记录
     * 
     * @param specValueId 商品规格值记录主键
     * @return 结果
     */
    public int deleteShopSpecValueBySpecValueId(Long specValueId);

    /**
     * 批量删除商品规格值记录
     * 
     * @param specValueIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteShopSpecValueBySpecValueIds(Long[] specValueIds);

    ShopSpecValue selectShopSpecValueBySpecValueNameAndSpecId(@Param("specValue") String specValue, @Param("specId") Long specId);
}
