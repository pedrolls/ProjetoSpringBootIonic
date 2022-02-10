package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
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
}