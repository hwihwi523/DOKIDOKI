package com.dokidoki.auction.dto.request;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LeaderboardRequest {
    private Long auction_id;
    private List<LeaderboardHistoryRequest> histories;
}