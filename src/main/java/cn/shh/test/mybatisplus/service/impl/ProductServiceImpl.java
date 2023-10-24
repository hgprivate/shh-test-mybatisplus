package cn.shh.test.mybatisplus.service.impl;

import cn.shh.test.mybatisplus.mapper.ProductMapper;
import cn.shh.test.mybatisplus.pojo.Product;
import cn.shh.test.mybatisplus.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}