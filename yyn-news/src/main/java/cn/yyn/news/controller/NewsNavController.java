package cn.yyn.news.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.news.domain.NewsNav;
import cn.yyn.news.service.INewsNavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 导航设置Controller
 *
 * @author yyncode
 * @date 2022-12-27
 */
@RestController
@RequestMapping("/news/nav")
public class NewsNavController extends BaseController {
    @Autowired
    private INewsNavService newsNavService;

    /**
     * 查询导航设置列表
     */
    @PreAuthorize("@ss.hasPermi('news:nav:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewsNav newsNav) {
        startPage();
        List<NewsNav> list = newsNavService.selectNewsNavList(newsNav);
        return getDataTable(list);
    }


    /**
     * 导出导航设置列表
     */
    @PreAuthorize("@ss.hasPermi('news:nav:export')")
    @Log(title = "导航设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsNav newsNav) {
        List<NewsNav> list = newsNavService.selectNewsNavList(newsNav);
        ExcelUtil<NewsNav> util = new ExcelUtil<NewsNav>(NewsNav.class);
        util.exportExcel(response, list, "导航设置数据");
    }

    /**
     * 获取导航设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('news:nav:query')")
    @GetMapping(value = "/{navId}")
    public AjaxResult getInfo(@PathVariable("navId") Long navId) {
        return success(newsNavService.selectNewsNavByNavId(navId));
    }

    /**
     * 新增导航设置
     */
    @PreAuthorize("@ss.hasPermi('news:nav:add')")
    @Log(title = "导航设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsNav newsNav) {
        return toAjax(newsNavService.insertNewsNav(newsNav));
    }

    /**
     * 修改导航设置
     */
    @PreAuthorize("@ss.hasPermi('news:nav:edit')")
    @Log(title = "导航设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsNav newsNav) {
        return toAjax(newsNavService.updateNewsNav(newsNav));
    }

    /**
     * 删除导航设置
     */
    @PreAuthorize("@ss.hasPermi('news:nav:remove')")
    @Log(title = "导航设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{navIds}")
    public AjaxResult remove(@PathVariable Long[] navIds) {
        return toAjax(newsNavService.deleteNewsNavByNavIds(navIds));
    }
}
