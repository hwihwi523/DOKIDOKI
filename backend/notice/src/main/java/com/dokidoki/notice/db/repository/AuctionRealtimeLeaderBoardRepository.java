package com.dokidoki.notice.db.repository;

import com.dokidoki.notice.api.response.LeaderBoardMemberInfo;
import com.dokidoki.notice.common.codes.RealTimeConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.protocol.ScoredEntry;
import org.springframework.stereotype.Component;

import java.util.Collection;

@RequiredArgsConstructor
@Component
@Slf4j
public class AuctionRealtimeLeaderBoardRepository {

    private final RedissonClient redisson;
    private final String keyPrefix = RealTimeConstants.leaderboardKey;
    private int limit = RealTimeConstants.leaderboardLimit;

    /**
     * auctionId로 Redis 에 leaderboard 를 저장할 키를 생성하는 메서드
     * @param auctionId 경매 ID
     * @return Redis 에 leaderboard 를 저장할 키
     */
    private String getKey(long auctionId) {
        StringBuilder sb = new StringBuilder();
        sb.append(keyPrefix).append(":").append(auctionId);
        return sb.toString();
    }

    public LeaderBoardMemberInfo getWinner(long auctionId) {
        RScoredSortedSet<LeaderBoardMemberInfo> scoredSortedSet = redisson.getScoredSortedSet(getKey(auctionId));
        System.out.println(scoredSortedSet.pollFirst());
        System.out.println(scoredSortedSet.pollLast());
        return scoredSortedSet.pollFirst();
    }

    public Collection<ScoredEntry<LeaderBoardMemberInfo>> getAll(long auctionId) {
        RScoredSortedSet<LeaderBoardMemberInfo> scoredSortedSet = redisson.getScoredSortedSet(getKey(auctionId));
        return scoredSortedSet.entryRangeReversed(0, -1);
    }

    public void save(int bidPrice, LeaderBoardMemberInfo memberInfo, long auctionId) {
        RScoredSortedSet<LeaderBoardMemberInfo> scoredSortedSet = redisson.getScoredSortedSet(getKey(auctionId));
        scoredSortedSet.add(bidPrice, memberInfo);
    }

    public void removeOutOfRange(long auctionId) {
        RScoredSortedSet<LeaderBoardMemberInfo> scoredSortedSet = redisson.getScoredSortedSet(getKey(auctionId));
        scoredSortedSet.removeRangeByRank(-limit -1, -limit -1);
    }

    public void deleteAll(long auctionId) {
        RScoredSortedSet<LeaderBoardMemberInfo> scoredSortedSet = redisson.getScoredSortedSet(getKey(auctionId));
        scoredSortedSet.delete();
    }


}
