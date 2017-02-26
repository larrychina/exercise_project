delimiter $$

CREATE PROCEDURE `seckill`.`seckill_execute`(
  IN v_seckill_id bigint , IN v_user_phone bigint ,in v_kill_time timestamp ,
  out r_result int)
begin
  declare result_count int default 0;
  start transaction;
  insert ignore into success_skilled(seckill_id,user_phone,state,create_time)
   values(v_seckill_id,v_user_phone,0,v_kill_time);
   select row_count() into result_count;
   if (result_count = 0) then
      -- 更新失败
      rollback;
      set r_result = -1 ;
    elseif(result_count < 0) then
      rollback ;
      set r_result = -2 ;
    else
      update seckill set number = number - 1
      where seckill_id = v_seckill_id
      and v_kill_time >= start_time
      and v_kill_time <= end_time
      and number > 0;
      select row_count() into result_count ;
      if (result_count = 0) then
      -- 更新失败
      rollback;
      set r_result = 0 ;
    elseif(result_count < 0) then
      rollback ;
      set r_result = -2 ;
    else
      commit;
      set r_result = 1 ;
    end if;
  end if ;
end ;
$$
delimiter ;

set @r_result = -3 ;

call seckill.seckill_execution(1010,18509871234,now(),@r_result) ;

select @r_result;


