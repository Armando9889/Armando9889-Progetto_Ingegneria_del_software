package it.unisa.utils;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CFGui {
	
  // Variabili di istanza
  // ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  private CFGenerator cfg;

  // Pressione sul bottone
  // -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  public String calcolaCodice(String nome, String cognome, String comune, String giorno, String anno,
		  String mese, String sesso) {
	  
    String n = nome;
    String c = cognome;
    String cc = comune;
    String g = giorno;
    String a = anno;
    String m1 = mese;
    String s = sesso;
    
    System.out.println(nome);
    System.out.println(cognome);
    System.out.println(comune);
    System.out.println(giorno);
    System.out.println(anno);
    System.out.println(mese);
    System.out.println(sesso);
    
    
    if((n.length() != 0) && (c.length() != 0) && (cc.length() != 0) && (g.length() != 0) &&
       (m1.length() != 0) && (a.length() != 0) && (!m1.equals("")) && (!s.equals(""))) {
       
       try {
         cfg = new CFGenerator(n,c,cc,m1,Integer.parseInt(a),Integer.parseInt(g),s);
       } catch(NumberFormatException e) {
         JOptionPane.showMessageDialog(null,"Anno e Giorno devono essere numerici!","Errore!",JOptionPane.ERROR_MESSAGE);
         return null;
       }
     
       
    }return cfg.getCodiceFiscale();
  }
  
  public String ritornaMese(String numero) {
	  if(numero.equals("01"))
		  return "Gennaio";
	  if(numero.equals("02"))
		  return "Febbraio";
	  if(numero.equals("03"))
		  return "Marzo";
	  if(numero.equals("04"))
		  return "Aprile";
	  if(numero.equals("05"))
		  return "Maggio";
	  if(numero.equals("06"))
		  return "Giugno";
	  if(numero.equals("07"))
		  return "Luglio";
	  if(numero.equals("08"))
		  return "Agosto";
	  if(numero.equals("09"))
		  return "Settembre";
	  if(numero.equals("10"))
		  return "Ottobre";
	  if(numero.equals("11"))
		  return "Novembre";
	  if(numero.equals("12"))
		  return "Dicembre";
	  return null;
  }
	  
}