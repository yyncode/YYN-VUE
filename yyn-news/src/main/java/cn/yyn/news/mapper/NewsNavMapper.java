package cn.yyn.news.mapper;

import cn.yyn.news.domain.NewsNav;

import java.util.List;

/**
 * 导航设置Mapper接口
 *
 * @author yyncode
 * @date 2022-12-27
 */
public interface NewsNavMapper {
    /**
     * 查询导航设置
     *
     * @param navId 导航设置主键
     * @return 导航设置
     */
    public NewsNav selectNewsNavByNavId(Long navId);

    /**
     * 查询导航设置列表
     *
     * @param newsNav 导航设置
     * @return 导航设置集合
     */
    public List<NewsNav> selectNewsNavList(NewsNav newsNav);

    /**
     * 新增导航设置
     *
     * @param newsNav 导航设置
     * @return 结果
     */
    public int insertNewsNav(NewsNav newsNav);

    /**
     * 修改导航设置
     *
     * @param newsNav 导航设置
     * @return 结果
     */
    public int updateNewsNav(NewsNav newsNav);

    /**
     * 删除导航设置
     *
     * @param navId 导航设置主键
     * @return 结果
     */
    public int deleteNewsNavByNavId(Long navId);

    /**
     * 批量删除导航设置
     *
     * @param navIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsNavByNavIds(Long[] navIds);
}
