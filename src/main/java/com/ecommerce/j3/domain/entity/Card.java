package com.ecommerce.j3.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
    private String purchaseCorp, purchaseCorpCode;
    private String issuerCorp, issuerCorpCode;
    private String bin, card_type, installMonth, approvedId, cardMid;
    private String interestFreeInstall, cardItemCode;
}
