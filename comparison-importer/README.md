# Importer

This module exposes a Gradle task that applies the necessary Database migrations and imports FinanceAds credit card products.

This module depends on the Core module because it uses the `ImportProducts` use case along with the `Products` infrastructure.

## Trade-offs

- The Database URL for the connection is hardcoded, in a production environment should be passed through an ENV variable.
- Running migrations and importing credit card products is done through the same task for convenience.
- The contract test for the `FinanceAdsProductProvider` only tests that is able to parse correctly the URL.
