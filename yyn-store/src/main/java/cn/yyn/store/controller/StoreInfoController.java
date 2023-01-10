package cn.yyn.store.controller;

import cn.yyn.common.annotation.Anonymous;
import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.store.domain.ShopStore;
import cn.yyn.store.service.IStoreInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/store/info")
public class StoreInfoController extends BaseController {

    @Resource
    private IStoreInfoService storeInfoService;

    /**
     * 查询商家(商户)记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ShopStore shopStore)
    {
        startPage();
        List<ShopStore> list = storeInfoService.selectShopStoreList(shopStore);
        return getDataTable(list);
    }


    /**
     * 获取商家(商户)记录详细信息
     */
    @Anonymous
    @GetMapping("get")
    public AjaxResult getInfo()
    {
        return success(storeInfoService.selectShopStoreByStoreId(100001L));
    }

    /**
     * 新增商家(商户)记录
     */
    @Log(title = "商家(商户)记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopStore shopStore)
    {
        return toAjax(storeInfoService.insertShopStore(shopStore));
    }

    /**
     * 修改商家(商户)记录
     */
    @Log(title = "商家(商户)记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopStore shopStore)
    {
        return toAjax(storeInfoService.updateShopStore(shopStore));
    }

    /**
     * 删除商家(商户)记录
     */
    @Log(title = "商家(商户)记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{storeIds}")
    public AjaxResult remove(@PathVariable Long[] storeIds)
    {
        return toAjax(storeInfoService.deleteShopStoreByStoreIds(storeIds));
    }
}
