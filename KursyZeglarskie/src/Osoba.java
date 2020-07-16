import java.time.LocalDate;

public abstract class Osoba extends ObjectPlus{

	private String login;
	private String haslo;
	private String imie;
	private String nazwisko;
	private LocalDate dataUrodzenia;
	
	public Osoba(String login, String haslo, String imie, String nazwisko, LocalDate dataUrodzenia) {
		super();
		setLogin(login);
		setHaslo(haslo);
		setImie(imie);
		setNazwisko(nazwisko);
		setDataUrodzenia(dataUrodzenia);
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	public String getHaslo() {
		return haslo;
	}
	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getImie() {
		return imie;
	}
	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}
	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public LocalDate getDataUrodzenia() {
		return dataUrodzenia;
	}
	public void setDataUrodzenia(LocalDate dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}
}
