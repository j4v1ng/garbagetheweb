package ejbs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejbinterfaces.IFileUploaderEJB;
import entities.Garbage;

@Stateless(name = "ejbs/FileUploaderEJB")
public class FileUploaderEJB implements IFileUploaderEJB {

	@PersistenceContext
	private EntityManager em;

	
	public Garbage uploadGarbage(Garbage garbage) {
		try {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");		
		String modifiedDate = dateFormat.format(date);
		Calendar c = Calendar.getInstance();		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		c.setTime(sdf.parse(modifiedDate));
		c.add(Calendar.DATE, 1);
		modifiedDate = sdf.format(c.getTime());
		
		garbage.setUploadDate(dateFormat.format(date));
		garbage.setDestroyDate(modifiedDate);		
		
		//Do data validation
		
		em.persist(garbage);
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		return garbage; 
	}

}
