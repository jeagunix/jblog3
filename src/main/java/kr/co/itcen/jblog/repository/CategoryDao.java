package kr.co.itcen.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo vo) {
		sqlSession.insert("category.insert", vo);
		
	}

	public void insertCategory(CategoryVo categoryVo) {
		sqlSession.insert("insertCategory", categoryVo);
		
	}

	public List<CategoryVo> getCategoryInfo(String id) {
		return sqlSession.selectList("getCategoryInfo",id);
	}

	public void updatePostCount(Long categoryNo) {
		sqlSession.update("updatePostCount",categoryNo);
		
		
	}

}
