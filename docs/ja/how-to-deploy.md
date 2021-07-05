# デプロイ手順

1. ModUpToDateMod.java の `MOD_VERSION` を変更

2. build.gradle の `version` を変更

3. `v1.2.3` という形式のタグをつけてGithubにプッシュ

4. Github Actions でビルドが行われ、リリースが作られる

5. 4.でできたリリースに変更内容を記述

   テンプレ
   ```
   English: 
   
   ----------
   日本語:
   
   ```

6. ローカルで `graldew build` すると通常のjar、dev環境のjar、srcのjarが生成される

7. 6.でできた3つのjarファイルをCurseForgeにアップロード。変更内容を記述
   
   Supported Java Versions は Java 8、1.7.10にチェック

9. リポジトリのREADMEの最新バージョンの記述などを変更

10. アップデート確認用リポジトリのtsvに新しいバージョンを追加

    - latest、recommendの指定バージョンも忘れずに変更
    
11. フォーラムで変更を通知

   テンプレ
   ```
   [b][size=110]アップデートのお知らせ[/size][/b]
   
   [b][color=#008000]UpToDateMod[/color] v2.*.*を公開しました[/b]
   [list]
   [*]項目1
   [*]項目2
   [/list]

   [url=https://www.curseforge.com/minecraft/mc-mods/uptodatemod/files/******]CurseForgeからダウンロード[/url]
   ```

12. フォーラムのトップの記事を更新

13. 機能リストのスプレッドに新しい機能について追記
