package cn.yyn.store.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.store.domain.ShopArticleCategory;
import cn.yyn.store.service.IShopArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文章分类Controller
 * 
 * @author yyncode
 * @date 2023-01-04
 */
@RestController
@RequestMapping("/content/article/category")
public class ShopArticleCategoryController extends BaseController
{
    @Autowired
    private IShopArticleCategoryService shopArticleCategoryService;

    /**
     * 查询文章分类列表
     */
    @PreAuthorize("@ss.hasPermi('content:article:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopArticleCategory shopArticleCategory)
    {
        startPage();
        List<ShopArticleCategory> list = shopArticleCategoryService.selectShopArticleCategoryList(shopArticleCategory);
        return getDataTable(list);
    }

    /**
     * 查询文章分类列表
     */
    @PreAuthorize("@ss.hasPermi('content:article:category:list')")
    @GetMapping("/list/no/page")
    public AjaxResult listNoPage()
    {
        List<ShopArticleCategory> list = shopArticleCategoryService.selectShopArticleCategoryList(new ShopArticleCategory());
        return success(list);
    }

    /**
     * 导出文章分类列表
     */
    @PreAuthorize("@ss.hasPermi('content:article:category:export')")
    @Log(title = "文章分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopArticleCategory shopArticleCategory)
    {
        List<ShopArticleCategory> list = shopArticleCategoryService.selectShopArticleCategoryList(shopArticleCategory);
        ExcelUtil<ShopArticleCategory> util = new ExcelUtil<ShopArticleCategory>(ShopArticleCategory.class);
        util.exportExcel(response, list, "文章分类数据");
    }

    /**
     * 获取文章分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('content:article:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return success(shopArticleCategoryService.selectShopArticleCategoryByCategoryId(categoryId));
    }

    /**
     * 新增文章分类
     */
    @PreAuthorize("@ss.hasPermi('content:article:category:add')")
    @Log(title = "文章分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopArticleCategory shopArticleCategory)
    {
        return toAjax(shopArticleCategoryService.insertShopArticleCategory(shopArticleCategory));
    }

    /**
     * 修改文章分类
     */
    @PreAuthorize("@ss.hasPermi('content:article:category:edit')")
    @Log(title = "文章分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopArticleCategory shopArticleCategory)
    {
        return toAjax(shopArticleCategoryService.updateShopArticleCategory(shopArticleCategory));
    }

    /**
     * 删除文章分类
     */
    @PreAuthorize("@ss.hasPermi('store:category:remove')")
    @Log(title = "文章分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(shopArticleCategoryService.deleteShopArticleCategoryByCategoryIds(categoryIds));
    }
}
