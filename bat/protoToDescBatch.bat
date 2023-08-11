@echo off

REM 指定 .proto 文件所在目录
set PROTO_DIR=./proto

REM 生成 .desc 文件的输出目录
set OUTPUT_DIR=./desc

REM 创建输出目录
mkdir %OUTPUT_DIR%

REM 遍历 .proto 文件并生成 .desc 文件
for %%F in (%PROTO_DIR%\*.proto) do (
    protoc -o%OUTPUT_DIR%\%%~nxF.desc "%%F"
)
