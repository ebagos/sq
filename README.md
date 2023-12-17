## SonarQubeの起動

1. DevContainerで本ワークスペースを開き直す
2. 以下を実行してElasticSearchのエラーを回避
    ```
    sudo sysctl -w vm.max_map_count=262144
    ```
3. docker composeでワークスペースルートの`docker-compose.yaml`を実行
4. 「ブラウザで開く」ボタンを押下
    - 起動直後は「機能していない」旨のメッセージが表示されるが、そのまま放置し、「SonarQube is up」が表示されたら「Home」をクリック
        - http://localhost:9001 を入力
        - ポートフォワードされている（`docker-compose.yaml`で指定したポートではない）ので注意
            - 確認は、「ポート」タブ
5. ユーザ名「admin」、パスワード「admin」でログインし、パスワードを変更
    本番では、別途ユーザを作成し以下を実行
6. 「Create a local project」を選択
    - 1ページ目の「Project key」は本番ではランダムにした方が良いかも？
    - 2ページ目は「Use grobal setting」を選択
7. 作成後の画面で「Locally」を選択
8. 「Generate」ボタンを押して、トークンを作成
9. 次の画面では「Continue」ボタンを押下
10. 次の画面で使用するツールを選択（ここではMaven）
11. Mavenの実行コマンドが表示されるので、`mvn test`実行後、表示されたコマンドを実行
    - SonarQubeのURLがポートフォワードされたものになっているので、`docker-compose.yml`で指定したもの（9000）に変更して実行する
    - テストのコードカバレッジが算出されないので、Jacocoを使用するコマンドに変更
        - `scan.sh`参照
12. SonarQubeに情報が取り込まれる

## プロジェクトdemoについて

- spring initializrで作成
- Collatz数列委の計算を提供するサービスとして付加
    - `src/main/java/com/example/demo/DemoController.java`を追加
- pom.xmlにJacocoを使用するように追加
    - カバレッジを得るため
- `scan.sh`を追加
    - sonarscan用（コメントアウト）
    - Jacoco用

つまり、テストを一切追加していない状態であることに注意

## 全てリセットしたい場合

- ホスト側でDevContainer用のコンテナを削除する
- docker volumeを削除する

## 問題

- プロジェクトで「Measures」を選択すると読み込み中のままになる

## 変更するべき点？

- Devcontainer用のディレクトリを作成してからdemoプロジェクトを作成した
- `demo`ディレクトリがプロジェクトルートになるように変更した方が良いような気がする
