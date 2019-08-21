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
次でいうとexecution(~)の部分。

```java:aspect
@Before("execution(* *..*.*Controller.*(..))")
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
