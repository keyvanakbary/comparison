package comparison.core.domain;

public record Info (
        String link,//mandatory
        String logo,//mandatory
        Integer bankId,//mandatory
        Integer productId,//mandatory
        String extraInfo,//mandatory
        String bankName,//mandatory
        String productTitle,//mandatory
        Float rating,//optional
        Boolean hasRating,//optional
        Double apr,//mandatory
        Double yearlyFee,//mandatory
        Double yearlyEuroFee,//mandatory
        Boolean offersBonusProgram,//optional
        Boolean offersInsurance,//optional
        Boolean offersBenefits,//optional
        Boolean offersExtraServices,//optional
        String applicationRequirements,//optional
        String participationFee,//optional
        String participationCost,//optional
        Double firstYearFee,//mandatory
        Double secondYearFee,//mandatory
        Double nationalAtmFee,//optional
        Double internationalAtmFee,//optional
        Double nationalAtmFreeFee,//optional
        Double internationalAtmFreeFee,//optional
        Double aprAmount,//mandatory
        Double interestRate,//optional
        CardType cardType,//mandatory
        Double freeCreditFeeEuroAtm//optional
){
    public Info merge(InfoEdit edit) {
        if (edit == null) {
            return this;
        }

        return new Info(
                isEmpty(edit.link()) ? link : edit.link(),
                isEmpty(edit.logo()) ? logo : edit.logo(),
                bankId,
                productId,
                isEmpty(edit.extraInfo()) ? extraInfo : edit.extraInfo(),
                isEmpty(edit.bankName()) ? bankName : edit.bankName(),
                isEmpty(edit.productTitle()) ? productTitle : edit.productTitle(),
                rating,
                hasRating,
                apr,
                yearlyFee,
                yearlyEuroFee,
                offersBonusProgram,
                offersInsurance,
                offersBenefits,
                offersExtraServices,
                isEmpty(edit.applicationRequirements()) ? applicationRequirements : edit.applicationRequirements(),
                isEmpty(edit.participationFee()) ? participationFee : edit.participationFee(),
                isEmpty(edit.participationCost()) ? participationCost : edit.participationCost(),
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

    private static boolean isEmpty(String text) {
        return text == null || text.equals("");
    }
}
