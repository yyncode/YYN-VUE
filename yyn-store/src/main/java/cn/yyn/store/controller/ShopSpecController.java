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
import cn.yyn.store.domain.ShopSpec;
import cn.yyn.store.service.IShopSpecService;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.common.core.page.TableDataInfo;

/**
 * 商品规格组记录Controller
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@RestController
@RequestMapping("/goods/spec")
public class ShopSpecController extends BaseController
{
    @Autowired
    private IShopSpecService shopSpecService;

    /**
     * 查询商品规格组记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ShopSpec shopSpec)
    {
        startPage();
        List<ShopSpec> list = shopSpecService.selectShopSpecList(shopSpec);
        return getDataTable(list);
    }

    /**
     * 导出商品规格组记录列表
     */
    @Log(title = "商品规格组记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopSpec shopSpec)
    {
        List<ShopSpec> list = shopSpecService.selectShopSpecList(shopSpec);
        ExcelUtil<ShopSpec> util = new ExcelUtil<ShopSpec>(ShopSpec.class);
        util.exportExcel(response, list, "商品规格组记录数据");
    }

    /**
     * 获取商品规格组记录详细信息
     */
    @GetMapping(value = "/{specId}")
    public AjaxResult getInfo(@PathVariable("specId") Long specId)
    {
        return success(shopSpecService.selectShopSpecBySpecId(specId));
    }

    /**
     * 新增商品规格组记录
     */
    @Log(title = "商品规格组记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopSpec shopSpec)
    {
        return toAjax(shopSpecService.insertShopSpec(shopSpec));
    }

    /**
     * 修改商品规格组记录
     */
    @Log(title = "商品规格组记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopSpec shopSpec)
    {
        return toAjax(shopSpecService.updateShopSpec(shopSpec));
    }

    /**
     * 删除商品规格组记录
     */
    @Log(title = "商品规格组记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{specIds}")
    public AjaxResult remove(@PathVariable Long[] specIds)
    {
        return toAjax(shopSpecService.deleteShopSpecBySpecIds(specIds));
    }
}
