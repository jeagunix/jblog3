package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo vo) {
		sqlSession.insert("blog.insert", vo);

	}

	public Map<String, Object> getAll(String id, long categoryNo, long postNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("blogInfo", sqlSession.selectOne("blog.blogInfo", id));
		map.put("categoryList", sqlSession.selectList("blog.categoryList", id));
		
		Map<String, Object> buff = new HashMap<String, Object>();
		buff.put("blogId", id);
		buff.put("categoryNo", categoryNo);
		buff.put("postNo", postNo);
		
		map.put("postList", sqlSession.selectList("blog.postList", buff));
		map.put("postInfo", sqlSession.selectOne("blog.postInfo", buff));
		
		return map;
	}

	public BlogVo getTitleLogo(String id) {
		
		return sqlSession.selectOne("getTitleLogo",id);
	}

	public void updateBasiceImfo(BlogVo blogVo) {
		sqlSession.update("updateBasiceImfo",blogVo);
		
	}

	public List<CategoryVo> getCategoryName(String id) {
		List<CategoryVo> result = sqlSession.selectList("getCategoryName", id);
		return result;
	}

}
