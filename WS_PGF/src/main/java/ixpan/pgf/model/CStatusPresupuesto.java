package ixpan.pgf.model;
// Generated Aug 26, 2015 10:06:04 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CStatusPresupuesto generated by hbm2java
 */
@Entity
@Table(name = "C_Status_Presupuesto", catalog = "PGF")
public class CStatusPresupuesto implements java.io.Serializable {

	private int idCStatusPresupuesto;

	public CStatusPresupuesto() {
	}

	public CStatusPresupuesto(int idCStatusPresupuesto) {
		this.idCStatusPresupuesto = idCStatusPresupuesto;
	}

	@Id

	@Column(name = "idC_Status_Presupuesto", unique = true, nullable = false)
	public int getIdCStatusPresupuesto() {
		return this.idCStatusPresupuesto;
	}

	public void setIdCStatusPresupuesto(int idCStatusPresupuesto) {
		this.idCStatusPresupuesto = idCStatusPresupuesto;
	}

}