package cn.yyn.store.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.store.domain.ShopGoodsService;
import cn.yyn.store.service.IShopGoodsServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品服务与承诺记录Controller
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@RestController
@RequestMapping("/goods/service")
public class ShopGoodsServiceController extends BaseController
{
    @Autowired
    private IShopGoodsServiceService shopGoodsServiceService;

    /**
     * 查询商品服务与承诺记录列表
     */
    @PreAuthorize("@ss.hasPermi('goods:service:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopGoodsService shopGoodsService)
    {
        startPage();
        List<ShopGoodsService> list = shopGoodsServiceService.selectShopGoodsServiceList(shopGoodsService);
        return getDataTable(list);
    }

    /**
     * 导出商品服务与承诺记录列表
     */
    @PreAuthorize("@ss.hasPermi('goods:service:export')")
    @Log(title = "商品服务与承诺记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopGoodsService shopGoodsService)
    {
        List<ShopGoodsService> list = shopGoodsServiceService.selectShopGoodsServiceList(shopGoodsService);
        ExcelUtil<ShopGoodsService> util = new ExcelUtil<ShopGoodsService>(ShopGoodsService.class);
        util.exportExcel(response, list, "商品服务与承诺记录数据");
    }

    /**
     * 获取商品服务与承诺记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('goods:service:query')")
    @GetMapping(value = "/{serviceId}")
    public AjaxResult getInfo(@PathVariable("serviceId") Long serviceId)
    {
        return success(shopGoodsServiceService.selectShopGoodsServiceByServiceId(serviceId));
    }

    /**
     * 新增商品服务与承诺记录
     */
    @PreAuthorize("@ss.hasPermi('goods:service:add')")
    @Log(title = "商品服务与承诺记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopGoodsService shopGoodsService)
    {
        return toAjax(shopGoodsServiceService.insertShopGoodsService(shopGoodsService));
    }

    /**
     * 修改商品服务与承诺记录
     */
    @PreAuthorize("@ss.hasPermi('goods:service:edit')")
    @Log(title = "商品服务与承诺记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopGoodsService shopGoodsService)
    {
        return toAjax(shopGoodsServiceService.updateShopGoodsService(shopGoodsService));
    }

    /**
     * 删除商品服务与承诺记录
     */
    @PreAuthorize("@ss.hasPermi('goods:service:remove')")
    @Log(title = "商品服务与承诺记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serviceIds}")
    public AjaxResult remove(@PathVariable Long[] serviceIds)
    {
        return toAjax(shopGoodsServiceService.deleteShopGoodsServiceByServiceIds(serviceIds));
    }
}
