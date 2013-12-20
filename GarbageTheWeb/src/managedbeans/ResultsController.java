package managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import ejbinterfaces.IFileDownloaderEJB;
import ejbinterfaces.ISearchEJB;
import entities.Garbage;

@ManagedBean
@ViewScoped
// TODO find what this annotation does
public class ResultsController implements Serializable {

	@EJB
	private ISearchEJB searchEJB;

	@EJB
	private IFileDownloaderEJB fileDownloaderEJB;

	private Garbage garbage;

	private List<Garbage> allGarbage;

	private Garbage selectedGarbage;

	public void startDownload(Garbage garbage) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			if (garbage.getFileType().equals("doc")) {
				externalContext.setResponseHeader("Content-Type",
						"application/msword" + garbage.getFileType());
			}
			else if(garbage.getFileType().equals("docx")) {
				externalContext.setResponseHeader("Content-Type",
						"application/vnd.openxmlformats-officedocument.wordprocessingml.document" + garbage.getFileType());
			}
			else if(garbage.getFileType().equals("docx")) {
				
			}

			externalContext.setResponseHeader("Content-Length",
					garbage.getFile().length + "");

			externalContext.setResponseHeader("Content-Disposition",
					"attachment;filename=\"" + garbage.getFilename() + "\"");
			externalContext.getResponseOutputStream().write(
					fileDownloaderEJB.downloadGarbage(garbage.getId()));

			facesContext.responseComplete();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public List<Garbage> getAllGarbage() {

		allGarbage = new ArrayList<Garbage>();
		for (Garbage g : searchEJB.findAllGarbage()) {
			allGarbage.add(g);
		}
		return allGarbage;
	}

	public void setAllGarbage(List<Garbage> allGarbage) {
		this.allGarbage = allGarbage;
	}

	public Garbage getGarbage() {
		return garbage;
	}

	public void setGarbage(Garbage garbage) {
		this.garbage = garbage;
	}

	public void onRowSelect(SelectEvent event) {
		garbage = (Garbage) event.getObject();
	}

	public Garbage getSelectedGarbage() {
		return selectedGarbage;
	}

	public void setSelectedGarbage(Garbage selectedGarbage) {
		this.selectedGarbage = selectedGarbage;
	}

	public void rowIsSelected(SelectEvent event) {
		System.out.println("A ROW WAS SELECTED!");
	}

}
