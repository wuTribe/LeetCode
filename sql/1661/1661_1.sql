# Write your MySQL query statement below
select a.machine_id, round((sum(a.timestamp) - sum(b.timestamp)) / count(1), 3) as processing_time
from Activity as a
         left join  Activity as b
                    on a.machine_id = b.machine_id and a.process_id = b.process_id
                        and b.activity_type = 'start'
where a.activity_type = 'end'
group by a.machine_id