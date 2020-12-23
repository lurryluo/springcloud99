package cn.tedu.sp06.consoller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@Slf4j
public class RibbonController {
	@Autowired
	private RestTemplate rt;
	
	@GetMapping("/item-service/{orderId}")//1
	public JsonResult<List<Item>> getItems(@PathVariable String orderId) {
	    //这里服务器路径用 service-id 代替，ribbon 会向服务的多台集群服务器分发请求
		return rt.getForObject("http://item-service/{1}", JsonResult.class, orderId);
	}

	@PostMapping("/item-service/decreaseNumber")//2
	public JsonResult<?> decreaseNumber(@RequestBody List<Item> items) {
		return rt.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);
	}

	/////////////////////////////////////////
	
	@GetMapping("/user-service/{userId}")//3
	public JsonResult<User> getUser(@PathVariable Integer userId) {
		return rt.getForObject("http://user-service/{1}", JsonResult.class, userId);
	}

	@GetMapping("/user-service/{userId}/score") //4
	public JsonResult<?> addScore(
			@PathVariable Integer userId,
			@RequestParam Integer score) {
		return rt.getForObject("http://localhost:8101/{1}/score?score={2}", JsonResult.class, userId, score);
	}
	
	/////////////////////////////////////////
	
	@GetMapping("/order-service/{orderId}")//5
	public JsonResult<Order> getOrder(@PathVariable String orderId) {

		/*
		*注册中心的注册表：
		* item-service ---- localhost:8001, localhost:8002
		*
		* 在多个地址之间来回调用
		* */

		return rt.getForObject("http://item-service/{1}", JsonResult.class, orderId);
	}

	@GetMapping("/order-service")//6
	public JsonResult addOrder() {
		return rt.getForObject("http://order-service/", JsonResult.class);
	}
}