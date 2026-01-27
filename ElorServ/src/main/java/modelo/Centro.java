package modelo;

import java.io.Serializable;

public class Centro implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer CCEN;
    private String NOM;
    private String NOME;
    private String DGENRC;
    private String DGENRE;
    private String GENR;
    private Integer MUNI;
    private String DMUNIC;
    private String DMUNIE;
    private String DTERRC;
    private String DTERRE;
    private Integer DEPE;
    private String DTITUC;
    private String DTITUE;
    private String DOMI;
    private Integer CPOS;
    private Long TEL1;
    private Long TFAX;
    private String EMAIL;
    private String PAGINA;
    private Double COOR_X;
    private Double COOR_Y;
    private Double LATITUD;
    private Double LONGITUD;

    public Centro() {}

    public Integer getCCEN() { return CCEN; }
    public void setCCEN(Integer CCEN) { this.CCEN = CCEN; }

    public String getNOM() { return NOM; }
    public void setNOM(String NOM) { this.NOM = NOM; }

    public String getNOME() { return NOME; }
    public void setNOME(String NOME) { this.NOME = NOME; }

    public String getDGENRC() { return DGENRC; }
    public void setDGENRC(String DGENRC) { this.DGENRC = DGENRC; }

    public String getDGENRE() { return DGENRE; }
    public void setDGENRE(String DGENRE) { this.DGENRE = DGENRE; }

    public String getGENR() { return GENR; }
    public void setGENR(String GENR) { this.GENR = GENR; }

    public Integer getMUNI() { return MUNI; }
    public void setMUNI(Integer MUNI) { this.MUNI = MUNI; }

    public String getDMUNIC() { return DMUNIC; }
    public void setDMUNIC(String DMUNIC) { this.DMUNIC = DMUNIC; }

    public String getDMUNIE() { return DMUNIE; }
    public void setDMUNIE(String DMUNIE) { this.DMUNIE = DMUNIE; }

    public String getDTERRC() { return DTERRC; }
    public void setDTERRC(String DTERRC) { this.DTERRC = DTERRC; }

    public String getDTERRE() { return DTERRE; }
    public void setDTERRE(String DTERRE) { this.DTERRE = DTERRE; }

    public Integer getDEPE() { return DEPE; }
    public void setDEPE(Integer DEPE) { this.DEPE = DEPE; }

    public String getDTITUC() { return DTITUC; }
    public void setDTITUC(String DTITUC) { this.DTITUC = DTITUC; }

    public String getDTITUE() { return DTITUE; }
    public void setDTITUE(String DTITUE) { this.DTITUE = DTITUE; }

    public String getDOMI() { return DOMI; }
    public void setDOMI(String DOMI) { this.DOMI = DOMI; }

    public Integer getCPOS() { return CPOS; }
    public void setCPOS(Integer CPOS) { this.CPOS = CPOS; }

    public Long getTEL1() { return TEL1; }
    public void setTEL1(Long TEL1) { this.TEL1 = TEL1; }

    public Long getTFAX() { return TFAX; }
    public void setTFAX(Long TFAX) { this.TFAX = TFAX; }

    public String getEMAIL() { return EMAIL; }
    public void setEMAIL(String EMAIL) { this.EMAIL = EMAIL; }

    public String getPAGINA() { return PAGINA; }
    public void setPAGINA(String PAGINA) { this.PAGINA = PAGINA; }

    public Double getCOOR_X() { return COOR_X; }
    public void setCOOR_X(Double COOR_X) { this.COOR_X = COOR_X; }

    public Double getCOOR_Y() { return COOR_Y; }
    public void setCOOR_Y(Double COOR_Y) { this.COOR_Y = COOR_Y; }

    public Double getLATITUD() { return LATITUD; }
    public void setLATITUD(Double LATITUD) { this.LATITUD = LATITUD; }

    public Double getLONGITUD() { return LONGITUD; }
    public void setLONGITUD(Double LONGITUD) { this.LONGITUD = LONGITUD; }
}
