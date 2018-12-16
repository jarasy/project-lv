package com.jarasy.lv.common.helper;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
public class PageHelper<T> {

    private int startIndex;

    private int pageIndex;

    private int pageSize;

    private int totalCount;

    private List<T> dataList;

    public PageHelper(){

    }

    public PageHelper(int pageIndex, int pageSize){

        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.startIndex = (pageIndex -1)* pageSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
