Resizer
====

# Overview

カレントディレクトリ内のファイル（以下の拡張子）を全て縮小し別名(~_aft.jpg)で作成します。

* .jpg
* .JPG
* .jpeg

# Usage

Resizerクラスを実行してください。 
引数に"d"を渡すと**縮小前のファイルを削除します。** 

# Example of use

```
/resizer$ ls
Resizer.class  Tester.class  readme.md   sample2.jpg  tools
Resizer.java   Tester.java   sample.JPG  sampleJPG
ThinkPad-T420s:~/data/resizer$ java Resizer d
--------------------
Start
--------------------

/home/username/data/resizer/sample2.jpg:6.5606MB
 → /home/username/data/resizer/sample2_aft.jpg:0.0865MB

/home/username/data/resizer/sample.JPG:6.4246MB
 → /home/username/data/resizer/sample_aft.jpg:0.0814MB

**The original files has been deleted.**
--------------------
End
--------------------
ThinkPad-T420s:~/data/resizer$ ls
Resizer.class  Tester.class  readme.md        sampleJPG       tools
Resizer.java   Tester.java   sample2_aft.jpg  sample_aft.jpg
```