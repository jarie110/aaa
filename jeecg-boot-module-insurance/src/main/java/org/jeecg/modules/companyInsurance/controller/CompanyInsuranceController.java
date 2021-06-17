package org.jeecg.modules.companyInsurance.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.companyInsurance.service.ICompanyInsuranceService;
import org.jeecg.modules.companyInsurance.entity.CompanyInsurance;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 保险公司保单
 * @Author: jeecg-boot
 * @Date:   2021-06-04
 * @Version: V1.0
 */
@Api(tags="保险公司保单")
@RestController
@RequestMapping("/companyInsurance/companyInsurance")
@Slf4j
public class CompanyInsuranceController extends JeecgController<CompanyInsurance, ICompanyInsuranceService> {
	@Autowired
	private ICompanyInsuranceService companyInsuranceService;
	
	/**
	 * 分页列表查询
	 *
	 * @param companyInsurance
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "保险公司保单-分页列表查询")
	@ApiOperation(value="保险公司保单-分页列表查询", notes="保险公司保单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CompanyInsurance companyInsurance,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CompanyInsurance> queryWrapper = QueryGenerator.initQueryWrapper(companyInsurance, req.getParameterMap());
		Page<CompanyInsurance> page = new Page<CompanyInsurance>(pageNo, pageSize);
		IPage<CompanyInsurance> pageList = companyInsuranceService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param companyInsurance
	 * @return
	 */
	@AutoLog(value = "保险公司保单-添加")
	@ApiOperation(value="保险公司保单-添加", notes="保险公司保单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CompanyInsurance companyInsurance) {
		companyInsuranceService.save(companyInsurance);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param companyInsurance
	 * @return
	 */
	@AutoLog(value = "保险公司保单-编辑")
	@ApiOperation(value="保险公司保单-编辑", notes="保险公司保单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CompanyInsurance companyInsurance) {
		companyInsuranceService.updateById(companyInsurance);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "保险公司保单-通过id删除")
	@ApiOperation(value="保险公司保单-通过id删除", notes="保险公司保单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		companyInsuranceService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "保险公司保单-批量删除")
	@ApiOperation(value="保险公司保单-批量删除", notes="保险公司保单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.companyInsuranceService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "保险公司保单-通过id查询")
	@ApiOperation(value="保险公司保单-通过id查询", notes="保险公司保单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CompanyInsurance companyInsurance = companyInsuranceService.getById(id);
		if(companyInsurance==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(companyInsurance);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param companyInsurance
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CompanyInsurance companyInsurance) {
        return super.exportXls(request, companyInsurance, CompanyInsurance.class, "保险公司保单");
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
        return super.importExcel(request, response, CompanyInsurance.class);
    }

}
