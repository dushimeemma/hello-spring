package com.rra.firstapp.hello_spring;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class HelloController {

    private final PhotoService photoService;

    public HelloController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/hellos")
    public Hello createHello(@RequestPart("data") MultipartFile file) throws IOException {
      return   photoService.save(file.getOriginalFilename(),  file.getBytes());
    }

    @GetMapping("/")
    public String sayHello(){
        return  "Hello Spring Boot, I am coming for you!";
    }

    @GetMapping("/hellos")
    public Collection<Hello> hellos(){
        return photoService.get();
    }

    @GetMapping("/hellos/{id}")
    public Hello hello(@PathVariable String id){
        Hello hello = photoService.get(id);
        if(hello == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return hello;
    }

    @DeleteMapping("/hellos/{id}")
    public  void deleteHello(@PathVariable String id){
        Hello hello = photoService.remove(id);
        if(hello == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
