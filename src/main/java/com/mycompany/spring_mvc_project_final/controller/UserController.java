/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.entities.ColorEntity;
import com.mycompany.spring_mvc_project_final.entities.CreditCardEntity;
import com.mycompany.spring_mvc_project_final.entities.OrderDetailEntity;
import com.mycompany.spring_mvc_project_final.entities.OrdersEntity;
import com.mycompany.spring_mvc_project_final.entities.PaymentEntity;
import com.mycompany.spring_mvc_project_final.entities.ProductDetailEntity;
import com.mycompany.spring_mvc_project_final.entities.ProductEntity;
import com.mycompany.spring_mvc_project_final.entities.SizeEntity;
import com.mycompany.spring_mvc_project_final.entities.UserEntity;
import com.mycompany.spring_mvc_project_final.enums.Gender;
import com.mycompany.spring_mvc_project_final.repository.ProductDetailRepository;
import com.mycompany.spring_mvc_project_final.repository.ProductRepository;
import com.mycompany.spring_mvc_project_final.service.CategoryService;
import com.mycompany.spring_mvc_project_final.service.ColorService;
import com.mycompany.spring_mvc_project_final.service.CreditCardService;
import com.mycompany.spring_mvc_project_final.service.FavoriteService;
import com.mycompany.spring_mvc_project_final.service.OrderDetailService;
import com.mycompany.spring_mvc_project_final.service.OrderService;
import com.mycompany.spring_mvc_project_final.service.PaymentService;
import com.mycompany.spring_mvc_project_final.service.ProductDetailService;
import com.mycompany.spring_mvc_project_final.service.ProductService;
import com.mycompany.spring_mvc_project_final.service.SizeService;
import com.mycompany.spring_mvc_project_final.service.UserDetailsServiceImpl;
import com.mycompany.spring_mvc_project_final.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDetailService detailService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductDetailRepository detailRepository;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private UserService userSer;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String viewHome(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        model.addAttribute("message", "Hello User: " + username);
        model.addAttribute("newP", productService.getNewProduct());
        // model.addAttribute("newP", detailService.getNew());
        model.addAttribute("favorite", favoriteService.getFavorite());

        return "home";
    }

    // Quay lại trang User
    @RequestMapping(value = "/user/user-page", method = RequestMethod.GET)
    public String userPage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }
        model.addAttribute("users", userService.findByUser(username));

        return "userPage";
    }

    // Thay đổi thông tin User
    @RequestMapping("/updateAccount/{id}")
    public String updateAccount(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("gender", Gender.values());
        model.addAttribute("action", "update-account");
        model.addAttribute("btnName", "Update");
        model.addAttribute("headerName", "CHANGE PERSONAL INFORMATION");
        // model.addAttribute("lstRole", roleService.getLstRole());
        model.addAttribute("account", userService.findUserById(id));

        return "updatePage";
    }

    // Nhập thông tin khách hàng khi check out
    @RequestMapping("/customerinfor")
    public String customerInfor(Model model) {
        model.addAttribute("action", "infomation");
        model.addAttribute("headerName", "PERSONAL INFORMATION");

        return "infoPage";
    }

    //Register Hoặc update User thành công
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String result(Model model,
            @Valid @ModelAttribute("account") UserEntity userEntity,
            BindingResult result) {
        if (result.hasErrors()) {
            if (userEntity.getId() > 0) {
                model.addAttribute("gender", Gender.values());
                model.addAttribute("action", "update-account");
                model.addAttribute("btnName", "Confirm");
                model.addAttribute("headerName", "PERSONAL INFORMATION");
            }
            return "updatePage";
        }

        userSer.saveUser(userEntity);
        return "updateSuccess";
    }

    //Check login
    @RequestMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) boolean error) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();

            model.addAttribute("users", userService.findByUser(username));

            return "userPage";
        }
        if (error) {
            model.addAttribute("message", "Login Fail!!!");
        }
        return "login";
    }

    //Search và hiển thị product 
    @RequestMapping("/product/{categoryName}/{page}")
    public String productByCategory(Model model,
            @PathVariable(value = "categoryName") String categoryName,
            @PathVariable("page") int page) {
        model.addAttribute("productDetail", detailService.getProductByCategory(categoryName, page));
        model.addAttribute("products", productService.getProductByCate(categoryName, page));

        return "product";
    }

    // Hiển thị trang shop theo page
    @RequestMapping("/product/{page}")
    public String productPage(Model model,
            @PathVariable("page") int page) {
        model.addAttribute("products", productService.getLimit(page));
        model.addAttribute("count", productService.getCount());
        //     model.addAttribute("count", detailService.getCount());

        //       model.addAttribute("products", productService.getProductList());
        //  model.addAttribute("productDetail", detailService.getProductLimit(page));
        return "product";

    }

    //Hiển thị chi tiết sản phẩm
    @RequestMapping("/product-detail/{id}")
    public String productDetailPage(Model model,
            @PathVariable("id") int id,
            @ModelAttribute("productDetail") ProductDetailEntity detailEntity) {

        ProductDetailEntity detailEntity1 = detailService.findProductById(id);

        int productId = detailEntity1.getProducts().getId();

        model.addAttribute("product", productService.findProductById(id));
        model.addAttribute("detail", detailService.findProductById(id));
        model.addAttribute("distinctColor", detailService.findColorDistin(productId));
        model.addAttribute("distinctSize", detailService.findSizeDistin(productId));
        model.addAttribute("colors", colorService.getColor());
        model.addAttribute("size", sizeService.getSize());
        //    model.addAttribute("productDetail", detailEntity);

        return "product-detail";
    }

    @RequestMapping(value = "/updateProductDetailId", method = RequestMethod.POST)
    public String updateDetail(Model model,
            @RequestParam("quantity") int quantity,
            @RequestParam("productId") int productId,
            @RequestParam("colorId") int colorId,
            @RequestParam("sizeId") int sizeId) {

//        ColorEntity colors = colorService.findColorById(colorId);   //timf color
//        SizeEntity size1 = sizeService.findById(sizeId);
//        ProductEntity product = productService.findProductById(productId);
//        ProductDetailEntity detailEntity = new ProductDetailEntity();
        int i = detailRepository.ProductDetailId(productId, colorId, sizeId);
        ProductDetailEntity detailEntity = detailService.findProductById(i);

        if (detailEntity.getQuantity() == 0) {
            model.addAttribute("message", "This product is out stock !");
            //      model.addAttribute("product", productService.findProductById(productId));
            model.addAttribute("detail", detailService.findProductById(i));
            model.addAttribute("distinctColor", detailService.findColorDistin(productId));
            model.addAttribute("distinctSize", detailService.findSizeDistin(productId));
            return "product-detail";
        }

        if (quantity > detailEntity.getQuantity()) {
            model.addAttribute("message", "This product does not have enough quantity");
            //    model.addAttribute("product", productService.findProductById(productId));
            model.addAttribute("detail", detailService.findProductById(i));
            model.addAttribute("distinctColor", detailService.findColorDistin(productId));
            model.addAttribute("distinctSize", detailService.findSizeDistin(productId));
            return "product-detail";
        }

        model.addAttribute("detail", detailService.findProductById(i));
        model.addAttribute("distinctColor", detailService.findColorDistin(productId));
        model.addAttribute("distinctSize", detailService.findSizeDistin(productId));

        return "product-detail";
    }

    @RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
    public String updateProduct(Model model,
            @RequestParam("quantity") int quantity,
            @RequestParam("productId") int productId,
            @RequestParam("colorId") int colorId,
            @RequestParam("sizeId") int sizeId) {

//        ColorEntity colors = colorService.findColorById(colorId);   //timf color
//        SizeEntity size1 = sizeService.findById(sizeId);
//        ProductEntity product = productService.findProductById(productId);
//        ProductDetailEntity detailEntity = new ProductDetailEntity();
        int i = detailRepository.ProductDetailId(productId, colorId, sizeId);
        ProductDetailEntity detailEntity = detailService.findProductById(i);

        if (detailEntity.getQuantity() == 0) {
            model.addAttribute("message", "This product is out stock ! ");
            //   model.addAttribute("product", productService.findProductById(productId));
            model.addAttribute("detail", detailService.findProductById(i));
            model.addAttribute("distinctColor", detailService.findColorDistin(productId));
            model.addAttribute("distinctSize", detailService.findSizeDistin(productId));
            return "updateProduct";
        }

        if (quantity > detailEntity.getQuantity()) {
            model.addAttribute("message", "This product does not have enough quantity");
            //   model.addAttribute("product", productService.findProductById(productId));
            model.addAttribute("detail", detailService.findProductById(i));
            model.addAttribute("distinctColor", detailService.findColorDistin(productId));
            model.addAttribute("distinctSize", detailService.findSizeDistin(productId));
            return "updateProduct";
        }

        model.addAttribute("detail", detailService.findProductById(i));
        model.addAttribute("distinctColor", detailService.findColorDistin(productId));
        model.addAttribute("distinctSize", detailService.findSizeDistin(productId));

        return "updateProduct";
    }

    //Order sản phẩm
    @RequestMapping("/order/{id}/{quantity}")
    public String orderPage(Model model,
            @PathVariable("id") int id,
            @PathVariable("quantity") int quantity,
            //@PathVariable("productId") int productId,
            //@RequestParam("colorId") int colorId,
            //@RequestParam("sizeId") int sizeId,
            HttpSession session) {
        HashMap<Integer, OrderDetailEntity> orderDetailItems = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("myOrderDetailItems");

        if (orderDetailItems == null) {
            orderDetailItems = new HashMap<>();
        }

        ProductDetailEntity detailEntity = detailService.findProductById(id);

        int productId = detailEntity.getProducts().getId();

        if (quantity > detailEntity.getQuantity()) {
            model.addAttribute("message", "This product does not have enough quantity");
            model.addAttribute("detail", detailService.findProductById(id));
            model.addAttribute("distinctColor", detailService.findColorDistin(productId));
            model.addAttribute("distinctSize", detailService.findSizeDistin(productId));
            return "product-detail";
        }

        if (orderDetailItems.containsKey(id)) {
            OrderDetailEntity item = orderDetailItems.get(id);
            item.setProductOrderDetail(detailEntity.getProducts());
            item.setColor(detailEntity.getColor().getColor());
            item.setSize(detailEntity.getSize().getSize());
            item.setUnitPrice(detailEntity.getPrice());
            item.setQuantity(item.getQuantity() + quantity);
            model.addAttribute("detail", detailEntity);
            orderDetailItems.put(id, item);

        } else {
            model.addAttribute("detail", detailEntity);
            OrderDetailEntity item = new OrderDetailEntity();
            item.setQuantity(item.getQuantity() + quantity);
            item.setProductOrderDetail(detailEntity.getProducts());
            item.setUnitPrice(detailEntity.getPrice());
            item.setColor(detailEntity.getColor().getColor());
            item.setSize(detailEntity.getSize().getSize());
            orderDetailItems.put(id, item);
        }
        model.addAttribute("detail", detailEntity);
        session.setAttribute("myOrderDetailItems", orderDetailItems);
        session.setAttribute("myOrderDetailTotal", totalPrices(session));

        return "redirect:/order";
    }

    //Order sản phảm trực tiếp tại trang Shop
    @RequestMapping("/addorder/{id}/{page}")
    public String orderPage1(Model model,
            @PathVariable("id") int ditailId,
            @PathVariable("page") int page,
            HttpSession session) {
        HashMap<Integer, OrderDetailEntity> orderDetailItems = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("myOrderDetailItems");

        ProductDetailEntity detail = detailService.findProductById(ditailId);
        int quantityProduct = detail.getQuantity();

        double price = detail.getPrice();
        if (1 > quantityProduct) {
            model.addAttribute("message", "This product is out stock !");
            model.addAttribute("products", productService.getLimit(page));
            //    model.addAttribute("products", productService.getProductList());
            model.addAttribute("count", productService.getCount());
            return "product";

        }

        if (orderDetailItems == null) {
            orderDetailItems = new HashMap<>();
        }

        if (orderDetailItems.containsKey(ditailId)) {

            OrderDetailEntity item = orderDetailItems.get(ditailId);
            item.setProductOrderDetail(detail.getProducts());
            item.setUnitPrice(price);
            item.setColor(detail.getColor().getColor());
            item.setSize(detail.getSize().getSize());
            item.setQuantity(item.getQuantity() + 1);

            orderDetailItems.put(ditailId, item);

        } else {
            OrderDetailEntity item = new OrderDetailEntity();
            item.setProductOrderDetail(detail.getProducts());
            item.setUnitPrice(price);
            item.setColor(detail.getColor().getColor());
            item.setSize(detail.getSize().getSize());
            item.setQuantity(1);
            orderDetailItems.put(ditailId, item);
        }

        session.setAttribute("myOrderDetailItems", orderDetailItems);
        session.setAttribute("myOrderDetailTotal", totalPrices(session));

        return "order";
    }

    // Xóa sản phẩm tại trang Order
    @RequestMapping("removeorder/{productId}/{color}/{size}")
    public String viewRemove(ModelMap model,
            HttpSession session,
            @PathVariable("productId") int productId,
            @PathVariable("color") String color,
            @PathVariable("size") String size
    ) {
        HashMap<Integer, OrderDetailEntity> orderDetailItems = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("myOrderDetailItems");
        if (orderDetailItems == null) {
            orderDetailItems = new HashMap<>();
        }
        int colorId = colorService.findColor(color);
        int sizeId = sizeService.findSize(size);

        int i = detailRepository.ProductDetailId(productId, colorId, sizeId);

        if (orderDetailItems.containsKey(i)) {
            orderDetailItems.remove(i);
        }

        session.setAttribute("myOrderDetailItems", orderDetailItems);
        session.setAttribute("myOrderDetailTotal", totalPrices(session));

        return "redirect:/order";
    }

    // update số lượng tại trang Order
    @RequestMapping(value = "/updateorder", method = RequestMethod.POST)
    public String viewUpdate(ModelMap model, HttpSession session,
            @ModelAttribute("productId") int productId,
            @ModelAttribute("color") String color,
            @ModelAttribute("size") String size,
            @ModelAttribute("quantity") int quantity
    ) {
        HashMap<Integer, OrderDetailEntity> orderDetailItems = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("myOrderDetailItems");
        if (orderDetailItems == null) {
            orderDetailItems = new HashMap<>();
        }
        int colorId = colorService.findColor(color);
        int sizeId = sizeService.findSize(size);

        int i = detailRepository.ProductDetailId(productId, colorId, sizeId);
        ProductDetailEntity detail = detailService.findProductById(i);

        ProductEntity product = productService.findProductById(productId);

        if (product != null) {
            if (orderDetailItems.containsKey(i)) {
                if (quantity > detail.getQuantity()) {
                    model.addAttribute("message", "This product does not have enough quantity");
                    return "order";
                } else {
                    OrderDetailEntity item = orderDetailItems.get(i);
                    item.setProductOrderDetail(product);
                    item.setQuantity(quantity);
                    orderDetailItems.put(i, item);
                }
            } else {
                OrderDetailEntity item = new OrderDetailEntity();
                item.setProductOrderDetail(product);
                item.setQuantity(1);
                orderDetailItems.put(i, item);
            }
        }

        session.setAttribute("myOrderDetailItems", orderDetailItems);
        session.setAttribute("myOrderDetailTotal", totalPrices(session));

        return "redirect:/order";
    }

