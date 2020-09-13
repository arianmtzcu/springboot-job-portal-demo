package uy.com.arianmtzcu.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uy.com.arianmtzcu.model.Vacante;
import uy.com.arianmtzcu.service.ICategoriasService;
import uy.com.arianmtzcu.service.IVacanteService;
import uy.com.arianmtzcu.util.Utileria;

@Controller
@RequestMapping(value = "/vacantes")
public class VacantesController {

	@Autowired
	private IVacanteService serviceVacante;

	@Autowired
	private ICategoriasService serviceCategoria;

	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		// TODO 1. Obtener todas las vacantes (recuperarlas con la clase de servicio)
		List<Vacante> lista = serviceVacante.buscarTodas();
		// TODO 2. Agregar al modelo el lstado de vacantes
		model.addAttribute("vacantes", lista);

		// TODO 3. Renderizar las vacantes en la vista (integrar el archivo
		// template-empleos/listaVacantes.html)
		// TODO 4. Agregar al menú una opción llamda "Vacantes" configurando la URL
		// "/vacantes/index"
		return "/vacantes/listVacantes";
	}

	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model model) {
		System.out.println("IdVacante: " + idVacante);
		model.addAttribute("idVacante", idVacante);

		// Trabajando con listado de vacante estatico...
		Vacante vacante = serviceVacante.buscarVacantePorId(idVacante);
		System.out.println(vacante);
		model.addAttribute("vacante", vacante);

		// Buscar los detalles de la vacante en la BD...

		return "detalle";
	}

	@GetMapping("/delete")
	private String eliminar(@RequestParam("id") int idVacante, Model model) {
		System.out.println("Borrando vacante con d: " + idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}

	// @RequestMapping("/create")
	@GetMapping("/create")
	public String crear(Vacante vacante, Model model) {
		// Método que se encarga de renderizar la vista para crear vacantes nuevas, por
		// consiguiente se debe enviar en el modelo el listado de categorias...
		model.addAttribute("categorias", serviceCategoria.buscarTodas());
		return "vacantes/formVacante";
	}

	/*
	 * @RequestMapping("/save") public String guardar(@RequestParam("nombre") String
	 * nombre, @RequestParam("descripcion") String descripcion,
	 * 
	 * @RequestParam("categoria") String categoria, @RequestParam("estatus") String
	 * estatus, @RequestParam("fecha") String fecha,
	 * 
	 * @RequestParam("destacado") int destacado, @RequestParam("salario") double
	 * salario,
	 * 
	 * @RequestParam("detalles") String detalles) {
	 * System.out.println("Nombre Vacante: " + nombre);
	 * System.out.println("Categoria: " + categoria);
	 * System.out.println("Descrpcion: " + descripcion);
	 * System.out.println("Estatus: " + estatus);
	 * System.out.println("Fecha de Publicacion: " + fecha);
	 * System.out.println("Destacado: " + destacado);
	 * System.out.println("Salario Ofrecido: " + salario);
	 * System.out.println("Detalles: " + detalles); return "vacantes/listVacantes";
	 * }
	 */

	@RequestMapping("/save")
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes,
			@RequestParam("archivoImagenSubirServer") MultipartFile multiPart) {
		
		// BindingResult result ---> Para hacer el tratamiento de errores durante el
		// Data Binding...
		if (result.hasErrors()) {
			// Mostramos en consola la descripcion de los errores!
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			return "vacantes/formVacante";
		}

		// Subida de archivo imagen al server!
		if (!multiPart.isEmpty()) {
			// String ruta = "/empleos/img-vacantes/"; // Linux/MAC
			String ruta = "C:/Users/Administrador/Documents/repo-github/empleos/img-vacantes/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null) { // La imagen si se subio
				// Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
		}

		// Guardando vacante en el listado
		serviceVacante.guardar(vacante);
		// Agregando un atributo al modelo usando Flash Attribute
		// Flash Attribute ---> Guarda el valor en la sesion hasta que se hace el
		// redirect, por eso cuando se recarga la pagina se pierden los valores
		// guardados...
		attributes.addFlashAttribute("msg", "Registro guardado");

		System.out.println("Vacante: " + vacante);
		// return "vacantes/listVacantes"; //Redirecciona a la vista, pero no carga las
		// vacantes
		return "redirect:/vacantes/index";
	}

	/*
	 * Configuración de Data Binding (obtención de datos y seteo a objetos de tipo
	 * Vacante en este caso!) Metodo que configura el tema del formato de fecha
	 * String al intentar convertir el atributo a Date
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

}
