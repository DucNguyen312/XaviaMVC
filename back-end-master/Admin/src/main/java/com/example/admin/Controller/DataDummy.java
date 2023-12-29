package com.example.admin.Controller;

import com.example.library.Model.Products;
import com.example.library.Model.Role;
import com.example.library.Model.Users;
import com.example.library.Repository.ProductRepository;
import com.example.library.Repository.RoleRepository;
import com.example.library.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

@Component
public class DataDummy implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {
        addUser();
        addRole();
        addUser_Role();
        addProduct();
        createP();
    }
    public void addUser(){
        if(!userRepository.existsByEmail("ADMIN@gmail.com")) {
            Users user = new Users();
            user.setFullName("ADMIN");
            user.setEmail("ADMIN@gmail.com");
            user.setPassword(passwordEncoder.encode("123"));
            userRepository.save(user);
        }
    }
    public void addRole(){
        if (!roleRepository.existsByName("ADMIN")) {
            Role role_admin = new Role();
            role_admin.setName("ADMIN");
            roleRepository.save(role_admin);
        }
        if (!roleRepository.existsByName("USER")){
            Role role_user = new Role();
            role_user.setName("USER");
            roleRepository.save(role_user);
        }
    }
    public void addUser_Role() {
        Users user = userRepository.findByEmail("ADMIN@gmail.com");
        Role role = roleRepository.findByName("ADMIN");
        if (user != null && role != null) {
            Collection<Role> roles = user.getRoles();
            if (roles == null) {
                roles = new HashSet<>();
            }
            if (!roles.contains(role)) {
                roles.add(role);
                user.setRoles(roles);
                userRepository.save(user);
            }
        }
    }
    public void addProduct(){
        if(!productRepository.existsByName("Hoa hồng"))
        {
            Products p1 = new Products();
            p1.setId(1);
            p1.setName("Hoa Hồng");
            p1.setPrice(1000);
            p1.setQuantity(20);
            p1.setSold(0);
            p1.setMaterial("Ngọc Trai");
            p1.setNote("");
            p1.setRewardPoints(100);
            p1.setImg("/image/spchinh/anh1.png");
            productRepository.save(p1);
        }
        if(!productRepository.existsByName("Hoa Của Đại Dương"))
        {
            Products p1 = new Products();
            p1.setId(2);
            p1.setName("Hoa Của Đại Dương");
            p1.setPrice(1000);
            p1.setQuantity(20);
            p1.setSold(0);
            p1.setMaterial("Ngọc Trai");
            p1.setNote("Hạt ngọc trai màu xanh dương, hạt thủy tinh trong suốt như nước biển, hạt pha lê màu xanh dương sâu");
            p1.setRewardPoints(100);
            p1.setImg("/image/spchinh/anh2.png");
            productRepository.save(p1);
        }
        if(!productRepository.existsByName("Tâm hồn của thiếu nữ"))
        {
            Products p1 = new Products();
            p1.setId(3);
            p1.setName("Tâm hồn của thiếu nữ");
            p1.setPrice(18900000);
            p1.setQuantity(20);
            p1.setSold(0);
            p1.setNote("Hạt ngọc trai trắng tinh khôi, hạt pha lê trong suốt, hạt đá màu pastel");
            p1.setRewardPoints(100);
            p1.setImg("/image/spchinh/anh6.png");
            productRepository.save(p1);
        }
        if(!productRepository.existsByName("Ý Nghĩa"))
        {
            Products p1 = new Products();
            p1.setId(5);
            p1.setName("Ý Nghĩa");
            p1.setPrice(15000000);
            p1.setQuantity(20);
            p1.setSold(0);
            p1.setNote("ngọc trai màu trắng, hạt pha lê màu đỏ rực, và hạt đá màu vàng");
            p1.setRewardPoints(100);
            p1.setImg("/image/spchinh/da1.png");
            productRepository.save(p1);
        }
        if(!productRepository.existsByName("Vận Mệnh"))
        {
            Products p1 = new Products();
            p1.setId(6);
            p1.setName("Vận Mệnh");
            p1.setPrice(19800000);
            p1.setQuantity(20);
            p1.setSold(0);
            p1.setNote("hạt ngọc trai, hạt đá màu xanh dương, và hạt thủy tinh màu vàng");
            p1.setRewardPoints(100);
            p1.setImg("/image/spchinh/da3.png");
            productRepository.save(p1);
        }
        if(!productRepository.existsByName("Đá quý"))
        {
            Products p1 = new Products();
            p1.setId(7);
            p1.setName("Đá quý");
            p1.setPrice(18900000);
            p1.setQuantity(20);
            p1.setSold(0);
            p1.setNote("Hạt ngọc trai tự nhiên, hạt đá quý tự nhiên như ruby, sapphire, hay emerald, hạt pha lê.");
            p1.setRewardPoints(100);
            p1.setImg("/image/spchinh/da4.png");
            productRepository.save(p1);
        }

    }

    private void createProduct(String name, int price , int quanity , int sold ,
                               String material , String note , int point , String img ) {
        if (!productRepository.existsByName(name)) {
            Products product = new Products();
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quanity);
            product.setSold(sold);
            product.setMaterial(material);
            product.setNote(note);
            product.setRewardPoints(point);
            product.setImg(img);
            productRepository.save(product);
        }
    }

    public void createP(){
        createProduct("Vĩnh Cửu", 999999999, 20, 0, "Vàng trắng 18k hoặc bạc sterling 925",
                "Sự lựa chọn này có thể đại diện cho sự bền vững và vĩnh cửu. Có thể đây là một chiếc vòng cổ, vòng đeo tay hoặc một chiếc nhẫn được thiết kế với họa tiết mang đậm tính biểu tượng, tượng trưng cho sự vững vàng, bền bỉ.",
                100, "/image/vinhcuu/anh4cccs.jpg");

        createProduct("Giọt Lệ Long Linh", 27999000, 20, 0, "Bạc sterling 925 và pha lê Swarovski.",
                "Sản phẩm này có thể là một dây chuyền hay đôi bông tai được chế tác với hình dáng và màu sắc của giọt lệ, tạo nên vẻ đẹp mềm mại, quyến rũ.",
                100, "/image/kimcuong/kimcuong1.jpg");

        createProduct("Hoa Tuyết Sơn", 12999000, 20, 0, "Vàng hồng 14k và kim cương nhỏ.",
                "Bộ trang sức hoa tinh tế với các cánh hoa mỏng, được trang trí bằng kim cương nhỏ tinh tế.",
                100, "/image/kimcuong/kimcuong2.jpg");

        createProduct("Bầu Trời Đầy Sao", 1999000, 20, 0, "Vàng và bạc đen.",
                "Đây có thể là một chiếc vòng cổ được đính đá quý hình sao lấp lánh, tạo nên bức tranh ấn tượng của bầu trời đêm đầy sao.",
                100, "/image/trantinh/product-6.jpg");

        createProduct("Cung Linh Vân Mộng", 899000, 20, 0, "Bạc sterling 925 và ngọc lục bảo.",
                "Sản phẩm này có thể là một chiếc dây chuyền có hình dáng của cung trăng hoặc cung trời, kết hợp với linh vân và các chi tiết mộng mơ.",
                100, "/image/trantinh/product-16.jpg");

        createProduct("Khúc Trần Tình", 1999000, 20, 0, "Vàng 18k và ngọc trai.",
                "Có thể là một bộ trang sức mang đặc điểm nghệ thuật, có thể là dây chuyền hoặc vòng cổ có thiết kế theo hình khúc trần tình, thể hiện sự tinh tế và nữ tính.",
                100, "/image/trantinh/product-18.jpg");

        createProduct("Tam Sinh Lưu Hoa", 8888888, 20, 0, "Bạc sterling 925 và màu sắc viên ngọc trai.",
                "Sản phẩm này có thể là một bức tranh tưởng tượng với tam bông hoa tượng trưng cho tam sinh lưu hương, có thể được tái tạo trên dây chuyền hoặc bông tai.",
                100, "/image/tamsinh/product-1.jpg");

        createProduct("Ma Đạo Tổ Sư", 9999000, 20, 0, "Vàng 14k và đá onyx.",
                "Có thể là một bộ trang sức độc đáo, có các yếu tố ma thuật hoặc tượng trưng liên quan đến ma đạo, có thể được thiết kế với các biểu tượng từ câu chuyện ma đạo tổ sư.",
                100, "/image/trantinh/product-10.jpg");

        createProduct("Lục Lạc Tú Cầu", 1999000, 20, 0, "Bạc sterling 925 và vàng 18k.",
                "Sản phẩm này có thể là một chiếc vòng cổ hoặc dây chuyền với các lục lạc và tú cầu, tạo nên vẻ đẹp truyền thống và may mắn.",
                100, "/image/tamsinh/product-16.jpg");
    }





}
