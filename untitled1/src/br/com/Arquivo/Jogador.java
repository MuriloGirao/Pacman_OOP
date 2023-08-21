package br.com.Arquivo;


/**
 * Classe que representa um jogador.
 */
public class Jogador {
    private String nome;
    private int pontuacao;
    private String conclusao;
    private int posicao;

    public Jogador(String nome, int pontuacao, String conclusao) {
        this.nome = nome;
        this.pontuacao = pontuacao;
        this.conclusao = conclusao;
    }

    public String getNome() {
        return nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public String getConclusao() {
        return conclusao;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
}
