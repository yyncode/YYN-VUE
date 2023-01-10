package cn.yyn.store.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.store.domain.ShopUploadGroup;
import cn.yyn.store.service.IShopUploadGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件库分组记录Controller
 * 
 * @author yyncode
 * @date 2023-01-04
 */
@RestController
@RequestMapping("/content/files/group")
public class ShopUploadGroupController extends BaseController
{
    @Autowired
    private IShopUploadGroupService shopUploadGroupService;


    @GetMapping("/treeSelect")
    public AjaxResult tree() {
        return success(shopUploadGroupService.selectShopUploadGroupTree());
    }

    /**
     * 查询文件库分组记录列表
     */
    @PreAuthorize("@ss.hasPermi('store:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopUploadGroup shopUploadGroup)
    {
        startPage();
        List<ShopUploadGroup> list = shopUploadGroupService.selectShopUploadGroupList(shopUploadGroup);
        return getDataTable(list);
    }

    /**
     * 导出文件库分组记录列表
     */
    @PreAuthorize("@ss.hasPermi('store:group:export')")
    @Log(title = "文件库分组记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopUploadGroup shopUploadGroup)
    {
        List<ShopUploadGroup> list = shopUploadGroupService.selectShopUploadGroupList(shopUploadGroup);
        ExcelUtil<ShopUploadGroup> util = new ExcelUtil<ShopUploadGroup>(ShopUploadGroup.class);
        util.exportExcel(response, list, "文件库分组记录数据");
    }

    /**
     * 获取文件库分组记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:group:query')")
    @GetMapping(value = "/{groupId}")
    public AjaxResult getInfo(@PathVariable("groupId") Long groupId)
    {
        return success(shopUploadGroupService.selectShopUploadGroupByGroupId(groupId));
    }

    /**
     * 新增文件库分组记录
     */
    @PreAuthorize("@ss.hasPermi('store:group:add')")
    @Log(title = "文件库分组记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopUploadGroup shopUploadGroup)
    {
        return toAjax(shopUploadGroupService.insertShopUploadGroup(shopUploadGroup));
    }

    /**
     * 修改文件库分组记录
     */
    @PreAuthorize("@ss.hasPermi('store:group:edit')")
    @Log(title = "文件库分组记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopUploadGroup shopUploadGroup)
    {
        return toAjax(shopUploadGroupService.updateShopUploadGroup(shopUploadGroup));
    }

    /**
     * 删除文件库分组记录
     */
    @PreAuthorize("@ss.hasPermi('store:group:remove')")
    @Log(title = "文件库分组记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{groupIds}")
    public AjaxResult remove(@PathVariable Long[] groupIds)
    {
        return toAjax(shopUploadGroupService.deleteShopUploadGroupByGroupIds(groupIds));
    }
}
