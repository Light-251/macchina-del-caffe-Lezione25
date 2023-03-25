package it.softwareinside.Lezione25MacchinettaDelCaffe.models;

import java.util.ArrayList;
import java.util.Scanner;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

@Entity
public class MacchinaDelCaffe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(cascade = CascadeType.ALL)
	private ArrayList<Bevanda> bevande;

	@OneToOne(cascade = CascadeType.ALL)
	protected Ingredienti ingredienti;

	private int creditoUtente;

	public MacchinaDelCaffe() {
		setCreditoUtente(0);
		this.bevande = new ArrayList<Bevanda>();
		this.ingredienti = new Ingredienti();
	}

	/**
	 * Aggiunge il credito inserito dall'utente solo se è maggiore di zero
	 * 
	 * @param credito
	 */
	public void aggiungiCredito(int credito) {
		if (credito > 0)
			this.creditoUtente += credito;
	}

	/**
	 * Azzera il credito utente restituendo il numero di centesimi rimossi
	 * 
	 * @return
	 */
	public int restituisciCreditoUtente() {
		int creditoTmp = getCreditoUtente();
		setCreditoUtente(0);
		return creditoTmp;
	}

	/**
	 * Aggiunge una bevanda se non è presente
	 * 
	 * @param bevandaDaAggiungere
	 */
	public boolean aggiuntaBevanda(Bevanda bevandaDaAggiungere) {
		if (!bevande.contains(bevandaDaAggiungere)) {
			this.bevande.add(bevandaDaAggiungere);
			return true;
		}
		return false;

	}

	/**
	 * Controlla se una bevanda presa in ingresso è presente nella macchinetta
	 * 
	 * @param bevandaDaControllare
	 * @return
	 */
	public boolean isBevandaPresent(Bevanda bevandaDaControllare) {
		if (bevande.contains(bevandaDaControllare))
			return true;

		return false;
	}

	/**
	 * Ricostituisce la quantità iniziale di ingredienti
	 * 
	 */
	public void ricaricaIngredienti() {
		ingredienti.riempi();
	}

	/**
	 * Prende in ingresso il nome della bevanda come stringa Eroga la bevanda presa
	 * in ingresso se è presente se il credito è sufficiente se ci sono abbastanza
	 * ingredienti
	 * 
	 * altrimenti ritorna false
	 * 
	 * @param nomeBevanda
	 * @return
	 */
	public boolean eroga(String nomeBevanda) {
		for (Bevanda bevanda : bevande) {
			if (bevanda.getNome().equals(nomeBevanda)) {
				if (bevanda.getCostoInCentesimi() <= getCreditoUtente()
						&& ingredienti.ingredientiSufficienti(bevanda)) {
					bevande.remove(bevanda);
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * Prende in ingresso una bevanda e modifica una bevanda con lo stesso nome solo
	 * se è presente se la modifica va a buon fine ritorna true; altrimenti ritorna
	 * false
	 * 
	 * @param bevandaModificata
	 * @return
	 */
	public boolean modificaBevanda(Bevanda bevandaModificata) {
		if (isBevandaPresent(bevandaModificata))
			for (Bevanda bevanda : bevande) {
				if (bevanda.equals(bevandaModificata)) {
					bevande.remove(bevanda);
					bevande.add(bevandaModificata);
					return true;
				}
			}
		return false;
	}

	/**
	 * Restituisce un array di interi che esprimono un voto dell'utente (da 1 a 5, 0
	 * = giudizio inespresso) per le bevande erogate
	 * 
	 * Per ogni bevanda erogabile il metodo chiede all'utente se vuole esprimere un
	 * giudizio, se si viene chiesto il voto reiterando la richiesta fino a che
	 * viene inserito un voto valido Alla fine il metodo stampa a video il valore
	 * del massimo, del minimo e della media dei voti senza contare i voti
	 * inespressi
	 * 
	 */
	public int[] questionarioSoddisfazione() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Vuoi lasciare una recensione? (s/n)");
		String ris;
		int[] voti = null;
		do {
			ris = scanner.nextLine();

			switch (ris) {
			case "s":
				voti = valutazioneUtente();
				break;
			case "n":
				System.out.println("Alla prossima!");
				break;
			default:
				System.err.println("Inserisci una risposta valida");
				break;
			}

		} while (!ris.toLowerCase().equals("s") && !ris.toLowerCase().equals("n"));
		scanner.close();
		return voti;

	}

	public int[] valutazioneUtente() {
		Scanner scanner = new Scanner(System.in);
		int[] voti = new int[bevande.size()];
		int maxVoto = 1, minVoto = 1, voto, somma = 0, contVoti = 0;
		for (int i = 0; i < bevande.size(); i++) {
			do {
				System.out.println("Inserisci un voto per " + bevande.get(i).getNome());
				voto = scanner.nextInt();
			} while (voto < 0 || voto > 5);
			voti[i] = voto;
			somma += voto;

			if (voto != 0) // Se il voto è 0 non viene contato nella media
				contVoti++;
			if (maxVoto < voto && voto != 0)
				maxVoto = voto;
			if (minVoto > voto && voto != 0)
				minVoto = voto;
		}
		scanner.close();
		double media = (double) somma / contVoti;
		System.out.println("Fine Valutazione: voto maggiore: " + maxVoto + ", voto minore: " + minVoto);
		System.out.println("Media voti: " + media);
		return voti;
	}

}
