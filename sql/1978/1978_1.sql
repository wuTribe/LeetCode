# Write your MySQL query statement below
select a.employee_id
from Employees as a
         left join Employees as b on a.manager_id = b.employee_id
where a.manager_id is not null
  and b.employee_id is null
  and a.salary < 30000
order by a.employee_id