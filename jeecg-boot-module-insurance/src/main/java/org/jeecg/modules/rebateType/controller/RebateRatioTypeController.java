package org.jeecg.modules.rebateType.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.rebateType.entity.RebateRatioType;
import org.jeecg.modules.rebateType.service.IRebateRatioTypeService;

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
 * @Description: 返点比类型
 * @Author: jeecg-boot
 * @Date:   2021-06-02
 * @Version: V1.0
 */
@Api(tags="返点比类型")
@RestController
@RequestMapping("/rebateType/rebateRatioType")
@Slf4j
public class RebateRatioTypeController extends JeecgController<RebateRatioType, IRebateRatioTypeService> {
	@Autowired
	private IRebateRatioTypeService rebateRatioTypeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param rebateRatioType
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "返点比类型-分页列表查询")
	@ApiOperation(value="返点比类型-分页列表查询", notes="返点比类型-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(RebateRatioType rebateRatioType,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RebateRatioType> queryWrapper = QueryGenerator.initQueryWrapper(rebateRatioType, req.getParameterMap());
		Page<RebateRatioType> page = new Page<RebateRatioType>(pageNo, pageSize);
		IPage<RebateRatioType> pageList = rebateRatioTypeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param rebateRatioType
	 * @return
	 */
	@AutoLog(value = "返点比类型-添加")
	@ApiOperation(value="返点比类型-添加", notes="返点比类型-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody RebateRatioType rebateRatioType) {
		rebateRatioTypeService.save(rebateRatioType);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param rebateRatioType
	 * @return
	 */
	@AutoLog(value = "返点比类型-编辑")
	@ApiOperation(value="返点比类型-编辑", notes="返点比类型-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody RebateRatioType rebateRatioType) {
		rebateRatioTypeService.updateById(rebateRatioType);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "返点比类型-通过id删除")
	@ApiOperation(value="返点比类型-通过id删除", notes="返点比类型-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		rebateRatioTypeService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "返点比类型-批量删除")
	@ApiOperation(value="返点比类型-批量删除", notes="返点比类型-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.rebateRatioTypeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "返点比类型-通过id查询")
	@ApiOperation(value="返点比类型-通过id查询", notes="返点比类型-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		RebateRatioType rebateRatioType = rebateRatioTypeService.getById(id);
		if(rebateRatioType==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(rebateRatioType);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param rebateRatioType
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RebateRatioType rebateRatioType) {
        return super.exportXls(request, rebateRatioType, RebateRatioType.class, "返点比类型");
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
        return super.importExcel(request, response, RebateRatioType.class);
    }

}
