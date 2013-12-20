package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
@NamedQueries({
@NamedQuery(name = "findAllGarbage", query = "SELECT g.filename, g.description, g.uploadDate, g.id FROM Garbage g")/*,
@NamedQuery(name = "downloadGarbage", query = "SELECT g.file FROM Garbage g WHERE g.id :idParam")*/
})
@Entity
public class Garbage implements Serializable {

	@Id
	@GeneratedValue
	@Column(nullable = false)
	private Long id;
	@Column(nullable = false)
	private String filename;
	@Column(nullable = false)
	private String fileType;
	@Column(nullable = false)
	private String uploadDate;
	@Column(nullable = false)
	private String destroyDate;
	@Lob
	@Column(nullable = false)
	private byte[] file;
	@Column(nullable = false)
	private String description;

	public String getFilename() {
		return filename;
	}

	public String getFileType() {
		return fileType;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public String getDestroyDate() {
		return destroyDate;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public void setDestroyDate(String destroyDate) {
		this.destroyDate = destroyDate;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
