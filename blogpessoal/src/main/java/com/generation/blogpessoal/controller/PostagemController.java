package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.BlogpessoalApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class PostagemController {

    @Autowired
    private BlogpessoalApplication.PostagemRepository postagemRepository;

    @GetMapping
    public <Postagem> ResponseEntity<List<Postagem>> getAll() {
        return ResponseEntity.ok(postagemRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> getById(@PathVariable Long id){
        return postagemRepository.FindBy(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

}
