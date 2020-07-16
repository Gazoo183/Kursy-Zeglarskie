import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Opiekun extends Osoba{

	private String numerTelefonu;
	private String dowodOsobisty;
	
	public Opiekun(String login, String haslo, String imie, String nazwisko, LocalDate dataUrodzenia, String numerTelefonu, String dowodOsobisty, Kursant kursant) {
		super(login, haslo, imie, nazwisko, dataUrodzenia);
		setNumerTelefonu(numerTelefonu);
		setDowodOsobisty(dowodOsobisty);
		addKursant(kursant);
	}
	
	public static void showExtent() throws Exception {
		ObjectPlus.showExtent(Opiekun.class);
	}

	public String getNumerTelefonu() {
		return numerTelefonu;
	}
	public void setNumerTelefonu(String numerTelefonu) {
		this.numerTelefonu = numerTelefonu;
	}

	public String getDowodOsobisty() {
		return dowodOsobisty;
	}
	public void setDowodOsobisty(String dowodOsobisty) {
		this.dowodOsobisty = dowodOsobisty;
	}
	
	public static Opiekun validate(String login, String haslo) throws ClassNotFoundException {
		Opiekun toReturn=null;
		try {
			toReturn=getExtent().stream().filter(opiekun -> (opiekun.getLogin().equals(login) && opiekun.getHaslo().equals(haslo))).findAny().get();
		} catch (Exception e) {}
		return toReturn;
	}
	
	public static ArrayList<Opiekun> getExtent() throws ClassNotFoundException {
		return (ArrayList) getExtent(Opiekun.class);
	}
	
	/*
	 * Asocjacja z Kursant (1 Opiekun - 1..* Kursant)
	 */

	private List<Kursant> podopieczni=new ArrayList<>();
	
	public ArrayList<Kursant> getPodopieczni() {
		return (ArrayList<Kursant>) podopieczni;
	}
	
	public void addKursant(Kursant kursant) {
		if(!podopieczni.contains(kursant)) {
			podopieczni.add(kursant);
			kursant.setOpiekun(this);
		}
	}
	
	public void removeKursant(Kursant kursant) {
		podopieczni.remove(kursant);
		if(podopieczni.isEmpty())
			removeObject(Opiekun.class, this);
	}
}
