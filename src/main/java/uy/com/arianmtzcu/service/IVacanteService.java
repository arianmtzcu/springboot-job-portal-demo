package uy.com.arianmtzcu.service;

import java.util.List;

import uy.com.arianmtzcu.model.Vacante;

public interface IVacanteService {
	
	List<Vacante> buscarTodas();
	Vacante buscarVacantePorId(Integer idVacante);
	void guardar(Vacante vacante);
}
