package it.unisa.model.bean;


import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

public class ProductVeicolo implements Serializable {
	private static final long serialVersionUID = 1L;

	String codice_telaio;
	String targa;
	String colore;
	String marchio;
	String modello;
	int kw;
	int prezzo;
	String partita_iva;
	String codice_fiscale;
	InputStream path;
	String base64Image;
	int numero_passegeri;
	String sconto;
	String accessori;
	String custom;
	String photo;

	public ProductVeicolo() {
		codice_telaio = "";
		targa = "";
		colore = "";
		marchio = "";
		modello = "";
		partita_iva = "";
		codice_fiscale = "";
		kw = 0;
		prezzo = 0;
		path = null;
		numero_passegeri = 0;
		sconto = "";
		accessori = "";
		custom = "";
		photo = "";
	}
	
	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public int getNumero_passegeri() {
		return numero_passegeri;
	}


	public void setNumero_passegeri(int numero_passegeri) {
		this.numero_passegeri = numero_passegeri;
	}


	public String getSconto() {
		return sconto;
	}


	public void setSconto(String sconto) {
		this.sconto = sconto;
	}


	public String getAccessori() {
		return accessori;
	}


	public void setAccessori(String accessori) {
		this.accessori = accessori;
	}


	public String getCustom() {
		return custom;
	}


	public void setCustom(String custom) {
		this.custom = custom;
	}


	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}



	/*
	 * public String getPath() { return path; }
	 * 
	 * public void setPath(String path) { this.path = path; }
	 */

	public InputStream getPath() {
		return path;
	}

	public void setPath(InputStream path) throws IOException {
		this.path = path;
		
	}

	public String getCodice_telaio() {
		return codice_telaio;
	}

	public void setCodice_telaio(String codice_telaio) {
		this.codice_telaio = codice_telaio;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getMarchio() {
		return marchio;
	}

	public void setMarchio(String marchio) {
		this.marchio = marchio;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public int getKw() {
		return kw;
	}

	public void setKw(int kw) {
		this.kw = kw;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public String getPartita_iva() {
		return partita_iva;
	}

	public void setPartita_iva(String partita_iva) {
		this.partita_iva = partita_iva;
	}

	public String getCodice_fiscale() {
		return codice_fiscale;
	}

	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}

	@Override
	public String toString() {
		return codice_telaio + "(" + targa + ")" + colore + "," + marchio + "," + modello + "," + kw + "," + prezzo
				+ "," + partita_iva + "," + codice_fiscale + "," + path;
	}
	
	
	/*
	 * public Image readStream(InputStream inStream) throws IOException {
	 * 
	 * InputStream is = new BufferedInputStream(inStream); Image image =
	 * ImageIO.read(is);
	 * 
	 * return image; }
	 */


}
