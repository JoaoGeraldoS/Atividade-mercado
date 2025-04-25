package model;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private Categoria categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto {" +
                "ID = " + id +
                ", Nome = '" + nome + '\'' +
                ", Preço = " + preco +
                ", Quantidade = " + quantidade +
                ", Categoria = " + (categoria != null ? categoria.getNome() : "Sem categoria") +
                '}';
    }

}
