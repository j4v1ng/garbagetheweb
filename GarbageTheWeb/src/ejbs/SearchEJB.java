package ejbs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ejbinterfaces.ISearchEJB;
import entities.Garbage;

/*"Stateless session beans are session beans whose instances have no 
 * conversational state. This means that all bean instances are equivalent 
 * when they are not involved in servicing a client-invoked method. 
 * The term 'stateless' signifies that an instance has no state for a 
 * specific client."*/
@Stateless(name = "ejbs/SearchEJB")
public class SearchEJB implements ISearchEJB {

	@PersistenceContext
	private EntityManager em;	
	
	public List<Garbage> findAllGarbage() {
		Query query = em.createNamedQuery("findAllGarbage");
		List<Garbage> gList = new ArrayList<Garbage>();
		
		for (Object o : query.getResultList()) {
		    Object[] cols = (Object[]) o;
		    Garbage tmpG = new Garbage();
		    tmpG.setFilename(cols[0].toString());
		    tmpG.setDescription(cols[1].toString());
		    tmpG.setUploadDate(cols[2].toString());
		    tmpG.setId((Long) cols[3]);

		    gList.add(tmpG);
		}
		return gList;
	}
}
