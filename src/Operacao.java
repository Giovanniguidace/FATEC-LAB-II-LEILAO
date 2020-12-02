import java.io.*;
import java.util.*;

public class Operacao {
    Scanner ler = new Scanner(System.in);
    static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    static ArrayList<Leilao> leiloes = new ArrayList<Leilao>();
    static ArrayList<InstituicaoFinanceira> instituiçoesFinanceiras = new ArrayList<InstituicaoFinanceira>();

    // Métodos Leilao
    public void ordenarLeilao(){
        Collections.sort(leiloes, Comparator.comparing(Leilao::getDataInicio));
    }

    public void listarLeiloes(){
        int contador = 1;
        for (Leilao leilao : leiloes){
            System.out.println(contador + " - Leilão: " + leilao.getNome());
            contador++;
        }
        System.out.println("Aperte '0' para Voltar");
        if (ler.nextInt() == 0){
            menuAdministrativo();
        }
    }
    public void detalharLeilao(){
        listarLeiloes();
        System.out.println("\nEscolha um leilão (digite o número do leilão)");
        int leilaoEscolhidoIndex = ler.nextInt() - 1;
        leiloes.get(leilaoEscolhidoIndex).exibirDetalhesLeilao();
    }

    public void detalharBemLeilao(){
        listarLeiloes();
        int leilaoEscolhidoIndex = ler.nextInt() - 1;
        Leilao leilaoEscolhido = leiloes.get(leilaoEscolhidoIndex);
        leilaoEscolhido.exibirDetalhesLeilao();
        System.out.println("Qual o Item que você quer detalhes?");
        System.out.println("Filtrar por:\n" +
                "1 - Sem filtro\n" +
                "2 - Filtrar por Faixa de Valores\n" +
                "3 - Filtrar por Palavra Chave\n" +
                "4 - Filtrar por Tipo de Produto\n" +
                "0 - Voltar");
        int indexBemEscolhido = 0;
        switch(ler.nextInt()){
            case 1:
                leilaoEscolhido.listarBensSemFiltro();
                System.out.println("Digite o número do item desejado");
                indexBemEscolhido = ler.nextInt();
                leilaoEscolhido.getBens().get(indexBemEscolhido).detalharBem();
                break;
            case 2:
                System.out.println("Digite o Valor Mínimo do Bem");
                double valorMinimo = ler.nextDouble();
                System.out.println("Digite o Valor Máximo do Bem");
                double valorMaximo = ler.nextDouble();
                leilaoEscolhido.listarBensPorFaixaValores(valorMinimo, valorMaximo);
                System.out.println("Digite o número do item desejado");
                leilaoEscolhido.getBens().get(ler.nextInt()).detalharBem();
                break;
            case 3:
                System.out.println("Digite a Palavra Chave da Busca do Item");
                String palavraChave = ler.nextLine();
                leilaoEscolhido.listarBensPorPalavraChave(palavraChave);
                System.out.println("Digite o número do item desejado");
                leilaoEscolhido.getBens().get(ler.nextInt()).detalharBem();
                break;
            case 4:
                System.out.println("Digite o Tipo de Produto");
                System.out.println("1 - Apartamento\n" +
                        "2 - Terreno\n" +
                        "3 - Casa\n" +
                        "4 - Edificio Comercial\n" +
                        "5 - Carro\n" +
                        "6 - Motocicleta");
                switch (ler.nextInt()){
                    case 1:
                        leilaoEscolhido.listarBensPorTipoProduto(TipoProduto.APARTAMENTO);
                    case 2:
                        leilaoEscolhido.listarBensPorTipoProduto(TipoProduto.TERRENO);
                    case 3:
                        leilaoEscolhido.listarBensPorTipoProduto(TipoProduto.CASA);
                    case 4:
                        leilaoEscolhido.listarBensPorTipoProduto(TipoProduto.EDIFICIO_COMERCIAL);
                    case 5:
                        leilaoEscolhido.listarBensPorTipoProduto(TipoProduto.CARRO);
                    case 6:
                        leilaoEscolhido.listarBensPorTipoProduto(TipoProduto.MOTOCICLETA);
                }

                System.out.println("Digite o número do item desejado");
                leilaoEscolhido.getBens().get(ler.nextInt()).detalharBem();
                break;
            case 0:
                System.out.println("Voltando...");
                break;
            default:
                detalharBemLeilao();
                break;
        }
    }

