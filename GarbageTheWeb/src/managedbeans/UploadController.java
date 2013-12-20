package managedbeans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import ejbinterfaces.IFileUploaderEJB;
import entities.Garbage;

@ManagedBean
@RequestScoped
public class UploadController {

	@EJB
	private IFileUploaderEJB fileUploaderEJB;
	
	private UploadedFile uploadedFile;

	public void handleFileUpload(FileUploadEvent event) {
		// System.out.println("INSIDE THE METHOD!!!!");
		uploadedFile = event.getFile();

		String fileName = FilenameUtils.getName(uploadedFile.getFileName());
		String contentType = uploadedFile.getFileName().substring(
				uploadedFile.getFileName().lastIndexOf('.'),
				uploadedFile.getFileName().length());
		contentType = contentType.replace('.',' ');
		contentType = contentType.trim();
		byte[] bytes = uploadedFile.getContents();

		// Now you can save bytes in DB (and also content type?)
		Garbage garbage = new Garbage();
		garbage.setFilename(fileName);
		garbage.setFile(bytes);
		garbage.setDescription("info about the file");
		garbage.setFileType(contentType);
		fileUploaderEJB.uploadGarbage(garbage);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(String.format(
						"File '%s' of type '%s' successfully uploaded!",
						fileName, contentType)));
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
}
