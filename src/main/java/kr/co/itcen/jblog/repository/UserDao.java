package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.UserVo;


@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;


	public void insert(UserVo vo)  { //왜 boolean으로 할까? 반환
		sqlSession.insert("user.insert", vo);
	}

	public UserVo getId(String id) {
		UserVo result = sqlSession.selectOne("user.getById", id);
		return result;
	}



}