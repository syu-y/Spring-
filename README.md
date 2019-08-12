# Spring学習ログ
 Springの学習過程をメモしていきたい。
 教材：Spring解体新書（著：田村達也）

<br>

# 環境
テキストと異なる部分は適宜読み替える
 - Chromebook + Eclipse Che
 - Winodws10 + VScode
  ※教材ではEclipse(STS)を使用している
 - gradle
   ※教材ではmavenを使用している
 - mysql
 ※教材ではH2DBを使用している

<br>

# 学習ポイント
## 1章 Springの概要
---
  - Springとは
  Javaの開発におけるFW(フレームワーク)。アプリケーションを開発する際の枠組みや土台となる。
  0からアプリを作る必要がなく、コードを短縮できる。

 - Springのいいところ
    1. [変更に強い](#4章DIについて)
    2. コードの可読性が高い
    3. 再利用性が高い

  - Springの全体像（構成ライブラリ）
    + Spring Boot (client side)
    + Spring Core (DI, AOP)（Server side）
    + Spring Security
    + Spring Data JDBC
    + Spring MVC
    + Spring batch

<br>

## 2章 開発環境の構築
---
  - 便利なライブラリたち

    |ライブラリ名|機能|
    |:--|:--|
    |Lombok|getter/setterを自動で実装してくれる|
    |DevTools| 自動でアプリケーションを再起動してくれる|
    |H2|H2DBを扱うことができる（本環境では使用しない）|
    |JDBC|Spring JDBCが利用できる|
    |Thymeleaf|SpringBoot標準のHTMLテンプレートエンジン|
    |Web|Spring Boot, Spring MVCが利用できる|
<br>

## 3章 Springを試してみよう
---
  [学習コードと詳細](https://github.com/syu-y/spring-study-cha3)

<br>

## 4章 DIについて
---
### 1. そもそもDIとは
DI (Dependency Injection)　=  依存性の注入
DIがインスタンス管理をやってくれるのでコードで明示的にnewやnullをしなくてもよくなる。
(＝楽ちん！読みやすくなる！)

具体的にはフィールドに@Autowiredアノテーションを付けるとDIコンテナ上にインスタンスが生成され、
アプリケーションはコンテナからインスタンスをもらってくる。

### 2. 依存性
あるクラスが依存しているかどうかは以下の2点で定義される。
- 他クラスをローカル変数に持っている
- メソッドの引数・戻り値に他クラスを使っている

上記の該当項目が多いことを依存度が高い（＝ **蜜結合** ）という。
