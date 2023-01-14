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
import cn.yyn.store.domain.ShopStoreAddress;
import cn.yyn.store.service.IShopStoreAddressService;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.common.core.page.TableDataInfo;

/**
 * 商家地址记录Controller
 * 
 * @author yyncode
 * @date 2023-01-12
 */
@RestController
@RequestMapping("/store/address")
public class ShopStoreAddressController extends BaseController
{
    @Autowired
    private IShopStoreAddressService shopStoreAddressService;

    /**
     * 查询商家地址记录列表
     */
    @PreAuthorize("@ss.hasPermi('store:address:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopStoreAddress shopStoreAddress)
    {
        startPage();
        List<ShopStoreAddress> list = shopStoreAddressService.selectShopStoreAddressList(shopStoreAddress);
        return getDataTable(list);
    }

    /**
     * 导出商家地址记录列表
     */
    @PreAuthorize("@ss.hasPermi('store:address:export')")
    @Log(title = "商家地址记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopStoreAddress shopStoreAddress)
    {
        List<ShopStoreAddress> list = shopStoreAddressService.selectShopStoreAddressList(shopStoreAddress);
        ExcelUtil<ShopStoreAddress> util = new ExcelUtil<ShopStoreAddress>(ShopStoreAddress.class);
        util.exportExcel(response, list, "商家地址记录数据");
    }

    /**
     * 获取商家地址记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:address:query')")
    @GetMapping(value = "/{addressId}")
    public AjaxResult getInfo(@PathVariable("addressId") Long addressId)
    {
        return success(shopStoreAddressService.selectShopStoreAddressByAddressId(addressId));
    }

    /**
     * 新增商家地址记录
     */
    @PreAuthorize("@ss.hasPermi('store:address:add')")
    @Log(title = "商家地址记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopStoreAddress shopStoreAddress)
    {
        return toAjax(shopStoreAddressService.insertShopStoreAddress(shopStoreAddress));
    }

    /**
     * 修改商家地址记录
     */
    @PreAuthorize("@ss.hasPermi('store:address:edit')")
    @Log(title = "商家地址记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopStoreAddress shopStoreAddress)
    {
        return toAjax(shopStoreAddressService.updateShopStoreAddress(shopStoreAddress));
    }

    /**
     * 删除商家地址记录
     */
    @PreAuthorize("@ss.hasPermi('store:address:remove')")
    @Log(title = "商家地址记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{addressIds}")
    public AjaxResult remove(@PathVariable Long[] addressIds)
    {
        return toAjax(shopStoreAddressService.deleteShopStoreAddressByAddressIds(addressIds));
    }
}
