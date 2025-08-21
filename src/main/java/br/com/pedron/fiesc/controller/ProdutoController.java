package br.com.pedron.fiesc.controller;

import br.com.pedron.fiesc.model.Produto;
import br.com.pedron.fiesc.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    
    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.findById(id);
        return produto.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Produto createProduto(@Valid @RequestBody Produto produto) {
        return produtoService.save(produto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @Valid @RequestBody Produto produtoDetails) {
        try {
            Produto updatedProduto = produtoService.update(id, produtoDetails);
            return ResponseEntity.ok(updatedProduto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
        try {
            produtoService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/categoria/{categoriaId}")
    public List<Produto> getProdutosByCategoria(@PathVariable Long categoriaId) {
        return produtoService.findByCategoriaId(categoriaId);
    }
}
