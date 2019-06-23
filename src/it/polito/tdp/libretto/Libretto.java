package it.polito.tdp.libretto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Libretto {

	private List<Voto> voti;
	// cerco di definire in modo piu' generico possibile
	
	public Libretto() {
		this.voti = new ArrayList<Voto>();
	}
	
	/*public void add(int voto, String corso, LocalDate data) {
		
	}*/
	
	/**
	 * Aggiungere un nuovo voto al libetto
	 * 
	 * @param v il {@link Voto} da aggiungere
	 * @return {@code true} nel caso normale,
	 * 			 {@code false} se non e' riuscito ad aggiungerre il voto
	 */
	
	public boolean add(Voto v) {
		if(!this.esisteGiaVoto(v) && !this.votoConflitto(v)) {
			voti.add(v);
			return true;
		}
		else {
			return false;
		}
	}
	
	//La secnda soluzione e' quella buona, se un domani modifichiamo la
	// struttura dati della classe voto -> devo cambiare anche
	// libretto.add nel caso del primo add(voto, corso, data) 
	
	/**
	 * Seleziona il sottoinsieme di voti che hanno il punteggio specificato
	 * 
	 * @param punti punteggio da ricercare
	 * @return lista di {@link Voto} aventi quel punteggio
	 */
	public List<Voto> cercaVoti(int punti){
		List<Voto> result = new ArrayList<Voto>();
		
		for(Voto v : this.voti) {
			if(v.getPunti() == punti) {
				result.add(v);
			}
		}
		return result;
	}
	
	/**
	 * Ricerca un voto relativo al corso di cui specifico il nome
	 * @param nomeEsame nome del corso da ricercare
	 * @return il {@link Voto} corrispondente, oppure {@code null} se non esiste
	 */
	
	public Voto cercaEsame(String nomeEsame) {
		
		/*for(Voto v: this.voti) {
			if(v.getCorso().equals(nomeEsame)) {
				return v;
				}
			}
		return null;*/
		Voto voto = new Voto(0, nomeEsame, null);
		//indexof confronta solo il nome esame, uguale con il metodo equals confronto solo nome corso
		int pos = this.voti.indexOf(voto);
		if(pos == -1)//se restituisce meno uno l'oggetto non e' stato trovato
			return null;
		else 
			return this.voti.get(pos);
	}
	
	
	/**
	 * Dato un {@link Voto}, determino se esiste gia un voto 
	 * con uguale corso e punteggio.
	 * @param v
	 * @return {@code true}, se ho trovato un corso e punteggi uguali ,{@code false} non ha trovato il corso o l'ha trovato con punti diversi 
	 */
	
	
	public boolean esisteGiaVoto(Voto v) {
		/*Voto trovato = this.cercaEsame(v.getCorso());
		if(trovato == null)
			return false;
		if(trovato.getPunti()==v.getPunti()) {
			return true;
		} else {
			return false;
		}*/
		int pos = this.voti.indexOf(v);
		if (pos == -1)
			return false;
		else
			return (v.getPunti() == this.voti.get(pos).getPunti());
		// mi dice true se e' vera, false se e' falsa
			
		
	}
	/**
	 * Mi dice se il voto v e' in conflitto con uno dei voti esistenti
	 * se il voto non esiste, non c'e' conflitto. Se esiste ed ha punteggio diverso c'e' conflitto
	 * @param v
	 * @return {@code true} se il voto esiste ed ha punteggio diverso, {@code false} se il voto non esiste o se esiste ma ha lo stesso punteggio
	 */
	
	public boolean votoConflitto(Voto v) {
		int pos = this.voti.indexOf(v);
		if (pos == -1)
			return false;
		else
			return (v.getPunti() != this.voti.get(pos).getPunti());
	}
	
	public String toString() {
		return this.voti.toString();
	}
	
	public Libretto librettoMigliorato() {
		Libretto nuovo = new Libretto();
		for(Voto v : this.voti) {
			nuovo.add(v.clone());
		}
		// essendo voti privati, io in libretto lo posso fare, fuori dalla classe libretto no!!!
		for(Voto v : nuovo.voti) {
			int punti = v.getPunti();
			if(punti < 24)
				punti = punti +1;
			else if(punti <= 28)
				punti = punti +2;
			v.setPunti(punti);
		}
		return nuovo;
	}
	
	public void cancellaVotiScarsi() {
		List<Voto> cancellare = new ArrayList<Voto>();
		for(Voto v : this.voti) {
			if(v.getPunti()<24) {
				cancellare.add(v);
			}
		}
		this.voti.removeAll(cancellare);
	}//posso modificare la collection SOLO se non la sto iterando???
	
	
	
}