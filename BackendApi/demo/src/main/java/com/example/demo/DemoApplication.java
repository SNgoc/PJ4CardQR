package com.example.demo;

import com.example.demo.domain.*;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.LinkTypeRepository;
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

	private LinkTypeRepository linkTypeRepository;
	public DemoApplication(RoleRepo roleRepository,CategoryRepository categoryRepository,OrderProceesRepository orderProceesRepository,
						   LinkTypeRepository linkTypeRepository) {
		this.orderProceesRepository = orderProceesRepository;
		this.roleRepository = roleRepository;
		this.CategoryRepository = categoryRepository;
		this.linkTypeRepository = linkTypeRepository;
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
			user.setEnable(true);
			user.setLocked(true);

			User user1 = new User("Will" ,"will@gmail.com",encoder.encode("1234"),roles11,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","Go Vap","1234","Pham trong nghia","NGHIAPHAM","DEVERLOPER",calendar.getTime(),true,"TPHCM");
			user1.setEnable(false);
			user1.setLocked(false);

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


			Order_Process o1 = new Order_Process("Waiting", "badge badge-secondary", "confirm and deliver the order");
			Order_Process o2 = new Order_Process("Delivery", "badge badge-primary", "complete order");
			Order_Process o3 = new Order_Process("Success", "badge badge-success", "order completed");
			Order_Process o4 = new Order_Process("Cancel", "badge badge-danger", "Cancel this order");

			orderProceesRepository.save(o1);
			orderProceesRepository.save(o2);
			orderProceesRepository.save(o3);
			orderProceesRepository.save(o4);


			LinkType l1 = new LinkType(1L, "Facebook", "Enter your Facebook Link", "", "https://res.cloudinary.com/tphcm/image/upload/v1657613872/linktype/facebook_fn3mvs.png");
			LinkType l2 = new LinkType(2L, "Instagram", "Enter your Instagram Link", "", "https://res.cloudinary.com/tphcm/image/upload/v1657613873/linktype/instagram_vfmvaa.png");
			LinkType l3 = new LinkType(3L, "Telegram", "Enter your Telegram Link", "",  "https://res.cloudinary.com/tphcm/image/upload/v1657613872/linktype/telegram_nr2uxs.png");
			LinkType l4 = new LinkType(4L, "WhatsApp", "Enter your WhatsApp Link", "",  "https://res.cloudinary.com/tphcm/image/upload/v1657613872/linktype/whatsApp_cpfcbl.png");
			LinkType l5 = new LinkType(5L, "Twitter", "Enter your Twitter Link", "",  "https://res.cloudinary.com/tphcm/image/upload/v1657613871/linktype/twitter_aqkjiy.png");
			LinkType l6 = new LinkType(6L, "Phone", "Enter your Phone number", "", "https://res.cloudinary.com/tphcm/image/upload/v1657613871/linktype/phone_ojgmtj.png");
			LinkType l7 = new LinkType(7L, "Email", "Enter your Email", "",  "https://res.cloudinary.com/tphcm/image/upload/v1657613871/linktype/gmail_qbsqox.png");
			LinkType l8 = new LinkType(8L, "Url", "Enter your Link", "",  "");

			linkTypeRepository.save(l1);
			linkTypeRepository.save(l2);
			linkTypeRepository.save(l3);
			linkTypeRepository.save(l4);
			linkTypeRepository.save(l5);
			linkTypeRepository.save(l6);
			linkTypeRepository.save(l7);
			linkTypeRepository.save(l8);
		};
	}
}
