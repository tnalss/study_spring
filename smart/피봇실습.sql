select department_name, 
       sum(decode(unit, 2001, count, 0) ) y2001,
       sum(decode(unit, 2002, count, 0) ) y2002,
       sum(decode(unit, 2003, count, 0) ) y2003,
       sum(decode(unit, 2004, count, 0) ) y2004,
       sum(decode(unit, 2005, count, 0) ) y2005,
       sum(decode(unit, 2010, count, 0) ) y2010
from   ( select  department_name, to_char(hire_date,'yyyy') unit, count(*) count
    from employees e inner join (select rank, department_id, '(TOP'||rank||')' || department_name department_name
                                 from  (select department_id, dense_rank() over(order by count(*) desc) rank
                                        from employees
                                        group by department_id) e left outer join departments d using(department_id)
                                 where rank <= 3) r using(department_id)
    group by  to_char(hire_date,'yyyy'), department_name ) e
group by department_name 
order by 1
;

select 1 "01", 2 "02", 3 "03", 4 "04", 5 "05"
from dual;

--여러열 을 여러행으로: unpivot
select * 
from (select 1 "01", 2 "02", 3 "03", 4 "04", 5 "05"
      from dual) 
unpivot( count for month in ( "01", "02", "03", "04", "05"   ) );      

--여러행 을 여러열으로: pivot
select * 
from ( select * 
        from (select 1 "01", 2 "02", 3 "03", 4 "04", 5 "05"
              from dual) 
        unpivot( count for month in ( "01", "02", "03", "04", "05"   ) ) )
pivot( sum(count) for month in ( '01', '02', '03', '04', '05' ) )
;






