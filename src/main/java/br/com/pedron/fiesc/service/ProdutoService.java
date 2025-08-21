package br.com.pedron.fiesc.service;

import br.com.pedron.fiesc.model.Categoria;
import br.com.pedron.fiesc.model.Produto;
import br.com.pedron.fiesc.repository.CategoriaRepository;
import br.com.pedron.fiesc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }
    
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }
    
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }
    
    public Produto update(Long id, Produto produtoDetails) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
        
        produto.setNome(produtoDetails.getNome());
        produto.setDescricao(produtoDetails.getDescricao());
        produto.setPreco(produtoDetails.getPreco());
        produto.setCategoria(produtoDetails.getCategoria());
        
        return produtoRepository.save(produto);
    }
    
    public void deleteById(Long id) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
        
        produtoRepository.delete(produto);
    }
    
    public List<Produto> findByCategoriaId(Long categoriaId) {
        return produtoRepository.findByCategoriaId(categoriaId);
    }
}
