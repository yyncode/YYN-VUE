package cn.yyn.news.controller.api;

import cn.yyn.common.annotation.Anonymous;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.news.domain.NewsContent;
import cn.yyn.news.domain.NewsNav;
import cn.yyn.news.service.INewsContentService;
import cn.yyn.news.service.INewsNavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Anonymous
@RestController
@RequestMapping("/dataApi/news")
public class NewsController extends BaseController {

    @Autowired
    private INewsNavService newsNavService;

    @Autowired
    private INewsContentService newsContentService;


    /**
     * 查询导航设置列表
     */
    @GetMapping("/nav/list")
    public TableDataInfo list() {
        startPage();
        List<NewsNav> list = newsNavService.selectNewsNavList(new NewsNav());
        return getDataTable(list);
    }

    /**
     * 查询新闻内容列表
     */
    @GetMapping("/content/list")
    public TableDataInfo list(Long navId) {
        startPage();
        List<NewsContent> list = newsContentService.selectNewsContentListByNavId(navId);
        return getDataTable(list);
    }

    /**
     * 获取新闻内容详细信息
     */
    @GetMapping("/content/detail")
    public AjaxResult getInfo(Long newsId) {
        return success(newsContentService.selectNewsContentByNewsId(newsId));
    }
}
