package com.nelioalves.cursomc;

import java.text.SimpleDateFormat;
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
import com.nelioalves.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.domain.Pagamento;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.PagamentoComCartao;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.Produto;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.repositories.EstadoRepository;
import com.nelioalves.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		Categoria categoriaTres = new Categoria(null, "Cama mesa e banho");
		Categoria categoriaQuarto = new Categoria(null, "ELetrônicos");
		Categoria categoriaCinco = new Categoria(null, "Jardinagem");
		Categoria categoriaSeis = new Categoria(null, "Decoração");
		Categoria categoriaSete = new Categoria(null, "Perfumaria");
		
		Produto produtoUm = new Produto(null, "Computador", 2000.00);
		Produto produtoDois = new Produto(null, "Impressora", 800.00);
		Produto produtoTres = new Produto(null, "Mouse", 50.00);
		
		categoriaUm.getProdutos().addAll(Arrays.asList(produtoUm, produtoDois, produtoTres));
		categoriaDois.getProdutos().addAll(Arrays.asList(produtoDois));
		
		produtoUm.getCategorias().addAll(Arrays.asList(categoriaUm));
		produtoDois.getCategorias().addAll(Arrays.asList(categoriaUm,categoriaDois));
		produtoTres.getCategorias().addAll(Arrays.asList(categoriaUm));		
		
		categoriaRepository.saveAll(Arrays.asList(categoriaUm, categoriaDois, categoriaTres, categoriaQuarto, categoriaCinco, categoriaSeis, categoriaSete));
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
		
		// Instâncias de Peididos
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido pedidoUm = new Pedido(null, sdf.parse("30/09/2017 10:32"), clienteUm, enderecoUm);
		Pedido pedidoDois = new Pedido(null, sdf.parse("10/10/2017 19:35"), clienteUm, enderecoDois);
		
		Pagamento pagamentoUm = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedidoUm, 6);
		
		pedidoUm.setPagamento(pagamentoUm);
		
		Pagamento pagamentoDois = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedidoDois, sdf.parse("20/10/2017 00:00"), null);
		pedidoDois.setPagamento(pagamentoDois);
		
		clienteUm.getPedidos().addAll(Arrays.asList(pedidoUm, pedidoDois));
		
		pedidoRepository.saveAll(Arrays.asList(pedidoUm, pedidoDois));
		pagamentoRepository.saveAllAndFlush(Arrays.asList(pagamentoUm, pagamentoDois));
		
		// Instacias de Pedidos
		
		ItemPedido itemUm = new ItemPedido(pedidoUm, produtoUm, 0.00, 1, 2000.00);
		ItemPedido itemDois = new ItemPedido(pedidoUm, produtoTres, 0.00, 2, 80.00);
		ItemPedido itemTres = new ItemPedido(pedidoDois, produtoDois, 100.00, 1, 800.00);
		
		pedidoUm.getItems().addAll(Arrays.asList(itemUm, itemDois));
		pedidoDois.getItems().addAll(Arrays.asList(itemTres));
		
		produtoUm.getItems().addAll(Arrays.asList(itemUm));
		produtoDois.getItems().addAll(Arrays.asList(itemTres));
		produtoTres.getItems().addAll(Arrays.asList(itemDois));
		
		itemPedidoRepository.saveAll(Arrays.asList(itemUm, itemDois, itemTres));
		
	}

}
