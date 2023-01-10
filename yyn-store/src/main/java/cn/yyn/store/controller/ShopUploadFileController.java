package cn.yyn.store.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.store.domain.ShopUploadFile;
import cn.yyn.store.service.IShopUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件库记录Controller
 * 
 * @author yyncode
 * @date 2023-01-04
 */
@RestController
@RequestMapping("/content/files/list")
public class ShopUploadFileController extends BaseController
{
    @Autowired
    private IShopUploadFileService shopUploadFileService;

    /**
     * 查询文件库记录列表
     */
    @PreAuthorize("@ss.hasPermi('content:files:list:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopUploadFile shopUploadFile)
    {
        startPage();
        List<ShopUploadFile> list = shopUploadFileService.selectShopUploadFileList(shopUploadFile);
        return getDataTable(list);
    }

    /**
     * 导出文件库记录列表
     */
    @PreAuthorize("@ss.hasPermi('content:files:list:export')")
    @Log(title = "文件库记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopUploadFile shopUploadFile)
    {
        List<ShopUploadFile> list = shopUploadFileService.selectShopUploadFileList(shopUploadFile);
        ExcelUtil<ShopUploadFile> util = new ExcelUtil<ShopUploadFile>(ShopUploadFile.class);
        util.exportExcel(response, list, "文件库记录数据");
    }

    /**
     * 获取文件库记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('content:files:list:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return success(shopUploadFileService.selectShopUploadFileByFileId(fileId));
    }

    /**
     * 新增文件库记录
     */
    @PreAuthorize("@ss.hasPermi('content:files:list:add')")
    @Log(title = "文件库记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopUploadFile shopUploadFile)
    {

        return toAjax(shopUploadFileService.insertShopUploadFile(shopUploadFile));
    }

    /**
     * 修改文件库记录
     */
    @PreAuthorize("@ss.hasPermi('content:files:list:edit')")
    @Log(title = "文件库记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopUploadFile shopUploadFile)
    {
        return toAjax(shopUploadFileService.updateShopUploadFile(shopUploadFile));
    }

    /**
     * 删除文件库记录
     */
    @PreAuthorize("@ss.hasPermi('content:files:list:remove')")
    @Log(title = "文件库记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(shopUploadFileService.deleteShopUploadFileByFileIds(fileIds));
    }
}
