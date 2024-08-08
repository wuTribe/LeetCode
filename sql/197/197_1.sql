select a.id
from Weather as a
         join Weather as b on a.recordDate = DATE_SUB(b.recordDate, INTERVAL -1 DAY)
where a.temperature > b.temperature