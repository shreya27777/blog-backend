package com.caseStudy.blog.service;

import com.caseStudy.blog.model.Comments;
import com.caseStudy.blog.model.Post;
import com.caseStudy.blog.model.Users;
import com.caseStudy.blog.repository.CommentsRepository;
import com.caseStudy.blog.repository.PostRepository;
import com.caseStudy.blog.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;
    private UsersRepository usersRepository;
    private CommentsRepository commentsRepository;

    @Autowired
    public PostService(PostRepository postRepository, CommentsRepository commentsRepository,
                       UsersRepository usersRepository) {
        this.postRepository = postRepository;
        this.commentsRepository = commentsRepository;
        this.usersRepository = usersRepository;
    }

    public List<Post> getAllPost() {
        return postRepository.findAllByIsPrivate(0);
    }

    public List<Post> getAllPostofUser(Long id) {
        Users users = usersRepository.findByUserId(id).get();
        return postRepository.findAllByAuthor(users);
    }

    public List<Post> getPostByCategory(String category) {
        return postRepository.findAllByCategoryAndIsPrivate(category, 0);
    }


    public List<Post> getMyPost(Principal principal) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        return postRepository.findAllByAuthor(users);
    }

    public List<Post> getPostByTitle(String title) {
        return postRepository.findAllByTitleContainingIgnoreCaseAndIsPrivate(title, 0);
    }

    public List<Post> getPostByDescription(String description) {
        return postRepository.findAllByDescriptionContainingIgnoreCaseAndIsPrivate(description, 0);
    }

    public List<Post> getPostByDate(int year, int month, int day) {
        LocalDate l = LocalDate.of(year, month, day);
        return postRepository.findAllByDateAndIsPrivate(l, 0);
    }


    public Post getPostByUser(Long id) {
        return postRepository.findById(id).get();
    }

    public Post addPost(Post post, Principal principal) {
        Users user = usersRepository.findByEmail(principal.getName()).get();
        post.setVisited(0L);
        post.setDate(LocalDate.now());
        post.setLikes(0L);
        post.setDisLikes(0L);
        post.setAuthor(user);
        return postRepository.saveAndFlush(post);
    }

    public List<Post> deletePost(Long id) {
        Post post = postRepository.findById(id).get();
        for (Comments comments : commentsRepository.findAllByPostOrderByDateDesc(post)) {
            commentsRepository.delete(comments);
        }
        postRepository.delete(post);
        return postRepository.findAllByIsPrivate(0);
    }

    public void viewPost(Long id) {
        Post post = postRepository.findById(id).get();
        post.setVisited(post.getVisited() + 1);
        postRepository.saveAndFlush(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        Post post = postRepository.findById(id).get();
        post.setCategory(updatedPost.getCategory());
        post.setContent(updatedPost.getContent());
        post.setImage(updatedPost.getImage());
        post.setTitle(updatedPost.getTitle());
        post.setIsPrivate(updatedPost.getIsPrivate());
        post.setDescription(updatedPost.getDescription());
        postRepository.saveAndFlush(post);
        return postRepository.findById(id).get();
    }

    public List<Post> popular() {
        return postRepository.findTop5ByIsPrivateOrderByVisitedDesc(0);
    }

    public List<Post> findAllByAuthorName(String name) {
        List<Users> usersList = usersRepository.findAllByNameContainingIgnoreCase(name);
        List<Post> postsList = new ArrayList<>();
        for (Users users : usersList) {
            postsList.addAll(postRepository.findAllByAuthorAndIsPrivateOrderByDateDesc(users, 0));
        }
        return postsList;
    }

//    public List<Post> findAllPosts(Principal principal) {
//        List<Post> postList
//    }

}
