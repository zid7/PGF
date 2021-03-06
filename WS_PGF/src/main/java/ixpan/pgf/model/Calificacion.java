package ixpan.pgf.model;
// Generated Aug 26, 2015 10:06:04 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Calificacion generated by hbm2java
 */
@Entity
@Table(name = "Calificacion", catalog = "PGF")
public class Calificacion implements java.io.Serializable {

	private CalificacionId id;
	private Date fechaAlta;
	private int calificacion;

	public Calificacion() {
	}

	public Calificacion(CalificacionId id, Date fechaAlta, int calificacion) {
		this.id = id;
		this.fechaAlta = fechaAlta;
		this.calificacion = calificacion;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idDeUsuario", column = @Column(name = "idDeUsuario", nullable = false) ),
			@AttributeOverride(name = "idAusuario", column = @Column(name = "idAUsuario", nullable = false) ),
			@AttributeOverride(name = "idRubro", column = @Column(name = "idRubro", nullable = false) ),
			@AttributeOverride(name = "idObra", column = @Column(name = "idObra", nullable = false) ) })
	public CalificacionId getId() {
		return this.id;
	}

	public void setId(CalificacionId id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FechaAlta", nullable = false, length = 19)
	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Column(name = "Calificacion", nullable = false)
	public int getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

}
