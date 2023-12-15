package dominio;

import java.util.Date;

public class AplicacaoMedicacao {
    private int idAplicacao;
    private int idMedicacao;
    private int idAnimal;
    private int idCuidador;
    private Date dataAplicacao;

    public int getIdAplicacao() {
        return idAplicacao;
    }

    public void setIdAplicacao(int idAplicacao) {
        this.idAplicacao = idAplicacao;
    }

    public int getIdMedicacao() {
        return idMedicacao;
    }

    public void setIdMedicacao(int idMedicacao) {
        this.idMedicacao = idMedicacao;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdCuidador() {
        return idCuidador;
    }

    public void setIdCuidador(int idCuidador) {
        this.idCuidador = idCuidador;
    }

    public Date getDataAplicacao() {
        return dataAplicacao;
    }

    public void setDataAplicacao(Date dataAplicacao) {
        this.dataAplicacao = dataAplicacao;
    }
}
