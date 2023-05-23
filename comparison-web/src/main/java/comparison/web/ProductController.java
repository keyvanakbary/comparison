package comparison.web;

import comparison.core.application.ListProducts;
import comparison.core.domain.Products;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ProductController {
    private Products products;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("products", new ListProducts(products).execute());

        return "products";
    }
}
