package com.and.middle;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AndController {

	//@ResponseBody
	@RequestMapping("/and")
	public String test() {
		
		return "test";
	}
}
