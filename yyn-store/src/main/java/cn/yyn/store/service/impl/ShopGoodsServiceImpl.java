package cn.yyn.store.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.common.utils.StringUtils;
import cn.yyn.store.domain.*;
import cn.yyn.store.dto.GoodsDto;
import cn.yyn.store.mapper.*;
import cn.yyn.store.service.IShopGoodsService;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static cn.yyn.common.utils.SecurityUtils.getUsername;

/**
 * 商品记录Service业务层处理
 *
 * @author yyncode
 * @date 2023-01-07
 */
@Service
public class ShopGoodsServiceImpl implements IShopGoodsService {

    @Value("${store.id}")
    private String storeId;

    @Autowired
    private ShopGoodsMapper shopGoodsMapper;

    @Autowired
    private ShopCategoryMapper shopCategoryMapper;
    @Autowired
    private ShopGoodsCategoryRelMapper shopGoodsCategoryRelMapper;
    @Autowired
    private ShopGoodsImageMapper shopGoodsImageMapper;
    @Autowired
    private ShopGoodsServiceRelMapper shopGoodsServiceRelMapper;
    @Autowired
    private ShopSpecMapper shopSpecMapper;
    @Autowired
    private ShopSpecValueMapper shopSpecValueMapper;
    @Autowired
    private ShopGoodsSpecRelMapper shopGoodsSpecRelMapper;
    @Autowired
    private ShopGoodsSkuMapper shopGoodsSkuMapper;
    @Autowired
    private ShopUploadFileMapper shopUploadFileMapper;

    /**
     * 查询商品记录
     *
     * @param goodsId 商品记录主键
     * @return 商品记录
     */
    @Override
    public GoodsDto selectShopGoodsByGoodsId(Long goodsId) {
        GoodsDto goodsDto = new GoodsDto();
        ShopGoods shopGoods = shopGoodsMapper.selectShopGoodsByGoodsId(goodsId);
        if (StringUtils.isNull(shopGoods)) {
            throw new RuntimeException("该商品不存在");
        }
        goodsDto.setGoodsId(goodsId);
        goodsDto.setGoodsName(shopGoods.getGoodsName());
        Long[] categoryIds = shopGoodsCategoryRelMapper.selectShopGoodsCategoryRelByGoodsId(goodsId);
        goodsDto.setGoodCategoryIds(categoryIds);
        Long[] goodsImageIds = shopGoodsImageMapper.selectShopGoodsImageByGoodsId(goodsId);
        goodsDto.setGoodImageIds(goodsImageIds);
        List<ShopUploadFile> shopUploadFiles = shopUploadFileMapper.selectShopUploadFileInFileIds(goodsImageIds);
        goodsDto.setGoodImages(shopUploadFiles);
        goodsDto.setGoodsNo(shopGoods.getGoodsNo());
        goodsDto.setDeliveryId(shopGoods.getDeliveryId());
        goodsDto.setStatus(shopGoods.getStatus().longValue());
        goodsDto.setSort(shopGoods.getSort());
        goodsDto.setSpecType(shopGoods.getSpecType().longValue());
        List<ShopGoodsSku> shopGoodsSkus = shopGoodsSkuMapper.selectShopGoodsSkuByGoodsId(goodsId);
        if (shopGoods.getSpecType().equals(10)) {
            goodsDto.setGoodsPrice(shopGoodsSkus.get(0).getGoodsPrice());
            goodsDto.setLinePrice(shopGoodsSkus.get(0).getLinePrice());
            goodsDto.setStockNum(shopGoodsSkus.get(0).getStockNum());
            goodsDto.setGoodsWeight(shopGoodsSkus.get(0).getGoodsWeight());
        } else {
            Map<String,Object> specData = new HashMap<>();
            String specValueIds = shopGoodsSkus.get(0).getSpecValueIds();
            specData.put("specList",JSONArray.parse(specValueIds));
            List<JSONObject> skuList = shopGoodsSkus.stream().map(ShopGoodsSku::getGoodsProps).map(JSONObject::parse).collect(Collectors.toList());
            Long[] imageIds = skuList.stream().map(item -> item.getLong("imageId")).toArray(Long[]::new);
            List<ShopUploadFile> images = shopUploadFileMapper.selectShopUploadFileInFileIds(imageIds);
            for (JSONObject jsonObject : skuList) {
                jsonObject.put("image",images.stream().filter(item -> item.getFileId().equals(jsonObject.getLong("imageId"))).collect(Collectors.toList()));
            }
            specData.put("skuList",skuList);
            goodsDto.setSpecData(specData);
        }
        goodsDto.setDeductStockType(shopGoods.getDeductStockType().longValue());
        goodsDto.setContent(shopGoods.getContent());
        goodsDto.setSellingPoint(shopGoods.getSellingPoint());
        Long[] goodsServiceIds = shopGoodsServiceRelMapper.selectShopGoodsServiceRelByGoodsId(goodsId);
        goodsDto.setGoodsServiceIds(goodsServiceIds);
        goodsDto.setSalesInitial(shopGoods.getSalesInitial().intValue());
        goodsDto.setIsPointsGift(shopGoods.getIsPointsGift());
        goodsDto.setIsPointsDiscount(shopGoods.getIsPointsDiscount());
        goodsDto.setIsEnableGrade(shopGoods.getIsEnableGrade());
        goodsDto.setIsAloneGrade(shopGoods.getIsAloneGrade());
        return goodsDto;
    }

