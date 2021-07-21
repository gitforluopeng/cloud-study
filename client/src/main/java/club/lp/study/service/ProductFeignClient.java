package club.lp.study.service;

import club.lp.study.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "server")
public interface ProductFeignClient {

    @GetMapping("/product")
    List<Product> getProducts();
}
