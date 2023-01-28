package br.com.intern.utils;

public enum Status {
	CONCLUIDO("Concluido"),
    EM_ANDAMENTO("Em andamento");

    private String label;

    private Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