    /**
     * 查询商品记录列表
     *
     * @param shopGoods 商品记录
     * @return 商品记录
     */
    @Override
    public List<ShopGoods> selectShopGoodsList(ShopGoods shopGoods) {
        shopGoods.setStoreId(Long.parseLong(storeId));
        List<ShopGoods> shopGoodsList = shopGoodsMapper.selectShopGoodsList(shopGoods);
        List<ShopUploadFile> shopUploadFileList = shopUploadFileMapper.selectShopUploadFileList(new ShopUploadFile());
        for (ShopGoods goods : shopGoodsList) {
            Optional<ShopUploadFile> first = shopUploadFileList.stream().filter(item -> item.getFileId().equals(goods.getFirstImageId())).findFirst();
            first.ifPresent(shopUploadFile -> goods.setFirstImageUrl(shopUploadFile.getCover()));
        }
        return shopGoodsList;
    }

    /**
     * 新增商品记录
     *
     * @param goodsDto 商品记录
     * @return 结果
     */
    @Override
    @Transactional
    public int insertShopGoods(GoodsDto goodsDto) {
        //保存商品信息
        saveGoodsMessage(goodsDto);

        //保存商品分类
        saveGoodsCategory(goodsDto);
        //保存商品图片
        saveGoodsImage(goodsDto);
        //保存商品规格信息
        saveGoodsSku(goodsDto);
        //保存商品服务与承诺
        saveGoodsService(goodsDto);
        //保存商品其他信息
        saveGoodsOtherMessage(goodsDto);
//        shopGoods.setCreateTime(DateUtils.getNowDate());
        return 1;
    }

    private void saveGoodsOtherMessage(GoodsDto goodsDto) {
        List<ShopGoodsSku> shopGoodsSkus = shopGoodsSkuMapper.selectShopGoodsSkuByGoodsId(goodsDto.getGoodsId());
        ShopGoods shopGoods = shopGoodsMapper.selectShopGoodsByGoodsId(goodsDto.getGoodsId());
        shopGoods.setGoodsPriceMin(shopGoodsSkus.stream().map(ShopGoodsSku::getGoodsPrice).min(BigDecimal::compareTo).get());
        shopGoods.setGoodsPriceMax(shopGoodsSkus.stream().map(ShopGoodsSku::getGoodsPrice).max(BigDecimal::compareTo).get());
        shopGoods.setLinePriceMin(shopGoodsSkus.stream().map(ShopGoodsSku::getLinePrice).min(BigDecimal::compareTo).get());
        shopGoods.setLinePriceMax(shopGoodsSkus.stream().map(ShopGoodsSku::getLinePrice).max(BigDecimal::compareTo).get());
        shopGoods.setStockTotal(shopGoodsSkus.stream().mapToLong(ShopGoodsSku::getStockNum).sum());
        shopGoodsMapper.updateShopGoods(shopGoods);
    }

