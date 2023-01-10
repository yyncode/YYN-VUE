package cn.yyn.store.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.store.domain.ShopArticle;
import cn.yyn.store.service.IShopArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文章记录Controller
 * 
 * @author yyncode
 * @date 2023-01-04
 */
@RestController
@RequestMapping("/content/article")
public class ShopArticleController extends BaseController
{
    @Autowired
    private IShopArticleService shopArticleService;

    /**
     * 查询文章记录列表
     */
    @PreAuthorize("@ss.hasPermi('content:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(ShopArticle shopArticle)
    {
        startPage();
        List<ShopArticle> list = shopArticleService.selectShopArticleList(shopArticle);
        return getDataTable(list);
    }

    /**
     * 导出文章记录列表
     */
    @PreAuthorize("@ss.hasPermi('content:article:export')")
    @Log(title = "文章记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopArticle shopArticle)
    {
        List<ShopArticle> list = shopArticleService.selectShopArticleList(shopArticle);
        ExcelUtil<ShopArticle> util = new ExcelUtil<ShopArticle>(ShopArticle.class);
        util.exportExcel(response, list, "文章记录数据");
    }

    /**
     * 获取文章记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('content:article:query')")
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Long articleId)
    {
        return success(shopArticleService.selectShopArticleByArticleId(articleId));
    }

    /**
     * 新增文章记录
     */
    @PreAuthorize("@ss.hasPermi('content:article:add')")
    @Log(title = "文章记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopArticle shopArticle)
    {
        return toAjax(shopArticleService.insertShopArticle(shopArticle));
    }

    /**
     * 修改文章记录
     */
    @PreAuthorize("@ss.hasPermi('content:article:edit')")
    @Log(title = "文章记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopArticle shopArticle)
    {
        return toAjax(shopArticleService.updateShopArticle(shopArticle));
    }

    /**
     * 删除文章记录
     */
    @PreAuthorize("@ss.hasPermi('content:article:remove')")
    @Log(title = "文章记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable Long[] articleIds)
    {
        return toAjax(shopArticleService.deleteShopArticleByArticleIds(articleIds));
    }
}
