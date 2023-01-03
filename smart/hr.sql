--부서별 사원수 조회
-- 개발부 5
-- 영업부 10

select department_id, nvl(department_name,'소속없음') department_name, count(employee_id) count
from employees e left outer join departments d using(department_id)
group by department_id, department_name
order by 1
;

--년도별 채용인원수 조회
-- 2020년 5
-- 2021년 10
select to_char(hire_date,'yyyy')||'년' unit , count(employee_id) count
from employees
group by to_char(hire_date,'yyyy')
order by 1;

select to_char(hire_date,'mm')||'월' unit , count(employee_id) count
from employees
group by to_char(hire_date,'mm')
order by 1;


--상위3위부서의 년도별 채용인원수 조회

--상위3위부서
select rank, department_id, '(TOP'||rank||')' || department_name department_name
from  (select department_id, dense_rank() over(order by count(*) desc) rank
        from employees
        group by department_id) e left outer join departments d using(department_id)
where rank <= 3 ;

--상위3위부서  년도별 채용인원수
select  department_name, to_char(hire_date,'yyyy') unit, count(*) count
from employees e inner join (select rank, department_id, '(TOP'||rank||')' || department_name department_name
                             from  (select department_id, dense_rank() over(order by count(*) desc) rank
                                    from employees
                                    group by department_id) e left outer join departments d using(department_id)
                             where rank <= 3) r using(department_id)
group by  to_char(hire_date,'yyyy'), department_name
;

                    2002  2003  ..... 2010
(TOP3)IT             10    0           10
(TOP3)Purchasing     0     5           1
(TOP1)Shipping       0     10           0
(TOP2)Sales          1     0           3
;

select department_name,
       sum(decode(unit, 2001, count, 0)) y2001,
       sum(decode(unit, 2002, count, 0)) y2002,
       sum(decode(unit, 2003, count, 0)) y2003,
       sum(decode(unit, 2004, count, 0)) y2004,
       sum(decode(unit, 2005, count, 0)) y2005,
       sum(decode(unit, 2006, count, 0)) y2006,
       sum(decode(unit, 2007, count, 0)) y2007,
       sum(decode(unit, 2008, count, 0)) y2008,
       sum(decode(unit, 2009, count, 0)) y2009,
       sum(decode(unit, 2010, count, 0)) y2010
from (select  department_name, to_char(hire_date,'yyyy') unit, count(*) count
    from employees e inner join (select rank, department_id, '(TOP'||rank||')' || department_name department_name
                                 from  (select department_id, dense_rank() over(order by count(*) desc) rank
                                        from employees
                                        group by department_id) e left outer join departments d using(department_id)
                                 where rank <= 3) r using(department_id)
    group by  to_char(hire_date,'yyyy'), department_name) e
group by department_name
order by 1
;

select * 
from ( select  department_name, to_char(hire_date,'yyyy') unit, count(*) count
        from employees e inner join (select rank, department_id, '(TOP'||rank||')' || department_name department_name
                                     from  (select department_id, dense_rank() over(order by count(*) desc) rank
                                            from employees
                                            group by department_id) e left outer join departments d using(department_id)
                                     where rank <= 3) r using(department_id)
        group by  to_char(hire_date,'yyyy'), department_name ) e
pivot( sum(count) for UNIT in ( '2001' y2001, '2002' y2002, '2003' y2003, '2004' y2004, '2005' y2005
                            , '2006' y2006, '2007' y2007, '2008' y2008, '2009' y2009, '2010' y2010)  )        
order by 1
;    

select * 
from ( select  department_name, to_char(hire_date,'yyyy') unit
        from employees e inner join (select rank, department_id, '(TOP'||rank||')' || department_name department_name
                                     from  (select department_id, dense_rank() over(order by count(*) desc) rank
                                            from employees
                                            group by department_id) e left outer join departments d using(department_id)
                                     where rank <= 3) r using(department_id)
         ) 
pivot( count(unit) for UNIT in ( '2001' y2001, '2002' y2002, '2003' y2003, '2004' y2004, '2005' y2005
                            , '2006' y2006, '2007' y2007, '2008' y2008, '2009' y2009, '2010' y2010)  )        
order by 1
;    

select * 
from ( select  department_name, to_char(hire_date,'mm') unit
        from employees e inner join (select rank, department_id, '(TOP'||rank||')' || department_name department_name
                                     from  (select department_id, dense_rank() over(order by count(*) desc) rank
                                            from employees
                                            group by department_id) e left outer join departments d using(department_id)
                                     where rank <= 3) r using(department_id)
         ) 
pivot( count(unit) for UNIT in ( '01' m01, '02' m02, '03' m03, '04' m04, '05' m05
                            , '06' m06, '07' m07, '08' m08, '09' m09, '10' m10, '11' m11, '12' m12)  )        
order by 1
;    

