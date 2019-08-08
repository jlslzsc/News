package com.DBoperation;

import com.database.DBConnection;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Statement;

public class AddClickNew {
    public AddClickNew(int d){


     try {


        Statement stm = DBConnection.getCon().createStatement();


        String sql = "update  news set n_clicknum=n_clicknum +1 where n_id="+d;

        stm.executeUpdate(sql);


        stm.close();
    }

                catch (
    SQLException e1){
        e1.printStackTrace();
    }
    }
}
