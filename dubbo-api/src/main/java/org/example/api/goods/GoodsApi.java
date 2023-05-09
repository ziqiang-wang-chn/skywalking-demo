package org.example.api.goods;

import org.example.entity.Goods;
import org.example.entity.Order;

public interface GoodsApi {
    Goods getGoodsById(Long id);
    int addGoods(Goods goods);
    int decGoods(Long id);
    int getGoodsSize();
}
