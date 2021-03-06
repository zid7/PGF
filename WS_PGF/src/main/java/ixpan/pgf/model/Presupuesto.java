package ixpan.pgf.model;
// Generated Aug 26, 2015 10:06:04 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Presupuesto generated by hbm2java
 */
@Entity
@Table(name = "Presupuesto", catalog = "PGF")
public class Presupuesto implements java.io.Serializable {

	private PresupuestoId id;
	private int idTipoPresupuesto;

	public Presupuesto() {
	}

	public Presupuesto(PresupuestoId id, int idTipoPresupuesto) {
		this.id = id;
		this.idTipoPresupuesto = idTipoPresupuesto;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idPresupuesto", column = @Column(name = "idPresupuesto", nullable = false) ),
			@AttributeOverride(name = "RContratistaObraContratistaIdContratista", column = @Column(name = "R_Contratista_Obra_Contratista_idContratista", nullable = false) ),
			@AttributeOverride(name = "RContratistaObraObraIdObra", column = @Column(name = "R_Contratista_Obra_Obra_idObra", nullable = false) ),
			@AttributeOverride(name = "CStatusPresupuestoIdCStatusPresupuesto", column = @Column(name = "C_Status_Presupuesto_idC_Status_Presupuesto", nullable = false) ) })
	public PresupuestoId getId() {
		return this.id;
	}

	public void setId(PresupuestoId id) {
		this.id = id;
	}

	@Column(name = "idTipo_Presupuesto", nullable = false)
	public int getIdTipoPresupuesto() {
		return this.idTipoPresupuesto;
	}

	public void setIdTipoPresupuesto(int idTipoPresupuesto) {
		this.idTipoPresupuesto = idTipoPresupuesto;
	}

}
