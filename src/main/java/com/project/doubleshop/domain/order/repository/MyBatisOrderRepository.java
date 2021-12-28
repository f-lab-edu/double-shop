package com.project.doubleshop.domain.order.repository;

import com.project.doubleshop.domain.common.Status;
import com.project.doubleshop.domain.order.entity.Order;
import com.project.doubleshop.domain.order.mapper.OrderMapper;
import com.project.doubleshop.web.common.StatusRequest;
import com.project.doubleshop.web.config.support.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisOrderRepository implements OrderRepository {

    private final OrderMapper mapper;

    @Override
    public boolean save(Order order) {
        int affectedRowCnt;

        if (order.getId() != null) {
            affectedRowCnt = mapper.updateOrder(order);
        } else {
            affectedRowCnt = mapper.insertOrder(order);
        }

        return affectedRowCnt != 0;
    }

    @Override
    public Order findById(Long id) {
        return mapper.selectByOrderId(id);
    }

    @Override
    public List<Order> findAll(Pageable pageable) {
        return mapper.selectAllOrders(pageable);
    }

    @Override
    public int updateStatus(StatusRequest requestDTO) {
        return mapper.updateOrderStatus(requestDTO);
    }

    @Override
    public int deleteData(Status status) {
        return mapper.deleteOrder(status);
    }

}
