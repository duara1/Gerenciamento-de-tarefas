import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

class Tarefa {
    public static List<Tarefa> tarefas = new ArrayList<>();

    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private boolean concluido;

    Tarefa(String titulo, String descricao, LocalDate prazo){
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.concluido = false;
    } 

    public void concluir() {
        this.concluido = true;
    }

    public static void addTarefa(String titulo, String descricao, LocalDate prazo) {
        tarefas.add(new Tarefa(titulo, descricao, prazo));
    }

    public static void listarTarefas() {
        if(tarefas.size() == 0){
            System.out.println("\n\tNenhuma tarefa encontrada");
        }
            System.out.print("\n");
            for(int i = 0; i < tarefas.size(); i++){
                System.out.printf("\t%d. %s\n", i, tarefas.get(i).titulo);
            }
    }

  

    public static void atualizarLista(int posicao, String nvTitulo, String nvDescricao, LocalDate nvPrazo){
        Tarefa tarefa = tarefas.get(posicao);

        if(nvTitulo == null){
            tarefa.titulo = nvTitulo;
        }
        if(nvDescricao == null){
            tarefa.descricao = nvDescricao;
        }
        if(nvPrazo == null){
            tarefa.prazo = nvPrazo;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int opcao;

        do{
            System.out.println("\n ====Gerenciamento de Tarefas====");
            System.out.println(" 1. Adicionar tarefa\n 2. Atualizar lista\n 3. Verificar lista\n 4. Sair");
            System.out.print(" Escolha uma opção: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\n\tTítulo: ");
                    String titulo = input.nextLine();
                    System.out.print("\tDescrição: ");
                    String descricao = input.nextLine();
                    System.out.print("\tPrazo (dd/MM/yyyy): ");
                    String prazostrg = input.nextLine();
                    LocalDate prazo = LocalDate.parse(prazostrg, formato);

                    Tarefa.addTarefa(titulo, descricao, prazo);
                    System.out.println("\tTarefa adicionada com sucesso!");
                    break;

                case 2:
                    if(Tarefa.tarefas.size() == 0){
                        System.out.println("\n\tNenhuma tarefa encontrada");
                    }

                    if(Tarefa.tarefas.size() == 1){
                        System.out.print("\n\t==Atualizar==");
                        System.out.println("\n\t1. Titulo\n\t2. Descrição\n\t3. Prazo\n\t4. Sair");
                        System.out.print(" Escolha uma opção: ");
                        System.out.print("\n");
                        int opc1 = input.nextInt();
                        input.nextLine();

                        if (opc1 == 1) {
                            System.out.print("\n\t\tDigite o novo título: ");
                            String nvTitulo = input.nextLine();
                            Tarefa.atualizarLista(0, nvTitulo, null, null);
                        }

                        if (opc1 == 2) {
                            System.out.print("\t\tDigite a nova descrição: ");
                            String nvDescricao = input.nextLine();
                            Tarefa.atualizarLista(0, null, nvDescricao, null);
                        }

                        if (opc1 == 3) {
                            System.out.print("\t\tDigite o novo prazo (dd/MM/yyyy): ");
                            String nvPrazo = input.nextLine();
                            LocalDate novoPrazo = LocalDate.parse(nvPrazo, formato);
                            Tarefa.atualizarLista(0, null, null, novoPrazo);
                        }

                        if (opc1 == 4)
                            break;

                    } 
                    
                    if (Tarefa.tarefas.size() > 1){

                        Tarefa.listarTarefas();
                        System.out.print("\n\t==Tarefas==");
                        System.out.print("\tEscolha a opção que deseja atualizar: "); 
                        int opcAtualizar = input.nextInt();

                        System.out.print("\t==Atualizar==");
                        System.out.println("\n\t1. Titulo\n\t2. Descrição\n\t3. Prazo\n\t4. Sair");
                        System.out.print(" Escolha uma opção: ");
                        System.out.print("\n");
                        int opc2 = input.nextInt();
                        input.nextLine();

                        if (opc2 == 1) {
                            System.out.print("\n\t\tDigite o novo título: ");
                            String nvTitulo = input.nextLine();
                            Tarefa.atualizarLista(opcAtualizar, nvTitulo, null, null);
                        }

                        if (opc2 == 2) {
                            System.out.print("\t\tDigite a nova descrição: ");
                            String nvDescricao = input.nextLine();
                            Tarefa.atualizarLista(opcAtualizar, null, nvDescricao, null);
                        }

                        if (opc2 == 3) {
                            System.out.print("\t\tDigite o novo prazo (dd/MM/yyyy): ");
                            String nvPrazo = input.nextLine();
                            LocalDate novoPrazo = LocalDate.parse(nvPrazo, formato);
                            Tarefa.atualizarLista(opcAtualizar, null, null, novoPrazo);
                        }

                        if (opc2 == 4)
                            break;
                    }
                    break;
            
                default:
                    System.out.println("Digite uma opção válida");
                    break;
            }
        } while(opcao != 4);
        input.close();
    }
}