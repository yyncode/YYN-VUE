package cn.yyn.store.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.store.domain.ShopDeliveryTemplate;
import cn.yyn.store.service.IShopDeliveryTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 配送模板主Controller
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@RestController
@RequestMapping("/setting/delivery/template")
public class ShopDeliveryTemplateController extends BaseController
{
    @Autowired
    private IShopDeliveryTemplateService shopDeliveryService;

    /**
     * 查询配送模板主列表
     */
    @PreAuthorize("@ss.hasPermi('setting:delivery:template:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopDeliveryTemplate shopDeliveryTemplate) {
        startPage();
        List<ShopDeliveryTemplate> list = shopDeliveryService.selectShopDeliveryList(shopDeliveryTemplate);
        return getDataTable(list);
    }

    /**
     * 查询配送模板不分页列表
     */
    @PreAuthorize("@ss.hasPermi('setting:delivery:template:list:no:page')")
    @GetMapping("/list/no/page")
    public AjaxResult list() {
        List<ShopDeliveryTemplate> list = shopDeliveryService.selectShopDeliveryList(new ShopDeliveryTemplate());
        return success(list);
    }

    /**
     * 导出配送模板主列表
     */
    @PreAuthorize("@ss.hasPermi('setting:delivery:template:export')")
    @Log(title = "配送模板主", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopDeliveryTemplate shopDeliveryTemplate) {
        List<ShopDeliveryTemplate> list = shopDeliveryService.selectShopDeliveryList(shopDeliveryTemplate);
        ExcelUtil<ShopDeliveryTemplate> util = new ExcelUtil<ShopDeliveryTemplate>(ShopDeliveryTemplate.class);
        util.exportExcel(response, list, "配送模板主数据");
    }

    /**
     * 获取配送模板主详细信息
     */
    @PreAuthorize("@ss.hasPermi('setting:delivery:template:query')")
    @GetMapping(value = "/{deliveryId}")
    public AjaxResult getInfo(@PathVariable("deliveryId") Long deliveryId)
    {
        return success(shopDeliveryService.selectShopDeliveryByDeliveryId(deliveryId));
    }

    /**
     * 新增配送模板主
     */
    @PreAuthorize("@ss.hasPermi('setting:delivery:template:add')")
    @Log(title = "配送模板主", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopDeliveryTemplate shopDeliveryTemplate)
    {
        return toAjax(shopDeliveryService.insertShopDelivery(shopDeliveryTemplate));
    }

    /**
     * 修改配送模板主
     */
    @PreAuthorize("@ss.hasPermi('setting:delivery:template:edit')")
    @Log(title = "配送模板主", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopDeliveryTemplate shopDeliveryTemplate)
    {
        return toAjax(shopDeliveryService.updateShopDelivery(shopDeliveryTemplate));
    }

    /**
     * 删除配送模板主
     */
    @PreAuthorize("@ss.hasPermi('setting:delivery:template:remove')")
    @Log(title = "配送模板主", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deliveryIds}")
    public AjaxResult remove(@PathVariable Long[] deliveryIds)
    {
        return toAjax(shopDeliveryService.deleteShopDeliveryByDeliveryIds(deliveryIds));
    }
}
