package cn.yyn.store.service.impl;

import cn.yyn.common.utils.DateUtils;
import cn.yyn.store.domain.ShopHelp;
import cn.yyn.store.mapper.ShopHelpMapper;
import cn.yyn.store.service.IShopHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static cn.yyn.common.utils.SecurityUtils.getUsername;

/**
 * 帮助中心记录Service业务层处理
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@Service
public class ShopHelpServiceImpl implements IShopHelpService 
{
    @Autowired
    private ShopHelpMapper shopHelpMapper;
    @Value("${store.id}")
    private String storeId;

    /**
     * 查询帮助中心记录
     * 
     * @param helpId 帮助中心记录主键
     * @return 帮助中心记录
     */
    @Override
    public ShopHelp selectShopHelpByHelpId(Long helpId)
    {
        return shopHelpMapper.selectShopHelpByHelpId(helpId);
    }

    /**
     * 查询帮助中心记录列表
     * 
     * @param shopHelp 帮助中心记录
     * @return 帮助中心记录
     */
    @Override
    public List<ShopHelp> selectShopHelpList(ShopHelp shopHelp)
    {
        shopHelp.setStoreId(Long.parseLong(storeId));
        return shopHelpMapper.selectShopHelpList(shopHelp);
    }

    /**
     * 新增帮助中心记录
     * 
     * @param shopHelp 帮助中心记录
     * @return 结果
     */
    @Override
    public int insertShopHelp(ShopHelp shopHelp)
    {
        shopHelp.setStoreId(Long.parseLong(storeId));
        shopHelp.setCreateTime(DateUtils.getNowDate());
        shopHelp.setCreateBy(getUsername());
        shopHelp.setUpdateTime(DateUtils.getNowDate());
        shopHelp.setUpdateBy(getUsername());
        return shopHelpMapper.insertShopHelp(shopHelp);
    }

    /**
     * 修改帮助中心记录
     * 
     * @param shopHelp 帮助中心记录
     * @return 结果
     */
    @Override
    public int updateShopHelp(ShopHelp shopHelp)
    {
        shopHelp.setUpdateTime(DateUtils.getNowDate());
        shopHelp.setUpdateBy(getUsername());
        return shopHelpMapper.updateShopHelp(shopHelp);
    }

    /**
     * 批量删除帮助中心记录
     * 
     * @param helpIds 需要删除的帮助中心记录主键
     * @return 结果
     */
    @Override
    public int deleteShopHelpByHelpIds(Long[] helpIds)
    {
        return shopHelpMapper.deleteShopHelpByHelpIds(helpIds);
    }

    /**
     * 删除帮助中心记录信息
     * 
     * @param helpId 帮助中心记录主键
     * @return 结果
     */
    @Override
    public int deleteShopHelpByHelpId(Long helpId)
    {
        return shopHelpMapper.deleteShopHelpByHelpId(helpId);
    }
}
