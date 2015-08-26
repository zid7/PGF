package ixpan.pgf.model;
// Generated Aug 26, 2015 10:06:04 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SolicitudPagoChangeId generated by hbm2java
 */
@Embeddable
public class SolicitudPagoChangeId implements java.io.Serializable {

	private int idSolicitudPagoChange;
	private int deStatus;
	private int AStastus;
	private int usuarioIdUsuario;

	public SolicitudPagoChangeId() {
	}

	public SolicitudPagoChangeId(int idSolicitudPagoChange, int deStatus, int AStastus, int usuarioIdUsuario) {
		this.idSolicitudPagoChange = idSolicitudPagoChange;
		this.deStatus = deStatus;
		this.AStastus = AStastus;
		this.usuarioIdUsuario = usuarioIdUsuario;
	}

	@Column(name = "idSolicitud_Pago_Change", nullable = false)
	public int getIdSolicitudPagoChange() {
		return this.idSolicitudPagoChange;
	}

	public void setIdSolicitudPagoChange(int idSolicitudPagoChange) {
		this.idSolicitudPagoChange = idSolicitudPagoChange;
	}

	@Column(name = "De_Status", nullable = false)
	public int getDeStatus() {
		return this.deStatus;
	}

	public void setDeStatus(int deStatus) {
		this.deStatus = deStatus;
	}

	@Column(name = "A_Stastus", nullable = false)
	public int getAStastus() {
		return this.AStastus;
	}

	public void setAStastus(int AStastus) {
		this.AStastus = AStastus;
	}

	@Column(name = "Usuario_idUsuario", nullable = false)
	public int getUsuarioIdUsuario() {
		return this.usuarioIdUsuario;
	}

	public void setUsuarioIdUsuario(int usuarioIdUsuario) {
		this.usuarioIdUsuario = usuarioIdUsuario;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SolicitudPagoChangeId))
			return false;
		SolicitudPagoChangeId castOther = (SolicitudPagoChangeId) other;

		return (this.getIdSolicitudPagoChange() == castOther.getIdSolicitudPagoChange())
				&& (this.getDeStatus() == castOther.getDeStatus()) && (this.getAStastus() == castOther.getAStastus())
				&& (this.getUsuarioIdUsuario() == castOther.getUsuarioIdUsuario());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdSolicitudPagoChange();
		result = 37 * result + this.getDeStatus();
		result = 37 * result + this.getAStastus();
		result = 37 * result + this.getUsuarioIdUsuario();
		return result;
	}

}
