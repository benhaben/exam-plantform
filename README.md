# 虚拟考试系统的分层架构实现

### 运行

```bash
./gradlew run
```

浏览器中打开 http://localhost:8080/papers 即可



### 说明

现有 Paper 和 Course 两个模块


### 现有 Paper 操作

| 功能          | Method | URI               |
| ------------- | ------ | ----------------- |
| 获取所有paper | GET    | /papers           |
| 组卷          | POST   | /papers           |
| 重新组卷      | PUT    | /papers/:paper_id |

### 现有 Course 操作
| 功能          | Method | URI               |
| ------------- | ------ | ----------------- |
| 获取所有课程信息 | GET    | /courses               |
| 获取课程详情    | GET    | /courses/:course_id    |
| 创建课程       | POST   | /courses               |
| 发布课程       | PATCH  | /courses/:course_id/publishing    |
| 更新课程       | PUT    | /courses/:course_id    |