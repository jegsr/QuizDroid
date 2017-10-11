package eduardo.example.com.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MyDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 17;


    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tblNiveis(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(50) NOT NULL, descricao VARCHAR(150) NOT NULL, pontuacao VARCHAR(10) NOT NULL)");
        db.execSQL("CREATE TABLE tblPerguntas(id INTEGER PRIMARY KEY AUTOINCREMENT, idNivel INTEGER NOT NULL, pergunta VARCHAR(150) NOT NULL, opcaoCerta VARCHAR(150) NOT NULL, opcaoErrada1 VARCHAR(150) NOT NULL, opcaoErrada2 VARCHAR(150) NOT NULL, opcaoErrada3 VARCHAR(150) NOT NULL )");
        db.execSQL("CREATE TABLE tblEstatistica(id INTEGER PRIMARY KEY AUTOINCREMENT,nomeNivel VARCHAR(50) NOT NULL, pontuacaoFinal INTEGER NOT NULL, nomeUtilizador VARCHAR(50) NOT NULL)");

        //Criação de 2 Modos de Jogo
        db.execSQL("INSERT INTO tblNiveis(nome,descricao,pontuacao) VALUES ('Cultura Geral','Modo de jogo acerca de cultura geral','5')");
        db.execSQL("INSERT INTO tblNiveis(nome,descricao,pontuacao) VALUES ('Desporto','Modo de jogo acerca do mundo do desporto','2')");

        //Criação de Perguntas

        //Modo de Jogo 1
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('1','Qual é a fórmula molecular da água?','H2O','CO2','O2','O3')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('1','Fala-se em Cuba e pensamos no país, mas é também nome de uma localidade?','No Alentejo','No Ribetejo','No Algarve','No Douro Litoral')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('1','Rehab é um single de que cantora?','Amy Winehouse','Adele','Lennox','Sinéad O Connor')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('1','Portugal não pertence...','À OPEP','À CPLP','À UE','À ONU')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('1','Que parte da mecânica de um automóvel mantém a bateria carregada enquanto o motor trabalha?','Alternador','Radiador','Embraiagem','Depósito')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('1','Na mitologia nórdica que nome tem o martelo usado por Thor, Deus do Trovão?','Mijollnir','Asgard','Bifrost','Valhalla')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('1','O historiador e criptanalista Tomás Noronha é o protagonista de vários livros de que escritor Português?','José R. dos Santos','Miguel Sousa Tavares','Miguel Rocha','Valter  Hugo Mãe')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('1','Destas óperas qual não é da autoria de Stravinsky?','Louise','Pássaro de fogo','Sagração da primavera','Petrouchka')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('1','Que nome se dá ao instante em que o sol cruza o equador celeste?','Equinócio','Solstício','Zénite','Periélio')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('1','Onde nasceu a rainha D.Maria II?','Brasil','Portugal','Alemanha','Espanha')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('1','Qual destes países do continente africano não possuí ligação a qualquer oceano?','Chade','Gabão','Quénia','Somália')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('1','O filme ,Império do Sol, de Steven Spielberg, é uma adaptação de um livro de que autor?','J.G. Ballard','Somerset Maugham','Edgar Rice Burroughs','Elmore Leonard')");


        //Modo de Jogo 2
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('2','Quem ganhou o primeiro Mundial de Futebol?','Uruguai','Argentina','Jugoslávia','EUA')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('2',' Em que ano a A.D.F.( Associação Desportiva de Fafe) esteve na primeira divisão de futebol portuguesa?','88/89','80/81','87/88','91/92')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('2','Quem venceu o Mundialito de Futebol de praia em 2008?','Portugal','Brasil','Itália','Espanha')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('2','Quem venceu o campeonato de basquetebol em 2007/2008?','Ovarense','FCP','SLB','Queluz')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('2','Quem ganhou o Mundial de 2009 de andebol?','França','Croácia','República Checa','Polónia')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('2','Na CAN 2015, o novo campeão africano de futebol é...','Costa do Marfim','Senegal','Camarões','Gana')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('2','Marcado pelo deflategate o 49º Superbowl é ganho pelos...','New England Patriots','Seattle Seahawks','Denver Broncos','Baltimore Ravens')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('2','PSG afasta o Chelsea da UCL. Quem marcou o golo decisivo?','Thiago Silva','Cavani','David Luiz','Lavezzi')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('2','O único Grand Slam que escapou a Djokovic em 2015 foi ganho por...','Stan Wawrinka','Andy Murray','Rafael Nadal','Roger Federer')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('2','Que seleção conquistou o mundial de futebol de Sub-20 em 2015?','Sérvia','Mali','Senegal','Brasil')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('2','Que seleção conquistou o Mundial de futebol feminino em 2015?','EUA','Brasil','Japão','Alemanha')");
        db.execSQL("INSERT INTO tblPerguntas(idNivel,pergunta,opcaoCerta,opcaoErrada1,opcaoErrada2,opcaoErrada3) VALUES ('2','Em 2015 Lewis Hamilton foi campeão pela...','Terceira vez','Segunda vez','Quarta vez','Quinta vez')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE tblPerguntas");
        db.execSQL("DROP TABLE tblNiveis");
        db.execSQL("DROP TABLE tblEstatistica");
        onCreate(db);
    }
}
