課題1 Restful APIのプロジェクトです。  
OAuthAppのプロジェクトを実行後ログインし、トークンを取得してからリクエストを送ってください。
```
curl -X GET -H "Authorization: Bearer {your_token}" 'localhost:8080/api/items/'
```
