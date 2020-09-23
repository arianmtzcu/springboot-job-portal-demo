/**
 * Esta clase representa una entidad (un registro) en la tabla de Usuarios de la base de datos
 */
package uy.com.arianmtzcu.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private Integer id;
	private String username;
	private String nombre;
	private String email;
	private String password;
	private Integer estatus;	
	private Date fechaRegistro;	
	
	/** Relación de MUCHO A MUCHO...
	 * Cuando se definde una relación ManyToMany, es muy importante el orden de los parametros.
	 * Por ejemplo, en este caso al estar en la clase Usuario, la primera llave foránea debe ser el id propio de la clase.
	 * joinColumns = @JoinColumn(name="idUsuario")
	 * y en el parámetro inverseJoinColumns = @JoinColumn(name="idPerfil"), se especifica el atributo con el que se relaciona el Usuario.
	 */	
	@ManyToMany(fetch=FetchType.EAGER) //Cuando hagamos una búsqueda de un usuario, se recupere en la misma consulta todos los perfiles que tenga asociado dicho usuario...
	@JoinTable(name="UsuarioPerfil",
			   joinColumns = @JoinColumn(name="idUsuario"),
			   inverseJoinColumns = @JoinColumn(name="idPerfil")			
			)
	private List<Perfil> perfiles;	
	
	/*Método que agrega un perfil al usuario; Por lo que al GUARDAR UN USUARIO, automáticamente
	 * Se guardan todos los perfiles en la tabla UsuarioPerfil... esto lo permite
	 * La configuración --> fetch=FetchType.EAGER*/
	public void agregar(Perfil tempPerfil) {
		if (perfiles == null) {
			perfiles = new LinkedList<Perfil>();
		}
		perfiles.add(tempPerfil);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
		

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", nombre=" + nombre + ", email=" + email
				+ ", password=" + password + ", estatus=" + estatus + ", fechaRegistro=" + fechaRegistro + "]";
	}
	
}
