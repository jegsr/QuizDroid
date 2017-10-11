package eduardo.example.com.database.Classes;


import java.io.Serializable;
import java.util.ArrayList;

public class Pergunta implements Serializable {
    private final int tamArray = 3;
    private int id;
    private int idNivel;
    private String pergunta;
    private ArrayList<String> respostasErradas = new ArrayList<>(tamArray);
    private String respostaCerta;


    //Sets
    public void setId(int id) {
        this.id = id;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public void setRespostasErradas(ArrayList<String> respostasErradas) {
        this.respostasErradas = respostasErradas;
    }

    public void setRespostaCerta(String respostaCerta) {
        this.respostaCerta = respostaCerta;
    }

    //Gets
    public int getId() {
        return id;
    }

    public int getIdNivel() {
        return idNivel;
    }

    public String getPergunta() {
        return pergunta;
    }

    public ArrayList<String> getRespostasErradas() {
        return respostasErradas;
    }

    public String getRespostaCerta() {
        return respostaCerta;
    }
}
