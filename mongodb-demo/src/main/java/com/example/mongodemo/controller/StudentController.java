package com.example.mongodemo.controller;

import com.example.mongodemo.entity.Student;
import com.example.mongodemo.repository.StudentRepository;
import com.example.mongodemo.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Api(value = "", tags = "【student测试】")
@RestController
@RequestMapping(value = "student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     *  (自选)自带 封装 CRUD
     */
    @Autowired
    private MongoOperations mongo;

    @Autowired
    MongoTemplate mongoTemplate;



    /**
     * 插入一条数据
     * @return
     */
    @ApiOperation( value = "【student测试】-保存 插入一条数据", notes = "", position = 1 )
    @GetMapping(value = "insert")
    public int insert(){
        Student student = new Student("张三",25,true);
        studentService.insert(student);
        return 1;
    }

    /**
     * 插入一条数据
     * @return
     */
    @ApiOperation( value = "【student测试】-插入一条数据", notes = "", position = 1 )
    @GetMapping(value = "insertByList")
    public int insertByList(){
        List<Student> students = new ArrayList<>(10);

        Student student = null;
        for (int i = 0;i<10;i++){
            student = new Student("李斯"+i,i+15,i%2==0?true:false);
            students.add(student);
        }
        studentService.insertByList(students);
        return 1;
    }

    /**
     * 无条件的数据统计
     */
    @ApiOperation( value = "【student测试】-无条件的数据统计", notes = "", position = 1 )
    @GetMapping(value="count")
    public int count(){
        return studentService.count();
    }

    /**
     * @function 无条件查询所有的数据
     * @return
     */
    @ApiOperation( value = "【student测试】-无条件查询所有的数据", notes = "", position = 1 )
    @GetMapping(value="queryAll")
    public List<Student> queryAll(){
        return studentService.queryAll();
    }

    /**
     * @function 无条件查询分页 ？
     * @return
     */
    @ApiOperation( value = "【student测试】-无条件查询分页", notes = "", position = 1 )
    @GetMapping(value="queryByPage")
    public Page<Student> queryByPage(Integer page, Integer rows){
        if(page == null || page <0){
            page = 0;
        }
        if(rows == null || rows < 0){
            rows = 5;
        }
        return studentService.queryByPage(page,rows);
    }

    /**
     * 根据实体属性进行精确查询
     */
    @ApiOperation( value = "【student测试】-根据实体属性进行精确查询", notes = "", position = 1 )
    @GetMapping(value = "queryByName")
    public List<Student> queryByName(String name){
        return studentService.queryByName(name);
    }

    @ApiOperation( value = "【student测试】-条件查询", notes = "", position = 1 )
    @GetMapping(value = "queryByAge")
    public List<Student> queryByAge(Integer age){
        return studentService.queryByAge(age);
    }

    @ApiOperation( value = "【student测试】-多条件查询", notes = "", position = 1 )
    @GetMapping(value = "queryByGender")
    public List<Student> queryByGender(Boolean gender){
        return studentService.queryByGender(gender);
    }

    /**
     * 根据实体属性进行模糊查询
     */
    @ApiOperation( value = "【student测试】-根据实体属性进行模糊查询", notes = "", position = 1 )
    @GetMapping(value = "queryByNameLike")
    public List<Student> queryByNameLike(String name){
        return studentService.queryByNameLike(name);
    }

    @ApiOperation( value = "【student测试】-大于", notes = "", position = 1 )
    @GetMapping(value = "findByAgeGreaterThan")
    public List<Student> findByAgeGreaterThan(Integer age){
        return studentService.findByAgeGreaterThan(age);
    }

    /**
     * 模糊查询加分页 ？
     */
    @ApiOperation( value = "【student测试】-模糊查询加分页", notes = "", position = 1 )
    @GetMapping(value = "queryByNameLikeAndPage")
    public Page<Student> queryByNameLikeAndPage(Integer page, Integer rows, String name){
        if(page == null || page <0){
            page = 0;
        }
        if(rows == null || rows < 0){
            rows = 5;
        }
        return studentService.queryByNameLikeAndPage(page,rows,name);
    }


    @ApiOperation( value = "【student测试】-使用Criteria封装查询条件", notes = "", position = 1 )
    @GetMapping(value = "getListWithCriteria")
    public Page<Student> getListWithCriteria(Integer page, Integer rows,String name,Integer age) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(page, rows, sort);

        Query query = new Query();

        //动态拼接查询条件
        if (!StringUtils.isEmpty(name)){
            Pattern pattern = Pattern.compile("^.*" + name + ".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("name").regex(pattern));
        }

        if (age != null){
            query.addCriteria(Criteria.where("age").is(age));
        }


        //计算总数
        long total = mongoTemplate.count(query, Student.class);

        //查询结果集
        List<Student> studentList = mongoTemplate.find(query.with(pageable), Student.class);
        Page<Student> studentPage = new PageImpl(studentList, pageable, total);
        return studentPage;
    }

}