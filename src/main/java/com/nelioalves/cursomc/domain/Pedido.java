package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instante;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "endereco_entrega_id")
	private Endereco enderecoDeEntrega;
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> items = new HashSet<>();

	public Pedido() {}

	public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the idInteger to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the instante
	 */
	public Date getInstante() {
		return instante;
	}

	/**
	 * @param instante the instante to set
	 */
	public void setInstante(Date instante) {
		this.instante = instante;
	}

	/**
	 * @return the pagamento
	 */
	public Pagamento getPagamento() {
		return pagamento;
	}

	/**
	 * @param pagamento the pagamento to set
	 */
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the enderecoDeEntrega
	 */
	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	/**
	 * @param enderecoDeEntrega the enderecoDeEntrega to set
	 */
	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	/**
	 * @return the items
	 */
	public Set<ItemPedido> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Set<ItemPedido> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
