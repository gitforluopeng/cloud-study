package club.lp.study.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    private Long id;
    private String code;
    private List<Product> products;
}
