package uy.com.arianmtzcu.service;

import java.util.List;
import uy.com.arianmtzcu.model.Categoria;

public interface ICategoriasService {
	void guardar(Categoria categoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);	
}

/**
 * TODO: P1. Crear la clase CategoríaServiceImpl que implemente esta Interfaz (Guardar categorías en una lista (LinkedList))
 * 
 * TODO: P2. Inyectar la clase de servicio en CategoríaController.
 * 
 * TODO: P3. Completar los siguientes métodos en CategoríaController:
 * 
 * mostrarIndex -> Renderizar el listado de Categorías (listCategorías.html)
 * 				   Configurar la URL del botón para crear una Categoría
 * 
 * guardar -> Guardar el objeto Categoría a través de la clase de servicio
 * 			  Validar errores de Data Binding y mostrarlos al usuario en caso de haber
 * 			  Mostrar al usuario mensaje de confirmación de registro guardado
 * 
 * TODO: P4. Agregar un nuevo menú llamado Categoría y configurar la URL al listado de Categorías
 */