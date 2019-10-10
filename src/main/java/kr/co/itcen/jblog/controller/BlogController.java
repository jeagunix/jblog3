package kr.co.itcen.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.vo.BlogVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(@PathVariable String id, @PathVariable Optional<Long> pathNo1,
			@PathVariable Optional<Long> pathNo2, ModelMap modelMap) {
		
		Long categoryNo = 0L;
		Long postNo = 0L;

		if (pathNo2.isPresent()) {
			postNo = pathNo2.get();
			categoryNo = pathNo1.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		} 

		modelMap.putAll(blogService.getAll(id, categoryNo, postNo));
		return "blog/blog-main";
	}
	@RequestMapping(value="/admin/basic", method = RequestMethod.GET)
	public String adminBasic(@PathVariable String id, Model model) {
		BlogVo blogInfo = blogService.getTitleLogo(id);
		
		model.addAttribute("blogInfo", blogInfo);
		
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/admin/basic", method = RequestMethod.POST)
	public String adminBasic(@PathVariable String id, @ModelAttribute BlogVo blogVo) {
		
		blogVo.setBlogId(id);
		blogService.updateBasiceImfo(blogVo);
		
		return "redirect:/"+id;
	}
	

//	@ResponseBody
//	@RequestMapping("/admin/basic")
//	public String adminBasic(@PathVariable String id) {
//		return "id:" + id;
//	}
}
