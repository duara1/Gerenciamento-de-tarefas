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
    private boolean status;

    Tarefa(String titulo, String descricao, LocalDate prazo){
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.status = false;
    } 

    public static void addTarefa(String titulo, String descricao, LocalDate prazo) {
        tarefas.add(new Tarefa(titulo, descricao, prazo));
    }

    public static int escolhaTarefa(Scanner input) {
        int opcAtualizar = 0;

        do{
            System.out.println("\n\t==Tarefas==");
            for(int i = 0; i < tarefas.size(); i++){
                System.out.printf("\t%d. %s\n", i, tarefas.get(i).titulo);
            }
            System.out.print("\tDigite uma opção: "); 
            opcAtualizar = input.nextInt();
            System.out.print("\n");

            if(opcAtualizar > tarefas.size() || opcAtualizar < 0){
                System.out.println("\n\t\tDigite uma opção válida");
            }
        } while (opcAtualizar > tarefas.size() || opcAtualizar < 0);

        return opcAtualizar;
            
    }

  

    public static void atualizarTarefa(int posicao, Scanner input, DateTimeFormatter formato){
        Tarefa tarefa = tarefas.get(posicao);

        System.out.print("\n\t==Atualizar==");
        System.out.println("\n\t1. Titulo\n\t2. Descrição\n\t3. Prazo\n\t4. Sair");
        System.out.print("\tEscolha uma opção: ");
        int opcao = input.nextInt();
        input.nextLine();

        if (opcao == 1) {
            System.out.print("\tDigite o novo título: ");
            String nvTitulo = input.nextLine();
            tarefa.titulo = nvTitulo;
        }

        if (opcao == 2) {
            System.out.print("\tDigite a nova descrição: ");
            String nvDescricao = input.nextLine();
            tarefa.descricao = nvDescricao;
        }

        if (opcao == 3) {
            System.out.print("\tDigite o novo prazo (dd/MM/yyyy): ");
            String nvPrazo = input.nextLine();
            LocalDate novoPrazo = LocalDate.parse(nvPrazo, formato);
            tarefa.prazo = novoPrazo;
        }

        if (opcao == 4)
            return;
    }

    public static void detalhesTarefa(int posicao){
        Tarefa tarefa = tarefas.get(posicao);
        LocalDate hoje = LocalDate.now();
        boolean atrazo = tarefa.prazo.isBefore(hoje);

        System.out.println("\t\tTitulo: " + tarefa.titulo);
        System.out.println("\t\tDescrição: " + tarefa.descricao);
        System.out.println("\t\tPrazo: " + tarefa.prazo);

        if(atrazo == true){
            System.out.println("\t\tStatus: Prazo expirado"); 
        } else{
            System.out.println("\t\tStatus: Dentro do prazo");
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
            System.out.println(" 1. Adicionar tarefa\n 2. Atualizar tarefa\n 3. Verificar tarefa\n 4. Sair");
            System.out.print(" Digite uma opção: ");
            opcao = input.nextInt();
            input.nextLine();

            if(opcao == 4){
                break;
            }
            switch (opcao) {
            case 1:
                System.out.println("\n\t==Nova tarefa==");
                System.out.print("\tTítulo: ");
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
                    Tarefa.atualizarTarefa(0, input, formato);
                } 
                
                if (Tarefa.tarefas.size() > 1){
                    Tarefa.escolhaTarefa(input);
                    Tarefa.atualizarTarefa(Tarefa.escolhaTarefa(input), input, formato);
                }
                break;
                
            case 3:
                int posicao = Tarefa.escolhaTarefa(input);
                Tarefa.detalhesTarefa(posicao);
                break;

            default:
                System.out.println("\n\tDigite uma opção válida");
                break;
            }
        } while(opcao != 4);
        input.close();
    }
}