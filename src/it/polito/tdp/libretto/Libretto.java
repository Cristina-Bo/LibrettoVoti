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
	 * 	
	 */
	
	public void add(Voto v) {
		voti.add(v);
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
	
	public Voto cercaVoto(String nomeEsame) {
		Voto voto = new Voto(0, nomeEsame, null);
		//indexof confronta solo il nome esame
		int pos = this.voti.indexOf(voto);
		if(pos == -1)//se restituisce meno uno l'oggetto non e' stato trovato
			return null;
		else 
			return this.voti.get(pos);
	}
	
	
	/** 
	 * Mi dice se il {@link Voto} {@code v} e' in conflitto con uno dei voti
	 * esistenti. Se il voto non esiste, non c'e' conflitto, oppure esiste e ha lo stesso puntegggio.
	 * @param v
	 * @return true se il voto esiste e 
	 */
	
	
	public boolean esisteGiaVoto(Voto v) {
		int pos = this.voti.indexOf(v);
		if (pos == -1)
			return false;
		else
			return (v.getPunti() == this.voti.get(pos).getPunti());
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