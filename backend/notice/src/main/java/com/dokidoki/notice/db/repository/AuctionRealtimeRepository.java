package com.dokidoki.notice.db.repository;

import com.dokidoki.notice.db.entity.AuctionRealtime;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public interface AuctionRealtimeRepository {

    Optional<AuctionRealtime> findById(long auctionId);

    void save(AuctionRealtime auctionRealtime);

    void save(AuctionRealtime auctionRealtime, long ttl, TimeUnit timeUnit);

    boolean deleteAll();

}
