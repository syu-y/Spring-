Springの学習過程をメモしていく。  
テキストはSpring解体新書 (著：田村達也）kindle Unlimitedなら無料で読める。  
買ったとしても500円なのでおすすめ。

# 環境

以下使用環境。テキストと異なる部分は適宜読み替える

> - Chromebook + Eclipse Che
> - Winodws10 + VScode  
>  ※教材ではEclipse(STS)を使用している
> - gradle  
>  ※教材ではmavenを使用している
>- mysql  
> ※教材ではH2DBを使用している

## 1章 Springの概要

- Springとは
  Javaの開発におけるFW(フレームワーク)。アプリケーションを開発する際の枠組みや土台となる。  
  0からアプリを作る必要がなく、コードを短縮できる。

- Springのいいところ
  1. [変更に強い](#4章-DIについて)
  2. コードの可読性が高い
  3. 再利用性が高い

- Springの全体像（構成ライブラリ）
  - Spring Boot (client side)
  - Spring Core (DI, AOP)（Server side）
  - Spring Security
  - Spring Data JDBC
  - Spring MVC
  - Spring batch

## 2章 開発環境の構築

- 便利なライブラリたち

  |ライブラリ名|機能|
  |:--|:--|
  |Lombok|getter/setterを自動で実装してくれる|
  |DevTools| 自動でアプリケーションを再起動してくれる|
  |H2|H2DBを扱うことができる（本環境では使用しない）|
  |JDBC|Spring JDBCが利用できる|
  |Thymeleaf|SpringBoot標準のHTMLテンプレートエンジン|
  |Web|Spring Boot, Spring MVCが利用できる|

## 3章 Springを試してみよう

簡単なサンプルコードを使って、Springですぐに動くアプリケーションを開発する体験。  
DBにも少し触れる。詳しくは以下で。 
[学習コードと詳細](https://github.com/syu-y/Spring-Study-Log/tree/master/chapter3)

## 4章 DIについて

### 1. そもそもDIとは

DI (Dependency Injection)　=  依存性の注入
DIがインスタンス管理をやってくれるので  
コードで明示的にnewやnullをしなくてもよくなる。  
(＝楽ちん！読みやすくなる！)

具体的にはフィールドに@Autowiredアノテーションを付けるとDIコンテナ上に  
インスタンスが生成され、アプリケーションはコンテナからインスタンスをもらってくる。

### 2. 依存性

あるクラスが依存しているかどうかは以下の2点で定義される。

- 他クラスをローカル変数に持っている
- メソッドの引数・戻り値に他クラスを使っている

上記の該当項目が多いことを依存度が高い（＝ **蜜結合** ）という。

一般的に、依存度が高いと以下の点で不便なのでインターフェースを  
間に挟んでクラスを複数生成することで結合度を下げる。

- 変更に弱い
  - 修正点が増える
  - テストしにくい

### 3. 注入

変数にインスタンスの参照値を持たせること。例えば以下のように。

```java:injection
  ClassA classA = new classA();
```

より多くのインスタンスを生成する必要が出てきた場合、上記のようなコード
を羅列した場合可読性が悪く、テストもしにくくなり、修正量も増えてしまう。

```java:moreinjection
  ClassA classA = new classA();
  ClassA classB = new classB();
  ClassA classC = new classC();
```

そこで、一般的には、デザインパターンのうちFactory Methodパターンを使う。  
[デザインパターンについては次で学習する予定](https://github.com/syu-y/Study-DesignPattern)

### 4. DIのお仕事

1. **コンポーネントスキャン**

  Springが起動すると走る処理。  
  アノテーションによってDIが管理するクラスを探す。  
  以下管理対象となるアノテーションの一部。

  ```@Component, @Controller, @Service, @Repository...```

  上記のようなアノテーションがついているクラスを**Bean**と呼ぶ。  
  （アノテーション以外にもDIに登録する方法あり）  
  
2. **インスタンスの生成と注入**

  スキャンが完了すると、次にBeanのインスタンス生成と注入が行われる。
  つまり、次のようなコードが展開されるイメージ。

  ```java:injectionsample
    private SomeControllerClass controller = new SomeControllerClass();
    private SomeServiceClass service = new SomeServiceClass();
    private SomeRepositoryClass repository = new SomeRepositoryrClass();
  ```

  この機能のおかげでいちいちデザインパターンを意識しなくてもインスタンス管理が楽にできる。

  また、```@Autowired```がついたフィールドも管理を行ってくれる。
  ```@Autowired```がつけられるのは以下の3つの場合

- フィールド変数
- コンストラクタの引数
- setterの引数

### 5. DIの注意点

Springがインスタンスの管理を勝手にやってくれる分、気を付けないといけない点もある。

1. Singleton
Springでは```@Scope("スコープ名")```でインスタンスのスコープを管理している。  
デフォルトはSingletonなので、付け忘れるとリクエストが同時にあった場合に対応できなくなる。

2. Scopeの違い
あるクラスが```@Scope("prototype")```にもかかわらず、そのクラスを他の```@Scope("singleton")```な  
（あるいは付け忘れ）クラスがフィールドに持っていた場合、所有しているクラスのスコープに  
上書きされてしまう。（requestとsessionでも同様の問題が発生）そのため、スコープの異なる  
Beanを扱う際には注意が必要である。

## 5章 Springを試してみよう

6章から最後の13章までで作成するアプリケーションの概要。  
章ごとに機能を少しずつ付け加えていく。詳しくは各章にて。

## 6章 データバインドとバリデーション

まずはログイン画面とアカウント登録画面を作成する。
[学習コードと詳細](https://github.com/syu-y/Spring-Study-Log/tree/master/chapter6)
