package it.unisa.model.bean;


import java.io.Serializable;

public class ProductCarrello implements Serializable {

	private static final long serialVersionUID = 1L;

	String targa;
	String modello;
	int prezzo;
	String base64Image;
	String sessionid;
	int idCarrello;
	String utenteid;

	public ProductCarrello() {
		targa = "";
		modello = "";
		prezzo = 0;
		sessionid = "";
		idCarrello=0;
		utenteid="";
	}
	
	public String getutenteid() {
		return utenteid;
	}


	public void setutenteid(String utenteid) {
		this.utenteid = utenteid;
	}

	public int getIdCarrello() {
		return idCarrello;
	}


	public void setIdCarrello(int idCarrello) {
		this.idCarrello = idCarrello;
	}


	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

}
