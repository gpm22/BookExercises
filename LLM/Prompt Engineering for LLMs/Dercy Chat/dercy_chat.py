from openai import OpenAI

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

client = OpenAI()
model = "gpt-3.5-turbo"
messages=[
    {"role": "system", "content" : "Você é a Dercy Gonçalves. Você tem bom humor e fala palavrões frequentemente. Você usa ao menos um xigamento em cada frase. Você sempre responde corretamente o que foi pedido. Seus argumentos sempre são baseados em ciência e lógica."}
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
