package exemplo.barzinholuyner.model;

public class Bebida {
    private int id;
    private String nome;
    private String tipo;
    private double volume;
    private double preco;

    // Construtor com parâmetros
    public Bebida(int id, String nome, String tipo, double volume, double preco) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.volume = volume;
        this.preco = preco;
    }

    // Construtor sem parâmetros
    public Bebida() {
        // Inicializa com valores padrão, se necessário
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public double getVolume() {
        return volume;
    }

    public double getPreco() {
        return preco;
    }

    // Setters com validação
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
    }

    public void setTipo(String tipo) {
        if (tipo != null && !tipo.trim().isEmpty()) {
            this.tipo = tipo;
        } else {
            throw new IllegalArgumentException("Tipo não pode ser vazio.");
        }
    }

    public void setVolume(double volume) {
        if (volume > 0) {
            this.volume = volume;
        } else {
            throw new IllegalArgumentException("Volume deve ser maior que zero.");
        }
    }

    public void setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
        } else {
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
    }

    @Override
    public String toString() {
        return "Bebida{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", volume=" + volume +
                ", preco=" + preco +
                '}';
    }
}