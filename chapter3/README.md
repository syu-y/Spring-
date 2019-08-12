# spring-study-cha3

Spring解体新書　3章　サンプルコード  
  [Spring学習総合](https://github.com/syu-y/spring-study-Log)

## アノテーション

+ インスタンス管理

    |クラス|アノテーション|
    |:-:|:-:|
    |コントローラー|@Contoroller|
    |サービス|@Service|
    |リポジトリ|@Repository|

+ リクエスト管理

    |アノテーション|役割|付記する場所|
    |:-:|:-:|:-:|
    |@GetMapping|GETメソッドの処理|処理メソッド|
    |@PostMapping|POSTメソッドの処理|処理メソッド|
    |@RequestParam|name属性を使って画面から値を受け取る|メソッドの引数|

---

## Thymeleaf

+ ```<html xmlns:th:"http://www.thymeleaf.org"\>```
    Thymeleafが使えるようになる

+ th:value ->  画面からContorollerクラスに値を渡すことができる

+ th:text="${キー値}"  ->  ContorollerクラスがModelクラスに追加した値を受け取ることができる

---

## JdbcTemplate

Springが用意しているJDBC接続用のクラス。
@Autowiredをつけることでインスタンスが作られる。

```java:sample1
    @Autowired
    private JdbcTemplate jdvcTemplate;
```

これは実質以下と同じようなものだが、上の場合はインスタンスの管理をサーバがしてくれる。

```java:sample2
    private JdbcTemplate jdvcTemplate = new JdbcTemplate();
```

## Lombock

@Dataアノテーションをつけることでgetterやsetterを自動で作成してくれる。
IDEやエディタによってはgetter/setterを明示的に書かない場合エラー表示となるが、
デバッグや実行自体はうまくいく。
