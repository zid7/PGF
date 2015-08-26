package ixpan.pgf.model;
// Generated Aug 26, 2015 10:06:04 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PresupuestoId generated by hbm2java
 */
@Embeddable
public class PresupuestoId implements java.io.Serializable {

	private int idPresupuesto;
	private int RContratistaObraContratistaIdContratista;
	private int RContratistaObraObraIdObra;
	private int CStatusPresupuestoIdCStatusPresupuesto;

	public PresupuestoId() {
	}

	public PresupuestoId(int idPresupuesto, int RContratistaObraContratistaIdContratista,
			int RContratistaObraObraIdObra, int CStatusPresupuestoIdCStatusPresupuesto) {
		this.idPresupuesto = idPresupuesto;
		this.RContratistaObraContratistaIdContratista = RContratistaObraContratistaIdContratista;
		this.RContratistaObraObraIdObra = RContratistaObraObraIdObra;
		this.CStatusPresupuestoIdCStatusPresupuesto = CStatusPresupuestoIdCStatusPresupuesto;
	}

	@Column(name = "idPresupuesto", nullable = false)
	public int getIdPresupuesto() {
		return this.idPresupuesto;
	}

	public void setIdPresupuesto(int idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}

	@Column(name = "R_Contratista_Obra_Contratista_idContratista", nullable = false)
	public int getRContratistaObraContratistaIdContratista() {
		return this.RContratistaObraContratistaIdContratista;
	}

	public void setRContratistaObraContratistaIdContratista(int RContratistaObraContratistaIdContratista) {
		this.RContratistaObraContratistaIdContratista = RContratistaObraContratistaIdContratista;
	}

	@Column(name = "R_Contratista_Obra_Obra_idObra", nullable = false)
	public int getRContratistaObraObraIdObra() {
		return this.RContratistaObraObraIdObra;
	}

	public void setRContratistaObraObraIdObra(int RContratistaObraObraIdObra) {
		this.RContratistaObraObraIdObra = RContratistaObraObraIdObra;
	}

	@Column(name = "C_Status_Presupuesto_idC_Status_Presupuesto", nullable = false)
	public int getCStatusPresupuestoIdCStatusPresupuesto() {
		return this.CStatusPresupuestoIdCStatusPresupuesto;
	}

	public void setCStatusPresupuestoIdCStatusPresupuesto(int CStatusPresupuestoIdCStatusPresupuesto) {
		this.CStatusPresupuestoIdCStatusPresupuesto = CStatusPresupuestoIdCStatusPresupuesto;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PresupuestoId))
			return false;
		PresupuestoId castOther = (PresupuestoId) other;

		return (this.getIdPresupuesto() == castOther.getIdPresupuesto())
				&& (this.getRContratistaObraContratistaIdContratista() == castOther
						.getRContratistaObraContratistaIdContratista())
				&& (this.getRContratistaObraObraIdObra() == castOther.getRContratistaObraObraIdObra())
				&& (this.getCStatusPresupuestoIdCStatusPresupuesto() == castOther
						.getCStatusPresupuestoIdCStatusPresupuesto());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdPresupuesto();
		result = 37 * result + this.getRContratistaObraContratistaIdContratista();
		result = 37 * result + this.getRContratistaObraObraIdObra();
		result = 37 * result + this.getCStatusPresupuestoIdCStatusPresupuesto();
		return result;
	}

}
