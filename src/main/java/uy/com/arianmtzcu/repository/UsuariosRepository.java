package uy.com.arianmtzcu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uy.com.arianmtzcu.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
