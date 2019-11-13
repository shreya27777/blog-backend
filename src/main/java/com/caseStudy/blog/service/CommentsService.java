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
import java.util.List;

@Service
public class CommentsService {
    private CommentsRepository commentsRepository;
    private PostRepository postRepository;
    private UsersRepository usersRepository;

    @Autowired
    public CommentsService(CommentsRepository commentsRepository, PostRepository postRepository,
                           UsersRepository usersRepository) {
        this.commentsRepository = commentsRepository;
        this.postRepository = postRepository;
        this.usersRepository = usersRepository;
    }

    public List<Comments> getComments(Long id) {
        Post post = postRepository.findById(id).get();
        return commentsRepository.findAllByPostOrderByDateDesc(post);
    }

    public List<Comments> addComments(Comments comments, Principal principal, Long id) {
        Users users = usersRepository.findByEmail(principal.getName()).get();
        Post post = postRepository.findById(id).get();
        comments.setPost(post);
        comments.setUsers(users);
        comments.setDate(LocalDate.now());
        comments.setLikes(0L);
        commentsRepository.saveAndFlush(comments);
        return commentsRepository.findAllByPostOrderByDateDesc(post);
    }

    public List<Comments> deleteComments(Long id) {
        Comments comments=commentsRepository.findById(id).get();
        commentsRepository.delete(comments);
        return commentsRepository.findAll();
    }

    public List<Comments> likeComments(Long id){
        Comments comments=commentsRepository.findById(id).get();
        comments.setLikes(comments.getLikes()+1);
        return commentsRepository.findAll();
    }

    public List<Comments> dislikeComments(Long id){
        Comments comments=commentsRepository.findById(id).get();
        comments.setLikes(comments.getLikes()-1);
        return commentsRepository.findAll();
    }
}
