package loader;

import data.AbstractObject;

/**
 * This interface defines a loader that loads some byte object which extends AbstractObject from file.
 * @author Aleksey Shishkin
 *
 * @param <T> now supports classes: Geometry, Material or Sprite.
 */
public interface ObjectLoader <T extends AbstractObject> {

	/**
	 * This method loads an object from file.
	 * @param name this argument will be used to fill a name property of an object.
	 * @param path - a path to a file.
	 * @return an object with filled properties.
	 */
	public T load(String name, String path);
	
}
