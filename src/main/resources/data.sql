INSERT INTO categorias (nome, descricao) VALUES 
('Eletrônicos', 'Dispositivos eletrônicos em geral'),
('Livros', 'Livros de diversos gêneros'),
('Roupas', 'Vestuário e acessórios');

INSERT INTO produtos (nome, descricao, preco, data_cadastro, categoria_id) VALUES 
('iPhone 21', 'Smartphone de última geração', 9799.99, NOW(), 1),
('Tablet iPad', 'Tablet com tela de 42 polegadas', 1899.99, NOW(), 1),
('Java para Iniciantes', 'Livro de programação Java', 59.90, NOW(), 2),
('Camiseta Básica', 'Camiseta 100% algodão', 29.90, NOW(), 3);
