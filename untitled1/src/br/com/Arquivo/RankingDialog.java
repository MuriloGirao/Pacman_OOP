package br.com.Arquivo;


import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;

public class RankingDialog extends JDialog {
    private PontuacaoJogadores pontuacaoJogadores;

    public RankingDialog(PontuacaoJogadores pontuacaoJogadores) {
        this.pontuacaoJogadores = pontuacaoJogadores;

        setTitle("Ranking");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        // Carrega o ranking
        //   pontuacaoJogadores.carregarRanking();

        // Obtém os jogadores e os imprime no JTextArea
        StringBuilder rankingText = new StringBuilder();
        //
        // inicio com posicao 1
        int posicao=1;
        for (Jogador jogador : pontuacaoJogadores.getJogadores()) {
            rankingText.append("Posição: ").append(posicao).append(", Nome: ").append(jogador.getNome())
                    .append(", Pontuação: ").append(jogador.getPontuacao())
                    .append(", Conclusão: ").append(jogador.getConclusao())
                    .append("\n");
            //adiciono posicao nova a cada laço
            posicao++;
        }
        textArea.setText(rankingText.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        PontuacaoJogadores pontuacaoJogadores = new PontuacaoJogadores();
        RankingDialog dialog = new RankingDialog(pontuacaoJogadores);
        dialog.setVisible(true);
    }
}
