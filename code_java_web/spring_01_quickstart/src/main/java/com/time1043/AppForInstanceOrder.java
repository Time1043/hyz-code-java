package com.time1043;

import com.time1043.dao.OrderDao;
import com.time1043.factory.OrderDaoFactory;

public class AppForInstanceOrder {
    public static void main(String[] args) {
        // 通过静态工厂创建对象
        OrderDao orderDao = OrderDaoFactory.getOrderDao();
        orderDao.save();
    }
}
