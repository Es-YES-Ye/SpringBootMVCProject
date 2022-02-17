package com.sesac.springBootMVCProject;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sesac.springBootMVCProject.vo.CarVO;

@SpringBootTest
class SpringBootMvcProjectApplicationTests {

	@Test
	public void test3() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			System.out.println(i + "--------------");
			CarVO car = CarVO.builder().model("bbbb").price(22222).build();
			CarVO car2 = CarVO.builder().model("bbbb").price(22222).build();
			System.out.println(car);
			System.out.println(car.equals(car2));
		});
	}

	// @Test
	public void test2() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			System.out.println(i + "--------------");
			CarVO car = CarVO.builder().model("자동차" + i).build();
			System.out.println(car);
			System.out.println(car.getModel());
		});
	}

	// @Test
	void contextLoads() {
	}

	// @Test
	public void test1() {
		// lombok 연습하기
		// 사용하는 목적 게터세터투스트링을 어노테이션으로 자동생성
		// CarVO car1 = new CarVO();
		// car1.setModel("드림카");
		// car1.setPrice(300000);
		// System.out.println(car1);

		// CarVO car2 = new CarVO("ABC", 1000);
		//System.out.println(car2);

		CarVO car3 = CarVO.builder().model("car3모델").price(99999).build();
		System.out.println(car3);

	}
}
