package cn.tedu.sp09.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp09.service.ItemClient;
import cn.tedu.sp09.service.OrderClient;
import cn.tedu.sp09.service.UserClient;
import cn.tedu.web.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeignController {
	@Autowired
	private ItemClient itemService;
	@Autowired
	private UserClient userService;
	@Autowired
	private OrderClient orderService;
	
	@GetMapping("/item-service/{orderId}")
	public JsonResult<List<Item>> getItems(@PathVariable String orderId) {
		return itemService.getItems(orderId);
	}

	@PostMapping("/item-service/decreaseNumber")
	public JsonResult decreaseNumber(@RequestBody List<Item> items) {
		return itemService.decreaseNumber(items);
	}

	/////////////////////////////////////////
	
	@GetMapping("/user-service/{userId}")
	public JsonResult<User> getUser(@PathVariable Integer userId) {
		return userService.getUser(userId);
	}

	@GetMapping("/user-service/{userId}/score") 
	public JsonResult addScore(@PathVariable Integer userId, Integer score) {
		return userService.addScore(userId, score);
	}
	
	/////////////////////////////////////////
	
	@GetMapping("/order-service/{orderId}")
	public JsonResult<Order> getOrder(@PathVariable String orderId) {
		return orderService.getOrder(orderId);
	}

	@GetMapping("/order-service")
	public JsonResult addOrder() {
		return orderService.addOrder();
	}
}