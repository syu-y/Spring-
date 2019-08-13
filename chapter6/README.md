# spring-study-chapter6

Spring解体新書　6章　サンプルコード  
  [Spring学習総合](https://github.com/syu-y/spring-study-Log)

## 追加機能とポイント

- [webjars](#webjars)
- [Bootstrap](#Bootstrap)
- [データバインド](#データバインド)
- [バリデーション（入力チェック）](#バリデーション)

## 作成したファイル

|種類|ファイル名|役割|
|:-:|:-:|:-:|
|java|LoginController|ログイン処理用コントローラークラス|
|java|SignupController|ユーザ登録処理用コントローラークラス|
|html|login|ログイン画面|
|html|signup|ユーザ登録画面|

## webjars

MavenやGradleなどのビルドツールでJavaScript、CSSなどのフレームワークを管理する仕組み  
(クライアントサイドのライブラリの依存関係を解決してくれるサービス)　　

webjarsを使うためには以下の2つのライブラリが必要

- Bootstrap : CSSフレームワーク。簡単に画面を見栄えよく作ることができる
- jQuery : JavaScriptの開発に必須のライブラリ。Bootstrapで一部jQueryが使われている。

Mavenの場合は**pom.xml**にGradleの場合は**build.gradle**の**dependenccies**に追加する

書籍ではMavenなので同じようにすればOK(verが書籍と異なる点に留意)  
Gradleの場合は次のように書く

```build.gradle
dependencies{
                …
  compile "org.webjars:jquery:2.1.4"
  compile "org.webjars:bootstrap:3.3.5"
                …
}
```

プロジェクトを更新するとライブラリが自動でダウンロードされる。  
Maven(Gradle)すげえ！

## Bootstrap

htmlでBootstrapを使うためには、jQueryとBootstrapのファイルを```<head>``` タグ内で行う必要がある。  
本来CSSやjsのファイルを利用する場合、resources配下におく必要があるが、webjarsを使うとダウンロードしたファイルをその場所に置いたまま利用できるようになる。  

ちなみに外部のライブラリを使う場合はbuild.gradleに以下の記述が必要。mavenCentralという公開されているリポジトリから必要なものを買ってに取ってきてくれる。gradleすげえ！

```build.gradle
repositories {
  mavenCentral()
}
```


## データバインド

## バリデーション
