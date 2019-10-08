package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kr.co.itcen.jblog.vo.UserVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo vo) {
		sqlSession.insert("category.insert", vo);
		
	}

}