    public void fornecerUmLance(Cliente cliente){
        Leilao leilaoDoLance;
        String nomeLeilao;
        System.out.println("Qual leilão o seu bem pertence");
        System.out.println("1- Já sei o leilão" +
                "2 - Quero pesquisar um leilão");
        if(ler.nextInt() == 1) {
            System.out.println("Digite o nome do leilão");
            nomeLeilao = ler.nextLine();
            Map<String, Leilao> hashNomeLeilao = new HashMap<String, Leilao>();
            for (Leilao leilao : leiloes){
                hashNomeLeilao.put(leilao.getNome(), leilao);
            }
            leilaoDoLance  = hashNomeLeilao.get(nomeLeilao);
            if (leilaoDoLance == null){
                System.out.println("Não foi possível encontrar o leilao correspondente");
                menuCliente(cliente);
            }
        } else{
            listarLeiloes();
            System.out.println("Digite o numero do leilao correspondente");
            leilaoDoLance = leiloes.get(ler.nextInt());
        }
        //----------------------------------------------------------
        System.out.println("Qual Item você deseja fazer um lance");
        leilaoDoLance.listarBensSemFiltro();
        System.out.println("Digite o numero do Item correspondente");
        leilaoDoLance.getBens().get(ler.nextInt()).fornecerLance(cliente);
    }
    public void atualizarLeilao(){
        Date hoje = Calendar.getInstance().getTime();
        for (Leilao leilao : leiloes){
            if (leilao.getDataInicio() == hoje){
                leilao.abrirLeilao();
            }
            if (leilao.getDataFim() == hoje){
                leilao.finalizarLeilao();
            }
        }
    }

    // Métodos Instituição Financeira
    public void listarInstituicaoFinanceira(){
        for (InstituicaoFinanceira instituicaoFinanceira : instituiçoesFinanceiras){
            int contador = 0;
            System.out.println(contador + " - " + instituicaoFinanceira.getCnpj());
            instituicaoFinanceira.exibirDetalhesInstituicaoFinanceira(leiloes);
            System.out.println("\n");
        }
        System.out.println("Digite qualquer número (positivo) para voltar para o menu anterior");
        if (ler.nextInt() >= 0){
            menuCliente(validaCliente());
        }
    }
    // Metodos Clientes
    public void cadastrarCliente(){
        System.out.println("Digite o CPF");
        Cliente cliente = new Cliente(ler.nextInt());
        clientes.add(cliente);
    }

    public void atualizarCliente(){
        System.out.println("Qual cliente deseja atualizar o cadastro?");
        for (Cliente cliente : clientes){
            if (cliente.getCpf() == ler.nextInt()){
                System.out.println("Digite o novo CPF");
                cliente.setCpf(ler.nextInt());
                break;
            }
        }
    }

    public void excluirCliente(){
        System.out.println("Qual cliente deseja excluir o cadastro?");
        for (Cliente cliente : clientes){
            if (cliente.getCpf() == ler.nextInt()){
                clientes.remove(cliente);
                System.out.println("Cliente removido com sucesso");
                break;
            }
        }
    }

    public void listarClientes(){
        System.out.println("Lista de Clientes: ");
        for (Cliente cliente : clientes){
            System.out.println(cliente.getCpf());
        }
    }

    public Cliente validaCliente(){
        System.out.println("Digite o seu CPF");
        int cpf = ler.nextInt();
        for (Cliente cliente : clientes){
            if (cliente.getCpf() == cpf){
                return cliente;
            }
        }
        return null;
    }

    // Metodos Instituicao Financeira
    public void cadastrarInstituicaoFinanceira(){
        System.out.println("Digite o CNPJ");
        InstituicaoFinanceira instituicaoFinanceira = new InstituicaoFinanceira(ler.nextInt());
        instituiçoesFinanceiras.add(instituicaoFinanceira);
    }

    public void atualizarInstituicaoFinanceira(){
        System.out.println("Qual Instituicao vocÊ deseja atualizar o cadastro?");
        for (InstituicaoFinanceira instituicaoFinanceira : instituiçoesFinanceiras){
            if (instituicaoFinanceira.getCnpj() == ler.nextInt()){
                System.out.println("Digite o novo CNPJ");
                instituicaoFinanceira.setCnpj(ler.nextInt());
                break;
            }
        }
    }

    public void listarInstituicoesFinanceiras(){
        System.out.println("Lista de Instituicoes: ");
        for (InstituicaoFinanceira instituicaoFinanceira : instituiçoesFinanceiras){
            System.out.println(instituicaoFinanceira.getCnpj());
        }
    }

