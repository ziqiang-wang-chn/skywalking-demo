package example;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.example.api.goods.GoodsApi;
import org.example.api.order.OrderApi;
import org.example.api.user.UserApi;
import org.example.entity.Goods;
import org.example.entity.Order;
import org.example.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class BasicController {

    @DubboReference
    private UserApi userApi;
    @DubboReference
    private GoodsApi goodsApi;
    @DubboReference
    private OrderApi orderApi;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userApi.getUserById(id);
    }

    /**
     * 消费场景
     * 用户登入
     * 下单
     * 减库存
     * 二次确认库存情况
     * 完成
     */
    @Trace(operationName = "BasicController#consume")
    @Tags({
            @Tag(key = "BasicController#consume=>user", value = "arg[0]"),
            @Tag(key = "BasicController#consume=>goodsId", value = "arg[1]"),
            @Tag(key = "BasicController#consume result", value = "returnedObj")
    })
    @PostMapping("/consume")
    public String consume(@RequestBody User user, @RequestParam Long goodsId) {
        log.info("用户登入 => {}", user);
        boolean login = userApi.userLogin(user);
        if (!login) return "登陆失败";
        log.info("用户登入成功 => {}", user);
        log.info("用户 => {}  消费  => {}", user, goodsId);
        orderApi.addOrder(new Order(4l, goodsId, user.getId(), "2023-04-21"));
        int beforeSize = goodsApi.getGoodsSize();
        log.info("商品库存减少 decGoods  => {}",goodsId);
        int afterSize = goodsApi.decGoods(goodsId);
        if (beforeSize > afterSize)
            return "消费成功";
        else
            return "消费失败";
    }
}
