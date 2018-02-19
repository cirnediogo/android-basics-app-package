# Android basics app package

Package of apps used in a Udacity course in the Android Basics Nanodegree by Google.

### Happy Birthday App

This app displays a happy birthday card.

### Court Counter App

This app controls a basketball match point count.

### Just Java App

This app controls coffee orders.

### Miwok App

This app displays lists of vocabulary words for the user to learn the Miwok language.

### Pets App

This app displays a list of pets and their related data that the user inputs.

### Quake Report App

This app displays a list of recent earthquakes in the world from the U.S.

# How to

Pre-requisites
--------------

- Android SDK v24
- Android Build Tools v23.0.3
- Android Support Repository v24.1.1

Getting Started
---------------

This sample uses the Gradle build system. To build this project, use the
"gradlew build" command or use "Import Project" in Android Studio.

Support
-------

- Google+ Community: https://plus.google.com/communities/105153134372062985968
- Stack Overflow: http://stackoverflow.com/questions/tagged/android

Patches are encouraged, and may be submitted by forking this project and
submitting a pull request through GitHub. Please see CONTRIBUTING.md for more details.

License
-------

Copyright 2016 The Android Open Source Project, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.

# Documentação complementar

### Criando requisições HTTP de objetos JSON

Criar o objeto URL a partir do endereço de requisição.

```java
URL url = null;
try {
  url = new URL(stringUrl);
} catch (MalformedURLException e) {
  Log.e(LOG_TAG, "Error with creating URL ", e);
}
```

Estabelecer a conexão a partir da URL.

```java
HttpURLConnection urlConnection = null;
urlConnection = (HttpURLConnection) url.openConnection();
urlConnection.setReadTimeout(10000 /* milliseconds */);
urlConnection.setConnectTimeout(15000 /* milliseconds */);
urlConnection.setRequestMethod("GET");
urlConnection.connect();
```

Verificar o status da conexão e obter a resposta no caso de requisição OK (código 200).

```java
if (urlConnection.getResponseCode() == 200) {
  InputStream inputStream = urlConnection.getInputStream();
  StringBuilder output = new StringBuilder();
  if (inputStream != null) {
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
    BufferedReader reader = new BufferedReader(inputStreamReader);
    String line = reader.readLine();
    while (line != null) {
      output.append(line);
      line = reader.readLine();
    }
  }
  String res = output.toString();
} else {
   Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
}
```

Por fim, criar o objeto JSON correspondente a resposta obtida. O objeto do tipo `JSONObject` possui diversos métodos para obtenção dos dados, como `getJSONObject(nomeAtributo)` para obter outro objeto JSON, `getJSONArray(nomeAtributo)` para obter um array, `getDouble(nomeAtributo)` para obter valores numéricos no formato double, `getString(nomeAtributo)` para obter strings, entre outros.

```java
JSONObject resJson = new JSONObject(res);
// JSONObject nome = resJson.getJSONObject("nome");
// JSONArray lista = resJson.getJSONArray("lista");
// Double valorDouble = resJson.getDouble("valor_double");
// Long valorLong = resJson.getLong("valor_long");
// String texto = resJson.getString("texto")
```
