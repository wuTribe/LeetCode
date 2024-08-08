# Write your MySQL query statement below
select a.class
from (select count(1) as num, class
      from Courses
      group by class
      having num >= 5) as a