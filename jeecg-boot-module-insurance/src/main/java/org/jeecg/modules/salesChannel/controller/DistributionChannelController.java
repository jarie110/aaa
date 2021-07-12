package org.jeecg.modules.salesChannel.controller;

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
import org.jeecg.modules.salesChannel.entity.DistributionChannel;
import org.jeecg.modules.salesChannel.service.IDistributionChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 渠道表
 * @Author: jeecg-boot
 * @Date:   2021-05-24
 * @Version: V1.0
 */
@Api(tags="渠道表")
@RestController
@RequestMapping("/salesChannel/distributionChannel")
@Slf4j
public class DistributionChannelController extends JeecgController<DistributionChannel, IDistributionChannelService> {
	@Autowired
	private IDistributionChannelService distributionChannelService;

	/**
	 * 分页列表查询
	 *
	 * @param distributionChannel
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "渠道表-分页列表查询")
	@ApiOperation(value="渠道表-分页列表查询", notes="渠道表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(DistributionChannel distributionChannel,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<DistributionChannel> queryWrapper = QueryGenerator.initQueryWrapper(distributionChannel, req.getParameterMap());
		Page<DistributionChannel> page = new Page<DistributionChannel>(pageNo, pageSize);
		IPage<DistributionChannel> pageList = distributionChannelService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 查询渠道名
	 * @return
	 */
	@AutoLog(value = "渠道表-列表查询")
	@ApiOperation(value="渠道表-列表查询", notes="渠道表-列表查询")
	@GetMapping("/channelList")
	public Result<?> queryChannelName(){
		List<DistributionChannel> list = distributionChannelService.list();
		List<String> stringList = list.stream().map(DistributionChannel::getChannelName).collect(Collectors.toList());
		return Result.OK(stringList);
	}

	/**
	 *   添加
	 *
	 * @param distributionChannel
	 * @return
	 */
	@AutoLog(value = "渠道表-添加")
	@ApiOperation(value="渠道表-添加", notes="渠道表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody DistributionChannel distributionChannel) {
		boolean isExist =distributionChannelService.isExist(distributionChannel);
		if(isExist){
			return Result.error("已存在该渠道类型");
		}
		distributionChannelService.save(distributionChannel);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param distributionChannel
	 * @return
	 */
	@AutoLog(value = "渠道表-编辑")
	@ApiOperation(value="渠道表-编辑", notes="渠道表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody DistributionChannel distributionChannel) {
		boolean isExist =distributionChannelService.isExist(distributionChannel);
		if(isExist){
			return Result.error("已存在该渠道类型");
		}
		distributionChannelService.updateById(distributionChannel);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "渠道表-通过id删除")
	@ApiOperation(value="渠道表-通过id删除", notes="渠道表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		distributionChannelService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "渠道表-批量删除")
	@ApiOperation(value="渠道表-批量删除", notes="渠道表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.distributionChannelService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "渠道表-通过id查询")
	@ApiOperation(value="渠道表-通过id查询", notes="渠道表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		DistributionChannel distributionChannel = distributionChannelService.getById(id);
		if(distributionChannel==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(distributionChannel);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param distributionChannel
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DistributionChannel distributionChannel) {
        return super.exportXls(request, distributionChannel, DistributionChannel.class, "渠道表");
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
        return super.importExcel(request, response, DistributionChannel.class);
    }

}
