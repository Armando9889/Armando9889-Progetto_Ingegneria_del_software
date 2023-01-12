package it.unisa.model.bean;

public class ProductCliente {

	String codice_fiscale;
	String nome;            
	String cognome;         
    String sesso;           
    String indirizzo;       
	String data_di_nascita;
	String password;
	String numero_di_carta;
	String data_scadenza;
	String cvv;
	String username;
	String comune_di_nascita;
	int saldo;
	
	public ProductCliente() {
		codice_fiscale="";
		nome="";            
		cognome="";         
	    sesso="";           
	    indirizzo="";       
		data_di_nascita="";
		password="";
		numero_di_carta="";
		data_scadenza="";
		cvv="";
		username="";
		comune_di_nascita="";
		saldo = 0;
		
	}


	public int getSaldo() {
		return saldo;
	}


	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}


	public String getComune_di_nascita() {
		return comune_di_nascita;
	}


	public void setComune_di_nascita(String comune_di_nascita) {
		this.comune_di_nascita = comune_di_nascita;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCodice_fiscale() {
		return codice_fiscale;
	}


	public void setCodice_fiscale(String codice_fiscale) {
		this.codice_fiscale = codice_fiscale;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getSesso() {
		return sesso;
	}


	public void setSesso(String sesso) {
		this.sesso = sesso;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public String getData_di_nascita() {
		return data_di_nascita;
	}


	public void setData_di_nascita(String data_di_nascita) {
		this.data_di_nascita = data_di_nascita;
	}
	public String getNumero_di_carta() {
		return numero_di_carta;
	}


	public void setNumero_di_carta(String numero_di_carta) {
		this.numero_di_carta = numero_di_carta;
	}


	public String getData_scadenza() {
		return data_scadenza;
	}


	public void setData_scadenza(String data_scadenza) {
		this.data_scadenza = data_scadenza;
	}


	public String getCvv() {
		return cvv;
	}


	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	@Override
	public String toString() {
		return  codice_fiscale +"("+nome+")"+cognome+","+sesso+","+indirizzo+","+data_di_nascita+","+numero_di_carta+","+data_scadenza+","+cvv;
	}
	
}
