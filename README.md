# 🥇event-2020-1-java ☕️ [![Java CI with Maven](https://github.com/b2etw/event-2020-1-java/workflows/Java%20CI%20with%20Maven/badge.svg?branch=luna&event=push)](https://github.com/b2etw/event-2020-1-java/actions)
* b2e tw 2020 first event, Java version
* cowork this project for **Slack Bot** with following functions
* [Docker Hub](https://hub.docker.com/repository/docker/taiwanbackendgroup/event-2020-1-java)

## Function
* 📈 [查詢股價](https://github.com/b2etw/event-2020-1-java/issues/1)
* 🌤 [查詢新聞和天氣](https://github.com/b2etw/event-2020-1-java/issues/2) 
* 1️⃣ [猜數字遊戲](https://github.com/b2etw/event-2020-1-java/issues/3)
* 💰 [查詢發票和對發票](https://github.com/b2etw/event-2020-1-java/issues/4) 
* 🥤 [找飲料單和訂飲料統計](https://github.com/b2etw/event-2020-1-java/issues/5)

## Prerequisite
* GitHub Account
* Slack Account

## Notation or Skill
* Slack Bot
* Web Crawler
* DDD
* TDD
* Docker, DockerHub
* CI, GitHub Action
* GitHub Flow
  * Fork, Pull Request, Code Review
* Java
  * Spring Boot, JVM Cache (Guava, Ehchche, Caffeine, etc.), Maven, jib
  
## Tool Recommendation
> 還是可以使用別的工具唷，只是我想把時間放在實作本身，如果是其他工具就沒有辦法花時間幫除錯
* IntelliJ IDEA Community or Ultimate
* Sourcetree

## References
* https://slack.dev/java-slack-sdk/guides/getting-started-with-bolt
* https://slack.dev/java-slack-sdk/guides/supported-web-frameworks
* https://api.slack.com/tutorials/tunneling-with-ngrok
* [Slack Bot Install](./doc/slack-bot-install.md)

## SLACK bot for PTT
#### 指令說明
```
/news help
```

#### 列出看板清單 (熱度前 5)
```
/news list
```

#### 列出看板最新 10 篇文章與連結
```
/news list [board]
```

#### 列出看板最新 10 篇文章與連結, 且熱度 ( >n )
```
/news list [board] hot
```