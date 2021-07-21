package club.lp.study.service;

import club.lp.study.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Cacheable("orderService:product:list")
    public List<Product> list() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ResponseEntity<List<Product>> response = restTemplate.exchange("http://server/product", HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
        });
        return response.getBody();
    }

    @Override
    @Cacheable(value = "orderService:product:single", key = "#id")
    public Product selectById(Long id) {
        return restTemplate.getForObject("http://server/product/"+id, Product.class);
    }
}
