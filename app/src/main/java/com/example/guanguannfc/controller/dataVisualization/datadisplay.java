package com.example.guanguannfc.controller.dataVisualization;

import android.renderscript.Sampler;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class datadisplay {
    public Object[] function(String username,String timestart,String timeend,String activityType,String showType){
        String[][] arr = new String[2][];
        String dataAnalysis="";
        String echarttype = showType;
        String url="" ;
        try {
            String acttype = activityType;//活动类型
            String acttime = "";//活动时长
            Connection conn = DriverManager.getConnection("url", acttype, acttime);//建立connection
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);// 更改jdbc事务的默认提交方式

            String sql = "";//查询语句
            ResultSet rs = stmt.executeQuery(sql);//得到结果集
            conn.commit();//事务提交
            conn.setAutoCommit(true);// 更改jdbc事务的默认提交方式
            List<String> list = new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
            List<String> list2 = new ArrayList<String>();
            while (rs.next()) {//如果有数据，取第一列添加入list
                list.add(rs.getString(1));
                list2.add(rs.getString(2));
            }
            if (list != null && list.size() > 0) {//如果list中存入了数据，转化为数组
                //String[][] arr = new String[2][a];//创建一个和list长度一样的数组
                for (int i = 0; i < list.size(); i++) {
                    arr[1][i] = list.get(i);//数组赋值了。
                    arr[2][i] = list2.get(i);
                }

            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        switch (echarttype) {
            case "列表":
                url = "";
                break;
            case "条状图":
                url = "javascript:createChart('bar',[89,78,77]);";
                break;
            case "饼状图":
                url = "javascript:createChart('pie',[89,78,77]);";
                break;
            default:
                break;
        }
        Object[] objs = new Object[]{arr,url,dataAnalysis};
        return objs;
    }
    }
