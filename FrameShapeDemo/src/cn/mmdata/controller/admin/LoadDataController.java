package cn.mmdata.controller.admin;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.mmdata.controller.base.BaseController;
import cn.mmdata.util.ConfigUtil;
import cn.mmdata.util.HttpClientUtil;
import cn.mmdata.util.Jurisdiction;
import cn.mmdata.util.PageData;

@Controller
@RequestMapping(value = "/load")
public class LoadDataController extends BaseController {

	@RequestMapping(value = "/update")
	public void loadToMem(PrintWriter out) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		boolean result = false;
		HttpClientUtil util = HttpClientUtil.getInstance("UTF-8");
		Map params = new HashMap();
		// params.put("method", "saveUser");
		ConfigUtil config = new ConfigUtil();
		String url = config.getLOADURL();
		System.out.println("url:" + url);
		result = util.setGetRequest(url, params);
		if(result){
//			mv.addObject("msg", "success");
			out.write("success");
			out.close();
			
		}else{
//			mv.addObject("msg", "fail");
			out.write("fail");
			out.close();
		}
		
//		mv.setViewName("save_result");
//		return mv;
	}

	 
	 
}
