package cn.yyn.store.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.store.domain.ShopGoodsSku;
import cn.yyn.store.service.IShopGoodsSkuService;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.common.core.page.TableDataInfo;

/**
 * 商品规格Controller
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@RestController
@RequestMapping("/store/sku")
public class ShopGoodsSkuController extends BaseController
{
    @Autowired
    private IShopGoodsSkuService shopGoodsSkuService;

    /**
     * 查询商品规格列表
     */
    @PreAuthorize("@ss.hasPermi('store:sku:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopGoodsSku shopGoodsSku)
    {
        startPage();
        List<ShopGoodsSku> list = shopGoodsSkuService.selectShopGoodsSkuList(shopGoodsSku);
        return getDataTable(list);
    }

    /**
     * 导出商品规格列表
     */
    @PreAuthorize("@ss.hasPermi('store:sku:export')")
    @Log(title = "商品规格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopGoodsSku shopGoodsSku)
    {
        List<ShopGoodsSku> list = shopGoodsSkuService.selectShopGoodsSkuList(shopGoodsSku);
        ExcelUtil<ShopGoodsSku> util = new ExcelUtil<ShopGoodsSku>(ShopGoodsSku.class);
        util.exportExcel(response, list, "商品规格数据");
    }

    /**
     * 获取商品规格详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:sku:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shopGoodsSkuService.selectShopGoodsSkuById(id));
    }

    /**
     * 新增商品规格
     */
    @PreAuthorize("@ss.hasPermi('store:sku:add')")
    @Log(title = "商品规格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopGoodsSku shopGoodsSku)
    {
        return toAjax(shopGoodsSkuService.insertShopGoodsSku(shopGoodsSku));
    }

    /**
     * 修改商品规格
     */
    @PreAuthorize("@ss.hasPermi('store:sku:edit')")
    @Log(title = "商品规格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopGoodsSku shopGoodsSku)
    {
        return toAjax(shopGoodsSkuService.updateShopGoodsSku(shopGoodsSku));
    }

    /**
     * 删除商品规格
     */
    @PreAuthorize("@ss.hasPermi('store:sku:remove')")
    @Log(title = "商品规格", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopGoodsSkuService.deleteShopGoodsSkuByIds(ids));
    }
}
