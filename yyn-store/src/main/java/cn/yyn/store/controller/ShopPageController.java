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
import cn.yyn.store.domain.ShopPage;
import cn.yyn.store.service.IShopPageService;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.common.core.page.TableDataInfo;

/**
 * 店铺页面记录Controller
 * 
 * @author yyncode
 * @date 2023-01-12
 */
@RestController
@RequestMapping("/store/page")
public class ShopPageController extends BaseController
{
    @Autowired
    private IShopPageService shopPageService;

    /**
     * 查询店铺页面记录列表
     */
    @PreAuthorize("@ss.hasPermi('store:page:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopPage shopPage)
    {
        startPage();
        List<ShopPage> list = shopPageService.selectShopPageList(shopPage);
        return getDataTable(list);
    }

    /**
     * 导出店铺页面记录列表
     */
    @PreAuthorize("@ss.hasPermi('store:page:export')")
    @Log(title = "店铺页面记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopPage shopPage)
    {
        List<ShopPage> list = shopPageService.selectShopPageList(shopPage);
        ExcelUtil<ShopPage> util = new ExcelUtil<ShopPage>(ShopPage.class);
        util.exportExcel(response, list, "店铺页面记录数据");
    }

    /**
     * 获取店铺页面记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:page:query')")
    @GetMapping(value = "/{pageId}")
    public AjaxResult getInfo(@PathVariable("pageId") Long pageId)
    {
        return success(shopPageService.selectShopPageByPageId(pageId));
    }

    /**
     * 新增店铺页面记录
     */
    @PreAuthorize("@ss.hasPermi('store:page:add')")
    @Log(title = "店铺页面记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopPage shopPage)
    {
        return toAjax(shopPageService.insertShopPage(shopPage));
    }

    /**
     * 修改店铺页面记录
     */
    @PreAuthorize("@ss.hasPermi('store:page:edit')")
    @Log(title = "店铺页面记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopPage shopPage)
    {
        return toAjax(shopPageService.updateShopPage(shopPage));
    }

    /**
     * 删除店铺页面记录
     */
    @PreAuthorize("@ss.hasPermi('store:page:remove')")
    @Log(title = "店铺页面记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pageIds}")
    public AjaxResult remove(@PathVariable Long[] pageIds)
    {
        return toAjax(shopPageService.deleteShopPageByPageIds(pageIds));
    }
}
