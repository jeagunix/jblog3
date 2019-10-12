package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.PostDao;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;

	
}
