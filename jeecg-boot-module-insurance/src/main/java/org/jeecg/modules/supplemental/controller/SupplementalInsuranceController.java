package org.jeecg.modules.supplemental.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.supplemental.entity.SupplementalInsurance;
import org.jeecg.modules.supplemental.service.ISupplementalInsuranceService;
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
 * @Description: 附加险
 * @Author: jeecg-boot
 * @Date:   2021-05-24
 * @Version: V1.0
 */
@Api(tags="附加险")
@RestController
@RequestMapping("/supplemental/supplementalInsurance")
@Slf4j
public class SupplementalInsuranceController extends JeecgController<SupplementalInsurance, ISupplementalInsuranceService> {
	@Autowired
	private ISupplementalInsuranceService supplementalInsuranceService;

	/**
	 * 分页列表查询
	 *
	 * @param supplementalInsurance
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "附加险-分页列表查询")
	@ApiOperation(value="附加险-分页列表查询", notes="附加险-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(SupplementalInsurance supplementalInsurance,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SupplementalInsurance> queryWrapper = QueryGenerator.initQueryWrapper(supplementalInsurance, req.getParameterMap());
		Page<SupplementalInsurance> page = new Page<SupplementalInsurance>(pageNo, pageSize);
		IPage<SupplementalInsurance> pageList = supplementalInsuranceService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param supplementalInsurance
	 * @return
	 */
	@AutoLog(value = "附加险-添加")
	@ApiOperation(value="附加险-添加", notes="附加险-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody SupplementalInsurance supplementalInsurance) {
		supplementalInsuranceService.save(supplementalInsurance);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param supplementalInsurance
	 * @return
	 */
	@AutoLog(value = "附加险-编辑")
	@ApiOperation(value="附加险-编辑", notes="附加险-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody SupplementalInsurance supplementalInsurance) {
		supplementalInsuranceService.updateById(supplementalInsurance);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "附加险-通过id删除")
	@ApiOperation(value="附加险-通过id删除", notes="附加险-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		supplementalInsuranceService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "附加险-批量删除")
	@ApiOperation(value="附加险-批量删除", notes="附加险-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.supplementalInsuranceService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "附加险-通过id查询")
	@ApiOperation(value="附加险-通过id查询", notes="附加险-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		SupplementalInsurance supplementalInsurance = supplementalInsuranceService.getById(id);
		if(supplementalInsurance==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(supplementalInsurance);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param supplementalInsurance
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SupplementalInsurance supplementalInsurance) {
        return super.exportXls(request, supplementalInsurance, SupplementalInsurance.class, "附加险");
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
        return super.importExcel(request, response, SupplementalInsurance.class);
    }

}
