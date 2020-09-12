package uy.com.arianmtzcu.controller;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uy.com.arianmtzcu.model.Vacante;
import uy.com.arianmtzcu.service.IVacanteService;

@Controller
public class HomeController {
	
	@Autowired
	private IVacanteService serviceVacante;
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		List<Vacante> lista = serviceVacante.buscarTodas();
		model.addAttribute("vacantes", lista);		
		return "home";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model model) {
		List<String> lista = new LinkedList<String>();
		lista.add("Ingeniero en Sstemas");
		lista.add("Auxiliar de Contabilidad");
		lista.add("Vendedor");
		lista.add("Arquitecto");
		
		model.addAttribute("empleos", lista);		
		return "listado";
	}
	
	@GetMapping("/detalle")
	public String mostrarDetalles(Model model) {
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de comunicaciones");
		vacante.setDescripcion("Se solicita ingeniero para dar soporte a Intranet");
		vacante.setFecha(new Date());
		vacante.setSalario(9700.0);
		vacante.setDestacado(0);
		
		model.addAttribute("vacante", vacante); 
		return "detalle";
	}
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		
		//model.addAttribute("vacantes", getVacantes());
		model.addAttribute("vacantes", serviceVacante.buscarTodas());
		return "tabla";
	}
	
	private List<Vacante> getVacantes() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		List<Vacante> lista = new LinkedList<Vacante>();
		
		/*try {
			// Creamos la oferta de Trabajo 1.
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil");
			vacante1.setDescripcion("Solicitamos Ing. Civil para diseñar puente peatonal.");
			vacante1.setFecha(sdf.parse("08-09-2020"));
			vacante1.setSalario(8500.0);
			vacante1.setDestacada(1);
			vacante1.setImagen("empresa1.png");
			
			// Creamos la oferta de Trabajo 2.
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Contador Publico");
			vacante2.setDescripcion("Empresa importante solicita contador con 5 años de experiencia titulado.");
			vacante2.setFecha(sdf.parse("09-09-2020"));
			vacante2.setSalario(12000.0);
			vacante2.setDestacada(0);
			vacante2.setImagen("empresa2.png");
			
			// Creamos la oferta de Trabajo 2.
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Ingeniero Eléctrico");
			vacante3.setDescripcion("Empresa internacional solicita Ingeniero mecánico para mantenimiento de la instalacion eléctrica.");
			vacante3.setFecha(sdf.parse("10-09-2020"));
			vacante3.setSalario(10500.0);
			vacante3.setDestacada(0);			
			
			// Creamos la oferta de Trabajo 4.
			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Diseñador Gráfico");
			vacante4.setDescripcion("Solicitamos Diseñador Gráfico titulado para diseñar publicidad de la empresa.");
			vacante4.setFecha(sdf.parse("11-09-2020"));
			vacante4.setSalario(7500.0);
			vacante4.setDestacada(1);
			vacante4.setImagen("empresa4.png");
			
			//Adicionando ojetos de tipo vacante!
			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);
			
		}catch (ParseException e) {
			System.out.println("Error: " + e.getMessage());
		}*/
		
		return lista;
	}
}
