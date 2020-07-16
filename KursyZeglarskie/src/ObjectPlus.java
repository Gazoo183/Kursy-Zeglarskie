import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/*
 *	Zarządzanie ekstencjami
 */

public class ObjectPlus implements Serializable{
	private static Map<Class<?>, List<ObjectPlus>> extents=new Hashtable<>();
	
	public ObjectPlus() {
		List<ObjectPlus> extent=null;
		Class theClass=this.getClass();
		
		if(extents.containsKey(theClass))
			extent=extents.get(theClass);
		else {
			extent=new ArrayList<>();
			extents.put(theClass, extent);
		}
		
		extent.add(this);
	}
	
	public static void writeExtents(ObjectOutputStream out) throws Exception {
		out.writeObject(extents);
	}
	
	public static void readExtents(ObjectInputStream in) throws Exception {
		extents=(Hashtable) in.readObject();
	}
	
	public static <T extends ObjectPlus> Iterable<T> getExtent(Class<T> theClass) throws ClassNotFoundException {
		if(extents.containsKey(theClass))
			return (Iterable<T>) extents.get(theClass);
		
		throw new ClassNotFoundException(String.format("%s. Stored extents: %s", theClass.toString(), extents.keySet()));
	}
	
	public static void showExtent(Class<? extends ObjectPlus> theClass) throws Exception {
		List<ObjectPlus> extent=null;
		if(extents.containsKey(theClass))
			extent=extents.get(theClass);
		else 
			throw new Exception("Unknown class "+theClass);
		
		System.out.println("Extent of the class: "+ theClass.getSimpleName());
		
		for(Object obj : extent)
			System.out.println(obj);
	}
	
	public static void removeObject(Class<?> theClass, ObjectPlus toRemove) {
		extents.get(theClass).remove(toRemove);
	}
	
}
