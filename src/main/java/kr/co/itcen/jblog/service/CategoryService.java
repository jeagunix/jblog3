package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public void join(UserVo vo) {
		categoryDao.insert(vo);
		
	}

	public void insertCategory(CategoryVo categoryVo) {
		categoryDao.insertCategory(categoryVo);
		
	}

	public List<CategoryVo> getCategoryInfo(String id) {
		
		return categoryDao.getCategoryInfo(id);
	}

	public void updatePostCount(Long categoryNo) {
		categoryDao.updatePostCount(categoryNo);
		
	}

	public void deleteCategory(CategoryVo categoryVo) {
		categoryDao.deleteCategory(categoryVo);
		
	}

}
