# Write your MySQL query statement below
select customer_id, count(1) as count_no_trans
from Visits as a left join Transactions as b
                           on a.visit_id = b.visit_id
where transaction_id is null
group by customer_id