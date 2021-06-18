package org.jeecg.modules.message.controller;

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
import org.jeecg.modules.message.entity.InsuranceMessage;
import org.jeecg.modules.message.entity.MsgPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.jeecg.modules.message.service.IInsuranceMessageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 保单消息
 * @Author: jeecg-boot
 * @Date:   2021-06-18
 * @Version: V1.0
 */
@Api(tags="保单消息")
@RestController
@RequestMapping("/message/insuranceMessage")
@Slf4j
public class InsuranceMessageController extends JeecgController<InsuranceMessage, IInsuranceMessageService> {
	@Autowired
	private IInsuranceMessageService insuranceMessageService;

	/**
	 * 保单消息提醒
	 * @param msgPo
	 * @return
	 */
	 @AutoLog(value = "获取当前保单消息")
	 @ApiOperation(value="保单消息-获取", notes="保单消息-获取")
	 @PostMapping(value = "/listByUid")
	 public Result<?> listByUid(@RequestBody MsgPo msgPo) {
		 Date createDate = null;
		 try {
			 createDate = DateUtils.parseDate(msgPo.getNowDay(),"yyyy-MM-dd");
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
		 List<InsuranceMessage> messages = insuranceMessageService.findByDayAndUid(msgPo.getUid(),createDate);
		 if(CollectionUtils.isNotEmpty(messages)){
			 return Result.OK(messages);
		 }
		 return Result.OK();
	 }




	/**
	 * 分页列表查询
	 *
	 * @param insuranceMessage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "保单消息-分页列表查询")
	@ApiOperation(value="保单消息-分页列表查询", notes="保单消息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(InsuranceMessage insuranceMessage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<InsuranceMessage> queryWrapper = QueryGenerator.initQueryWrapper(insuranceMessage, req.getParameterMap());
		Page<InsuranceMessage> page = new Page<InsuranceMessage>(pageNo, pageSize);
		IPage<InsuranceMessage> pageList = insuranceMessageService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param insuranceMessage
	 * @return
	 */
	@AutoLog(value = "保单消息-添加")
	@ApiOperation(value="保单消息-添加", notes="保单消息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody InsuranceMessage insuranceMessage) {
		insuranceMessageService.save(insuranceMessage);
		return Result.OK("添加成功！");
	}




	/**
	 *  编辑
	 *
	 * @param insuranceMessage
	 * @return
	 */
	@AutoLog(value = "保单消息-编辑")
	@ApiOperation(value="保单消息-编辑", notes="保单消息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody InsuranceMessage insuranceMessage) {
		insuranceMessageService.updateById(insuranceMessage);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "保单消息-通过id删除")
	@ApiOperation(value="保单消息-通过id删除", notes="保单消息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		insuranceMessageService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "保单消息-批量删除")
	@ApiOperation(value="保单消息-批量删除", notes="保单消息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.insuranceMessageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "保单消息-通过id查询")
	@ApiOperation(value="保单消息-通过id查询", notes="保单消息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		InsuranceMessage insuranceMessage = insuranceMessageService.getById(id);
		if(insuranceMessage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(insuranceMessage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param insuranceMessage
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, InsuranceMessage insuranceMessage) {
        return super.exportXls(request, insuranceMessage, InsuranceMessage.class, "保单消息");
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
        return super.importExcel(request, response, InsuranceMessage.class);
    }

}
