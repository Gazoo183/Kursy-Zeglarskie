import java.time.LocalDate;
import java.util.List;

public class EgzaminKoncowy extends Egzamin{
	
	public EgzaminKoncowy(Typ typ, Termin termin, Status status, List<String> zadania, LocalDate dataPisania, Kurs kurs) {
		super(typ, termin, status, zadania, dataPisania);
		setKurs(kurs);
	}
	
	/*
	 * Asocjacja z Kurs (1 EgzaminKoncowy - 1 Kurs)
	 */
	
	private Kurs kurs;
	
	public Kurs getKurs() {
		return kurs;
	}
	
	public void setKurs(Kurs kurs) {
		if(this.kurs==null) {
			this.kurs=kurs;
			kurs.addEgzaminKoncowy(this);
		} else if(!this.kurs.equals(kurs)) {
			this.kurs.removeEgzaminKoncowy(this);
			this.kurs=kurs;
			kurs.addEgzaminKoncowy(this);
		}
	}

}
