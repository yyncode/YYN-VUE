package cn.yyn.store.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.store.domain.ShopCategory;
import cn.yyn.store.service.IShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品分类Controller
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@RestController
@RequestMapping("/goods/category")
public class ShopCategoryController extends BaseController
{
    @Autowired
    private IShopCategoryService shopCategoryService;

    /**
     * 查询商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('goods:category:list')")
    @GetMapping("/list")
    public AjaxResult list(ShopCategory shopCategory)
    {
        List<ShopCategory> list = shopCategoryService.selectShopCategoryList(shopCategory);
        return success(list);
    }

    /**
     * 导出商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('goods:category:export')")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopCategory shopCategory)
    {
        List<ShopCategory> list = shopCategoryService.selectShopCategoryList(shopCategory);
        ExcelUtil<ShopCategory> util = new ExcelUtil<ShopCategory>(ShopCategory.class);
        util.exportExcel(response, list, "商品分类数据");
    }

    /**
     * 获取商品分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('goods:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return success(shopCategoryService.selectShopCategoryByCategoryId(categoryId));
    }

    /**
     * 新增商品分类
     */
    @PreAuthorize("@ss.hasPermi('goods:category:add')")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopCategory shopCategory)
    {
        return toAjax(shopCategoryService.insertShopCategory(shopCategory));
    }

    /**
     * 修改商品分类
     */
    @PreAuthorize("@ss.hasPermi('goods:category:edit')")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopCategory shopCategory)
    {
        return toAjax(shopCategoryService.updateShopCategory(shopCategory));
    }

    /**
     * 删除商品分类
     */
    @PreAuthorize("@ss.hasPermi('goods:category:remove')")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(shopCategoryService.deleteShopCategoryByCategoryIds(categoryIds));
    }
}
