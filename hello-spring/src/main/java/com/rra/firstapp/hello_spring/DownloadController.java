package com.rra.firstapp.hello_spring;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {

    private final PhotoService photoService;

    public DownloadController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id){

         Hello hello = photoService.get(id);

         if(hello == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

         byte[] data = new hello.getData();

         HttpHeaders headers = new HttpHeaders();
         return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
}
