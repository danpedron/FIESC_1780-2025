package br.com.pedron.fiesc.config;

import br.com.pedron.fiesc.model.Categoria;
import br.com.pedron.fiesc.model.Produto;
import br.com.pedron.fiesc.repository.CategoriaRepository;
import br.com.pedron.fiesc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoriaRepository.count() == 0) {
            Categoria eletronicos = new Categoria("Eletrônicos", "Dispositivos eletrônicos em geral");
            Categoria livros = new Categoria("Livros", "Livros de diversos gêneros");
            Categoria roupas = new Categoria("Roupas", "Vestuário e acessórios");

            categoriaRepository.save(eletronicos);
            categoriaRepository.save(livros);
            categoriaRepository.save(roupas);

            if (produtoRepository.count() == 0) {
                Produto smartphone = new Produto("Smartphone iPhone 21", "Smartphone de última geração", 9799.99, eletronicos);
                Produto tablet = new Produto("Tablet ABC", "Tablet com tela de 10 polegadas", 899.99, eletronicos);
                Produto livroJava = new Produto("Java para Iniciantes", "Livro de programação Java", 59.90, livros);
                Produto camiseta = new Produto("Camiseta Básica", "Camiseta 100% algodão", 29.90, roupas);

                produtoRepository.save(smartphone);
                produtoRepository.save(tablet);
                produtoRepository.save(livroJava);
                produtoRepository.save(camiseta);
            }
        }
    }
}
