

create table storm by orc


create table d_match
(
radiant_win boolean,
duration INT,
pre_game_duration INT,
start_time INT,
match_id BIGINT,
match_seq_num BIGINT,
tower_status_radiant INT,
tower_status_dire INT,
barracks_status_radiant INT,
barracks_status_dire INT,
cluster INT,
first_blood_time INT,
lobby_type INT,
human_players INT,
leagueid INT,
positive_votes INT,
negative_votes INT,
game_mode INT,
flags INT,
engine INT,
radiant_score INT,
dire_score INT
) partitioned by(dt string)
stored as textfile;

create table d_ability
(
match_id BIGINT,
account_id BIGINT,
ability INT,
time INT,
level INT
) partitioned by(dt string)
stored as textfile;

create table d_player
(
match_id BIGINT,
account_id BIGINT,
player_slot INT,
hero_id INT,
item_0 INT,
item_1 INT,
item_2 INT,
item_3 INT,
item_4 INT,
item_5 INT,
backpack_0 INT,
backpack_1 INT,
backpack_2 INT,
kills INT,
deaths INT,
assists INT,
leaver_status INT,
last_hits INT,
denies INT,
gold_per_min INT,
xp_per_min INT,
level INT,
hero_damage INT,
tower_damage INT,
hero_healing INT,
gold INT,
gold_spent INT,
scaled_hero_damage INT,
scaled_tower_damage INT,
scaled_hero_healing INT
) partitioned by(dt string)
stored as textfile;

create table d_error_record
(
errorMsg string,
record string
) partitioned by(dt string)
stored as textfile;

/usr/hdp/2.4.2.0-258/kafka/bin/kafka-topics.sh --create --zookeeper `hostname`:2181 --replication-factor 1 --partition 6 --topic d-topic

kafka-run-class.sh kafka.tools.ConsumerOffsetChecker --group test-consumer-group  --topic d-topic  --zookeeper node1:2181,node2:2181,node3:2181/kafka
/usr/hdp/2.4.2.0-258/kafka/bin/kafka-consumer-groups.sh --bootstrap-server 10.10.10.21:6667,10.10.10.22:6667 --describe --group test-consumer-group
/usr/hdp/2.4.2.0-258/kafka/bin/kafka-topics.sh --zookeeper node1:2181 --topic "d-topic" --describe

/usr/hdp/2.4.2.0-258/kafka/bin/kafka-consumer-offset-checker.sh --zookeeper node1:2181 --topic d-topic  --group test-consumer-group
alter table d_match add partition(dt='20171221');
alter table d_player add partition(dt='20171221');
alter table d_ability add partition(dt='20171221');
alter table d_error_record add partition(dt='20171221');



select a.*
(select distinct match_id,account_id,ability from d_ability d where d.dt in('20171221','20170921','20170922','20170923')) a,d_match b where b.dt in('20171221','20170921','20170922','20170923') and a.match_id = b.match_id and a.radiant_win = true;