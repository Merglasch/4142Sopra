package model.modules;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.IDGenerator;

import java.sql.*;

import klassenDB.Modul;

@Stateless
public class ModuleService {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<Modul> Modulsuche(String studienabschluss, String studiengang, String pruefungsordnung, String modulname){
	//TODO Modulsuche
		List<Modul> resultList = null;
		//drei leer		
		if(studienabschluss.equals("Alles auswaehlen")&&studiengang.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")) 
			resultList.add(searchByName(modulname));
		else if(studiengang.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")&&modulname.equals("Alles auswaehlen")) {
			resultList = searchByPruefungsordnung(studienabschluss);			
		}		
		else if(studienabschluss.equals("Alles auswaehlen")&&studiengang.equals("Alles auswaehlen")&&modulname.equals("Alles auswaehlen")) {
			resultList = searchByPruefungsordnung(pruefungsordnung);
		}	
		else if(studienabschluss.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")&&modulname.equals("Alles auswaehlen")) {
			resultList = searchByStudiengang(studiengang);
		} //zwei leer
		else if(studienabschluss.equals("Alles auswaehlen")&&studiengang.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung and m.modulname = :modulname", Modul.class)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.setParameter("modulname", modulname)
				.getResultList();
		}
		else if(studienabschluss.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.studiengang = :studiengang and m.modulname = :modulname", Modul.class)
				.setParameter("studiengang", studiengang)
				.setParameter("modulname", modulname)
				.getResultList();
		}
		else if(studiengang.equals("Alles auswaehlen")&&pruefungsordnung.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.studienabschluss = :studienabschluss and m.modulname = :modulname", Modul.class)
				.setParameter("studienabschluss", studienabschluss)
				.setParameter("modulname", modulname)
				.getResultList();
		}
		else if(studienabschluss.equals("Alles auswaehlen")&&modulname.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.studiengang = :studiengang and mh.pruefungsordnung = :pruefungsordnung", Modul.class)
				.setParameter("studiengang", studiengang)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();
		}
		else if(studienabschluss.equals("Alles auswaehlen")&&modulname.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.studiengang = :studiengang and mh.pruefungsordnung = :pruefungsordnung", Modul.class)
				.setParameter("studiengang", studiengang)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();
		}
		else if(studiengang.equals("Alles auswaehlen")&&modulname.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.studienabschluss = :studienabschluss and mh.pruefungsordnung = :pruefungsordnung", Modul.class)
				.setParameter("studienabschluss", studienabschluss)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();
		}
		else if(pruefungsordnung.equals("Alles auswaehlen")&&modulname.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.studienabschluss = :studienabschluss and mh.studiengang = :studiengang", Modul.class)
				.setParameter("studienabschluss", studienabschluss)
				.setParameter("studiengang", studiengang)
				.getResultList();
		} //eins leer
		else if(studienabschluss.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.studiengang = :studiengang and mh.pruefungsordnung = :pruefungsordnung"
				+"and m.modulname = :modulname", Modul.class)
				.setParameter("studiengang", studiengang)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.setParameter("modulname", modulname)
				.getResultList();
		}
		else if(pruefungsordnung.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.studiengang = :studiengang and mh.studienabschluss = :studienabschluss"
				+"and m.modulname = :modulname", Modul.class)
				.setParameter("studienabschluss", studienabschluss)
				.setParameter("studiengang", studiengang)
				.setParameter("modulname", modulname)
				.getResultList();
		}
		else if(modulname.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.studiengang = :studiengang and mh.studienabschluss = :studienabschluss"
				+"and mh.pruefungsordnung = :pruefungsordnung", Modul.class)
				.setParameter("studienabschluss", studienabschluss)
				.setParameter("studiengang", studiengang)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();
		}
		else if(studiengang.equals("Alles auswaehlen")) {
			em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung and mh.studienabschluss = :studienabschluss"
				+"and m.modulname = :modulname", Modul.class)
				.setParameter("studienabschluss", studienabschluss)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.setParameter("modulname", modulname)
				.getResultList();
		}
		else {
			resultList = getAllModules();
		}
		return resultList;
	}
	
	public boolean createModule(Modul m){
		System.out.println("METHODE:  createModul");
		System.out.println("Uid :"+m.getUid());
		System.out.println("Freigegeben :"+m.getFreigegeben());
		System.out.println("Zeitstempel :"+m.getZeitstempel());
		System.out.println("ModulId :"+m.getModulid());
		System.out.println("ModulNsame :"+m.getModulname());
		List<Modul> resultList = em.createQuery("SELECT m FROM Modul m", Modul.class).getResultList();
		boolean moduleExists = false;
		for(Modul n : resultList){
			if (m.getModulname().equals(n.getModulname()))
				moduleExists = true;
		}
		if (moduleExists==false){
//			m.setModulid(IDGenerator.getID());
			
			int modulid = em.createQuery("SELECT MAX(u.modulid) FROM Modul u", Integer.class).getSingleResult().intValue();
			m.setModulid(modulid+1);
			
			System.out.println("**Neu Generierte ModulId :"+m.getModulid());
			em.persist(m);				
			System.out.println("Modul Exist == "+ !moduleExists);
			return !moduleExists;
		}	
		else 
			return !moduleExists;
	}
	
	public void deleteModule(List<Modul> moduleList){
		for(Modul m : moduleList){
			em.remove(em.merge(m));
		}
	}
	
	public void updateModule(Modul m){
		em.merge(m);
	}
	
	public List<Modul> searchByStudiengang(String studiengang){
		
		return em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.studiengang = :studiengang ", Modul.class)
					.setParameter("studiengang", studiengang)
					.getResultList();		
	}
	
	public List<Modul> searchByPruefungsordnung(String pruefungsordnung){
		
		return em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.pruefungsordnung = :pruefungsordnung ", Modul.class)
				.setParameter("pruefungsordnung", pruefungsordnung)
				.getResultList();		
	}
	
	public List<Modul> searchByAbschluss(String abschluss){
		
		return em.createQuery("SELECT m FROM Modul m JOIN m.Modulhandbuch mh WHERE mh.abschluss = :abschluss ", Modul.class)
				.setParameter("abschluss", abschluss)
				.getResultList();		
	}
	
	public Modul searchByName(String name){
		
		return em.createQuery("SELECT m FROM Modul m WHERE m.modulname = :name", Modul.class)
				.setParameter("name", name)
				.getSingleResult();		
	}
	
	public List<Modul> getAllModules(){
		return em.createQuery("Select m FROM Modul m", Modul.class).getResultList();
	}	
}
