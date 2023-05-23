package comparison.core.application;

import comparison.core.domain.InfoEdit;
import comparison.core.domain.Product;
import comparison.core.domain.Products;

public record EditProductInfo(Products products) {
    public Product execute(EditProductInfoRequest request) {
        Product product = products.byId(request.id()).orElseThrow(RuntimeException::new);

        product.editInfo(new InfoEdit(
                request.link(),
                request.logo(),
                request.extraInfo(),
                request.bankName(),
                request.productTitle(),
                request.applicationRequirements(),
                request.participationFee(),
                request.participationCost()
        ));

        return products.add(product);
    }

    public record EditProductInfoRequest(
            String id,
            String link,
            String logo,
            String extraInfo,
            String bankName,
            String productTitle,
            String applicationRequirements,
            String participationFee,
            String participationCost
    ){ }
}
