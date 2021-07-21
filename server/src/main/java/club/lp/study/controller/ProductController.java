package club.lp.study.controller;

import club.lp.study.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping
    public List<Product> list() {
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("测试");
        return Collections.singletonList(p1);
    }

    @GetMapping("/{id}")
    public Product selectById(@PathVariable Long id) {
        Product p1 = new Product();
        p1.setId(id);
        p1.setName("测试");
        return p1;
    }
}
