package cn.yyn.news.service;

import cn.yyn.news.domain.NewsContent;

import java.util.List;

/**
 * 新闻内容Service接口
 *
 * @author yyncode
 * @date 2022-12-27
 */
public interface INewsContentService {
    /**
     * 查询新闻内容
     *
     * @param newsId 新闻内容主键
     * @return 新闻内容
     */
    public NewsContent selectNewsContentByNewsId(Long newsId);

    /**
     * 查询新闻内容列表
     *
     * @param newsContent 新闻内容
     * @return 新闻内容集合
     */
    public List<NewsContent> selectNewsContentList(NewsContent newsContent);

    /**
     * 新增新闻内容
     *
     * @param newsContent 新闻内容
     * @return 结果
     */
    public int insertNewsContent(NewsContent newsContent);

    /**
     * 修改新闻内容
     *
     * @param newsContent 新闻内容
     * @return 结果
     */
    public int updateNewsContent(NewsContent newsContent);

    /**
     * 批量删除新闻内容
     *
     * @param newsIds 需要删除的新闻内容主键集合
     * @return 结果
     */
    public int deleteNewsContentByNewsIds(Long[] newsIds);

    /**
     * 删除新闻内容信息
     *
     * @param newsId 新闻内容主键
     * @return 结果
     */
    public int deleteNewsContentByNewsId(Long newsId);

    /**
     * 根据导航id查询文章内容列表
     *
     * @param navId
     * @return
     */
    List<NewsContent> selectNewsContentListByNavId(Long navId);
}
