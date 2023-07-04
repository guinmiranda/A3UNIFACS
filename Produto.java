public class Produto {

    public String setor, nome, validade; // Variáveis para armazenar informações do produto
    public int ndeserie; // Variável para o número de série do produto
    public double preco, quantidade; // Variáveis para o preço e quantidade do produto
    public boolean promo; // Variável para indicar se o produto está em promoção ou não

    // Construtor da classe Produto
    public Produto(String setor, String nome, String validade, int ndeserie, double preco, double quantidade, boolean promo) {
        this.setor = setor;
        this.nome = nome;
        this.validade = validade;
        this.ndeserie = ndeserie;
        this.preco = preco;
        this.quantidade = quantidade;
        this.promo = promo;
    }

    // Método setter para definir se o produto está em promoção
    public void setPromo(boolean promo) {
        this.promo = promo;
    }

    // Método setter para definir o setor do produto
    public void setSetor(String setor) {
        this.setor = setor;
    }

    // Método setter para definir o nome do produto
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método setter para definir o número de série do produto
    public void setNdeSerie(int ndeserie) {
        this.ndeserie = ndeserie;
    }

    // Método setter para definir o preço do produto
    public void setPreco(double preco) {
        this.preco = preco;
    }

    // Método setter para definir a quantidade do produto
    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    // Método getter para obter o setor do produto
    public String getSetor() {
        return setor;
    }

    // Método getter para obter o nome do produto
    public String getNome() {
        return nome;
    }

    // Método getter para obter a validade do produto
    public String getValidade() {
        return validade;
    }

    // Método getter para obter o número de série do produto
    public int getNdeserie() {
        return ndeserie;
    }

    // Método getter para obter o preço do produto
    public double getPreco() {
        return preco;
    }

    // Método getter para obter a quantidade do produto
    public double getQuantidade() {
        return quantidade;
    }
    
    // Método getter para verificar se o produto está em promoção
    public boolean isPromo() {
        return promo;
    }

    // Método toString para retornar uma representação em String do objeto Produto
    @Override
    public String toString() {
        return "Produto: " +
                "Setor='" + setor + '\'' +
                ", Nome='" + nome + '\'' +
                ", Validade='" + validade + '\'' +
                ", Numero de serie=" + ndeserie +
                ", Preco=R$ " + preco +
                ", Quantidade=" + quantidade;
    }

    // Método toString2 para retornar uma representação simplificada em String do objeto Produto
    public String toString2() {
        return "Produto: " +
                "Numero de serie=" + ndeserie +
                ", Nome='" + nome + '\'' +
                ", Preco= R$ " + preco;
    }

}
