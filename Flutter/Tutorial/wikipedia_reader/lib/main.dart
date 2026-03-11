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
  Widget build(BuildContext context) => MaterialApp(
      theme: ThemeData(
        scaffoldBackgroundColor: Colors.black87,
        textTheme: Theme.of(
          context,
        ).textTheme.apply(bodyColor: Colors.amber, displayColor: Colors.purple),
      ),
    home: ArticleView(),
  );
}

class ArticleModel {
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

class ArticleViewModel extends ChangeNotifier {
  final ArticleModel model;
  Summary? summary;
  String? errorMessage;
  bool loading = false;

  ArticleViewModel(this.model) {
    getRandomArticleSummary();
  }

  Future<void> getRandomArticleSummary() async {
    loading = true;
    notifyListeners();

    try {
      summary = await model.getRandomArticleSUmmary();
      errorMessage = null;
    } on HttpException catch (error) {
      errorMessage = error.message;
      summary = null;
    }

    loading = false;
    notifyListeners();
  }
}

class ArticleView extends StatelessWidget {
  ArticleView({super.key});

  final viewModel = ArticleViewModel(ArticleModel());

  @override
  Widget build(BuildContext context) => Scaffold(
    appBar: AppBar(title: const Text('Wikipedia Flutter')),
    body: ListenableBuilder(
      listenable: viewModel,
      builder: (context, child) => switch ((
        viewModel.loading,
        viewModel.summary,
        viewModel.errorMessage,
      )) {
        (true, _, _) => CircularProgressIndicator(),
        (false, _, String message) => Center(child: Text(message)),
        (false, null, null) => Center(
          child: Text("An unkown error has occured"),
        ),
        (false, Summary summary, null) => ArticlePage(
          summary: summary,
          nextArticleCallback: viewModel.getRandomArticleSummary,
        ),
      },
    ),
  );
}

class ArticlePage extends StatelessWidget {
  const ArticlePage({
    super.key,
    required this.summary,
    required this.nextArticleCallback,
  });

  final Summary summary;
  final VoidCallback nextArticleCallback;

  @override
  Widget build(BuildContext context) => SingleChildScrollView(
    child: Column(
      children: [
        ArticleWidget(summary: summary),
        ElevatedButton(
          onPressed: nextArticleCallback,
          child: Text('Next random article'),
        ),
      ],
    ),
  );
}

class ArticleWidget extends StatelessWidget {
  const ArticleWidget({super.key, required this.summary});

  final Summary summary;

  @override
  Widget build(BuildContext context) => Padding(
    padding: const EdgeInsets.all(8.0),
    child: Column(
      spacing: 10.0,
      children: [
        if (summary.hasImage) Image.network(summary.originalImage!.source),
        Text(
          summary.titles.normalized,
          overflow: TextOverflow.ellipsis,
          style: TextTheme.of(context).displaySmall,
        ),
        if (summary.description != null)
          Text(
            summary.description!,
            overflow: TextOverflow.ellipsis,
            style: TextTheme.of(context).bodySmall,
          ),
        Text(summary.extract),
      ],
    ),
  );
}