    public void excluirInstituicaosFinanceira(){
        System.out.println("Qual Instituicao deseja excluir o cadastro?");
        for (InstituicaoFinanceira instituicaoFinanceira : instituiçoesFinanceiras){
            if (instituicaoFinanceira.getCnpj() == ler.nextInt()){
                instituiçoesFinanceiras.remove(instituicaoFinanceira);
                System.out.println("Instituicao removida com sucesso");
                break;
            }
        }
    }

    public InstituicaoFinanceira validaInstituicaoFinanceira(){
        System.out.println("Digite o seu CNPJ");
        int cnpj = ler.nextInt();
        for (InstituicaoFinanceira instituicaoFinanceira : instituiçoesFinanceiras){
            if (instituicaoFinanceira.getCnpj() == cnpj){
                return instituicaoFinanceira;
            }
        }
        return null;
    }

    // Métodos Menu
    public void menuAdministrativo(){
        System.out.println("" +
                "##MENU ADMINISTRATIVO##\n" +
                "1 - Listar Todos os Leilões\n" +
                "2 - Detalhar um Leilão\n" +
                "3 - Detalhar o produto de um Leilão\n" +
                "4 - Atualizar Cadastro de um Cliente\n" +
                "5 - Excluir Cadastro de um Cliente\n" +
                "6 - Consultar uma Lista de Clientes\n" +
                "7 - Atualizar o Cadastro de uma Instituiçõe Financeira\n" +
                "8 - Excluir o Cadastro de uma Instituiçõe Financeira\n" +
                "9 - Consultar uma lista de Instituições Financeiras\n" +
                "10 - Exportar Dados do Leilão\n" +
                "0 - Voltar para o menu anterior\n");
        switch (ler.nextInt()){
            case 1:
                listarLeiloes();
                menuAdministrativo();
                break;
            case 2:
                detalharLeilao();
                menuAdministrativo();
                break;
            case 3:
                detalharBemLeilao();
                menuAdministrativo();
            case 4:
                atualizarCliente();
                menuAdministrativo();
                break;
            case 5:
                excluirCliente();
                menuAdministrativo();
                break;
            case 6:
                listarClientes();
                menuAdministrativo();
                break;
            case 7:
                atualizarInstituicaoFinanceira();
                menuAdministrativo();
                break;
            case 8:
                excluirInstituicaosFinanceira();
                menuAdministrativo();
                break;
            case 9:
                listarInstituicoesFinanceiras();
                menuAdministrativo();
                break;
            case 10:
                exportarArquivo();
                menuAdministrativo();
                break;
            case 0:
                menuPrincipal();
                break;
            default:
                menuAdministrativo();
                break;
        }
    }

    public void menuCliente(Cliente cliente){
        System.out.println("" +
                "##MENU CLIENTE##\n" +
                "1 - Listar Todos os Leilões\n" +
                "2 - Detalhar um Leilão\n" +
                "3 - Detalhar o produto de um Leilão\n" +
                "4 - Fornecer um Lance" +
                "5 - Consultar Instituições Financeiras\n" +
                "6 - Excluir meu cadastro\n" +
                "0 - Voltar para o menu anterior\n");
        switch (ler.nextInt()){
            case 1:
                listarLeiloes();
                menuCliente(cliente);
                break;
            case 2:
                detalharLeilao();
                menuCliente(cliente);
                break;
            case 3:
                detalharBemLeilao();
                menuCliente(cliente);
            case 4:
                fornecerUmLance(cliente);
                break;
            case 5:
                listarInstituicaoFinanceira();
                break;
            case 6:
                System.out.println("Implementar!");
                break;
            case 0:
                menuPrincipal();
                break;
            default:
                menuAdministrativo();
                break;
        }
    }

