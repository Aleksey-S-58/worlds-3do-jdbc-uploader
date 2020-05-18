package data;

/**
 * This custom object will represent any users object and 
 * is intended to save an object to a runtime defined table.
 * 
 * @author Aleksey Shishkin
 *
 */
public class CustomObject extends AbstractObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6768838363393731497L;

	public CustomObject() {
		
	}
	
	public CustomObject(String name, byte[] bytes) {
		super();
		this.name = name;
		this.bytes = bytes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CustomObject))
			return false;
		CustomObject other = (CustomObject) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
