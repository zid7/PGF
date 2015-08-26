package ixpan.pgf.model;
// Generated Aug 26, 2015 10:06:04 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RInternosObra generated by hbm2java
 */
@Entity
@Table(name = "R_Internos_Obra", catalog = "PGF")
public class RInternosObra implements java.io.Serializable {

	private int idRInternosObra;
	private int obraIdObra;
	private int internosIdInternos;
	private int CRolObraIdCRolObra;

	public RInternosObra() {
	}

	public RInternosObra(int idRInternosObra, int obraIdObra, int internosIdInternos, int CRolObraIdCRolObra) {
		this.idRInternosObra = idRInternosObra;
		this.obraIdObra = obraIdObra;
		this.internosIdInternos = internosIdInternos;
		this.CRolObraIdCRolObra = CRolObraIdCRolObra;
	}

	@Id

	@Column(name = "idR_Internos_Obra", unique = true, nullable = false)
	public int getIdRInternosObra() {
		return this.idRInternosObra;
	}

	public void setIdRInternosObra(int idRInternosObra) {
		this.idRInternosObra = idRInternosObra;
	}

	@Column(name = "Obra_idObra", nullable = false)
	public int getObraIdObra() {
		return this.obraIdObra;
	}

	public void setObraIdObra(int obraIdObra) {
		this.obraIdObra = obraIdObra;
	}

	@Column(name = "Internos_idInternos", nullable = false)
	public int getInternosIdInternos() {
		return this.internosIdInternos;
	}

	public void setInternosIdInternos(int internosIdInternos) {
		this.internosIdInternos = internosIdInternos;
	}

	@Column(name = "C_Rol_Obra_idC_Rol_Obra", nullable = false)
	public int getCRolObraIdCRolObra() {
		return this.CRolObraIdCRolObra;
	}

	public void setCRolObraIdCRolObra(int CRolObraIdCRolObra) {
		this.CRolObraIdCRolObra = CRolObraIdCRolObra;
	}

}
