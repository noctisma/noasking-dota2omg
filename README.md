# Dota2 OMG 网站

> 目前项目还处于开发阶段，预计春节前上线

## 系统介绍

Dota2 OMG 网站我是平时没事的时候写的一个分析Dota2 OMG 战绩然后给大家推荐技能选择的网站，访问网址：dota2.noasking.com

## 功能介绍

- OMG技能排行
- OMG英雄排行
- OMG战绩查询（取消因为数据量比较大，四千万*10，我的服务器不能支持大量的实时查询）
- OMG技能选择页面识别并推荐技能（后续添加一些算法，考虑增加Mahout机器学习）

## 环境依赖

- Hive:1.2.1x
- Kafka:0.9.0.x
- HDFS:2.7.1.x
- MySQL:5.7.x
- Prosto:0.177(取消，因为我的服务器在运行Prosto任务时的效率很低，没有起到实时查询工具应该有的效果)

## 技术选型

- Spring Boot:1.5.8
- Angular:4.3.1
- Java:1.8

## 系统组成

- noasking-dota2omg-clawer:steam接口爬虫
- noasking-dota2omg-consumer:战绩数据消费者，解析入Hive
- noasking-dota2omg-opencv：包含dota2 omg技能选择页面的截取以及技能的识别
- noasking-dota2omg-ui:UI前端静态页面
- noasking-dota2omg-web:web后端项目

## 时间轴

