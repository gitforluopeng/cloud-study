package club.lp.study.service;

import club.lp.study.entity.Order;

public interface OrderService {
    Order list(Long id);

    Order selectById(Long id);
}
