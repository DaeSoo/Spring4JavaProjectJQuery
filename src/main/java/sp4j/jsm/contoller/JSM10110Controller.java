package sp4j.jsm.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sp4j.common.model.CommandMap;

@Controller
public class JSM10110Controller {
	
	@RequestMapping(value = "/jsm/jsm10110/openPage.do")
	public ModelAndView jsm10110OpenPage(CommandMap commandMap,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("/jsm/jsm10110");

		return mv;
	}
}