    private void saveGoodsSku(GoodsDto goodsDto) {
        //保存商品与规格值关系
        shopGoodsSpecRelMapper.deleteShopGoodsSpecRelByGoodsId(goodsDto.getGoodsId());
        //保存商品规格
        shopGoodsSkuMapper.deleteShopGoodsSkuByGoodsId(goodsDto.getGoodsId());
        if (goodsDto.getSpecType().equals(20L)) {
            Map<String, Object> specData = goodsDto.getSpecData();
            JSONObject specDataJo = JSONObject.parseObject(JSON.toJSONString(specData));
            JSONArray specList = specDataJo.getJSONArray("specList");
            Map<String,String> skuKeyMap = new HashMap<String,String>();
            for (Object specObj : specList) {
                //保存规格组
                JSONObject specJo = (JSONObject) specObj;
                ShopSpec shopSpec = shopSpecMapper.selectShopSpecBySpecName(specJo.getString("specName"));
                if (StringUtils.isNull(shopSpec)) {
                    shopSpec = new ShopSpec();
                    shopSpec.setSpecName(specJo.getString("specName"));
                    shopSpec.setStoreId(Long.parseLong(storeId));
                    shopSpecMapper.insertShopSpec(shopSpec);
                }

                //保存规格值
                JSONArray valueList = specJo.getJSONArray("valueList");
                for (Object specValueObj : valueList) {
                    JSONObject specValueJo = (JSONObject) specValueObj;
                    ShopSpecValue specValue = shopSpecValueMapper.selectShopSpecValueBySpecValueNameAndSpecId(specValueJo.getString("specValue"),shopSpec.getSpecId());
                    if (StringUtils.isNull(specValue)) {
                        specValue = new ShopSpecValue();
                        specValue.setSpecValue(specValueJo.getString("specValue"));
                        specValue.setSpecId(shopSpec.getSpecId());
                        specValue.setStoreId(Long.parseLong(storeId));
                        shopSpecValueMapper.insertShopSpecValue(specValue);
                    }
                    skuKeyMap.put(specValueJo.getInteger("groupKey") + "_" + specValueJo.getInteger("key"),shopSpec.getSpecId() + "_" + specValue.getSpecValueId());
                    ShopGoodsSpecRel shopGoodsSpecRel = new ShopGoodsSpecRel();
                    shopGoodsSpecRel.setGoodsId(goodsDto.getGoodsId());
                    shopGoodsSpecRel.setSpecId(shopSpec.getSpecId());
                    shopGoodsSpecRel.setSpecValueId(specValue.getSpecValueId());
                    shopGoodsSpecRel.setStoreId(Long.parseLong(storeId));
                    shopGoodsSpecRelMapper.insertShopGoodsSpecRel(shopGoodsSpecRel);
                }
            }

            JSONArray skuList = specDataJo.getJSONArray("skuList");
            for (Object skuObj : skuList) {
                JSONObject skuJo = (JSONObject) skuObj;
                ShopGoodsSku shopGoodsSku = new ShopGoodsSku();
                shopGoodsSku.setGoodsSkuId(skuKeyMap.get(skuJo.getString("skuKey")));
                shopGoodsSku.setGoodsId(goodsDto.getGoodsId());
                shopGoodsSku.setImageId(skuJo.getLong("imageId"));
                shopGoodsSku.setGoodsSkuNo(skuJo.getString("goodsSkuNo"));
                shopGoodsSku.setGoodsPrice(skuJo.getBigDecimal("goodsPrice"));
                shopGoodsSku.setLinePrice(skuJo.getBigDecimal("linePrice"));
                shopGoodsSku.setStockNum(skuJo.getLong("stockNum"));
                shopGoodsSku.setGoodsWeight(skuJo.getBigDecimal("goodsWeight"));
                shopGoodsSku.setGoodsProps(skuJo.toJSONString());
                shopGoodsSku.setSpecValueIds(specList.toJSONString());
                shopGoodsSku.setStoreId(Long.parseLong(storeId));
                shopGoodsSkuMapper.insertShopGoodsSku(shopGoodsSku);
            }
        } else {
            ShopGoodsSku shopGoodsSku = new ShopGoodsSku();
            shopGoodsSku.setGoodsId(goodsDto.getGoodsId());
            shopGoodsSku.setGoodsSkuNo(goodsDto.getGoodsNo());
            shopGoodsSku.setGoodsPrice(goodsDto.getGoodsPrice());
            shopGoodsSku.setLinePrice(goodsDto.getLinePrice());
            shopGoodsSku.setStockNum(goodsDto.getStockNum());
            shopGoodsSku.setGoodsWeight(goodsDto.getGoodsWeight());
            shopGoodsSku.setStoreId(Long.parseLong(storeId));
            shopGoodsSkuMapper.insertShopGoodsSku(shopGoodsSku);
        }

    }

    private void saveGoodsService(GoodsDto goodsDto) {
        shopGoodsServiceRelMapper.deleteShopGoodsServiceRelByGoodsId(goodsDto.getGoodsId());
        Long[] goodsServiceIds = goodsDto.getGoodsServiceIds();
        for (Long goodsServiceId : goodsServiceIds) {
            ShopGoodsServiceRel shopGoodsServiceRel = new ShopGoodsServiceRel();
            shopGoodsServiceRel.setGoodsId(goodsDto.getGoodsId());
            shopGoodsServiceRel.setServiceId(goodsServiceId);
            shopGoodsServiceRel.setStoreId(Long.parseLong(storeId));
            shopGoodsServiceRelMapper.insertShopGoodsServiceRel(shopGoodsServiceRel);
        }
    }

