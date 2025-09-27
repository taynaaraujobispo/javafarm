package br.com.joaocarloslima;

public class Terreno {
    private Planta planta;
    private int x;
    private int y;

    // Construtor vazio (opcional)
    public Terreno() {
        this.x = -1;
        this.y = -1;
        this.planta = null;
    }

    // Construtor com coordenadas
    public Terreno(int x, int y) {
        this.x = x;
        this.y = y;
        this.planta = null;
    }

    // Planta uma planta no terreno (lança exceção se já estiver ocupado)
    public void plantar(Planta p) {
        if (!estaOcupado()) {
            this.planta = p;
        } else {
            throw new IllegalStateException("O terreno já está ocupado!");
        }
    }

    // Colhe: se planta pronta, armazena no celeiro e libera o terreno
    public void colher(Celeiro celeiro) throws Exception {
        if (planta == null) {
            throw new IllegalStateException("Nenhum produto plantado neste terreno.");
        }
        if (!planta.podeColher()) {
            throw new IllegalStateException("Produto ainda não está pronto para colher.");
        }

        String nome = planta.getNome();
        if ("batata".equals(nome)) {
            celeiro.armazenarBatata();
        } else if ("cenoura".equals(nome)) {
            celeiro.armazenarCenoura();
        } else if ("morango".equals(nome)) {
            celeiro.armazenarMorango();
        } else {
            throw new IllegalStateException("Produto desconhecido: " + nome);
        }

        // libera terreno após armazenamento
        this.planta = null;
    }

    public boolean estaOcupado() {
        return planta != null;
    }

    public Planta getPlanta() {
        return planta;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
