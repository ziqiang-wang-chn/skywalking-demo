package org.example;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.example.api.order.OrderApi;
import org.example.entity.Goods;
import org.example.entity.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@DubboService
public class OrderServiceImpl implements OrderApi {
    private List<Order> list;
    {
        list = new ArrayList<>();
        list.add(new Order(1L, 1L, 1L, "2022-02-25"));
        list.add(new Order(2L, 2L, 1L, "2023-04-15"));
        list.add(new Order(3L, 3L, 2L, "2002-07-05"));
    }
    private AtomicInteger size = new AtomicInteger(list.size());
    @Override
    public Order getOrderById(Long id) {
        return list.stream().filter(g -> g.getId() == id).collect(Collectors.toList()).get(0);
    }
    @Trace
    @Tag(key = "创建订单 ", value = "arg[0]")
    @Override
    public void addOrder(Order order) {
        list.add(order);
        size.incrementAndGet();
    }
}