package com.dg.myblog.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dg.myblog.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentMapper extends BaseMapper<Comment> {

}