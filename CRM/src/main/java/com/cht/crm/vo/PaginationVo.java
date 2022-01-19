package com.cht.crm.vo;

import java.util.List;

/**
 * @author Ryuzu
 * @date 2022/1/16 22:33
 */
public class PaginationVo<T> {
    private Integer total;
    private List<T> dataList;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
