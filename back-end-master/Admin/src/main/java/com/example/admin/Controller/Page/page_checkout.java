package com.example.admin.Controller.Page;

import com.example.library.Service.Impl.CartService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.google.gson.JsonArray;


@Controller
@RequestMapping("/page/checkout")
public class page_checkout {
    @Autowired
    private CartService cartService;

    @PostMapping("")
    public String homeCheckOut(Model model,@RequestBody String dataToSend){
        String json = dataToSend;
        JsonArray data = JsonParser.parseString(json).getAsJsonArray();
        for (JsonElement element : data) {
            long id = element.getAsJsonObject().get("id").getAsLong();
            int sold = element.getAsJsonObject().get("sold").getAsInt();

            String result_checkout = cartService.Checkout(id , sold);
            if (result_checkout != null)
                return "FAIL";
        }

        int items = cartService.countItemsInCart();
        model.addAttribute("items" , items);
        return "/user/checkout";
    }
    @GetMapping("")
    public String home(Model model){
        //Thong tin gio hang
        int items = cartService.countItemsInCart();
        model.addAttribute("items" , items);
        return "/user/checkout";
    }
}
