package comparison.importer.financeads;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public record ProductData(
        @JacksonXmlProperty(localName = "link") String link,//mandatory
        @JacksonXmlProperty(localName = "logo") String logo,//mandatory
        @JacksonXmlProperty(localName = "testsiegel") String sealTest,//optional
        @JacksonXmlProperty(localName = "testsiegel_url") String sealTestUrl,//optional
        @JacksonXmlProperty(localName = "bankid") int bankId,//mandatory
        @JacksonXmlProperty(localName = "productid") int productId,//mandatory
        @JacksonXmlProperty(localName = "anmerkungen") String extraInfo,//mandatory
        @JacksonXmlProperty(localName = "bank") String bankName,//mandatory
        @JacksonXmlProperty(localName = "produkt") String productTitle,//mandatory
        @JacksonXmlProperty(localName = "bewertung") Float rating,//optional
        @JacksonXmlProperty(localName = "bewertung_anzahl") Boolean hasRating,//optional
        @JacksonXmlProperty(localName = "incentive") Double apr,//mandatory
        @JacksonXmlProperty(localName = "gebuehren") Double yearlyFee,//mandatory
        @JacksonXmlProperty(localName = "kosten") Double yearlyEuroFee,//mandatory
        @JacksonXmlProperty(localName = "bonusprogram") Boolean offersBonusProgram,//optional
        @JacksonXmlProperty(localName = "insurances") Boolean offersInsurance,//optional
        @JacksonXmlProperty(localName = "benefits") Boolean offersBenefits,//optional
        @JacksonXmlProperty(localName = "services") Boolean offersExtraServices,//optional
        @JacksonXmlProperty(localName = "besonderheiten") String applicationRequirements,//optional
        @JacksonXmlProperty(localName = "gebuehrenmitaktion") String participationFee,//optional
        @JacksonXmlProperty(localName = "kostenmitaktion") String participationCost,//optional
        @JacksonXmlProperty(localName = "gebuehrenjahr1") Double firstYearFee,//mandatory
        @JacksonXmlProperty(localName = "dauerhaft") Double secondYearFee,//mandatory
        @JacksonXmlProperty(localName = "gc_atmfree_domestic") Double nationalAtmFee,//optional
        @JacksonXmlProperty(localName = "gc_atmfree_international") Double internationalAtmFee,//optional
        @JacksonXmlProperty(localName = "cc_atmfree_domestic") Double nationalAtmFreeFee,//optional
        @JacksonXmlProperty(localName = "cc_atmfree_international") Double internationalAtmFreeFee,//optional
        @JacksonXmlProperty(localName = "incentive_amount") Double aprAmount,//mandatory
        @JacksonXmlProperty(localName = "habenzins") Double interestRate,//optional
        @JacksonXmlProperty(localName = "sollzins") Double shallInterestRate,//optional
        @JacksonXmlProperty(localName = "cardtype") int cardTypeId,//optional
        @JacksonXmlProperty(localName = "cardtype_text") String cardType,//mandatory
        @JacksonXmlProperty(localName = "cc_atmfree_euro") Double freeCreditFeeEuroAtm,//optional
        @JacksonXmlProperty(localName = "kkoffer") Boolean offer//optional
) { }
