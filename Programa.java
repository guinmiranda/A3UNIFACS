import java.util.Scanner;
import java.time.LocalDate;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Programa {

    public static void main(String[] args) {
        int menu = 0;
        int menu2 = 0;
        boolean running = true;
        Scanner scan = new Scanner(System.in);

        ArrayList<Produto> gerenciarProduto = new ArrayList<>();
        ArrayList<Promocao> gerenciarPromocao = new ArrayList<>();
        gerenciarProduto.add(new Produto("bebidas", "leite", datadevalidade(), 1, 7.00, 5, true));
        gerenciarPromocao.add(new Promocao("bebidas", "leite", datadevalidade(), 1, 7.00, 5, true,25));
        gerenciarProduto.add(new Produto("limpeza", "Agua Sanitaria", datadevalidade(), 2, 5.00, 9, false));
        gerenciarProduto.add(new Produto("limpeza", "Vassoura", datadevalidade(), 3, 13.00, 3, false));
        gerenciarProduto.add(new Produto("Carnes", "alcatra", datadevalidade(), 4, 65, 3, false));
        gerenciarProduto.add(new Produto("Enlatados", "milho", datadevalidade(), 5, 4.50, 7, false));
        gerenciarProduto.add(new Produto("bebidas", "Monster", datadevalidade(), 6, 12, 25, false));
        gerenciarProduto.add(new Produto("bebidas", "Vodka", datadevalidade(), 7, 38, 2, false));

        while (running) {
            switch (menu) {
                case 0:
                    //MENU
                    System.out.println("===== MENU =====");
                    System.out.println("1 - Cadastrar Produto");
                    System.out.println("2 - Editar Produtos");
                    System.out.println("3 - Excluir Produtos");
                    System.out.println("4 - Listar Produtos");
                    System.out.println("5 - Promocoes");
                    System.out.println("6 - Sair");
                    System.out.println("================");
                    menu = scan.nextInt();
                    scan.nextLine();
                    break;

                case 1:
                    //CADASTRAR PRODUTO
                    System.out.println("Digite o setor do produto");
                    String setor = scan.nextLine();

                    System.out.println("Digite o nome do produto");
                    String nome = scan.nextLine();
                    
                    //TESTANDO O NUMERO DE SERIE COM UM TRY/CATCH PARA NÃO EXISTIR REPETIÇÕES DELE 
                    System.out.println("Digite o numero de serie do produto");
                    int ndeserie = scan.nextInt();
                    scan.nextLine(); 

                    boolean numeroSerieDuplicado = true;

                    while (numeroSerieDuplicado) {
                        try {
                            // Procurar o produto pelo numero de serie
                            for (Produto p : gerenciarProduto) {
                                if (p.getNdeserie() == ndeserie) {
                                    throw new Exception("Esse numero de serie ja existe, tente outro.");
                                }
                            }

        
                            numeroSerieDuplicado = false;

                        }                   
                        catch (Exception e) {
                            System.out.println("Erro: " + e.getMessage());
                            System.out.println("Digite um novo numero de serie:");
                            ndeserie = scan.nextInt();
                            scan.nextLine();
                            numeroSerieDuplicado = true;
                        }
                    }

                
            
                    System.out.println("Digite o preco do produto");
                    double preco = scan.nextDouble();
                    scan.nextLine(); // Limpar o buffer

                    System.out.println("Digite a quantidade do produto");
                    double quantidade = scan.nextDouble();
                    scan.nextLine(); 
                    
                    Produto Produtonovo = new Produto(setor, nome, datadevalidade(), ndeserie, preco, quantidade, false);

                    gerenciarProduto.add(Produtonovo);
                    System.out.println("Dados do produto listados com sucesso.");
                    System.out.println("Pressione 's' se quiser voltar ao menu ou 'n' se quiser adicionar mais um produto");
                    menu = Confirmacao(scan, 1, 0);
                    scan.nextLine();
                    break;

                case 2:
                    //EDITAR PRODUTO
                    System.out.println("Digite o numero de serie do produto que deseja editar:");
                    int numeroSerieEditar = scan.nextInt();
                    scan.nextLine(); 

                    // Procurar o produto pelo numero de serie
                    Produto produtoEditar = null;
                    for (Produto p : gerenciarProduto) {
                        if (p.getNdeserie() == numeroSerieEditar) {
                            produtoEditar = p;
                            break;
                        }
                    }

                    if (produtoEditar != null) {
                    // Produto encontrado, solicitar informações atualizadas do produto   
                        System.out.println("Produto encontrado: " + produtoEditar.toString());
                        System.out.println("Digite o novo setor do produto:");
                        String novoSetor = scan.nextLine();
                        produtoEditar.setSetor(novoSetor);

                        System.out.println("Digite o novo nome do produto:");
                        String novoNome = scan.nextLine();
                        produtoEditar.setNome(novoNome);

                        System.out.println("Digite o novo preco do produto:");
                        double novoPreco = scan.nextDouble();
                        scan.nextLine(); 
                        produtoEditar.setPreco(novoPreco);

                        System.out.println("Digite a nova quantidade do produto:");
                        double novoQuantidade = scan.nextDouble();
                        scan.nextLine(); 
                        produtoEditar.setQuantidade(novoQuantidade);

                        System.out.println("Produto editado com sucesso.");
                        // Atualizar informações do produto na promoção, se aplicável
                        for (Promocao promo : gerenciarPromocao) {
                            if (promo.getNdeserie() == numeroSerieEditar) {
                                promo.setSetor(produtoEditar.getSetor());
                                promo.setNome(produtoEditar.getNome());
                                
                                promo.setPreco(produtoEditar.getPreco());
                                promo.setQuantidade(produtoEditar.getQuantidade());
                                break;

                            }
                        }
                    } else {
                        System.out.println("Produto nao encontrado.");
                    }

                    System.out.println("Pressione 's' para voltar ao menu principal ou 'n' para continuar editando produtos.");
                    menu = Confirmacao(scan, 2, 0);
                    scan.nextLine();
                    break;

                case 3:
                    System.out.println("Digite o numero de serie do produto que deseja excluir:");
                    int numeroSerieExcluir = scan.nextInt();
                    scan.nextLine(); 
                    boolean remocao=false;
                    boolean produtoRemovido = false;
                    boolean produtoEncontrado=false;
                    for (Produto p : gerenciarProduto) {
                        if (p.getNdeserie() == numeroSerieExcluir) {
                            System.out.println("Tem certeza que deseja excluir o "+p.toString()+"? (s/n)");
                            remocao= Confirmacao(scan,1,0)==0;
                            produtoEncontrado=true;
                            if(remocao){
                            gerenciarProduto.remove(p);
                            produtoRemovido = true;
                            break;
                            }
                        }
                    }

                    if (produtoRemovido) {
                        // Produto encontrado e removido com sucesso
                        System.out.println("Produto removido com sucesso.");
                        // Remover produto da promoção, se aplicável
                        for (Promocao promo : gerenciarPromocao) {
                            if (promo.getNdeserie() == numeroSerieExcluir) {
                                gerenciarPromocao.remove(promo);
                                break;
                            }
                        }
                    } 
                    else if(!produtoEncontrado){
                        System.out.println("Produto nao encontrado.");
                        
                    }
                    else {
                        System.out.println("Remoçao cancelada pelo usuário.");
                    }

                    System.out.println("Pressione 's' para voltar ao menu principal ou 'n' para continuar excluindo produtos.");
                    menu = Confirmacao(scan, 3, 0);
                    scan.nextLine();
                    break;

                case 4:
                    // EXIBIR LISTA DE PRODUTOS
                    System.out.println("===== Produtos Cadastrados =====");
                    for (Produto p : gerenciarProduto) {
                        System.out.println(p.toString2());
                    }
                    System.out.println("===============================");
                    System.out.println("Deseja ordenar por setores? (s/n)");
                    boolean ordsetor = Confirmacao(scan, 1, 0) == 0;
                    scan.nextLine();

                    if (ordsetor) {
                        System.out.println("Digite o setor que deseja ordenar:");
                        String setorDesejado = scan.nextLine();
                        boolean setorEncontrado = false; // Variável para verificar se há correspondências

                        for (Produto p : gerenciarProduto) {
                            if (p.getSetor().equalsIgnoreCase(setorDesejado)) {
                                System.out.println(p.toString());
                                setorEncontrado = true; // Indicar que foi encontrada pelo menos uma correspondência
                            }
                        }

                        if (!setorEncontrado) {
                            System.out.println("Nenhum produto encontrado para o setor informado.");
                        }
                    }

                    System.out.println("Pressione 's' se quiser voltar ao menu principal ou 'n' para continuar visualizando a lista.");
                    menu = Confirmacao(scan, 4, 0);
                    scan.nextLine();
                    break;


                case 5:
                    switch (menu2) {
                        case 0:
                            //MENU
                            System.out.println("===== MENU DE PROMOCOES =====");
                            System.out.println("1 - Listar Promocoes");
                            System.out.println("2 - Adicionar Produto a promocao");
                            System.out.println("3 - Editar um produto da promoçao");
                            System.out.println("4 - Excluir um produto da promoçao");
                            System.out.println("5 - Voltar ao Menu Principal");
                            System.out.println("==============================");
                            menu2 = scan.nextInt();
                            scan.nextLine();
                            break;

                        case 1:
                            // EXIBIR PROMOÇÕES
                            boolean encontrouPromocao = false;
                            for (Promocao p : gerenciarPromocao) {
                                if (p.isPromo()) {
                                    System.out.println(p.toString());
                                    encontrouPromocao = true;
                                }
                            }

                            if (!encontrouPromocao) {
                                // Nenhuma promoção encontrada
                                System.out.println("Nenhuma promoção encontrada. Deseja adicionar alguma? (s/n)");
                                boolean addPromo = Confirmacao(scan, 1, 0) == 0;
                                if (addPromo) {
                                    // Redirecionar para o menu de adicionar promoção
                                    menu2 = 2;
                                    scan.nextLine();
                                    break;
                                }
                            }

                            // Opção para voltar ao menu de promoções ou continuar visualizando a lista
                            System.out.println("===============================");
                            System.out.println("Pressione 's' se quiser voltar ao menu de promoções ou 'n' para continuar visualizando a lista.");
                            menu2 = Confirmacao(scan, 1, 0);
                            scan.nextLine();
                            break;

                        case 2:
                            // ADICIONAR PROMOÇÃO A UM PRODUTO
                            System.out.println("Digite o numero de serie do produto que deseja adicionar a promocao:");
                            int numeroSerieAdicionarPromo = scan.nextInt();
                            scan.nextLine(); 
                            boolean numeroSerieDuplicado1 = true;

                            while (numeroSerieDuplicado1) {
                            try {
                            // Procurar o produto pelo numero de serie
                                for (Produto p : gerenciarPromocao) {
                                    if (p.getNdeserie() == numeroSerieAdicionarPromo) {
                                        throw new Exception("Esse numero de serie ja está em promoçao, tente outro.");
                                    }
                                }

        
                                numeroSerieDuplicado1 = false;

                            }                   
                            catch (Exception e) {
                                System.out.println("Erro: " + e.getMessage());
                                System.out.println("Digite um novo numero de serie:");
                                numeroSerieAdicionarPromo = scan.nextInt();
                                scan.nextLine();
                                numeroSerieDuplicado1 = true;
                            }
                        }
                            Promocao pEditarPromo=null;
                            Produto produtoPromocao = null;
                            // Procurar o produto pelo numero de serie
                            for (Produto p : gerenciarProduto) {
                                if (p.getNdeserie() == numeroSerieAdicionarPromo) {
                                    produtoPromocao = p;
                                    break;
                                }
                            }

                            if (produtoPromocao != null) {
                                // Produto encontrado
                                System.out.println("Produto encontrado: " + produtoPromocao.toString());
                                System.out.println("Deseja ativar a promocao para este produto? (s/n)");
                                boolean checkPromo = Confirmacao(scan, 1, 0) == 0;
                                produtoPromocao.setPromo(checkPromo);
                                gerenciarPromocao.add(new Promocao(produtoPromocao.getSetor(),produtoPromocao.getNome(),produtoPromocao.getValidade(),produtoPromocao.getNdeserie(),produtoPromocao.getPreco(),produtoPromocao.getQuantidade(),produtoPromocao.isPromo(),0));
                                
                                System.out.println("Promocao atualizada com sucesso.");
                            } else {
                                System.out.println("Produto nao encontrado.");
                            }
                            // Procurar a promocao adicionada na lista de promocoes
                            for (Promocao p : gerenciarPromocao) {
                            if (p.getNdeserie() == numeroSerieAdicionarPromo && p.isPromo()) {
                                pEditarPromo = p;
                                break;
                                }
                             }
                            if (pEditarPromo != null) {
                            // Promocao encontrada    
                            System.out.println("Deseja colocar um desconto nesse produto?(s/n)");
                            int EditarPromo = Confirmacao(scan, 0, 1);
                            scan.nextLine();
                            if(EditarPromo==1){
                             // Colocar um desconto na promocao   
                             System.out.println("coloque quanto porcento de desconto quer");
                             double Desconto = scan.nextDouble();
                             scan.nextLine();
                             pEditarPromo.setDesconto(Desconto);
                             pEditarPromo.AplicarDesconto(pEditarPromo.getPreco(), pEditarPromo.getDesconto());
                             System.out.println(pEditarPromo.toString());
                             System.out.println("Promocao atualizada com sucesso.");
                            }
                        }   
                            
                            
                            
                        

                            System.out.println("Pressione 's' para voltar ao menu de promocoes ou 'n' para continuar adicionando produtos a promocao.");
                            menu2 = Confirmacao(scan, 2, 0);
                            scan.nextLine();
                            break;

                        
                            
    
                        case 3:
                            // EDITAR PROMOÇÃO DE UM PRODUTO
                            System.out.println("Digite o número de série do produto cuja promoção deseja editar:");
                            int numeroSerieEditarPromo = scan.nextInt();
                            scan.nextLine(); 
                            
                            Promocao produtoEditarPromo = null;
                            // Procurar a promoção do produto pelo número de série  
                            for (Promocao p : gerenciarPromocao) {
                                if (p.getNdeserie() == numeroSerieEditarPromo && p.isPromo()) {
                                    produtoEditarPromo = p;
                                    break;
                                }
                            }

                            if (produtoEditarPromo != null) {
                            // Promoção encontrada    
                            System.out.println("Produto encontrado: " + produtoEditarPromo.toString());
                            System.out.println("Deseja colocar um desconto nesse produto?");
                            int EditarPromo = Confirmacao(scan, 0, 1);
                            scan.nextLine();
                            if(EditarPromo==1){
                            // Colocar um desconto na promoção 
                             System.out.println("coloque quanto porcento de desconto quer");
                             double Desconto = scan.nextDouble();
                             scan.nextLine();
                             produtoEditarPromo.setDesconto(Desconto);
                             produtoEditarPromo.AplicarDesconto(produtoEditarPromo.getPreco(), produtoEditarPromo.getDesconto());
                             System.out.println(produtoEditarPromo.toString());
                            }
                        }                       
                            else {
                            System.out.println("Produto não encontrado ou não está em promoção.");
                            }

                            System.out.println("Pressione 's' para voltar ao menu de promoções ou 'n' para continuar editando promoções.");
                            menu2 = Confirmacao(scan, 3, 0);
                            scan.nextLine();
                            break;




                        case 4:
                        // REMOVER PROMOÇÃO DE UM PRODUTO
                        System.out.println("Digite o número de série do produto cuja promoção deseja remover:");
                        int Excluirpromo=scan.nextInt();
                        scan.nextLine();
                        boolean promocaoRemovida=false;
                        boolean remocao2=false;
                        boolean promocaoEncontrada=false;
                        // Procurar a promoção do produto pelo número de série e removê-la
                        for (Promocao p : gerenciarPromocao) {
                            if(p.getNdeserie() == Excluirpromo){
                                promocaoEncontrada=true;
                                System.out.println("Tem certeza que deseja excluir a promoçao do "+p.toString()+"? (s/n)");
                                remocao2=Confirmacao(scan, 1, 0)==0;
                                if(remocao2){
                                    gerenciarPromocao.remove(p);
                                    promocaoRemovida = true;
                                    break;
                                }
                            }
                        }
                        if(promocaoRemovida){
                            // Promoção removida com sucesso
                            System.out.println("Promoçao removida com sucesso.");
                        }
                        else if(!promocaoEncontrada){
                           System.out.println( "Promocao nao encontrada.");
                        }
                         
                        else {
                            System.out.println("Remoçao cancelada pelo usuário.");
                        }
                        System.out.println("Pressione 's' para voltar ao menu de promoçoes ou 'n' para continuar excluindo promoçoes.");
                        menu2 = Confirmacao(scan, 4, 0);
                        scan.nextLine();
                        break;
    
                        
                            
                        case 5:
                            //VOLTAR AO MENU DE PROMOÇÕES
                            menu=0;
                            menu2=0;
                            
                            break;
                        default:
                            //MENU DE ERRO
                            System.out.println("Opcao invalida, use uma das seguintes opcoes:");
                            System.out.println("===== MENU DE PROMOCOES =====");
                            System.out.println("1 - Listar Promocoes");
                            System.out.println("2 - Adicionar Produto a promocao");
                            System.out.println("3 - Editar Promocoes");
                            System.out.println("4 - Voltar ao Menu Principal");
                            System.out.println("==============================");
                            menu2 = scan.nextInt();
                            scan.nextLine();
                            break;
                    }
                    break;

                case 6:
                    //ENCERRAR PROGRAMA
                    running = false;
                    System.out.println("Programa encerrado");
                    break;

                default:
                    //ERRO CASO COLOQUE UM VALOR DIFERENTE DA OPÇÕES DISPONÍVEIS
                    System.out.println("Opcao invalida, use uma das seguintes opcoes:");
                    System.out.println("===== MENU =====");
                    System.out.println("1 - Cadastrar Produto");
                    System.out.println("2 - Editar Produtos");
                    System.out.println("3 - Excluir Produtos");
                    System.out.println("4 - Listar Produtos");
                    System.out.println("5 - Promocoes");
                    System.out.println("6 - Sair");
                    System.out.println("================");
                    menu = scan.nextInt();
                    scan.nextLine();
                    break;
            }
        }
    }

    public static int Confirmacao(Scanner scan, int numcaso, int nums) {
        while (true) {
            // Solicita ao usuário que digite sua escolha (s/n)
            String confirmacao = scan.next();

            if (confirmacao.equalsIgnoreCase("s")) {
                // Se o usuário digitar 's', retorna o valor de `nums`
                return nums;
            } else if (confirmacao.equalsIgnoreCase("n")) {
                // Se o usuário digitar 'n', retorna o valor de `numcaso`
                return numcaso;
            } else {
                // Se o usuário digitar uma opção inválida, exibe uma mensagem de erro e continua o loop
                System.out.println("Opcao invalida. Digite 's' ou 'n'.");
            }
        }
    }

    public static String datadevalidade() {
        LocalDate dataBase = LocalDate.of(2023, 6, 16); // Define a data base como 16/06/2023
        Random random = new Random();

        int dia = random.nextInt(984) + 1; // Número de dias entre 1 e 984 (31 de dezembro de 2025 - 16 de junho de 2023)
        LocalDate data = dataBase.plusDays(dia); // Adiciona dias aleatórios à data base

    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Define o formato da data
        String dataFormatada = data.format(formatter); // Formata a data no formato especificado

        return dataFormatada; // Retorna a data formatada
    }


    
}
