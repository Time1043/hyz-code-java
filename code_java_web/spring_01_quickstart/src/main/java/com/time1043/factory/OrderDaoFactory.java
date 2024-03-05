package com.time1043.factory;

import com.time1043.dao.OrderDao;
import com.time1043.dao.impl.OrderDaoImpl;

public class OrderDaoFactory {
    public static OrderDao getOrderDao() {
        return new OrderDaoImpl();
    }
}
