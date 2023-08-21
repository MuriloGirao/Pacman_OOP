package br.com.Arquivo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PontuacaoJogadores {
    private static final String ARQUIVO_RANKING = "ranking.txt";
    private static final int TAMANHO_MAXIMO = 10;

    private List<Jogador> jogadores;

    /**
     * Construtor da classe PontuacaoJogadores.
     * Inicializa a lista de jogadores.
     */
    public PontuacaoJogadores() {
        jogadores = new ArrayList<>();
    }

    /**
     * Adiciona um novo jogador ao ranking.
     * Se o ranking já estiver completo, o jogador com menor pontuação será substituído pelo novo jogador.
     * @param nome o nome do jogador
     * @param pontuacao a pontuação do jogador
     * @param conclusao a conclusão do jogador
     */
    public void adicionarJogador(String nome, int pontuacao, String conclusao) {
        //carrego o ranking e adiciono a colecao
        carregarRanking();
        //adiciono o novo jogador na colecao e corrijo tamanho da colecao retirando pontuacao mais baixa
        Jogador novoJogador = new Jogador(nome, pontuacao, conclusao);
        if (jogadores.size() < TAMANHO_MAXIMO) {
            jogadores.add(novoJogador);
        } else {
            Jogador piorJogador = Collections.min(jogadores, Comparator.comparingInt(Jogador::getPontuacao));
            if (pontuacao > piorJogador.getPontuacao()) {
                jogadores.remove(piorJogador);
                jogadores.add(novoJogador);
            }
        }
        //ordena o ranking por pontuacao
        ordenarPorPontuacao();
        //grava no ranking
        gravarRanking();
        //exibir novo ranking
        RankingDialog exiberesultado = new RankingDialog(this);
        exiberesultado.setVisible(true);
    }

    /**
     * Ordena a lista de jogadores por pontuação em ordem decrescente.
     */
    private void ordenarPorPontuacao() {
        Collections.sort(jogadores, Comparator.comparingInt(Jogador::getPontuacao).reversed());
    }

    /**
     * Grava o ranking atual em um arquivo.
     */
    public void gravarRanking() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_RANKING))) {
            for (Jogador jogador : jogadores) {
                writer.write(jogador.getNome() + "," + jogador.getPontuacao() + "," + jogador.getConclusao());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carrega o ranking a partir do arquivo.
     */
    public void carregarRanking() {
        boolean exists = (new File(ARQUIVO_RANKING)).exists();
        if (exists) {
            try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_RANKING))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] campos = linha.split(",");
                    if (campos.length == 3) {
                        String nome = campos[0];
                        int pontuacao = Integer.parseInt(campos[1]);
                        String conclusao = campos[2];
                        Jogador jogador = new Jogador(nome, pontuacao, conclusao);
                        jogadores.add(jogador);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
//Se não existe, cria o arquivo...
            File file = new File(ARQUIVO_RANKING);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }

    /**
     * Imprime os jogadores do ranking.
     */
    public void imprimirJogadores() {
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.getPosicao() + ". " +
                    "Nome: " + jogador.getNome() +
                    ", Pontuação: " + jogador.getPontuacao() +
                    ", Conclusão: " + jogador.getConclusao());
        }
    }

    /**
     * Atribui a posição de cada jogador no ranking.
     */
    private void atribuirPosicoes() {
        //inicando na posicao 1 int i = 1
        for (int i = 1; i < jogadores.size(); i++) {
            jogadores.get(i).setPosicao(i + 1);
        }
    }

    Iterable<Jogador> getJogadores() {
        return jogadores;
    }
}

