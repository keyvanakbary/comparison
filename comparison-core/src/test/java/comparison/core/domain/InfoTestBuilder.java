package comparison.core.domain;

import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
public class InfoTestBuilder {
    private static int SEC_NUM = 0;

    @Setter private String link = "";
    @Setter private String logo = "";
    @Setter private int bankId = SEC_NUM++;
    @Setter private int productId = SEC_NUM++;
    @Setter private String extraInfo = "";
    @Setter private String bankName = "";
    @Setter private String productTitle = "";
    @Setter private Float rating = 0.0F;
    @Setter private Boolean hasRating = true;
    @Setter private Double apr = 0.0;
    @Setter private Double yearlyFee = 0.0;
    @Setter private Double yearlyEuroFee = 0.0;
    @Setter private Boolean offersBonusProgram = true;
    @Setter private Boolean offersInsurance = true;
    @Setter private Boolean offersBenefits = true;
    @Setter private Boolean offersExtraServices = true;
    @Setter private String applicationRequirements = "";
    @Setter private String participationFee = "";
    @Setter private String participationCost = "";
    @Setter private Double firstYearFee = 0.0;
    @Setter private Double secondYearFee = 0.0;
    @Setter private Double nationalAtmFee = 0.0;
    @Setter private Double internationalAtmFee = 0.0;
    @Setter private Double nationalAtmFreeFee = 0.0;
    @Setter private Double internationalAtmFreeFee = 0.0;
    @Setter private Double aprAmount = 0.0;
    @Setter private Double interestRate = 0.0;
    @Setter private CardType cardType = CardType.DEBIT;
    @Setter private Double freeCreditFeeEuroAtm = 0.0;

    public static InfoTestBuilder anInfo() {
        return new InfoTestBuilder();
    }

    public Info build() {
        return new Info(
                link,
                logo,
                bankId,
                productId,
                extraInfo,
                bankName,
                productTitle,
                rating,
                hasRating,
                apr,
                yearlyFee,
                yearlyEuroFee,
                offersBonusProgram,
                offersInsurance,
                offersBenefits,
                offersExtraServices,
                applicationRequirements,
                participationFee,
                participationCost,
                firstYearFee,
                secondYearFee,
                nationalAtmFee,
                internationalAtmFee,
                nationalAtmFreeFee,
                internationalAtmFreeFee,
                aprAmount,
                interestRate,
                cardType,
                freeCreditFeeEuroAtm
        );
    }
}
