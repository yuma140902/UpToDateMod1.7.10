# デプロイ手順

1. ModUpToDateMod.java の `MOD_VERSION` を変更

2. build.gradle の `version` を変更

3. `v1.2.3` という形式のタグをつけてGithubにプッシュ

4. Github Actions でビルドが行われ、リリースが作られる

5. 4.でできたリリースに変更内容を記述

   テンプレ
   ```
   English: 
   
   ## Additions
   
   ## Changes
   
   You can see the complete feature list [here](https://docs.google.com/spreadsheets/d/1OiqEOgDV31E1ZJaxhGSqJT37xrWkqHZGmpzVUvk4uZs/edit?usp=sharing).
   
   ----------
   日本語:
   
   ## 追加
   
   ## 変更
   
   このMODで追加されるすべての要素は[ここ](https://docs.google.com/spreadsheets/d/1OiqEOgDV31E1ZJaxhGSqJT37xrWkqHZGmpzVUvk4uZs/edit?usp=sharing)で見られます。
   ```

6. ローカルで `graldew build` すると通常のjar、dev環境のjar、srcのjarが生成される

7. 6.でできた3つのjarファイルをCurseForgeにアップロード。変更内容はGitHubリリースへのリンク
   
   Supported Java Versions は Java 8、1.7.10、Forgeにチェック

8. リポジトリのREADMEの最新バージョンの記述などを変更

9. アップデート確認用リポジトリのtsvに新しいバージョンを追加

    - latest、recommendの指定バージョンも忘れずに変更
    
10. フォーラムで変更を通知

   テンプレ
   ```
   [b][size=110]アップデートのお知らせ[/size][/b]
   
   [b][color=#008000]UpToDateMod[/color] v2.*.*を公開しました[/b]
   
   [b]追加[/b]
   [list]
   [*]項目1
   [*]項目2
   [/list]
   
   [b]変更[/b]
   [list]
   [*]項目1
   [*]項目2
   [/list]

   このMODで追加されるすべての要素は[url=https://docs.google.com/spreadsheets/d/1OiqEOgDV31E1ZJaxhGSqJT37xrWkqHZGmpzVUvk4uZs/edit?usp=sharing]ここ[/url]で見られます
   [url=https://www.curseforge.com/minecraft/mc-mods/uptodatemod/files]CurseForgeからダウンロード[/url]
   ```

11. フォーラムのトップの記事を更新

12. 機能リストのスプレッドシートに新しい機能について追記
