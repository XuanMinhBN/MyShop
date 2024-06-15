package org.xumin.myshop.service.impl;

import org.springframework.stereotype.Service;
import org.xumin.myshop.entity.UserAddress;
import org.xumin.myshop.entity.UserDetail;
import org.xumin.myshop.service.UserDetailService;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailService {
    @Override
    public UserDetail getUserDetailInfo(UserDetail userDetailList) {
        return userDetailList;
    }
}
