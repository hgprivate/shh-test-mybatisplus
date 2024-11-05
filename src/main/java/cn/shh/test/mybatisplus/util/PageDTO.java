package cn.shh.test.mybatisplus.util;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageDTO<T> {
    private Long total;
    private Long pages;
    private List<T> list;

    public static <PO, VO> PageDTO<VO> of(Page<PO> page, Class<VO> clazz){
        PageDTO<VO> pageDTO = new PageDTO();
        pageDTO.setTotal(page.getTotal());
        pageDTO.setPages(page.getPages());
        pageDTO.setList(BeanUtil.copyToList(page.getRecords(), clazz));
        return pageDTO;
    }

    public static <PO, VO> PageDTO<VO> of2(Page<PO> page, Function<PO, VO> convertor){
        PageDTO<VO> pageDTO = new PageDTO();
        pageDTO.setTotal(page.getTotal());
        pageDTO.setPages(page.getPages());
        pageDTO.setList(page.getRecords().stream().map(convertor).collect(Collectors.toList()));
        return pageDTO;
    }
}
