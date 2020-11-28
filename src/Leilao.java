
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter

public class Leilao {

    private String local;
    private String detalhes;
    private String endereco;
    private String cidade;
    private String estado;
    private String dataInicio;
    private String horaInicio;
    private String dataFim;
    private String horaFim;
    private status status_leilao;

    public Leilao(String local, String detalhes, String endereco, String cidade, String estado, String dataInicio, String horaInicio, String dataFim, String horaFim, status status_leilao) {
        this.local = local;
        this.detalhes = detalhes;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.dataFim = dataFim;
        this.horaFim = horaFim;
        this.status_leilao = status_leilao.ABERTO;
    }

    public void registroLeilao(){

    }

    public void consultaLeilao(){

    }

    public void atualizacaoLeilao(){

    }

    public void remocaoLeilao(){

    }

    public void listarLeiloesOrdenados(){

    }

    public void consultaItemLeilaoPreco(){

    }

    public void consultaItemLeilaoPalavraChave(){

    }

    public void finalizarLeilao(){

    }

    public void iniciarLeilao(){

    }

    public void listarGanhadores(){

    }

    public void exportarDetalhesdoLeilao(){

    }



}
