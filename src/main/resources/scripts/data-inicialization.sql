INSERT INTO tb_tipo_imovel (
nome) values
('casa'),
('apartamento'),
('sobrado'),
('terreno'),
('chacara'),
('fazenda'),
('rancho'),
('kitnet'),
('flat'),
('bangalo'),
('duplex'),
('triplex'),
('galpão'),
('sala'),
('studio');

INSERT INTO public.tb_dados_complementares
(met_quadrados, nome_empreendimento, quant_banheiros, quant_quartos, quant_suites, quant_vagas, valor)
values
(250.5, 'Cond. Green Ville', 3, 3, 2, 4, 500000.00),
(160.5, 'Edificio Asturias', 3, 3, 1, 2, 250000.00),
(350.5, 'Cond. de Sobrados Recanto dos Passaros', 3, 3, 2, 4, 750000.00),
(400, 'Loteamento Verde Ville', 0, 0, 0, 0, 200000.00),
(1250.5, 'Cond. de Chácaras Omega', 0, 0, 0, 0, 400000.00),
(5000.5, 'Fazenda Touro Dourado', 0, 0, 0, 0, 1500000.00);


INSERT INTO public.tb_endereco
(bairro, cep, localidade, complemento, logradouro, numero, uf, ddd, gia, ibge,siafi)
values
('Bairro Florianopolis', '45500500', 'Florianopolis', 'casa verde','rua florianopolis', '42','SC','48','42','42','42'),
('Bairro Curitiba', '41500500', 'Curitiba', 'bloco A', 'rua curitiba', '41', 'PR','41','41','41','41'),
('Bairro Goiania', '74500500', 'Goiania','sobrado azul', 'rua goiania', '71',  'GO','62','123','123','123'),
('Bairro Belo Horizonte', '31500500', 'Belo Horizonte', 'saneamento concluído','rua relo horizonte', '31',  'MG','31','31','31','31'),
('Zona Rural', '31500500', 'São Paulo','chacara residencial', 'rua sao paulo', 'sn',  'SP','11','11','11','11'),
('Zona rural', '31500500','fazenda com gado de corte', 'Palmas', 'rua palmas', 'sn',  'TO','67','67','67','67');


INSERT INTO tb_tipo_empreendimento (
nome)
values
('cond.horizontal'),
('cond.vertical'),
('loteamento'),
('cond. de chacaras'),
('fazenda'),
('conj.habitacional'),
('shopping'),
('galeria');




INSERT INTO tb_grupo_caracteristica (
nome) VALUES
('areas comuns'),
('apto/casa'),
('lazer'),
('seguran�a' ),
('itens proximos' );


INSERT INTO tb_caracteristica (
nome, grupo_caracteristica_id) VALUES
('lavanderia', 1),('quintal', 1),('escaninho', 1),('muros', 1),('estacionamento visitantes', 1),('garagem coberta', 1),
('hall de entrada', 1),('portao eletronico', 1),
('armario embutido ', 2),('cozinha americana ', 2),('gas encanado', 2),('aquecimento solar', 2),('lavabo', 2),('ar condicionado', 2),('churrasqueira na varanda', 2),('closet', 2),('escritorio', 2),('varanda gourmet', 2),
('academia', 3),('brinquedoteca', 3),('piscina', 3),('salao de festas', 3),('sauna', 3),('salao de jogos', 3),('home office', 3),('quadra poliesportiva', 3),('spa', 3),('espaao mulher', 3),
('cameras de segurança', 4),('portaria 24 horas', 4),('pulmao de segurança', 4),
('bares e restaurantes',5),('escola',5),('farmacia',5),('shopping center',5),('supermercados',5),('area verde',5);


