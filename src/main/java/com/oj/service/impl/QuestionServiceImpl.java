package com.oj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oj.model.entity.Question;
import com.oj.service.QuestionService;
import com.oj.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
* @author 29769
* @description 针对表【question(题目)】的数据库操作Service实现
* @createDate 2023-10-14 20:50:12
*/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




