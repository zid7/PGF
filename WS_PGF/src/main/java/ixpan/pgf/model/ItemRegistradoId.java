package ixpan.pgf.model;
// Generated Aug 26, 2015 10:06:04 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ItemRegistradoId generated by hbm2java
 */
@Embeddable
public class ItemRegistradoId implements java.io.Serializable {

	private int idItemRegistrado;
	private int CTipoComprobanteIdCTipoComprobante;
	private int CItemCajaIdCItemCaja;
	private int cajaChicaIdCajaChica;
	private int cajaChicaInternosIdInternos;

	public ItemRegistradoId() {
	}

	public ItemRegistradoId(int idItemRegistrado, int CTipoComprobanteIdCTipoComprobante, int CItemCajaIdCItemCaja,
			int cajaChicaIdCajaChica, int cajaChicaInternosIdInternos) {
		this.idItemRegistrado = idItemRegistrado;
		this.CTipoComprobanteIdCTipoComprobante = CTipoComprobanteIdCTipoComprobante;
		this.CItemCajaIdCItemCaja = CItemCajaIdCItemCaja;
		this.cajaChicaIdCajaChica = cajaChicaIdCajaChica;
		this.cajaChicaInternosIdInternos = cajaChicaInternosIdInternos;
	}

	@Column(name = "idItem_Registrado", nullable = false)
	public int getIdItemRegistrado() {
		return this.idItemRegistrado;
	}

	public void setIdItemRegistrado(int idItemRegistrado) {
		this.idItemRegistrado = idItemRegistrado;
	}

	@Column(name = "C_Tipo_Comprobante_idC_Tipo_Comprobante", nullable = false)
	public int getCTipoComprobanteIdCTipoComprobante() {
		return this.CTipoComprobanteIdCTipoComprobante;
	}

	public void setCTipoComprobanteIdCTipoComprobante(int CTipoComprobanteIdCTipoComprobante) {
		this.CTipoComprobanteIdCTipoComprobante = CTipoComprobanteIdCTipoComprobante;
	}

	@Column(name = "C_Item_Caja_idC_Item_Caja", nullable = false)
	public int getCItemCajaIdCItemCaja() {
		return this.CItemCajaIdCItemCaja;
	}

	public void setCItemCajaIdCItemCaja(int CItemCajaIdCItemCaja) {
		this.CItemCajaIdCItemCaja = CItemCajaIdCItemCaja;
	}

	@Column(name = "Caja_Chica_idCaja_Chica", nullable = false)
	public int getCajaChicaIdCajaChica() {
		return this.cajaChicaIdCajaChica;
	}

	public void setCajaChicaIdCajaChica(int cajaChicaIdCajaChica) {
		this.cajaChicaIdCajaChica = cajaChicaIdCajaChica;
	}

	@Column(name = "Caja_Chica_Internos_idInternos", nullable = false)
	public int getCajaChicaInternosIdInternos() {
		return this.cajaChicaInternosIdInternos;
	}

	public void setCajaChicaInternosIdInternos(int cajaChicaInternosIdInternos) {
		this.cajaChicaInternosIdInternos = cajaChicaInternosIdInternos;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ItemRegistradoId))
			return false;
		ItemRegistradoId castOther = (ItemRegistradoId) other;

		return (this.getIdItemRegistrado() == castOther.getIdItemRegistrado())
				&& (this.getCTipoComprobanteIdCTipoComprobante() == castOther.getCTipoComprobanteIdCTipoComprobante())
				&& (this.getCItemCajaIdCItemCaja() == castOther.getCItemCajaIdCItemCaja())
				&& (this.getCajaChicaIdCajaChica() == castOther.getCajaChicaIdCajaChica())
				&& (this.getCajaChicaInternosIdInternos() == castOther.getCajaChicaInternosIdInternos());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdItemRegistrado();
		result = 37 * result + this.getCTipoComprobanteIdCTipoComprobante();
		result = 37 * result + this.getCItemCajaIdCItemCaja();
		result = 37 * result + this.getCajaChicaIdCajaChica();
		result = 37 * result + this.getCajaChicaInternosIdInternos();
		return result;
	}

}
