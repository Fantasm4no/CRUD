package ec.edu.ups.demoUni;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_uni")
public class Universidad {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="uni_id")
	private int uniId;
	
	@Column(name="uni_nombre")
    private String Nombre;
	
	@Column(name="uni_direccion")
    private String Direccion;
	
	@Column(name="uni_fundador")
    private String Fundador;

	public int getUniId() {
		return uniId;
	}

	public void setUniId(int uniId) {
		this.uniId = uniId;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		this.Direccion = direccion;
	}

	public String getFundador() {
		return Fundador;
	}

	public void setFundador(String fundador) {
		this.Fundador = fundador;
	}
	
	
}
