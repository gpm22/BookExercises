from openai import OpenAI
from requests import get
from googlesearch import search
from json import loads

goodbye_list = ["tchau", "adeus", "até mais", "ate mais", "até logo", "ate logo", "até breve", "ate breve"]

messages=[
    {
        "role": "system",
        "content" : """
            Você é a Dercy Gonçalves.
            Você apenas response em Português e xingamentos, mesmo se a pergunta for em outra lingua.
            Você tem bom humor, é divertida, é engraçada e fala palavrões o tempo todo.
            Você usa ao menos um xigamento em cada frase.            
            Você sempre responde corretamente o que foi pedido, sempre com palavrões.
            Teus argumentos sempre são baseados em ciência, lógica e xingamentos.
            Você alterar os textos da wikipedia para conter mais xingamentos. 
        """
        }
]

tools = [
    { 
        "type": "function",
        "function": { "name": "get_wikipedia_page",
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
]

client = OpenAI()

def get_prompt():
    contents = []
    while True:
        try:
            line = input()
            if any(sub.lower() == line.lower() for sub in goodbye_list):
                return False
            
        except EOFError:
            break
        contents.append(line)
    
    dercy = "Dercy Gonçalves"
    
    return "\n".join(contents).replace("você", dercy).replace("Você", dercy)
    
def get_wikipedia_page(search_item):
    for wiki_page in search(search_item + " site:en.wikipedia.org", tld="co.in", num=1, stop=1, pause=2):
        title = wiki_page.split('/')[-1]         
        # this gets a summarized and smaller version of the wikipedia page
        url = f"https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro&explaintext&redirects=1&titles={title}"
        response = get(url)
        if response.status_code == 200 and response.text:
            return response.text
        else:
            print(f"Failed to fetch page {wiki_page}, status code: {response.status_code}")
            return f"sem página da wikipedia para: {search_item}"

def call_model():
    return client.chat.completions.create(
        model="gpt-3.5-turbo",
        tools=tools,
        tool_choice="auto",
        messages=messages,
        temperature=1.2,
        n=1,
        max_completion_tokens=4096,
    )


if __name__ == "__main__":
    print("---------------------------")
    print("Dercy: Olá! Como posso te ajudar hoje?")
    print("---------------------------")

    while True:
        prompt = get_prompt()
        print("---------------------------")
        if not prompt:
            print("Dercy: Adeus...")
            break
            
        messages.append({"role":"user", "content": prompt})
        resp = call_model()
        
        while resp.choices[0].message.tool_calls:    
            messages.append({"role":"assistant", "tool_calls": resp.choices[0].message.tool_calls})
            for tool_call in resp.choices[0].message.tool_calls:
                if tool_call.function.name == 'get_wikipedia_page':
                    arguments = loads(tool_call.function.arguments)
                    messages.append({"role":"tool", "tool_call_id" : tool_call.id, "content": get_wikipedia_page(arguments["search_item"])})

            resp = call_model()                    
        
        content = resp.choices[0].message.content
        messages.append({"role":"assistant", "content": content})
        print("Dercy: ", content)
        print("---------------------------")
