package cn.yyn.store.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.store.domain.ShopRegion;
import cn.yyn.store.service.IShopRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 省市区数据Controller
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@RestController
@RequestMapping("/store/region")
public class ShopRegionController extends BaseController
{
    @Autowired
    private IShopRegionService shopRegionService;

    /**
     * 查询省市区数据列表
     */
//    @GetMapping("/list")
//    public TableDataInfo list(ShopRegion shopRegion)
//    {
//        startPage();
//        List<ShopRegion> list = shopRegionService.selectShopRegionList(shopRegion);
//        return getDataTable(list);
//    }

    /**
     * 查询省市区数据列表
     */
    @GetMapping("/list")
    public AjaxResult list(ShopRegion shopRegion)
    {
        List<ShopRegion> list = shopRegionService.selectShopRegionList(shopRegion);
        return success(list);
    }

    /**
     * 导出省市区数据列表
     */
    @Log(title = "省市区数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopRegion shopRegion)
    {
        List<ShopRegion> list = shopRegionService.selectShopRegionList(shopRegion);
        ExcelUtil<ShopRegion> util = new ExcelUtil<ShopRegion>(ShopRegion.class);
        util.exportExcel(response, list, "省市区数据数据");
    }

    /**
     * 获取省市区数据详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shopRegionService.selectShopRegionById(id));
    }

    /**
     * 新增省市区数据
     */
    @Log(title = "省市区数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopRegion shopRegion)
    {
        return toAjax(shopRegionService.insertShopRegion(shopRegion));
    }

    /**
     * 修改省市区数据
     */
    @Log(title = "省市区数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopRegion shopRegion)
    {
        return toAjax(shopRegionService.updateShopRegion(shopRegion));
    }

    /**
     * 删除省市区数据
     */
    @Log(title = "省市区数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopRegionService.deleteShopRegionByIds(ids));
    }
}
