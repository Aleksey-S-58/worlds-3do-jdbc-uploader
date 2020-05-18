package service.database.object.loader;

import data.CustomObject;
import data.Geometry;
import data.Material;
import data.Sprite;

/**
 * 
 * @author Aleksey Shishkin
 *
 */
public interface DataService {

	public void save(Geometry geometry, Material material);
	
	public void save(Sprite sprite);
	
	public void save(CustomObject customObject, String tableName);
	
}
