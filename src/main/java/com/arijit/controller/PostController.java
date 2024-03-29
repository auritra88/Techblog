package com.arijit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.arijit.model.Post;
import com.arijit.model.User;
import com.arijit.service.PostService;

@Controller
public class PostController {
		
	@Autowired
	private PostService postservice;
	
	@RequestMapping("posts")
	public String getUserPosts(Model model) {
		List<Post> posts = postservice.getAllPosts();
		model.addAttribute("posts", posts);
		return "posts";
	}

	@RequestMapping("/posts/newpost")
	public String newPost() {
		return "posts/create";
	}
	
	@RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public String createPost(Post newPost) {
		
		newPost.setDate(new Date());
		
		postservice.createPost(newPost);
        return "redirect:/posts";
    }
	
	
	
	@RequestMapping(value = "/editPost", method = RequestMethod.GET)
	public String editPost(@RequestParam(name="postId") Integer postId, Model model) {
	   Post post = postservice.getPost(postId);
	   model.addAttribute("post",post);
	   return "posts/edit";
	}
	
	@RequestMapping(value = "/editPost", method = RequestMethod.PUT)
	public String editPostSubmit(@RequestParam(name="postId") Integer postId, Post updatedPost) {
		updatedPost.setId(postId);
	   postservice.updatePost(updatedPost);
	   return "redirect:/posts";
	}
	
	@RequestMapping(value = "/deletePost", method = RequestMethod.DELETE)
	public String deletePostSubmit(@RequestParam(name="postId") Integer postId) {
		postservice.deletePost(postId);
		return "redirect:/posts";
	}
	
}
