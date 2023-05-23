# Core

This module follows a similar structure to the one promoted by Domain-Driven Design. There are the following packages:
- Application: Where use cases belong. This is the main entry point for interacting with the Domain, they expose the behaviour of the system and translate the outside-world into the Domain, and vice versa.
- Domain: Where the Domain model resides. It includes the Domain entities, Repositories and Value objects of the system.
- Infrastructure: Where the low-level details of high-level Domain concepts (such as the Repository) get implemented. Mainly JDBC and in-memory implementations for the `Products` repository.

## Trade-offs

- Domain entities are not being translated to DTOs in the Application layer. This means that the View is coupled to the Domain and the Domain won't be able to change that easily (will break the UI).
- Some use cases such as `GetProduct` or `ListProducts` are very thin. The idea was to reduce the coupling of View with the Domain model. There are no tests for these thin use cases.
- Explicitly chose not to use an ORM to have freedom to evolve the Domain model. There is some boilerplate and duplication in the infrastructure layer.
- To avoid having very long names, the `CreditCard` prefix has been avoided. For example, instead of `CreditCardProduct` we have just `Product`.
- Entities deal store primitives, not proper Value objects with behaviour.
- Slow tests have been moved to the boundaries. There are very few tests that ensure the contract with external services (database, API) works, and everything else has been tested with in-memory implementations.
- Decided to include a database migration framework such as Flyway.