INSERT INTO public.tb_usuario
(ativo, data_atualizacao, data_cadastro, email, nome, perfil, senha, telefone)
values
(true, '2021-06-01 00:36:25', '2021-06-01 00:36:25', 'aires@gmail.com', 'Aires ADS', 'VENDEDOR', '123456', '62984012478'),
(true, '2021-06-01 00:36:25', '2021-06-01 00:36:25', 'gabriel@gmail.com', 'Gabriel ADS', 'ADMINISTRADOR', '123456', '62987654321'),
(false, '2021-06-01 00:36:25', '2021-06-01 00:36:25', 'lucas@gmail.com', 'Lucas ADS', 'VENDEDOR', '123456', '62987654321'),
(false, '2021-06-01 00:36:25', '2021-06-01 00:36:25', 'rogerio@gmail.com', 'Rogerio ADS', 'ADMINISTRADOR', '123456', '62987654321');

INSERT INTO public.tb_anuncio
(ativo,
categoria,
descricao,
data_cadastro,
quant_visualizacoes,
dados_complementares_id,
endereco_id,
tipo_empreendimento_id,
tipo_imovel_id,
usuario_id)
values
(false,'residencial',
'Casa confortável',
'2021-06-09', 0, 1, 1, 1, 1, 1),
(false,'residencial',
'Apartamento confortável',
'2021-06-09', 0, 2, 2, 2, 2, 2),
(false,'residencial',
'Sobrado confotável',
'2021-06-09', 0, 3, 3, 3, 3, 3),
(false,'residencial',
'Terreno em ótima localização',
'2021-06-09', 0, 4, 4, 4, 4, 4),
(false,'residencial',
'Chácara com piscina',
'2021-06-09', 0, 5, 5, 5, 5, 1),
(false,'residencial',
'Fazenda com pista para hipismo',
'2021-06-09', 0, 6, 6, 6, 6, 2);


INSERT INTO public.tb_imagem
(imagem, anuncio_id)
values
('https://images.unsplash.com/photo-1609859419229-88889379a595?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nzd8fGNhc2F8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 1),
('https://images.unsplash.com/photo-1609859419229-88889379a595?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nzd8fGNhc2F8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 1),
('https://images.unsplash.com/photo-1609859419229-88889379a595?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nzd8fGNhc2F8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 1),

('https://images.unsplash.com/photo-1588848571070-0741d5a997ff?(ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8YXBhcnRhbWVudHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 2),
('https://images.unsplash.com/photo-1588848571070-0741d5a997ff?(ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8YXBhcnRhbWVudHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 2),
('https://images.unsplash.com/photo-1588848571070-0741d5a997ff?(ixid=MnwxMjA3fDB8MHxzZWFyY2h8OXx8YXBhcnRhbWVudHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 2),

('https://images.unsplash.com/photo-1505873242700-f289a29e1e0f?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Njh8fGhvbWV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 3),
('https://images.unsplash.com/photo-1505873242700-f289a29e1e0f?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Njh8fGhvbWV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 3),
('https://images.unsplash.com/photo-1505873242700-f289a29e1e0f?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Njh8fGhvbWV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 3),

('https://images.unsplash.com/photo-1598278157109-e20bc056fe2c?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fGdyb3VuZHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 4),
('https://images.unsplash.com/photo-1598278157109-e20bc056fe2c?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fGdyb3VuZHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 4),
('https://images.unsplash.com/photo-1598278157109-e20bc056fe2c?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fGdyb3VuZHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 4),

('https://images.unsplash.com/photo-1523745663588-1bfa973c9b35?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fGZhemVuZGF8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 5),
('https://images.unsplash.com/photo-1523745663588-1bfa973c9b35?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fGZhemVuZGF8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 5),
('https://images.unsplash.com/photo-1523745663588-1bfa973c9b35?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fGZhemVuZGF8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 5),

('https://images.unsplash.com/photo-1449844908441-8829872d2607?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGhvbWV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 6),
('https://images.unsplash.com/photo-1449844908441-8829872d2607?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGhvbWV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 6),
('https://images.unsplash.com/photo-1449844908441-8829872d2607?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTB8fGhvbWV8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60', 6);
