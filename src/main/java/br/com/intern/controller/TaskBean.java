package br.com.intern.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.intern.dao.TaskDAO;
import br.com.intern.model.Task;
import br.com.intern.utils.Priority;
import br.com.intern.utils.Status;

@Named
@SessionScoped
public class TaskBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Task task;
	private List<Task> tasks = new ArrayList<Task>();
	

	public Priority[] getPriorities() {
		return Priority.values();
	}
	
	public Status[] getStatus() {
		return Status.values();
	}
	
	public void search(Task task) {
		tasks = new TaskDAO().search(task);
	}

	public String submit() {
		tasks.add(task);
		new TaskDAO().save(task);
		task = new Task();
		return "list?faces-redirect=true";
	}
	
	public String complete(Task task) {
		new TaskDAO().complete(task);
		return "list?faces-redirect=true";
	}
	
	public String completeButton() {
		if(task.getStatus() == "EM_ANDAMENTO") {
			return "false";
		}
		
		return "true";
	}

	public String listTasks() {
		tasks = new TaskDAO().list();
		return "list?faces-redirect=true";
	}

	public void edit(Task task) {
		new TaskDAO().edit(task);
	}
	
	public void delete(Task task) {
		new TaskDAO().delete(task.getId());
		tasks.remove(task);
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