    public void menuInstituicaoFinanceira(InstituicaoFinanceira instituicaoFinanceira){
        System.out.println("" +
                "##MENU INSTITUIÇÃO FINANCEIRA##\n" +
                "1 - Criar um Leilão\n" +
                "2 - Atualizar um Leilão\n" +
                "3 - Remover o um Leilão\n" +
                "4 - Adicionar um Bem a um Leilão\n" +
                "5 - Atualizar um Bem em um Leilão\n" +
                "0 - Voltar para o menu anterior\n");
        switch (ler.nextInt()){
            case 1:
                instituicaoFinanceira.criarLeilao();
                ordenarLeilao();
                break;
            case 2:
                instituicaoFinanceira.listarLeiloesInstituicaoFinanceira(leiloes);
                System.out.println("Digite o número do Leilão que deseja consultar");
                int indexLeilao = ler.nextInt();
                instituicaoFinanceira.atualizarLeilao(leiloes.get(indexLeilao));
                System.out.println("Leilão Atualizado");
                ordenarLeilao();
                menuInstituicaoFinanceira(instituicaoFinanceira);
                break;
            case 3:
                instituicaoFinanceira.listarLeiloesInstituicaoFinanceira(leiloes);
                System.out.println("Digite o número do Leilão que deseja remover");
                int indexRemoveLeilao = ler.nextInt();
                leiloes = instituicaoFinanceira.removeLeilao(leiloes, leiloes.get(indexRemoveLeilao));
                System.out.println("Leilão Atualizado");
                menuInstituicaoFinanceira(instituicaoFinanceira);
            case 4:
                instituicaoFinanceira.listarLeiloesInstituicaoFinanceira(leiloes);
                System.out.println("Digite o número do Leilão que criar um bem");
                int indexCriarBem = ler.nextInt();
                leiloes.get(indexCriarBem).criarBem();
                System.out.println("Bem Adicionado!");
                menuInstituicaoFinanceira(instituicaoFinanceira);
                break;
            case 5:
                instituicaoFinanceira.listarLeiloesInstituicaoFinanceira(leiloes);
                System.out.println("Digite o número do Leilão que criar um bem");
                int indexAtualizarBem = ler.nextInt();
                leiloes.get(indexAtualizarBem).listarBensSemFiltro();
                System.out.println("Digite o número do item que deseja atualizar");
                Bem bemAtualizar = leiloes.get(indexAtualizarBem).getBens().get(ler.nextInt());
                bemAtualizar.atualizarBem();
                break;
            case 0:
                menuPrincipal();
                break;
            default:
                menuAdministrativo();
                break;
        }
    }

    public void menuPrincipal(){
        boolean loopPrograma = true;
        while (loopPrograma){
            // Atualiza Leiloes
            atualizarLeilao();

            // Menu Inicial
            System.out.println("##MENU INICIAL##\n" +
                    "Você deseja entrar como que tipo de Usuário:\n" +
                    "1 - Administrativo\n" +
                    "2 - Cliente\n" +
                    "3 - Instituição Financeira\n" +
                    "4 - Desejo me Cadastrar (Cliente)\n" +
                    "5 - Desejo Excluir meu Cadastro (Cliente)\n" +
                    "6 - Desejo me Cadastrar (Instituicao Financeira)\n" +
                    "7 - Desejo Excluir meu Cadastro  (Instituicao Financeira)\n" +
                    "0 - Fechar o programa\n");
            switch (ler.nextInt()){
                case 1:
                    menuAdministrativo();
                    break;
                case 2:
                    Cliente cliente = validaCliente();
                    if (cliente != null){
                        menuCliente(cliente);
                    } else{
                        System.out.println("Número Inválido...");
                        menuPrincipal();
                    }
                    break;
                case 3:
                    InstituicaoFinanceira instituicaoFinanceira = validaInstituicaoFinanceira();
                    if (instituicaoFinanceira != null){
                        menuInstituicaoFinanceira(instituicaoFinanceira);
                    } else{
                        System.out.println("Número Inválido...");
                        menuPrincipal();
                    }
                    break;
                case 4:
                    cadastrarCliente();
                    break;
                case 5:
                    excluirCliente();
                    break;
                case 6:
                    cadastrarInstituicaoFinanceira();
                    break;
                case 7:
                    excluirInstituicaosFinanceira();
                    break;
                case 0:
                    loopPrograma = false;
                    break;
                default:
                    System.out.println("Escolha Inválida!");
                    menuPrincipal();
                    break;
            }
        }
    }

    public void exportarArquivo(){
        try {
            // Conteudo


            // Cria arquivo
            File file = new File("dadosLeilao.txt");

            // Se o arquivo nao existir, ele gera
           /* if (!file.exists()) {
                file.createNewFile();
            }*/

            // Prepara para escrever no arquivo
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            // Escreve e fecha arquivo
            for(Leilao leilao : leiloes){
                bw.write(leilao.getNome());
                bw.close();
            }


          /*  // Le o arquivo
            FileReader ler = new FileReader("dadosLeilao.txt");
            BufferedReader reader = new BufferedReader(ler);
            String linha;
            while( (linha = reader.readLine()) != null ){
                System.out.println(linha);
            }

            // Imprime confirmacao
            System.out.println("Feito =D");*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
