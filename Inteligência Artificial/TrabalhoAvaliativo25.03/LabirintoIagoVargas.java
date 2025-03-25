/*
 * Implementação do problema do Labirinto com Obstáculos
 * Autor: Iago Vargas
 * Disciplina: Inteligência Artificial 
 * Data: 25.03.2025
 * iagovargas.com
 * Este código implementa um labirinto com duas entradas e uma saída,
 * utilizando algoritmos de busca em largura e profundidade.
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import busca.Heuristica;
import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.Estado;
import busca.MostraStatusConsole;
import busca.Nodo;
import javax.swing.JOptionPane;

public class JogoLabirinto implements Estado, Heuristica {
    
    // Descrição do problema -  Iago Vargas
    @Override
    public String getDescricao() {
        return "Implementação do jogo do labirinto com obstáculos\n" +
               "Desenvolvido por Iago Vargas\n\n" +
               "O labirinto consiste em uma matriz quadrada com:\n" +
               "- Duas entradas marcadas como E1 e E2\n" +
               "- Uma saída marcada como S\n" +
               "- Obstáculos marcados com @\n" +
               "O objetivo é encontrar o caminho mais curto da entrada à saída.";
    }

    // Matriz representando o labirinto
    final char lab[][]; 
    // Posições das entradas - modificado por Iago Vargas
    int linhaE1, colunaE1; 
    int linhaE2, colunaE2;
    // Posição da saída
    int linhaSaida, colunaSaida;
    final String operacao; 

    // Método para copiar a matriz
    char [][]copiarMatriz(char original[][]) {
        char copia[][] = new char[original.length][original.length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, copia[i], 0, original.length);
        }
        return copia;
    }
    
    // Construtor principal 
    public JogoLabirinto(char m[][], int linhaE1, int colunaE1, int linhaE2, int colunaE2,
        int linhaSaida, int colunaSaida, String descricao) {
        this.lab = m; 
        this.linhaE1 = linhaE1;
        this.colunaE1 = colunaE1;
        this.linhaE2 = linhaE2;
        this.colunaE2 = colunaE2;
        this.linhaSaida = linhaSaida;
        this.colunaSaida = colunaSaida;
        this.operacao = descricao;
    }

    // Construtor para gerar labirinto aleatório 
    public JogoLabirinto(int tamanho, String descricao, int percentualObstaculos) {
        this.lab = new char[tamanho][tamanho];
        this.operacao = descricao;
        
        int numObstaculos = (tamanho*tamanho)* percentualObstaculos/100;
        System.out.println("Número de obstáculos gerados: " + numObstaculos);
        
        Random aleatorio = new Random();

        // Gerando posições aleatórias para as entradas e saída
        int posE1 = aleatorio.nextInt(tamanho * tamanho); 
        int posE2;
        do {
            posE2 = aleatorio.nextInt(tamanho * tamanho);
        } while (posE1 == posE2);
        
        int posSaida;
        do {
            posSaida = aleatorio.nextInt(tamanho * tamanho);
        } while (posSaida == posE1 || posSaida == posE2);

        // Preenchendo o labirinto
        int contador = 0;
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (contador == posE1) {
                    this.lab[i][j] = '¹';
                    this.linhaE1 = i;
                    this.colunaE1 = j;
                } else if (contador == posE2) {
                    this.lab[i][j] = '²';
                    this.linhaE2 = i;
                    this.colunaE2 = j;
                } else if (contador == posSaida) {
                    this.lab[i][j] = 'S';
                    this.linhaSaida = i;
                    this.colunaSaida = j;
                } else if (numObstaculos > 0 && aleatorio.nextInt(3) == 1) {
                    numObstaculos--;
                    this.lab[i][j] = '@';
                } else {
                    this.lab[i][j] = 'O';
                }
                contador++;
            }
        }
    }

    // Verifica se chegou ao objetivo 
    @Override
    public boolean ehMeta() {
        return (this.linhaE1 == this.linhaSaida && this.colunaE1 == this.colunaSaida) ||
               (this.linhaE2 == this.linhaSaida && this.colunaE2 == this.colunaSaida);
    }

    @Override
    public int custo() {
        return 1;
    }

    // Heurística - distância de Manhattan
    @Override 
    public int h() {
        int distanciaE1 = Math.abs(linhaE1 - linhaSaida) + Math.abs(colunaE1 - colunaSaida);
        int distanciaE2 = Math.abs(linhaE2 - linhaSaida) + Math.abs(colunaE2 - colunaSaida);
        return Math.min(distanciaE1, distanciaE2);
    }

    // Gera os sucessores - método principal por Iago Vargas
    @Override
    public List<Estado> sucessores() {
        List<Estado> proximos = new LinkedList<Estado>();

        moverE1(proximos);
        moverE2(proximos);
        
        return proximos;
    }

    // Movimentos para a entrada 1 
    private void moverE1(List<Estado> proximos) {
        // Movimento para cima
        if (this.linhaE1 > 0 && this.lab[this.linhaE1 - 1][this.colunaE1] != '@') {
            char temp[][] = copiarMatriz(this.lab);
            temp[this.linhaE1][this.colunaE1] = 'O';
            temp[this.linhaE1 - 1][this.colunaE1] = '¹';
            
            JogoLabirinto novo = new JogoLabirinto(temp, this.linhaE1 - 1, this.colunaE1, 
                                     this.linhaE2, this.colunaE2,
                                     this.linhaSaida, this.colunaSaida,
                                     "Movimento E1 para cima");
            if (!proximos.contains(novo)) proximos.add(novo);
        }

        // Movimento para baixo
        if (this.linhaE1 < this.lab.length-1 && this.lab[this.linhaE1 + 1][this.colunaE1] != '@') {
            char temp[][] = copiarMatriz(this.lab);
            temp[this.linhaE1][this.colunaE1] = 'O';
            temp[this.linhaE1 + 1][this.colunaE1] = '¹';
            
            JogoLabirinto novo = new JogoLabirinto(temp, this.linhaE1 + 1, this.colunaE1, 
                                     this.linhaE2, this.colunaE2,
                                     this.linhaSaida, this.colunaSaida,
                                     "Movimento E1 para baixo");
            if (!proximos.contains(novo)) proximos.add(novo);
        }

        // Movimento para esquerda
        if (this.colunaE1 > 0 && this.lab[this.linhaE1][this.colunaE1 - 1] != '@') {
            char temp[][] = copiarMatriz(this.lab);
            temp[this.linhaE1][this.colunaE1] = 'O';
            temp[this.linhaE1][this.colunaE1 - 1] = '¹';
            
            JogoLabirinto novo = new JogoLabirinto(temp, this.linhaE1, this.colunaE1 - 1, 
                                     this.linhaE2, this.colunaE2,
                                     this.linhaSaida, this.colunaSaida,
                                     "Movimento E1 para esquerda");
            if (!proximos.contains(novo)) proximos.add(novo);
        }

        // Movimento para direita
        if (this.colunaE1 < this.lab.length-1 && this.lab[this.linhaE1][this.colunaE1 + 1] != '@') {
            char temp[][] = copiarMatriz(this.lab);
            temp[this.linhaE1][this.colunaE1] = 'O';
            temp[this.linhaE1][this.colunaE1 + 1] = '¹';
            
            JogoLabirinto novo = new JogoLabirinto(temp, this.linhaE1, this.colunaE1 + 1, 
                                     this.linhaE2, this.colunaE2,
                                     this.linhaSaida, this.colunaSaida,
                                     "Movimento E1 para direita");
            if (!proximos.contains(novo)) proximos.add(novo);
        }
    }

    // Movimentos para a entrada 2 
    private void moverE2(List<Estado> proximos) {
        // Movimento para cima
        if (this.linhaE2 > 0 && this.lab[this.linhaE2 - 1][this.colunaE2] != '@') {
            char temp[][] = copiarMatriz(this.lab);
            temp[this.linhaE2][this.colunaE2] = 'O';
            temp[this.linhaE2 - 1][this.colunaE2] = '²';
            
            JogoLabirinto novo = new JogoLabirinto(temp, this.linhaE1, this.colunaE1, 
                                     this.linhaE2 - 1, this.colunaE2,
                                     this.linhaSaida, this.colunaSaida,
                                     "Movimento E2 para cima");
            if (!proximos.contains(novo)) proximos.add(novo);
        }

        // Movimento para baixo
        if (this.linhaE2 < this.lab.length-1 && this.lab[this.linhaE2 + 1][this.colunaE2] != '@') {
            char temp[][] = copiarMatriz(this.lab);
            temp[this.linhaE2][this.colunaE2] = 'O';
            temp[this.linhaE2 + 1][this.colunaE2] = '²';
            
            JogoLabirinto novo = new JogoLabirinto(temp, this.linhaE1, this.colunaE1, 
                                     this.linhaE2 + 1, this.colunaE2,
                                     this.linhaSaida, this.colunaSaida,
                                     "Movimento E2 para baixo");
            if (!proximos.contains(novo)) proximos.add(novo);
        }

        // Movimento para esquerda
        if (this.colunaE2 > 0 && this.lab[this.linhaE2][this.colunaE2 - 1] != '@') {
            char temp[][] = copiarMatriz(this.lab);
            temp[this.linhaE2][this.colunaE2] = 'O';
            temp[this.linhaE2][this.colunaE2 - 1] = '²';
            
            JogoLabirinto novo = new JogoLabirinto(temp, this.linhaE1, this.colunaE1, 
                                     this.linhaE2, this.colunaE2 - 1,
                                     this.linhaSaida, this.colunaSaida,
                                     "Movimento E2 para esquerda");
            if (!proximos.contains(novo)) proximos.add(novo);
        }

        // Movimento para direita
        if (this.colunaE2 < this.lab.length-1 && this.lab[this.linhaE2][this.colunaE2 + 1] != '@') {
            char temp[][] = copiarMatriz(this.lab);
            temp[this.linhaE2][this.colunaE2] = 'O';
            temp[this.linhaE2][this.colunaE2 + 1] = '²';
            
            JogoLabirinto novo = new JogoLabirinto(temp, this.linhaE1, this.colunaE1, 
                                     this.linhaE2, this.colunaE2 + 1,
                                     this.linhaSaida, this.colunaSaida,
                                     "Movimento E2 para direita");
            if (!proximos.contains(novo)) proximos.add(novo);
        }
    }
    
    // Método equals 
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JogoLabirinto) {
            JogoLabirinto e = (JogoLabirinto) obj;
            for (int i = 0; i < e.lab.length; i++) {
                for (int j = 0; j < e.lab.length; j++) {
                    if (e.lab[i][j] != this.lab[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        StringBuilder estado = new StringBuilder();
        
        for (int i = 0; i < this.lab.length; i++) {
            for (int j = 0; j < this.lab.length; j++) {
                estado.append(this.lab[i][j]);
            }
        }
        return estado.toString().hashCode();
    }

    // Representação visual do labirinto
    @Override
    public String toString() {
        final String RESET = "\u001B[0m";
        final String VERMELHO = "\u001B[31m";
        final String AMARELO = "\u001B[33m";
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab.length; j++) {
                char c = this.lab[i][j];
                switch (c) {
                    case '¹':
                        sb.append(AMARELO).append("E1").append(RESET);
                        break;
                    case '²':
                        sb.append(AMARELO).append("E2").append(RESET);
                        break;
                    case 'S':
                        sb.append(VERMELHO).append("S").append(RESET).append(" ");
                        break;
                    default:
                        sb.append(c).append(" ");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.append("\nPosição Entrada E1: (").append(linhaE1).append(",").append(colunaE1).append(")");
        sb.append("\nPosição Entrada E2: (").append(linhaE2).append(",").append(colunaE2).append(")");
        sb.append("\nPosição Saída S: (").append(linhaSaida).append(",").append(colunaSaida).append(")\n");
        return "\n" + operacao + "\n" + sb.toString();
    }

    // Método principal 
    public static void main(String[] args) {
        int tamanho;
        int percentualObstaculos;
        
        try {
            tamanho = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o tamanho do labirinto:"));
            percentualObstaculos = Integer.parseInt(JOptionPane.showInputDialog(null,"Digite o percentual de obstáculos (0-100):"));
            
            final JogoLabirinto inicio = new JogoLabirinto(tamanho, "Estado inicial", percentualObstaculos);
            
            System.out.println("Labirinto inicial gerado por Iago Vargas:");
            System.out.println(inicio);
            
            final Nodo[] resultadoProf = {null};
            final Nodo[] resultadoLarg = {null};
            final long[] tempoProf = {0};
            final long[] tempoLarg = {0};
            
            ExecutorService exec = Executors.newFixedThreadPool(2);
            
            // Thread para busca em profundidade
            exec.execute(() -> {
                long inicioTempo = System.currentTimeMillis();
                System.out.println("\nIniciando busca em PROFUNDIDADE - por Iago Vargas");
                resultadoProf[0] = new BuscaProfundidade(new MostraStatusConsole()).busca(inicio);
                tempoProf[0] = System.currentTimeMillis() - inicioTempo;
                
                if (resultadoProf[0] == null) {
                    System.out.println("Busca em profundidade não encontrou solução!");
                } else {
                    System.out.println("Solução encontrada (profundidade):\n" + resultadoProf[0].montaCaminho() + "\n\n");
                }
            });
            
            // Thread para busca em largura
            exec.execute(() -> {
                long inicioTempo = System.currentTimeMillis();
                System.out.println("\nIniciando busca em LARGURA - por Iago Vargas");
                resultadoLarg[0] = new BuscaLargura(new MostraStatusConsole()).busca(inicio);
                tempoLarg[0] = System.currentTimeMillis() - inicioTempo;
                
                if (resultadoLarg[0] == null) {
                    System.out.println("Busca em largura não encontrou solução!");
                } else {
                    System.out.println("Solução encontrada (largura):\n" + resultadoLarg[0].montaCaminho() + "\n\n");
                }
            });
            
            exec.shutdown();
            
            while (!exec.isTerminated()) {
                Thread.sleep(100);
            }
            
            // Análise de desempenho 
            System.out.println("\nRESULTADOS DE DESEMPENHO:");
            System.out.println("Tempo Profundidade: " + tempoProf[0] + "ms");
            System.out.println("Tempo Largura: " + tempoLarg[0] + "ms");
            
            String conclusao;
            if (resultadoProf[0] == null && resultadoLarg[0] == null) {
                conclusao = "Nenhum algoritmo encontrou solução.";
            } else if (resultadoProf[0] == null) {
                conclusao = "Largura foi mais eficiente (Profundidade falhou).";
            } else if (resultadoLarg[0] == null) {
                conclusao = "Profundidade foi mais eficiente (Largura falhou).";
            } else {
                if (tempoProf[0] < tempoLarg[0]) {
                    conclusao = "Profundidade foi mais rápida.";
                } else if (tempoLarg[0] < tempoProf[0]) {
                    conclusao = "Largura foi mais rápida.";
                } else {
                    conclusao = "Empate no tempo de execução.";
                }
            }
            
            System.out.println("\nConclusão: " + conclusao);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro encontrado por Iago Vargas: " + e.getMessage());
        }
    }
}
