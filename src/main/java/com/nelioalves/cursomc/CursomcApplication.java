package com.nelioalves.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.Estado;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
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
		
		//Instanciando Estado e Cidades
		
		Estado estadoUm = new Estado(null, "Minas Gerais");
		Estado estadoDois = new Estado(null, "Uberlândia");
		
		Cidade cidadeUm = new Cidade(null, "Uberlandia", estadoUm);
		Cidade cidadeDois = new Cidade(null, "São Paulo", estadoDois);
		Cidade cidadeTres = new Cidade(null, "Campinas", estadoDois);
		
		estadoUm.getCidades().addAll(Arrays.asList(cidadeUm));
		estadoDois.getCidades().addAll(Arrays.asList(cidadeDois, cidadeTres));
		
		estadoRepository.saveAll(Arrays.asList(estadoUm, estadoDois));
		cidadeRepository.saveAll(Arrays.asList(cidadeUm, cidadeDois, cidadeTres));
		
		// Instancias de Cliente, endereço e telefones com cidades.
		
		Cliente clienteUm = new Cliente(null, "Maria Silva", "maria@gmail.com", "3651898465", TipoCliente.PESSOAFISICA);
		clienteUm.getTelefones().addAll(Arrays.asList("2738468","9933545"));
		
		Endereco enderecoUm = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38458884", clienteUm, cidadeUm);
		Endereco enderecoDois = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "35844999", clienteUm, cidadeDois);
		
		clienteUm.getEnderecos().addAll(Arrays.asList(enderecoUm, enderecoDois));
		
		clienteRepository.saveAll(Arrays.asList(clienteUm));
		enderecoRepository.saveAll(Arrays.asList(enderecoUm, enderecoDois));
		
	}

}
