package org.jeecg.modules.proxyInsurance.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.enumUtil.IsChecked;
import org.jeecg.modules.proxyInsurance.entity.InsuranceInHand;
import org.jeecg.modules.proxyInsurance.entity.RenewalPo;
import org.jeecg.modules.proxyInsurance.service.IInsuranceInHandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 录入的保单
 * @Author: jeecg-boot
 * @Date:   2021-05-26
 * @Version: V1.0
 */
@Api(tags="录入的保单")
@RestController
@RequestMapping("/proxyInsurance/insuranceInHand")
@Slf4j
public class InsuranceInHandController extends JeecgController<InsuranceInHand, IInsuranceInHandService> {
	@Autowired
	private IInsuranceInHandService insuranceInHandService;

	 @AutoLog(value = "核对的保单-计算手续费")
	 @ApiOperation(value="核对的保单-计算手续费", notes="核对的保单-计算手续费")
	 @GetMapping(value = "/calculate")
	 public Result<?> calculateServiceFee(String id) throws Exception {
		 InsuranceInHand insuranceInHand = insuranceInHandService.getById(id);
		 return insuranceInHandService.serviceTotalFee(insuranceInHand);
	 }

	 /**
	  * @param uid 用户信息
	  * @return
	  */
	 @AutoLog(value = "录入保单-计算已录入保单计数")
	 @ApiOperation(value="录入保单-计算已录入保单计数", notes="录入保单-计算已录入保单计数")
	 @GetMapping(value = "/count")
	 public Result<?> insuranceCount(String uid){
		 int count = insuranceInHandService.countByUser(uid);
		 return Result.OK(String.valueOf(count));
	 }

	 /**
	  * 对比后的保单计数
	  * @return
	  */
	 @AutoLog(value = "录入保单-对比后的保单计数")
	 @ApiOperation(value="录入保单-对比后的保单计数", notes="录入保单-对比后的保单计数")
	 @GetMapping(value = "/checkedCount")
	 public Result<?> checkNum(String uid){
		 int count = insuranceInHandService.queryByIsCheck(uid);
		 return  Result.OK(String.valueOf(count));
	 }



	 /**
	  * 已返点的保单计数
	  * @return
	  */
	 @AutoLog(value = "录入保单-已返点的保单计数")
	 @ApiOperation(value="录入保单-已返点的保单计数", notes="录入保单-已返点的保单计数")
	 @GetMapping(value = "/paidCount")
	 public Result<?> paidCount(String uid){
		 int count = insuranceInHandService.queryByPaid(uid);
		 return  Result.OK(String.valueOf(count));
	 }

	 /**
	  * 计算该用户所有保单保费总金额
	  * @return
	  */
	 @AutoLog(value = "录入保单-保费总金额")
	 @ApiOperation(value="录入保单-保费总金额", notes="录入保单-保费总金额")
	 @GetMapping(value = "/totalInsuranceFee")
	 public Result<?> totalInsuranceFee(String uid){
		 String totalInsuranceFee = insuranceInHandService.totalInsuranceFee(uid);
		 return Result.OK(totalInsuranceFee);
	 }

	 /**
	  * 计算该用户所有保单已返点总金额
	  * @return
	  */
	 @AutoLog(value = "录入保单-已返点总金额")
	 @ApiOperation(value="录入保单-已返点总金额", notes="录入保单-已返点总金额")
	 @GetMapping(value = "/totalInsurancePaidFee")
	 public Result<?> totalInsurancePaidFee(String uid){
		 String insurancePaidFee = insuranceInHandService.totalInsurancePaidFee(uid);
		 return Result.OK(insurancePaidFee);
	 }

	 /**
	  * 汽车使用性质列表
	  * @return
	  */
	 @AutoLog(value = "录入保单-新续保标志列表")
	 @ApiOperation(value="录入保单-新续保标志列表", notes="录入保单-新续保标志列表")
	 @GetMapping(value = "/usageList")
	 public Result<?> insuranceRenewalList(){
		 List<RenewalPo> usageList = insuranceInHandService.insuranceRenewalList();
		 return Result.OK(usageList);
	 }



