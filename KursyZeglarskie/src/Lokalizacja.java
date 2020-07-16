import java.util.ArrayList;
import java.util.List;

public class Lokalizacja extends ObjectPlus{

	private String nazwa;
	private int raport;

	public Lokalizacja(String nazwa) {
		super();
		setNazwa(nazwa);
	}

	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public int stworzRaport() {
		int raport=0;
		for(Jacht j : getJachty())
			raport+=j.getMiejsca();
		this.raport=raport;
		return this.raport;
	}
	
	
	
	/*
	 * Asocjacja z Kurs (1 Lokalizacja - * Kurs)
	 */
	
	private List<Kurs> kursy=new ArrayList<>();
	
	public ArrayList<Kurs> getKursy() {
		return (ArrayList<Kurs>) kursy;
	}
	
	public void addKurs(Kurs kurs) {
		if(!kursy.contains(kurs)) {
			kursy.add(kurs);
			kurs.setLokalizacja(this);
		}
	}
	
	public void removeKurs(Kurs kurs) {
		kursy.remove(kurs);
	}
	
	/*
	 * Asocjacja z Jacht (1 Lokalizacja - * Jacht)
	 */
	
	private List<Jacht> jachty=new ArrayList<>();
	
	public ArrayList<Jacht> getJachty() {
		return (ArrayList<Jacht>) jachty;
	}
	
	public void addJacht(Jacht jacht) {
		if(!jachty.contains(jacht)) {
			jachty.add(jacht);
			jacht.setLokalizacja(this);
		}
	}
	
	public void removeJacht(Jacht jacht) {
		jachty.remove(jacht);
	}
}
