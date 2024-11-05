package cn.shh.test.mybatisplus.util;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class PageQuery {
    private Integer pageNo = 1;
    private Integer pageSize = 5;
    private String sortBy;
    private Boolean isAsc = true;
    public <T> Page<T> toMpPage(OrderItem... orderItems){
        Page<T> page = Page.of(pageNo, pageSize);
        if (null != sortBy && !("".equals(sortBy))){
            OrderItem orderItem = new OrderItem();
            orderItem.setColumn(sortBy);
            orderItem.setAsc(isAsc);
            page.addOrder(orderItem);
        } else {
            page.addOrder(orderItems);
        }
        return page;
    }

    public <T> Page<T> toMpPageSortByCreateTime(){
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("createTime");
        orderItem.setAsc(false);
        return toMpPage(orderItem);
    }

    public <T> Page<T> toMpPageSortByUpdateTime(){
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("updateTime");
        orderItem.setAsc(false);
        return toMpPage(orderItem);
    }

    public <T> Page<T> toMpPageDefault(String customeSortBy, Boolean customeIsAsc){
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn(customeSortBy);
        orderItem.setAsc(customeIsAsc);
        return toMpPage(orderItem);
    }
}
