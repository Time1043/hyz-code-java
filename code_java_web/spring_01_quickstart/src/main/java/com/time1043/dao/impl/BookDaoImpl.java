package com.time1043.dao.impl;

import com.time1043.dao.BookDao;

public class BookDaoImpl implements BookDao {
    public BookDaoImpl() {  // 私有的也能调用到
        System.out.println("book dao constructor is running ...");
    }

    @Override
    public void save() {
        System.out.println("book dao save ...");
    }
}
