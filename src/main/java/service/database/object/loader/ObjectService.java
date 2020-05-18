package service.database.object.loader;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import data.CustomObject;
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
public class ObjectService implements DataService {
	
	private GeometryRepository geometryRepository;
	private MaterialRepository materialRepository;
	private SpriteRepository spriteRepository;

	/**
	 * Unfortunately we can't use @PersistenceContext annotation with a constructor 
	 * so we need transaction manager and to begin and commit such transaction manually.
	 * We also can't use shared EntityManager for our purpose.
	 * 
	 * An entityManagerFactory will be used to save a custom object 
	 * as we don't know a table name before runtime.
	 */
	private JpaTransactionManager transactionManager;

	public ObjectService(GeometryRepository geometryRepository, 
			MaterialRepository materialRepository, SpriteRepository spriteRepository,
			JpaTransactionManager transactionManager) {
		this.geometryRepository = geometryRepository;
		this.materialRepository = materialRepository;
		this.spriteRepository = spriteRepository;
		this.transactionManager = transactionManager;
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

	private String getNativeQuery(String tableName) {
		return "INSERT INTO " + tableName + " VALUES (?, ?);";
	}

//	@Transactional
	public void save(CustomObject customObject, String tableName) {
		EntityManager entityManager = transactionManager.getEntityManagerFactory().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		String sqlQuery = getNativeQuery(tableName);
		Query query = entityManager.createNativeQuery(sqlQuery);
		query.setParameter(1, customObject.getName());
		query.setParameter(2, customObject.getBytes());
		int i = query.executeUpdate();
		transaction.commit();
		if (i < 1) {
			System.out.println("An object wasn't saved, try again!");
		}
	}
}
