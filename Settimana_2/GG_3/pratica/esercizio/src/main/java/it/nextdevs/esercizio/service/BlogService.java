package it.nextdevs.esercizio.service;


import com.cloudinary.Cloudinary;
import it.nextdevs.esercizio.DTO.BlogDTO;
import it.nextdevs.esercizio.exception.AutoreNonTrovatoException;
import it.nextdevs.esercizio.exception.BlogNonTrovatoException;
import it.nextdevs.esercizio.model.Autore;
import it.nextdevs.esercizio.model.Blog;
import it.nextdevs.esercizio.repository.AutoreRepository;
import it.nextdevs.esercizio.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private Cloudinary cloudinary;

    //********************************
    public Optional<Blog> getBlogById(int id) {
        return blogRepository.findById(id);
    }

    public String saveBlog(BlogDTO blogDTO) {
        Blog blog = new Blog();
        blog.setTitolo(blogDTO.getTitolo());
        blog.setCategoria(blogDTO.getCategoria());
        blog.setContenuto(blogDTO.getContenuto());
        blog.setTempoDiLettura(blogDTO.getTempoDiLettura());
        blog.setCover("https://picsum.photos/200/300");
        blogRepository.save(blog);

        Optional<Autore> autoreOptional = autoreRepository.findById(blogDTO.getAutoreId());
        if(autoreOptional.isPresent()){
            Autore autore = autoreOptional.get();
            blog.setAutore(autore);
            blogRepository.save(blog);
            return "Blog creato!! Ecco l'id corrispondente: " + blog.getId();
        } else {
            throw new AutoreNonTrovatoException("Autore con l'id " + blog.getId() + " non trovato");
        }
    }

    public Page<Blog> getAllBlog(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return blogRepository.findAll(pageable);
    }



    public Blog updateBlog(int id, BlogDTO blogDTO) throws BlogNonTrovatoException {
        Optional<Blog> blogOptional = getBlogById(id);
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();  // prendo il posto corrispondente
            blog.setTitolo(blogDTO.getTitolo());
            blog.setContenuto(blogDTO.getContenuto());
            blog.setCategoria(blogDTO.getCategoria());
            blog.setTempoDiLettura(blogDTO.getTempoDiLettura());
            return blogRepository.save(blog);
        }else {
            throw new BlogNonTrovatoException("Blog non esiste");
        }
    }

    public String deleteBlog(int id) throws BlogNonTrovatoException {
        Optional<Blog> blogOptional = getBlogById(id);
        if (blogOptional.isPresent()) {
            blogRepository.delete(blogOptional.get());
            return "Blog con l'id " + id + "eliminato";
        }else  {
            throw new BlogNonTrovatoException("Blog con l'id " + id + "non trovato");
        }
    }

    public String patchCoverBlog(int id, MultipartFile foto) throws IOException {
        Optional<Blog> blogOptional = getBlogById(id);

        if ( blogOptional.isPresent() ) {
            String url = (String) cloudinary.uploader().upload(foto.getBytes(), Collections.emptyMap()).get("url");
            Blog blog = blogOptional.get();
            blog.setCover(url);
            blogRepository.save(blog);

            return "Blog con id " + id + " aggiornata con successo con la cover inviata";
        }else{
            throw new BlogNonTrovatoException("Blog con id: " + id + " non trovato");
        }
    }
}
