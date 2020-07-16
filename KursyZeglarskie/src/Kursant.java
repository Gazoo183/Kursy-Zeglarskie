import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

enum Poziom {NOWICJUSZ, SZKOLONY, LICENCJONOWANY}

public class Kursant extends Osoba{
	
	private Poziom poziomUmiejetnosci;
	private String dowodTozsamosci;
	
	public Kursant(String login, String haslo, String imie, String nazwisko, LocalDate dataUrodzenia, Poziom poziomUmiejetnosci, String dowodTozsamosci, Kurs kurs) {
		super(login, haslo, imie, nazwisko, dataUrodzenia);
		setPoziomUmiejetnosci(poziomUmiejetnosci);
		setDowodTozsamosci(dowodTozsamosci);
		addKurs(kurs);
	}
	
	public Kurs inProgress() {
		Kurs toReturn=null;
		
		try {
			toReturn = this.kursy.stream().filter(x -> (x.getDataRozpoczecia().isBefore(LocalDate.now()) || x.getDataRozpoczecia().isEqual(LocalDate.now())) && (x.getDataZakonczenia().isAfter(LocalDate.now()) || x.getDataZakonczenia().isEqual(LocalDate.now()))).findFirst().get();
		} catch (Exception e) {}
		
		return toReturn;
	}
	
	public Wynik[][] getDataForTable() throws ClassNotFoundException {
		Wynik[][] toReturn=new Wynik[this.getWyniki().size()][1];
		
		for(int i=0;i<this.getWyniki().size();i++)
			toReturn[i][0]=this.getWyniki().get(i);
		
		return toReturn;
	}

	public Poziom getPoziomUmiejetnosci() {
		return poziomUmiejetnosci;
	}
	public void setPoziomUmiejetnosci(Poziom poziomUmiejetnosci) {
		this.poziomUmiejetnosci = poziomUmiejetnosci;
	}

	public String getDowodTozsamosci() {
		return dowodTozsamosci;
	}
	public void setDowodTozsamosci(String dowodTozsamosci) {
		this.dowodTozsamosci = dowodTozsamosci;
	}
	
	public static Kursant validate(String login, String haslo) throws ClassNotFoundException {
		Kursant toReturn=null;
		try {
			toReturn=getExtent().stream().filter(kursant -> (kursant.getLogin().equals(login) && kursant.getHaslo().equals(haslo))).findAny().get();
		} catch (Exception e) {}
		return toReturn;
	}
	
	public static ArrayList<Kursant> getExtent() throws ClassNotFoundException {
		return (ArrayList) getExtent(Kursant.class);
	}

	/*
	 * Asocjacja z Opiekun (1 Kursant - 0..1 Opiekun)
	 */
	
	private Opiekun opiekun;
	
	public Opiekun getOpiekun() {
		return opiekun;
	}
	
	public void setOpiekun(Opiekun opiekun) {
		if(this.opiekun==null) {
			this.opiekun=opiekun;
			opiekun.addKursant(this);
		} else if(!this.opiekun.equals(opiekun)) {
			this.opiekun.removeKursant(this);
			this.opiekun=opiekun;
			opiekun.addKursant(this);
		}
	}
	
	/*
	 * Asocjacja z Jacht (1 Kursant - 1 Jacht)
	 */
	
	private Jacht jacht;
	
	public Jacht getJacht() {
		return jacht;
	}
	
	public void setJacht(Jacht jacht) {
		if(this.jacht==null) {
			this.jacht=jacht;
			jacht.addKursant(this);
		} else if(!this.jacht.equals(jacht)) {
			this.jacht.removeKursant(this);
			this.jacht=jacht;
			jacht.addKursant(this);	
		}
	}
	
	/*
	 * Asocjacja z Wynik (1 Kursant - * Wynik)
	 */
	
	private List<Wynik> wyniki=new ArrayList<>();
	
	public ArrayList<Wynik> getWyniki(){
		return (ArrayList<Wynik>) wyniki;
	}
	
	public void addWynik(Wynik wynik) {
		if(!wyniki.contains(wynik))
			wyniki.add(wynik);
	}
	
	/*
	 * Asocjacja z Kurs (1 Kursant - * Kurs)
	 */
	
	private List<Kurs> kursy=new ArrayList<>();
	
	public ArrayList<Kurs> getKursy(){
		return (ArrayList<Kurs>) kursy;
	}
	
	public void addKurs(Kurs kurs) {
		if(!kursy.contains(kurs)) {
			kursy.add(kurs);
			kurs.addKursant(this);
		}	
	}
}
