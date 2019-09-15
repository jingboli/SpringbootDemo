package cc.lijingbo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import cc.lijingbo.domain.User;


@Mapper
public interface UserMapper {

    @Select("select * from tb_user")
    List<User> getAllUsers();

    @Delete("DELETE FROM tb_user WHERE id =#{id}")
    void delete(Integer id);

    @Insert("INSERT INTO tb_user VALUES (null, #{username}, #{address})")
    void insertUser(String username,String address);
}