    private void saveGoodsImage(GoodsDto goodsDto) {
        shopGoodsImageMapper.deleteShopGoodsImageByGoodsId(goodsDto.getGoodsId());
        Long[] goodImageIds = goodsDto.getGoodImageIds();
        for (Long goodImageId : goodImageIds) {
            ShopGoodsImage shopGoodsImage = new ShopGoodsImage();
            shopGoodsImage.setGoodsId(goodsDto.getGoodsId());
            shopGoodsImage.setImageId(goodImageId);
            shopGoodsImage.setStoreId(Long.parseLong(storeId));
            shopGoodsImageMapper.insertShopGoodsImage(shopGoodsImage);
        }
    }

    private void saveGoodsCategory(GoodsDto goodsDto) {
        shopGoodsCategoryRelMapper.deleteShopGoodsCategoryRelByGoodsId(goodsDto.getGoodsId());
        Long[] goodCategoryIds = goodsDto.getGoodCategoryIds();
        for (Long goodCategoryId : goodCategoryIds) {
            ShopGoodsCategoryRel shopGoodsCategoryRel = new ShopGoodsCategoryRel();
            shopGoodsCategoryRel.setGoodsId(goodsDto.getGoodsId());
            shopGoodsCategoryRel.setCategoryId(goodCategoryId);
            shopGoodsCategoryRel.setStoreId(Long.parseLong(storeId));
            shopGoodsCategoryRelMapper.insertShopGoodsCategoryRel(shopGoodsCategoryRel);
        }
    }

    private void saveGoodsMessage(GoodsDto goodsDto) {
        ShopGoods shopGoods = new ShopGoods();
        shopGoods.setGoodsName(goodsDto.getGoodsName());
        shopGoods.setGoodsNo(goodsDto.getGoodsNo());
        shopGoods.setSellingPoint(goodsDto.getSellingPoint());
        shopGoods.setSpecType(goodsDto.getSpecType().intValue());
        shopGoods.setFirstImageId(goodsDto.getGoodImageIds()[0]);
        shopGoods.setDeductStockType(goodsDto.getDeductStockType().intValue());
        shopGoods.setContent(goodsDto.getContent());
        shopGoods.setSalesInitial((long) goodsDto.getSalesInitial());
//        shopGoods.setSalesActual();
        shopGoods.setDeliveryId(goodsDto.getDeliveryId());
        shopGoods.setIsPointsGift(goodsDto.getIsPointsGift());
        shopGoods.setIsPointsDiscount(goodsDto.getIsPointsDiscount());
        shopGoods.setIsAlonePointsDiscount(goodsDto.getIsPointsDiscount());
//        shopGoods.setPointsDiscountConfig();
        shopGoods.setIsEnableGrade(goodsDto.getIsEnableGrade());
        shopGoods.setIsAloneGrade(goodsDto.getIsAloneGrade());
//        shopGoods.setAloneGradeEquity();
        shopGoods.setStatus(Math.toIntExact(goodsDto.getStatus()));
        shopGoods.setSort(goodsDto.getSort());
        shopGoods.setStoreId(Long.parseLong(storeId));
        shopGoods.setCreateBy(getUsername());
        shopGoods.setCreateTime(DateUtils.getNowDate());
        shopGoods.setUpdateBy(getUsername());
        shopGoods.setUpdateTime(DateUtils.getNowDate());
        if (StringUtils.isNotNull(goodsDto.getGoodsId())) {
            //更新
            shopGoods.setGoodsId(goodsDto.getGoodsId());
            shopGoodsMapper.updateShopGoods(shopGoods);
        } else {
            //新增
            shopGoodsMapper.insertShopGoods(shopGoods);
        }
        goodsDto.setGoodsId(shopGoods.getGoodsId());
    }

    /**
     * 修改商品记录
     *
     * @param goodsDto 商品记录
     * @return 结果
     */
    @Override
    public int updateShopGoods(GoodsDto goodsDto) {
        return this.insertShopGoods(goodsDto);
    }

    /**
     * 批量删除商品记录
     *
     * @param goodsIds 需要删除的商品记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsByGoodsIds(Long[] goodsIds) {
        return shopGoodsMapper.deleteShopGoodsByGoodsIds(goodsIds);
    }

    /**
     * 删除商品记录信息
     *
     * @param goodsId 商品记录主键
     * @return 结果
     */
    @Override
    public int deleteShopGoodsByGoodsId(Long goodsId) {
        return shopGoodsMapper.deleteShopGoodsByGoodsId(goodsId);
    }
}
