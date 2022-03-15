package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.dto.ClienteInputDTO;
import com.nelioalves.cursomc.repositories.CidadeRepository;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import com.nelioalves.cursomc.services.util.ConstantesSistema;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encotrado! ID: ".concat(id.toString()).
				concat(", Tipo: ").concat(Cliente.class.getSimpleName())));
	}
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = repository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}
	
	public Cliente update(Cliente objCliente) {
		Cliente cliente = find(objCliente.getId());
		this.updateDadosDoCLiente(cliente, objCliente);
		return repository.save(cliente);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityException e) {
			 throw new DataIntegrityException(ConstantesSistema.MSG_ERROR_DATA_INTEGRITY_CLIENTE);
		} catch (DataIntegrityViolationException e) {
			 throw new DataIntegrityException(ConstantesSistema.MSG_ERROR_DATA_INTEGRITY_CLIENTE);
		}
	}
	
	public List<Cliente> findAll(){
		return repository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return  repository.findAll(pageRequest);
	}
	
	/**
	 * Conversao de ClienteDTO para Cliente
	 * @param objtoClienteDTO
	 * @return
	 */
	public Cliente fromDTO(ClienteDTO objtoClienteDTO) {
		return new Cliente(null, objtoClienteDTO.getNome(), objtoClienteDTO.getEmail(), null, null);
	}
	
	/**
	 * Conversao de ClienteDTO para Cliente
	 * @param clienteInputDTO
	 * @return
	 */
	public Cliente fromDTO(ClienteInputDTO clienteInputDTO) {
		Cliente cliente = new Cliente(null, clienteInputDTO.getNome(), clienteInputDTO.getEmail(), clienteInputDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteInputDTO.getTipo()));
		Cidade cidade = cidadeRepository.findById(clienteInputDTO.getCidadeId()).orElseThrow(() -> new ObjectNotFoundException("Cidade não encontrada"));
		Endereco endereco = new Endereco(null, clienteInputDTO.getLogradouro(), clienteInputDTO.getNumero(), clienteInputDTO.getComplemento(), 
				clienteInputDTO.getBairro(), clienteInputDTO.getCep(), cliente, cidade);
		
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteInputDTO.getTelefonePrincipal());
		
		if(clienteInputDTO.getTelefoneOpcinalUm() != null)
			cliente.getTelefones().add(clienteInputDTO.getTelefoneOpcinalUm());
		
		if(clienteInputDTO.getTelefoneOpcinalDois() != null)
			cliente.getTelefones().add(clienteInputDTO.getTelefoneOpcinalDois());
		
		return cliente;
	}
	
	private void updateDadosDoCLiente(Cliente cliente, Cliente objCliente) {
		cliente.setNome(objCliente.getNome());
		cliente.setEmail(objCliente.getEmail());
	}
}
