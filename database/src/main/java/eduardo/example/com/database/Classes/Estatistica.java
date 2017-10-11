package eduardo.example.com.database.Classes;

import java.io.Serializable;

public class Estatistica implements Serializable{

    private int id;
    private int pontuacaoFinal;
    private String nomeNivel;
    private String nomeUtilizador;

    //Sets
    public void setId(int id) {
        this.id = id;
    }

    public void setPontuacaoFinal(int pontuacaoFinal) {
        this.pontuacaoFinal = pontuacaoFinal;
    }

    public void setNomeNivel(String nomeNivel) {
        this.nomeNivel = nomeNivel;
    }

    public void setNomeUtilizador(String nomeUtilizador) {
        this.nomeUtilizador = nomeUtilizador;
    }

    //Gets
    public int getId() {
        return id;
    }

    public int getPontuacaoFinal() {
        return pontuacaoFinal;
    }

    public String getNomeNivel() {
        return nomeNivel;
    }

    public String getNomeUtilizador() {
        return nomeUtilizador;
    }
}
