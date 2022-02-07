package com.nelioalves.cursomc.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository repository;
	
	public Categoria buscarCategoriaPorId(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encotrado! ID: ".concat(id.toString()).
				concat(", Tipo: ").concat(Categoria.class.getSimpleName())));
	}
	
	public Categoria insert(Categoria objCategoria) {
		objCategoria.setId(null);
		return repository.save(objCategoria);
	}

}