//    public double totalPrice(HashMap<Integer, OrderDetailEntity> orderDetailItems) {
//        int count = 0;
//        for (Map.Entry<Integer, OrderDetailEntity> list : orderDetailItems.entrySet()) {
//            count += list.getValue().getProductOrderDetail().getPrice() * list.getValue().getQuantity();
//        }
//        return count;
//    }
    
    
    // Tính Total Price tại trang Order
    public double totalPrices(HttpSession session) {
        HashMap<Integer, OrderDetailEntity> OrderDetailEntity = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("myOrderDetailItems");

        int count = 0;
        for (Map.Entry<Integer, OrderDetailEntity> list : OrderDetailEntity.entrySet()) {
            int productId = list.getValue().getProductOrderDetail().getId();
            int colorId = colorService.findColor(list.getValue().getColor());
            int sizeId = sizeService.findSize(list.getValue().getSize());
            int detailId = detailRepository.ProductDetailId(productId, colorId, sizeId);

            ProductDetailEntity detail = detailService.findProductById(detailId);

            count += detail.getPrice() * list.getValue().getQuantity();
        }
        return count;
    }

    //check khách hàng đã login hay chưa login để check out
    @RequestMapping("/checkout")
    public String checkoutPage(HttpSession session,
            Model model) {
        HashMap<Integer, OrderDetailEntity> OrderDetailEntity = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("myOrderDetailItems");

        for (Map.Entry<Integer, OrderDetailEntity> oder : OrderDetailEntity.entrySet()) {
            int productId = oder.getValue().getProductOrderDetail().getId();
            int colorId = colorService.findColor(oder.getValue().getColor());
            int sizeId = sizeService.findSize(oder.getValue().getSize());
            int detailId = detailRepository.ProductDetailId(productId, colorId, sizeId);

            ProductDetailEntity detail = detailService.findProductById(detailId);
            int quantityOrder = oder.getValue().getQuantity();
            int quantityProduct = detail.getQuantity();

            if (quantityOrder > quantityProduct) {
                model.addAttribute("message", "Product " + detail.getProducts().getName()
                        + "/" + detail.getColor().getColor()
                        + "/" + detail.getSize().getSize() + " not enough quantity");
                return "order";
            }

        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        if (principal instanceof UserDetails) {

            username = ((UserDetails) principal).getUsername();

            model.addAttribute("users", userService.findByUser(username));

            return "userPage";

        } else {
            model.addAttribute("action", "infomation");
            model.addAttribute("headerName", "PERSONAL INFORMATION");

            return "infoPage";
        }
    }

    // Form search sản phẩm
    @RequestMapping(value = "/search/{page}", method = RequestMethod.POST)
    public String searchProduct(Model model,
            @RequestParam("strSearch") String strSearch,
            @PathVariable("page") int page) {

        //   model.addAttribute("productDetail", detailService.searchProduct(strSearch));
        //   model.addAttribute("products", productService.searchNameOrPrice(strSearch, price));
        model.addAttribute("count", productService.getCount1(strSearch));
        model.addAttribute("products", productService.searchProduct(strSearch, page));

        return "product";
    }

    @RequestMapping("/search-price/{price1}/{price2}")
    public String searchPrice(Model model,
            @PathVariable("price1") double price1,
            @PathVariable("price2") double price2) {
        model.addAttribute("products", productService.searchPrice2(price1, price2));
        // model.addAttribute("productDetail", detailService.searchPrice(price1,price2));

        return "product";
    }

    //Gửi Mail Cho Khách Hàng
    @RequestMapping(value = "/infomation", method = RequestMethod.POST)
    public String infomation(Model model,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "cardNumber", required = false) String cardNumber,
            @RequestParam(name = "expDate", required = false) String expireDate,
            @RequestParam(name = "cvcCode", required = false) String cvv,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "fullname", required = false) String fullname,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            HttpSession session) {
        HashMap<Integer, OrderDetailEntity> OrderDetailEntity = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("myOrderDetailItems");

        CreditCardEntity creditCardEntity = creditCardService.getCreditCard(cardNumber);

        if (creditCardEntity.getName().equals(name) && creditCardEntity.getCardNumber().equals(cardNumber)) {
            double totalPrice = 0;
            for (Map.Entry<Integer, OrderDetailEntity> ode : OrderDetailEntity.entrySet()) {

                totalPrice = totalPrice + ode.getValue().getQuantity() * ode.getValue().getUnitPrice();
            }

            if (creditCardEntity.getBalance() >= totalPrice) {

                for (Map.Entry<Integer, OrderDetailEntity> ode : OrderDetailEntity.entrySet()) {

                    OrdersEntity ordersEntity = new OrdersEntity();
                    ordersEntity.setOrderDate(new Date());
                    ordersEntity.setEmail(email);
                    ordersEntity.setAddress(address);
                    ordersEntity.setFullname(fullname);
                    ordersEntity.setPhoneNumber(phoneNumber);
                    orderService.saveOrder(ordersEntity);

                    OrderDetailEntity orderDetail = new OrderDetailEntity();
                    orderDetail.setOrders(ordersEntity);
                    orderDetail.setProductOrderDetail(ode.getValue().getProductOrderDetail());
                    orderDetail.setId(ode.getValue().getId());
                    orderDetail.setColor(ode.getValue().getColor());
                    orderDetail.setQuantity(ode.getValue().getQuantity());
                    orderDetail.setSize(ode.getValue().getSize());
                    orderDetail.setUnitPrice(ode.getValue().getQuantity() * ode.getValue().getUnitPrice());
                    orderDetailService.saveOrderdetail(orderDetail);

                    int productId = ode.getValue().getProductOrderDetail().getId();
                    int colorId = colorService.findColor(ode.getValue().getColor());
                    int sizeId = sizeService.findSize(ode.getValue().getSize());
                    int detailId = detailRepository.ProductDetailId(productId, colorId, sizeId);

                    ProductDetailEntity detail = detailService.findProductById(detailId);
//                    int quantityOrder = ode.getValue().getQuantity();
//                    int quantityProduct = detail.getQuantity();
                    int quantity = detail.getQuantity() - ode.getValue().getQuantity();

                    detail.setQuantity(quantity);
                    detailService.saveProductdetail(detail);

                    PaymentEntity payment = new PaymentEntity();
                    payment.setAmount(ode.getValue().getQuantity() * ode.getValue().getUnitPrice());
                    payment.setOrders(ordersEntity);
                    payment.setPaymentDate(new Date());
                    payment.setCreditcard(creditCardEntity);
                    paymentService.savePayment(payment);

                }

                String text = "Information of products you order in my shop : " + "\n";

                for (Map.Entry<Integer, OrderDetailEntity> ode : OrderDetailEntity.entrySet()) {
                    double UnitPrice = ode.getValue().getQuantity() * ode.getValue().getUnitPrice();
                    text = text + "Product Name :" + ode.getValue().getProductOrderDetail().getName() + "\n" + "Price: " + ode.getValue().getUnitPrice() + "\n" + "Quantity :" + ode.getValue().getQuantity() + "\n" + "Unit Price: " + UnitPrice + "\n" + "------------------------" + "\n";
                }
                SimpleMailMessage message = new SimpleMailMessage();

                message.setTo(email);
                message.setText(text + "Total Pricce: " + totalPrice + "\n" + "Address :" + address + " ( " + fullname + "-" + phoneNumber + " )");
                message.setSubject("Order confirmation ! Thank you - T&T SHOP");
                mailSender.send(message);

                double balance = creditCardEntity.getBalance() - totalPrice;
                creditCardEntity.setBalance(balance);
                creditCardService.saveCreditCard(creditCardEntity);

                session.removeAttribute("myOrderDetailItems");
                session.removeAttribute("myOrderDetailTotal");
                return "payment";
            } else {
                model.addAttribute("message", "Please check your account balance");
                return "infoPage";
            }
        } else {
            model.addAttribute("message", "Please check your card information");
            return "infoPage";
        }
    }

    // Gửi mail cho User
    @RequestMapping(value = "/result-page", method = RequestMethod.POST)
    public String checkoutSuccess(HttpSession session,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "cardNumber", required = false) String cardNumber,
            @RequestParam(name = "expDate", required = false) String expireDate,
            @RequestParam(name = "cvcCode", required = false) String cvv,
            Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        HashMap<Integer, OrderDetailEntity> OrderDetailEntity = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("myOrderDetailItems");

        CreditCardEntity cardEntity = creditCardService.getCreditCard(cardNumber);

        if (principal instanceof UserDetails) {

            double totalPrice = 0;
            for (Map.Entry<Integer, OrderDetailEntity> ode : OrderDetailEntity.entrySet()) {

                totalPrice = totalPrice + ode.getValue().getQuantity() * ode.getValue().getUnitPrice();
            }
            if (cardEntity != null && cardEntity.getName().equals(name) && cardEntity.getCardNumber().equals(cardNumber)) {
                if (cardEntity.getBalance() >= totalPrice) {
                    username = ((UserDetails) principal).getUsername();
                    for (Map.Entry<Integer, OrderDetailEntity> ode : OrderDetailEntity.entrySet()) {
                        UserEntity ordersEntity1 = userService.findByUser(username);

                        OrdersEntity ordersEntity = new OrdersEntity();
                        ordersEntity.setOrderDate(new Date());
                        ordersEntity.setEmail(username);
                        ordersEntity.setFullname(ordersEntity1.getFullname());
                        ordersEntity.setAddress(ordersEntity1.getAddress());
                        ordersEntity.setPhoneNumber(ordersEntity1.getPhoneNumber());
                        ordersEntity.setGender(ordersEntity1.getGender());
                        ordersEntity.setUser(ordersEntity1);
                        orderService.saveOrder(ordersEntity);

                        OrderDetailEntity orderDetail = new OrderDetailEntity();
                        orderDetail.setOrders(ordersEntity);
                        orderDetail.setProductOrderDetail(ode.getValue().getProductOrderDetail());
                        orderDetail.setId(ode.getValue().getId());
                        orderDetail.setColor(ode.getValue().getColor());
                        orderDetail.setQuantity(ode.getValue().getQuantity());
                        orderDetail.setSize(ode.getValue().getSize());
                        orderDetail.setUnitPrice(ode.getValue().getQuantity() * ode.getValue().getUnitPrice());

                        orderDetailService.saveOrderdetail(orderDetail);

                        int productId = ode.getValue().getProductOrderDetail().getId();
                        int colorId = colorService.findColor(ode.getValue().getColor());
                        int sizeId = sizeService.findSize(ode.getValue().getSize());
                        int detailId = detailRepository.ProductDetailId(productId, colorId, sizeId);

                        ProductDetailEntity detail = detailService.findProductById(detailId);
                        int quantityOrder = ode.getValue().getQuantity();
                        int quantityProduct = detail.getQuantity();
                        int quantity = quantityProduct - quantityOrder;

                        detail.setQuantity(quantity);
                        detailService.saveProductdetail(detail);

                        PaymentEntity payment = new PaymentEntity();
                        payment.setAmount(ode.getValue().getQuantity() * ode.getValue().getUnitPrice());
                        payment.setOrders(ordersEntity);
                        payment.setPaymentDate(new Date());
                        payment.setCreditcard(cardEntity);
                        paymentService.savePayment(payment);
                    }
                    double balance = cardEntity.getBalance() - totalPrice;
                    cardEntity.setBalance(balance);
                    creditCardService.saveCreditCard(cardEntity);
                    UserEntity userEntity = userService.findByUser(username);
                    String recipientAddress = userEntity.getEmail();

                    String text = "Information of products you order in my shop : " + "\n";

                    for (Map.Entry<Integer, OrderDetailEntity> ode : OrderDetailEntity.entrySet()) {
                        double UnitPrice = ode.getValue().getQuantity() * ode.getValue().getUnitPrice();
                        text = text + "Product Name :" + ode.getValue().getProductOrderDetail().getName() + "\n" + "Price: " + ode.getValue().getUnitPrice() + "\n" + "Quantity :" + ode.getValue().getQuantity() + "\n" + "Unit Price: " + UnitPrice + "\n" + "------------------------" + "\n";
                    }
                    // Send Message!

                    SimpleMailMessage message = new SimpleMailMessage();

                    message.setTo(recipientAddress);
                    message.setText(text + "Total Pricce: " + totalPrice + "\n" + "Address :" + userEntity.getAddress() + " ( " + userEntity.getFullname() + "-" + userEntity.getPhoneNumber() + ")");
                    message.setSubject("Order confirmation ! Thank you - T&T SHOP");
                    mailSender.send(message);

                    session.removeAttribute("myOrderDetailItems");
                    session.removeAttribute("myOrderDetailTotal");

                    return "payment";
                }
                model.addAttribute("users", userService.findByUser(username));
                model.addAttribute("message", "Please check your account balance");
                return "userPage";
            } else {
                model.addAttribute("message", "Please check your card information");
                model.addAttribute("users", userService.findByUser(username));
                return "userPage";
            }
        }
        return "userPage";
    }

    //Show trang Order
    @RequestMapping("/order")
    public String showOrder(HttpSession session
    ) {
        HashMap<Integer, OrderDetailEntity> orderDetailItems = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("myOrderDetailItems");
        //  OrdersEntity order = (OrdersEntity) session.getAttribute("orders");
        return "order";
    }

    @RequestMapping("/testOrder")
    public String showtestOrder(HttpSession session
    ) {
        HashMap<Integer, OrderDetailEntity> orderDetailItems = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("myOrderDetailItems");
        //  OrdersEntity order = (OrdersEntity) session.getAttribute("orders");
        return "testOrder";
    }

    // Show trang Order khi bấm vào link 
    @RequestMapping("/confirm")
    public String showConfirm(HttpSession session
    ) {
        HashMap<Integer, OrderDetailEntity> orderDetailItems = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("myOrderDetailItems");

        return "checkout";
    }

    // Xóa Session khi xác nhận Order
    @RequestMapping("/removesession")
    public String removeSession(HttpSession session
    ) {
        HashMap<Integer, OrderDetailEntity> orderDetailItems = (HashMap<Integer, OrderDetailEntity>) session.getAttribute("myOrderDetailItems");
        session.removeAttribute("myOrderDetailItems");
        session.removeAttribute("myOrderDetailTotal");
        return "payment";

    }

    @RequestMapping(value = "/add-order/{id}", method = RequestMethod.POST)
    public String addOrder(Model model, HttpSession session,
            @RequestParam("productId") int productId,
            @RequestParam("colorId") int colorId,
            @RequestParam("sizeId") int sizeId,
            @RequestParam("quantity") int quantity
    ) {

        OrdersEntity order = (OrdersEntity) session.getAttribute("orders");
        if (order != null) {
            List<OrderDetailEntity> odes = order.getOrderDetail();
            if (odes != null && odes.size() > 0) {
                boolean isExist = false;

                ColorEntity colors = colorService.findColorById(colorId);   //timf color
                SizeEntity size1 = sizeService.findById(sizeId);
                ProductEntity product = productService.findProductById(productId);// tim product
                for (OrderDetailEntity ode : odes) {
                    if (ode.getProductOrderDetail().getId() == product.getId()
                            && ode.getColor().equalsIgnoreCase(colors.getColor())
                            && ode.getSize().equalsIgnoreCase(size1.getSize())) {          // && cungf size && cung color
                        ode.setQuantity(ode.getQuantity() + quantity); // tawng quantity len 1;
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    // theem sanr pham
                    OrderDetailEntity odetail = new OrderDetailEntity();
                    odetail.setOrders(order);
                    odetail.setColor(colors.getColor());
                    odetail.setSize(size1.getSize());
                    odetail.setQuantity(quantity);

                    // set thong tin cho orderDetail
                    odes.add(odetail);

                    order.setOrderDetail(odes);
                }
            } else {
                ColorEntity colorEntity = colorService.findColorById(colorId);   //timf color
                SizeEntity sizeEntity = sizeService.findById(sizeId);
                OrderDetailEntity odetail = new OrderDetailEntity();
                odetail.setOrders(order);
                odetail.setColor(colorEntity.getColor());
                odetail.setSize(sizeEntity.getSize());
                odetail.setQuantity(1);
                // set thong tin cho orderDetail
                odes = new ArrayList<>();
                odes.add(odetail);

                order.setOrderDetail(odes);
            }

        } else {
            ProductEntity product = productService.findProductById(productId);
            ColorEntity colorEntity = colorService.findColorById(colorId);   //timf color
            SizeEntity sizeEntity = sizeService.findById(sizeId);
            order = new OrdersEntity();
//            ProductEntity product = productService.findProductById(idProduct);
            order.setOrderDate(new Date());
            OrderDetailEntity odetail = new OrderDetailEntity();

            odetail.setOrders(order);
            odetail.setColor(colorEntity.getColor()); // timf color
            odetail.setSize(sizeEntity.getSize());  // tim size
            odetail.setQuantity(quantity);                  // tim product
            odetail.setProductOrderDetail(product);         // set thong tin cho orderDetail

            List<OrderDetailEntity> odes = new ArrayList<>();

            odes.add(odetail);

            order.setOrderDetail(odes);

        }

        session.setAttribute("orders", order);
        return "testOrder";
    }

}
