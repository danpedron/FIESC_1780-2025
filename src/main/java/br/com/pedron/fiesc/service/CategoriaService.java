package br.com.pedron.fiesc.service;

import br.com.pedron.fiesc.model.Categoria;
import br.com.pedron.fiesc.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
    
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }
    
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
    
    public Categoria update(Long id, Categoria categoriaDetails) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + id));
        
        categoria.setNome(categoriaDetails.getNome());
        categoria.setDescricao(categoriaDetails.getDescricao());
        
        return categoriaRepository.save(categoria);
    }
    
    public void deleteById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + id));
        
        categoriaRepository.delete(categoria);
    }
    
    public boolean existsByNome(String nome) {
        return categoriaRepository.existsByNome(nome);
    }
}
