package org.jeecg.modules.demo.usage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.demo.usage.entity.InsuranceUsage;
import org.jeecg.modules.demo.usage.service.IInsuranceUsageService;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 使用性质
 * @Author: jeecg-boot
 * @Date:   2021-05-24
 * @Version: V1.0
 */
@Api(tags="insurance_usage")
@RestController
@RequestMapping("/usage/insuranceUsage")
@Slf4j
public class InsuranceUsageController extends JeecgController<InsuranceUsage, IInsuranceUsageService> {
	@Autowired
	private IInsuranceUsageService insuranceUsageService;

	/**
	 * 分页列表查询
	 *
	 * @param insuranceUsage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "insurance_usage-分页列表查询")
	@ApiOperation(value="insurance_usage-分页列表查询", notes="insurance_usage-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(InsuranceUsage insuranceUsage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<InsuranceUsage> queryWrapper = QueryGenerator.initQueryWrapper(insuranceUsage, req.getParameterMap());
		Page<InsuranceUsage> page = new Page<InsuranceUsage>(pageNo, pageSize);
		IPage<InsuranceUsage> pageList = insuranceUsageService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param insuranceUsage
	 * @return
	 */
	@AutoLog(value = "insurance_usage-添加")
	@ApiOperation(value="insurance_usage-添加", notes="insurance_usage-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody InsuranceUsage insuranceUsage) {
		insuranceUsageService.save(insuranceUsage);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param insuranceUsage
	 * @return
	 */
	@AutoLog(value = "insurance_usage-编辑")
	@ApiOperation(value="insurance_usage-编辑", notes="insurance_usage-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody InsuranceUsage insuranceUsage) {
		insuranceUsageService.updateById(insuranceUsage);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "insurance_usage-通过id删除")
	@ApiOperation(value="insurance_usage-通过id删除", notes="insurance_usage-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		insuranceUsageService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "insurance_usage-批量删除")
	@ApiOperation(value="insurance_usage-批量删除", notes="insurance_usage-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.insuranceUsageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "insurance_usage-通过id查询")
	@ApiOperation(value="insurance_usage-通过id查询", notes="insurance_usage-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		InsuranceUsage insuranceUsage = insuranceUsageService.getById(id);
		if(insuranceUsage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(insuranceUsage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param insuranceUsage
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, InsuranceUsage insuranceUsage) {
        return super.exportXls(request, insuranceUsage, InsuranceUsage.class, "insurance_usage");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, InsuranceUsage.class);
    }

}
