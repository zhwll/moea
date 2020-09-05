:: Copyright (c) 2013 The Chromium Embedded Framework Authors. All rights
:: reserved. Use of this source code is governed by a BSD-style license
:: that can be found in the LICENSE file.

setlocal
set bd=%cd%
set JAVA_HOME=F:\jdk\jdk-11.0.8_windows-x64_bin\jdk-11.0.8
%JAVA_HOME%/bin/java -version
%JAVA_HOME%/bin/java --module-path="F:\jdk\openjfx-11.0.2_windows-x64_bin-sdk\javafx-sdk-11.0.2\lib" --add-modules=javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web -jar "com.xfactor.moea.main-0.0.1-SNAPSHOT.jar"