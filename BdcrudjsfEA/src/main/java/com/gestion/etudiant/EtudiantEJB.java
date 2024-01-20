/**
 * 
 */
package com.gestion.etudiant;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * 
 */
@RequestScoped
@Named
public class EtudiantEJB {
	private Etudiant etudiant;
	private List<Etudiant> listeEtudiants;
	private Date date;
	private boolean modif=false;
	private static int perId;
	
	
	

	public EtudiantEJB() {
		etudiant = new Etudiant();
	}
	
	
	public Connection connect() {
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdcrudjsf", "root", "");
	            return con;
	        } catch (ClassNotFoundException e) {
	        	e.printStackTrace();
	        	Connection con = null;
	        	return con;  
	        } catch (SQLException e) {
	        	e.printStackTrace();
	        	Connection con = null;
	        	return con;
	        }
		
	} 

	

		
	
	
	public List<Etudiant> afficheEtudiants() {
		listeEtudiants = new ArrayList<Etudiant>();
		
		String req = "select * from etudiant";
		try {
			PreparedStatement ps = connect().prepareStatement(req);
			
			ResultSet res = ps.executeQuery();
			
			while(res.next()) {
				Etudiant pers = new Etudiant();
				pers.setId(res.getInt("id"));
				pers.setNom(res.getString("nom"));
				pers.setPrenom(res.getString("prenom"));
				pers.setDatenaiss(res.getDate("Datenaiss"));
				listeEtudiants.add(pers);
			}
			return listeEtudiants;	
		} catch (SQLException e) {
			
			e.printStackTrace();
        	
        	return listeEtudiants;
		}
			
	}
	
	public void ajoutEtudiant() {
		
		String req = "insert into etudiant (nom,prenom,datenaiss) value (?,?,?) ";
		etudiant.setDatenaiss(convDate(date));
		try {
			
			PreparedStatement ps = connect().prepareStatement(req);
			
			ps.setString(1, etudiant.getNom());
			ps.setString(2, etudiant.getPrenom());
			ps.setDate(3, etudiant.getDatenaiss());
			
			ps.execute();
			
			afficheEtudiants();
			etudiant = new Etudiant();
			date = null;
		}  catch (SQLException e1) {
			
			e1.printStackTrace();
		
	}
	}
	
public void deleteEtudiant(Etudiant per) {
		
		String req = "delete from etudiant where id = ?";
		try {
			
			PreparedStatement ps = connect().prepareStatement(req);
			
			ps.setInt(1, per.getId());
			ps.execute();
			
			
		}  catch (SQLException e1) {
			
			e1.printStackTrace();
		
	}
	}

public void affiche(Etudiant per) {
	perId = per.getId();
	date = per.getDatenaiss();
	etudiant = per;
	modif=true;
	
}

public void modifEtudiant() {
	etudiant.setDatenaiss(convDate(date));
	try {
		String req = "UPDATE etudiant SET nom = ?, prenom = ?, datenaiss = ? WHERE id = ?";
		
		PreparedStatement ps = connect().prepareStatement(req);
		
		ps.setString(1, etudiant.getNom());
		ps.setString(2, etudiant.getPrenom());
		ps.setDate(3, etudiant.getDatenaiss());
		ps.setInt(4, perId);
		
		System.out.println(ps);
		
		ps.executeUpdate();
		
		afficheEtudiants();
		etudiant = new Etudiant();
		date = null;
	}  catch (SQLException e1) {
		
		e1.printStackTrace();
	
}
}
	
	public java.sql.Date convDate(java.util.Date calendarDate) {
		return new java.sql.Date(calendarDate.getTime());
		
	}
	
	
	
	


	public int getPerId() {
		return perId;
	}


	public void setPerId(int perId) {
		EtudiantEJB.perId = perId;
	}


	public boolean isModif() {
		return modif;
	}


	public void setModif(boolean modif) {
		this.modif = modif;
	}


	public List<Etudiant> getPisteEtudiant() {
		return afficheEtudiants();
	}

	public void setPisteEtudiant(List<Etudiant> pisteEtudiant) {
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}


	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}


	public List<Etudiant> getListeEtudiant() {
		return listeEtudiants;
	}


	public void setListeEtudiants(List<Etudiant> listeEtudiants) {
		this.listeEtudiants = listeEtudiants;
	}
	

	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}

	
	

}