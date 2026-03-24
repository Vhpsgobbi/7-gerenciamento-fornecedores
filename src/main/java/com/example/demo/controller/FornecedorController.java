package com.example.demo.controller;

import com.example.demo.entity.Fornecedor;
import com.example.demo.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public ResponseEntity<List<Fornecedor>> findAll() {
        List<Fornecedor> lista = fornecedorService.findAll();
        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public ResponseEntity<Fornecedor> criar(@RequestBody Fornecedor fornecedor) {
        Fornecedor resultado = fornecedorService.criar(fornecedor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(fornecedor.getId())
                .toUri();
        return ResponseEntity.created(uri).body(resultado);
    }

    @GetMapping("/{id}")
    public Optional<Fornecedor> buscarPorId(@PathVariable Long id) {
        return fornecedorService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        fornecedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
