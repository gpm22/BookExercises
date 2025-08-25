from requests import get
from googlesearch import search
from json import loads
from datetime import datetime

tools_definition = [
    { 
        "type": "function",
        "function": {
            "name": "get_wikipedia_page",
            "description": "Retorna a página da wikipedia mais próxima ao input dado",
            "parameters": {
                "type": "object",
                "properties": {
                    "search_item": {
                        "type": "string",
                        "description": "Input ao qual a página da wikipedia será retornado.",
                    },
                },
                "required": ["search_item"],
            },
        },
    },
    { 
        "type": "function",
        "function": {
            "name": "get_current_date_and_time",
            "description": "Retorna a data e hora atuais.",
            "parameters": {
                "type": "object",
                "properties": {},
                "required": [],
            },
        },
    },
]

def get_wikipedia_page(search_item):
    for wiki_page in search(search_item + " site:en.wikipedia.org", tld="co.in", num=1, stop=1, pause=2):
        title = wiki_page.split('/')[-1]         
        # this gets a summarized and smaller version of the wikipedia page
        url = f"https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&titles={title}"
        headers = {'User-Agent': 'DercyBot/0.0 (https://github.com/gpm22/BookExercises/blob/master/LLM/Prompt%20Engineering%20for%20LLMs/Dercy%20Chat/dercy_chat.py)'}
        response = get(url, headers=headers)
        if response.status_code == 200 and response.text:
            return response.text
        else:
            print(f"Failed to fetch page {wiki_page}, status code: {response.status_code}")
            print(f"Faile with body: {response.text}")
            return f"sem página da wikipedia para: {search_item}"
    
    return f"sem página da wikipedia para: {search_item}"

def get_current_date_and_time():
    return datetime.now()

def answer_tool_call(tool_call_function):
    match tool_call_function.name:
        case "get_wikipedia_page":
            arguments = loads(tool_call_function.arguments)
            return get_wikipedia_page(arguments["search_item"])

        case "get_current_date_and_time":
            return str(get_current_date_and_time())

        case _:
            return f"function {tool_call_function.name} does not exist"
