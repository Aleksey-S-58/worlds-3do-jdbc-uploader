package loader;

import data.AbstractObject;

/**
 * This abstract class defines a common methods to load any AbstractObject from file.
 * @author Aleksey Shishkin
 *
 */
public abstract class AbstractObjectLoader<T extends AbstractObject> implements ObjectLoader<T> {

	protected ObjectReader reader;
	
	protected abstract T create(String name, byte[] bytes);
	
	@Override
	public T load(String name, String path) {
		byte[] bytes = reader.read(path);
		return create(name, bytes);
	}

}
