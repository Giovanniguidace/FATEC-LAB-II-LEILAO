import java.util.*;


public class main {
    Leilao leilao;
    static ArrayList <Leilao>leiloes = new ArrayList<Leilao>();

    public void criarLeiloes(){
        Scanner sc = new Scanner(System.in);
        String local = sc.nextLine();
        String detalhes = sc.nextLine();
        String endereco  = sc.nextLine();
        String cidade = sc.nextLine();
        String estado = sc.nextLine();
        String dataInicio = sc.nextLine();
        String horaInicio = sc.nextLine();
        String dataFim = sc.nextLine();
        String horaFim = sc.nextLine();
        status status_leilao = status.ABERTO;
        leiloes.add(new Leilao(local,detalhes, endereco, cidade, estado,dataInicio,horaInicio,dataFim,horaFim, status_leilao));
        Collections.sort(leiloes, Comparator.comparing(Leilao::getDataInicio));
    }

    public void exibirListaLeiloes(){

        for(Leilao x : leiloes){
            System.out.println(x.getDetalhes());
        }
    }

    public void criarListaProdutos(){
        
    }

    public static void main(String[] args){




    }
}
