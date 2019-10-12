package kr.co.itcen.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.FileuploadService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private PostService postService;
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private FileuploadService fileuploadService;

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

	@RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
	public String adminBasic(@PathVariable String id, Model model) {
		/* blog의 title을 변동을 위해 추가*/
		BlogVo blogInfo = blogService.getTitleLogo(id);
		model.addAttribute("blogInfo", blogInfo);

		return "blog/blog-admin-basic";
	}

	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String adminBasic(@PathVariable String id, @ModelAttribute BlogVo blogVo,
			@RequestParam(value = "logoFile", required = false) MultipartFile multipartFile) {

		String url = fileuploadService.restore(multipartFile);
		blogVo.setBlogId(id);
		blogVo.setLogo(url);
		blogService.updateBasiceImfo(blogVo);

		return "redirect:/" + id;
	}

	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable String id, ModelMap model) {
		/* blog의 title을 변동을 위해 추가*/
		BlogVo blogInfo = blogService.getTitleLogo(id);
		model.addAttribute("blogInfo", blogInfo);
		
		
		List<CategoryVo> categoryInfoList = categoryService.getCategoryInfo(id);
		model.addAttribute("categoryInfoList", categoryInfoList);
		
		return "blog/blog-admin-category";
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/categoryInsert", method=RequestMethod.POST)
	public List<CategoryVo> adminCategoryInsert(@PathVariable String id, CategoryVo categoryVo) {

		categoryVo.setBlogId(id);
		categoryService.insertCategory(categoryVo);
		
		List<CategoryVo> categoryInfoList = categoryService.getCategoryInfo(id);
		return categoryInfoList;
	}
	
	@ResponseBody
	@RequestMapping(value="/admin/categoryDelete", method=RequestMethod.POST)
	public List<CategoryVo> adminCategoryDelete(@PathVariable String id, CategoryVo categoryVo) {

		List<CategoryVo> categoryInfoList = categoryService.getCategoryInfo(id);
		return categoryInfoList;
	}

	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable String id, Model model) {
		/* blog의 title을 변동을 위해 추가*/
		BlogVo blogInfo = blogService.getTitleLogo(id);
		model.addAttribute("blogInfo", blogInfo);
		
		List<CategoryVo> categoryList = blogService.getCategoryName(id);
		model.addAttribute("categoryList", categoryList);
		return "blog/blog-admin-write";
	}

	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String adminWrite(@PathVariable String id, @ModelAttribute PostVo postVo) {
		blogService.insertPost(postVo);
		categoryService.updatePostCount(postVo.getCategoryNo());

		return "redirect:/" + id + "/1";
	}

//	@ResponseBody
//	@RequestMapping("/admin/basic")
//	public String adminBasic(@PathVariable String id) {
//		return "id:" + id;
//	}
}
