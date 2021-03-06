package com.xiaokaceng.openci.web.controller.developer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayatang.querychannel.support.Page;
import com.xiaokaceng.openci.application.DeveloperApplication;
import com.xiaokaceng.openci.domain.Developer;
import com.xiaokaceng.openci.web.controller.BaseController;
import com.xiaokaceng.openci.web.dto.ResultDto;
import com.xiaokaceng.openci.web.dto.ResultMsgDto;

@Controller
@RequestMapping("/developer")
public class DeveloperController extends BaseController {

	@Inject
	private DeveloperApplication developerApplication;
	
	@ResponseBody
    @RequestMapping("/pagingquery")
	public Map<String, Object> pagingQuery(int page, int pagesize, Developer example) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Page<Developer> developerPage = developerApplication.pagingQeuryDevelopers(example, page, pagesize);
		
		dataMap.put("Rows", developerPage.getResult());
		dataMap.put("start", page * pagesize - pagesize);
		dataMap.put("limit", pagesize);
		dataMap.put("Total", developerPage.getTotalCount());
		return dataMap;
	}
	
	@ResponseBody
    @RequestMapping("/create")
	public ResultDto createDeveloper(Developer developer) {
		if (developerApplication.checkDeveloperIdIsExist(developer.getDeveloperId())) {
			return ResultMsgDto.createFailure("开发者ID已存在!");
		}
		developerApplication.createDeveloper(developer);
		return ResultDto.createSuccess();
	}
	
	@ResponseBody
    @RequestMapping("/update")
	public ResultDto updateDeveloper(Developer developer) {
		developerApplication.updateDeveloperInfo(developer);
		return ResultDto.createSuccess();
	}
	
	@ResponseBody
    @RequestMapping("/abolish")
	public ResultDto abolishDeveloper(Developer developer) {
		developerApplication.abolishDeveloper(developer);
		return ResultDto.createSuccess();
	}
	
	@ResponseBody
    @RequestMapping(value = "/abolish_developers", method = RequestMethod.POST, consumes = "application/json")
	public ResultDto abolishDevelopers(@RequestBody Developer[] developers) {
		developerApplication.abolishDevelopers(Arrays.asList(developers));
		return ResultDto.createSuccess();
	}
	
}
