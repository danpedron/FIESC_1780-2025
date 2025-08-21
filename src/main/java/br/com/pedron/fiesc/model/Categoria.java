package br.com.pedron.fiesc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false, unique = true)
    private String nome;
    
    private String descricao;
    
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos = new ArrayList<>();
    
    // Construtores
    public Categoria() {}
    
    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }
    
    public void addProduto(Produto produto) {
        produtos.add(produto);
        produto.setCategoria(this);
    }
    
    public void removeProduto(Produto produto) {
        produtos.remove(produto);
        produto.setCategoria(null);
    }
}
