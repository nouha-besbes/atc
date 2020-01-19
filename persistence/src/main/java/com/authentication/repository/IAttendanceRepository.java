package com.authentication.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.authentication.dto.UserAttendanceDto;
import com.authentication.model.Attendance;

public interface IAttendanceRepository extends IBaseRepository<Attendance, Long> {

    Page<Attendance> findAttendanceByUserId(Long userId, Pageable pageble);

    boolean existsByUserId(Long userId);

    @Query("From Attendance a where a.isDeleted=0 and (a.date BETWEEN :startDate AND :endDate) and a.user.id in :usersId")
    List<Attendance> findAttendanceByUserBetweenDates(@Param("usersId") Long[] usersId,
            @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("Select new com.authentication.dto.UserAttendanceDto (a.user, CAST(a.date AS date))From Attendance a where a.isDeleted=0 and a.user.id in :usersId and CAST(a.date AS date) in :dates group by a.user.id, CAST(a.date AS date) order by a.date ASC")
    List<UserAttendanceDto> findAttendanceGroupedByUserAndDate(@Param("usersId") Long[] userIds,
            @Param("dates") List<Date> dates);

    @Query("From Attendance a where a.isDeleted=0 and CAST(a.date AS date)= :date and a.user.id = :id order by a.date ASC")
    List<Attendance> findAttendanceByUserIdAndDate(@Param("id") Long id, @Param("date") Date date);

}
