package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Book;
import com.javatechie.crud.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService service;


    @RequestMapping(value = "/prescribedbooks", method=RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Book addBook(@RequestBody Book book) {
        return service.saveBook(book);
    }

    @RequestMapping(value = "/prescribedbooks", method = RequestMethod.GET ,consumes = "multiplpart/form-data")
    //@GetMapping("/prescribedbooks")
    public Book findBookByModuleCode(@RequestParam int moduleCode) {
        if (service.getBookByModuleCode(moduleCode) == null)
            BookService.return404();
        return service.getBookByModuleCode(moduleCode);
    }

    @PutMapping("/prescribedbooks")
    public Book updateBook(@RequestParam int moduleCode, @RequestBody Book book) {
        return service.updateBook(moduleCode, book);
    }

    @Transactional
    @DeleteMapping("/prescribedbooks")
    public String deleteBook(@RequestParam int moduleCode) {
        return service.deleteBook(moduleCode);
    }
}
