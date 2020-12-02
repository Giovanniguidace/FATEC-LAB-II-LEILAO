import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Bem {
    private String id;
    private TipoBem tipoDoBem;
    private TipoProduto tipoDoProduto;
    private double lanceMin;
    private double lanceAtual;
    private ArrayList<Lance> listaLancesID;

    Scanner ler = new Scanner(System.in);

// CONSTRUTOR
    public Bem(TipoBem tipoDoBem, TipoProduto tipoDoProduto, Double lanceMin) {
        this. id = UUID.randomUUID().toString().replace("-", "");
        this.tipoDoBem = tipoDoBem;
        this.tipoDoProduto = tipoDoProduto;
        this.lanceMin = lanceMin;
        this.lanceAtual = 0.00;
    }
// GETTERS E SETTERS


    public String getId() {
        return id;
    }

    public TipoBem getTipoDoBem() {
        return tipoDoBem;
    }

    public TipoProduto getTipoDoProduto() {
        return tipoDoProduto;
    }

    public double getLanceMin() {
        return lanceMin;
    }

    public double getLanceAtual() {
        return lanceAtual;
    }

    public ArrayList<Lance> getListaLancesID() {
        return listaLancesID;
    }

    public Scanner getLer() {
        return ler;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTipoDoBem(TipoBem tipoDoBem) {
        this.tipoDoBem = tipoDoBem;
    }

    public void setTipoDoProduto(TipoProduto tipoDoProduto) {
        this.tipoDoProduto = tipoDoProduto;
    }

    public void setLanceMin(double lanceMin) {
        this.lanceMin = lanceMin;
    }

    public void setLanceAtual(double lanceAtual) {
        this.lanceAtual = lanceAtual;
    }

    public void setListaLancesID(ArrayList<Lance> listaLancesID) {
        this.listaLancesID = listaLancesID;
    }

    public void setLer(Scanner ler) {
        this.ler = ler;
    }

    // LANCES
    public void fornecerLance(Cliente cliente){
        System.out.println("Valor do lance atual: " + lanceAtual);
        System.out.println("Qual o valor do Lance?");
        double novoLance = ler.nextDouble();
        if (novoLance > this.lanceAtual && novoLance > this.lanceMin){
            this.lanceAtual = novoLance;
            Lance lance = new Lance(novoLance, cliente);
            this.listaLancesID.add(lance);
        } else {
            System.out.println("Lance Inválido\n");
            System.out.println("Deseja fornecer um novo lance?" +
                    "\nDigite 1 para dar um novo lance" +
                    "\nDigite 0 para voltar ao menu anterior");
            int opcao = ler.nextInt();
            if (opcao == 0){
                System.out.println("Voltando...");
            } else if (opcao == 1){
                fornecerLance(cliente);
            }
        }
    }

    public void consultarLance(){
        if (this.listaLancesID.isEmpty()){
            System.out.println("Não temos lances ainda!");
        } else {
            System.out.println("O valor do lance atual é: " + this.lanceAtual);
            System.out.println("\nHistórico de Lances:");
            for (Lance itemLance : listaLancesID){
                System.out.println("Valor do Lance: " + itemLance.getValorLance() + " Cliente: " + itemLance.getCliente().getCpf());
            }
        }

    }
// BENS
    public String detalharBem() {
        return "Bem:\n" +
                "tipoDoBem: " + tipoDoBem + "\n" +
                "tipoDoProduto: " + tipoDoProduto + "\n" +
                "lanceMin: " + lanceMin + "\n" +
                "lanceAtual: " + lanceAtual + "\n" +
                "listaLancesID: " + listaLancesID;
    }

    public void atualizarBem(){
        System.out.println("Qual informação deseja atualizar?");
        System.out.println("1 - Tipo do Bem");
        System.out.println("2 - Tipo do Produto");
        System.out.println("3 - Lance Mínimo");
        System.out.println("0 - Voltar");
        int option = ler.nextInt();
        switch (option){
            case 1:
                if (tipoDoBem == TipoBem.VEICULO) {
                    setTipoDoBem(TipoBem.IMOVEL);
                    System.out.println("O tipo do bem foi alterado para: " + getTipoDoBem());
                    System.out.println("\nQual o tipo do Produto? ");
                    System.out.println("" +
                            "1 - Apartamento\n" +
                            "2 - Casa\n" +
                            "3 - Terreno\n" +
                            "4 - Edificios Comerciais");
                    int opcaoTipo = ler.nextInt();
                    switch (opcaoTipo) {
                        case 1:
                            setTipoDoProduto(TipoProduto.APARTAMENTO);
                            break;
                        case 2:
                            setTipoDoProduto(TipoProduto.CASA);
                            break;
                        case 3:
                            setTipoDoProduto(TipoProduto.TERRENO);
                            break;
                        case 4:
                            setTipoDoProduto(TipoProduto.EDIFICIO_COMERCIAL);
                            break;
                        default:
                            setTipoDoBem(TipoBem.VEICULO);
                            System.out.println("Opção Inválida, Voltando...");
                            atualizarBem();
                            break;
                    }
                    System.out.println("O tipo do produto foi alterado para: " + getTipoDoProduto());
                }
                else {
                    setTipoDoBem(TipoBem.VEICULO);
                    System.out.println("O tipo do bem foi alterado para: " + getTipoDoBem());
                    System.out.println("\nQual o tipo do Produto? ");
                    System.out.println("" +
                            "1 - Carro\n" +
                            "2 - Motocicleta\n");
                    int opcaoTipo = ler.nextInt();

                    switch (opcaoTipo) {
                        case 1:
                            setTipoDoProduto(TipoProduto.CARRO);
                            break;
                        case 2:
                            setTipoDoProduto(TipoProduto.MOTOCICLETA);
                            break;
                        default:
                            setTipoDoBem(TipoBem.IMOVEL);
                            System.out.println("Opção Inválida, Voltando...");
                            atualizarBem();
                            break;
                    }
                }
                break;
            case 2:
                if (tipoDoBem == TipoBem.IMOVEL) {
                    System.out.println("\nQual o tipo do Produto? ");
                    System.out.println("" +
                            "1 - Apartamento\n" +
                            "2 - Casa\n" +
                            "3 - Terreno\n" +
                            "4 - Edificios Comerciais");
                    int opcaoTipo = ler.nextInt();
                    switch (opcaoTipo) {
                        case 1:
                            setTipoDoProduto(TipoProduto.APARTAMENTO);
                            break;
                        case 2:
                            setTipoDoProduto(TipoProduto.CASA);
                            break;
                        case 3:
                            setTipoDoProduto(TipoProduto.TERRENO);
                            break;
                        case 4:
                            setTipoDoProduto(TipoProduto.EDIFICIO_COMERCIAL);
                            break;
                        default:
                            System.out.println("Opção Inválida, Voltando...");
                            atualizarBem();
                            break;
                    }
                }
                else {
                    System.out.println("\nQual o tipo do Produto? ");
                    System.out.println("" +
                            "1 - Carro\n" +
                            "2 - Motocicleta\n");
                    int opcaoTipo = ler.nextInt();
                    switch (opcaoTipo) {
                        case 1:
                            setTipoDoProduto(TipoProduto.CARRO);
                            break;
                        case 2:
                            setTipoDoProduto(TipoProduto.MOTOCICLETA);
                            break;
                        default:
                            System.out.println("Opção Inválida, Voltando...");
                            atualizarBem();
                            break;
                    }
                }
                break;
            case 3:
                System.out.println("Digite o novo Lance Mínimo");
                setLanceMin(ler.nextDouble());
                System.out.println("Lance mínimo alterado para: " + getLanceMin());
                break;
            case 0:
                System.out.println("Voltando...");
                break;
            default:
                atualizarBem();
                break;
        }
    }

    public ArrayList<Bem> excluirBem(ArrayList<Bem> Bens, Bem bem){
        Bens.remove(bem);
        System.out.println("Bem removido");
        return Bens;
    }
}
