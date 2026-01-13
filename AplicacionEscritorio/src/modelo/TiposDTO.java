package modelo;

import java.io.Serializable;

public class TiposDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String nameEu;

    public TiposDTO() {
    }

    public TiposDTO(Integer id, String name, String nameEu) {
        this.id = id;
        this.name = name;
        this.nameEu = nameEu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEu() {
        return nameEu;
    }

    public void setNameEu(String nameEu) {
        this.nameEu = nameEu;
    }
}
