package com.bs.spring.common.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebsoketController {
	@RequestMapping("/chattingpage")
	public String chattingPage() {
		return "chatting/chat";
	}
}
