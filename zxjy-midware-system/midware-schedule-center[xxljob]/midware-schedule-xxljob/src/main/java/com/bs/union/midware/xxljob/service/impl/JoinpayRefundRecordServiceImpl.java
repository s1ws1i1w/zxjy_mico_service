package com.bs.union.midware.xxljob.service.impl;

import com.bs.union.midware.xxljob.mapper.JoinpayRefundRecordMapper;
import com.bs.union.midware.xxljob.service.JoinpayRefundRecordService;
import org.springframework.stereotype.Service;

/**
 * 汇聚订单详情
 *
 * @author guochao
 * @date 2020/9/22 10:45
 */
@Service
public class JoinpayRefundRecordServiceImpl implements JoinpayRefundRecordService {
    private final JoinpayRefundRecordMapper joinpayRefundRecordMapper;

    public JoinpayRefundRecordServiceImpl(JoinpayRefundRecordMapper joinpayRefundRecordMapper) {
        this.joinpayRefundRecordMapper = joinpayRefundRecordMapper;
    }
}
