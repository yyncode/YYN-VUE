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
import cn.yyn.store.domain.ShopGoodsImage;
import cn.yyn.store.service.IShopGoodsImageService;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.common.core.page.TableDataInfo;

/**
 * 商品图片记录Controller
 * 
 * @author yyncode
 * @date 2023-01-11
 */
@RestController
@RequestMapping("/goods/image/image")
public class ShopGoodsImageController extends BaseController
{
    @Autowired
    private IShopGoodsImageService shopGoodsImageService;

    /**
     * 查询商品图片记录列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ShopGoodsImage shopGoodsImage)
    {
        startPage();
        List<ShopGoodsImage> list = shopGoodsImageService.selectShopGoodsImageList(shopGoodsImage);
        return getDataTable(list);
    }

    /**
     * 导出商品图片记录列表
     */
    @Log(title = "商品图片记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopGoodsImage shopGoodsImage)
    {
        List<ShopGoodsImage> list = shopGoodsImageService.selectShopGoodsImageList(shopGoodsImage);
        ExcelUtil<ShopGoodsImage> util = new ExcelUtil<ShopGoodsImage>(ShopGoodsImage.class);
        util.exportExcel(response, list, "商品图片记录数据");
    }

    /**
     * 获取商品图片记录详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(shopGoodsImageService.selectShopGoodsImageById(id));
    }

    /**
     * 新增商品图片记录
     */
    @Log(title = "商品图片记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopGoodsImage shopGoodsImage)
    {
        return toAjax(shopGoodsImageService.insertShopGoodsImage(shopGoodsImage));
    }

    /**
     * 修改商品图片记录
     */
    @Log(title = "商品图片记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopGoodsImage shopGoodsImage)
    {
        return toAjax(shopGoodsImageService.updateShopGoodsImage(shopGoodsImage));
    }

    /**
     * 删除商品图片记录
     */
    @Log(title = "商品图片记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(shopGoodsImageService.deleteShopGoodsImageByIds(ids));
    }
}
