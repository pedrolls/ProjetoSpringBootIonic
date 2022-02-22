package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import com.nelioalves.cursomc.services.util.ConstantesSistema;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encotrado! ID: ".concat(id.toString()).
				concat(", Tipo: ").concat(Cliente.class.getSimpleName())));
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
	
	private void updateDadosDoCLiente(Cliente cliente, Cliente objCliente) {
		cliente.setNome(objCliente.getNome());
		cliente.setEmail(objCliente.getEmail());
	}
}
