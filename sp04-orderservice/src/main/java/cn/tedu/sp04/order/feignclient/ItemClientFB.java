package cn.tedu.sp04.order.feignclient;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ItemClientFB implements ItemClient {

	@Override
	public JsonResult<List<Item>> getItems(String orderId) {
		if(Math.random()<0.5) {
			List<Item> list = new ArrayList<>();
			list.add(new Item(1, "缓存商品1", 1));
			list.add(new Item(2, "缓存商品2", 2));
			list.add(new Item(3, "缓存商品3", 3));
			return JsonResult.ok().data(list);
		}
		return JsonResult.err().msg("无法获取订单商品列表");
	}



	@Override
	public JsonResult decreaseNumber(List<Item> items) {
		return JsonResult.err().msg("无法修改商品库存");
	}

}