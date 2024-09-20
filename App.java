import java.util.Scanner;

public class App {
    public static void main (String[] args) {

        RB<Integer> arvore = new RB<Integer>();
        int op,num;
       

        
        Scanner input = new Scanner(System.in);
    
        do {
            exibirOpcoes();
            op = input.nextInt();
            input.nextLine();
            switch(op) {
                case 1: System.out.print("Insira valor: ");
                    num = input.nextInt();
                    arvore.inserir(num);
                    System.out.println();
                    break;
                case 2:
                arvore.passeioPorNivel();
                    
                    break;
                case 3:                 
                    break;
                case 4: 
                    
                    break;
                case 0:
                    System.out.println("Bye bye!");
                    break;                    
            }
        } while (op != 0);
        input.close();
    }
    public static void exibirOpcoes () {
        System.out.println("Opções");
        System.out.println("1 - Inserir");
        System.out.println("2 - Exibir por nivel");
        System.out.println("3 - Exibir em Ordem");
        System.out.println("4 - Altura");
        System.out.println("0 - Encerrar programa");
        System.out.print("Informe a opção: ");
    }
}