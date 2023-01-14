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
import cn.yyn.store.domain.ShopSpecValue;
import cn.yyn.store.service.IShopSpecValueService;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.common.core.page.TableDataInfo;

/**
 * 商品规格值记录Controller
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@RestController
@RequestMapping("/goods/spec/value")
public class ShopSpecValueController extends BaseController
{
    @Autowired
    private IShopSpecValueService shopSpecValueService;

    /**
     * 查询商品规格值记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ShopSpecValue shopSpecValue)
    {
        startPage();
        List<ShopSpecValue> list = shopSpecValueService.selectShopSpecValueList(shopSpecValue);
        return getDataTable(list);
    }

    /**
     * 导出商品规格值记录列表
     */
    @Log(title = "商品规格值记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopSpecValue shopSpecValue)
    {
        List<ShopSpecValue> list = shopSpecValueService.selectShopSpecValueList(shopSpecValue);
        ExcelUtil<ShopSpecValue> util = new ExcelUtil<ShopSpecValue>(ShopSpecValue.class);
        util.exportExcel(response, list, "商品规格值记录数据");
    }

    /**
     * 获取商品规格值记录详细信息
     */
    @GetMapping(value = "/{specValueId}")
    public AjaxResult getInfo(@PathVariable("specValueId") Long specValueId)
    {
        return success(shopSpecValueService.selectShopSpecValueBySpecValueId(specValueId));
    }

    /**
     * 新增商品规格值记录
     */
    @Log(title = "商品规格值记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopSpecValue shopSpecValue)
    {
        return toAjax(shopSpecValueService.insertShopSpecValue(shopSpecValue));
    }

    /**
     * 修改商品规格值记录
     */
    @Log(title = "商品规格值记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopSpecValue shopSpecValue)
    {
        return toAjax(shopSpecValueService.updateShopSpecValue(shopSpecValue));
    }

    /**
     * 删除商品规格值记录
     */
    @Log(title = "商品规格值记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{specValueIds}")
    public AjaxResult remove(@PathVariable Long[] specValueIds)
    {
        return toAjax(shopSpecValueService.deleteShopSpecValueBySpecValueIds(specValueIds));
    }
}
