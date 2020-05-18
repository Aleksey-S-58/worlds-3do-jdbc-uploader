package command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.Geometry;
import data.Material;
import loader.GeometryLoader;
import loader.MaterialLoader;
import service.database.object.loader.DataService;

/**
 * This command is intended to save a 3DO to a database.
 * @author Aleksey Shishkin
 *
 */
public class SaveObject implements Command {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SaveObject.class);
	
	private GeometryLoader geometryLoader;
	private MaterialLoader materialLoader;
	private DataService service;
	private String name;
	private String geometryPath;
	private String materialPath;

	public SaveObject(GeometryLoader geometryLoader, MaterialLoader materialLoader, 
			DataService service, String name, String geometryPath, String materialPath) {
		super();
		this.geometryLoader = geometryLoader;
		this.materialLoader = materialLoader;
		this.service = service;
		this.name = name;
		this.geometryPath = geometryPath;
		this.materialPath = materialPath;
	}

	@Override
	public boolean execute() {
		try {
			Geometry geometry = geometryLoader.load(name, geometryPath);
			LOGGER.info("Geometry name: {}, length: {}", geometry.getName(), geometry.getBytes().length);
			Material material = materialLoader.load(name, materialPath);
			LOGGER.info("Material name: {}, length: {}", material.getName(), material.getBytes().length);
			service.save(geometry, material);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
