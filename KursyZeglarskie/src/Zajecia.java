import java.util.ArrayList;
import java.util.List;

public class Zajecia extends ObjectPlus{

	private String nazwa;
	private int wymiarGodzin;
	
	public Zajecia(String nazwa, int wymiarGodzin, Instruktor prowadzacy) {
		super();
		setNazwa(nazwa);
		setWymiarGodzin(wymiarGodzin);
		setProwadzacy(prowadzacy);
	}

	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public int getWymiarGodzin() {
		return wymiarGodzin;
	}
	public void setWymiarGodzin(int wymiarGodzin) {
		this.wymiarGodzin = wymiarGodzin;
	}
	
	/*
	 * Asocjacja z EgzaminCzastkowy (1 Zajecia - * EgzaminCzastkowy)
	 */
	
	private List<EgzaminCzastkowy> egzaminy=new ArrayList<>();
	
	public ArrayList<EgzaminCzastkowy> getEgzaminy() {
		return (ArrayList<EgzaminCzastkowy>) egzaminy;
	}
	
	public void addEgzaminCzastkowy(EgzaminCzastkowy egzamin) {
		if(!egzaminy.contains(egzamin)) {
			egzaminy.add(egzamin);
			egzamin.setMaterial(this);
		}
	}
	
	public void removeEgzaminCzastkowy(EgzaminCzastkowy egzamin) {
		egzaminy.remove(egzamin);
	}
	
	/*
	 * Asocjacja z Instruktor (1 Zajecia - 0..1 Instruktor)
	 */
	
	private Instruktor prowadzacy;
	
	public Instruktor getProwadzacy() {
		return prowadzacy;
	}
	
	public void setProwadzacy(Instruktor prowadzacy) {
		if(this.prowadzacy==null) {
			this.prowadzacy=prowadzacy;
			prowadzacy.setZajecia(this);
		} else if(!this.prowadzacy.equals(prowadzacy)) {
			this.prowadzacy.removeZajecia(this);
			this.prowadzacy=prowadzacy;
			prowadzacy.setZajecia(this);
		}
	}
	
	public void removeProwadzacy(Instruktor prowadzacy) {
		if(this.prowadzacy.equals(prowadzacy)) {
			this.prowadzacy=null;
		}
	}
	
}
