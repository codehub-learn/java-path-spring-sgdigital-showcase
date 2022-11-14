package gr.codelearn.spring.showcase.app.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
	/* In case we need to define the same cache regions programmatically
	@Bean
	public CacheManager getCacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		Cache categories = new ConcurrentMapCache("categories");
		Cache category = new ConcurrentMapCache("category");
		Cache products = new ConcurrentMapCache("products");
		Cache product = new ConcurrentMapCache("product");
		cacheManager.setCaches(Arrays.asList(categories, category, products, product));
		return cacheManager;
	}
	*/

	@Bean("customCacheKeyGenerator")
	public KeyGenerator getCacheKeyGenerator() {
		return new CustomCacheKeyGenerator();
	}
}
