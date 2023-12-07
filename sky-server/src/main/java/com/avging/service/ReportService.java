package com.avging.service;

import com.avging.vo.OrderReportVO;
import com.avging.vo.SalesTop10ReportVO;
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
     * @return UserReportVO
     */
    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);

    /**
     * 统计指定时间区间内的订单数据
     * @param begin LocalDate
     * @param end LocalDate
     * @return OrderReportVO
     */
    OrderReportVO getOrdersStatistics(LocalDate begin, LocalDate end);

    SalesTop10ReportVO getSalesTop10(LocalDate begin, LocalDate end);
}
