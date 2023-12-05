package com.example.project.Controllers;

import com.example.project.Models.Orders;
import com.example.project.Models.User;
import com.example.project.Repository.FoodRepository;
import com.example.project.Repository.UserRepository;
import com.example.project.Services.OrderService;
import com.example.project.config.vnpayConfig;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/VNPay")
public class VNPayController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private UserRepository userRepo;

    @ModelAttribute
    public void commonUser(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            User user = userRepo.findByEmail(email);
            m.addAttribute("user", user);
        }
    }
    @GetMapping("/checkout/{ResId}")
    public ModelAndView checkout(@PathVariable int ResId, HttpSession session) throws UnsupportedEncodingException {
        ModelAndView modelAndView;

        String email = (String) session.getAttribute("email");

        if (email == null) {
            modelAndView = new ModelAndView("redirect:/signin");
        } else {
            modelAndView = new ModelAndView("Orders/checkout");
            User user = userRepo.findByEmail(email);
            modelAndView.addObject("fullName", user.getName());
            modelAndView.addObject("address", user.getAddress());
            modelAndView.addObject("emailAddress", user.getEmail());
            modelAndView.addObject("phoneNumber", user.getPhone());
            Double totalPrice = this.orderService.totalPriceCart(email, ResId);
            modelAndView.addObject("TotalPrice", totalPrice);
            Optional<Orders> o = this.orderService.findOrdering(email, ResId);
            modelAndView.addObject("OrderID", o.get().getOrder_id());

            String vnp_TxnRef = vnpayConfig.getRandomNumber(8);
            String vnp_TmnCode = vnpayConfig.vnp_TmnCode;

            long amount = Double.valueOf(totalPrice.doubleValue()*1000*100).longValue();
            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_Command", vnpayConfig.vnp_Command);
            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
            vnp_Params.put("vnp_CurrCode", "VND");
            vnp_Params.put("vnp_IpAddr", "127.0.0.1");
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_OrderInfo", o.get().getOrder_id() + "_" + ResId);
            vnp_Params.put("vnp_OrderType", "other");
            vnp_Params.put("vnp_ReturnUrl", vnpayConfig.vnp_ReturnUrl);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_Version", vnpayConfig.vnp_Version);

            vnp_Params.put("vnp_BankCode", "NCB");

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while(itr.hasNext()){
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if((fieldValue != null) && (fieldValue).length() > 0){
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));

                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if(itr.hasNext()){
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = vnpayConfig.hmacSHA512(vnpayConfig.secretKey, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = vnpayConfig.vnp_PayUrl + "?" + queryUrl;
            modelAndView.addObject("paymentLink", paymentUrl);

        }
        return modelAndView;
    }
    @GetMapping("payment_infor")
    public ModelAndView transaction(
            HttpSession session,
            @RequestParam(value = "vnp_Amount") String amount,
            @RequestParam(value = "vnp_OrderInfo") String orderid_Resid,
            @RequestParam(value = "vnp_ResponseCode") String responseCode,
            @RequestParam(value = "vnp_PayDate") String vnp_PayDate
    ){
        ModelAndView modelAndView;

        String email = (String) session.getAttribute("email");

        if (email == null) {
            modelAndView = new ModelAndView("redirect:/signin");
        } else {
            if(responseCode.equals("00")) {
                modelAndView = new ModelAndView("redirect:/Restaurant/showAll");
                String orderid[] = orderid_Resid.split("_");
                String inputString = vnp_PayDate;
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
                try {
                    Date date = inputFormat.parse(inputString);
                    String formattedString = outputFormat.format(date);
                    String res[] = formattedString.split("/");
                    this.orderService.updateOrderShipping(orderid[0], Double.valueOf(amount)/100/1000, res[0] + "/" + res[1] + "/" + res[2] + " - " + res[3] + ":" + res[4] +":" + res[5]);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                String Resid[] = orderid_Resid.split("_");
                modelAndView = new ModelAndView("redirect:/VNPay/checkout/" + Resid[1]);
            }
        }
        return modelAndView;
    }
}
