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
import cn.yyn.store.domain.ShopStoreSetting;
import cn.yyn.store.service.IShopStoreSettingService;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.common.core.page.TableDataInfo;

/**
 * 商家设置记录Controller
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@RestController
@RequestMapping("/store/setting")
public class ShopStoreSettingController extends BaseController
{
    @Autowired
    private IShopStoreSettingService shopStoreSettingService;

    /**
     * 查询商家设置记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ShopStoreSetting shopStoreSetting)
    {
        startPage();
        List<ShopStoreSetting> list = shopStoreSettingService.selectShopStoreSettingList(shopStoreSetting);
        return getDataTable(list);
    }

    /**
     * 导出商家设置记录列表
     */
    @Log(title = "商家设置记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopStoreSetting shopStoreSetting)
    {
        List<ShopStoreSetting> list = shopStoreSettingService.selectShopStoreSettingList(shopStoreSetting);
        ExcelUtil<ShopStoreSetting> util = new ExcelUtil<ShopStoreSetting>(ShopStoreSetting.class);
        util.exportExcel(response, list, "商家设置记录数据");
    }

    /**
     * 获取商家设置记录详细信息
     */
    @GetMapping(value = "/{key}")
    public AjaxResult getInfo(@PathVariable("key") String key)
    {
        return success(shopStoreSettingService.selectShopStoreSettingByKey(key));
    }

    /**
     * 保存商家设置记录
     */
    @Log(title = "保存商家设置记录", businessType = BusinessType.UPDATE)
    @PostMapping("/save")
    public AjaxResult save(@RequestBody ShopStoreSetting shopStoreSetting)
    {
        return toAjax(shopStoreSettingService.saveShopStoreSetting(shopStoreSetting));
    }

    /**
     * 新增商家设置记录
     */
    @Log(title = "商家设置记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopStoreSetting shopStoreSetting)
    {
        return toAjax(shopStoreSettingService.insertShopStoreSetting(shopStoreSetting));
    }

    /**
     * 修改商家设置记录
     */
    @Log(title = "商家设置记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopStoreSetting shopStoreSetting)
    {
        return toAjax(shopStoreSettingService.updateShopStoreSetting(shopStoreSetting));
    }

    /**
     * 删除商家设置记录
     */
    @Log(title = "商家设置记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{keys}")
    public AjaxResult remove(@PathVariable String[] keys)
    {
        return toAjax(shopStoreSettingService.deleteShopStoreSettingByKeys(keys));
    }
}
