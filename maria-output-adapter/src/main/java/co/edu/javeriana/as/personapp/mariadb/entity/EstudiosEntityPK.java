package co.edu.javeriana.as.personapp.mariadb.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author aasanchez
 */
@Setter
@Getter
@Embeddable
public class EstudiosEntityPK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "id_prof", nullable = false)
	private int idProf;
	@Basic(optional = false)
	@Column(name = "cc_per", nullable = false)
	private int ccPer;

	public EstudiosEntityPK() {
	}

	public EstudiosEntityPK(int idProf, int ccPer) {
		this.idProf = idProf;
		this.ccPer = ccPer;
	}

    @Override
	public int hashCode() {
		int hash = 0;
		hash += idProf;
		hash += ccPer;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof EstudiosEntityPK)) {
			return false;
		}
		EstudiosEntityPK other = (EstudiosEntityPK) object;
		if (this.idProf != other.idProf) {
			return false;
		}
        return this.ccPer == other.ccPer;
    }

	@Override
	public String toString() {
		return "EstudiosEntityPK [idProf=" + idProf + ", ccPer=" + ccPer + "]";
	}

}
