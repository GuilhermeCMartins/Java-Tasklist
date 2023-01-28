package br.com.intern.controller;

import br.com.intern.model.Responsable;
import br.com.intern.utils.Level;

public class ResponsableBean {
	
	private Responsable responsable;
	
	
	public Level[] getPriorities() {
		return Level.values();
	}
	
	public String getInfo() {
		return responsable.getName() + "-" + responsable.getLevel();
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}
	
	
}
