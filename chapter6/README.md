# spring-study-chapter6

Spring解体新書　6章　サンプルコード  
  [Spring学習総合](https://github.com/syu-y/spring-study-Log)

## 追加機能とポイント

- [webjars](#webjars)
- [Bootstrap](#bootstrap)
- [実装コードのポイント](#実装コードのポイント)
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
本来CSSやjsのファイルを利用する場合、resources配下におく必要があるが、webjarsを使うとダウンロードした  
ファイルをその場所に置いたまま利用できるようになる。  

ちなみに外部のライブラリを使う場合はbuild.gradleに以下の記述が必要。mavenCentralという公開されている
リポジトリから必要なものを勝手に取ってきてくれる。Gradleすげえ！  

```build.gradle
repositories {
  mavenCentral()
}
```

なので、htmlではThymeleafの属性を使ってそれぞれ読み込むことで使えるようになる。  
2行目にjQueryが挟まっているのは3行目でBootstrapのjsを呼んでいるため。

```html
  <link th:href="@{/webjars/bootstrap/3.3.5/css/bootstrap.min.css}" rel="stylesheet"></link>
  <script th:src="@{/webjars/jquery/2.1.4/jquery.min.js}"></script>
  <script th:src="@{/webjars/bootstrap/3.3.5/js/bootstrap.min.js}"></script>
```

`<body>`部分の各タグで`class="xxx"`となっているのはBootstrapが用意した設定を使っている。

## 実装コードのポイント

- Thymeleafを使ったアンカータグは`th:href="<url/Mapping Key>`を使うことができる。
- 動的なラジオボタンをThymeleafで実装
  > Controller側でMap<String, String>を用意する  
  > Mapのインスタンスに値を入れ、Modelクラスに登録(addAttribute)する  
  > html側では次のように取り出す  
  > `th:each="<変数名>:${<addAttributeしたときのキー値>}"`を使うと  
  > <変数名>を使って拡張for文のように取り出せる
- リダイレクトする場合は`return "redilect:<path>"`
  - 通常の遷移(Forward)は`return "<path>"`
- `<form>`タグの`action`属性は通常`action="<path>"`であるが、  
  SpringSecurtiyを使う場合はThymeleafの`th:acttion="@{<path>}"`を使う  
  詳細は[10章](https://github.com/syu-y/Spring-Study-Log/tree/master/chapter10)にて

## データバインド

## バリデーション
