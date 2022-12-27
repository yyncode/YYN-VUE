package cn.yyn.news.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.news.domain.NewsNav;
import cn.yyn.news.mapper.NewsNavMapper;
import cn.yyn.news.service.INewsNavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 导航设置Service业务层处理
 *
 * @author yyncode
 * @date 2022-12-27
 */
@Service
public class NewsNavServiceImpl implements INewsNavService {
    @Autowired
    private NewsNavMapper newsNavMapper;

    /**
     * 查询导航设置
     *
     * @param navId 导航设置主键
     * @return 导航设置
     */
    @Override
    public NewsNav selectNewsNavByNavId(Long navId) {
        return newsNavMapper.selectNewsNavByNavId(navId);
    }

    /**
     * 查询导航设置列表
     *
     * @param newsNav 导航设置
     * @return 导航设置
     */
    @Override
    public List<NewsNav> selectNewsNavList(NewsNav newsNav) {
        return newsNavMapper.selectNewsNavList(newsNav);
    }

    /**
     * 新增导航设置
     *
     * @param newsNav 导航设置
     * @return 结果
     */
    @Override
    public int insertNewsNav(NewsNav newsNav) {
        newsNav.setCreateTime(DateUtils.getNowDate());
        return newsNavMapper.insertNewsNav(newsNav);
    }

    /**
     * 修改导航设置
     *
     * @param newsNav 导航设置
     * @return 结果
     */
    @Override
    public int updateNewsNav(NewsNav newsNav) {
        newsNav.setUpdateTime(DateUtils.getNowDate());
        return newsNavMapper.updateNewsNav(newsNav);
    }

    /**
     * 批量删除导航设置
     *
     * @param navIds 需要删除的导航设置主键
     * @return 结果
     */
    @Override
    public int deleteNewsNavByNavIds(Long[] navIds) {
        return newsNavMapper.deleteNewsNavByNavIds(navIds);
    }

    /**
     * 删除导航设置信息
     *
     * @param navId 导航设置主键
     * @return 结果
     */
    @Override
    public int deleteNewsNavByNavId(Long navId) {
        return newsNavMapper.deleteNewsNavByNavId(navId);
    }
}
