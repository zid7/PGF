package ixpan.pgf.model;
// Generated Aug 26, 2015 10:06:04 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Telefono generated by hbm2java
 */
@Entity
@Table(name = "Telefono", catalog = "PGF")
public class Telefono implements java.io.Serializable {

	private TelefonoId id;
	private int numTelefono;
	private String tipoTelefono;

	public Telefono() {
	}

	public Telefono(TelefonoId id, int numTelefono, String tipoTelefono) {
		this.id = id;
		this.numTelefono = numTelefono;
		this.tipoTelefono = tipoTelefono;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idTelefono", column = @Column(name = "idTelefono", nullable = false) ),
			@AttributeOverride(name = "usuarioIdUsuario", column = @Column(name = "Usuario_idUsuario", nullable = false) ) })
	public TelefonoId getId() {
		return this.id;
	}

	public void setId(TelefonoId id) {
		this.id = id;
	}

	@Column(name = "NumTelefono", nullable = false)
	public int getNumTelefono() {
		return this.numTelefono;
	}

	public void setNumTelefono(int numTelefono) {
		this.numTelefono = numTelefono;
	}

	@Column(name = "TipoTelefono", nullable = false, length = 20)
	public String getTipoTelefono() {
		return this.tipoTelefono;
	}

	public void setTipoTelefono(String tipoTelefono) {
		this.tipoTelefono = tipoTelefono;
	}

}
