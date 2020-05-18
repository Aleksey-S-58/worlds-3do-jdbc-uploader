package command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.CustomObject;
import loader.CustomObjectLoader;
import service.database.object.loader.DataService;

/**
 * 
 * @author Aleksey Shishkin
 *
 */
public class SaveCustomObject implements Command {
	
	private static final Logger LOGGER  = LoggerFactory.getLogger(SaveCustomObject.class);
	
	private CustomObjectLoader loader;
	private DataService service;
	private String name;
	private String tableName;
	private String objectPath;

	public SaveCustomObject(CustomObjectLoader loader, DataService service, String name, String tableName,
			String objectPath) {
		super();
		this.loader = loader;
		this.service = service;
		this.name = name;
		this.tableName = tableName;
		this.objectPath = objectPath;
	}

	@Override
	public boolean execute() {
		try {
			CustomObject object = loader.load(name, objectPath);
			LOGGER.info("Sprite name: {}, length: {}", object.getName(), object.getBytes().length);
			service.save(object, tableName);
			return true;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
	}

}
