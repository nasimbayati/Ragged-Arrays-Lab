@echo off
REM ============================================
REM Build script for ragged-arrays-lab
REM ============================================

if not exist bin mkdir bin

echo Compiling Java files...
javac -d bin src\App.java src\arrays\RaggedArrays.java
if %ERRORLEVEL% neq 0 (
  echo.
  echo Compilation FAILED.
  pause
  exit /b %ERRORLEVEL%
)

echo.
echo Compilation SUCCESSFUL.
echo.
echo Examples:
echo   java -cp bin src.App fill 2 5 5
echo   java -cp bin src.App auto 3 5
pause
