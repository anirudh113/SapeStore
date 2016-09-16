package com.sapestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.OrderTrackDao;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.service.OrderTrackService;

@Service("orderTrackService")
@Transactional
public class OrderTrackServiceImpl implements OrderTrackService {

  @Autowired
  OrderTrackDao ordertrackdao;

  public OrderTrackDao getOrdertrackdao() {
    return ordertrackdao;
  }

  public void setOrdertrackdao(OrderTrackDao ordertrackdao) {
    this.ordertrackdao = ordertrackdao;
  }

  @Override
  public OrderInfo orderTracking(int orderId, String userId) {
    // TODO Auto-generated method stub
    OrderInfo trackstatus = ordertrackdao.getTrackingStatus(orderId, userId);
    return trackstatus;
  }

  @Override
  public OrderItemInfo orderRentPurchase(int orderId) {
    // TODO Auto-generated method stub
    OrderItemInfo trackrentpurchase = ordertrackdao.getRentPurchase(orderId);
    return trackrentpurchase;
  }

}
