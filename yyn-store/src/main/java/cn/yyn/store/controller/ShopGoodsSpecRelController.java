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
import cn.yyn.store.domain.ShopGoodsSpecRel;
import cn.yyn.store.service.IShopGoodsSpecRelService;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.common.core.page.TableDataInfo;

/**
 * 商品与规格值关系记录Controller
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@RestController
@RequestMapping("/goods/spec/rel")
public class ShopGoodsSpecRelController extends BaseController
{
    @Autowired
    private IShopGoodsSpecRelService shopGoodsSpecRelService;

    /**
     * 查询商品与规格值关系记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ShopGoodsSpecRel shopGoodsSpecRel)
    {
        startPage();
        List<ShopGoodsSpecRel> list = shopGoodsSpecRelService.selectShopGoodsSpecRelList(shopGoodsSpecRel);
        return getDataTable(list);
    }

    /**
     * 导出商品与规格值关系记录列表
     */
    @Log(title = "商品与规格值关系记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopGoodsSpecRel shopGoodsSpecRel)
    {
        List<ShopGoodsSpecRel> list = shopGoodsSpecRelService.selectShopGoodsSpecRelList(shopGoodsSpecRel);
        ExcelUtil<ShopGoodsSpecRel> util = new ExcelUtil<ShopGoodsSpecRel>(ShopGoodsSpecRel.class);
        util.exportExcel(response, list, "商品与规格值关系记录数据");
    }

    /**
     * 获取商品与规格值关系记录详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shopGoodsSpecRelService.selectShopGoodsSpecRelById(id));
    }

    /**
     * 新增商品与规格值关系记录
     */
    @Log(title = "商品与规格值关系记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopGoodsSpecRel shopGoodsSpecRel)
    {
        return toAjax(shopGoodsSpecRelService.insertShopGoodsSpecRel(shopGoodsSpecRel));
    }

    /**
     * 修改商品与规格值关系记录
     */
    @Log(title = "商品与规格值关系记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopGoodsSpecRel shopGoodsSpecRel)
    {
        return toAjax(shopGoodsSpecRelService.updateShopGoodsSpecRel(shopGoodsSpecRel));
    }

    /**
     * 删除商品与规格值关系记录
     */
    @Log(title = "商品与规格值关系记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopGoodsSpecRelService.deleteShopGoodsSpecRelByIds(ids));
    }
}
