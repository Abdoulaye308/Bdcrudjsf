/**
 * 
 */
package com.gestion.etudiant;

import java.sql.Date;

/**
 * 
 */
public class Etudiant {
	private int id;
	private String nom;
	private String prenom;
    private Date datenaiss;
    
    
    
    
    public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Etudiant(int id, String nom, String prenom, Date datenaiss) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.datenaiss = datenaiss;
	}
   
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDatenaiss() {
		return datenaiss;
	}
	public void setDatenaiss(Date datenaiss) {
		this.datenaiss = datenaiss;
	}


}
