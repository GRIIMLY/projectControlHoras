package co.com.samtel.ControlAccesos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import co.com.samtel.ControlAccesos.dto.ReportControlAccesoOrd;


/**
 * The persistent class for the tblcontrol_accesos_ord database table.
 * 
 */
@Entity
@Table(name="tblcontrol_accesos_ord")
@NamedQuery(name="ControlAccesosOrd.findAll", query="SELECT c FROM ControlAccesosOrd c")
@NamedNativeQuery(name = "ControlAccesosOrd.findAllControlRegistrosOrd" , query = "select ca.fecha as fecha , ca.tipo_acceso as tipoAcceso , ca.tblcodigo_usuarios_codigo as codigoUsuario, us.nombre as nombre from tblcontrol_accesos_ord as ca inner join tblcodigo_usuarios as cu on ca.tblcodigo_usuarios_codigo = cu.codigo inner join tblusuarios as us on us.cedula = cu.cedula WHERE MONTH(DATE(ca.fecha)) = :mes AND YEAR(DATE(ca.fecha)) = :year AND DAY(DATE(ca.fecha))  BETWEEN  :diaI AND  :diaF", resultSetMapping = "ReportControlAccesoOrdMapping" )
@SqlResultSetMapping(
        name = "ReportControlAccesoOrdMapping",
        classes = @ConstructorResult(
                targetClass = ReportControlAccesoOrd.class,
                columns = { @ColumnResult(name = "fecha"), 
                            @ColumnResult(name = "tipoAcceso"), 
                            @ColumnResult(name = "codigoUsuario"), 
                            @ColumnResult(name = "nombre")}))
public class ControlAccesosOrd implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ControlAccesosOrdPK id;

	@Column(name="tipo_acceso")
	private int tipoAcceso;

	//bi-directional many-to-one association to CodigoUsuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tblcodigo_usuarios_codigo" , insertable=false, updatable=false)
	private CodigoUsuario tblcodigoUsuario;

	public ControlAccesosOrd() {
	}

	
	public ControlAccesosOrd(ControlAccesosOrdPK id, int tipoAcceso, CodigoUsuario tblcodigoUsuario) {
		super();
		this.id = id;
		this.tipoAcceso = tipoAcceso;
		this.tblcodigoUsuario = tblcodigoUsuario;
	}


	public ControlAccesosOrdPK getId() {
		return this.id;
	}

	public void setId(ControlAccesosOrdPK id) {
		this.id = id;
	}

	public int getTipoAcceso() {
		return this.tipoAcceso;
	}

	public void setTipoAcceso(int tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}

	public CodigoUsuario getTblcodigoUsuario() {
		return this.tblcodigoUsuario;
	}

	public void setTblcodigoUsuario(CodigoUsuario tblcodigoUsuario) {
		this.tblcodigoUsuario = tblcodigoUsuario;
	}


	@Override
	public String toString() {
		return "ControlAccesosOrd [id=" + id + ", tipoAcceso=" + tipoAcceso + ", tblcodigoUsuario=" + tblcodigoUsuario
				+ "]";
	}
	
	

}