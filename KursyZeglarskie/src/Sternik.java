import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Sternik extends Instruktor{

	private String numerPatentu;
	
	public Sternik(String login, String haslo, String imie, String nazwisko, LocalDate dataUrodzenia, BigDecimal pensja, String numerPatentu) {
		super(login, haslo, imie, nazwisko, dataUrodzenia, pensja);
		setNumerPatentu(numerPatentu);
	}
	
	public static void showExtent() throws Exception {
		ObjectPlus.showExtent(Sternik.class);
	}

	public String getNumerPatentu() {
		return numerPatentu;
	}

	public void setNumerPatentu(String numerPatentu) {
		this.numerPatentu = numerPatentu;
	}
	
	public static Instruktor validate(String login, String haslo) throws ClassNotFoundException {
		Instruktor toReturn=null;
		try {
			toReturn=getExtent().stream().filter(sternik -> (sternik.getLogin().equals(login) && sternik.getHaslo().equals(haslo))).findAny().get();
		} catch (Exception e) {}
		return toReturn;
	}
	
	public static ArrayList<Instruktor> getExtent() throws ClassNotFoundException {
		return (ArrayList) getExtent(Sternik.class);
	}
	
	/*
	 * Asocjacja z Jacht (1 Sternik - 0..1 Jacht)
	 */

	private Jacht jacht;
	
	public Jacht getJacht() {
		return jacht;
	}
	
	public void setJacht(Jacht jacht) {
		if(this.jacht==null) {
			this.jacht=jacht;
			jacht.setSternik(this);
		} else if(!this.jacht.equals(jacht)) {
			this.jacht.removeSternik(this);
			this.jacht=jacht;
			jacht.setSternik(this);
		}
	}
	
	public void removeJacht(Jacht jacht) {
		if(this.jacht.equals(jacht))
			this.jacht=null;
	}
}
