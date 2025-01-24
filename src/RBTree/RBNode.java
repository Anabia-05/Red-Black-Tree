package RBTree;

class RBNode<T extends Comparable<T>>{
    //Define atributos do nó da arvore
    private RBNode<T> pai;
    private T info;
    private RBNode<T> esq;
    private RBNode<T> dir;
    private char cor;
    private int status;

    RBNode(T valor){
        this.info = valor;
    }

    // Gets e Sets tradicionais
    RBNode<T> getPai() {
        return this.pai;
    }
    void setPai(RBNode<T> pai) {
        this.pai = pai;
    }
    T getInfo() {
        return this.info;
    }
    void setInfo(T info) {
        this.info = info;
    }
    RBNode<T> getEsq() {
        return this.esq;
    }
    void setEsq(RBNode<T> esq) {
        this.esq = esq;
    }
    RBNode<T> getDir() {
        return this.dir;
    }
    void setDir(RBNode<T> dir) {
        this.dir = dir;
    }

    char getCor(){
        return this.cor;
    }

    void setCor(char cor){
        this.cor = cor;
    }

    int getStatus(){
        return this.status;
    }
    void setStatus(int status){
        this.status = status;
    }

}