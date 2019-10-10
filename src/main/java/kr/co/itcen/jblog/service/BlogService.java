package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;
	
	public void join(UserVo vo) {
		blogDao.insert(vo);
	}

	public ModelMap getAll(String id, long categoryNo, long postNo) {
	
		ModelMap modelMap = new ModelMap();
		modelMap.addAllAttributes(blogDao.getAll(id, categoryNo, postNo));
		return modelMap;
	}

	public BlogVo getTitleLogo(String id) {
		
		return blogDao.getTitleLogo(id);
	}

	public void updateBasiceImfo(BlogVo blogVo) {
		blogDao.updateBasiceImfo(blogVo);
		
	}
}

