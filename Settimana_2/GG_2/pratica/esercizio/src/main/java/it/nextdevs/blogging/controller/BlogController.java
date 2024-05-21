package it.nextdevs.blogging.controller;

import it.nextdevs.blogging.exception.BlogNonTrovatoException;
import it.nextdevs.blogging.model.Blog;
import it.nextdevs.blogging.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/api/blogs")
    public String saveBlog(@RequestBody Blog blog) {
        return blogService.saveBlog(blog);
    }

    @GetMapping("/api/blogs")
    private List<Blog> getAllBlogs() {
        return blogService.getAllBlog();
    }

    @GetMapping("/api/blogs/{id}")
    public Blog getBlogById(@PathVariable int id) throws BlogNonTrovatoException {
        Optional<Blog> blogOptional = blogService.getBlogById(id);
        if (blogOptional.isPresent()){
            return blogOptional.get();
        }else {
            throw new BlogNonTrovatoException("Blog con l'id " + id + " non trovato");
        }
    }

    @PutMapping("/api/blogs/{id}")
    public Blog updateBlog(@PathVariable int id,@RequestBody Blog blog) throws BlogNonTrovatoException {
        return blogService.updateBlog(id, blog);
    }

    @DeleteMapping("/api/blogs/{id}")
    public String deleteBlog(@PathVariable int id) throws BlogNonTrovatoException {
        return blogService.deleteBlog(id);
    }


}
