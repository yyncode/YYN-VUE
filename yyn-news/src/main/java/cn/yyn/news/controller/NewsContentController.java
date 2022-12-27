package cn.yyn.news.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.StringUtils;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.news.domain.NewsContent;
import cn.yyn.news.domain.NewsNav;
import cn.yyn.news.service.INewsContentService;
import cn.yyn.news.service.INewsNavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * 新闻内容Controller
 *
 * @author yyncode
 * @date 2022-12-27
 */
@RestController
@RequestMapping("/news/content")
public class NewsContentController extends BaseController {
    @Autowired
    private INewsContentService newsContentService;

    @Autowired
    private INewsNavService newsNavService;

    /**
     * 查询新闻内容列表
     */
    @PreAuthorize("@ss.hasPermi('news:content:list')")
    @GetMapping("/list")
    public TableDataInfo list(NewsContent newsContent) {
        List<NewsNav> newsNavs = newsNavService.selectNewsNavList(new NewsNav());
        startPage();
        List<NewsContent> list = newsContentService.selectNewsContentList(newsContent);
        for (NewsContent content : list) {
            Optional<NewsNav> first = newsNavs
                    .stream()
                    .filter(nav -> nav.getNavId().equals(content.getNewsNavId())).findFirst();
            first.ifPresent(newsNav -> content.setNewsNavName(newsNav.getNavName()));
        }
        return getDataTable(list);
    }

    /**
     * 导出新闻内容列表
     */
    @PreAuthorize("@ss.hasPermi('news:content:export')")
    @Log(title = "新闻内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NewsContent newsContent) {
        List<NewsContent> list = newsContentService.selectNewsContentList(newsContent);
        ExcelUtil<NewsContent> util = new ExcelUtil<NewsContent>(NewsContent.class);
        util.exportExcel(response, list, "新闻内容数据");
    }

    /**
     * 获取新闻内容详细信息
     */
    @PreAuthorize("@ss.hasPermi('news:content:query')")
    @GetMapping(value = {"", "/{newsId}"})
    public AjaxResult getInfo(@PathVariable(value = "newsId", required = false) Long newsId) {
        AjaxResult ajax = AjaxResult.success();
        List<NewsNav> newsNavs = newsNavService.selectNewsNavList(new NewsNav());
        ajax.put("newsNavs", newsNavs);
        if (StringUtils.isNotNull(newsId)) {
            ajax.put(AjaxResult.DATA_TAG, newsContentService.selectNewsContentByNewsId(newsId));
        }
        return ajax;
    }

    /**
     * 新增新闻内容
     */
    @PreAuthorize("@ss.hasPermi('news:content:add')")
    @Log(title = "新闻内容", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NewsContent newsContent) {
        return toAjax(newsContentService.insertNewsContent(newsContent));
    }

    /**
     * 修改新闻内容
     */
    @PreAuthorize("@ss.hasPermi('news:content:edit')")
    @Log(title = "新闻内容", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NewsContent newsContent) {
        return toAjax(newsContentService.updateNewsContent(newsContent));
    }

    /**
     * 删除新闻内容
     */
    @PreAuthorize("@ss.hasPermi('news:content:remove')")
    @Log(title = "新闻内容", businessType = BusinessType.DELETE)
    @DeleteMapping("/{newsIds}")
    public AjaxResult remove(@PathVariable Long[] newsIds) {
        return toAjax(newsContentService.deleteNewsContentByNewsIds(newsIds));
    }
}
