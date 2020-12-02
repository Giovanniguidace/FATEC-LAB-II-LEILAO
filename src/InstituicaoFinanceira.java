import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.util.Scanner;

public class InstituicaoFinanceira {
    private Integer cnpj;

    // CONSTRUTOR
    public InstituicaoFinanceira(Integer cnpj) {
        this.cnpj = cnpj;
    }

    // GETTERS E SETTERS


    public Integer getCnpj() {
        return cnpj;
    }

    public void setCnpj(Integer cnpj) {
        this.cnpj = cnpj;
    }

    public void exibirDetalhesInstituicaoFinanceira(ArrayList<Leilao> leiloes)
    {
        System.out.println("Os detalhes da Instituição Financeira Selecionada são:");
        System.out.println("CNPJ: " + this.cnpj);
        listarLeiloesInstituicaoFinanceira(leiloes);

    }
    // LEILAO
    public void listarLeiloesInstituicaoFinanceira(ArrayList<Leilao> leiloes){
        int contador = 0;
        for (Leilao leilao: leiloes) {
            if (leilao.getCnpjInstFin() == this.cnpj) {
                System.out.println(contador + " - leilão: " + leilao.getNome());
                contador ++;
            }
        }
    }

    public Leilao criarLeilao(){
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o Nome do Leilão: ");
        String nome = ler.nextLine();
        System.out.println("Digite o Local do Leilão: ");
        String local = ler.nextLine();
        System.out.println("Digite o Endereço: ");
        String endereco  = ler.nextLine();
        System.out.println("Digite a Cidade: ");
        String cidade = ler.nextLine();
        System.out.println("Digite o Estado: ");
        String estado = ler.nextLine();
        Status status_leilao = Status.ABERTO;
        System.out.println("Digite o cnpj da Instituicao FInanceira: ");
        Integer cnpjInstFin = ler.nextInt();
        System.out.println("Digite a data de início: ");
        String dataInicioString = ler.nextLine();
        System.out.println("Digite a data de fim: ");
        String dataFimString = ler.nextLine();
        DateFormat formatarData = DateFormat.getInstance();
        try {
            Date dataInicio = formatarData.parse(dataInicioString);
            Date dataFim = formatarData.parse(dataFimString);
            Leilao leilao = new Leilao(nome, local, endereco, cidade, estado,dataInicio,dataFim, status_leilao, cnpjInstFin);
            return leilao;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Data de entrada Inválida");
            criarLeilao();
        }
        return null;
    }

    public void atualizarLeilao(Leilao leilao){
        Scanner ler = new Scanner(System.in);
        System.out.println("Qual item você deseja atualizar (digite o número do item)");
        System.out.println("1 - nome");
        System.out.println("2 - local");
        System.out.println("3 - endereco");
        System.out.println("4 - cidade");
        System.out.println("5 - estado");
        System.out.println("6 - dataInicio");
        System.out.println("7 - dataFim");
        System.out.println("0 - Voltar");
        int opcao = ler.nextInt();
        switch (opcao){
            case 1:
                System.out.println("Digite o novo nome:");
                leilao.setNome(ler.nextLine());
                break;
            case 2:
                System.out.println("Digite o novo local:");
                leilao.setLocal(ler.nextLine());
                break;
            case 3:
                System.out.println("Digite o novo Endereco:");
                leilao.setEndereco(ler.nextLine());
                break;
            case 4:
                System.out.println("Digite a nova Cidade:");
                leilao.setCidade(ler.nextLine());
                break;
            case 5:
                System.out.println("Digite o novo Estado:");
                leilao.setEstado(ler.nextLine());
                break;
            case 6:
                System.out.println("Digite a nova Data de Início:");
                String dataInicioString = ler.nextLine();
                DateFormat formatarData = DateFormat.getInstance();
                try {
                    Date dataInicio = formatarData.parse(dataInicioString);
                    leilao.setDataInicio(dataInicio);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Data de entrada Inválida");
                }
                break;
            case 7:
                System.out.println("Digite a nova Data de Fim:");
                String dataFimString = ler.nextLine();
                DateFormat formatarDataFim = DateFormat.getInstance();
                try {
                    Date dataFim = formatarDataFim.parse(dataFimString);
                    leilao.setDataFim(dataFim);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Data de entrada Inválida");
                }
                break;
            case 0:
                System.out.println("Voltando...");
                break;
            default:
                System.out.println("Opcao Inválida");
                break;
        }
    }

    public ArrayList<Leilao> removeLeilao (ArrayList<Leilao> leiloes, Leilao leilao){
        leiloes.remove(leilao);
        return  leiloes;
    }

}
