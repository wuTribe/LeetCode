# Write your MySQL query statement below
select a.customer_number
from (select count(1) as s, customer_number
      from Orders
      group by customer_number
      order by s desc
      limit 1) as a;