package it.nextdevs.esercizio.controller;

import it.nextdevs.esercizio.DTO.BlogDTO;
import it.nextdevs.esercizio.exception.BadRequestException;
import it.nextdevs.esercizio.exception.BlogNonTrovatoException;
import it.nextdevs.esercizio.model.Blog;
import it.nextdevs.esercizio.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/api/blogs")
    public String saveBlog(@RequestBody @Validated BlogDTO blogDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors()
                    .stream().map(objectError -> objectError.getDefaultMessage())
                    .reduce("",((s,s2)-> s+s2)));
        }
        return blogService.saveBlog(blogDTO);
    }

    @GetMapping("/api/blogs")
    private Page<Blog> getAllBlogs(@RequestParam (defaultValue = "0")int page,
                                   @RequestParam(defaultValue = "15")int size,
                                   @RequestParam(defaultValue = "id") String sortBy) {
        return blogService.getAllBlog(page, size, sortBy);
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
    public Blog updateBlog(@PathVariable int id,@RequestBody @Validated BlogDTO blogDTO, BindingResult bindingResult) throws BlogNonTrovatoException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors()
                    .stream().map(objectError -> objectError.getDefaultMessage())
                    .reduce("",((s,s2)-> s+s2)));
        }
        return blogService.updateBlog(id, blogDTO);
    }

    @DeleteMapping("/api/blogs/{id}")
    public String deleteBlog(@PathVariable int id) throws BlogNonTrovatoException {
        return blogService.deleteBlog(id);
    }

    @PatchMapping("/api/blogs/{id}")
    public String patchCoverBlog(@RequestBody MultipartFile foto, @PathVariable int id) throws IOException {
        return blogService.patchCoverBlog(id, foto);
    }
}
