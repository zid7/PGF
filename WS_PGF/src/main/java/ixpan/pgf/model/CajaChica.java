package ixpan.pgf.model;
// Generated Aug 26, 2015 10:06:04 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CajaChica generated by hbm2java
 */
@Entity
@Table(name = "Caja_Chica", catalog = "PGF")
public class CajaChica implements java.io.Serializable {

	private CajaChicaId id;
	private byte saldoCajaAnterior;
	private byte pagoPgf;
	private byte saldoActual;
	private byte saltoTotal;
	private byte[] status;

	public CajaChica() {
	}

	public CajaChica(CajaChicaId id, byte saldoCajaAnterior, byte pagoPgf, byte saldoActual, byte saltoTotal,
			byte[] status) {
		this.id = id;
		this.saldoCajaAnterior = saldoCajaAnterior;
		this.pagoPgf = pagoPgf;
		this.saldoActual = saldoActual;
		this.saltoTotal = saltoTotal;
		this.status = status;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "idCajaChica", column = @Column(name = "idCaja_Chica", nullable = false) ),
			@AttributeOverride(name = "internosIdInternos", column = @Column(name = "Internos_idInternos", nullable = false) ) })
	public CajaChicaId getId() {
		return this.id;
	}

	public void setId(CajaChicaId id) {
		this.id = id;
	}

	@Column(name = "Saldo_Caja_Anterior", nullable = false, precision = 2, scale = 0)
	public byte getSaldoCajaAnterior() {
		return this.saldoCajaAnterior;
	}

	public void setSaldoCajaAnterior(byte saldoCajaAnterior) {
		this.saldoCajaAnterior = saldoCajaAnterior;
	}

	@Column(name = "Pago_PGF", nullable = false, precision = 2, scale = 0)
	public byte getPagoPgf() {
		return this.pagoPgf;
	}

	public void setPagoPgf(byte pagoPgf) {
		this.pagoPgf = pagoPgf;
	}

	@Column(name = "Saldo_Actual", nullable = false, precision = 2, scale = 0)
	public byte getSaldoActual() {
		return this.saldoActual;
	}

	public void setSaldoActual(byte saldoActual) {
		this.saldoActual = saldoActual;
	}

	@Column(name = "Salto_Total", nullable = false, precision = 2, scale = 0)
	public byte getSaltoTotal() {
		return this.saltoTotal;
	}

	public void setSaltoTotal(byte saltoTotal) {
		this.saltoTotal = saltoTotal;
	}

	@Column(name = "Status", nullable = false)
	public byte[] getStatus() {
		return this.status;
	}

	public void setStatus(byte[] status) {
		this.status = status;
	}

}
