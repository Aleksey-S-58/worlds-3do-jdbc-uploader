package loader;

import data.Geometry;

/**
 * 
 * @author Aleksey Shishkin
 *
 */
public class GeometryLoader extends AbstractObjectLoader<Geometry> {

	public GeometryLoader() {
		reader = new DataReader();
	}
	
	public GeometryLoader(ObjectReader reader) {
		this.reader = reader;
	}
	
	@Override
	protected Geometry create(String name, byte[] bytes) {
		return new Geometry(name, bytes);
	}

}
