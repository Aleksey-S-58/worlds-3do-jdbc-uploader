package loader;

import data.Sprite;

/**
 * 
 * @author Aleksey Shishkin
 *
 */
public class SpriteLoader extends AbstractObjectLoader<Sprite> {
	
	public SpriteLoader() {
		reader = new DataReader();
	}
	
	public SpriteLoader(ObjectReader reader) {
		this.reader = reader;
	}

	@Override
	protected Sprite create(String name, byte[] bytes) {
		return new Sprite(name, bytes);
	}

}
