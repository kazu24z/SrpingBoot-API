# [1章 RESTAPI作成](https://spring.pleiades.io/guides/tutorials/rest/#:~:text=%E6%A7%8B%E7%AF%89%E3%81%97%E3%81%BE%E3%81%99%E3%80%82-,%E5%85%A5%E9%96%80,-%E3%81%93%E3%81%AE%E3%83%81%E3%83%A5%E3%83%BC%E3%83%88%E3%83%AA%E3%82%A2%E3%83%AB%E3%82%92)
→[ここまで](https://spring.pleiades.io/guides/tutorials/rest/#:~:text=find%20employee%203-,%E3%81%93%E3%82%8C,-%E3%81%AF%E3%81%99%E3%81%B9%E3%81%A6%E3%81%86%E3%81%BE%E3%81%8F)

## 内容
- HTTPベースのAPIを作成
- 各エンドポイントの生成＆メソッド作成（マッピング）
- Spring Data JPAの利用

# [2章](https://spring.pleiades.io/guides/tutorials/rest/#:~:text=%E3%81%A6%E3%81%84%E3%81%BE%E3%81%99%E3%81%8B%EF%BC%9F-,RESTful%20%E3%81%AA%E3%82%82%E3%81%AE%E3%81%AF%E4%BD%95%E3%81%A7%E3%81%99%E3%81%8B%EF%BC%9F,-%E3%81%93%E3%82%8C%E3%81%BE%E3%81%A7%E3%81%AE%E3%81%A8%E3%81%93%E3%82%8D)
## 内容
- HATEOASの概念
- リソースにURI（リンクを付与）
- Spring HATEOASライブラリの利用

## テストデータ（ログ）
```
2022-09-20 12:17:33.488  INFO 3384 --- [           main] payroll.LoadDatabase                     : Preloading Employee(id=1, name=Bilbo Baggins, role=burglar)
2022-09-20 12:17:33.489  INFO 3384 --- [           main] payroll.LoadDatabase                     : Preloading Employee(id=2, name=Frodo Baggins, role=thief)
```

# [3章](https://spring.pleiades.io/guides/tutorials/rest/#:~:text=%E3%81%A7%E3%81%AF%E3%81%82%E3%82%8A%E3%81%BE%E3%81%9B%E3%82%93%E3%80%82-,%E9%80%B2%E5%8C%96%E3%81%99%E3%82%8B%20REST%20API,-1%20%E3%81%A4%E3%81%AE)  
## 内容
- APIのバーションアップに伴う、新旧データ混在時の対応

## テストデータ（ログ）
```
2022-09-20 16:12:35.704  INFO 8742 --- [           main] payroll.LoadDatabase                     : Preloading Employee(id=1, firstName=Bilbo, lastName=Baggins, name=null, role=burglar)
2022-09-20 16:12:35.706  INFO 8742 --- [           main] payroll.LoadDatabase                     : Preloading Employee(id=2, firstName=Frodo, lastName=Baggins, name=null, role=thief)
```
