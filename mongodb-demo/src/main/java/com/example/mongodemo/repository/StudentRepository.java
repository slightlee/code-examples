package com.example.mongodemo.repository;

import com.example.mongodemo.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository  extends MongoRepository<Student,String> {

    /**
     * 根据实体属性进行精确查询：find + By + 属性名（首字母大写）
     */
    List<Student> findByName(String name);

    List<Student> findByAge(Integer age);

    List<Student> findByGender(Boolean gender);

    /**
     * 根据实体属性进行模糊查询：find + By + 属性名（首字母大写） + Like
     */
    List<Student> findByNameLike(String name);

    /**
     * 大于
     */
    List<Student> findByAgeGreaterThan(Integer age);

    /**
     * 小于
     */
    List<Student> findByAgeLessThan(Integer age);

    /**
     * 在...之间
     */
    List<Student> findByAgeBetween(Integer from, Integer to);

    /**
     *  IsNotNull, NotNull（是否非空）
     *
     */

    /**
     * 根据实体属性进行模糊查询+分页：find + By + 属性名（首字母大写） + Like
     */
    Page<Student> findByNameLike(String name, PageRequest pageRequest);

    /**
     * 查询所有数据，同时指定返回的键。
     * <p>
     * 不能使用仓库中自带的findAll（）方法了。我们可以查询所有id不为空的数据，同时指定返回的键。
     * 当我们需要根据一个key且该key不为空进行查询，方法名的定义规则为：find + By + 属性名（首字母大写） + NotNull。
     * <p>
     * 指定返回的键：也就是说当我们进行带分页的模糊查询时，不想返回数据库中的所有字段，只是返回一部分字段,若想指定返回的键，我们需要在
     * PersonRepository中添加方法，同时使用注解@Query。其中value是查询的条件，？0这个是占位符，对应着方法中参数中的第一个参数，如果对应的是
     * 第二个参数则为？1。fields是我们指定的返回字段，其中id是自动返回的，不用我们指定
     */
    @Query(value = "{'_id':{'$ne':null}}", fields = "{'name':1}")
    Page<Student> findByIdNotNull(Pageable pageable);
}