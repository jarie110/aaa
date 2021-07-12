package org.jeecg.modules.checked.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.DateUtils;
import org.jeecg.enumUtil.IsChecked;
import org.jeecg.modules.checked.entity.CheckInsurance;
import org.jeecg.modules.checked.service.ICheckInsuranceService;
import org.jeecg.modules.proxyInsurance.entity.InsuranceInHand;
import org.jeecg.modules.proxyInsurance.service.IInsuranceInHandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 核对的保单
 * @Author: jeecg-boot
 * @Date:   2021-05-27
 * @Version: V1.0
 */
@Api(tags="核对的保单")
@RestController
@RequestMapping("/checked/checkInsurance")
@Slf4j
public class CheckInsuranceController extends JeecgController<CheckInsurance, ICheckInsuranceService> {
	@Autowired
	private ICheckInsuranceService checkInsuranceService;

	@Autowired
	private IInsuranceInHandService insuranceInHandService;


	@AutoLog(value = "录入的保单-批量比对")
	@GetMapping(value = "/batchCheck")
	public Result<?> batchCheck(@RequestParam String ids) {
		String[] split = ids.split(",");
//		todo 对比失败，收集比对失败的车牌号
		ArrayList<String> insuranceNums = new ArrayList<>();
		int successList = 0;
		InsuranceInHand insuranceInHand = null;
		for (String id : split) {
			insuranceInHand = insuranceInHandService.getById(id);
			boolean flag = checkInsuranceService.checkAndSaveInsuracne(insuranceInHand);
			if(!flag){
				insuranceInHand.setIsChecked(IsChecked.NO_CHECKED.getCode());//比对失败，修改比对状态为未比对
				insuranceInHandService.saveOrUpdate(insuranceInHand);
				insuranceNums.add(insuranceInHand.getVehicleLicense());
			}else {
				successList ++;
			}
		}
		return Result.OK(successList+"条数据比对成功；"+insuranceNums.size()+"条数据比对失败");
	}



	 @AutoLog(value = "录入的保单-对比保司保单数据")
	 @ApiOperation(value="录入的保单-对比保司保单数据", notes="录入的保单-对比保司保单数据")
	 @GetMapping(value = "/check")
	 public Result<?> check(String id){
		 InsuranceInHand insuranceInHand = insuranceInHandService.getById(id);
		 boolean flag = checkInsuranceService.checkAndSaveInsuracne(insuranceInHand);
		 if(flag){
			 return Result.OK("比对成功");
		 }
		 return Result.error("比对失败,录入信息有误或未导入保司保单");
	 }

	/**
	 * 分页列表查询
	 *
	 * @param checkInsurance
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "核对的保单-分页列表查询")
	@ApiOperation(value="核对的保单-分页列表查询", notes="核对的保单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CheckInsurance checkInsurance,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CheckInsurance> queryWrapper = QueryGenerator.initQueryWrapper(checkInsurance, req.getParameterMap());
		queryWrapper.orderByDesc("check_date");
		Page<CheckInsurance> page = new Page<CheckInsurance>(pageNo, pageSize);
		IPage<CheckInsurance> pageList = checkInsuranceService.page(page, queryWrapper);
		return Result.OK(pageList);
	}


	/**
	 *   根据日期查询
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "比对的保单-根据出单/录入日期查询")
	@ApiOperation(value="比对的保单-根据出单/录入日期查询", notes="比对的保单-根据出单/录入日期查询")
	@GetMapping(value = "/sortBy/{sortBy}")
	public Result<?> sortBy(@PathVariable(name="sortBy") Integer sortBy) {
		List<CheckInsurance> checkInsuranceList = null;
		if(sortBy == 1){
//			1.按照出单日期倒叙
			checkInsuranceList = checkInsuranceService.sortByInsuranceDay();
		}else if(sortBy == 2){
//			2.按照录入时间倒叙
			checkInsuranceList = checkInsuranceService.sortByInputTime();
		}
		return Result.OK(checkInsuranceList);
	}


	/**
	 *   添加
	 *
	 * @param checkInsurance
	 * @return
	 */
	@AutoLog(value = "核对的保单-添加")
	@ApiOperation(value="核对的保单-添加", notes="核对的保单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CheckInsurance checkInsurance) {
		checkInsuranceService.save(checkInsurance);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param checkInsurance
	 * @return
	 */
	@AutoLog(value = "核对的保单-编辑")
	@ApiOperation(value="核对的保单-编辑", notes="核对的保单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CheckInsurance checkInsurance) {
		//		判断如果设置了返点支付时间，则返点支付时间需要大于出单日期
		Date paymentDate = checkInsurance.getRebatePaymentDate();//支付日期
		long paymentMillis = DateUtils.getMillis(paymentDate);

		Date insuranceDate = checkInsurance.getInsuranceDate();//出单日期
		long insuranceDateMillis = DateUtils.getMillis(insuranceDate);
		if(paymentDate != null && paymentMillis > insuranceDateMillis ){
			checkInsuranceService.updateById(checkInsurance);
			return Result.OK("编辑成功!");
		}
		return Result.error("支付时间不应小于出单日期");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "核对的保单-通过id删除")
	@ApiOperation(value="核对的保单-通过id删除", notes="核对的保单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
//		todo
//		删除比对后的数据，改变录入保单的比对状态为未比对
		boolean isChanged = checkInsuranceService.changeInsuranceInHand(id);
		if(isChanged){
			checkInsuranceService.removeById(id);
			return Result.OK("删除成功!");
		}
		return Result.error("删除失败");

	}



	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "核对的保单-批量删除")
	@ApiOperation(value="核对的保单-批量删除", notes="核对的保单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		List<String> idList = (List<String>) Arrays.asList(ids.split(","));
		List<String> list = checkInsuranceService.changeInsuranceInHandBatch(idList);
		this.checkInsuranceService.removeByIds(idList);
		if(CollectionUtils.isNotEmpty(list)){
			return Result.OK("批量删除成功，但未找到对应的录入保单信息"+list.toString());
		}
		return Result.OK("批量删除成功!");
	}



	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "核对的保单-通过id查询")
	@ApiOperation(value="核对的保单-通过id查询", notes="核对的保单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CheckInsurance checkInsurance = checkInsuranceService.getById(id);
		if(checkInsurance==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(checkInsurance);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param checkInsurance
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CheckInsurance checkInsurance) {
        return super.exportXls(request, checkInsurance, CheckInsurance.class, "核对的保单");
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
        return super.importExcel(request, response, CheckInsurance.class);
    }

}
