package cn.tedu.sp09.service;

import cn.tedu.sp01.pojo.Order;
import cn.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

@Component
public class OrderClientFB implements OrderClient {

	@Override
	public JsonResult<Order> getOrder(String orderId) {
		return JsonResult.err().msg("获取商品订单失败");
	}

	@Override
	public JsonResult<?> addOrder() {
		return JsonResult.err().msg("保存订单失败");
	}

}