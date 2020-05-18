package command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data.Sprite;
import loader.SpriteLoader;
import service.database.object.loader.DataService;

/**
 * This command is intended to save a sprite to a database.
 * @author Aleksey Shishkin
 *
 */
public class SaveSprite implements Command {
	
	private static final Logger LOGGER  = LoggerFactory.getLogger(SaveSprite.class);
	
	private SpriteLoader loader;
	private DataService service;
	private String name;
	private String spritePath;

	public SaveSprite(SpriteLoader loader, DataService service, String name, String spritePath) {
		super();
		this.loader = loader;
		this.service = service;
		this.name = name;
		this.spritePath = spritePath;
	}

	@Override
	public boolean execute() {
		try {
			Sprite sprite = loader.load(name, spritePath);
			LOGGER.info("Sprite name: {}, length: {}", sprite.getName(), sprite.getBytes().length);
			service.save(sprite);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
