package cn.yyn.store.domain;

import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 商品记录对象 shop_goods
 * 
 * @author yyncode
 * @date 2023-01-07
 */
public class ShopGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    private Long goodsId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsNo;

    /** 商品卖点 */
    @Excel(name = "商品卖点")
    private String sellingPoint;

    /** 商品规格 */
    @Excel(name = "商品规格")
    private Integer specType;

    /** 商品价格(最低) */
    @Excel(name = "商品价格(最低)")
    private BigDecimal goodsPriceMin;

    /** 商品价格(最高) */
    @Excel(name = "商品价格(最高)")
    private BigDecimal goodsPriceMax;

    /** 划线价格(最低) */
    @Excel(name = "划线价格(最低)")
    private BigDecimal linePriceMin;

    /** 划线价格(最高) */
    @Excel(name = "划线价格(最高)")
    private BigDecimal linePriceMax;

    /** 库存总量(包含所有sku) */
    @Excel(name = "库存总量(包含所有sku)")
    private Long stockTotal;

    /** 库存计算方式(10下单减库存 20付款减库存) */
    @Excel(name = "库存计算方式(10下单减库存 20付款减库存)")
    private Integer deductStockType;

    /** 商品详情 */
    @Excel(name = "商品详情")
    private String content;
    /** 商品头图 */
    @Excel(name = "商品头图")
    private Long firstImageId;
    /** 商品头图 */
    @Excel(name = "商品头图")
    private String firstImageUrl;

    /** 初始销量 */
    @Excel(name = "初始销量")
    private Long salesInitial;

    /** 实际销量 */
    @Excel(name = "实际销量")
    private Long salesActual;

    /** 配送模板ID */
    @Excel(name = "配送模板ID")
    private Long deliveryId;

    /** 是否开启积分赠送 */
    @Excel(name = "是否开启积分赠送")
    private Integer isPointsGift;

    /** 是否允许使用积分抵扣 */
    @Excel(name = "是否允许使用积分抵扣")
    private Integer isPointsDiscount;

    /** 积分抵扣设置 */
    @Excel(name = "积分抵扣设置")
    private Integer isAlonePointsDiscount;

    /** 单独设置积分抵扣的配置 */
    @Excel(name = "单独设置积分抵扣的配置")
    private String pointsDiscountConfig;

    /** 是否开启会员折扣 */
    @Excel(name = "是否开启会员折扣")
    private Integer isEnableGrade;

    /** 会员折扣设置 */
    @Excel(name = "会员折扣设置")
    private Integer isAloneGrade;

    /** 单独设置折扣的配置 */
    @Excel(name = "单独设置折扣的配置")
    private String aloneGradeEquity;

    /** 商品状态 */
    @Excel(name = "商品状态")
    private Integer status;

    /** 排序(数字越小越靠前) */
    @Excel(name = "排序(数字越小越靠前)")
    private Long sort;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer isDelete;

    /** 商城ID */
    @Excel(name = "商城ID")
    private Long storeId;

    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setGoodsNo(String goodsNo) 
    {
        this.goodsNo = goodsNo;
    }

    public String getGoodsNo() 
    {
        return goodsNo;
    }
    public void setSellingPoint(String sellingPoint) 
    {
        this.sellingPoint = sellingPoint;
    }

    public String getSellingPoint() 
    {
        return sellingPoint;
    }
    public void setSpecType(Integer specType) 
    {
        this.specType = specType;
    }

    public Integer getSpecType() 
    {
        return specType;
    }
    public void setGoodsPriceMin(BigDecimal goodsPriceMin) 
    {
        this.goodsPriceMin = goodsPriceMin;
    }

    public BigDecimal getGoodsPriceMin() 
    {
        return goodsPriceMin;
    }
    public void setGoodsPriceMax(BigDecimal goodsPriceMax) 
    {
        this.goodsPriceMax = goodsPriceMax;
    }

    public BigDecimal getGoodsPriceMax() 
    {
        return goodsPriceMax;
    }
    public void setLinePriceMin(BigDecimal linePriceMin) 
    {
        this.linePriceMin = linePriceMin;
    }

    public BigDecimal getLinePriceMin() 
    {
        return linePriceMin;
    }
    public void setLinePriceMax(BigDecimal linePriceMax) 
    {
        this.linePriceMax = linePriceMax;
    }

    public BigDecimal getLinePriceMax() 
    {
        return linePriceMax;
    }
    public void setStockTotal(Long stockTotal) 
    {
        this.stockTotal = stockTotal;
    }

    public Long getStockTotal() 
    {
        return stockTotal;
    }
    public void setDeductStockType(Integer deductStockType) 
    {
        this.deductStockType = deductStockType;
    }

    public Integer getDeductStockType() 
    {
        return deductStockType;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setSalesInitial(Long salesInitial) 
    {
        this.salesInitial = salesInitial;
    }

    public Long getFirstImageId() {
        return firstImageId;
    }

    public void setFirstImageId(Long firstImageId) {
        this.firstImageId = firstImageId;
    }

    public String getFirstImageUrl() {
        return firstImageUrl;
    }

    public void setFirstImageUrl(String firstImageUrl) {
        this.firstImageUrl = firstImageUrl;
    }

    public Long getSalesInitial()
    {
        return salesInitial;
    }
    public void setSalesActual(Long salesActual) 
    {
        this.salesActual = salesActual;
    }

    public Long getSalesActual() 
    {
        return salesActual;
    }
    public void setDeliveryId(Long deliveryId) 
    {
        this.deliveryId = deliveryId;
    }

    public Long getDeliveryId() 
    {
        return deliveryId;
    }
    public void setIsPointsGift(Integer isPointsGift) 
    {
        this.isPointsGift = isPointsGift;
    }

    public Integer getIsPointsGift() 
    {
        return isPointsGift;
    }
    public void setIsPointsDiscount(Integer isPointsDiscount) 
    {
        this.isPointsDiscount = isPointsDiscount;
    }

    public Integer getIsPointsDiscount() 
    {
        return isPointsDiscount;
    }
    public void setIsAlonePointsDiscount(Integer isAlonePointsDiscount) 
    {
        this.isAlonePointsDiscount = isAlonePointsDiscount;
    }

    public Integer getIsAlonePointsDiscount() 
    {
        return isAlonePointsDiscount;
    }
    public void setPointsDiscountConfig(String pointsDiscountConfig) 
    {
        this.pointsDiscountConfig = pointsDiscountConfig;
    }

    public String getPointsDiscountConfig() 
    {
        return pointsDiscountConfig;
    }
    public void setIsEnableGrade(Integer isEnableGrade) 
    {
        this.isEnableGrade = isEnableGrade;
    }

    public Integer getIsEnableGrade() 
    {
        return isEnableGrade;
    }
    public void setIsAloneGrade(Integer isAloneGrade) 
    {
        this.isAloneGrade = isAloneGrade;
    }

    public Integer getIsAloneGrade() 
    {
        return isAloneGrade;
    }
    public void setAloneGradeEquity(String aloneGradeEquity) 
    {
        this.aloneGradeEquity = aloneGradeEquity;
    }

    public String getAloneGradeEquity() 
    {
        return aloneGradeEquity;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsId", getGoodsId())
            .append("goodsName", getGoodsName())
            .append("goodsNo", getGoodsNo())
            .append("sellingPoint", getSellingPoint())
            .append("specType", getSpecType())
            .append("goodsPriceMin", getGoodsPriceMin())
            .append("goodsPriceMax", getGoodsPriceMax())
            .append("linePriceMin", getLinePriceMin())
            .append("linePriceMax", getLinePriceMax())
            .append("stockTotal", getStockTotal())
            .append("deductStockType", getDeductStockType())
            .append("content", getContent())
            .append("salesInitial", getSalesInitial())
            .append("salesActual", getSalesActual())
            .append("deliveryId", getDeliveryId())
            .append("isPointsGift", getIsPointsGift())
            .append("isPointsDiscount", getIsPointsDiscount())
            .append("isAlonePointsDiscount", getIsAlonePointsDiscount())
            .append("pointsDiscountConfig", getPointsDiscountConfig())
            .append("isEnableGrade", getIsEnableGrade())
            .append("isAloneGrade", getIsAloneGrade())
            .append("aloneGradeEquity", getAloneGradeEquity())
            .append("status", getStatus())
            .append("sort", getSort())
            .append("isDelete", getIsDelete())
            .append("storeId", getStoreId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
