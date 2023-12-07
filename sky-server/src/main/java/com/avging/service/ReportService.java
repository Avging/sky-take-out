package com.avging.service;

import com.avging.vo.TurnoverReportVO;
import com.avging.vo.UserReportVO;

import java.time.LocalDate;

public interface ReportService {

    /**
     * 统计指定时间区间内的营业额数据
     * @param begin LocalDate
     * @param end LocalDate
     * @return TurnoverReportVO
     */
    TurnoverReportVO getTurnoverStatistics(LocalDate begin,LocalDate end);

    /**
     * 统计指定时间区间内的用户数据
     * @param begin LocalDate
     * @param end LocalDate
     * @return TurnoverReportVO
     */
    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);
}
