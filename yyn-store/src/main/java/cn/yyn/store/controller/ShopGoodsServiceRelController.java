package cn.yyn.store.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.store.domain.ShopGoodsServiceRel;
import cn.yyn.store.service.IShopGoodsServiceRelService;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.common.core.page.TableDataInfo;

/**
 * 商品与服务承诺关系记录Controller
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@RestController
@RequestMapping("/goods/service/rel")
public class ShopGoodsServiceRelController extends BaseController
{
    @Autowired
    private IShopGoodsServiceRelService shopGoodsServiceRelService;

    /**
     * 查询商品与服务承诺关系记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ShopGoodsServiceRel shopGoodsServiceRel)
    {
        startPage();
        List<ShopGoodsServiceRel> list = shopGoodsServiceRelService.selectShopGoodsServiceRelList(shopGoodsServiceRel);
        return getDataTable(list);
    }

    /**
     * 导出商品与服务承诺关系记录列表
     */
    @Log(title = "商品与服务承诺关系记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopGoodsServiceRel shopGoodsServiceRel)
    {
        List<ShopGoodsServiceRel> list = shopGoodsServiceRelService.selectShopGoodsServiceRelList(shopGoodsServiceRel);
        ExcelUtil<ShopGoodsServiceRel> util = new ExcelUtil<ShopGoodsServiceRel>(ShopGoodsServiceRel.class);
        util.exportExcel(response, list, "商品与服务承诺关系记录数据");
    }

    /**
     * 获取商品与服务承诺关系记录详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shopGoodsServiceRelService.selectShopGoodsServiceRelById(id));
    }

    /**
     * 新增商品与服务承诺关系记录
     */
    @Log(title = "商品与服务承诺关系记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopGoodsServiceRel shopGoodsServiceRel)
    {
        return toAjax(shopGoodsServiceRelService.insertShopGoodsServiceRel(shopGoodsServiceRel));
    }

    /**
     * 修改商品与服务承诺关系记录
     */
    @Log(title = "商品与服务承诺关系记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopGoodsServiceRel shopGoodsServiceRel)
    {
        return toAjax(shopGoodsServiceRelService.updateShopGoodsServiceRel(shopGoodsServiceRel));
    }

    /**
     * 删除商品与服务承诺关系记录
     */
    @Log(title = "商品与服务承诺关系记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopGoodsServiceRelService.deleteShopGoodsServiceRelByIds(ids));
    }
}
