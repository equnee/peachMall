package com.peach_mall.yyq439.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.peach_mall.yyq439.app.MyApplication;
import com.peach_mall.yyq439.home.bean.GoodsBean;
import com.peach_mall.yyq439.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

public class CartStorage {

    private static final String JSON_CART = "json_cart";
    private static CartStorage instance;
    private final Context mContext;
    private SparseArray<GoodsBean> sparseArray;

    private CartStorage(Context context) {
        this.mContext = context;
        sparseArray = new SparseArray<>(100);   // 读取之前存储数据
        listToSparseArray();
    }

    // 读取本地数据到SparseArray
    private void listToSparseArray() {
        List<GoodsBean> goodsBeanList = getAllData();
        // 转换List数据
        for (int i = 0; i < goodsBeanList.size(); i++) {
            GoodsBean goodsBean = goodsBeanList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        }
    }

    // 得到本地所有数据
    public List<GoodsBean> getAllData() {
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        System.out.println("===="+mContext+"===");
        String json = CacheUtils.getString(mContext, JSON_CART);// 读取
        if(!TextUtils.isEmpty(json)){ // 使用Gson转换列表
            goodsBeanList = new Gson().fromJson(json, new TypeToken<List<GoodsBean>>(){}.getType());
        }
        return goodsBeanList;
    }

    // 得到购物车实例
    public static CartStorage getInstance(){
        if(instance == null) {
            instance = new CartStorage(MyApplication.getContext());
        }
        return instance;
    }

    // 添加数据
    public void addData(GoodsBean goodsBean) {
        GoodsBean tempData = sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if(tempData != null) {
            tempData.setNumber(tempData.getNumber() + 1);
        }else {
            tempData = goodsBean;
            tempData.setNumber(1);
        }
        // 同步内存
        sparseArray.put(Integer.parseInt(tempData.getProduct_id()), tempData);
        saveLocal(); // 同步到本地
    }

    private void saveLocal() {
        List<GoodsBean> goodsBeanList = sparseToList(); // SparseArray转换成List
        String json = new Gson().toJson(goodsBeanList); // Gson：列表转String
        CacheUtils.saveString(mContext, JSON_CART, json);// 保存
    }

    private List<GoodsBean> sparseToList() {
        List<GoodsBean> goodsBeanList = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            GoodsBean goodsBean = sparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }

    // 删除数据
    public void deleteData(GoodsBean goodsBean) {
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
        saveLocal();    // 保存内存
    }

    // 更新数据
    public void updateData(GoodsBean goodsBean) {
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        saveLocal();
    }
}
