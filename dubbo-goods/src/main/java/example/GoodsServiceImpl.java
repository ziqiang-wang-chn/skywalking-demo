package example;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.example.api.goods.GoodsApi;
import org.example.entity.Goods;
import org.example.entity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@DubboService
public class GoodsServiceImpl implements GoodsApi {
    private List<Goods> list;
    {
        list = new ArrayList<>();
        list.add(new Goods("饼干", 2.5F, 1L));
        list.add(new Goods("抱枕", 33.5F, 2L));
        list.add(new Goods("大西瓜", 22.5F, 3L));
    }
    private AtomicInteger size = new AtomicInteger(list.size());

    @Override
    public Goods getGoodsById(Long id) {
        return list.stream().filter(g -> g.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public int addGoods(Goods goods) {
        list.add(goods);
        return size.incrementAndGet();
    }

    @Trace(operationName = "Goods#decGoods")
    @Tag(key = "商品消费", value = "arg[0]")
    @Override
    public int decGoods(Long id) {
        list = list.stream().filter(g -> g.getId() != id).collect(Collectors.toList());
        return size.decrementAndGet();
    }

    @Override
    public int getGoodsSize() {
        return size.get();
    }
}