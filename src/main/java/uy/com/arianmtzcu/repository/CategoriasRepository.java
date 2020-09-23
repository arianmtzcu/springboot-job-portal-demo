package uy.com.arianmtzcu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import uy.com.arianmtzcu.model.Categoria;

//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	
	/**
	 * JpaRepository extiende de PagingAndSortingRepository,
	 * por lo que cuenta con métodos para paginacion y ordenamiento..,
	 */
}
