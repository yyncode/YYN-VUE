package cn.yyn.news.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.news.domain.NewsContent;
import cn.yyn.news.mapper.NewsContentMapper;
import cn.yyn.news.service.INewsContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.yyn.common.utils.SecurityUtils.getUsername;

/**
 * 新闻内容Service业务层处理
 *
 * @author yyncode
 * @date 2022-12-27
 */
@Service
public class NewsContentServiceImpl implements INewsContentService {
    @Autowired
    private NewsContentMapper newsContentMapper;

    /**
     * 查询新闻内容
     *
     * @param newsId 新闻内容主键
     * @return 新闻内容
     */
    @Override
    public NewsContent selectNewsContentByNewsId(Long newsId) {
        return newsContentMapper.selectNewsContentByNewsId(newsId);
    }

    /**
     * 查询新闻内容列表
     *
     * @param newsContent 新闻内容
     * @return 新闻内容
     */
    @Override
    public List<NewsContent> selectNewsContentList(NewsContent newsContent) {
        String username = getUsername();
        if (!username.equals("admin") && !username.contains("审核员"))
            newsContent.setCreateBy(getUsername());
        return newsContentMapper.selectNewsContentList(newsContent);
    }

    /**
     * 新增新闻内容
     *
     * @param newsContent 新闻内容
     * @return 结果
     */
    @Override
    public int insertNewsContent(NewsContent newsContent) {
        newsContent.setCreateBy(getUsername());
        newsContent.setCreateTime(DateUtils.getNowDate());
        return newsContentMapper.insertNewsContent(newsContent);
    }

    /**
     * 修改新闻内容
     *
     * @param newsContent 新闻内容
     * @return 结果
     */
    @Override
    public int updateNewsContent(NewsContent newsContent) {
        newsContent.setUpdateBy(getUsername());
        newsContent.setUpdateTime(DateUtils.getNowDate());
        return newsContentMapper.updateNewsContent(newsContent);
    }

    /**
     * 批量删除新闻内容
     *
     * @param newsIds 需要删除的新闻内容主键
     * @return 结果
     */
    @Override
    public int deleteNewsContentByNewsIds(Long[] newsIds) {
        return newsContentMapper.deleteNewsContentByNewsIds(newsIds);
    }

    /**
     * 删除新闻内容信息
     *
     * @param newsId 新闻内容主键
     * @return 结果
     */
    @Override
    public int deleteNewsContentByNewsId(Long newsId) {
        return newsContentMapper.deleteNewsContentByNewsId(newsId);
    }
}
