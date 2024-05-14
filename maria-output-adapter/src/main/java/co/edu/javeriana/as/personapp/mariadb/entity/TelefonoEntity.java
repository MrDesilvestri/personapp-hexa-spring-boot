package co.edu.javeriana.as.personapp.mariadb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author aasanchez
 */
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="telefono", catalog = "persona_db", schema = "")
@NamedQueries({ @NamedQuery(name = "TelefonoEntity.findAll", query = "SELECT t FROM TelefonoEntity t"),
		@NamedQuery(name = "TelefonoEntity.findByNum", query = "SELECT t FROM TelefonoEntity t WHERE t.num = :num"),
		@NamedQuery(name = "TelefonoEntity.findByOper", query = "SELECT t FROM TelefonoEntity t WHERE t.oper = :oper") })
public class TelefonoEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id

	@Basic(optional = false)
	@Column(nullable = false, length = 15)
	private String num;
	@Basic(optional = false)
	@Column(nullable = false, length = 45)
	private String oper;
	@JoinColumn(name = "duenio", referencedColumnName = "cc", nullable = false)
	@ManyToOne(optional = false)
	private PersonaEntity duenio;


    @Override
	public int hashCode() {
		int hash = 0;
		hash += (num != null ? num.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TelefonoEntity)) {
			return false;
		}
		TelefonoEntity other = (TelefonoEntity) object;
        return (this.num != null || other.num == null) && (this.num == null || this.num.equals(other.num));
    }

	@Override
	public String toString() {
		return "TelefonoEntity [num=" + num + ", oper=" + oper + "]";
	}

}
