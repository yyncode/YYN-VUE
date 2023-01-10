package cn.yyn.store.controller;

import cn.yyn.common.annotation.Log;
import cn.yyn.common.core.controller.BaseController;
import cn.yyn.common.core.domain.AjaxResult;
import cn.yyn.common.core.page.TableDataInfo;
import cn.yyn.common.enums.BusinessType;
import cn.yyn.common.utils.poi.ExcelUtil;
import cn.yyn.store.domain.ShopDeliveryRule;
import cn.yyn.store.service.IShopDeliveryRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 配送模板区域及运费Controller
 * 
 * @author yyncode
 * @date 2023-01-07
 */
@RestController
@RequestMapping("/store/rule")
public class ShopDeliveryRuleController extends BaseController
{
    @Autowired
    private IShopDeliveryRuleService shopDeliveryRuleService;

    /**
     * 查询配送模板区域及运费列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ShopDeliveryRule shopDeliveryRule)
    {
        startPage();
        List<ShopDeliveryRule> list = shopDeliveryRuleService.selectShopDeliveryRuleList(shopDeliveryRule);
        return getDataTable(list);
    }

    /**
     * 导出配送模板区域及运费列表
     */
    @Log(title = "配送模板区域及运费", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ShopDeliveryRule shopDeliveryRule)
    {
        List<ShopDeliveryRule> list = shopDeliveryRuleService.selectShopDeliveryRuleList(shopDeliveryRule);
        ExcelUtil<ShopDeliveryRule> util = new ExcelUtil<ShopDeliveryRule>(ShopDeliveryRule.class);
        util.exportExcel(response, list, "配送模板区域及运费数据");
    }

    /**
     * 获取配送模板区域及运费详细信息
     */
    @GetMapping(value = "/{ruleId}")
    public AjaxResult getInfo(@PathVariable("ruleId") Long ruleId)
    {
        return success(shopDeliveryRuleService.selectShopDeliveryRuleByRuleId(ruleId));
    }

    /**
     * 新增配送模板区域及运费
     */
    @Log(title = "配送模板区域及运费", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ShopDeliveryRule shopDeliveryRule)
    {
        return toAjax(shopDeliveryRuleService.insertShopDeliveryRule(shopDeliveryRule));
    }

    /**
     * 修改配送模板区域及运费
     */
    @Log(title = "配送模板区域及运费", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ShopDeliveryRule shopDeliveryRule)
    {
        return toAjax(shopDeliveryRuleService.updateShopDeliveryRule(shopDeliveryRule));
    }

    /**
     * 删除配送模板区域及运费
     */
    @Log(title = "配送模板区域及运费", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ruleIds}")
    public AjaxResult remove(@PathVariable Long[] ruleIds)
    {
        return toAjax(shopDeliveryRuleService.deleteShopDeliveryRuleByRuleIds(ruleIds));
    }
}
