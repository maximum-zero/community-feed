package org.maximum0.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.maximum0.admin.ui.dto.users.GetDailyRegisterUserResponseDto;
import org.maximum0.admin.ui.query.UserStateQueryRepository;
import org.maximum0.common.TimeCalculator;
import org.maximum0.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserStateQueryRepositoryImpl implements UserStateQueryRepository {
    private final JPAQueryFactory queryFactory;
    private static final QUserEntity userEntity = QUserEntity.userEntity;

    @Override
    public List<GetDailyRegisterUserResponseDto> getDailyRegisterUserState(int beforeDays) {
        return queryFactory
                .select(
                        Projections.fields(
                                GetDailyRegisterUserResponseDto.class,
                                userEntity.registerAt.as("date"),
                                userEntity.count().as("count")
                        )
                )
                .from(userEntity)
                .where(userEntity.registerAt.after(TimeCalculator.getDateDaysAgo(beforeDays)))
                .groupBy(userEntity.registerAt)
                .orderBy(userEntity.registerAt.asc())
                .fetch();
    }
}
