package service.database.object.loader;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import data.Geometry;
import data.Material;
import data.Sprite;
import repository.GeometryRepository;
import repository.MaterialRepository;
import repository.SpriteRepository;

/**
 * This service allows to save 3DO and sprites to a database.
 * @author Aleksey Shishkin
 *
 */
@Service
public class ObjectService {
	
	private GeometryRepository geometryRepository;
	private MaterialRepository materialRepository;
	private SpriteRepository spriteRepository;

	public ObjectService(GeometryRepository geometryRepository, 
			MaterialRepository materialRepository, SpriteRepository spriteRepository) {
		this.geometryRepository = geometryRepository;
		this.materialRepository = materialRepository;
		this.spriteRepository = spriteRepository;
	}
	
	@Transactional
	public void save(Geometry geometry, Material material) {
		geometryRepository.save(geometry);
		materialRepository.save(material);
	}
	
	@Transactional
	public void save(Sprite sprite) {
		spriteRepository.save(sprite);
	}
}
