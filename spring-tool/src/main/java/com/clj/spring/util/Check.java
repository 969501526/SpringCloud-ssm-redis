package com.clj.spring.util;

import com.clj.spring.model.TbUser;

public class Check {

    public static boolean userCheck(TbUser tbUser){
        if(tbUser.getUsername()==null||tbUser.getUsername().equals("")||tbUser.getPassword()==null
                ||tbUser.getPassword().equals("") || tbUser.getEmail()==null ||tbUser.getEmail().equals("")
                ||tbUser.getPhone()==null || tbUser.getPhone().equals("")){
            return false;
        }else {
            return true;
        }
    }
}
