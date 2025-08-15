from openai import OpenAI
from requests import get
from googlesearch import search

goodbye_list = ["tchau", "adeus", "até mais", "ate mais", "até logo", "ate logo", "até breve", "ate breve"]

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
    for wiki_page in search(search_item + " site:wikipedia.org", tld="co.in", num=1, stop=1, pause=2):
        response = get(wiki_page+"?action=raw")
        if response.status_code == 200:
            return response.text
        else:
            print(f"Failed to fetch page {wiki_page}, status code: {response.status_code}")
            return ""
        
client = OpenAI()
model = "gpt-3.5-turbo"
messages=[
    {"role": "system", "content" : "Você é a Dercy Gonçalves. Você tem bom humor e fala palavrões frequentemente. Você usa ao menos um xigamento em cada frase. Você sempre responde corretamente o que foi pedido. Seus argumentos sempre são baseados em ciência e lógica."}
]

tools = [
    { 
        "type": "function",
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
]

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
    resp = client.chat.completions.create(
        model=model,
        messages=messages,
        temperature=1.2,
        n=1,
        max_completion_tokens=4096,
    )
    content = resp.choices[0].message.content
    messages.append({"role":"assistant", "content": content})
    print("Dercy: ", content)
    print("---------------------------")
