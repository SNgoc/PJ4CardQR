package com.example.demo;

import com.example.demo.domain.*;
import com.example.demo.repo.*;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

	private ProductRepository productRepository;

	private OrderRepository orderRepository;

	private ForbiddenWordRepository forbiddenWordRepository;

	public DemoApplication(RoleRepo roleRepository,CategoryRepository categoryRepository,OrderProceesRepository orderProceesRepository,
						   LinkTypeRepository linkTypeRepository, ProductRepository productRepository, OrderRepository orderRepository,
						   ForbiddenWordRepository  forbiddenWordRepository) {
		this.orderProceesRepository = orderProceesRepository;
		this.roleRepository = roleRepository;
		this.CategoryRepository = categoryRepository;
		this.linkTypeRepository = linkTypeRepository;
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
		this.forbiddenWordRepository = forbiddenWordRepository;
	}

	@Autowired
	PasswordEncoder encoder;

	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role( ERole.ROLE_USER));
			userService.saveRole(new Role( ERole.ROLE_ADMIN));
			userService.saveRole(new Role( ERole.ROLE_MODERATOR));
			Set<Role> roles11 = new HashSet<>();
			Set<Role> roles12 = new HashSet<>();
			Role roleUser11 = roleRepository.findByName(ERole.ROLE_USER).get();
			Role roleUser12 = roleRepository.findByName(ERole.ROLE_ADMIN).get();
			roles11.add(roleUser11);
			roles12.add(roleUser12);
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY,17);
			calendar.set(Calendar.MINUTE,30);
			calendar.set(Calendar.SECOND,0);
			calendar.set(Calendar.MILLISECOND,0);

			User user1 = new User("will" ,"will@gmail.com",encoder.encode("123456"),roles12,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","0794619498","1234","Will Smith","Will","DEVERLOPER",new Date(100,9,5),true,"TPHCM",true,true);
			User user2 = new User("john" ,"john@gmail.com",encoder.encode("123456"),roles11,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","0794619497","10 Distric HCM city","Johnny Depp","John","DEVERLOPER",new Date(100,9,5),false,"TPHCM",true,true);
			User user3 = new User("taylor" ,"taylor@gmail.com",encoder.encode("123456"),roles11,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","0794619499","1234","Pham trong nghia","Taylor Swift ","DEVERLOPER", new Date(100,9,5),true,"TPHCM",true,true);
			User user4 = new User("will4" ,"will4@gmail.com",encoder.encode("123456"),roles12,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","0794619498","1234","Will Smith","Will","DEVERLOPER",new Date(100,9,5),true,"TPHCM",true,true);
			User user5 = new User("john5" ,"john5@gmail.com",encoder.encode("123456"),roles11,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","0794619497","10 Distric HCM city","Johnny Depp","John","DEVERLOPER",new Date(100,9,5),false,"TPHCM",true,true);
			User user6 = new User("taylor6" ,"taylor6@gmail.com",encoder.encode("123456"),roles11,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","0794619499","1234","Pham trong nghia","Taylor Swift ","DEVERLOPER", new Date(100,9,5),true,"TPHCM",true,true);
			User user7 = new User("will7" ,"will7@gmail.com",encoder.encode("123456"),roles12,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","0794619498","1234","Will Smith","Will","DEVERLOPER",new Date(100,9,5),true,"TPHCM",true,true);
			User user8 = new User("john8" ,"john8@gmail.com",encoder.encode("123456"),roles11,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","0794619497","10 Distric HCM city","Johnny Depp","John","DEVERLOPER",new Date(100,9,5),false,"TPHCM",true,true);
			User user9 = new User("taylor9" ,"taylor9@gmail.com",encoder.encode("123456"),roles11,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","0794619499","1234","Pham trong nghia","Taylor Swift ","DEVERLOPER", new Date(100,9,5),true,"TPHCM",true,true);
			User user10 = new User("will10" ,"will10@gmail.com",encoder.encode("123456"),roles12,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","0794619498","1234","Will Smith","Will","DEVERLOPER",new Date(100,9,5),true,"TPHCM",true,true);
			User user11= new User("john11" ,"john11@gmail.com",encoder.encode("123456"),roles11,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","0794619497","10 Distric HCM city","Johnny Depp","John","DEVERLOPER",new Date(100,9,5),false,"TPHCM",true,true);
			User user12= new User("taylor12" ,"taylor12@gmail.com",encoder.encode("123456"),roles11,"v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","https://res.cloudinary.com/tphcm/image/upload/v1648873229/image/o5s5twfrmgfu0q3smg0j.jpg","0794619499","1234","Pham trong nghia","Taylor Swift ","DEVERLOPER", new Date(100,9,5),true,"TPHCM",true,true);


			userService.saveUser(user3);
			userService.saveUser(user1);
			userService.saveUser(user2);

			userService.saveUser(user4);
			userService.saveUser(user5);
			userService.saveUser(user6);

			userService.saveUser(user7);
			userService.saveUser(user8);
			userService.saveUser(user9);

			userService.saveUser(user10);
			userService.saveUser(user11);
			userService.saveUser(user12);

			Category c1 = new Category(70,"CARD",90,"https://res.cloudinary.com/tphcm/image/upload/v1658486444/category/01_fporii.jpg","https://res.cloudinary.com/tphcm/image/upload/v1658486444/category/02_k69tyn.jpg",calendar.getTime(),null,null);
			Category c2 = new Category(50,"STICKER",10,"https://res.cloudinary.com/tphcm/image/upload/v1658486444/category/03_kudrpc.jpg","https://res.cloudinary.com/tphcm/image/upload/v1658486444/category/04_altabb.jpg",calendar.getTime(),null,null);

			CategoryRepository.save(c1);
			CategoryRepository.save(c2);


			Order_Process o1 = new Order_Process("Waiting", "badge badge-secondary", "Confirm and deliver the order");
			Order_Process o2 = new Order_Process("Delivery", "badge badge-primary", "Complete order");
			Order_Process o3 = new Order_Process("Success", "badge badge-success", "Order completed");
			Order_Process o4 = new Order_Process("Cancel", "badge badge-danger", "Cancel this order");

			orderProceesRepository.save(o1);
			orderProceesRepository.save(o2);
			orderProceesRepository.save(o3);
			orderProceesRepository.save(o4);


			LinkType l1 = new LinkType(1L, "Facebook", "Enter your Facebook Link", "", "https://res.cloudinary.com/tphcm/image/upload/v1657613872/linktype/facebook_fn3mvs.png","https://www.facebook.com/");
			LinkType l2 = new LinkType(2L, "Instagram", "Enter your Instagram Link", "", "https://res.cloudinary.com/tphcm/image/upload/v1657613873/linktype/instagram_vfmvaa.png", "https://www.instagram.com/");
			LinkType l3 = new LinkType(3L, "Telegram", "Enter your Telegram Link", "",  "https://res.cloudinary.com/tphcm/image/upload/v1657613872/linktype/telegram_nr2uxs.png","https://t.me/");
			LinkType l4 = new LinkType(4L, "WhatsApp", "Enter your WhatsApp Link", "",  "https://res.cloudinary.com/tphcm/image/upload/v1657613872/linktype/whatsApp_cpfcbl.png","https://wa.me/");
			LinkType l5 = new LinkType(5L, "Twitter", "Enter your Twitter Link", "",  "https://res.cloudinary.com/tphcm/image/upload/v1657613871/linktype/twitter_aqkjiy.png", "https://twitter.com/");
			LinkType l6 = new LinkType(6L, "Phone", "Enter your Phone number", "", "https://res.cloudinary.com/tphcm/image/upload/v1658477403/linktype/contact-page-for-flatsome-wordpress-theme-pointed-icon-phone_isnjtw.png","tel://");
			LinkType l7 = new LinkType(7L, "Email", "Enter your Email", "",  "https://res.cloudinary.com/tphcm/image/upload/v1658478217/linktype/images_u3t6df.png","mailto:");
			LinkType l8 = new LinkType(8L, "Url", "Enter your Link","", "https://res.cloudinary.com/tphcm/image/upload/v1658477950/linktype/download_yomp4q.png",  "https://");

			linkTypeRepository.save(l1);
			linkTypeRepository.save(l2);
			linkTypeRepository.save(l3);
			linkTypeRepository.save(l4);
			linkTypeRepository.save(l5);
			linkTypeRepository.save(l6);
			linkTypeRepository.save(l7);
			linkTypeRepository.save(l8);

			Product product1 = new Product("Description", "Will Smith","https://res.cloudinary.com/tphcm/image/upload/v1658420873/avatar/download_wvczcu.jpg", "http://localhost:8081/Display/will","https://res.cloudinary.com/tphcm/image/upload/v1658464444/product/ndylx8dtyj2tj6zmvytl.png", user1,1,1,30,new Date(122, 02, 11));
			productRepository.save(product1);
			Product product2 = new Product("Description", "Johnny Depp","https://res.cloudinary.com/tphcm/image/upload/v1658559824/avatar/images_hqmsvu.jpg", "http://localhost:8081/Display/john","https://res.cloudinary.com/tphcm/image/upload/v1658464444/product/ndylx8dtyj2tj6zmvytl.png", user2,1,3,30,new Date(121, 06, 20));
			productRepository.save(product1);
			Product product3 = new Product("Description", "Taylor Swift","https://res.cloudinary.com/tphcm/image/upload/v1658560076/avatar/taylorswift_sxidpv.jpg", "http://localhost:8081/Display/taylor","https://res.cloudinary.com/tphcm/image/upload/v1658464444/product/ndylx8dtyj2tj6zmvytl.png", user3,1,10,30,new Date(120, 06, 18));

			productRepository.save(product1);
			productRepository.save(product2);
			productRepository.save(product3);

			Orders order1 = new Orders(1L, 12 , c1, user1, " Sherman Oaks, Los Angeles, California, American", "01937464843","Will Smith",o1, product1,new Date(122, 02, 11),null,null,null);
			orderRepository.save(order1);
			Orders order2 = new Orders(2L, 27 , c1, user2, " Sherman Oaks, Los Angeles, California, American", "01937464843","Johnny Depp",o3, product1,new Date(122, 03, 11),new Date(122, 02, 11),new Date(122, 02, 11),null);
			orderRepository.save(order2);
			Orders order3 = new Orders(3L, 82 , c2, user3, " Sherman Oaks, Los Angeles, California, American", "01937464843","Taylor Swift",o3, product1,new Date(122, 04, 11),new Date(122, 02, 11),new Date(122, 02, 11),null);
            orderRepository.save(order3);
			Orders order4= new Orders(4L, 27 , c1, user4, " Sherman Oaks, Los Angeles, California, American", "01937464843","Johnny Depp",o3, product1,new Date(122, 05, 11),new Date(122, 02, 11),new Date(122, 02, 11),null);
			orderRepository.save(order4);
			Orders order5 = new Orders(5L, 82 , c2, user5, " Sherman Oaks, Los Angeles, California, American", "01937464843","Taylor Swift",o3, product1,new Date(122, 06, 11),new Date(122, 02, 11),new Date(122, 02, 11),null);
			orderRepository.save(order5);
			Orders order6 = new Orders(6L, 82 , c2, user6, " Sherman Oaks, Los Angeles, California, American", "01937464843","Taylor Swift",o3, product1,new Date(122, 07, 11),new Date(122, 02, 11),new Date(122, 02, 11),null);
			Orders order7 = new Orders(7L, 27 , c1, user7, " Sherman Oaks, Los Angeles, California, American", "01937464843","Johnny Depp",o3, product1,new Date(122, 8, 11),new Date(122, 02, 11),new Date(122, 02, 11),null);
			orderRepository.save(order7);
			orderRepository.save(order6);
			Orders order8 = new Orders(8L, 82 , c2, user8, " Sherman Oaks, Los Angeles, California, American", "01937464843","Taylor Swift",o3, product1,new Date(122, 9, 11),new Date(122, 02, 11),new Date(122, 02, 11),null);
			Orders order9 = new Orders(9L, 27 , c1, user9, " Sherman Oaks, Los Angeles, California, American", "01937464843","Johnny Depp",o3, product1,new Date(122, 10, 11),new Date(122, 02, 11),new Date(122, 02, 11),null);
			orderRepository.save(order8);
			Orders order10 = new Orders(10L, 82 , c2, user10, " Sherman Oaks, Los Angeles, California, American", "01937464843","Taylor Swift",o3, product1,new Date(122, 11, 11),new Date(122, 02, 11),new Date(122, 02, 11),null);
			Orders order11 = new Orders(11L, 27 , c1, user11, " Sherman Oaks, Los Angeles, California, American", "01937464843","Johnny Depp",o3, product1,new Date(122, 12, 11),new Date(122, 02, 11),new Date(122, 02, 11),null);
			orderRepository.save(order11);
			Orders order12 = new Orders(12L, 82 , c2, user12, " Sherman Oaks, Los Angeles, California, American", "01937464843","Taylor Swift",o3, product1,new Date(122, 04, 11),new Date(122, 02, 11),new Date(122, 02, 11),null);
			orderRepository.save(order9);
			orderRepository.save(order10);
			orderRepository.save(order12);

			ForbiddenReview forbidden1 = new ForbiddenReview(1L, "bitch");
			ForbiddenReview forbidden2 = new ForbiddenReview(2L, "fuck");
			ForbiddenReview forbidden3 = new ForbiddenReview(3L, "damn");
			ForbiddenReview forbidden4 = new ForbiddenReview(4L, "bad");
			ForbiddenReview forbidden5 = new ForbiddenReview(5L, "crazy");
			ForbiddenReview forbidden6 = new ForbiddenReview(6L, "nonsense");
			ForbiddenReview forbidden7 = new ForbiddenReview(7L, "ridiculous");
			ForbiddenReview forbidden8 = new ForbiddenReview(7L, "asshole");
			ForbiddenReview forbidden9 = new ForbiddenReview(7L, "fucker");
			ForbiddenReview forbidden10 = new ForbiddenReview(7L, "idiot");

			forbiddenWordRepository.save(forbidden1);
			forbiddenWordRepository.save(forbidden2);
			forbiddenWordRepository.save(forbidden3);
			forbiddenWordRepository.save(forbidden4);
			forbiddenWordRepository.save(forbidden5);
			forbiddenWordRepository.save(forbidden6);
			forbiddenWordRepository.save(forbidden7);
			forbiddenWordRepository.save(forbidden8);
			forbiddenWordRepository.save(forbidden9);
			forbiddenWordRepository.save(forbidden10);

		};
	}
}
