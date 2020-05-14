package loader;

import data.Material;

/**
 * 
 * @author Aleksey Shishkin
 *
 */
public class MaterialLoader extends AbstractObjectLoader<Material> {
	
	public MaterialLoader() {
		reader = new DataReader();
	}
	
	public MaterialLoader(ObjectReader reader) {
		this.reader = reader;
	}

	@Override
	protected Material create(String name, byte[] bytes) {
		return new Material(name, bytes);
	}

}
