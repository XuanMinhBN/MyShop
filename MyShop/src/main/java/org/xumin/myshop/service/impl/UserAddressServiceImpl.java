package org.xumin.myshop.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.xumin.myshop.entity.UserAddress;
import org.xumin.myshop.service.UserAddressService;

import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Override
    public UserAddress getUserAddressDefault(List<UserAddress> userAddressList) {
        for (UserAddress userAddress : userAddressList) {
            if(userAddress.isDefaultAddress()){
                return userAddress;
            }
        }
        return null;
    }
}
