package com.example.userscrud.service;

import org.springframework.stereotype.Service;

import com.example.userscrud.entity.Post;
import com.example.userscrud.repository.PostRepository;
import com.example.userscrud.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;

	@Override
	public Post createPost(Post post) {
		return postRepository.save(post);
	}

}
