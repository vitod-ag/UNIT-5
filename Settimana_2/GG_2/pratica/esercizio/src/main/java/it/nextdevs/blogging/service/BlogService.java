package it.nextdevs.blogging.service;

import it.nextdevs.blogging.exception.BlogNonTrovatoException;
import it.nextdevs.blogging.model.Blog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private List<Blog> blogs = new ArrayList<>();

    public Optional<Blog> getBlogById(int id) {
        return blogs.stream().filter(blog -> blog.getId()==id).findFirst();
    }

    public List<Blog> getAllBlog() {
        return blogs;
    }

    public String saveBlog(Blog blog) {
        blogs.add(blog);
        return "Blog creato!! Ecco l'id corrispondente: " + blog.getId();
    }

    public Blog updateBlog(int id, Blog blogUpdate) throws BlogNonTrovatoException {
        Optional<Blog> blogOptional = getBlogById(id);
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();  // prendo il posto corrispondente
            blog.setTitolo(blogUpdate.getTitolo());
            blog.setContenuto(blogUpdate.getContenuto());
            blog.setCategoria(blogUpdate.getCategoria());
            blog.setTempoDiLettura(blogUpdate.getTempoDiLettura());
            return blog;
        }else {
            throw new BlogNonTrovatoException("Blog non esiste");
        }
    }

    public String deleteBlog(int id) throws BlogNonTrovatoException {
        Optional<Blog> blogOptional = getBlogById(id);
        if (blogOptional.isPresent()) {
            blogs.remove(blogOptional.get());
            return "Blog eliminato";
        }else  {
            throw new BlogNonTrovatoException("Blog non trovato");
        }
    }
}
