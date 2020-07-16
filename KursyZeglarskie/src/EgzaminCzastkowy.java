import java.time.LocalDate;
import java.util.List;

public class EgzaminCzastkowy extends Egzamin{

	public EgzaminCzastkowy(Typ typ, Termin termin, Status status, List<String> zadania, LocalDate dataPisania, Zajecia material) {
		super(typ, termin, status, zadania, dataPisania);
		setMaterial(material);
	}
	
	/*
	 * Asocjacja z Zajecia (1 EgzaminCzastkowy - 1 Zajecia)
	 */

	private Zajecia material;
	
	public Zajecia getMaterial() {
		return material;
	}
	
	public void setMaterial(Zajecia material) {
		if(this.material==null) {
			this.material=material;
			material.addEgzaminCzastkowy(this);
		} else if(!this.material.equals(material)) {
			this.material.removeEgzaminCzastkowy(this);
			this.material=material;
			material.addEgzaminCzastkowy(this);
		}
	}
}
