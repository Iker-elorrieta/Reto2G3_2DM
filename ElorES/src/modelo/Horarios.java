package modelo;


import java.io.Serializable;

public class Horarios implements Serializable {

    private static final long serialVersionUID = 1L;

        private Integer id;
        private Modulos modulos;
        private Users users;
        private String dia;
        private byte hora;
        private String aula;
    

    public Horarios() {
    }


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Modulos getModulos() {
		return modulos;
	}


	public void setModulos(Modulos modulos) {
		this.modulos = modulos;
	}


	public Users getUsers() {
		return users;
	}


	public void setUsers(Users users) {
		this.users = users;
	}


	public String getDia() {
		return dia;
	}


	public void setDia(String dia) {
		this.dia = dia;
	}


	public byte getHora() {
		return hora;
	}


	public void setHora(byte hora) {
		this.hora = hora;
	}


	public String getAula() {
		return aula;
	}


	public void setAula(String aula) {
		this.aula = aula;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}
