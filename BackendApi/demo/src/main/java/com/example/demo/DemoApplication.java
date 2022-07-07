package com.example.demo;

import com.example.demo.domain.*;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.OrderProceesRepository;
import com.example.demo.repo.RoleRepo;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private RoleRepo roleRepository;

	private CategoryRepository CategoryRepository;

	private OrderProceesRepository orderProceesRepository;
	public DemoApplication(RoleRepo roleRepository,CategoryRepository categoryRepository,OrderProceesRepository orderProceesRepository) {
		this.orderProceesRepository = orderProceesRepository;
		this.roleRepository = roleRepository;
		this.CategoryRepository = categoryRepository;
	}
	@Autowired
	BCryptPasswordEncoder  encoder;



	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role( ERole.ROLE_USER));
			userService.saveRole(new Role( ERole.ROLE_ADMIN));
			userService.saveRole(new Role( ERole.ROLE_MODERATOR));
			Set<Role> roles11 = new HashSet<>();
			Role roleUser11 = roleRepository.findByName(ERole.ROLE_USER).get();
			roles11.add(roleUser11);
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY,17);
			calendar.set(Calendar.MINUTE,30);
			calendar.set(Calendar.SECOND,0);
			calendar.set(Calendar.MILLISECOND,0);
			User user = new User("John" ,"John@gmail.com",encoder.encode("1234"),roles11,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","Go Vap","1234","Pham trong nghia","NGHIAPHAM","DEVERLOPER", calendar.getTime(),true,"TPHCM");

			User user1 = new User("Will" ,"will@gmail.com",encoder.encode("1234"),roles11,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","Go Vap","1234","Pham trong nghia","NGHIAPHAM","DEVERLOPER",calendar.getTime(),true,"TPHCM");

			userService.saveUser(user);
			userService.saveUser(user1);

			roleRepository.findByName(ERole.ROLE_USER);
			log.info("EEE");
			String enco = "$2a$10$U3muu7cBKmyWGPyGVOHFpOmp49Rzv0.T/4h6LYg68qgHz/XPDSR/K";
			log.info(String.valueOf(encoder.upgradeEncoding(enco)));
			userService.addToRoleUser("John", ERole.ROLE_ADMIN);
			userService.addToRoleUser("Will", ERole.ROLE_USER);

			Category c1 = new Category(70,"CARD",null,null,calendar.getTime(),null,null);
			Category c2 = new Category(50,"STICKER",null,null,calendar.getTime(),null,null);

			CategoryRepository.save(c1);
			CategoryRepository.save(c2);


			Order_Process o = new Order_Process("Cancel");

			Order_Process o1 = new Order_Process("Success");
			orderProceesRepository.save(o);
			orderProceesRepository.save(o1);


		};
	}
}
