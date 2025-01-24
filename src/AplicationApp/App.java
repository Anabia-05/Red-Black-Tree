package AplicationApp;
import java.util.Scanner;

import RBTree.RB;

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
                    break;
                case 2: System.out.print("Remover valor: ");
                    num = input.nextInt();
                    arvore.remocaoP(num);
                    break;
                
                case 3: arvore.emOrdem();             
                    break;
                case 4: arvore.porNivel();                  
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
        System.out.println("2 - Remover");
        System.out.println("3 - Exibir em Ordem");
        System.out.println("4 - Exibir por nivel");
        System.out.println("0 - Encerrar programa");
        System.out.print("Informe a opção: ");
    }
}