package cn.yyn.store.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.store.domain.ShopExpress;
import cn.yyn.store.service.IShopExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 物流公司记录Controller
 * 
 * @author yyncode
 * @date 2023-01-12
 */
@RestController
@RequestMapping("/setting/delivery/express")
public class ShopExpressController extends BaseController
{
    @Autowired
    private IShopExpressService shopExpressService;

    /**
     * 查询物流公司记录列表
     */
    @PreAuthorize("@ss.hasPermi('setting:delivery:express:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopExpress shopExpress)
    {
        startPage();
        List<ShopExpress> list = shopExpressService.selectShopExpressList(shopExpress);
        return getDataTable(list);
    }

    /**
     * 导出物流公司记录列表
     */
    @PreAuthorize("@ss.hasPermi('setting:delivery:express:export')")
    @Log(title = "物流公司记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopExpress shopExpress)
    {
        List<ShopExpress> list = shopExpressService.selectShopExpressList(shopExpress);
        ExcelUtil<ShopExpress> util = new ExcelUtil<ShopExpress>(ShopExpress.class);
        util.exportExcel(response, list, "物流公司记录数据");
    }

    /**
     * 获取物流公司记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('setting:delivery:express:query')")
    @GetMapping(value = "/{expressId}")
    public AjaxResult getInfo(@PathVariable("expressId") Long expressId)
    {
        return success(shopExpressService.selectShopExpressByExpressId(expressId));
    }

    /**
     * 新增物流公司记录
     */
    @PreAuthorize("@ss.hasPermi('setting:delivery:express:add')")
    @Log(title = "物流公司记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopExpress shopExpress)
    {
        return toAjax(shopExpressService.insertShopExpress(shopExpress));
    }

    /**
     * 修改物流公司记录
     */
    @PreAuthorize("@ss.hasPermi('setting:delivery:express:edit')")
    @Log(title = "物流公司记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopExpress shopExpress)
    {
        return toAjax(shopExpressService.updateShopExpress(shopExpress));
    }

    /**
     * 删除物流公司记录
     */
    @PreAuthorize("@ss.hasPermi('setting:delivery:express:remove')")
    @Log(title = "物流公司记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{expressIds}")
    public AjaxResult remove(@PathVariable Long[] expressIds)
    {
        return toAjax(shopExpressService.deleteShopExpressByExpressIds(expressIds));
    }
}
