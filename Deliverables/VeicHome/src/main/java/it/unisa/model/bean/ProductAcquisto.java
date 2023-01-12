package it.unisa.model.bean;

import java.io.Serializable;

public class ProductAcquisto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String targaFK;
	String codice_fiscaleFK;
	String data_di_acquisto;

	public ProductAcquisto(){
		targaFK="";
		codice_fiscaleFK="";
		data_di_acquisto="";
	}
	
	public String getData_di_acquisto() {
		return data_di_acquisto;
	}

	public void setData_di_acquisto(String data_di_acquisto) {
		this.data_di_acquisto = data_di_acquisto;
	}
	public String getTargaFK() {
		return targaFK;
	}

	public void setTargaFK(String targaFK) {
		this.targaFK = targaFK;
	}

	public String getCodice_fiscaleFK() {
		return codice_fiscaleFK;
	}

	public void setCodice_fiscaleFK(String codice_fiscaleFK) {
		this.codice_fiscaleFK = codice_fiscaleFK;
	}

}
