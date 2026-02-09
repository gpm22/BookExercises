import 'dart:io';
import 'package:http/http.dart' as http;

const version = '0.0.1';

void main(List<String> arguments) {
  if(arguments.isEmpty || arguments.first == 'help') {
    printUsage();
  } else if (arguments.first == 'version'){
    print('Dartpedia CLI version $version');
  } else if (arguments.first == 'wikipedia') {
    searchWikipedia(arguments.length > 1 ? arguments.sublist(1) : null);
  } else {
    print('Unknown commands: $arguments');
    printUsage();
  }
}

void printUsage() {
  print(
    "The following commands are valid 'help', 'version', 'wikipedia <ARTICLE-TITLE>'"
  );
}

Future<String> getWikipediaArticle(String articleTitle) async {
  final url = Uri.https(
    'en.wikipedia.org',
    '/api/rest_v1/page/summary/$articleTitle',
  );

  final response = await http.get(url);

  if(response.statusCode == 200){
    return response.body;
  }

  return 'Error: Failed to fetch article "$articleTitle". Status code: ${response.statusCode}';

}

void searchWikipedia(List<String>? arguments) async{
  final String? articleTitle;
  if(arguments == null || arguments.isEmpty){
    print("please provide an article title.");
    final inputFromStdin = stdin.readLineSync();
    if(inputFromStdin == null || inputFromStdin.isEmpty){
      print('Nor article title provided. Exiting.');
      return;
    }
    articleTitle = inputFromStdin;
  } else {
    articleTitle = arguments.join(' ');
  }

  print('Looking up articles about "$articleTitle". Please wait.');

  final articleContent = await getWikipediaArticle(articleTitle);

  print('Here ya go!');
  print(articleContent);
}