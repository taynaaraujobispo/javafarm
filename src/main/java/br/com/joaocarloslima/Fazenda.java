package br.com.joaocarloslima;

public class Fazenda {

    private Terreno[][] terrenos;
    private Celeiro celeiro;
    private final int LINHAS = 13;
    private final int COLUNAS = 13;

    // Construtor padrão: 13x13 e celeiro com capacidade 100
    public Fazenda() {
        this.terrenos = new Terreno[LINHAS][COLUNAS];
        for (int i = 0; i < LINHAS; i++) {
            for (int j = 0; j < COLUNAS; j++) {
                terrenos[i][j] = new Terreno(i, j);
            }
        }
        this.celeiro = new Celeiro(100);
    }

    // Construtor flexível (caso queira mudar tamanhos)
    public Fazenda(int linhas, int colunas, int capacidadeCeleiro) {
        this.terrenos = new Terreno[linhas][colunas];
        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                terrenos[i][j] = new Terreno(i, j);
            }
        }
        this.celeiro = new Celeiro(capacidadeCeleiro);
    }

    public Terreno getTerreno(int x, int y) {
        if (x < 0 || y < 0 || x >= terrenos.length || y >= terrenos[0].length) return null;
        return terrenos[x][y];
    }

    // Planta uma Planta no terreno 
    public boolean plantar(int x, int y, Planta planta) throws Exception {
    Terreno t = getTerreno(x, y);
    if (t != null && !t.estaOcupado()) {
        Celeiro c = getCeleiro();
        String nome = planta.getNome();

        // verifica estoque e consome
        if ("batata".equals(nome) && c.getQtdeBatatas() > 0) {
            c.consumirBatata();
        } else if ("cenoura".equals(nome) && c.getQtdeCenouras() > 0) {
            c.consumirCenoura();
        } else if ("morango".equals(nome) && c.getQtdeMorangos() > 0) {
            c.consumirMorango();
        } else {
            // sem estoque
            throw new Exception("Não há " + nome + " disponível para plantar!");
        }

        t.plantar(planta);
        return true;
    }
    return false;
}


    // Colhe e tenta armazenar no celeiro
    public boolean colher(int x, int y) {
        Terreno t = getTerreno(x, y);
        if (t != null && t.estaOcupado()) {
            try {
                t.colher(celeiro);
                return true;
            } catch (Exception e) {
                // pode propagar ou tratar; aqui retorna false para o caller mostrar erro
                return false;
            }
        }
        return false;
    }

    public Celeiro getCeleiro() {
        return celeiro;
    }
}
