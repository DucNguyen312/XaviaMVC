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
import java.text.DecimalFormat;
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

    @Override
    public String sendOrder(String subject ,Order order) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(order.getCustomer().getEmail());
            mimeMessageHelper.setSubject(subject);

            String body = "";
            String status = String.valueOf(order.getOrderStatus());
            if (status == "SUBMIT")
                body = "Chào bạn "+order.getCustomer().getFullName() + ",\n"
                       +"Chúng tôi xin chân thành cảm ơn quý khác đã đặt hàng tại cửa hàng của chúng tôi. Đơn hàng của bạn đã được xác nhận và đang được xử lý. Dưới đây là thông tin đơn hàng:\n"
                       +"Mã đơn hàng: " + order.getId() + "\n"
                       +"Số lượng sản phẩm: " + order.getTotal_quantity() + "\n"
                       +"Tổng giá trị đơn hàng: " + formatCurrency(order.getTotal_price()) + "\n"
                       +"Đã trả trước: " + order.getPrePay() + "% vào thời gian:" + order.getOrderDate() + "\n"
                       +"Phương thức thanh toán: " + order.getPaymentMethod() + "\n"
                       +"Số tiền còn lại: " + formatCurrency(order.getTotal_price() - (order.getTotal_price() * ((double) order.getPrePay() /100))) + "\n"
                       +"Thời gian giao hàng dự kiến: " + dateTimeService.CurrentDateTime(true) + "\n"
                       +"Địa chỉ nhận hàng: " + order.getCustomer().getAddress() + "\n\n"
                       +"Nếu có bất kỳ câu hỏi hoặc yêu cầu nào, vui lòng liên hệ chúng tôi tại xaviatgu@gmail.com hoặc số điện thoại 0562879967.\n"
                       +"Cảm ơn Quý khách đã lựa chọn chúng tôi. Chúng tôi rất mong được phục vụ Quý khách một lần nữa trong tương lai.\n"
                       +"Trân trọng,\n"
                       +"Xavia";
            else
                body = "Chào bạn "+order.getCustomer().getFullName() + ",\n"
                        +"Chúng tôi rất tiếc thông báo rằng đơn hàng của bạn đã được hủy. Dưới đây là thông tin đơn hàng:\n"
                        +"Mã đơn hàng: " + order.getId() + "\n"
                        +"Đã trả trước: " + order.getPrePay() + "% vào thời gian:" + order.getOrderDate() + "\n"
                        +"Phương thức thanh toán: " + order.getPaymentMethod() + "\n"
                        +"Chúng tôi sẽ hoàn tiền lại cho bạn sau khi bạn nhận được thông báo này!" + "\n"
                        +"Nếu có bất kỳ câu hỏi hoặc yêu cầu nào, vui lòng liên hệ chúng tôi tại xaviatgu@gmail.com hoặc số điện thoại 0562879967.\n"
                        +"Cảm ơn Quý khách đã lựa chọn chúng tôi. Chúng tôi rất mong được phục vụ Quý khách một lần nữa trong tương lai.\n"
                        +"Trân trọng,\n"
                        +"Xavia";


            mimeMessageHelper.setText(body);

            javaMailSender.send(mimeMessage);

            return "mail send";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String formatCurrency(double number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###đ");
        return decimalFormat.format(number);
    }
}
