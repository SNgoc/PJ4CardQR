package com.example.demo.service;

import com.example.demo.Email.EmailValidator;
import com.example.demo.Email.IEmailSender;
import com.example.demo.Exception.ApiRequestException;
import com.example.demo.Payload.Request.CreateOrderRequest;
import com.example.demo.domain.*;
import com.example.demo.repo.*;
import com.example.demo.service.CloudBinary.CloudBinaryService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.commons.io.IOUtils;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepo userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderProceesRepository orderProceesRepository;

    @Autowired
    CategoryRepository categoryRepository;



    EmailValidator emailValidator;
    private final IEmailSender emailSender;
    @Value("${javadocfast.cloudinary.folder.product}")
    private String image;

    private Map<String, String> options = new HashMap<>();

    @Autowired
    private CloudBinaryService cloudinaryService;

    public OrderService(IEmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public List<Orders> showAll() {
        List<Orders> list = orderRepository.findAll();
        return list;
    }

    public Orders details(Long id){
        if(!orderRepository.existsById(id)){
            return null;
        }
        return  orderRepository.findById(id).get();
    }

    public String create(CreateOrderRequest createOrderRequest) throws IOException, WriterException {
        User user = userRepository.findByEmail(createOrderRequest.getEmail());
        Map hintMap = new HashMap();
//        createQRCode(jsonString, filePath, charset, hintMap, 500, 500);
        BitMatrix bitMatrix = new MultiFormatWriter().encode("http://localhost:8081/Display/"+user.getUsername(), BarcodeFormat.QR_CODE, 500, 500, hintMap);
        options.put("folder", image);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File("N:/PJCardTerm4/demo/Qrcode/qrcode.png").toPath());

        File file = new File("N:/PJCardTerm4/demo/Qrcode/qrcode.png");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "image/png", IOUtils.toByteArray(input));
        Map result =   cloudinaryService.upload(multipartFile, options);
        String url =  result.get("url").toString();
        String public_id =  result.get("public_id").toString();
        String token = UUID.randomUUID().toString();
        Product p = new Product("Description",user.getFullname(),user.getLinkImage(),"http://localhost:8081/Display/"+user.getUsername(),url,user, new Date(),0, createOrderRequest.getYear(), token);

        Product productId =  productRepository.save(p);

        Orders newOrder = new Orders();
        newOrder.setFullname(createOrderRequest.getFullname());
        newOrder.setAddress(createOrderRequest.getAddress());
        newOrder.setPhone(createOrderRequest.getPhone());

        int price = 0;
        if(createOrderRequest.getYear()==1){
            price = 12;
        }else if(createOrderRequest.getYear()==3){
            price = 27;
        }else if(createOrderRequest.getYear()==10){
            price = 82;
        }

        newOrder.setPrice(price);
        Category category = categoryRepository.findById(createOrderRequest.getCategory_id()).get();
        //stogare
        category.setQuantity(category.getQuantity()-1);
        categoryRepository.save(category);

        newOrder.setCategory(category);
        newOrder.setUser(user);
        newOrder.setProduct(productId);
        newOrder.setCreatedAt(new Date());
        newOrder.setOrder_process(orderProceesRepository.findById(1L).get());
        orderRepository.save(newOrder);

        String link = "http://localhost:8080/api/order/confirmProduct?token=" + token;
        emailSender.send(
                createOrderRequest.getEmail(),"Active your Product account",
                buildEmail(createOrderRequest.getEmail(), link));
        return token;
    }

    public Boolean nextProcess(Long id){
        Orders order = orderRepository.findById(id).get();
        Long idProcess = order.getOrder_process().getId();
        if( idProcess < 4 ){
            order.setOrder_process(orderProceesRepository.findById(idProcess + 1L).get());
            if(order.getOrder_process().getId() ==2){
                order.setConfirmedAt(new Date());
            }
            if(order.getOrder_process().getId()==3){
                order.setFinishedAt(new Date());
                Product product = order.getProduct();
                product.setStatus(1);
                productRepository.save(product);
            }
            orderRepository.save(order);
            return true;
        }
        return false;
    }

    public Boolean cancelOrder(Long id){
        Orders order = orderRepository.findById(id).get();
        if(order != null){
            order.setOrder_process(orderProceesRepository.findById(4L).get());
            order.setCanceledAt(new Date());
            orderRepository.save(order);
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public RedirectView confirmToken(String token)  {
        Product productConfirm =productRepository.findByToken(token)
                .orElseThrow(() ->
                        new ApiRequestException("token not found"));


        setConfirmedAt(token);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8081/ProductDetails");
        return redirectView;
    }
    private int setConfirmedAt(String token) {
        return productRepository.updateConfirmedAt(token);
    }
    private String buildEmail(String name, String link) {
        return
                "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                        "\n" +
                        "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                        "\n" +
                        "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                        "    <tbody><tr>\n" +
                        "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                        "        \n" +
                        "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                        "          <tbody><tr>\n" +
                        "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                        "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                        "                  <tbody><tr>\n" +
                        "                    <td style=\"padding-left:10px\">\n" +
                        "                  \n" +
                        "                    </td>\n" +
                        "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                        "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                        "                    </td>\n" +
                        "                  </tr>\n" +
                        "                </tbody></table>\n" +
                        "              </a>\n" +
                        "            </td>\n" +
                        "          </tr>\n" +
                        "        </tbody></table>\n" +
                        "        \n" +
                        "      </td>\n" +
                        "    </tr>\n" +
                        "  </tbody></table>\n" +
                        "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                        "    <tbody><tr>\n" +
                        "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                        "      <td>\n" +
                        "        \n" +
                        "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                        "                  <tbody><tr>\n" +
                        "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                        "                  </tr>\n" +
                        "                </tbody></table>\n" +
                        "        \n" +
                        "      </td>\n" +
                        "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                        "    </tr>\n" +
                        "  </tbody></table>\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                        "    <tbody><tr>\n" +
                        "      <td height=\"30\"><br></td>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                        "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                        "        \n" +
                        "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                        "        \n" +
                        "      </td>\n" +
                        "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "      <td height=\"30\"><br></td>\n" +
                        "    </tr>\n" +
                        "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                        "\n" +
                        "</div></div>";
    }

    //api for revenue web and android app///////////////////
    //get order list by user
    public List<Orders> getOrdersByUsername(String username){
        return orderRepository.findOrdersByUser(userRepository.findByUsername(username).get());
    }
    //doanh thu(SNgoc)
    public double getRevenueOrder(){
        List<Double> orderPriceList = orderRepository.revenueOrder();
        double sumRevenue = 0;
        for (double p: orderPriceList) {
            sumRevenue += p;
        }
        return sumRevenue;
    }

    //count order status
    public List<Integer> getSumOrderStatus(){
        List<Integer> orderList = new ArrayList<>();
        orderList.add(orderRepository.orderWaiting());
        orderList.add(orderRepository.orderDelivery());
        orderList.add(orderRepository.orderSuccess());
        orderList.add(orderRepository.orderCancel());
        return orderList;
    }
    /////////////////////////////ANDROID//////////////////

}
