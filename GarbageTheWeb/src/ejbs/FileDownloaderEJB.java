package ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ejbinterfaces.IFileDownloaderEJB;

@Stateless(name = "ejbs/FileDownloaderEJB")
public class FileDownloaderEJB implements IFileDownloaderEJB {
	
	//@PersistenceContext
	private EntityManager em;
		
	public byte[] downloadGarbage(Long id) {
		Query query = em.createNamedQuery("downloadGarbage");
		query.setParameter("idParam", id);
		
		Object o = query.getSingleResult();
		byte[] tmpArray = (byte[]) o; 
		return tmpArray;		
	}
}
