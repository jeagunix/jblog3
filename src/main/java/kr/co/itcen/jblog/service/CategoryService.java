package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	public void join(UserVo vo) {
		categoryDao.insert(vo);
		
	}

}
