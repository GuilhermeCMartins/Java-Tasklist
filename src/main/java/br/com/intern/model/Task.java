package br.com.intern.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String title;
	private String description;
	@NotNull(message = "Precisa haver uma data.")
	@Future(message = "A data precisa estar no futuro.")
	private Date deadline;
	private String priority;
	private String responsable;
	private Date startDate = new Date();
	private String status;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String string) {
		this.priority = string;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String string) {
		this.status = string;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
