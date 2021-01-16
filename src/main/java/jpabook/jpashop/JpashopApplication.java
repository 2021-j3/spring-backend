package jpabook.jpashop;

import jpabook.jpashop.domain.Account;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.repository.AccountRepository;
import jpabook.jpashop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		SpringApplication.run(JpashopApplication.class, args);

		TestCase t = new TestCase();
		t.Insert();
	}


}
