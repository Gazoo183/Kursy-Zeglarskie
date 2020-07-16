import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Kurs extends ObjectPlus{

	private LocalDate dataRozpoczecia;
	private LocalDate dataZakonczenia;
	private BigDecimal cena;
	
	public Kurs(LocalDate dataRozpoczecia, LocalDate dataZakonczenia, BigDecimal cena, Lokalizacja lokalizacja) {
		super();
		setDataRozpoczecia(dataRozpoczecia);
		setDataZakonczenia(dataZakonczenia);
		setCena(cena);
		setLokalizacja(lokalizacja);
	}

	public LocalDate getDataRozpoczecia() {
		return dataRozpoczecia;
	}
	public void setDataRozpoczecia(LocalDate dataRozpoczecia) {
		this.dataRozpoczecia = dataRozpoczecia;
	}

	public LocalDate getDataZakonczenia() {
		return dataZakonczenia;
	}
	public void setDataZakonczenia(LocalDate dataZakonczenia) {
		this.dataZakonczenia = dataZakonczenia;
	}

	public BigDecimal getCena() {
		return cena;
	}
	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}
	
	public static ArrayList<Kurs> getExtent() throws ClassNotFoundException {
		return (ArrayList) getExtent(Kurs.class);
	}
	
	public static Kurs[][] getDataForTable() throws ClassNotFoundException {
		List<Kurs> tmp=getExtent().stream().filter(x -> x.getLokalizacja().stworzRaport()>x.getKursanci().size()).collect(Collectors.toList());
		Kurs[][] toReturn=new Kurs[tmp.size()][1];
		
		for(int i=0;i<tmp.size();i++)
			toReturn[i][0]=tmp.get(i);
		
		return toReturn;
	}
	
	@Override
	public String toString() {
		return "Lokalizacja: "+getLokalizacja().getNazwa()+" | Rozpoczęcie: "+getDataRozpoczecia()+" | Zakończenie: "+getDataZakonczenia()+ " | Cena: "+getCena();
	}
	
	/*
	 * Asocjacja z Lokalizacja (1 Kursa - 1 Lokalizacja)
	 */
	
	private Lokalizacja lokalizacja;
	
	public Lokalizacja getLokalizacja() {
		return lokalizacja;
	}
	
	public void setLokalizacja(Lokalizacja lokalizacja) {
		if(this.lokalizacja==null) {
			this.lokalizacja=lokalizacja;
			lokalizacja.addKurs(this);
		} else if(!this.lokalizacja.equals(lokalizacja)) {
			this.lokalizacja.removeKurs(this);
			this.lokalizacja=lokalizacja;
			lokalizacja.addKurs(this);
		}
	}
	
	/*
	 * Asocjacja z Instruktor (1 Kursa - * Instruktor)
	 */
	
	private List<Instruktor> kadra=new ArrayList<>();
	
	public ArrayList<Instruktor> getKadra() {
		return (ArrayList<Instruktor>) kadra;
	}
	
	public void addInstruktor(Instruktor instruktor) {
		if(!kadra.contains(instruktor)) {
			kadra.add(instruktor);
			instruktor.setKurs(this);
		}
	}
	
	public void removeInstruktor(Instruktor instruktor) {
		kadra.remove(instruktor);
	}
	
	/*
	 * Asocjacja z EgzaminKoncowy (1 Kurs - * EgzaminKoncowy)
	 */
	
	private List<EgzaminKoncowy> egzaminy=new ArrayList<>();
	
	public ArrayList<EgzaminKoncowy> getEgzaminy() {
		return (ArrayList<EgzaminKoncowy>) egzaminy;
	}
	
	public void addEgzaminKoncowy(EgzaminKoncowy egzamin) {
		if(!egzaminy.contains(egzamin)) {
			egzaminy.add(egzamin);
			egzamin.setKurs(this);
		}
	}
	
	public void removeEgzaminKoncowy(EgzaminKoncowy egzamin) {
		egzaminy.remove(egzamin);
	}
	
	/*
	 * Asocjacja z Kursant (1 Kurs - * Kursant)
	 */
	
	private List<Kursant> kursanci=new ArrayList<>();
	
	public ArrayList<Kursant> getKursanci(){
		return (ArrayList<Kursant>) kursanci;
	}
	
	public void addKursant(Kursant kursant) {
		if(!kursanci.contains(kursant)) {
			kursanci.add(kursant);
			kursant.addKurs(this);
		}
	}
}
