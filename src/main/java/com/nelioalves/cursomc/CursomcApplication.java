package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	/**
	 * Metodo para popular a tabela de categorias. 
	 */
	@Override
	public void run(String... args) throws Exception {
		
		Categoria categoriaUm = new Categoria(null,"Informática");
		Categoria categoriaDois = new Categoria(null,"Escritório");
		
		Produto produtoUm = new Produto(null, "Computador", 2000.00);
		Produto produtoDois = new Produto(null, "Impressora", 800.00);
		Produto produtoTres = new Produto(null, "Mouse", 50.00);
		
		categoriaUm.getProdutos().addAll(Arrays.asList(produtoUm, produtoDois, produtoTres));
		categoriaDois.getProdutos().addAll(Arrays.asList(produtoDois));
		
		produtoUm.getCategorias().addAll(Arrays.asList(categoriaUm));
		produtoDois.getCategorias().addAll(Arrays.asList(categoriaUm,categoriaDois));
		produtoTres.getCategorias().addAll(Arrays.asList(categoriaUm));
		
		categoriaRepository.saveAll(Arrays.asList(categoriaUm, categoriaDois));
		produtoRepository.saveAll(Arrays.asList(produtoUm, produtoDois, produtoTres));
		
	}

}
