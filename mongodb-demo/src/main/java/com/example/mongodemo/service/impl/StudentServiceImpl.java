package com.example.mongodemo.service.impl;

import com.example.mongodemo.entity.Student;
import com.example.mongodemo.repository.StudentRepository;
import com.example.mongodemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * @function 插入单条数据
     * @param student
     */
    @Override
    public void insert(Student student) {
        studentRepository.insert(student);
    }

    /**
     * @function 批量插入数据
     */
    @Override
    public void insertByList(List<Student> studentList) {
        studentRepository.insert(studentList);
    }

    /**
     * @function 无条件计数
     * @return
     */
    @Override
    public int count() {
        return (int)studentRepository.count();
    }

    /**
     * @function 无条件查询所有的数据
     * @return
     */
    @Override
    public List<Student> queryAll() {
        return studentRepository.findAll();
    }

    /**
     * @function 无条件查询分页
     * @return
     */
    @Override
    public Page<Student> queryByPage(int page, int rows) {
//        PageRequest pageRequest = new PageRequest(page,rows);
        PageRequest pageRequest = PageRequest.of(page, rows);
        return studentRepository.findAll(pageRequest);
    }

    /**
     * 根据实体属性进行精确查询
     */
    @Override
    public List<Student> queryByName(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    public List<Student> queryByAge(Integer age) {
        return studentRepository.findByAge(age);
    }

    @Override
    public List<Student> queryByGender(Boolean gender) {
        return studentRepository.findByGender(gender);
    }

    /**
     * 根据实体属性进行模糊查询
     */
    @Override
    public List<Student> queryByNameLike(String name) {
        return studentRepository.findByNameLike(name);
    }

    /**
     * 根据实体属性进行模糊查询并分页
     * @return
     */
    @Override
    public Page<Student> queryByNameLikeAndPage(int page, int rows, String name) {
//        PageRequest pageRequest = new PageRequest(page,rows);
        PageRequest pageRequest = PageRequest.of(page, rows);
        return studentRepository.findByNameLike(name,pageRequest);
    }
}
