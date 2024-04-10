package com.dazecake.ocjave;

import com.dazecake.ocjave.service.JoPASService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OcjaveApplicationTests {

	@Resource
	private JoPASService joPASService;

	@Test
	void contextLoads() {
		joPASService.run("/Users/cake/IdeaProjects/Ocjave/src/test/resources/script/test_script.m");
	}

}
