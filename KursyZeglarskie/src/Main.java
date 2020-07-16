import java.awt.EventQueue;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception {
		// Kursant --[1..*]----[0..1]-- Opiekun
		// Kurs --[0..*]----[1]-- Lokalizacja
		// Jacht --[0..*]----[1]-- Lokalizacja
		// Jacht --[1]----[0..*]-- Kursant
		// Jacht --[0..1]----[0..1]-- Sternik
		// Instruktor --[1]----[0..1]-- Zajecia
		// Instruktor --[*]----[1]-- Kurs
		// Zajecia --[1]----[*] Egzamin czastkowy
		// Kurs --[1]----[*]-- Egzamin koncowy
		// Kursant --[1]----[*]-- Wynik --[*]----[1]-- Egzamin
		// Kursant --[*]----[*]-- Kurs
		
		/*
		 *	Przykładowe dane
		 */
		
		Lokalizacja l1=new Lokalizacja("Mazury");
		Lokalizacja l2=new Lokalizacja("Zalew zegrzyński");
		
		Jacht j1=new Jacht("Utopia", "wz11111", "Tango", 5, l1);
			j1.setLokalizacja(l1);
		Jacht j2=new Jacht("Kraken", "wz22222", "Carter", 6, l2);
			j2.setLokalizacja(l2);
		
		Kurs kurs1=new Kurs(LocalDate.now(), LocalDate.now().plusDays(21), new BigDecimal(1400), l1);
		Kurs kurs2=new Kurs(LocalDate.now(), LocalDate.now().plusDays(21), new BigDecimal(1600), l2);
		
		int autoId=0;
		try {
			autoId=((ArrayList)ObjectPlus.getExtent(Kursant.class)).size()+1;
		} catch (ClassNotFoundException e2) {}
		
		Kursant kursant1=new Kursant("k"+autoId, "k"+autoId, "Jakub", "Kowalski", LocalDate.of(1997, 1, 10), Poziom.SZKOLONY, "dowod.png", kurs1);
		
		try {
			autoId=((ArrayList)ObjectPlus.getExtent(Kursant.class)).size()+1;
		} catch (ClassNotFoundException e2) {}
		
		Kursant kursant2=new Kursant("k"+autoId, "k"+autoId, "Wojtek", "Szpak", LocalDate.of(2004, 4, 20), Poziom.NOWICJUSZ, "legitymacja.png", kurs1);
		Opiekun o1=new Opiekun("o"+autoId, "o"+autoId, "Maria", "Szpak", LocalDate.of(1974, 6, 15), "111111111", "dowod.png", kursant1);
			
		Instruktor i1=new Instruktor("i"+autoId, "i"+autoId, "Kamil", "Górski", LocalDate.of(1976, 10, 7), new BigDecimal(3000));
			i1.setKurs(kurs1);
		Instruktor i2=new Instruktor("i"+autoId+1, "i"+autoId+1, "Piotr", "Kolec", LocalDate.of(1972, 4, 13), new BigDecimal(2500));
			i2.setKurs(kurs1);
			
		Zajecia z1=new Zajecia("Meteorologia", 30, i1);
		
		Sternik s1=new Sternik("s"+autoId+2, "s"+autoId+2, "Zuzanna", "Nowak", LocalDate.of(1990, 8, 21), new BigDecimal(2000), "N12345");
			s1.setJacht(j1);
			
		List<String> zad=new ArrayList<>();
		zad.add("zadanie 1"); zad.add("zadanie 2"); zad.add("zadanie 3"); zad.add("zadanie 4"); zad.add("zadanie 5");
		EgzaminCzastkowy p1=new EgzaminCzastkowy(Typ.TEORETYCZNY, Termin.PIERWSZY, Status.SPRAWDZANY, zad, LocalDate.now().plusDays(14), z1);
			p1.setMaterial(z1);
			
		EgzaminKoncowy k1=new EgzaminKoncowy(Typ.TEORETYCZNY, Termin.PIERWSZY, Status.SPRAWDZANY, zad, LocalDate.now().plusDays(19), kurs1);
		EgzaminKoncowy k2=new EgzaminKoncowy(Typ.TEORETYCZNY, Termin.PIERWSZY, Status.SPRAWDZANY, zad, LocalDate.now().plusDays(20), kurs2);
			
		Wynik w1=new Wynik(3, kursant2, p1);
		
		/*
		 *  Write/Read to/from file
		 */

/*		
		ObjectInputStream in=new ObjectInputStream(new BufferedInputStream(new FileInputStream("extentFile.txt")));
		ObjectPlus.readExtents(in);
		in.close();
//*/
/*
 		ObjectOutputStream out=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("extentFile.txt")));
 		ObjectPlus.writeExtents(out);
		out.close();
//*/
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
