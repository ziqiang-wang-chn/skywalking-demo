package org.example;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.example.api.user.UserApi;
import org.example.entity.Goods;
import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@DubboService
public class UserServiceImpl implements UserApi {

    private List<User> list;
    {
        list = new ArrayList<>();
        list.add(new User(1L, "老八", "男"));
        list.add(new User(2L, "虎哥", "女"));
        list.add(new User(3L, "刀哥", "男"));
    }
    private AtomicInteger size = new AtomicInteger(list.size());

    @Override
    public User getUserById(Long id) {
        return list.stream().filter(g-> g.getId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    @Trace(operationName = "User#userLogin")
    @Tag(key = "用户登录", value = "arg[0]")
    public boolean userLogin(User user) {
        if (list.stream().filter(u -> u.equals(user)).collect(Collectors.toList()).size() == 1) {
            return true;
        }
        return false;
    }
}