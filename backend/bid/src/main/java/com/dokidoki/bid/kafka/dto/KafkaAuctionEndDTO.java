package com.dokidoki.bid.kafka.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KafkaAuctionEndDTO {
    // TODO 모든 정보를 다 담는게 맞는지, 아님 auctionRealtime 정보만 넘기고 저기서 처리하게 하는게 맞는건지..
    private long auctionId;
    private long sellerId;
    private long buyerId;
    private int finalPrice;

    public KafkaAuctionEndDTO() {}

    @Builder
    public KafkaAuctionEndDTO(long auctionId, long sellerId, long buyerId, int finalPrice) {
        this.auctionId = auctionId;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.finalPrice = finalPrice;
    }
}
