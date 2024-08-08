# 窗口函数

SELECT
    X.player_id,
    X.event_date AS first_login
FROM
    (
        SELECT
            A.player_id,
            A.event_date,
            RANK() OVER (
                PARTITION BY
                    A.player_id
                ORDER BY
                    A.event_date
                ) AS rnk
        FROM
            Activity A
    ) X
WHERE
    X.rnk = 1;
什么意思