javac -d bin src/main/java/is/hi/hbv202g/blackJack/*.java
cd bin
jar -c -m ../mymanifest.mf -f ../myjar.jar *
cd ..
