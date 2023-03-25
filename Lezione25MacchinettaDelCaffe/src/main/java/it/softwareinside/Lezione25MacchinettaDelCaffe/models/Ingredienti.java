package it.softwareinside.Lezione25MacchinettaDelCaffe.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

@Entity
public class Ingredienti {
    @Id
    private int id;

    public static final int QNT_INIZIALE = 10;
    private int caffe;
    private int latte;
    private int cacao;
    private int zucchero;

    public Ingredienti() {
        riempi();
    }

    public void riempi() {
        caffe = QNT_INIZIALE;
        latte = QNT_INIZIALE;
        cacao = QNT_INIZIALE;
        zucchero = QNT_INIZIALE;
    }

    @Override
    public String toString() {
        return "caffe: " + this.getCaffe()
                + ", latte: " + this.getLatte()
                + ", cacao: " + this.getCacao()
                + ", zucchero: " + this.getZucchero() + ".";
    }

    public boolean ingredientiSufficienti(Bevanda bevanda) {
        if (this.caffe >= bevanda.getQntCaffe() && this.latte >= bevanda.getQntLatte()
                && this.cacao >= bevanda.getQntCacao() && this.zucchero >= bevanda.getQntZucchero())
            return true;
 
        return false;
    }

    public void consumaBevanda(Bevanda bevanda) {
        if (ingredientiSufficienti(bevanda)) {
            this.caffe -= bevanda.getQntCaffe();
            this.latte -= bevanda.getQntLatte();
            this.cacao -= bevanda.getQntCacao();
            this.zucchero -= bevanda.getQntZucchero();
        }

    }
}