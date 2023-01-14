package cn.yyn.store.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.store.domain.ShopGoodsCategoryRel;
import cn.yyn.store.service.IShopGoodsCategoryRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品与分类关系记录Controller
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@RestController
@RequestMapping("/goods/category/rel")
public class ShopGoodsCategoryRelController extends BaseController
{
    @Autowired
    private IShopGoodsCategoryRelService shopGoodsCategoryRelService;

    /**
     * 查询商品与分类关系记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ShopGoodsCategoryRel shopGoodsCategoryRel)
    {
        startPage();
        List<ShopGoodsCategoryRel> list = shopGoodsCategoryRelService.selectShopGoodsCategoryRelList(shopGoodsCategoryRel);
        return getDataTable(list);
    }

    /**
     * 导出商品与分类关系记录列表
     */
    @Log(title = "商品与分类关系记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopGoodsCategoryRel shopGoodsCategoryRel)
    {
        List<ShopGoodsCategoryRel> list = shopGoodsCategoryRelService.selectShopGoodsCategoryRelList(shopGoodsCategoryRel);
        ExcelUtil<ShopGoodsCategoryRel> util = new ExcelUtil<ShopGoodsCategoryRel>(ShopGoodsCategoryRel.class);
        util.exportExcel(response, list, "商品与分类关系记录数据");
    }

    /**
     * 获取商品与分类关系记录详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shopGoodsCategoryRelService.selectShopGoodsCategoryRelById(id));
    }

    /**
     * 新增商品与分类关系记录
     */
    @Log(title = "商品与分类关系记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopGoodsCategoryRel shopGoodsCategoryRel)
    {
        return toAjax(shopGoodsCategoryRelService.insertShopGoodsCategoryRel(shopGoodsCategoryRel));
    }

    /**
     * 修改商品与分类关系记录
     */
    @Log(title = "商品与分类关系记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopGoodsCategoryRel shopGoodsCategoryRel)
    {
        return toAjax(shopGoodsCategoryRelService.updateShopGoodsCategoryRel(shopGoodsCategoryRel));
    }

    /**
     * 删除商品与分类关系记录
     */
    @PreAuthorize("@ss.hasPermi('store:rel:remove')")
    @Log(title = "商品与分类关系记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopGoodsCategoryRelService.deleteShopGoodsCategoryRelByIds(ids));
    }
}
