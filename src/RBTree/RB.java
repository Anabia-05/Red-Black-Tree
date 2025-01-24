package RBTree;

import Queue.Queue;

public class RB<T extends Comparable<T>> {
    private RBNode<T> raiz;
    private RBNode<T> nil;

    public RB() {
        // Inicializa a árvore com um nó nil, pois inicialmente está vazia
        nil = new RBNode<>(null);
        nil.setCor('b'); 
        this.raiz = nil;
    }
    private boolean isEmpty(){
        if(this.raiz == nil){
            return true;
        }
        else{
            return false;
        }
    }
    
    
    public void inserir(T valor) {
        //cria nó novo e percorre arvore para ver o local que vai inserir e chama funcao de ajuste da red-black
        RBNode<T> novo = new RBNode<>(valor);
        novo.setDir(nil);
        novo.setEsq(nil);
        novo.setCor('r');
        novo.setStatus(1);
    
        RBNode<T> atual = this.raiz;
        RBNode<T> anterior = nil;
    
        while (atual != nil) {
            int resultado = valor.compareTo(atual.getInfo());
            anterior = atual;
    
            if (resultado == 0) {
                if (atual.getStatus() == 0) {
                    atual = atual.getDir();
                } else {
                    System.out.println("Valor Repetido");
                    return;
                }
            } else if (resultado < 0) {
                atual = atual.getEsq();
            } else {
                atual = atual.getDir();
            }
        }
    
        novo.setPai(anterior);
        if (anterior == nil) {
            this.raiz = novo;
        } else if (novo.getInfo().compareTo(anterior.getInfo()) < 0) {
            anterior.setEsq(novo);
        } else {
            anterior.setDir(novo);
        }
        
        ajustesInsercao(novo);
    }
    
    // Ajusta a árvore após a inserção para manter as propriedades Vermelho e pretas
    private void ajustesInsercao(RBNode<T> K){
        //considere b para preto e r para vermelho
        RBNode<T> P,G,S;
        //faz a recoloração ou rotacao dependendo do tio
        while(K.getPai() != nil && K.getPai().getCor() == 'r'){
            P = K.getPai();
            G = P.getPai();

            if(P == G.getEsq()){
                S = G.getDir();
                if(S != nil && S.getCor() == 'r'){
                    P.setCor('b');
                    S.setCor('b');
                    G.setCor('r');
                    K = G;
                }
                else{
                     if(K == P.getDir()){
                        rotacaoEsq(P);
                        K = P;
                     }
                     rotacaoDir(G);
                     
                }
            }
            else{
                S = G.getEsq();
                if(S != nil && S.getCor() == 'r'){
                    P.setCor('b');
                    S.setCor('b');
                    G.setCor('r');
                    K = G;
                }
                else{
                    if(K == P.getEsq()){
                        rotacaoDir(P);
                        K = P;
                    }
                    rotacaoEsq(G);
                }
            }
        }

        this.raiz.setCor('b');
    }


    private void rotacaoEsq(RBNode<T> A){
        //rotacao simples a esquerda
        RBNode<T> B = A.getDir();
        if (B == nil) return;

        A.setDir(B.getEsq());
        if(B.getEsq() != nil){
            B.getEsq().setPai(A);
        }

        B.setPai(A.getPai());
        if(A.getPai() == nil){
            this.raiz = B;
        }
        else if(A == A.getPai().getEsq()){
            A.getPai().setEsq(B);
        }
        else{
            A.getPai().setDir(B);
        }
        B.setEsq(A);
        B.setCor('b');
        A.setPai(B);
        A.setCor('r');
        
    }

    private void rotacaoDir(RBNode<T> A){
        //rotacao simples a direita
        RBNode<T> B = A.getEsq();
        if (B == nil) return;

        A.setEsq(B.getDir());
        if(B.getDir() != nil){
            B.getDir().setPai(A);
        }

        B.setPai(A.getPai());
        if(A.getPai() == nil){
            this.raiz = B;
        }
        else if(A == A.getPai().getDir()){
            A.getPai().setDir(B);
        }
        else{
            A.getPai().setEsq(B);
        }
        B.setDir(A);
        B.setCor('b');
        A.setPai(B);
        A.setCor('r');
        
        
    }


    public void remocaoP(T valor){
        //Faz uma busca para achar o no a ser removido e altera seu status de 1 para o 0
        RBNode<T> aux;
        int retorno;
        if(isEmpty() == true){
            System.out.println("Arvore Vazia");
        }
        else{
            aux = this.raiz;
            while(aux != nil){
                retorno = valor.compareTo(aux.getInfo());
                if(retorno == 0){
                    aux.setStatus(0); // Marca o nó como inativo
                    return;
                }
                else if(retorno<0){
                    aux = aux.getEsq();
                    
                }
                else{
                    aux = aux.getDir();
                }
            }
        }
        System.out.println("Valor nao encontrado");

    }

    public void emOrdem(){
        if(this.isEmpty() == true){
            System.out.println("arvore vazia");
        }
        else{
            percorrerEmOrdem(this.raiz);
        }
    }
    private void percorrerEmOrdem(RBNode<T> r){
        //passeio em ordem com verificacao se o no esta ou nao ativo
        if(r != null){
            percorrerEmOrdem(r.getEsq());
            if(r.getStatus() == 1){
                 // Imprime informações do nó com indicação de cor para melhor visualização
                if(r.getCor() == 'r'){
                    System.out.println("\u001B[31m" + "Valor:" + r.getInfo() + " Cor: "+ r.getCor() + "\u001B[0m"); 
                }
                else{
                    System.out.println("\u001B[30m" + "Valor:" + r.getInfo() + " Cor: "+ r.getCor() + "\u001B[0m"); 

                }
                           
            }
            percorrerEmOrdem(r.getDir());
        }
    }

    public void porNivel(){
        //passeio por nivel incluindo nós nil e verificacao de imprimir apenas ativos
        Queue<RBNode<T>> fila;
        RBNode<T> aux;

        if(this.isEmpty() == false){
            fila = new Queue<RBNode<T>>();
            fila.enQueue(this.raiz);
            while(fila.isEmpty() == false){
                aux = fila.deQueue();
                if(aux == nil){
                    System.out.println("\u001B[30m" + "nil" + "\u001B[0m");
                }
                else{
                    if(aux.getStatus() == 1){ 
                        if(aux.getCor() == 'r'){
                            System.out.println("\u001B[31m" + "Valor:" + aux.getInfo() + " Cor: "+ aux.getCor() + "\u001B[0m");
                        }
                        else{
                            System.out.println("\u001B[30m" + "Valor:" + aux.getInfo() + " Cor: "+ aux.getCor() + "\u001B[0m"); 
                        }
                    }

                    fila.enQueue(aux.getEsq());
                    fila.enQueue(aux.getDir());
                }
            }
        }
    
         else { 
            System.out.println("Árvore vazia");
        }
    }
}
