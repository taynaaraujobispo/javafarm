package br.com.joaocarloslima;

public class Celeiro {
    private int capacidade;
    private int qtdeBatatas;
    private int qtdeCenouras;
    private int qtdeMorangos;

    public Celeiro(int capacidade) {
        this.capacidade = capacidade;
        this.qtdeBatatas = 2;   // quantidade inicial 
        this.qtdeCenouras = 2;
        this.qtdeMorangos = 2;
    }

    public void armazenarBatata() throws Exception {
    if (getEspacoDisponivel() < 2) // verifica espaço para 2 unidades
        throw new Exception("Celeiro cheio! Não é possível armazenar batatas.");
    qtdeBatatas += 2; // adiciona 2 unidades
}

public void armazenarCenoura() throws Exception {
    if (getEspacoDisponivel() < 2)
        throw new Exception("Celeiro cheio! Não é possível armazenar cenouras.");
    qtdeCenouras += 2;
}

public void armazenarMorango() throws Exception {
    if (getEspacoDisponivel() < 2)
        throw new Exception("Celeiro cheio! Não é possível armazenar morangos.");
    qtdeMorangos += 2;
}


    // Métodos de consumo (se precisar)
    public void consumirBatata() throws Exception {
        if (qtdeBatatas <= 0) throw new Exception("Não há batatas para consumir!");
        qtdeBatatas -= 1;
    }

    public void consumirCenoura() throws Exception {
        if (qtdeCenouras <= 0) throw new Exception("Não há cenouras para consumir!");
        qtdeCenouras -= 1;
    }

    public void consumirMorango() throws Exception {
        if (qtdeMorangos <= 0) throw new Exception("Não há morangos para consumir!");
        qtdeMorangos -= 1;
    }

    public int getEspacoDisponivel() {
        return capacidade - (qtdeBatatas + qtdeCenouras + qtdeMorangos);
    }

    // Retorna ocupação como FRAÇÃO (0.0 - 1.0) compatível com ProgressBar.setProgress(...)
    public double getOcupacao() {
        double total = (double) (qtdeBatatas + qtdeCenouras + qtdeMorangos);
        if (capacidade <= 0) return 0.0;
        return total / (double) capacidade;
    }

    public boolean celeiroCheio() {
        return getEspacoDisponivel() <= 0;
    }

    public int getQtdeBatatas() {
        return qtdeBatatas;
    }

    public int getQtdeCenouras() {
        return qtdeCenouras;
    }

    public int getQtdeMorangos() {
        return qtdeMorangos;
    }

    public int getCapacidade() {
        return capacidade;
    }
}
