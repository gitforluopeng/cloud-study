package club.lp.study.service;

import club.lp.study.entity.Order;
import club.lp.study.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Override
    public Order list(Long id) {
        Order order = new Order();
        order.setId(id);
        order.setCode(UUID.randomUUID().toString());
        order.setProducts(getProds3());
        return order;
    }

    /**
     * @see org.springframework.cloud.client.discovery.DiscoveryClient DiscoveryClient方式 通过元数据获取服务信息
     * @return
     */
    private List<Product> getProds1() {
        StringBuilder sb = new StringBuilder("http://");

        //获取服务列表
        List<String> servicesIds = discoveryClient.getServices();
        if (CollectionUtils.isEmpty(servicesIds)) return null;
        //根据服务名获取服务
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("server");
        if (CollectionUtils.isEmpty(serviceInstances)) return null;
        ServiceInstance serviceInstance = serviceInstances.get(0);
        //host
        sb.append(serviceInstance.getHost());
        sb.append(":");
        //port
        sb.append(serviceInstance.getPort());
        //
        sb.append("/product");

        //http请求服务
        ResponseEntity<List<Product>> response = restTemplate.exchange(sb.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {});
        return response.getBody();
     }

    /**
     * LoadBalancerClient方式 Ribbon的负载均衡器
     * @return
     */
    private List<Product> getProds2() {
        StringBuilder sb = new StringBuilder("http://");

        ServiceInstance serviceInstance = loadBalancerClient.choose("server");
        //host
        sb.append(serviceInstance.getHost());
        sb.append(":");
        //port
        sb.append(serviceInstance.getPort());
        //
        sb.append("/product");

        //http请求服务
        ResponseEntity<List<Product>> response = restTemplate.exchange(sb.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {});
        return response.getBody();
    }

    /**
     * @see org.springframework.cloud.client.loadbalancer.LoadBalanced 方式 通过注解开启Ribbon的负载均衡器
     * RestTemplate 加注解 restTemplate具有负载均衡的能力
     * @return
     */
    private List<Product> getProds3() {
        //http请求服务
        //直接请求服务ID
        ResponseEntity<List<Product>> response = restTemplate.exchange("http://server/product", HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {});
        return response.getBody();
    }
}
