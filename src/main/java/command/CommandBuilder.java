package command;

import loader.CustomObjectLoader;
import loader.GeometryLoader;
import loader.MaterialLoader;
import loader.SpriteLoader;
import service.database.object.loader.DataService;

/**
 * This command builder allows to create either 
 * a command which will save a 3DO or a command that will save a sprite.
 * @author Aleksey Shishkin
 *
 */
public class CommandBuilder {

	private GeometryLoader geometryLoader;
	private MaterialLoader materialLoader;
	private SpriteLoader spriteLoader;
	private CustomObjectLoader customObjectLoader;
	private DataService service;
	private String name;
	private String geometryPath;
	private String materialPath;
	private String spritePath;
	private String customObjectPath;
	private String tableName;

	public void setGeometryLoader(GeometryLoader geometryLoader) {
		this.geometryLoader = geometryLoader;
	}
	public void setMaterialLoader(MaterialLoader materialLoader) {
		this.materialLoader = materialLoader;
	}
	public void setSpriteLoader(SpriteLoader spriteLoader) {
		this.spriteLoader = spriteLoader;
	}
	public void setCustomObjectLoader(CustomObjectLoader customObjectLoader) {
		this.customObjectLoader = customObjectLoader;
	}
	public void setService(DataService service) {
		this.service = service;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGeometryPath(String geometryPath) {
		this.geometryPath = geometryPath;
	}
	public void setMaterialPath(String materialPath) {
		this.materialPath = materialPath;
	}
	public void setSpritePath(String spritePath) {
		this.spritePath = spritePath;
	}
	public void setCustomObjectPath(String path) {
		customObjectPath = path;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Creates a ready to execute command according to a specified class type.
	 * If some of demanded properties was not specified an IllegalArgumentException will be thrown!
	 * @param clazz supports classes SaveObject, SaveSprite otherwise an IllegalArgumentException will be thrown.
	 * @return a SaveObject or a SaveSprite
	 */
	public <T extends Command> Command build(Class<T> clazz) {
		if (clazz == SaveObject.class) {
			return buildSaveObject();
		}
		if (clazz == SaveSprite.class) {
			return buildSaveSprite();
		}
		if (clazz == SaveCustomObject.class) {
			return buildSaveCustomObject();
		}
		throw new IllegalArgumentException("Unsupported command type!");
	}

	private SaveObject buildSaveObject() {
		if (geometryLoader == null || materialLoader == null 
				|| service == null || name == null || geometryPath == null 
				|| materialPath == null) {
			throw new IllegalArgumentException("geometryLoader, materialLoader, service, name, geometryPath, materialPath can't be null!");
		}
		return new SaveObject(geometryLoader, materialLoader, service, name, geometryPath, materialPath);
	}

	private SaveSprite buildSaveSprite() {
		if (spriteLoader == null || service == null || name == null || spritePath == null) {
			throw new IllegalArgumentException("spriteLoader, service, name, spritePath can't be null!");
		}
		return new SaveSprite(spriteLoader, service, name, spritePath);
	}

	private SaveCustomObject buildSaveCustomObject() {
		if (customObjectLoader == null || service == null || name == null || customObjectPath == null || tableName == null) {
			throw new IllegalArgumentException("customObjectLoader, service, name, customObjectPath, tableName can't be null!");
		}
		return new SaveCustomObject(customObjectLoader, service, name, tableName, customObjectPath);
	}
}
