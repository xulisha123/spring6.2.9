package com.xushu.controller;

import com.xushu.service.XushuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class XushuController {  // Bean Spring容器

	@Autowired
	private XushuService xushuService;




	@GetMapping("/test")
	public String test() {

		return xushuService.test();
	}

	@PostMapping("/test2")
	public User test(@RequestBody  User user) {
		return user;
	}

	public static class User  {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}
