package dominio;

public class Vivedouro {
    private int idVivedouro;
    private String vivedouro;
    private String tamanho;

    public int getIdVivedouro() {
        return idVivedouro;
    }

    public void setIdVivedouro(int idVivedouro) {
        this.idVivedouro = idVivedouro;
    }

    public String getVivedouro() {
        return vivedouro;
    }

    public void setVivedouro(String vivedouro) {
        this.vivedouro = vivedouro;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return vivedouro != null ? vivedouro : "Nome não disponível";
        // Ou, se desejar mostrar mais informações:
        // return nomeMedicacao + " - " + mlPorKg + " ml/Kg";
    }
}
