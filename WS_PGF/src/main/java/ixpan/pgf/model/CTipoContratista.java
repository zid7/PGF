package ixpan.pgf.model;
// Generated Aug 26, 2015 10:06:04 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CTipoContratista generated by hbm2java
 */
@Entity
@Table(name = "C_Tipo_Contratista", catalog = "PGF")
public class CTipoContratista implements java.io.Serializable {

	private Integer idCTipoContratista;

	public CTipoContratista() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idC_Tipo_Contratista", unique = true, nullable = false)
	public Integer getIdCTipoContratista() {
		return this.idCTipoContratista;
	}

	public void setIdCTipoContratista(Integer idCTipoContratista) {
		this.idCTipoContratista = idCTipoContratista;
	}

}