	 /**
	 * 分页列表查询
	 *
	 * @param insuranceInHand
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "录入的保单-分页列表查询")
	@ApiOperation(value="录入的保单-分页列表查询", notes="录入的保单-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(InsuranceInHand insuranceInHand,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<InsuranceInHand> queryWrapper = QueryGenerator.initQueryWrapper(insuranceInHand, req.getParameterMap());
		queryWrapper.orderByDesc("insurance_date");
		Page<InsuranceInHand> page = new Page<InsuranceInHand>(pageNo, pageSize);
		IPage<InsuranceInHand> pageList = insuranceInHandService.page(page, queryWrapper);
		return Result.OK(pageList);
	}


//	@AutoLog(value = "录入的保单-分页列表查询排序")
//	@ApiOperation(value="录入的保单-分页列表查询排序", notes="录入的保单-分页列表查询排序")
//	@GetMapping(value = "/list")
//	public Result<?> queryPageList(InsuranceInHand insuranceInHand,
//								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
//								   HttpServletRequest req) {
//		QueryWrapper<InsuranceInHand> queryWrapper = QueryGenerator.initQueryWrapper(insuranceInHand, req.getParameterMap());
//		Page<InsuranceInHand> page = new Page<InsuranceInHand>(pageNo, pageSize);
//		IPage<InsuranceInHand> pageList = insuranceInHandService.page(page, queryWrapper);
//		return Result.OK(pageList);
//	}
	/**
	 *   添加
	 *
	 * @param insuranceInHand
	 * @return
	 */
	@AutoLog(value = "录入的保单-添加")
	@ApiOperation(value="录入的保单-添加", notes="录入的保单-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody InsuranceInHand insuranceInHand) {
//		根据保单号判断数据库中是否有该保单号
		boolean exits = insuranceInHandService.queryByCompulsoryInsurCodeOrCommercialInsurCode(insuranceInHand);
		if(exits){
			return Result.error("添加失败，已存在该保单");
		}
		insuranceInHandService.save(insuranceInHand);
		return Result.OK("添加成功！");
	}
	/**
	 *  编辑
	 *
	 * @param insuranceInHand
	 * @return
	 */
	@AutoLog(value = "录入的保单-编辑")
	@ApiOperation(value="录入的保单-编辑", notes="录入的保单-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody InsuranceInHand insuranceInHand) {
//		从数据库中查找与修改后的对象是否相等
		boolean isEquals = insuranceInHandService.isEquals(insuranceInHand);
		if(!isEquals){
//		不相等就修改比对状态为“未比对”
			insuranceInHand.setIsChecked(IsChecked.NO_CHECKED.getCode());
		}
//		相等则什么也不做
//		如果修改了输入保单信息，则需要将比对状态设置为未比对
		//		完善所有数据
//		insuranceInHandService.setAllArgs(insuranceInHand);
		insuranceInHandService.updateById(insuranceInHand);
		return Result.OK("编辑成功!");
	}


	/**
	 *  编辑
	 *
	 * @param insuranceInHand
	 * @return
	 */
	@AutoLog(value = "录入的保单-用户编辑")
	@ApiOperation(value="录入的保单-用户编辑", notes="录入的保单-用户编辑")
	@PutMapping(value = "/editByUser")
	public Result<?> editByUser(@RequestBody InsuranceInHand insuranceInHand) {
		insuranceInHandService.updateById(insuranceInHand);
		return Result.OK("编辑成功!");
	}



//	/**
//	 *   根据出单/录入日期查询
//	 *
//	 * @param
//	 * @return
//	 */
//	@AutoLog(value = "录入的保单-根据出单/录入日期查询")
//	@ApiOperation(value="录入的保单-根据出单/录入日期查询", notes="录入的保单-根据出单/录入日期查询")
//	@GetMapping(value = "/sortBy/{sortBy}")
//	public Result<?> sortBy(@PathVariable(name="sortBy") Integer sortBy) {
//		List<InsuranceInHand> insuranceInHandList = null;
//		if(sortBy == 1){
////			1.按照出单日期倒叙
//			insuranceInHandList = insuranceInHandService.sortByInsuranceDay();
//		}else if(sortBy == 2){
////			2.按照录入时间倒叙
//			insuranceInHandList = insuranceInHandService.sortByInputTime();
//		}
//		return Result.OK(insuranceInHandList);
//	}


	/**
	 *   根据日期查询
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "录入的保单-根据出单/录入日期查询")
	@ApiOperation(value="录入的保单-根据出单/录入日期查询", notes="录入的保单-根据出单/录入日期查询")
	@GetMapping(value = "/sortBy/{sortBy}")
	public Result<?> sortBy(@PathVariable(name="sortBy") Integer sortBy) {
		List<InsuranceInHand> insuranceInHandList = null;
		if(sortBy == 1){
//			1.按照出单日期倒叙
			insuranceInHandList = insuranceInHandService.sortByInsuranceDay();
		}else if(sortBy == 2){
//			2.按照录入时间倒叙
			insuranceInHandList = insuranceInHandService.sortByInputTime();
		}
		return Result.OK(insuranceInHandList);
	}


	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "录入的保单-通过id删除")
	@ApiOperation(value="录入的保单-通过id删除", notes="录入的保单-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		insuranceInHandService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "录入的保单-批量删除")
	@ApiOperation(value="录入的保单-批量删除", notes="录入的保单-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.insuranceInHandService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "录入的保单-通过id查询")
	@ApiOperation(value="录入的保单-通过id查询", notes="录入的保单-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		InsuranceInHand insuranceInHand = insuranceInHandService.getById(id);
		if(insuranceInHand==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(insuranceInHand);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param insuranceInHand
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, InsuranceInHand insuranceInHand) {
        return super.exportXls(request, insuranceInHand, InsuranceInHand.class, "录入的保单");
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
        return super.importExcel(request, response, InsuranceInHand.class);
    }

}
