class Article {
  Article({required this.title, required this.extract});

  final String title;
  final String extract;

  static List<Article> listFromJson(Map<String, Object?> json) {
    if (json case {'query': {'pages': final Map<String, Object?> pages}}) {
      return pages.values
          .whereType<Map<String, Object?>>()
          .map(
            (page) => switch (page) {
              {'title': final String title, 'extract': final String extract} =>
                Article(title: title, extract: extract),
              _ => null,
            },
          )
          .whereType<Article>()
          .toList();
    }

    throw FormatException('Could not deserialize Article, json=$json');
  }

  Map<String, Object?> toJson() => <String, Object?>{
    'title': title,
    'extract': extract,
  };

  @override
  String toString() => 'Article{title: $title, extract: $extract}';
}
