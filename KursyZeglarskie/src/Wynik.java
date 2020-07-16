
enum Ocena {ZALICZONY, NIEZALICZONY}

/*
 * Klasa asocjacyjna (Kursant, Egzamin)
 */

public class Wynik extends ObjectPlus{

	private int zaliczoneZadania;
	private Kursant kursant;
	private Egzamin egzamin;
	private Ocena ocena;
	
	
	public Wynik(int zaliczoneZadania, Kursant kursant, Egzamin egzamin) {
		super();
		setZaliczoneZadania(zaliczoneZadania);
		setKursant(kursant);
		setEgzamin(egzamin);
		setOcena(zaliczoneZadania);
	}

	public int getZaliczoneZadania() {
		return zaliczoneZadania;
	}
	public void setZaliczoneZadania(int zaliczoneZadania) {
		this.zaliczoneZadania = zaliczoneZadania;
	}
	
	public Kursant getKursant() {
		return kursant;
	}
	public void setKursant(Kursant kursant) {
		this.kursant=kursant;
		kursant.addWynik(this);
	}
	
	public Egzamin getEgzamin() {
		return egzamin;
	}
	public void setEgzamin(Egzamin egzamin) {
		this.egzamin=egzamin;
		egzamin.addWynik(this);
	}
	
	public Ocena getOcena() {
		return ocena;
	}
	public void setOcena(int zaliczoneZadania) {
		if(zaliczoneZadania>=getEgzamin().getProg())
			this.ocena=Ocena.ZALICZONY;
		else
			this.ocena=Ocena.NIEZALICZONY;
	}
	
	@Override
	public String toString() {
		return " Zdobyte punkty: "+getZaliczoneZadania()+" | Ocena: "+getOcena();
	}
	
}
