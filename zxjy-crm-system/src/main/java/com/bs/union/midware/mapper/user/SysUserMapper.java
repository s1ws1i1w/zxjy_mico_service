package com.bs.union.midware.mapper.user;


        import com.bs.union.midware.model.entity.SysRoleEntity;
        import com.bs.union.midware.model.entity.SysUserEntity;
        import org.apache.ibatis.annotations.Mapper;
        import org.apache.ibatis.annotations.Select;

        import java.util.List;


/**
 * sys_user 持久化层
 *
 * @author : zhuangruitao  1335877357@qq.com
 * @date : 2021/01/01 18:00
 */
@Mapper
public interface SysUserMapper{
    @Select("SELECT * FROM sys_user where user_name=#{userName} ")
    SysUserEntity loadUserByUserName(String userName);

    @Select("SELECT * FROM sys_role r,sys_user sr where r.id=sr.role_id  and r.id=#{id} ")
    List<SysRoleEntity> getRoleById(Long  id);
}
