package eduardo.example.com.database.Classes;


import java.io.Serializable;

public class Nivel implements Serializable{
    private int id;
    private String nome, descricao,pontuacao;


    //Métodos Construtores, o da db não precisa de parâmetros
    public Nivel(){}

    //Sets
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPontuacao(String pontuacao) {
        this.pontuacao = pontuacao;
    }

    //Gets
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPontuacao() {
        return pontuacao;
    }
}
