//import lombok.Getter;
//import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

//@Getter
//@Setter

public class Leilao {
    private String nome;
    private String local;
    private ArrayList<Bem> bens;
    private String endereco;
    private String cidade;
    private String estado;
    private Date dataInicio;
    private Date dataFim;
    private Status status_leilao;
    private Integer cnpjInstFin;

    //CONSTRUTOR
    public Leilao(
            String nome,
            String local,
            String endereco,
            String cidade,
            String estado,
            Date dataInicio,
            Date dataFim,
            Status status_leilao,
            Integer cnpjInstFin) {
        this.nome = nome;
        this.local = local;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status_leilao = status_leilao;
        this.cnpjInstFin = cnpjInstFin;
    }

   // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }

    public String getLocal() {
        return local;
    }

    public ArrayList<Bem> getBens() {
        return bens;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public Status getStatus_leilao() {
        return status_leilao;
    }

    public Integer getCnpjInstFin() {
        return cnpjInstFin;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setBens(ArrayList<Bem> bens) {
        this.bens = bens;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public void setStatus_leilao(Status status_leilao) {
        this.status_leilao = status_leilao;
    }

    public void setCnpjInstFin(Integer cnpjInstFin) {
        this.cnpjInstFin = cnpjInstFin;
    }

    Scanner ler = new Scanner(System.in);

    public void exibirDetalhesLeilao() {
        System.out.println("Leilao: \n" +
                "nome='" + nome + '\'' +
                ", local='" + local + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataFim='" + dataFim + '\'' +
                ", status_leilao=" + status_leilao +
                ", cnpjInstFin=" + cnpjInstFin);

        if (status_leilao == Status.FINALIZADO){
            listarBensLeilaoFinalizado();
        } else {
            listarBensSemFiltro();
        }
    }
// BENS
    public void listarBensSemFiltro(){
        System.out.println("Lista de Bens do leilão: " + getNome() + "\n");
        int contador = 0;
        for (Bem bem: bens){
            System.out.println("\n" + contador +" - Tipo do Bem: " +bem.getTipoDoBem() +
                    " , Tipo do Produto: " + bem.getTipoDoProduto());
            if (bem.getLanceAtual() != 0.00){
                System.out.println("Lance Mínimo: " + bem.getLanceMin() +
                        " , Lance Atual: " + bem.getLanceAtual()+ "\n");
            } else {
                System.out.println("Lance Mínimo: " + bem.getLanceMin() +
                        " , Nenhum lance foi dado ainda.\n");
            }
            contador++;
        }
    }

    public void listarBensPorFaixaValores(double valorMinimo, double valorMaximo){
        System.out.println("Lista de Bens do leilão: " + getNome() + " por faixa de valores\n");
        int contador = 0;
        for (Bem bem: bens){
            if (bem.getLanceMin() >= valorMinimo && bem.getLanceMin() <= valorMaximo){
                System.out.println("\n" + contador +" - Tipo do Bem: " +bem.getTipoDoBem() +
                        " , Tipo do Produto: " + bem.getTipoDoProduto());
                if (bem.getLanceAtual() != 0.00){
                    System.out.println("Lance Mínimo: " + bem.getLanceMin() +
                            " , Lance Atual: " + bem.getLanceAtual()+ "\n");
                } else {
                    System.out.println("Lance Mínimo: " + bem.getLanceMin() +
                            " , Nenhum lance foi dado ainda.\n");
                }
            }
            contador++;
        }
    }

    public void listarBensPorPalavraChave(String palavraChave){
        System.out.println("Lista de Bens do leilão: " + getNome() + "por palavra chave\n");
        int contador = 0;
        for (Bem bem: bens){
            if (bem.getTipoDoBem().toString().contains(palavraChave) || bem.getTipoDoProduto().toString().contains(palavraChave) ){
                System.out.println("\n" + contador +" - Tipo do Bem: " +bem.getTipoDoBem() +
                        " , Tipo do Produto: " + bem.getTipoDoProduto());
                if (bem.getLanceAtual() != 0.00){
                    System.out.println("Lance Mínimo: " + bem.getLanceMin() +
                            " , Lance Atual: " + bem.getLanceAtual()+ "\n");
                } else {
                    System.out.println("Lance Mínimo: " + bem.getLanceMin() +
                            " , Nenhum lance foi dado ainda.\n");
                }
            }
            contador++;
        }
    }

    public void listarBensPorTipoProduto(TipoProduto tipoProduto){
        System.out.println("Lista de Bens do leilão: " + getNome() + "por tipo de produto\n");
        int contador = 0;
        for (Bem bem: bens){
            if (bem.getTipoDoProduto() == tipoProduto){
                System.out.println("\n" + contador +" - Tipo do Bem: " +bem.getTipoDoBem() +
                        " , Tipo do Produto: " + bem.getTipoDoProduto());
                if (bem.getLanceAtual() != 0.00){
                    System.out.println("Lance Mínimo: " + bem.getLanceMin() +
                            " , Lance Atual: " + bem.getLanceAtual()+ "\n");
                } else {
                    System.out.println("Lance Mínimo: " + bem.getLanceMin() +
                            " , Nenhum lance foi dado ainda.\n");
                }
            }
            contador++;
        }
    }

    public void listarBensLeilaoFinalizado(){
        System.out.println("Lista de Bens do leilão: " + getNome() + "\n");
        for (Bem bem: bens){
            System.out.println("\nTipo do Bem: " +bem.getTipoDoBem() +
                    " , Tipo do Produto: " + bem.getTipoDoProduto());
            if (bem.getLanceAtual() != 0.00){
                ArrayList<Lance> lances = bem.getListaLancesID();

                System.out.println("O lance final foi de: "+ lances.get(lances.size()-1).getValorLance() +
                        " feito pelo cliente: " + lances.get(lances.size()-1).getCliente());
            } else {
                System.out.println("Produto não teve lances");
            }
        }
    }

    public void criarBem(){
        TipoBem tipoBem = null;
        TipoProduto tipoProduto = null;

        System.out.println("Digite o Tipo do Bem");
        System.out.println("1 - Imovel");
        System.out.println("2 - Veiculo");
        System.out.println("0 - Voltar");
        int opcao = ler.nextInt();
        switch (opcao){
            case 1:
                tipoBem = TipoBem.IMOVEL;
                System.out.println("\nQual o tipo do Produto? ");
                System.out.println("" +
                        "1 - Apartamento\n" +
                        "2 - Casa\n" +
                        "3 - Terreno\n" +
                        "4 - Edificios Comerciais");
                int opcaoImovel = ler.nextInt();
                switch (opcaoImovel) {
                    case 1:
                        tipoProduto = TipoProduto.APARTAMENTO;
                        break;
                    case 2:
                        tipoProduto = TipoProduto.CASA;
                        break;
                    case 3:
                        tipoProduto = TipoProduto.TERRENO;
                        break;
                    case 4:
                        tipoProduto = TipoProduto.EDIFICIO_COMERCIAL;
                        break;
                    default:
                        System.out.println("Opção Inválida, Voltando...");
                        criarBem();
                        break;
                }
                break;
            case 2:
                tipoBem = TipoBem.VEICULO;
                System.out.println("\nQual o tipo do Produto? ");
                System.out.println("" +
                        "1 - Carro\n" +
                        "2 - Motocicleta\n");
                int opcaoVeiculo = ler.nextInt();
                switch (opcaoVeiculo) {
                    case 1:
                        tipoProduto = TipoProduto.CARRO;
                        break;
                    case 2:
                        tipoProduto = TipoProduto.MOTOCICLETA;
                        break;
                    default:
                        System.out.println("Opção Inválida, Voltando...");
                        criarBem();
                        break;
                }
        }
        System.out.println("Digite o Lance Mínimo");
        Double lanceMin = ler.nextDouble();
        System.out.println("O bem foi criado com sucesso!" +
                "Tipo do Bem: " + tipoBem +
                "Tipo do Produto: " + tipoProduto +
                "Lance Mínimo: " + lanceMin);
        Bem bem = new Bem(tipoBem, tipoProduto, lanceMin);
        bens.add(bem);
    }

    public void abrirLeilao() {
        setStatus_leilao(Status.ABERTO);
    }

    public void finalizarLeilao() {
        setStatus_leilao(Status.FINALIZADO);
    }


// Exportar TODOS os detalhes em formato .DET


}
