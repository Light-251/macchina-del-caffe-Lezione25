package it.softwareinside.Lezione25MacchinettaDelCaffe.models;

import it.softwareinside.Lezione25MacchinettaDelCaffe.exceptions.ParametriNonValidiException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Bevanda {
    @Id
    private String nome;
    private int costoInCentesimi;
    private int qntCaffe;
    private int qntLatte;
    private int qntCacao;
    private int qntZucchero;

    public Bevanda(String nome, int prezzo, int caffe, int latte, int cacao, int zucchero)
            throws ParametriNonValidiException {
        if (nome == null || prezzo < 0 || caffe < 0 || latte < 0 || cacao < 0 || zucchero < 0)
            throw new ParametriNonValidiException();
        if (caffe == 0 && latte == 0)
            throw new ParametriNonValidiException();
        this.nome = nome;
        costoInCentesimi = prezzo;
        qntCaffe = caffe;
        qntLatte = latte;
        qntCacao = cacao;
        qntZucchero = zucchero;
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    @Override
    public boolean equals(Object bevanda) {
        if (bevanda == null || !(bevanda instanceof Bevanda))
            return false;
        
        Bevanda bevandaTmp = (Bevanda) bevanda;
        
        if (this.nome.equals(bevandaTmp.getNome()))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + ", costo: " + this.costoInCentesimi + ", caffe: " + this.qntCaffe + ", latte: "
                + this.qntLatte + ", cacao: " + this.qntCacao + ", zucchero: " + this.qntZucchero;
    }

}
