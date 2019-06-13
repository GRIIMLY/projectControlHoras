package co.com.samtel.ControlAccesos.dto;

import java.util.Date;

public class ReportControlAccesoOrd {

	private Date fecha ;
	private Integer tipoAcceso;
	private Integer codigoUsuario;
	private String nombre;
	
	public ReportControlAccesoOrd(Date fecha, Integer tipoAcceso, Integer codigoUsuario, String nombre) {
		super();
		this.fecha = fecha;
		this.tipoAcceso = tipoAcceso;
		this.codigoUsuario = codigoUsuario;
		this.nombre = nombre;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getTipoAcceso() {
		return tipoAcceso;
	}
	public void setTipoAcceso(Integer tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}
	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
}
