package br.com.intern.utils;

public enum Level {
	SENIOR("Senior"),
	MEDIUM("Pleno"),
	JUNIOR("Junior"),
	INTERN("Estagiario");
	
	private String label;

    private Level(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
