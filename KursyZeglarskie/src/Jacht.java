import java.util.ArrayList;
import java.util.List;

public class Jacht extends ObjectPlus{

	private String nazwa;
	private String model;
	private String numerRejestracyjny;
	private int miejsca;
	
	public Jacht(String nazwa, String model, String numerRejestracyjny, int miejsca, Lokalizacja lokalizacja) {
		super();
		setNazwa(nazwa);
		setModel(model);
		setNumerRejestracyjny(numerRejestracyjny);
		setMiejsca(miejsca);
		setLokalizacja(lokalizacja);
	}
	
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getNumerRejestracyjny() {
		return numerRejestracyjny;
	}
	public void setNumerRejestracyjny(String numerRejestracyjny) {
		this.numerRejestracyjny = numerRejestracyjny;
	}
	
	public int getMiejsca() {
		return miejsca;
	}
	public void setMiejsca(int miejsca) {
		this.miejsca = miejsca;
	}
	
	/*
	 * Asocjacja z Lokalizacja (1 Jacht - 1 Lokalizacja)
	 */
	
	private Lokalizacja lokalizacja;
	
	public Lokalizacja getLokalizacja() {
		return lokalizacja;
	}
	
	public void setLokalizacja(Lokalizacja lokalizacja) {
		if(this.lokalizacja==null) {
			this.lokalizacja=lokalizacja;
			lokalizacja.addJacht(this);
		} else if(!this.lokalizacja.equals(lokalizacja)) {
			this.lokalizacja.removeJacht(this);
			this.lokalizacja=lokalizacja;
			lokalizacja.addJacht(this);
		}
	}
	
	/*
	 * Asocjacja z Kursant (1 Jacht - * Kursant)
	 */
	
	private List<Kursant> zaloga=new ArrayList<>();
	
	public ArrayList<Kursant> getZaloga() {
		return (ArrayList<Kursant>) zaloga;
	}
	
	public void addKursant(Kursant kursant) {
		if(!zaloga.contains(kursant)) {
			zaloga.add(kursant);
			kursant.setJacht(this);
		}
	}
	
	public void removeKursant(Kursant kursant) {
		zaloga.remove(kursant);
	}
	
	/*
	 * Asocjacja ze Sternik (1 Jacht - 0..1 Sternik)
	 */
	
	private Sternik sternik;
	
	public Sternik getSternik() {
		return sternik;
	}
	
	public void setSternik(Sternik sternik) {
		if(this.sternik==null) {
			this.sternik=sternik;
			sternik.setJacht(this);
		} else if(!this.sternik.equals(sternik)) {
			this.sternik.removeJacht(this);
			this.sternik=sternik;
			sternik.setJacht(this);
		}
	}
	
	public void removeSternik(Sternik sternik) {
		if(this.sternik.equals(sternik))
			this.sternik=null;
	}
}
