package club.lp.study.controller;

import club.lp.study.entity.Order;
import club.lp.study.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Order byId(@PathVariable Long id) {
        return orderService.list(id);
    }

    @GetMapping("/temp/{id}")
    public Order byId2(@PathVariable Long id) {
        return orderService.selectById(id);
    }

}
