package cn.yyn.store.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.store.domain.ShopHelp;
import cn.yyn.store.service.IShopHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 帮助中心记录Controller
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@RestController
@RequestMapping("/content/files/help")
public class ShopHelpController extends BaseController
{
    @Autowired
    private IShopHelpService shopHelpService;

    /**
     * 查询帮助中心记录列表
     */
    @PreAuthorize("@ss.hasPermi('content:files:help:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopHelp shopHelp)
    {
        startPage();
        List<ShopHelp> list = shopHelpService.selectShopHelpList(shopHelp);
        return getDataTable(list);
    }

    /**
     * 导出帮助中心记录列表
     */
    @PreAuthorize("@ss.hasPermi('content:files:help:export')")
    @Log(title = "帮助中心记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopHelp shopHelp)
    {
        List<ShopHelp> list = shopHelpService.selectShopHelpList(shopHelp);
        ExcelUtil<ShopHelp> util = new ExcelUtil<ShopHelp>(ShopHelp.class);
        util.exportExcel(response, list, "帮助中心记录数据");
    }

    /**
     * 获取帮助中心记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('content:files:help:query')")
    @GetMapping(value = "/{helpId}")
    public AjaxResult getInfo(@PathVariable("helpId") Long helpId)
    {
        return success(shopHelpService.selectShopHelpByHelpId(helpId));
    }

    /**
     * 新增帮助中心记录
     */
    @PreAuthorize("@ss.hasPermi('content:files:help:add')")
    @Log(title = "帮助中心记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopHelp shopHelp)
    {
        return toAjax(shopHelpService.insertShopHelp(shopHelp));
    }

    /**
     * 修改帮助中心记录
     */
    @PreAuthorize("@ss.hasPermi('content:files:help:edit')")
    @Log(title = "帮助中心记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopHelp shopHelp)
    {
        return toAjax(shopHelpService.updateShopHelp(shopHelp));
    }

    /**
     * 删除帮助中心记录
     */
    @PreAuthorize("@ss.hasPermi('content:files:help:remove')")
    @Log(title = "帮助中心记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{helpIds}")
    public AjaxResult remove(@PathVariable Long[] helpIds)
    {
        return toAjax(shopHelpService.deleteShopHelpByHelpIds(helpIds));
    }
}
