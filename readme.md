# 基于Vue+Springboot日志监控组件[2023]
## TODO
- [ ] 实现日志在线存储，支持MongoDB and Elastic Search
  - [x] 策略定义
  - [ ] 策略存储信息配置校验
- [ ] 支持日志快速搜索，方便定位问题
- [ ] 支持Error日志异常告警，支持通知企业微信，钉钉等


## 使用方法
### 存储策略配置
已支持策略：

| 配置项 | 策略              |
| ------ | ----------------- |
| es     | ElasticSearch存储 |
| mongo  | MongoDB存储       |

配置语法：
> 注意：需要在application.yml文件中配置

```yaml
storage:
  strategy:
    type: mongo
```