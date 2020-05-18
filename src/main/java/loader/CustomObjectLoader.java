package loader;

import data.CustomObject;

/**
 * 
 * @author Aleksey Shishkin
 *
 */
public class CustomObjectLoader extends AbstractObjectLoader<CustomObject> {
	
	public CustomObjectLoader() {
		reader = new DataReader();
	}
	
	public CustomObjectLoader(ObjectReader reader) {
		this.reader = reader;
	}

	@Override
	protected CustomObject create(String name, byte[] bytes) {
		return new CustomObject(name, bytes);
	}

}
