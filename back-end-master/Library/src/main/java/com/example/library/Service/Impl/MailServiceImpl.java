package com.example.library.Service.Impl;

import com.example.library.DTO.CustomerDTO.SendMailDTO;
import com.example.library.DTO.ProductsDTO.Product_Items;
import com.example.library.Model.Customer;
import com.example.library.Model.Order;
import com.example.library.Service.DateTimeService;
import com.example.library.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private DateTimeService dateTimeService;


    @Override
    public String sendMail(Customer customer , Order order , List<Product_Items> product_items) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(customer.getEmail());
            mimeMessageHelper.setSubject("Xác nhận đơn hàng từ Xavia");

            String products = "";
            int total = 0;

            for(Product_Items product : product_items){
                long id = product.getId();
                String product_name = product.getName();
                int product_quantity = product.getSold();
                double product_price = product.getPrice();
                total += (int) (product.getSold() * product.getPrice());
                products += "ID: "+ id + " - " + product_name + "- Giá: " + product_price + "đ, Số lượng: " + product_quantity +"\n";

            }

            String body = "Chào bạn " + customer.getFullName() +",\n"
                        + "Chúng tôi xin chân thành cảm ơn bạn đã mua sắm tại Xavia. Đơn hàng của bạn đã đang chờ duyệt.\n"
                        + "Dưới đây là thông tin chi tiết của đơn hàng: \n"
                        + "Mã Đơn Hàng: " + order.getId()+ " \n"
                        + "Ngày đặt hàng: " + dateTimeService.DateFormatTime(order.getOrderDate().toString()) +"\n\n"
                        + "Sản phẩm đã đặt: \n"
                        + products +"\n"
                        +"Tổng cộng: " + total + "\n"
                        +"Trạng thái đơn hàng: Đang chờ duyệt"+ "\n"
                        +"Địa chỉ giao hàng: " + customer.getAddress() + "\n\n"
                        +"Chúng tôi sẽ thông báo cho bạn khi đơn hàng của bạn đã được gửi đi. Nếu có bất kỳ câu hỏi hoặc yêu cầu nào, vui lòng liên hệ chúng tôi tại xaviatgu@gmail.com hoặc số điện thoại 0562879967.\n\n"
                        +"Chúng tôi rất hân hạnh được phục vụ bạn và mong bạn có trải nghiệm mua sắm thú vị tại Xavia.\n"
                        +"Trân trọng,\n"
                        +"Xavia";


            mimeMessageHelper.setText(body);

            javaMailSender.send(mimeMessage);

            return "mail send";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String send(SendMailDTO sendMailDTO) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(sendMailDTO.getToEmail());
            mimeMessageHelper.setSubject(sendMailDTO.getSubject());
            mimeMessageHelper.setText(sendMailDTO.getBody());

            javaMailSender.send(mimeMessage);

            return "mail send";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
