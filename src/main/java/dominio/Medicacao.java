package dominio;

public class Medicacao {
    private int idMedicacao;
    private String nomeMedicacao;
    private float mlPorKg;

    public int getIdMedicacao() {
        return idMedicacao;
    }

    public void setIdMedicacao(int idMedicacao) {
        this.idMedicacao = idMedicacao;
    }

    public String getNomeMedicacao() {
        return nomeMedicacao;
    }

    public void setNomeMedicacao(String nomeMedicacao) {
        this.nomeMedicacao = nomeMedicacao;
    }

    public float getMlPorKg() {
        return mlPorKg;
    }

    public void setMlPorKg(float mlPorKg) {
        this.mlPorKg = mlPorKg;
    }

    @Override
    public String toString() {
        return nomeMedicacao != null ? nomeMedicacao : "Nome não disponível";
        // Ou, se desejar mostrar mais informações:
        // return nomeMedicacao + " - " + mlPorKg + " ml/Kg";
    }
}
