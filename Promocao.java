public class Promocao extends Produto {
    private double desconto; // Variável para armazenar o desconto
    private double precodesconto; // Variável para armazenar o preço com desconto
    
    // Construtor da classe Promocao
    public Promocao(String setor, String nome, String validade, int ndeserie, double preco, double quantidade, boolean promo, double desconto) {
        super(setor, nome, validade, ndeserie, preco, quantidade, promo);
        this.desconto = desconto; // Atribui o desconto fornecido ao atributo desconto da classe
    }

    // Método getter para obter o preço com desconto
    public double getPrecodesconto() {
        return precodesconto;
    }

    // Método setter para definir o preço com desconto
    public void setPrecodesconto(double precodesconto) {
        this.precodesconto = precodesconto;
    }

    // Método getter para obter o desconto
    public double getDesconto() {
        return desconto;
    }

    // Método setter para definir o desconto
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
    
    // Método para aplicar o desconto ao preço e definir o preço com desconto
    public void AplicarDesconto(double preco, double desconto) {
        setPrecodesconto(preco - (preco * (desconto / 100)));
    }
  
    // Método toString para retornar uma representação em String do objeto Promocao
    @Override
    public String toString() {
        return "Produto: " +
                "Setor='" + setor + '\'' +
                ", Nome='" + nome + '\'' +
                ", Validade='" + validade + '\'' +
                ", Numero de serie= " + ndeserie +
                ", Preco= R$ " + preco +
                ", Quantidade= " + quantidade +
                ", Desconto= " + desconto +
                ", Preco com desconto= R$ " + precodesconto;
    }
}
