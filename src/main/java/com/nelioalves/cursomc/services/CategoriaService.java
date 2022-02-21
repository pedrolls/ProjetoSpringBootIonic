package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;
import com.nelioalves.cursomc.services.util.ConstantesSistema;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository repository;
	
	public Categoria find(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encotrado! ID: ".concat(id.toString()).
				concat(", Tipo: ").concat(Categoria.class.getSimpleName())));
	}
	
	public Categoria insert(Categoria objCategoria) {
		objCategoria.setId(null);
		return repository.save(objCategoria);
	}
	
	public Categoria update(Categoria objCategoria) {
		find(objCategoria.getId());
		return repository.save(objCategoria);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityException e) {
			 throw new DataIntegrityException(ConstantesSistema.MSG_ERROR_DATA_INTEGRITY);
		} catch (DataIntegrityViolationException e) {
			 throw new DataIntegrityException(ConstantesSistema.MSG_ERROR_DATA_INTEGRITY);
		}
	}
	
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return  repository.findAll(pageRequest);
	}
	
	/**
	 * Conversao de CategoriaDTO para Categoria
	 * @param objtoCategoriaDTO
	 * @return
	 */
	public Categoria fromDTO(CategoriaDTO objtoCategoriaDTO) {
		return new Categoria(objtoCategoriaDTO.getId(), objtoCategoriaDTO.getNome());
	}
}