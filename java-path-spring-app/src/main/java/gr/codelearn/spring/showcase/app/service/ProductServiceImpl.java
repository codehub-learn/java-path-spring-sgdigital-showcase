package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.domain.Product;
import gr.codelearn.spring.showcase.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@CacheConfig(cacheNames = {"products"}, keyGenerator = "customCacheKeyGenerator")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
	private final ProductRepository productRepository;

	@Override
	public JpaRepository<Product, Long> getRepository() {
		return productRepository;
	}

	@Cacheable
	@Override
	public Product findBySerial(final String serial) {
		return productRepository.findBySerial(serial).orElseThrow();
	}

	@Cacheable
	@Override
	public List<Product> findAll() {
		logger.info("List does not exist in cache, fetching from repository.");
		return productRepository.findAll();
	}

	@CacheEvict(value = "products", allEntries = true)
	@Scheduled(cron = "0/10 * * * * ?")
	public void clearAllCaches() {
		logger.info("Clearing products cache.");
	}
}
