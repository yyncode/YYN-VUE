package cn.yyn.store.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.yyn.common.annotation.Excel;
import cn.yyn.common.core.domain.BaseEntity;

/**
 * 商品规格对象 shop_goods_sku
 * 
 * @author yyncode
 * @date 2023-01-11
 */
public class ShopGoodsSku extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long id;

    /** 商品sku唯一标识 (由规格id组成) */
    @Excel(name = "商品sku唯一标识 (由规格id组成)")
    private String goodsSkuId;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long goodsId;

    /** 规格图片ID */
    @Excel(name = "规格图片ID")
    private Long imageId;

    /** 商品sku编码 */
    @Excel(name = "商品sku编码")
    private String goodsSkuNo;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal goodsPrice;

    /** 商品划线价 */
    @Excel(name = "商品划线价")
    private BigDecimal linePrice;

    /** 当前库存数量 */
    @Excel(name = "当前库存数量")
    private Long stockNum;

    /** 商品重量(Kg) */
    @Excel(name = "商品重量(Kg)")
    private BigDecimal goodsWeight;

    /** SKU的规格属性(json格式) */
    @Excel(name = "SKU的规格属性(json格式)")
    private String goodsProps;

    /** 规格值ID集(json格式) */
    @Excel(name = "规格值ID集(json格式)")
    private String specValueIds;

    /** 商城ID */
    @Excel(name = "商城ID")
    private Long storeId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGoodsSkuId(String goodsSkuId) 
    {
        this.goodsSkuId = goodsSkuId;
    }

    public String getGoodsSkuId() 
    {
        return goodsSkuId;
    }
    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setImageId(Long imageId) 
    {
        this.imageId = imageId;
    }

    public Long getImageId() 
    {
        return imageId;
    }
    public void setGoodsSkuNo(String goodsSkuNo) 
    {
        this.goodsSkuNo = goodsSkuNo;
    }

    public String getGoodsSkuNo() 
    {
        return goodsSkuNo;
    }
    public void setGoodsPrice(BigDecimal goodsPrice) 
    {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsPrice() 
    {
        return goodsPrice;
    }
    public void setLinePrice(BigDecimal linePrice) 
    {
        this.linePrice = linePrice;
    }

    public BigDecimal getLinePrice() 
    {
        return linePrice;
    }
    public void setStockNum(Long stockNum) 
    {
        this.stockNum = stockNum;
    }

    public Long getStockNum() 
    {
        return stockNum;
    }
    public void setGoodsWeight(BigDecimal goodsWeight) 
    {
        this.goodsWeight = goodsWeight;
    }

    public BigDecimal getGoodsWeight() 
    {
        return goodsWeight;
    }
    public void setGoodsProps(String goodsProps) 
    {
        this.goodsProps = goodsProps;
    }

    public String getGoodsProps() 
    {
        return goodsProps;
    }
    public void setSpecValueIds(String specValueIds) 
    {
        this.specValueIds = specValueIds;
    }

    public String getSpecValueIds() 
    {
        return specValueIds;
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
            .append("id", getId())
            .append("goodsSkuId", getGoodsSkuId())
            .append("goodsId", getGoodsId())
            .append("imageId", getImageId())
            .append("goodsSkuNo", getGoodsSkuNo())
            .append("goodsPrice", getGoodsPrice())
            .append("linePrice", getLinePrice())
            .append("stockNum", getStockNum())
            .append("goodsWeight", getGoodsWeight())
            .append("goodsProps", getGoodsProps())
            .append("specValueIds", getSpecValueIds())
            .append("storeId", getStoreId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
