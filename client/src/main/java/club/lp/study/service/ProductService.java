package club.lp.study.service;

import club.lp.study.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> list();

    Product selectById(Long id);
}
