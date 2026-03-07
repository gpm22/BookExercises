import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:http/http.dart';

import 'summary.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        scaffoldBackgroundColor: Colors.black87,
        textTheme: Theme.of(
          context,
        ).textTheme.apply(bodyColor: Colors.amber, displayColor: Colors.purple),
      ),
      home: Scaffold(
        appBar: AppBar(title: const Text('Wikipedia Flutter')),
        body: const Center(child: Text('Loading...')),
      ),
    );
  }
}

class ArticleModel {
  // todo

  Future<Summary> getRandomArticleSUmmary() async {
    final uri = Uri.https(
      'en.wikipedia.org',
      '/api/rest_v1/page/random/summary',
    );

    final response = await get(uri);

    if (response.statusCode != 200) {
      throw HttpException(
        'Failed to fetch random article.\nstatus code=${response.statusCode}\nbody:${response.body}',
      );
    }

    return Summary.fromJson(jsonDecode(response.body));
  }
}
