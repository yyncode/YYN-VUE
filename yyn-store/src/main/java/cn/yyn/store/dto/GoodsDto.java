package cn.yyn.store.dto;

import cn.yyn.store.domain.ShopUploadFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class GoodsDto {
    private Long goodsId;
    private String goodsName;
    private Long[] goodCategoryIds;
    private Long[] goodImageIds;

    public List<ShopUploadFile> getGoodImages() {
        return goodImages;
    }

    public void setGoodImages(List<ShopUploadFile> goodImages) {
        this.goodImages = goodImages;
    }

    private List<ShopUploadFile> goodImages;
    private String goodsNo;
    private Long deliveryId;
    private Long status;
    private Long sort;
    private Long specType;
    private BigDecimal goodsPrice;
    private BigDecimal linePrice;
    private Long stockNum;
    private BigDecimal goodsWeight;
    private Long deductStockType;
    private String content;
    private String sellingPoint;
    private Long[] goodsServiceIds;
    private int salesInitial;
    private int isPointsGift;
    private int isPointsDiscount;
    private int isEnableGrade;
    private int isAloneGrade;
    private Map<String,Object> specData;


    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Long[] getGoodCategoryIds() {
        return goodCategoryIds;
    }

    public void setGoodCategoryIds(Long[] goodCategoryIds) {
        this.goodCategoryIds = goodCategoryIds;
    }

    public Long[] getGoodImageIds() {
        return goodImageIds;
    }

    public void setGoodImageIds(Long[] goodImageIds) {
        this.goodImageIds = goodImageIds;
    }


    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSpecType() {
        return specType;
    }

    public void setSpecType(Long specType) {
        this.specType = specType;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(BigDecimal linePrice) {
        this.linePrice = linePrice;
    }

    public Long getStockNum() {
        return stockNum;
    }

    public void setStockNum(Long stockNum) {
        this.stockNum = stockNum;
    }

    public BigDecimal getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(BigDecimal goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public Long getDeductStockType() {
        return deductStockType;
    }

    public void setDeductStockType(Long deductStockType) {
        this.deductStockType = deductStockType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSellingPoint() {
        return sellingPoint;
    }

    public void setSellingPoint(String sellingPoint) {
        this.sellingPoint = sellingPoint;
    }

    public Long[] getGoodsServiceIds() {
        return goodsServiceIds;
    }

    public void setGoodsServiceIds(Long[] goodsServiceIds) {
        this.goodsServiceIds = goodsServiceIds;
    }

    public int getSalesInitial() {
        return salesInitial;
    }

    public void setSalesInitial(int salesInitial) {
        this.salesInitial = salesInitial;
    }

    public int getIsPointsGift() {
        return isPointsGift;
    }

    public void setIsPointsGift(int isPointsGift) {
        this.isPointsGift = isPointsGift;
    }

    public int getIsPointsDiscount() {
        return isPointsDiscount;
    }

    public void setIsPointsDiscount(int isPointsDiscount) {
        this.isPointsDiscount = isPointsDiscount;
    }

    public int getIsEnableGrade() {
        return isEnableGrade;
    }

    public void setIsEnableGrade(int isEnableGrade) {
        this.isEnableGrade = isEnableGrade;
    }

    public int getIsAloneGrade() {
        return isAloneGrade;
    }

    public void setIsAloneGrade(int isAloneGrade) {
        this.isAloneGrade = isAloneGrade;
    }

    public Map<String, Object> getSpecData() {
        return specData;
    }

    public void setSpecData(Map<String, Object> specData) {
        this.specData = specData;
    }
}
