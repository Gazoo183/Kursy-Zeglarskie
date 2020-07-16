import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

enum Typ {PRAKTYCZNY, TEORETYCZNY}
enum Termin {PIERWSZY, DRUGI}
enum Status {ULOZONY, ZDAWANY, SPRAWDZANY}

public abstract class Egzamin extends ObjectPlus{

	private Typ typ;
	private Termin termin;
	private Status status;
	private List<String> zadania;
	private LocalDate dataPisania;
	private int prog;
	
	public Egzamin(Typ typ, Termin termin, Status status, List<String> zadania, LocalDate dataPisania) {
		super();
		setTyp(typ);
		setTermin(termin);
		setStatus(status);
		setZadania(zadania);
		setProg((int)Math.ceil(zadania.size()/2.0));
	}

	public Typ getTyp() {
		return typ;
	}
	public void setTyp(Typ typ) {
		this.typ = typ;
	}

	public Termin getTermin() {
		return termin;
	}
	public void setTermin(Termin termin) {
		this.termin = termin;
	}

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public List<String> getZadania() {
		return zadania;
	}
	public void setZadania(List<String> zadania) {
		this.zadania = zadania;
	}
	
	public LocalDate getDataPisania() {
		return dataPisania;
	}
	public void setDataPisania(LocalDate dataPisania) {
		this.dataPisania = dataPisania;
	}
	
	public int getProg() {
		return prog;
	}
	public void setProg(int prog) {
		this.prog = prog;
	}
	
	@Override
	public String toString() {
		return "Typ: "+getTyp()+"\n"+" Ilość zadań: "+getZadania().size()+"\n"+" Próg zdawalności: "+getProg();
	}
	
	/*
	 * Asocjacja z Wynik (1 Egzamin - * Wynik)
	 */
	
	private List<Wynik> wyniki=new ArrayList<>();
	
	public void addWynik(Wynik wynik) {
		if(!wyniki.contains(wynik))
			wyniki.add(wynik);
	}
}
