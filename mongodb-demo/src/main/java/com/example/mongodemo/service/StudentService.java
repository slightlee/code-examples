package com.example.mongodemo.service;

import com.example.mongodemo.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    /**
     * @function 插入单条数据
     * @param student
     */
    void insert(Student student);

    /**
     * @function 批量插入数据
     */
    void insertByList(List<Student> studentList);

    /**
     * @function 无条件计数
     * @return
     */
    int count();

    /**
     * @function 无条件查询所有的数据
     * @return
     */
    List<Student> queryAll();

    /**
     * @function 无条件查询分页
     * @return
     */
    Page<Student> queryByPage(int page, int rows);

    /**
     * 根据实体属性进行精确查询
     */
    List<Student> queryByName(String name);
    List<Student> queryByAge(Integer age);
    List<Student> queryByGender(Boolean gender);

    /**
     * 根据实体属性进行模糊查询
     */
    List<Student> queryByNameLike(String name);

    /**
     * 根据实体属性进行【模糊+分页】查询
     */
    Page<Student> queryByNameLikeAndPage(int page,int rows,String name);
}
