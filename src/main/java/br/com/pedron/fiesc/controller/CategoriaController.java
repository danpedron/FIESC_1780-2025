package br.com.pedron.fiesc.controller;

import br.com.pedron.fiesc.model.Categoria;
import br.com.pedron.fiesc.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.findById(id);
        return categoria.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Categoria createCategoria(@Valid @RequestBody Categoria categoria) {
        if (categoriaService.existsByNome(categoria.getNome())) {
            throw new RuntimeException("Já existe uma categoria com este nome");
        }
        return categoriaService.save(categoria);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoriaDetails) {
        try {
            Categoria updatedCategoria = categoriaService.update(id, categoriaDetails);
            return ResponseEntity.ok(updatedCategoria);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        try {
            categoriaService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
