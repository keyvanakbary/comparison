# Web

This module includes the Web (HTTP) Delivery mechanism for the application, effectively exposing the Core use cases.

This module depends on the Core module due to the intensive use of use cases and infrastructure implementations.

Available paths:
- `/`: home page
- `/list`: product list
- `/admin/`: list of products
- `/admin/{id}`: edit product page

## Trade-offs

- Pages don't have layout inheritance, there is some duplication (peg: Header and Footer)
- Admin is not protected (basic auth)
- There are no UI tests