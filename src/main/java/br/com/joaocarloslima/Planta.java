package br.com.joaocarloslima;

public abstract class Planta {
    protected int tamanho;
    protected int tempoDeVida;
    protected int tempoDeCrescimento;
    protected String nome; // usado para montar a imagem e identificar

    public Planta(String nome, int tempoDeCrescimento) {
        this.tamanho = 1;
        this.tempoDeVida = 1;
        this.tempoDeCrescimento = tempoDeCrescimento;
        this.nome = nome;
    }

    public void crescer() {
        tempoDeVida++;
        if (tempoDeVida % tempoDeCrescimento == 0 && tamanho < 4) {
            tamanho++;
        }
    }

    public boolean podeColher() {
        return tamanho == 4;
    }

    public String getImagem() {
        // o arquivo deve existir em resources/br/com/joaocarloslima/images/
        return "images/" + nome + tamanho + ".png";
    }

    public String getNome() {
        return nome;
    }
}
