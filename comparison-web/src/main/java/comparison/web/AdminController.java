package comparison.web;

import comparison.core.application.*;
import comparison.core.domain.Products;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class AdminController {
    private Products products;

    @GetMapping("/admin/")
    public String productList(Model model) {
        model.addAttribute("products", new ListProducts(products).execute());

        return "admin/list";
    }

    @GetMapping("/admin/{id}/")
    public String edit(@PathVariable("id") String id, Model model) {
        model.addAttribute("product", new GetProduct(products).execute(id));

        return "admin/edit";
    }

    @PostMapping("/admin/{id}/")
    public String edit(EditProductInfo.EditProductInfoRequest request, Model model) {
        model.addAttribute("product", new EditProductInfo(products).execute(request));

        return "redirect:/admin/" + request.id() + "/";
    }

    @PostMapping("/admin/{id}/flag/")
    public String addFlag(AddProductFlag.AddProductFlagRequest request) {
        new AddProductFlag(products).execute(request);

        return "redirect:/admin/" + request.productId() + "/";
    }

    @PostMapping("/admin/{id}/flag/{index}/")
    public String removeFlag(RemoveProductFlag.RemoveProductFlagRequest request) {
        new RemoveProductFlag(products).execute(request);

        return "redirect:/admin/" + request.productId() + "/";
    }
}
