package com.dg.myblog.global.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @description: mybatis-plus自动填充字段
 * @author: lij
 * @create: 2020-03-03 16:14
 */
@Component
@Slf4j
public class MybatisFillHandler implements MetaObjectHandler {

    private static final Map<String,Supplier> DEFAULT_INSERT_MAP = Maps.newLinkedHashMap();
    private static final Map<String,Supplier> DEFAULT_UPDATE_MAP = Maps.newLinkedHashMap();

    static {
        DEFAULT_INSERT_MAP.put("createTime", new Supplier() {
            @Override
            public Object get() {
                return new Date();
            }
        });
        DEFAULT_INSERT_MAP.put("updateTime", new Supplier() {
            @Override
            public Object get() {
                return new Date();
            }
        });

        DEFAULT_UPDATE_MAP.put("updateTime", new Supplier() {
            @Override
            public Object get() {
                return new Date();
            }
        });
    }

    @Override
    public void insertFill(MetaObject metaObject) {

        for (Map.Entry<String, Supplier> entry : DEFAULT_INSERT_MAP.entrySet()) {
            String fieldName = entry.getKey();
            if(getFieldValByName(fieldName, metaObject) == null && metaObject.hasSetter(fieldName)){
                log.info("自动填充属性{}", fieldName);
                this.setInsertFieldValByName(fieldName, entry.getValue().get(), metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        for (Map.Entry<String, Supplier> entry : DEFAULT_UPDATE_MAP.entrySet()) {
            String fieldName = entry.getKey();
            if(getFieldValByName(fieldName, metaObject) == null && metaObject.hasSetter(fieldName)){
                log.info("自动更新属性{}", fieldName);
                this.setInsertFieldValByName(fieldName, entry.getValue().get(), metaObject);
            }
        }
    }
}
