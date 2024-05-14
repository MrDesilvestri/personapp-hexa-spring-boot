package co.edu.javeriana.as.personapp.mariadb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author aasanchez
 */
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="persona", catalog = "persona_db", schema = "persona_db")
@NamedQueries({ @NamedQuery(name = "PersonaEntity.findAll", query = "SELECT p FROM PersonaEntity p"),
		@NamedQuery(name = "PersonaEntity.findByCc", query = "SELECT p FROM PersonaEntity p WHERE p.cc = :cc"),
		@NamedQuery(name = "PersonaEntity.findByNombre", query = "SELECT p FROM PersonaEntity p WHERE p.nombre = :nombre"),
		@NamedQuery(name = "PersonaEntity.findByApellido", query = "SELECT p FROM PersonaEntity p WHERE p.apellido = :apellido"),
		@NamedQuery(name = "PersonaEntity.findByGenero", query = "SELECT p FROM PersonaEntity p WHERE p.genero = :genero"),
		@NamedQuery(name = "PersonaEntity.findByEdad", query = "SELECT p FROM PersonaEntity p WHERE p.edad = :edad") })
public class PersonaEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(nullable = false)
	private Integer cc;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String nombre;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String apellido;
	@Basic(optional = false)
	@Column(nullable = false)
	private Character genero;
	private Integer edad;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "duenio")
	private List<TelefonoEntity> telefonos;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
	private List<EstudiosEntity> estudios;



	public PersonaEntity(Integer cc) {
		this.cc = cc;
	}

	public PersonaEntity(Integer cc, String nombre, String apellido, Character genero) {
		this.cc = cc;
		this.nombre = nombre;
		this.apellido = apellido;
		this.genero = genero;
	}

    @Override
	public int hashCode() {
		int hash = 0;
		hash += (cc != null ? cc.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof PersonaEntity)) {
			return false;
		}
		PersonaEntity other = (PersonaEntity) object;
        return (this.cc != null || other.cc == null) && (this.cc == null || this.cc.equals(other.cc));
    }

	@Override
	public String toString() {
		return "PersonaEntity [cc=" + cc + ", nombre=" + nombre + ", apellido=" + apellido + ", genero=" + genero + ", edad="
				+ edad + "]";
	}

}
