package cn.yyn.store.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.store.domain.ShopGoods;
import cn.yyn.store.service.IShopGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品记录Controller
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@RestController
@RequestMapping("/goods/goods")
public class ShopGoodsController extends BaseController
{
    @Autowired
    private IShopGoodsService shopGoodsService;

    /**
     * 查询商品记录列表
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopGoods shopGoods)
    {
        startPage();
        List<ShopGoods> list = shopGoodsService.selectShopGoodsList(shopGoods);
        return getDataTable(list);
    }

    /**
     * 导出商品记录列表
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:export')")
    @Log(title = "商品记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopGoods shopGoods)
    {
        List<ShopGoods> list = shopGoodsService.selectShopGoodsList(shopGoods);
        ExcelUtil<ShopGoods> util = new ExcelUtil<ShopGoods>(ShopGoods.class);
        util.exportExcel(response, list, "商品记录数据");
    }

    /**
     * 获取商品记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:query')")
    @GetMapping(value = "/{goodsId}")
    public AjaxResult getInfo(@PathVariable("goodsId") Long goodsId)
    {
        return success(shopGoodsService.selectShopGoodsByGoodsId(goodsId));
    }

    /**
     * 新增商品记录
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:add')")
    @Log(title = "商品记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopGoods shopGoods)
    {
        return toAjax(shopGoodsService.insertShopGoods(shopGoods));
    }

    /**
     * 修改商品记录
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:edit')")
    @Log(title = "商品记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopGoods shopGoods)
    {
        return toAjax(shopGoodsService.updateShopGoods(shopGoods));
    }

    /**
     * 删除商品记录
     */
    @PreAuthorize("@ss.hasPermi('goods:goods:remove')")
    @Log(title = "商品记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsIds}")
    public AjaxResult remove(@PathVariable Long[] goodsIds)
    {
        return toAjax(shopGoodsService.deleteShopGoodsByGoodsIds(goodsIds));
    }
}
