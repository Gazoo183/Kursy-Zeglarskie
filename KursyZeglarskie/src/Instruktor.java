import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Instruktor extends Osoba{

	private BigDecimal pensja;
	
	public Instruktor(String login, String haslo, String imie, String nazwisko, LocalDate dataUrodzenia, BigDecimal pensja) {
		super(login, haslo, imie, nazwisko, dataUrodzenia);
		setPensja(pensja);
	}

	public BigDecimal getPensja() {
		return pensja;
	}
	public void setPensja(BigDecimal pensja) {
		this.pensja = pensja;
	}
	
	public static Instruktor validate(String login, String haslo) throws ClassNotFoundException {
		Instruktor toReturn=null;
		try {
			toReturn=getExtent().stream().filter(instruktor -> (instruktor.getLogin().equals(login) && instruktor.getHaslo().equals(haslo))).findAny().get();
		} catch (Exception e) {}
		return toReturn;
	}
	
	public static ArrayList<Instruktor> getExtent() throws ClassNotFoundException {
		return (ArrayList) getExtent(Instruktor.class);
	}
	
	/*
	 * Asocjacja z Kurs (1 Instruktor - 1 Kurs)
	 */
	
	private Kurs kurs;
	
	public Kurs getKurs() {
		return kurs;
	}
	
	public void setKurs(Kurs kurs) {
		if(this.kurs==null) {
			this.kurs=kurs;
			kurs.addInstruktor(this);
		} else if(!this.kurs.equals(kurs)) {
			this.kurs.removeInstruktor(this);
			this.kurs=kurs;
			kurs.addInstruktor(this);
		}
	}
	
	/*
	 * Asocjacja z Zajecia (1 Instruktor - 0..1 Zajecia)
	 */
	
	private Zajecia zajecia;
	
	public Zajecia getZajecia() {
		return zajecia;
	}
	
	public void setZajecia(Zajecia zajecia) {
		if(this.zajecia==null) {
			this.zajecia=zajecia;
			zajecia.setProwadzacy(this);
		} else if(!this.zajecia.equals(zajecia)) {
			this.zajecia.removeProwadzacy(this);
			this.zajecia=zajecia;
			zajecia.setProwadzacy(this);
		}
	}
	
	public void removeZajecia(Zajecia zajecia) {
		if(this.zajecia.equals(zajecia))
			this.zajecia=null;
	}
	
}
