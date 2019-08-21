# spring-study-chapter6

Spring解体新書　7章　サンプルコード  
  [Spring学習総合](https://github.com/syu-y/spring-study-Log)

## 追加機能とポイント

- [AOP](#AOP)
- [Advice](#Advice)
- [Pointcut](#Pointcut)
- [JoinPoint](#JoinPoint)

## 作成したファイル

|種類|ファイル名|役割|
|:-:|:-:|:-:|
|java|LogAspect|ログ表示処理クラス|

## AOP

Asect Oriented Programming : 共通の処理をまとめて管理できる仕組み

- メリット
  - 冗長なコードをまとめる
  - コードの修正量を抑える
  - 可読性の向上

- 共通処理の例
  - ログ出力
  - セキュリティ
  - トランザクション
  - 例外処理

## Advice

AOPのクラスで実行する処理。  
処理内容は@Aspectを付けたクラスのメソッドの中に記述する。

## Pointcut

処理を実行する場所（クラス・メソッド）。  
次で説明するJointPointを示すアノテーションのオプションとして記述する。  
次でいうと`executio(~)`の部分。

```java:aspect
@Before("execution(* *..*.*Controller.*(..))")
```

executionを含め、Pointcutには以下の4種類の指定方法が存在する。  

|指定方法|指定単位|説明|
|---|---|---|
|execution|クラス・メソッド|正規表現を用いてクラス・メソッドを指定|
|bean|クラス|DIコンテナに登録した(eg. @Component/@Controllerがついた)クラスを指定|
|@annotation|メソッド|指定したアノテーション(eg. @GetMappingなど)がついているメソッドを指定|
|@within|クラス|指定したアノテーション(eg. @GetMappingなど)がついているクラスのメソッドを全て指定|

@annotationと@withinで指定する場合は以下のようにフルパッケージ名で指定する必要がある。

```java:aop
@Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")
@After("@within(org.springframework.stereotype.Controller)")
```

executionとbeanで指定する場合は正規表現を用いて指定することが可能。  
例えばControllerクラスのすべてのメソッドを対象にしたい場合は以下のように記述する。

```java:aop
// execution(<戻り値> <パッケージ名>.<クラス名>.<メソッド名>(<引数>))
@Before("execution(* *..*.*Controller.*(..))")
// bean(<クラス名>)
@After("bean(*Controller)")
```

## JoinPoint

処理を実行するタイミング。
以下の5種類の実行タイミングを指定できる。

|実行タイミング|説明|
|---|---|
|Before|メソッド実行前|
|After|メソッド実行後|
|Around|メソッド実行の前後|
|AfterReturning|メソッド正常終了時|
|AfterThrowing|メソッド異常終了時|

Aroundの場合はAOP対象の処理を@Aroundをつけたメソッドの中で実行する必要がある。  
特に、戻り値があるメソッドが対象の場合には自分で戻り値を返す処理を書く必要がある点には注意が必要である。
